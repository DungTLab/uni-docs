package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 * LoginServlet - Xử lý đăng nhập, hỗ trợ redirect 302 khi thất bại 
 * để đáp ứng tiêu chuẩn phản hồi HTTP 300-series theo yêu cầu của thầy giáo.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Lấy thông báo lỗi từ session (sau khi redirect 302) và xoá ngay lập tức
            String errorMessage = (String) session.getAttribute("errorMessage");
            if (errorMessage != null) {
                request.setAttribute("errorMessage", errorMessage);
                session.removeAttribute("errorMessage");
            }

            // Đọc và chuyển tiếp giá trị username gõ sai rồi xoá khỏi session
            String usernameVal = (String) session.getAttribute("usernameVal");
            if (usernameVal != null) {
                request.setAttribute("usernameVal", usernameVal);
                session.removeAttribute("usernameVal");
            }

            // Lấy thông báo thành công (ví dụ đăng ký xong redirect qua) và xoá ngay
            String successMessage = (String) session.getAttribute("successMessage");
            if (successMessage != null) {
                request.setAttribute("successMessage", successMessage);
                session.removeAttribute("successMessage");
            }

            // Nếu đã đăng nhập rồi -> chuyển qua trang danh sách sản phẩm
            if (session.getAttribute("loggedInUser") != null) {
                response.sendRedirect(request.getContextPath() + "/products");
                return;
            }
        }

        // Đọc cookie ghi nhớ tài khoản
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("rememberUser".equals(c.getName())) {
                    request.setAttribute("usernameCookie", c.getValue());
                    request.setAttribute("rememberMeChecked", "checked");
                    break;
                }
            }
        }

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String pass = request.getParameter("password");
        String remember = request.getParameter("rememberMe");

        HttpSession session = request.getSession(true);

        if (userName == null || userName.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Username and password cannot be empty!");
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect 302
            return;
        }

        UserDAO userDAO = new UserDAO();
        User loggedInUser = userDAO.login(userName.trim(), pass);

        if (loggedInUser != null) {
            if (!loggedInUser.isStatus()) {
                session.setAttribute("errorMessage", "This account has been locked!");
                response.sendRedirect(request.getContextPath() + "/login"); // Redirect 302
                return;
            }

            session.setAttribute("loggedInUser", loggedInUser);

            // Ghi nhớ cookie username
            Cookie cookieUser = new Cookie("rememberUser", userName.trim());
            if ("on".equals(remember)) {
                cookieUser.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cookieUser.setMaxAge(0);
            }
            response.addCookie(cookieUser);

            // Cookie theme giống dự án WebPRJ
            Cookie themeCookie = new Cookie("theme", "dark");
            themeCookie.setMaxAge(60 * 60 * 24 * 2);
            response.addCookie(themeCookie);

            // Xoá trường tên đăng nhập tạm thời khi thành công
            session.removeAttribute("usernameVal");

            response.sendRedirect(request.getContextPath() + "/products"); // Redirect 302
        } else {
            // Đăng nhập thất bại -> Lưu lỗi vào session và REDIRECT 302 về trang login
            session.setAttribute("errorMessage", "Invalid username or password!");
            session.setAttribute("usernameVal", userName); // Lưu lại giá trị đã nhập vào session để đổ lại
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect 302
        }
    }
}
