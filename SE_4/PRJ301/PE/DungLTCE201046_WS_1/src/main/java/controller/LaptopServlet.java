package controller;

import dao.LaptopDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import model.Laptop;

@WebServlet(name = "LaptopServlet", urlPatterns = {"/laptops"})
public class LaptopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LaptopDAO dao = new LaptopDAO();
        String action = request.getParameter("action");
        if (action == null) {
            action = request.getParameter("view");
        }
        
        if (action == null || action.equals("list")) {
            List<Laptop> list = dao.findAll();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/laptops.jsp").forward(request, response);

        } else if (action.equals("create")) {
            request.getRequestDispatcher("/WEB-INF/add-laptop.jsp").forward(request, response);
            
        } else if (action.equals("update") || action.equals("edit")) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Laptop laptop = dao.findById(id);
                    if (laptop != null) {
                        request.setAttribute("laptop", laptop);
                        request.getRequestDispatcher("/WEB-INF/update-laptop.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/laptops");
            
        } else if (action.equals("delete")) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Laptop laptop = dao.findById(id);
                    if (laptop != null) {
                        request.setAttribute("laptop", laptop);
                        request.getRequestDispatcher("/WEB-INF/delete-laptop.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/laptops");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LaptopDAO dao = new LaptopDAO();
        
        if (action.equals("create")) {
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String releaseYearStr = request.getParameter("release-year");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");

            if (brand == null || brand.trim().isEmpty() ||
                model == null || model.trim().isEmpty() ||
                releaseYearStr == null || releaseYearStr.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty()) {

                request.setAttribute("error", "All fields are required and cannot be empty.");
                request.getRequestDispatcher("/WEB-INF/add-laptop.jsp").forward(request, response);
                return;
            }

            try {
                int releaseYear = Integer.parseInt(releaseYearStr.trim());
                double price = Double.parseDouble(priceStr.trim());
                int quantity = Integer.parseInt(quantityStr.trim());

                int currentYear = java.time.Year.now().getValue();

                if (releaseYear > currentYear || price <= 0 || quantity < 0) {
                    request.setAttribute("error", "Validation failed: Release Year must be <= " + currentYear + ", Price must be > 0, and Quantity must be >= 0.");
                    request.getRequestDispatcher("/WEB-INF/add-laptop.jsp").forward(request, response);
                    return;
                }

                Laptop laptop = new Laptop(0, brand, model, releaseYear, price, quantity);
                dao.insert(laptop);
                response.sendRedirect(request.getContextPath() + "/laptops");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Validation failed: Release Year, Price, and Quantity must be valid numeric values.");
                request.getRequestDispatcher("/WEB-INF/add-laptop.jsp").forward(request, response);
            }
            
        } else if (action.equals("update") || action.equals("edit")) {
            String idStr = request.getParameter("id");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String releaseYearStr = request.getParameter("release-year");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");

            if (idStr == null || brand == null || brand.trim().isEmpty() ||
                model == null || model.trim().isEmpty() ||
                releaseYearStr == null || releaseYearStr.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty()) {

                request.setAttribute("error", "All fields are required and cannot be empty.");
                try {
                    int id = Integer.parseInt(idStr);
                    Laptop laptop = new Laptop(id, brand, model, 0, 0.0, 0);
                    request.setAttribute("laptop", laptop);
                } catch (Exception e) {}
                request.getRequestDispatcher("/WEB-INF/update-laptop.jsp").forward(request, response);
                return;
            }

            try {
                int id = Integer.parseInt(idStr.trim());
                int releaseYear = Integer.parseInt(releaseYearStr.trim());
                double price = Double.parseDouble(priceStr.trim());
                int quantity = Integer.parseInt(quantityStr.trim());

                int currentYear = java.time.Year.now().getValue();

                if (releaseYear > currentYear || price <= 0 || quantity < 0) {
                    request.setAttribute("error", "Validation failed: Release Year must be <= " + currentYear + ", Price must be > 0, and Quantity must be >= 0.");
                    Laptop laptop = new Laptop(id, brand, model, releaseYear, price, quantity);
                    request.setAttribute("laptop", laptop);
                    request.getRequestDispatcher("/WEB-INF/update-laptop.jsp").forward(request, response);
                    return;
                }

                Laptop laptop = new Laptop(id, brand, model, releaseYear, price, quantity);
                dao.update(laptop);
                response.sendRedirect(request.getContextPath() + "/laptops");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Validation failed: Numeric formats are incorrect.");
                try {
                    int id = Integer.parseInt(idStr);
                    Laptop laptop = new Laptop(id, brand, model, 0, 0.0, 0);
                    request.setAttribute("laptop", laptop);
                } catch (Exception ex) {}
                request.getRequestDispatcher("/WEB-INF/update-laptop.jsp").forward(request, response);
            }
            
        } else if (action.equals("delete")) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    dao.delete(id);
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/laptops");
        }
    }
}
