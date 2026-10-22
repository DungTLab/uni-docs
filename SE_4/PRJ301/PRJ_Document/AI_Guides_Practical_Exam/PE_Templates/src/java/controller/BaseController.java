package controller;

import dao.BaseDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Mẫu Servlet chuẩn xử lý đầy đủ List, Create, Update, Delete.
 * Đổi 'BaseController' thành tên Servlet thực tế.
 * Đổi '@WebServlet(urlPatterns = {"/base"})' theo yêu cầu đề bài.
 */
@WebServlet(urlPatterns = {"/base"})
public class BaseController extends HttpServlet {

    /**
     * Phương thức GET: Hiển thị danh sách HOẶC hiển thị Form thêm/sửa
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        BaseDAO dao = new BaseDAO(); // Đổi thành DAO thực tế
        
        try {
            if (action == null || action.equals("list")) {
                // XỬ LÝ HIỂN THỊ DANH SÁCH
                List<Object> list = dao.getAll(); // Chuyển Object thành Entity thực tế
                request.setAttribute("dataList", list);
                request.getRequestDispatcher("list_template.jsp").forward(request, response);
                
            } else if (action.equals("create")) {
                // HIỂN THỊ FORM THÊM MỚI (chưa có dữ liệu)
                request.getRequestDispatcher("form_template.jsp").forward(request, response);
                
            } else if (action.equals("edit")) {
                // HIỂN THỊ FORM CẬP NHẬT (cần lấy id và load dữ liệu cũ lên)
                int id = Integer.parseInt(request.getParameter("id"));
                Object item = dao.getById(id);
                request.setAttribute("item", item);
                request.getRequestDispatcher("form_template.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                // XỬ LÝ XÓA VÀ QUAY LẠI TRANG DANH SÁCH
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("base"); // 'base' là urlPattern của servlet này
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi, chuyển hướng sang trang báo lỗi (tùy đề)
        }
    }

    /**
     * Phương thức POST: Xử lý khi Form Submit (Create hoặc Update)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8"); // Sửa lỗi font tiếng Việt (nếu có)
        String action = request.getParameter("action");
        BaseDAO dao = new BaseDAO();
        
        try {
            // Lấy dữ liệu từ Form (ĐỔI TÊN THAM SỐ)
            String name = request.getParameter("name");
            // String col2Str = request.getParameter("col2");
            // int col2 = Integer.parseInt(col2Str);
            
            // Validate dữ liệu cơ bản (nếu đề yêu cầu)
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("error", "Name cannot be empty!");
                request.getRequestDispatcher("form_template.jsp").forward(request, response);
                return;
            }

            if (action.equals("create")) {
                // Khởi tạo đối tượng và gọi Insert
                // Entity obj = new Entity(name, col2);
                // dao.insert(obj);
                
            } else if (action.equals("edit")) {
                // Lấy thêm ID bị ẩn trong form để Update
                // int id = Integer.parseInt(request.getParameter("id"));
                // Entity obj = new Entity(id, name, col2);
                // dao.update(obj);
            }
            
            // CHÚ Ý CHUẨN: Sau khi thay đổi CSDL thành công, luôn dùng sendRedirect để tránh lỗi F5 (Duplicate submission)
            response.sendRedirect("base");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "System error: " + e.getMessage());
            request.getRequestDispatcher("form_template.jsp").forward(request, response);
        }
    }
}
