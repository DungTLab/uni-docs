# 📋 BẢNG PHÂN CÔNG CÔNG VIỆC VÀ TIẾN ĐỘ DỰ ÁN AURELIABOOKS (PRJ301)

> **Dự án:** AureliaBooks — Hệ thống bán sách & văn phòng phẩm trực tuyến  
> **Mô hình phát triển:** Vertical Slice Architecture (Mỗi thành viên sở hữu end-to-end module)  
> **Cập nhật ngày:** 21/07/2026  
> **Trạng thái chung:** 100% Hoàn thành (55/55 Chức năng, 23/23 Controllers, Build Success)

---

## 👥 I. TỔNG QUAN PHÂN CÔNG THÀNH VIÊN (SUMMARY)

| # | Thành viên | Biệt danh Git | Vai trò chính | Phạm vi Module đảm nhận | Số Controller / View |
|---|---|---|---|---|---|
| 1 | **Lê Tiến Dũng** | `DungLT` / `Le Tien Dung` | **Team Lead** | Frame & Core Engine, Auth, Profile, Google Login, Brands Admin, User Admin | 5 Controllers, 6 Views |
| 2 | **Huỳnh Nhật Duy** | `husky` / `huskysama123` | **Catalog Owner** | Catalog & Products Admin, Contributor n:n, Inventory Admin, Search & Pagination | 5 Controllers, 6 Views |
| 3 | **Nguyễn Phú Trọng** | `TrongNP` / `Nguyễn Phú Trọng` | **Transaction Specialist** | Cart, Checkout COD, Voucher Engine, Suppliers Admin, Revenue & Sales Reports | 4 Controllers, 5 Views |
| 4 | **Nguyễn Trần Đức Anh** | `DuckAnh` / `Nguyen Tran Duc Anh` | **Order & Discount Manager** | Customer Orders & Return, Admin Order State Machine, SQL Restock Transaction, Vouchers Admin | 3 Controllers, 4 Views |
| 5 | **Trần Huỳnh Giác** | `GiacCoBu` | **Support & Content Manager** | Support Tickets (Client + Admin), Authors Admin, Publishers Admin, Policies | 4 Controllers, 6 Views |

---

## 📅 II. BẢNG PHÂN CÔNG CHI TIẾT THEO SPRINT & TIẾN ĐỘ THỰC TẾ

### 🚀 SPRINT 1 (Tuần 1): Thiết Lập Cốt Lõi & Tính Năng Cơ Bản

| Thành viên | Nhiệm vụ đảm nhận | Tệp tin phát triển chính | Trạng thái |
|---|---|---|---|
| **Lê Tiến Dũng** | • Khởi tạo Repository & Kiến trúc MVC Java Web Maven<br>• Xây dựng `BaseDAO.java` kết nối SQL Server<br>• Xây dựng `AuthController.java` & Security Filter<br>• Đăng ký, Đăng nhập truyền thống (BCrypt), Đăng xuất<br>• Trang chủ `HomeController.java` & Layout Header/Footer | `BaseDAO.java`<br>`AuthController.java`<br>`UserDAO.java`<br>`login.jsp`, `register.jsp` | ✅ **100% Hoàn thành** |
| **Huỳnh Nhật Duy** | • Lớp Entity Models (`Product`, `Book`, `Stationery`, `Category`)<br>• Quản lý Danh mục phía Admin (`CategoryController`, `CategoryDAO`)<br>• Hiển thị danh sách sản phẩm trang khách hàng | `CategoryController.java`<br>`CategoryDAO.java`<br>`ProductDAO.java`<br>`category/list.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Phú Trọng** | • Xây dựng hệ thống Giỏ hàng (`CartController`, `CartDAO`)<br>• Logic Thêm, Xem, Sửa số lượng, Xóa sản phẩm khỏi giỏ<br>• Kiểm tra quyền sở hữu giỏ hàng (Cart Item Ownership) | `CartController.java`<br>`CartDAO.java`<br>`cart.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Trần Đức Anh** | • Thiết kế thực thể Đơn hàng (`Order`, `OrderItem`)<br>• Xây dựng DAO truy vấn đơn hàng cơ bản | `OrderDAO.java`<br>`Order.java` | ✅ **100% Hoàn thành** |
| **Trần Huỳnh Giác** | • Chuẩn bị dữ liệu mẫu SQL Server (`setup.sql`)<br>• Dựng trang chính sách & điều khoản dịch vụ | `PolicyController.java`<br>`setup.sql`<br>`policy/view.jsp` | ✅ **100% Hoàn thành** |

---

### 🏃 SPRINT 2 (Tuần 2): Nghiệp Vụ Mua Bán, Đơn Hàng & Thống Kê

| Thành viên | Nhiệm vụ đảm nhận | Tệp tin phát triển chính | Trạng thái |
|---|---|---|---|
| **Lê Tiến Dũng** | • Chức năng Quản lý Hồ sơ cá nhân (`ProfileController`)<br>• Đổi mật khẩu & cập nhật thông tin người dùng<br>• Phân hệ Quản lý Tài khoản Admin (`AdminUserController`) | `ProfileController.java`<br>`AdminUserController.java`<br>`profile.jsp`, `user/list.jsp` | ✅ **100% Hoàn thành** |
| **Huỳnh Nhật Duy** | • Tìm kiếm sản phẩm theo từ khóa, lọc danh mục<br>• Phân trang danh sách sản phẩm (`OFFSET/FETCH NEXT`)<br>• Xem trang chi tiết sản phẩm (`ProductController`)<br>• Servlet tải & lưu hình ảnh sản phẩm (`ImageServlet`, `UploadUtils`) | `ProductController.java`<br>`ImageServlet.java`<br>`product-detail.jsp`, `search.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Phú Trọng** | • Quy trình Đặt hàng COD (`CheckoutController`)<br>• Tạo đơn hàng & xóa giỏ hàng tương ứng<br>• Báo cáo Thống kê Doanh thu & SP bán chạy (`ReportController`) | `CheckoutController.java`<br>`ReportController.java`<br>`checkout.jsp`, `report/view.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Trần Đức Anh** | • Trang Lịch sử đơn hàng cá nhân phía Khách hàng<br>• Quản lý đơn hàng phía Admin (`AdminOrderController`)<br>• Chuyển trạng thái đơn (`PENDING` $\rightarrow$ `SHIPPED` $\rightarrow$ `DELIVERED`) | `AdminOrderController.java`<br>`OrderController.java`<br>`order/history.jsp`, `admin_list.jsp` | ✅ **100% Hoàn thành** |
| **Trần Huỳnh Giác** | • Xây dựng sơ đồ phân quyền hệ thống<br>• Hỗ trợ viết tài liệu hướng dẫn upload ảnh & thiết kế DB | `huong_dan_upload_anh.md`<br>ClassDiagram XMLs | ✅ **100% Hoàn thành** |

---

### 🌟 SPRINT 3 & 3+ (Tuần 3): Tích Hợp Nâng Cao, Master Data & Hoàn Thiện Dự Án

| Thành viên | Nhiệm vụ ban đầu | Điều chỉnh & Cải tiến bổ sung (Sprint 3+) | Tệp tin sản phẩm | Trạng thái |
|---|---|---|---|---|
| **Lê Tiến Dũng** | Tích hợp Google OAuth 2.0 Login | • Bổ sung **CRUD Thương hiệu Admin (Brands)**<br>• Sửa lỗi NullPointerException profile | `LoginGoogleController.java`<br>`GoogleUtils.java`<br>`AdminBrandController.java`<br>`brand/list.jsp` | ✅ **100% Hoàn thành** |
| **Huỳnh Nhật Duy** | Admin Product CRUD (Sách & VPP) | • Tích hợp **Contributor (Liên kết Tác giả-Sách n:n)**<br>• Quản lý **Kho hàng (Inventory Dashboard & Stock In/Out)**<br>• Bổ sung trường Supplier & Checkbox Tác giả trong Form Sách | `AdminProductController.java`<br>`AdminInventoryController.java`<br>`product/create.jsp`, `update.jsp`<br>`inventory/list.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Phú Trọng** | Áp dụng Voucher lúc Checkout | • Bổ sung **CRUD Nhà cung cấp Admin (Suppliers)**<br>• Sửa lỗi mua ngay bỏ qua kho & giữ thông tin giao hàng khi áp Voucher | `CheckoutController.java`<br>`AdminSupplierController.java`<br>`supplier/list.jsp` | ✅ **100% Hoàn thành** |
| **Nguyễn Trần Đức Anh** | Admin CRUD Vouchers (Discounts) | • Phân hệ **Trả hàng & Hoàn kho an toàn với SQL Transaction**<br>• Khôi phục trạng thái CONFIRMED & sửa logic kiểm tra ngày Voucher | `DiscountController.java`<br>`AdminOrderController.java`<br>`OrderDAO.java`<br>`discount/list.jsp` | ✅ **100% Hoàn thành** |
| **Trần Huỳnh Giác** | Client gửi Support Tickets | • Đảm nhận **Trọn gói Support Tickets (Khách gửi + Admin phản hồi)**<br>• Bổ sung **CRUD Tác giả (Authors)** & **CRUD NXB (Publishers)**<br>• Bổ sung link Tác giả vào Navbar Dropdown | `SupportRequestController.java`<br>`AdminAuthorController.java`<br>`AdminPublisherController.java`<br>`support/create.jsp`, `list.jsp`<br>`author/list.jsp`, `publisher/list.jsp` | ✅ **100% Hoàn thành** |

---

## 📊 III. BẢNG MA TRẬN PHÂN CHÍNH 55 CHỨC NĂNG (SYSTEM PERMISSION MATRIX)

| STT | Nhóm Tính năng | Chức năng nghiệp vụ | Vai trò cho phép | Thành viên Phụ trách |
|---|---|---|---|---|
| **1.0 - 7.0** | Quản lý tài khoản | Thêm, Sửa, Khóa, Xóa, Xem DS, Xem Chi tiết, Tìm kiếm tài khoản | Admin | **Lê Tiến Dũng** |
| **8.0 - 10.0** | Hồ sơ cá nhân | Xem hồ sơ, Sửa thông tin profile, Đổi mật khẩu | Customer / Staff / Admin | **Lê Tiến Dũng** |
| **11.0 - 12.0** | Đăng nhập | Đăng nhập tài khoản truyền thống & **Google OAuth 2.0** | Guest / All | **Lê Tiến Dũng** |
| **13.0 - 14.0** | Đăng xuất & Quên MK | Đăng xuất hệ thống, Quên mật khẩu | Customer / All | **Lê Tiến Dũng** |
| **15.0 - 17.0** | Request Admin chung | Thêm, Sửa, Xóa request hỗ trợ chung | Staff / Manager | **Trần Huỳnh Giác** |
| **18.0 - 24.0** | Quản lý nhân viên | Xem, Sửa, Tìm kiếm, Phân quyền, Xóa nhân viên | Admin | **Lê Tiến Dũng** |
| **25.0 - 30.0** | Quản lý hàng hóa | Tạo, Xem, Chi tiết, Sửa, Xóa, Tìm kiếm Sách & VPP | Guest / Customer / Admin | **Huỳnh Nhật Duy** |
| **31.0 - 35.0** | Quản lý giỏ hàng | Thêm, Xem, Sửa số lượng, Xóa item giỏ hàng, Đặt hàng | Guest / Customer | **Nguyễn Phú Trọng** |
| **36.0 - 41.0** | Quản lý hóa đơn / đơn hàng | Tạo, Xem, Sửa, Xóa, Tìm kiếm, Duyệt đơn & Xuất hóa đơn | Customer / Staff / Admin | **Nguyễn Trần Đức Anh** |
| **42.0 - 47.0** | Yêu cầu hỗ trợ | Tạo ticket, Xem DS ticket, Xem nội dung, Phản hồi ticket | Customer / CS Staff / Admin | **Trần Huỳnh Giác** |
| **48.0 - 51.0** | Quản lý thanh toán | Chọn hình thức (COD), Thay đổi hình thức, Đặt hàng | Customer / Admin | **Nguyễn Phú Trọng** |
| **52.0 - 55.0** | Quản lý giảm giá | Tạo, Xem, Sửa, Xóa mã giảm giá (Vouchers) | Customer / Manager / Admin | **Nguyễn Trần Đức Anh** |

---

## 🏆 IV. TỔNG KẾT KHỐI LƯỢNG SẢN PHẨM HOÀN THÀNH

- **Tổng số Controllers/Servlets:** 23 files Java.
- **Tổng số Modules JSP Views:** 18 thư mục view.
- **Tổng số DAOs:** 12 files DAO đóng gói kết nối CSDL.
- **Tổng số Commits:** Hơn 73 commits trên GitHub.
- **Chất lượng Build:** 100% `BUILD SUCCESS` không lỗi biên dịch.
