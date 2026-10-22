package util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 📚 SỔ TAY CHINH CHIẾN PE (Xem trực tiếp trong NetBeans)
 * 
 * 💡 Hướng dẫn: Giữ tab ChinhChien.java này mở trong NetBeans khi đang làm bài.
 * Cần tính năng nâng cao nào, chỉ cần Copy khối code tương ứng và Paste vào bài.
 */
public class ChinhChien {

    /*
    ============================================================================
    SNIPPET 1: TÌM KIẾM THEO TÊN (SEARCH BY NAME)
    ============================================================================
    
    1. COPY DÁN VÀO DAO (ví dụ ProductDAO.java):
    ----------------------------------------------------------------------------
    public List<Product> searchByName(String search) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS categoryName FROM Product p LEFT JOIN Category c ON p.categoryId = c.id WHERE 1=1 ";
        if (search != null && !search.trim().isEmpty()) {
            sql += "AND p.name LIKE ? ";
        }
        sql += "ORDER BY p.id DESC;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(1, "%" + search.trim() + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException ex) {}
        return list;
    }

    2. COPY DÁN VÀO SERVLET doGet():
    ----------------------------------------------------------------------------
    String search = request.getParameter("search");
    List<Product> products = productDAO.searchByName(search);
    request.setAttribute("productList", products);
    request.setAttribute("searchVal", search); // Gửi lại ô input ở JSP

    3. COPY DÁN VÀO JSP (products.jsp - Đặt ngay trên bảng <table>):
    ----------------------------------------------------------------------------
    <form action="${pageContext.request.contextPath}/products" method="GET" class="row g-2 mb-3">
        <input type="hidden" name="action" value="list">
        <div class="col-auto">
            <input type="text" name="search" class="form-control form-control-sm" placeholder="Enter name..." value="${searchVal}">
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary btn-sm">Search</button>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary btn-sm">Reset</a>
        </div>
    </form>
    */


    /*
    ============================================================================
    SNIPPET 2: LỌC THEO DANH MỤC (CATEGORY FILTER DROPDOWN)
    ============================================================================
    
    1. COPY DÁN VÀO DAO:
    ----------------------------------------------------------------------------
    public List<Product> filterByCategory(Integer categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS categoryName FROM Product p LEFT JOIN Category c ON p.categoryId = c.id WHERE 1=1 ";
        if (categoryId != null && categoryId > 0) {
            sql += "AND p.categoryId = ? ";
        }
        sql += "ORDER BY p.id DESC;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            if (categoryId != null && categoryId > 0) {
                ps.setInt(1, categoryId);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException ex) {}
        return list;
    }

    2. COPY DÁN VÀO SERVLET doGet():
    ----------------------------------------------------------------------------
    String categoryStr = request.getParameter("category");
    Integer categoryId = null;
    if (categoryStr != null && !categoryStr.trim().isEmpty()) {
        categoryId = Integer.parseInt(categoryStr);
    }
    List<Product> products = productDAO.filterByCategory(categoryId);
    request.setAttribute("productList", products);
    request.setAttribute("categoryVal", categoryId); // Giữ trạng thái dropdown

    3. COPY DÁN VÀO JSP (products.jsp):
    ----------------------------------------------------------------------------
    <form action="${pageContext.request.contextPath}/products" method="GET" class="row g-2 mb-3">
        <input type="hidden" name="action" value="list">
        <div class="col-auto">
            <select name="category" class="form-select form-select-sm">
                <option value="">-- All Categories --</option>
                <c:forEach var="cat" items="${categoryList}">
                    <option value="${cat.id}" ${categoryVal == cat.id ? 'selected' : ''}>${cat.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary btn-sm">Filter</button>
        </div>
    </form>
    */


    /*
    ============================================================================
    SNIPPET 3: PHÂN TRANG CSDL (SQL SERVER PAGINATION)
    ============================================================================
    
    1. COPY DÁN VÀO DAO:
    ----------------------------------------------------------------------------
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM Product;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException ex) {}
        return 0;
    }

    public List<Product> getPagedList(int pageIndex, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS categoryName FROM Product p LEFT JOIN Category c ON p.categoryId = c.id " +
                     "ORDER BY p.id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, (pageIndex - 1) * pageSize);
            ps.setInt(2, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException ex) {}
        return list;
    }

    2. COPY DÁN VÀO SERVLET doGet():
    ----------------------------------------------------------------------------
    int pageSize = 5;
    String pageStr = request.getParameter("page");
    int pageIndex = 1;
    if (pageStr != null) {
        try { pageIndex = Integer.parseInt(pageStr); } catch (NumberFormatException e) {}
    }
    int totalProducts = productDAO.getTotalCount();
    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
    List<Product> products = productDAO.getPagedList(pageIndex, pageSize);

    request.setAttribute("productList", products);
    request.setAttribute("currentPage", pageIndex);
    request.setAttribute("totalPages", totalPages);

    3. COPY DÁN VÀO JSP (products.jsp - Đặt dưới bảng dữ liệu):
    ----------------------------------------------------------------------------
    <c:if test="${totalPages > 1}">
        <nav>
            <ul class="pagination justify-content-center mt-3">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="?action=list&page=${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
    */


    /*
    ============================================================================
    SNIPPET 4: TÍNH TOÁN TRỰC TIẾP TRÊN JSP BẰNG JSTL (TỔNG / TRUNG BÌNH)
    ============================================================================
    
    1. Đặt đoạn này TRƯỚC thẻ <tbody> ở products.jsp để cộng dồn:
    ----------------------------------------------------------------------------
    <c:set var="totalPrice" value="0" />
    <c:set var="itemCount" value="0" />
    <c:forEach var="p" items="${productList}">
        <c:set var="totalPrice" value="${totalPrice + p.price}" />
        <c:set var="itemCount" value="${itemCount + 1}" />
    </c:forEach>
    <c:set var="avgPrice" value="${itemCount > 0 ? totalPrice / itemCount : 0}" />

    2. Hiển thị dòng kết quả ở cuối bảng <table> (sau thẻ </c:forEach> của bảng):
    ----------------------------------------------------------------------------
    <tr class="fw-bold table-light text-primary">
        <td colspan="3">Average Price</td>
        <td class="text-end">
            <fmt:formatNumber value="${avgPrice}" pattern="#,###" /> đ
        </td>
        <td colspan="3"></td>
    </tr>
    */


    /*
    ============================================================================
    SNIPPET 5: MÃ HÓA MẬT KHẨU MD5 (PASSWORD ENCRYPTION)
    ============================================================================
    */
    public static String hashMd5(String raw) {
        if (raw == null) return "";
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] mess = md.digest(raw.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : mess) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            return "";
        }
    }


    /*
    ============================================================================
    SNIPPET 6: TIỆN ÍCH COOKIES (GHI NHỚ ĐĂNG NHẬP)
    ============================================================================
    */
    // 1. Thêm cookie ở Servlet doPost():
    public static void addCookie(HttpServletResponse response, String name, String value, int days) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(60 * 60 * 24 * days);
        response.addCookie(c);
    }

    // 2. Hủy cookie ở Servlet doPost():
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie c = new Cookie(name, "");
        c.setMaxAge(0);
        response.addCookie(c);
    }

    /*
    ============================================================================
    SNIPPET 7: PHÂN QUYỀN TRUY CẬP (AUTHORIZATION - 2 PHƯƠNG ÁN)
    ============================================================================
    
    ----------------------------------------------------------------------------
    👉 PHƯƠNG ÁN A: CHỈ CẦN ĐĂNG NHẬP LÀ ĐƯỢC PHÉP CRUD (Phổ biến trong đề PE)
    ----------------------------------------------------------------------------
    
    1. Kiểm tra ở Servlet doPost() để chặn khách:
    HttpSession session = request.getSession(false);
    User loggedInUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
    if (loggedInUser == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    
    2. Hiển thị nút ở JSP (Ẩn nút nếu chưa đăng nhập, khách chỉ được xem):
    <c:if test="${not empty sessionScope.loggedInUser}">
        <a href="?action=create">Add product</a>
    </c:if>
    
    ----------------------------------------------------------------------------
    👉 PHƯƠNG ÁN B: CHỈ CÓ ADMIN MỚI ĐƯỢC PHÉP CRUD (Phân quyền nâng cao giống WebPRJ)
    ----------------------------------------------------------------------------
    
    1. Kiểm tra ở Servlet doPost() để chặn tài khoản User thường:
    HttpSession session = request.getSession(false);
    User loggedInUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
    if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin role required!");
        return;
    }
    
    2. Hiển thị nút ở JSP (Ẩn nút nếu không phải Admin):
    <c:if test="${sessionScope.loggedInUser.role eq 'ADMIN'}">
        <a href="?action=create">Add product</a>
    </c:if>
    */
}
