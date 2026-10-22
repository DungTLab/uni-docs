package controller;

import dao.StaffDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Staff;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        if ("logout".equals(status)) {
            request.setAttribute("logoutMsg", "You have successfully logged out. Please login again.");
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String rawPassword = request.getParameter("password");

        StaffDAO staffDao = new StaffDAO();

        Staff loggedInstaff = staffDao.login(username, rawPassword);

        if (loggedInstaff == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            HttpSession session = request.getSession();

            session.setAttribute("loggedInStaff", loggedInstaff);

            response.sendRedirect(request.getContextPath() + "/shoes");

        }
    }
}
