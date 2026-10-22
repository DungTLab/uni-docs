/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DungLT
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = request.getParameter("view");
        if (view == null) {
            request.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        String userName = request.getParameter("userName");
        String rawPassword = request.getParameter("password");

        User loggedInUser = dao.login(userName, rawPassword);

        if (loggedInUser == null) {

            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            HttpSession session = request.getSession();

            session.setAttribute("loggedInUser", loggedInUser);

            Cookie themeCookie = new Cookie("theme", "dark");
            themeCookie.setMaxAge(60 * 60 * 24 * 2); //tinh bang giay 

            response.addCookie(themeCookie);

            response.sendRedirect(request.getContextPath() + "/artist");

        }
    }

}
