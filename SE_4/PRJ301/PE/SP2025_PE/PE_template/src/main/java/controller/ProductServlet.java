package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Category;
import model.Product;
import model.User;

/**
 * ProductServlet - Unified Controller xử lý CRUD sản phẩm.
 * Hỗ trợ tham số điều hướng "action" hoặc "view".
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        // Đọc tham số điều phối (action hoặc view)
        String action = request.getParameter("action");
        if (action == null) {
            action = request.getParameter("view");
        }

        // Định hướng views
        if (action == null || "list".equals(action) || "read".equals(action)) {
            // Lấy toàn bộ danh sách sản phẩm và danh mục (Không phân trang/lọc mặc định)
            List<Product> products = productDAO.getAll();
            List<Category> categories = categoryDAO.getAll();

            request.setAttribute("productList", products);
            request.setAttribute("categoryList", categories);
            request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);

        } else if ("create".equals(action) || "add".equals(action)) {
            request.setAttribute("categoryList", categoryDAO.getAll());
            request.getRequestDispatcher("/WEB-INF/add-product.jsp").forward(request, response);

        } else if ("update".equals(action) || "edit".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = productDAO.getById(id);
                    if (product != null) {
                        request.setAttribute("product", product);
                        request.setAttribute("categoryList", categoryDAO.getAll());
                        request.getRequestDispatcher("/WEB-INF/update-product.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/products");

        } else if ("delete".equals(action) || "remove".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr == null) {
                idStr = request.getParameter("productId");
            }
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = productDAO.getById(id);
                    if (product != null) {
                        request.setAttribute("product", product);
                        request.getRequestDispatcher("/WEB-INF/delete-product.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/products");
        } else {
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Kiểm tra quyền hạn ghi dữ liệu (Admin)
        HttpSession session = request.getSession(false);
        User loggedInUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin role required!");
            return;
        }

        String action = request.getParameter("action");
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        if ("create".equals(action) || "add".equals(action)) {
            String name = request.getParameter("name");
            String categoryIdStr = request.getParameter("categoryId");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String image = request.getParameter("image");
            String statusStr = request.getParameter("status");
            String description = request.getParameter("description");

            boolean hasError = false;
            Product p = new Product();
            p.setName(name);
            p.setImage(image);
            p.setDescription(description);

            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("errName", "Product name cannot be empty!");
                hasError = true;
            } else if (productDAO.checkDuplicateName(name.trim(), 0)) {
                request.setAttribute("errName", "Product name already exists!");
                hasError = true;
            }

            int categoryId = 0;
            try {
                categoryId = Integer.parseInt(categoryIdStr);
                p.setCategoryId(categoryId);
            } catch (NumberFormatException e) {
                request.setAttribute("errCategory", "Please select a valid category!");
                hasError = true;
            }

            double price = 0;
            try {
                price = Double.parseDouble(priceStr);
                p.setPrice(price);
                if (price <= 0) {
                    request.setAttribute("errPrice", "Price must be greater than 0!");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errPrice", "Price must be a valid number!");
                hasError = true;
            }

            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityStr);
                p.setQuantity(quantity);
                if (quantity < 0) {
                    request.setAttribute("errQuantity", "Quantity must be >= 0!");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errQuantity", "Quantity must be a valid integer!");
                hasError = true;
            }

            boolean status = "on".equals(statusStr) || "true".equals(statusStr);
            p.setStatus(status);

            if (hasError) {
                request.setAttribute("product", p);
                request.setAttribute("categoryList", categoryDAO.getAll());
                request.setAttribute("generalError", "Please correct the errors below.");
                request.getRequestDispatcher("/WEB-INF/add-product.jsp").forward(request, response);
                return;
            }

            boolean success = productDAO.insert(p) > 0;
            if (success) {
                session.setAttribute("toastSuccess", "Product added successfully!");
            } else {
                session.setAttribute("toastError", "Failed to add product!");
            }
            response.sendRedirect(request.getContextPath() + "/products");

        } else if ("update".equals(action) || "edit".equals(action)) {
            String productIdStr = request.getParameter("productId");
            String name = request.getParameter("name");
            String categoryIdStr = request.getParameter("categoryId");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String image = request.getParameter("image");
            String statusStr = request.getParameter("status");
            String description = request.getParameter("description");

            int productId = Integer.parseInt(productIdStr);
            boolean hasError = false;
            Product p = new Product();
            p.setId(productId);
            p.setName(name);
            p.setImage(image);
            p.setDescription(description);

            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("errName", "Product name cannot be empty!");
                hasError = true;
            } else if (productDAO.checkDuplicateName(name.trim(), productId)) {
                request.setAttribute("errName", "Product name already exists!");
                hasError = true;
            }

            int categoryId = 0;
            try {
                categoryId = Integer.parseInt(categoryIdStr);
                p.setCategoryId(categoryId);
            } catch (NumberFormatException e) {
                request.setAttribute("errCategory", "Please select a valid category!");
                hasError = true;
            }

            double price = 0;
            try {
                price = Double.parseDouble(priceStr);
                p.setPrice(price);
                if (price <= 0) {
                    request.setAttribute("errPrice", "Price must be greater than 0!");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errPrice", "Price must be a valid number!");
                hasError = true;
            }

            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityStr);
                p.setQuantity(quantity);
                if (quantity < 0) {
                    request.setAttribute("errQuantity", "Quantity must be >= 0!");
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errQuantity", "Quantity must be a valid integer!");
                hasError = true;
            }

            boolean status = "on".equals(statusStr) || "true".equals(statusStr);
            p.setStatus(status);

            if (hasError) {
                request.setAttribute("product", p);
                request.setAttribute("categoryList", categoryDAO.getAll());
                request.setAttribute("generalError", "Please correct the errors below.");
                request.getRequestDispatcher("/WEB-INF/update-product.jsp").forward(request, response);
                return;
            }

            boolean success = productDAO.update(p);
            if (success) {
                session.setAttribute("toastSuccess", "Product updated successfully!");
            } else {
                session.setAttribute("toastError", "Failed to update product!");
            }
            response.sendRedirect(request.getContextPath() + "/products");

        } else if ("delete".equals(action) || "remove".equals(action)) {
            String productIdStr = request.getParameter("productId");
            if (productIdStr == null) {
                productIdStr = request.getParameter("id");
            }
            if (productIdStr != null) {
                try {
                    int productId = Integer.parseInt(productIdStr);
                    boolean success = productDAO.delete(productId);
                    if (success) {
                        session.setAttribute("toastSuccess", "Product deleted successfully!");
                    } else {
                        session.setAttribute("toastError", "Failed to delete product!");
                    }
                } catch (NumberFormatException e) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }
}
