# PHẦN 1: BẢN TIẾNG VIỆT (VIETNAMESE)

## 4.3 Reports

### 4.3.1 Báo cáo Doanh số & Sản phẩm bán chạy (Revenue Report)
| | |
| --- | --- |
| Report ID: | REV-R-1 |
| Report Title: | Báo cáo Doanh số và Sản phẩm Best Seller hàng tháng |
| Report Purpose: | Giám đốc (Manager) cần theo dõi tổng doanh thu, tỷ trọng phương thức thanh toán và các mặt hàng bán chạy để ra quyết định kinh doanh. |
| Priority: | High |
| Report Users: | Manager, Sales Manager |
| Data Sources: | Dữ liệu từ bảng Order, Payment và Product của tháng tương ứng. |
| Frequency and Disposition: | Tự động tổng hợp vào ngày cuối cùng của mỗi tháng. Báo cáo hiển thị trên Dashboard và có thể xuất file PDF/Excel. |
| Latency: | Hiển thị trong vòng 3 giây (Đảm bảo bằng Database Indexing trên cột created_at, status và Redis Caching cho các phép tính tổng). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header chứa Tiêu đề báo cáo và tháng báo cáo. Footer chứa Số trang. |
| Report Body: | - Tổng doanh thu theo ngày/tháng/năm.<br>- Tỷ lệ phần trăm thanh toán: COD vs VNPay.<br>- Danh sách mặt hàng Best Seller (các mặt hàng bán > 50 lượt/tháng theo quy tắc `BR-13`).<br>- Phân loại Best Seller theo 3 phân khúc giá (Dưới 500k, 500k-1M, và Trên 1M theo quy tắc `BR-05`). |
| Interactivity: | Người dùng có thể click vào từng sản phẩm Best Seller để xem chi tiết doanh thu từ sản phẩm đó. |
| Security Access Restrictions: | Chỉ Manager và Sales Manager mới có quyền xem thông tin doanh thu. |

### 4.3.2 Báo cáo Tồn kho (Inventory Report)
| | |
| --- | --- |
| Report ID: | INV-R-1 |
| Report Title: | Báo cáo Hiện trạng Tồn kho và Cảnh báo |
| Report Purpose: | Kiểm soát lượng tồn kho, cảnh báo các mặt hàng sắp hết để nhập hàng hoặc luân chuyển giữa các chi nhánh (`BR-07`). |
| Priority: | High |
| Report Users: | Manager, Store Manager |
| Data Sources: | Dữ liệu từ bảng Product, Inventory, và StockLog. |
| Frequency and Disposition: | Theo yêu cầu (On-demand) của quản lý. Hiển thị trên màn hình hệ thống. |
| Latency: | Hiển thị trong vòng 3 giây (Đảm bảo bằng Database Indexing trên cột created_at, status và Redis Caching cho các phép tính tổng). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header chứa Tiêu đề báo cáo và tháng báo cáo. Footer chứa Số trang. |
| Report Body: | - Bảng thống kê số lượng tồn kho theo từng chi nhánh.<br>- Danh sách cảnh báo sản phẩm sắp hết hàng (tồn kho giảm dưới 10% so với lần nhập gần nhất theo quy tắc `BR-04`).<br>- Lịch sử biến động kho (StockLog). |
| Interactivity: | Có thể lọc tồn kho theo từng chi nhánh riêng biệt hoặc xem cảnh báo low stock. |
| Security Access Restrictions: | Manager và Store Manager được quyền xem toàn bộ báo cáo tồn kho các chi nhánh. |

### 4.3.3 Báo cáo Đơn hàng (Order Report)
| | |
| --- | --- |
| Report ID: | ORD-R-1 |
| Report Title: | Báo cáo Tình trạng Đơn hàng |
| Report Purpose: | Đánh giá hiệu suất xử lý đơn hàng và tình trạng vận chuyển. |
| Priority: | Medium |
| Report Users: | Manager, Operations Manager |
| Data Sources: | Dữ liệu từ bảng Order và bảng AuditLog (để truy vết thời gian chuyển trạng thái). |
| Frequency and Disposition: | Theo yêu cầu hoặc tổng hợp hàng tháng. |
| Latency: | Hiển thị trong vòng 3 giây (Đảm bảo bằng Database Indexing trên cột created_at, status và Redis Caching cho các phép tính tổng). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header chứa Tiêu đề báo cáo và tháng báo cáo. Footer chứa Số trang. |
| Report Body: | - Số lượng đơn hàng theo từng trạng thái (PENDING, CONFIRMED, SHIPPING, DELIVERED, CANCELLED).<br>- Tỷ lệ giao hàng thành công.<br>- Tỷ lệ hủy đơn (Cancellation Rate).<br>- Thời gian xử lý đơn hàng trung bình. |
| Interactivity: | Có thể click vào từng trạng thái (ví dụ CANCELLED) để xem danh sách đơn hàng tương ứng. |
| Security Access Restrictions: | Manager và Operations Manager được phép truy cập. |

### 4.3.4 Báo cáo Đối soát Thanh toán (Payment Reconciliation Report)
| | |
| --- | --- |
| Report ID: | PAY-R-1 |
| Report Title: | Báo cáo Đối soát Thanh toán VNPay |
| Report Purpose: | Đối soát số liệu thực tế với cổng thanh toán VNPay, phát hiện sai lệch và giao dịch treo. |
| Priority: | High |
| Report Users: | Manager, Finance Manager |
| Data Sources: | Dữ liệu từ bảng Order, Payment và bảng AuditLog (lưu trữ IPN logs dưới dạng JSON). |
| Frequency and Disposition: | Tự động tạo mỗi ngày hoặc xem theo yêu cầu. |
| Latency: | Hiển thị trong vòng 3 giây (Đảm bảo bằng Database Indexing trên cột created_at, status và Redis Caching cho các phép tính tổng). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header chứa Tiêu đề báo cáo và tháng báo cáo. Footer chứa Số trang. |
| Report Body: | - Tổng doanh thu thực tế ghi nhận từ VNPay so với Tổng giá trị các đơn hàng trạng thái DELIVERED thanh toán qua VNPay.<br>- Danh sách các giao dịch "treo" (chưa nhận được IPN callback từ VNPay).<br>- Bảng đối chiếu phát hiện sai lệch giữa hệ thống nội bộ và cổng thanh toán. |
| Interactivity: | Click vào mã giao dịch treo để xem chi tiết log của giao dịch đó. |
| Security Access Restrictions: | Chỉ Manager và Finance Manager được phép truy cập báo cáo tài chính này. |

---

# PART 2: ENGLISH VERSION (BẢN TIẾNG ANH)

## 4.3 Reports

### 4.3.1 Monthly Revenue & Best-Selling Product Report
| | |
| --- | --- |
| Report ID: | REV-R-1 |
| Report Title: | Monthly Revenue and Best-Selling Product Report |
| Report Purpose: | The Manager wants to track total revenue, payment method proportion, and best-selling products for business strategy. |
| Priority: | High |
| Report Users: | Manager, Sales Manager |
| Data Sources: | Order, Payment, and Product database of the corresponding month. |
| Frequency and Disposition: | Automatically generated on the last day of each month. Displayed on Dashboard and exportable to PDF/Excel. |
| Latency: | Displayed within 3 seconds (Ensured by Database Indexing on created_at, status and Redis Caching for heavy calculations). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header contains report title and month. Footer contains page numbers. |
| Report Body: | - Total revenue by day/month/year.<br>- Revenue percentage by payment method: COD vs VNPay.<br>- List of Best-Selling products (> 50 units sold/month per `BR-13`).<br>- Best-Sellers categorized into 3 price segments (Under 500k, 500k-1M, Over 1M VND per `BR-05`). |
| Interactivity: | The user can click on a best-selling product to view its detailed revenue statistics. |
| Security Access Restrictions: | Only Manager and Sales Manager can view revenue and profit data. |

### 4.3.2 Inventory Status & Low Stock Report
| | |
| --- | --- |
| Report ID: | INV-R-1 |
| Report Title: | Inventory Status and Low Stock Report |
| Report Purpose: | To control inventory levels, receive early warnings on low-stock items for restock or branch transfers (`BR-07`). |
| Priority: | High |
| Report Users: | Manager, Store Manager |
| Data Sources: | Product, Inventory, and StockLog database. |
| Frequency and Disposition: | Generated on-demand or monthly. Displayed on system screens. |
| Latency: | Displayed within 3 seconds (Ensured by Database Indexing on created_at, status and Redis Caching for heavy calculations). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header contains report title and month. Footer contains page numbers. |
| Report Body: | - Current inventory quantities grouped by branch.<br>- List of low-stock products (inventory drops below 10% of the last import quantity per `BR-04`).<br>- Stock variation history (StockLog). |
| Interactivity: | Users can filter inventory by specific branches or filter only low-stock alerts. |
| Security Access Restrictions: | Manager and Store Manager have the right to view branch inventory data. |

### 4.3.3 Order Status & Performance Report
| | |
| --- | --- |
| Report ID: | ORD-R-1 |
| Report Title: | Order Status and Performance Report |
| Report Purpose: | To evaluate order processing efficiency and delivery status. |
| Priority: | Medium |
| Report Users: | Manager, Operations Manager |
| Data Sources: | Order and AuditLog table (to trace order status transition history). |
| Frequency and Disposition: | On-demand or monthly synthesis. |
| Latency: | Displayed within 3 seconds (Ensured by Database Indexing on created_at, status and Redis Caching for heavy calculations). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header contains report title and month. Footer contains page numbers. |
| Report Body: | - Total orders grouped by status (PENDING, CONFIRMED, SHIPPING, DELIVERED, CANCELLED).<br>- Successful delivery rate.<br>- Order cancellation rate.<br>- Average order processing time. |
| Interactivity: | Users can click on a status (e.g., CANCELLED) to view the list of corresponding orders. |
| Security Access Restrictions: | Manager and Operations Manager are allowed to view this report. |

### 4.3.4 Payment Reconciliation Report
| | |
| --- | --- |
| Report ID: | PAY-R-1 |
| Report Title: | VNPay Payment Reconciliation Report |
| Report Purpose: | To reconcile internal financial data with the VNPay gateway, identifying discrepancies or hanging transactions. |
| Priority: | High |
| Report Users: | Manager, Finance Manager |
| Data Sources: | Order, Payment, and AuditLog table (storing VNPay IPN logs as JSON). |
| Frequency and Disposition: | Automatically generated daily or on-demand. |
| Latency: | Displayed within 3 seconds (Ensured by Database Indexing on created_at, status and Redis Caching for heavy calculations). |
| Visual Layout: | Portrait / Landscape (A4) |
| Header and Footer: | Header contains report title and month. Footer contains page numbers. |
| Report Body: | - Actual revenue recorded by VNPay versus total value of DELIVERED orders paid via VNPay.<br>- List of hanging transactions (transactions without IPN callback from VNPay).<br>- Discrepancy table highlighting differences between internal system and VNPay gateway. |
| Interactivity: | Click on a hanging transaction ID to view its detailed IPN logs. |
| Security Access Restrictions: | Only Manager and Finance Manager can access this financial report. |
