# 📚 Cẩm Nang Chinh Chiến PE - Java Web (PRJ301)
## ⚡️ Chiến Thuật 80 Phút: Khung Xương Tối Giản + Modul Đắp Thêm

Bí quyết để làm kịp bài thi trong 80 phút là **không viết code thừa**. Template hiện tại đã được rút gọn về mức **tối giản nhất** (chỉ có hiển thị bảng thông tin, login, logout, thêm, sửa, xóa cơ bản).

Khi thi, hãy chạy khung xương này trước (mất 10 phút), sau đó nếu đề bài yêu cầu các tính năng nâng cao (như tìm kiếm, lọc, phân trang, tính tổng/trung bình), hãy copy-paste các snippet tương ứng dưới đây vào dự án.

---

## 🛠️ PHẦN I: KHUNG XƯƠNG TỐI GIẢN (ĐÃ CÓ SẴN)
1. **DBContext**: Cấu hình CSDL phòng thi (CSDL tên gì, mật khẩu sa là gì).
2. **Login/Logout**: Đăng nhập, lưu session `loggedInUser`, chuyển hướng 302 khi lỗi.
3. **CRUD**: Hiển thị bảng, thêm mới, sửa đổi dữ liệu (có validate), xóa bản ghi (có xác nhận).

---

## 💡 PHẦN II: COOKBOOK - SNIPPET TÍNH NĂNG NÂNG CAO (COPY-PASTE KHI CẦN)

### 1. Tìm Kiếm Theo Tên (Search by Name)

#### 📝 Bước A: Sửa DAO
Thay vì dùng `getAll()` đơn giản, sửa câu SQL của bạn nhận tham số tìm kiếm:
```java
public List<Product> searchByName(String search) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT p.*, c.name AS categoryName FROM Product p " +
                 "LEFT JOIN Category c ON p.categoryId = c.id WHERE 1=1 ";
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
                list.add(mapResultSetToProduct(rs)); // Hàm map có sẵn ở DAO
            }
        }
    } catch (SQLException ex) { }
    return list;
}
```

#### 📝 Bước B: Sửa Servlet (`doGet`)
```java
String search = request.getParameter("search");
List<Product> products = productDAO.searchByName(search);
request.setAttribute("productList", products);
request.setAttribute("searchVal", search); // Đẩy lại ô input
```

#### 📝 Bước C: Thêm Form vào JSP (`products.jsp`)
Dán khối này ngay trên thẻ bảng `<table>`:
```html
<form action="${pageContext.request.contextPath}/products" method="GET" class="row g-2 mb-3">
    <input type="hidden" name="action" value="list">
    <div class="col-auto">
        <input type="text" name="search" class="form-control" placeholder="Search by name..." value="${searchVal}">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Search</button>
        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Reset</a>
    </div>
</form>
```

---

### 2. Lọc Theo Danh Mục (Category Filter Dropdown)

#### 📝 Bước A: Sửa DAO
Thêm điều kiện so sánh khóa ngoại vào SQL:
```java
public List<Product> filterByCategory(Integer categoryId) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT p.*, c.name AS categoryName FROM Product p " +
                 "LEFT JOIN Category c ON p.categoryId = c.id WHERE 1=1 ";
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
    } catch (SQLException ex) { }
    return list;
}
```

#### 📝 Bước B: Sửa Servlet (`doGet`)
```java
String categoryStr = request.getParameter("category");
Integer categoryId = null;
if (categoryStr != null && !categoryStr.trim().isEmpty()) {
    categoryId = Integer.parseInt(categoryStr);
}
List<Product> products = productDAO.filterByCategory(categoryId);
request.setAttribute("productList", products);
request.setAttribute("categoryVal", categoryId); // Giữ trạng thái dropdown
```

#### 📝 Bước C: Thêm Dropdown Form vào JSP (`products.jsp`)
```html
<form action="${pageContext.request.contextPath}/products" method="GET" class="row g-2 mb-3">
    <input type="hidden" name="action" value="list">
    <div class="col-auto">
        <select name="category" class="form-select">
            <option value="">-- All Categories --</option>
            <c:forEach var="cat" items="${categoryList}">
                <option value="${cat.id}" ${categoryVal == cat.id ? 'selected' : ''}>${cat.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Filter</button>
    </div>
</form>
```

---

### 3. Phân Trang CSDL (SQL Server Pagination)

#### 📝 Bước A: Thêm 2 hàm tính tổng & phân trang vào DAO
```java
// Lấy tổng số dòng để tính số trang
public int getTotalCount() {
    String sql = "SELECT COUNT(*) FROM Product;";
    try (PreparedStatement ps = this.getConnection().prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
    } catch (SQLException ex) { }
    return 0;
}

// Lấy danh sách phân trang (sử dụng OFFSET FETCH của SQL Server)
public List<Product> getPagedList(int pageIndex, int pageSize) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT p.*, c.name AS categoryName FROM Product p " +
                 "LEFT JOIN Category c ON p.categoryId = c.id " +
                 "ORDER BY p.id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
        ps.setInt(1, (pageIndex - 1) * pageSize);
        ps.setInt(2, pageSize);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        }
    } catch (SQLException ex) { }
    return list;
}
```

#### 📝 Bước B: Sửa Servlet (`doGet`)
```java
int pageSize = 5; // Hoặc đề thi yêu cầu (ví dụ: 3, 5, 10)
String pageStr = request.getParameter("page");
int pageIndex = 1;
if (pageStr != null) {
    try {
        pageIndex = Integer.parseInt(pageStr);
    } catch (NumberFormatException e) {}
}

int totalProducts = productDAO.getTotalCount();
int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

List<Product> products = productDAO.getPagedList(pageIndex, pageSize);

request.setAttribute("productList", products);
request.setAttribute("currentPage", pageIndex);
request.setAttribute("totalPages", totalPages);
```

#### 📝 Bước C: Thêm thanh phân trang vào JSP (`products.jsp`)
Dán khối này ở cuối bảng dữ liệu:
```html
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
```

---

### 4. Tính Toán JSTL Trực Tiếp trên JSP (Sum / Avg / Min / Max)
*Rất hay ra đề: tính trung bình giá, tổng tiền hoặc đếm dòng hàng tồn kho (giống WS 2).*

Đặt đoạn code này **trước** thẻ `<tbody>` để tính toán:
```html
<!-- Khởi tạo các biến tính toán -->
<c:set var="totalPrice" value="0" />
<c:set var="itemCount" value="0" />

<c:forEach var="p" items="${productList}">
    <!-- Cộng dồn giá và tăng bộ đếm -->
    <c:set var="totalPrice" value="${totalPrice + p.price}" />
    <c:set var="itemCount" value="${itemCount + 1}" />
</c:forEach>

<c:set var="avgPrice" value="${itemCount > 0 ? totalPrice / itemCount : 0}" />
```

Hiển thị dòng kết quả ở cuối bảng `<table>` (sau vòng lặp `c:forEach` chính):
```html
<tr class="fw-bold table-light text-primary">
    <td colspan="3">Average Price</td>
    <td class="text-end">
        <fmt:formatNumber value="${avgPrice}" pattern="#,###" /> đ
    </td>
    <td colspan="3"></td>
</tr>
```

---

### 5. Mã Hóa Mật Khẩu MD5 (Password Security)
Nếu đề bài yêu cầu đăng ký/đăng nhập mật khẩu dạng mã hóa MD5:
1. Thêm hàm mã hóa tĩnh này vào `dao/UserDAO.java` hoặc class tiện ích:
```java
public String hashMd5(String input) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
```
2. Khi đăng ký hoặc đăng nhập, hãy chạy `pass = hashMd5(pass);` trước khi đưa vào PreparedStatement.

---

### 6. Cấu hình Cookie ghi nhớ tài khoản (Remember Me)
1. Để ghi nhớ tài khoản (lưu cookie) khi đăng nhập thành công:
```java
Cookie rememberCookie = new Cookie("usernameCookie", userName);
rememberCookie.setMaxAge(60 * 60 * 24 * 7); // Lưu 7 ngày
response.addCookie(rememberCookie);
```
2. Để xóa cookie khi người dùng đăng xuất (Logout):
```java
Cookie rememberCookie = new Cookie("usernameCookie", "");
rememberCookie.setMaxAge(0); // Hủy ngay
response.addCookie(rememberCookie);
```
3. Đọc cookie trong `doGet()` của `LoginServlet`:
```java
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie c : cookies) {
        if ("usernameCookie".equals(c.getName())) {
            request.setAttribute("usernameVal", c.getValue());
            break;
        }
    }
}
```

Chúc bạn thi tốt và làm chủ thời gian tuyệt đối! 🎯
