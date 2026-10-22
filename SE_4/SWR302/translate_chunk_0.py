import json
import os

input_path = r"C:\Users\DungLT\.gemini\antigravity\brain\b6620408-6526-4645-99cb-3ccc689c9ae8\scratch\chunks\chunk_0.json"
output_path = r"C:\Users\DungLT\.gemini\antigravity\brain\b6620408-6526-4645-99cb-3ccc689c9ae8\scratch\chunks\chunk_0_translated.json"

translation_map = {
    "Software Requirements Specification": "Đặc tả Yêu cầu Phần mềm",
    "for": "cho",
    "Mini Fahasa Integrated Bookstore Website, Release 1.0": "Hệ thống Website Nhà sách Tích hợp Mini Fahasa, Phiên bản 1.0",
    "Version 1.0 approved\nPrepared by Le Tien Dung & Tran Huynh Giac\nJune 29, 2026": "Phiên bản 1.0 đã phê duyệt\nĐược chuẩn bị bởi Lê Tiến Dũng & Trần Huỳnh Giác\nNgày 29 tháng 6 năm 2026",
    "Table of Contents": "Mục lục",
    "Revision History": "Lịch sử hiệu chỉnh",
    "1. Introduction": "1. Giới thiệu",
    "1.1 Purpose": "1.1 Mục đích",
    "1.2 Document Conventions": "1.2 Quy ước tài liệu",
    "1.3 Project Scope and Product Features": "1.3 Phạm vi dự án và các Tính năng sản phẩm",
    "1.4 References": "1.4 Tài liệu tham khảo",
    "2. Overall Description": "2. Mô tả tổng quan",
    "2.1 Product Perspective": "2.1 Bối cảnh sản phẩm",
    "2.2 User Classes and Characteristics": "2.2 Các lớp người dùng và Đặc điểm",
    "2.3 Operating Environment": "2.3 Môi trường vận hành",
    "2.4 Design and Implementation Constraints": "2.4 Ràng buộc Thiết kế và Triển khai",
    "2.5 Assumptions and Dependencies": "2.5 Giả định và Phụ thuộc",
    "3. System Features": "3. Các tính năng hệ thống",
    "3.1 Account & Access Management (FE-1)": "3.1 Quản lý tài khoản & Quyền truy cập (FE-1)",
    "3.2 Product Catalog Management (FE-2)": "3.2 Quản lý danh mục sản phẩm (FE-2)",
    "3.3 Shopping Cart & Order Management (FE-3)": "3.3 Quản lý Giỏ hàng & Đơn hàng (FE-3)",
    "3.4 Inventory Management (FE-4)": "3.4 Quản lý Kho hàng (FE-4)",
    "3.5 Payment Processing (FE-5)": "3.5 Xử lý Thanh toán (FE-5)",
    "3.6 Customer Support (FE-6)": "3.6 Hỗ trợ Khách hàng (FE-6)",
    "3.7 System Administration (FE-7)": "3.7 Quản trị Hệ thống (FE-7)",
    "3.8 Reports & Statistics (FE-8)": "3.8 Báo cáo & Thống kê (FE-8)",
    "4. Data Requirements": "4. Yêu cầu về Dữ liệu",
    "4.1 Logical Data Model": "4.1 Mô hình Dữ liệu Logic",
    "4.2 Data Dictionary": "4.2 Từ điển Dữ liệu",
    "4.3 Reports": "4.3 Báo cáo",
    "4.4 Data Integrity, Retention, and Disposal": "4.4 Tính toàn vẹn, Lưu trữ và Hủy bỏ Dữ liệu",
    "5. External Interface Requirements": "5. Yêu cầu Giao tiếp Ngoại vi",
    "5.1 User Interfaces": "5.1 Giao diện người dùng",
    "5.2 Software Interfaces": "5.2 Giao diện phần mềm",
    "5.3 Hardware Interfaces": "5.3 Giao diện phần cứng",
    "5.4 Communications Interfaces": "5.4 Giao diện truyền thông",
    "6. Quality Attributes": "6. Các thuộc tính chất lượng",
    "6.1 Usability Requirements": "6.1 Yêu cầu về Khả năng sử dụng",
    "6.2 Performance Requirements": "6.2 Yêu cầu về Hiệu năng",
    "6.3 Security Requirements": "6.3 Yêu cầu về Bảo mật",
    "6.4 Safety Requirements": "6.4 Yêu cầu về An toàn",
    "6.5 Availability Requirements": "6.5 Yêu cầu về Độ khả dụng",
    "6.6 Robustness Requirements": "6.6 Yêu cầu về Độ mạnh mẽ",
    "Appendix A: Analysis Models": "Phụ lục A: Các Mô hình phân tích",
    "1. Use Case Diagram": "1. Sơ đồ Use Case",
    "2. Entity Relationship Diagram (ERD)": "2. Sơ đồ Quan hệ Thực thể (ERD)",
    "3. Checkout Flow (Activity Diagram)": "3. Quy trình thanh toán (Sơ đồ Hoạt động)",
    "4. State-Transition Diagrams (Lifecycles)": "4. Sơ đồ Chuyển trạng thái (Vòng đời)",
    "This Software Requirements Specification (SRS) document details the functional requirements and non-functional requirements for version 1.0 of the Mini Fahasa Integrated Bookstore Website System.\nThis document is prepared for the following audiences:": "Tài liệu Đặc tả Yêu cầu Phần mềm (SRS) này chi tiết các yêu cầu chức năng và yêu cầu phi chức năng cho phiên bản 1.0 của Hệ thống Website Nhà sách Tích hợp Mini Fahasa.\nTài liệu này được chuẩn bị cho các đối tượng độc giả sau:",
    "Development Team: Used as a technical basis for designing system architecture, building the database, and implementing software functionalities.": "Đội ngũ phát triển (Development Team): Được sử dụng làm cơ sở kỹ thuật để thiết kế kiến trúc hệ thống, xây dựng cơ sở dữ liệu và triển khai các chức năng phần mềm.",
    "Testing/QA Team: Used as a foundation for creating test cases.": "Đội ngũ Kiểm thử/QA (Testing/QA Team): Được sử dụng làm cơ sở để xây dựng các kịch bản kiểm thử (test cases).",
    "Customers & Stakeholders: Used to verify and cross-check the delivery scope, serving as a basis for system acceptance upon completion of Release 1.0.": "Khách hàng & Các bên liên quan (Customers & Stakeholders): Được sử dụng để xác minh và đối chiếu phạm vi bàn giao, làm cơ sở cho việc nghiệm thu hệ thống sau khi hoàn thành Phiên bản 1.0 (Release 1.0).",
    "To ensure consistency, readability, and ease of cross-checking, this SRS document applies the following conventions:": "Để đảm bảo tính nhất quán, dễ đọc và dễ đối chiếu, tài liệu SRS này áp dụng các quy ước sau:",
    "1. Requirement Tagging:": "1. Gắn nhãn Yêu cầu (Requirement Tagging):",
    "Functional Requirements: FR-[Module]-[Sequence Number] (Example: FR-ACC-01, FR-ORD-02).": "Yêu cầu chức năng: FR-[Module]-[Sequence Number] (Ví dụ: FR-ACC-01, FR-ORD-02).",
    "ACC (Account): Account management and authorization module.": "ACC (Account): Module quản lý tài khoản và phân quyền.",
    "PRO (Product): Product catalog management module.": "PRO (Product): Module quản lý danh mục sản phẩm.",
    "ORD (Cart & Order): Shopping cart and order processing module.": "ORD (Cart & Order): Module giỏ hàng và xử lý đơn hàng.",
    "INV (Inventory): Inventory management and stock transaction module.": "INV (Inventory): Module quản lý kho hàng và giao dịch kho.",
    "PAY (Payment): Payment module.": "PAY (Payment): Module thanh toán.",
    "SUP (Support): Customer support and return/exchange module.": "SUP (Support): Module hỗ trợ khách hàng và đổi trả hàng.",
    "ADM (Admin): System administration module.": "ADM (Admin): Module quản trị hệ thống.",
    "Non-functional Requirements: NFR-[Requirement Type]-[Sequence Number] (Example: NFR-SEC-01).": "Yêu cầu phi chức năng: NFR-[Requirement Type]-[Sequence Number] (Ví dụ: NFR-SEC-01).",
    "SEC (Security): Security.": "SEC (Security): Bảo mật.",
    "PER (Performance): Performance.": "PER (Performance): Hiệu năng.",
    "USA (Usability): Usability.": "USA (Usability): Khả năng sử dụng.",
    "AVA (Availability): Availability.": "AVA (Availability): Độ khả dụng.",
    "2. Business Rules Reference: Codes such as BR-01 to BR-16 refer directly to the project's Business Rules list.": "2. Tham chiếu Quy tắc Nghiệp vụ (Business Rules): Các mã như BR-01 đến BR-16 tham chiếu trực tiếp đến danh sách Quy tắc Nghiệp vụ (Business Rules) của dự án.",
    "3. Priority Levels:": "3. Các mức độ ưu tiên:",
    "High: Mandatory for core system operation.": "Cao (High): Bắt buộc đối với hoạt động cốt lõi của hệ thống.",
    "Medium: Advanced features (e.g., VNPay gateway, applying vouchers).": "Trung bình (Medium): Các tính năng nâng cao (ví dụ: cổng thanh toán VNPay, áp dụng voucher).",
    "Low: Supplementary features or those to be developed in future releases.": "Thấp (Low): Các tính năng bổ sung hoặc các tính năng sẽ được phát triển trong các phiên bản tương lai.",
    "The Mini Fahasa project is an integrated e-commerce storefront and intuitive ERP-Lite inventory management system, operating on two main interfaces:": "Dự án Mini Fahasa là một hệ thống tích hợp giao diện bán hàng trực tuyến (Storefront) và hệ thống quản lý kho hàng ERP-Lite trực quan, hoạt động trên hai giao diện chính:",
    "Storefront (Online Sales Channel): A minimalist Web interface for Customers to browse, search, and order Books & Stationery in a single order, pay online or via COD, track orders, and submit support/complaint requests.": "Giao diện bán hàng (Storefront) (Kênh bán hàng trực tuyến): Một giao diện Web tối giản dành cho Khách hàng để duyệt, tìm kiếm và đặt mua Sách & Văn phòng phẩm trong cùng một đơn hàng, thanh toán trực tuyến hoặc qua COD, theo dõi đơn hàng và gửi các yêu cầu hỗ trợ/khiếu nại.",
    "Dashboard (Internal Management Panel): An interface for the operational team (Admin, Manager, Sale Staff, CS Staff) to manage inventory, process orders, handle returns/exchanges, and reconcile real-time stock.": "Trang quản trị (Dashboard) (Bảng quản lý nội bộ): Một giao diện dành cho đội ngũ vận hành (Admin, Manager, Sale Staff, CS Staff) để quản lý kho hàng, xử lý đơn hàng, giải quyết đổi trả hàng và đối soát tồn kho theo thời gian thực.",
    "Summary of Feature Epics (FE):": "Tóm tắt các Nhóm tính năng chính (Feature Epics - FE):",
    "FE-1: Account Management: Registration (age ), login, Google OAuth 2.0, view/edit personal profile and accumulated points balance.": "FE-1: Quản lý tài khoản: Đăng ký (tuổi ), đăng nhập, Google OAuth 2.0, xem/chỉnh sửa thông tin cá nhân và số dư điểm tích lũy.",
    "FE-2: Product Management: Product management (Book, Stationery), recursive categories, manage authors, publishers, brands, and automated clearance sale alerts.": "FE-2: Quản lý sản phẩm: Quản lý sản phẩm (Sách, Văn phòng phẩm), danh mục đệ quy, quản lý tác giả, nhà xuất bản, thương hiệu và cảnh báo xả kho tự động.",
    "FE-3: Cart and Order Management: Cart management, apply birthday discounts/vouchers/loyalty points, place orders, and manage the entire order lifecycle.": "FE-3: Quản lý Giỏ hàng và Đơn hàng: Quản lý giỏ hàng, áp dụng giảm giá sinh nhật/voucher/điểm thành viên, đặt hàng và quản lý toàn bộ vòng đời đơn hàng.",
    "FE-4: Inventory Management: Inventory management for Managers, low stock alerts, and Stock Transaction Logs.": "FE-4: Quản lý Kho hàng: Quản lý kho dành cho Manager, cảnh báo sắp hết hàng và Nhật ký Giao dịch Kho (Stock Transaction Logs).",
    "FE-5: Payment Processing: Process payment method selection, integrate VNPay Sandbox gateway.": "FE-5: Xử lý Thanh toán: Xử lý lựa chọn phương thức thanh toán, tích hợp cổng thanh toán thử nghiệm VNPay Sandbox.",
    "FE-6: Customer Support: Submit support requests (Tickets), CS Staff checks history, invoices, creates return/exchange requests on behalf of customers, and performance statistics.": "FE-6: Hỗ trợ Khách hàng: Gửi các yêu cầu hỗ trợ (Tickets), CS Staff kiểm tra lịch sử, hóa đơn, tạo yêu cầu đổi trả hàng thay cho khách hàng và thống kê hiệu suất.",
    "FE-7: System Administration: System administration, configure system parameters, and view System Audit Logs.": "FE-7: Quản trị Hệ thống: Quản trị hệ thống, cấu hình các tham số hệ thống và xem Nhật ký Kiểm toán Hệ thống (System Audit Logs).",
    "FE-8: Reports & Statistics: Export revenue, inventory, order reports, and periodic payment reconciliation for Managers.": "FE-8: Báo cáo & Thống kê: Xuất báo cáo doanh thu, kho hàng, đơn hàng và đối soát thanh toán định kỳ dành cho Manager.",
    "1. Vision and Scope Document (Mini Fahasa Project Vision and Scope, Group 1).": "1. Tài liệu Tầm nhìn và Phạm vi (Tầm nhìn và Phạm vi Dự án Mini Fahasa, Nhóm 1).",
    "2. Business Rules Group 1 (BR-01 to BR-16) (Business Rules List).": "2. Quy tắc Nghiệp vụ Nhóm 1 (BR-01 đến BR-16) (Danh sách Quy tắc Nghiệp vụ).",
    "3. Use Cases Group 1 (UC-01 to UC-61) (Standard use case document at tab t.k2qvj9n2h148).": "3. Use Cases Nhóm 1 (UC-01 đến UC-61) (Tài liệu Use Case chuẩn tại tab t.k2qvj9n2h148).",
    "Mini Fahasa is a new software system intended to replace traditional sales management methods (using Excel, paper ledgers) by allowing customers to view, order online, and receive home delivery, while simultaneously providing an ERP-Lite management system for the store. The system addresses issues such as: inventory data discrepancies, loss, slow order processing, and the lack of an online sales channel.": "Mini Fahasa là một hệ thống phần mềm mới nhằm thay thế các phương pháp quản lý bán hàng truyền thống (sử dụng Excel, sổ sách giấy) bằng cách cho phép khách hàng xem, đặt hàng trực tuyến và nhận hàng tại nhà, đồng thời cung cấp hệ thống quản lý ERP-Lite cho cửa hàng. Hệ thống giải quyết các vấn đề như: sai lệch dữ liệu tồn kho, thất thoát, xử lý đơn hàng chậm và việc thiếu kênh bán hàng trực tuyến.",
    "The Context Diagram below illustrates the data interaction boundaries between the system and external entities:": "Sơ đồ Bối cảnh (Context Diagram) dưới đây minh họa các ranh giới tương tác dữ liệu giữa hệ thống và các thực thể bên ngoài:",
    "Guest: Accesses the Storefront to search and browse products but is not logged in.": "Khách (Guest): Truy cập Giao diện bán hàng (Storefront) để tìm kiếm và xem sản phẩm nhưng chưa đăng nhập.",
    "Customer: Logged in. Can browse, add products to the cart, place orders, make payments, track orders, and submit support requests.": "Khách hàng (Customer): Đã đăng nhập. Có thể duyệt, thêm sản phẩm vào giỏ hàng, đặt hàng, thực hiện thanh toán, theo dõi đơn hàng và gửi các yêu cầu hỗ trợ.",
    "Sale Staff: Uses the Dashboard to process orders (PENDING  CONFIRMED  SHIPPING  DELIVERED), update statuses, and check inventory.": "Nhân viên bán hàng (Sale Staff): Sử dụng Trang quản trị (Dashboard) để xử lý đơn hàng (PENDING  CONFIRMED  SHIPPING  DELIVERED), cập nhật trạng thái và kiểm tra kho hàng.",
    "CS Staff (Customer Service Staff): Receives and processes support Tickets, complaints, and return/exchange requests from customers. Has Read-only access to order and customer data.": "Nhân viên CS (CS Staff - Customer Service Staff): Tiếp nhận và xử lý các Yêu cầu hỗ trợ (Tickets), khiếu nại và yêu cầu đổi trả từ khách hàng. Có quyền truy cập Chỉ đọc (Read-only) vào dữ liệu đơn hàng và khách hàng.",
    "Manager: Has full control over the product catalog, inventory (view, manual stock adjustments, inter-branch transfers), promotions, and views revenue and inventory reports.": "Quản lý (Manager): Có toàn quyền kiểm soát danh mục sản phẩm, kho hàng (xem, điều chỉnh kho thủ công, chuyển kho liên chi nhánh), các chương trình khuyến mãi và xem báo cáo doanh thu, kho hàng.",
    "Admin: Manages internal user accounts and assigns RBAC roles.": "Quản trị viên (Admin): Quản lý các tài khoản người dùng nội bộ và phân vai trò RBAC.",
    "Client (Frontend): Modern web browsers (Chrome, Firefox, Safari, Edge) supporting HTML5/CSS3. Compatible and displays well on Desktop, Tablet, and Smartphone.": "Máy khách (Client - Frontend): Các trình duyệt web hiện đại (Chrome, Firefox, Safari, Edge) hỗ trợ HTML5/CSS3. Tương thích và hiển thị tốt trên Máy tính để bàn (Desktop), Máy tính bảng (Tablet) và Điện thoại thông minh (Smartphone).",
    "Server (Backend): Server running Linux/Windows operating system. Java Runtime Environment (JRE 17+).": "Máy chủ (Server - Backend): Máy chủ chạy hệ điều hành Linux/Windows. Môi trường thực thi Java (JRE 17+).",
    "Database: MySQL or PostgreSQL.": "Cơ sở dữ liệu (Database): MySQL hoặc PostgreSQL.",
    "CO-1: The Backend system uses Java Spring Boot, Frontend uses ReactJS / pure HTML CSS JS.": "CO-1: Hệ thống Backend sử dụng Java Spring Boot, Frontend sử dụng ReactJS / HTML CSS JS thuần.",
    "CO-2: Transactions related to inventory and payments must adhere to ACID properties to ensure data integrity (Data discrepancies are not allowed).": "CO-2: Các giao dịch liên quan đến kho hàng và thanh toán phải tuân thủ các thuộc tính ACID để đảm bảo tính toàn vẹn của dữ liệu (Không cho phép sai lệch dữ liệu).",
    "CO-3: Sensitive configuration information (JWT Secret, API Keys) must not be stored directly in the source code (Hard-code).": "CO-3: Thông tin cấu hình nhạy cảm (JWT Secret, các API Keys) không được lưu trữ trực tiếp trong mã nguồn (Hard-code).",
    "AS-1: The production deployment server is capable of handling at least 100 concurrent users.": "AS-1: Máy chủ triển khai thực tế (production deployment server) có khả năng xử lý ít nhất 100 người dùng đồng thời (concurrent users)."
}

with open(input_path, 'r', encoding='utf-8') as f:
    data = json.load(f)

missing = []
for item in data:
    if "text" in item:
        text = item["text"]
        if text in translation_map:
            item["translated"] = translation_map[text]
        else:
            missing.append(text)
    elif "cell_text" in item:
        cell_text = item["cell_text"]
        if cell_text in translation_map:
            item["translated"] = translation_map[cell_text]
        else:
            missing.append(cell_text)

if missing:
    print("WARNING: The following texts were not found in the translation map:")
    for m in missing:
        print(repr(m))
else:
    print("All texts successfully matched and translated.")

os.makedirs(os.path.dirname(output_path), exist_ok=True)

with open(output_path, 'w', encoding='utf-8') as f:
    json.dump(data, f, ensure_ascii=False, indent=2)
print("Saved translated JSON to", output_path)
