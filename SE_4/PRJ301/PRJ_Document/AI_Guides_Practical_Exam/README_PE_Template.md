# Hướng dẫn Bí kíp Sử dụng PE Templates môn PRJ301

Bộ template này được sinh ra để bạn **"Hack Speed"** (tăng tốc độ) khi đi thi Practical Exam (PE). Hầu hết các đề thi PE của PRJ301 đều yêu cầu bạn viết code hoàn thiện chức năng CRUD (Create, Read, Update, Delete) cho một bảng trong CSDL.

Thay vì gõ lại từ đầu, hãy copy/paste các file trong thư mục `PE_Templates` và làm theo các bước sau.

---

## BƯỚC 1: KẾT NỐI DATABASE (LÀM TRONG 30 GIÂY)
1. Copy file `src/java/db/DBContext.java` vào project thi của bạn.
2. Mở file `DBContext.java` lên.
3. Tìm đến dòng `private final String DB_URL = ...`. Đổi tên CSDL `YourDatabaseName` thành tên CSDL mà đề bài cung cấp (thường đề bài sẽ ghi rõ ở phần setup DB).
4. Sửa `DB_PWD` thành mật khẩu SQL Server của máy trường (thường là `12345678`, `sa`, hoặc để trống).

## BƯỚC 2: TẠO MODEL VÀ DAO (LÀM TRONG 2 PHÚT)
Ví dụ: Đề bài yêu cầu quản lý **Product** (có `id`, `name`, `price`).
1. **Tạo Model:** Tạo `class Product` chứa 3 thuộc tính đó, Generate Constructor, Getter và Setter.
2. **Tạo DAO:** Copy file `BaseDAO.java` vào project, đổi tên thành `ProductDAO.java`.
3. **Sửa code trong DAO:**
   - Dùng chức năng `Ctrl + R` (Replace) của NetBeans. Thay đổi tất cả chữ `BaseDAO` thành `ProductDAO`.
   - Tìm chữ `TableName` và đổi thành tên bảng trong SQL (vd: `tbl_product`).
   - Sửa các hàm CRUD: Ép kiểu `Object` thành `Product`, và thay thế logic `stm.set...` tương ứng với số lượng cột.

## BƯỚC 3: XỬ LÝ SERVLET (LÀM TRONG 2 PHÚT)
1. Copy file `BaseController.java` vào project, đổi tên thành `ProductServlet.java`.
2. Khai báo `@WebServlet(urlPatterns={"/product"})`.
3. Nhìn vào yêu cầu của đề:
   - Nếu đề thi là **Q2 (chỉ yêu cầu hiển thị)**: Chỉ giữ lại luồng `action == null` trong hàm `doGet()`. Xóa phần `create`, `edit`, `delete` và hàm `doPost()`.
   - Nếu đề thi là **Q3 (yêu cầu thêm/sửa/xóa)**: Giữ nguyên toàn bộ. Chỉ cần sửa `BaseDAO` thành `ProductDAO`, ép kiểu dữ liệu từ form trả về ở `doPost()` cho đúng với các field của `Product`.

## BƯỚC 4: RÁP GIAO DIỆN (LÀM TRONG 3 PHÚT)
1. **Trang hiển thị danh sách:**
   - Copy nội dung `list_template.jsp`.
   - Sửa phần `<th>` và `<td>` cho khớp với thuộc tính của Product (`${item.price}`).
2. **Trang Form thêm/sửa:**
   - Copy nội dung `form_template.jsp`.
   - Tạo các ô `<input>` tương ứng với `name`, `price`. Đừng quên gắn `value="${item.xxx}"` để lúc cập nhật (Update) dữ liệu cũ tự động hiển thị ra.

---

### ⚠️ NHỮNG BẪY CHẾT NGƯỜI CẦN TRÁNH
- **Lỗi 404:** Xảy ra do gọi sai `url-pattern` của Servlet hoặc sai tên file JSP. Hãy kiểm tra lại hàm `sendRedirect()` và `forward()`.
- **Lỗi 500 khi Submit Form:** Xảy ra do `NullPointerException` (nhập thiếu tham số từ Form) hoặc `NumberFormatException` (cố tình ép kiểu chuỗi rỗng sang số). Hãy luôn check `if (thamSo == null)` trước khi ép kiểu.
- **Form Resubmission (F5 bị lặp data):** Luôn nhớ sau khi INSERT, UPDATE, DELETE thành công, hãy dùng `response.sendRedirect("TênServletDanhSach")` thay vì dùng `forward`.
