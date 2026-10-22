# Conceptual ERD — Public Library Management System

## Mermaid Code

```mermaid
erDiagram
    LIBRARY_PATRON {
        int patron_id PK
        string reader_card_number
        string national_pin
        string full_name
        string patron_type
        string phone_number
        string email
        string card_status
        date expiry_date
    }

    BOOK_CATALOG_ENTRY {
        int catalog_id PK
        string isbn_code
        string book_title
        string author_name
        string publisher_name
        int publication_year
        string dewey_classification_code
        string category_name
    }

    BOOK_ITEM {
        int item_id PK
        int catalog_id FK
        string barcode_id
        string rfid_tag_id
        string shelf_location_code
        string item_status
        decimal book_cover_price
    }

    LOAN_TRANSACTION {
        int loan_id PK
        int patron_id FK
        int item_id FK
        date checkout_date
        date due_date
        date actual_return_date
        int renewal_count
        string loan_status
    }

    FINE_PAYMENT {
        int fine_id PK
        int loan_id FK
        int patron_id FK
        decimal fine_amount
        string fine_reason
        string payment_status
        datetime paid_at
    }

    BOOK_RESERVATION {
        int reservation_id PK
        int catalog_id FK
        int patron_id FK
        datetime reservation_date
        string queue_status
        date pickup_deadline
    }

    DIGITAL_EBOOK_ASSET {
        int ebook_id PK
        int catalog_id FK
        string file_format
        decimal file_size_mb
        string drm_license_key
        string download_url
    }

    INTER_LIBRARY_LOAN {
        int ill_id PK
        int catalog_id FK
        int patron_id FK
        string partner_library_name
        date request_date
        string ill_status
    }

    STUDY_ROOM_BOOKING {
        int booking_id PK
        int patron_id FK
        string room_number
        datetime booking_start_time
        datetime booking_end_time
        string booking_status
    }

    LIBRARY_STAFF {
        int staff_id PK
        string employee_code
        string full_name
        string role_title
    }

    BOOK_CATALOG_ENTRY ||--o{ BOOK_ITEM : "has_physical_copies"
    BOOK_CATALOG_ENTRY ||--o{ DIGITAL_EBOOK_ASSET : "has_digital_file"
    LIBRARY_PATRON ||--o{ LOAN_TRANSACTION : "borrows"
    BOOK_ITEM ||--o{ LOAN_TRANSACTION : "lent_in"
    LOAN_TRANSACTION ||--o| FINE_PAYMENT : "incurs"
    LIBRARY_PATRON ||--o{ FINE_PAYMENT : "pays"
    BOOK_CATALOG_ENTRY ||--o{ BOOK_RESERVATION : "reserved_in"
    LIBRARY_PATRON ||--o{ BOOK_RESERVATION : "requests"
    BOOK_CATALOG_ENTRY ||--o{ INTER_LIBRARY_LOAN : "requested_via"
    LIBRARY_PATRON ||--o{ STUDY_ROOM_BOOKING : "reserves"
    LIBRARY_STAFF ||--o{ LOAN_TRANSACTION : "processes"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | LIBRARY_PATRON | Độc giả Thư viện | Thông tin thẻ và nhân thân của công dân đăng ký sử dụng thư viện | patron_id, reader_card_number, full_name, patron_type, card_status | Borrows LOAN_TRANSACTION, pays FINE_PAYMENT |
| 2 | BOOK_CATALOG_ENTRY | Biên mục Sách OPAC | Thông tin biểu ghi biên mục xuất bản của cuốn sách theo chuẩn MARC21 | catalog_id, isbn_code, book_title, author_name, dewey_classification_code | Has physical copies BOOK_ITEM, has digital file DIGITAL_EBOOK_ASSET |
| 3 | BOOK_ITEM | Bản sách Vật lý | Thông tin mã vạch Barcode/RFID của từng cuốn sách cụ thể trên kệ | item_id, barcode_id, rfid_tag_id, shelf_location_code, item_status | Belongs to BOOK_CATALOG_ENTRY, lent in LOAN_TRANSACTION |
| 4 | LOAN_TRANSACTION | Giao dịch Mượn Trả Sách | Nhật ký mượn, gia hạn và trả sách của độc giả | loan_id, checkout_date, due_date, actual_return_date, loan_status | Belongs to LIBRARY_PATRON, BOOK_ITEM, incurs FINE_PAYMENT |
| 5 | FINE_PAYMENT | Tiền Phạt Nộp Chậm / Hỏng Sách | Chứng từ thu tiền phạt nộp chậm quá hạn hoặc bồi thường làm hỏng sách | fine_id, fine_amount, fine_reason, payment_status | Belongs to LOAN_TRANSACTION, LIBRARY_PATRON |
| 6 | BOOK_RESERVATION | Đặt chỗ Mượn Sách | Hàng chờ đặt trước các cuốn sách đang được mượn | reservation_id, reservation_date, queue_status, pickup_deadline | Belongs to BOOK_CATALOG_ENTRY, LIBRARY_PATRON |
| 7 | DIGITAL_EBOOK_ASSET | Tài liệu Số / E-Book | Tệp tài liệu số hóa e-Book (PDF/EPUB) kèm khóa quản lý bản quyền DRM | ebook_id, file_format, file_size_mb, drm_license_key | Belongs to BOOK_CATALOG_ENTRY |
| 8 | INTER_LIBRARY_LOAN | Mượn Liên Thư viện (ILL) | Yêu cầu mượn tài liệu từ các thư viện liên kết đối tác ngoài tỉnh | ill_id, partner_library_name, request_date, ill_status | Belongs to BOOK_CATALOG_ENTRY, LIBRARY_PATRON |
| 9 | STUDY_ROOM_BOOKING | Đặt Phòng Học Nhóm | Lịch đăng ký sử dụng phòng nghiên cứu/phòng đọc nhóm tại thư viện | booking_id, room_number, booking_start_time, booking_status | Belongs to LIBRARY_PATRON |
| 10 | LIBRARY_STAFF | Nhân viên Thủ thư | Thông tin cán bộ thủ thư và biên mục xử lý nghiệp vụ thư viện | staff_id, employee_code, full_name, role_title | Processes LOAN_TRANSACTION |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | BOOK_CATALOG_ENTRY | one-to-many | BOOK_ITEM | has_physical_copies | Một đầu sách biên mục có nhiều cuốn bản sao vật lý nằm trên các kệ |
| 2 | BOOK_CATALOG_ENTRY | one-to-many | DIGITAL_EBOOK_ASSET | has_digital_file | Một đầu sách có thể có các phiên bản tệp sách điện tử e-Book số hóa |
| 3 | LIBRARY_PATRON | one-to-many | LOAN_TRANSACTION | borrows | Một độc giả thực hiện nhiều lượt giao dịch mượn trả sách trong lịch sử |
| 4 | BOOK_ITEM | one-to-many | LOAN_TRANSACTION | lent_in | Một cuốn sách vật lý được cho mượn qua nhiều đợt giao dịch khác nhau |
| 5 | LOAN_TRANSACTION | one-to-one | FINE_PAYMENT | incurs | Một lượt mượn sách quá hạn phát sinh duy nhất một khoản phạt nộp chậm |
| 6 | LIBRARY_PATRON | one-to-many | FINE_PAYMENT | pays | Một độc giả nộp các khoản tiền phạt nộp chậm cho thư viện |
| 7 | BOOK_CATALOG_ENTRY | one-to-many | BOOK_RESERVATION | reserved_in | Một đầu sách có thể được nhiều độc giả đặt hàng chờ xếp hàng mượn |
| 8 | LIBRARY_PATRON | one-to-many | BOOK_RESERVATION | requests | Một độc giả có thể đặt chỗ trước cho nhiều đầu sách đang mượn |
| 9 | BOOK_CATALOG_ENTRY | one-to-many | INTER_LIBRARY_LOAN | requested_via | Một đầu sách hiếm được mượn thông qua hệ thống mượn liên thư viện |
| 10 | LIBRARY_STAFF | one-to-many | LOAN_TRANSACTION | processes | Một thủ thư xử lý nhiều giao dịch cho mượn và nhận trả sách tại quầy |
