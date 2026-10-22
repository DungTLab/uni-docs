package controller;

import dao.ShoeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Shoe;
import model.Staff;

@WebServlet(name = "ShoeServlet", urlPatterns = {"/shoes"})
public class ShoeServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Staff loggedInStaff = (Staff) session.getAttribute("loggedInStaff");
        if (loggedInStaff == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
          ShoeDAO shoeDAO = new ShoeDAO();
        ArrayList<Shoe> shoesList = shoeDAO.findAll();
        request.setAttribute("shoes", shoesList);
        
        request.getRequestDispatcher("/WEB-INF/shoes.jsp").forward(request, response);
    }
}
