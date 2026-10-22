package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 * RegisterServlet - Đăng ký tài khoản, hỗ trợ redirect 302 chuẩn PRG.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String errorMessage = (String) session.getAttribute("errorMessage");
            if (errorMessage != null) {
                request.setAttribute("errorMessage", errorMessage);
                session.removeAttribute("errorMessage");
            }

            // Đọc và chuyển tiếp các giá trị gõ tạm thời rồi xoá khỏi session
            String usernameVal = (String) session.getAttribute("usernameVal");
            if (usernameVal != null) {
                request.setAttribute("usernameVal", usernameVal);
                session.removeAttribute("usernameVal");
            }
            
            String displayNameVal = (String) session.getAttribute("displayNameVal");
            if (displayNameVal != null) {
                request.setAttribute("displayNameVal", displayNameVal);
                session.removeAttribute("displayNameVal");
            }
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");
        String displayName = request.getParameter("displayName");

        HttpSession session = request.getSession();
        session.setAttribute("usernameVal", userName);
        session.setAttribute("displayNameVal", displayName);

        if (userName == null || userName.trim().isEmpty() 
            || pass == null || pass.trim().isEmpty() 
            || displayName == null || displayName.trim().isEmpty()) {
            session.setAttribute("errorMessage", "All fields are required!");
            response.sendRedirect(request.getContextPath() + "/register"); // Redirect 302
            return;
        }

        userName = userName.trim();
        displayName = displayName.trim();

        if (!pass.equals(confirmPass)) {
            session.setAttribute("errorMessage", "Passwords do not match!");
            response.sendRedirect(request.getContextPath() + "/register"); // Redirect 302
            return;
        }

        UserDAO userDAO = new UserDAO();
        if (userDAO.checkDuplicateUsername(userName)) {
            session.setAttribute("errorMessage", "Username already exists!");
            response.sendRedirect(request.getContextPath() + "/register"); // Redirect 302
            return;
        }

        User user = new User(null, userName, pass, displayName, "USER", true);
        boolean success = userDAO.register(user);

        if (success) {
            // Xoá các trường lưu tạm khi đăng ký thành công
            session.removeAttribute("usernameVal");
            session.removeAttribute("displayNameVal");
            
            session.setAttribute("successMessage", "Registration successful! Please login.");
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect 302
        } else {
            session.setAttribute("errorMessage", "System error. Please try again.");
            response.sendRedirect(request.getContextPath() + "/register"); // Redirect 302
        }
    }
}
