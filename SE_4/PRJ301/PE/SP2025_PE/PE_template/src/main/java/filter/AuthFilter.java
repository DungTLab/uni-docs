package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 * AuthFilter - Bộ lọc bảo mật phân quyền.
 * Sử dụng Session attribute key "loggedInUser" và tham số "view/action" động
 * tương thích với cấu trúc điều hướng hợp nhất.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        
        // Kiểm tra xem request có truy cập Servlet quản lý sản phẩm hay không
        boolean isProductPath = uri.endsWith("/products") || uri.endsWith("/products/");

        if (isProductPath) {
            String view = req.getParameter("view");
            String action = req.getParameter("action");
            
            // Các giá trị tham số yêu cầu quyền Admin để thực hiện (hỗ trợ nhiều tên gọi)
            boolean requiresAdmin = "create".equals(view) || "add".equals(view)
                                 || "update".equals(view) || "edit".equals(view)
                                 || "delete".equals(view) || "remove".equals(view)
                                 || "create".equals(action) || "add".equals(action)
                                 || "update".equals(action) || "edit".equals(action)
                                 || "delete".equals(action) || "remove".equals(action);

            if (requiresAdmin) {
                User loggedInUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
                
                // 1. Chưa đăng nhập -> Chuyển hướng 302 về trang login
                if (loggedInUser == null) {
                    HttpSession newSession = req.getSession(true);
                    newSession.setAttribute("errorMessage", "Please login with an Admin account to proceed!");
                    res.sendRedirect(contextPath + "/login");
                    return;
                }
                
                // 2. Đã đăng nhập nhưng không phải ADMIN -> Đẩy về trang danh sách và báo lỗi
                if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                    HttpSession activeSession = req.getSession();
                    activeSession.setAttribute("toastError", "Access Denied: You do not have permissions for this action!");
                    res.sendRedirect(contextPath + "/products");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
