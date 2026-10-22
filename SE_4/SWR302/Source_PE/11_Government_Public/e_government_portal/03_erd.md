# Conceptual ERD — E-Government Portal

## Mermaid Code

```mermaid
erDiagram
    CITIZEN_USER {
        int citizen_id PK
        string vneid_number
        string full_name
        string identity_card_no
        string email
        string phone_number
        datetime created_at
    }

    BUSINESS_ENTITY {
        int business_id PK
        string tax_code
        string company_name
        string legal_representative
        string address
    }

    PUBLIC_SERVICE {
        int service_id PK
        string service_code
        string service_name
        string managing_agency
        decimal fee_amount
        int sla_processing_days
    }

    APPLICATION {
        int application_id PK
        string dossier_code
        int citizen_id FK
        int business_id FK
        int service_id FK
        string status
        datetime submitted_at
        datetime expected_completion_at
    }

    APPLICATION_DOCUMENT {
        int document_id PK
        int application_id FK
        string document_name
        string file_path
        string file_hash
        datetime uploaded_at
    }

    PAYMENT_TRANSACTION {
        int transaction_id PK
        int application_id FK
        string transaction_code
        decimal amount
        string payment_method
        string status
        datetime paid_at
    }

    CERTIFICATE_ISSUED {
        int certificate_id PK
        int application_id FK
        string certificate_number
        string digital_signature
        datetime issue_date
        datetime expiry_date
    }

    GRIEVANCE_FEEDBACK {
        int feedback_id PK
        int citizen_id FK
        int application_id FK
        string topic
        string content
        string response_status
        datetime created_at
    }

    GOVERNMENT_OFFICIAL {
        int official_id PK
        string official_code
        string full_name
        string agency_name
        string department
        string role
    }

    SYSTEM_AUDIT_LOG {
        int log_id PK
        int official_id FK
        int application_id FK
        string action_name
        string detail
        datetime action_timestamp
    }

    CITIZEN_USER ||--o{ APPLICATION : "submits"
    BUSINESS_ENTITY ||--o{ APPLICATION : "owns"
    PUBLIC_SERVICE ||--o{ APPLICATION : "defines"
    APPLICATION ||--|{ APPLICATION_DOCUMENT : "contains"
    APPLICATION ||--o| PAYMENT_TRANSACTION : "generates"
    APPLICATION ||--o| CERTIFICATE_ISSUED : "results_in"
    CITIZEN_USER ||--o{ GRIEVANCE_FEEDBACK : "files"
    APPLICATION ||--o{ GRIEVANCE_FEEDBACK : "referenced_in"
    GOVERNMENT_OFFICIAL ||--o{ SYSTEM_AUDIT_LOG : "performs"
    APPLICATION ||--o{ SYSTEM_AUDIT_LOG : "tracked_in"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | CITIZEN_USER | Nguời dùng Công dân | Lưu trữ thông tin tài khoản công dân được đồng bộ qua VNeID | citizen_id, vneid_number, identity_card_no | Has many APPLICATION, files GRIEVANCE_FEEDBACK |
| 2 | BUSINESS_ENTITY | Thực thể Doanh nghiệp | Thông tin pháp nhân doanh nghiệp nộp thủ tục | business_id, tax_code, company_name | Has many APPLICATION |
| 3 | PUBLIC_SERVICE | Dịch vụ Công | Danh mục thủ tục hành chính công được cung cấp trên cổng | service_id, service_code, fee_amount, sla_processing_days | Defines many APPLICATION |
| 4 | APPLICATION | Hồ sơ Hành chính | Thông tin hồ sơ thủ tục do công dân/doanh nghiệp nộp | application_id, dossier_code, status | Belongs to CITIZEN_USER, PUBLIC_SERVICE |
| 5 | APPLICATION_DOCUMENT | Tài liệu Hồ sơ | Các tệp đính kèm điện tử chứng minh trong hồ sơ | document_id, file_path, file_hash | Belongs to APPLICATION |
| 6 | PAYMENT_TRANSACTION | Giao dịch Thanh toán | Nhật ký thanh toán lệ phí hành chính trực tuyến | transaction_id, transaction_code, amount, status | Belongs to APPLICATION |
| 7 | CERTIFICATE_ISSUED | Kết quả / Giấy phép Đã cấp | Văn bản điện tử kết quả có chữ ký số được ban hành | certificate_id, certificate_number, digital_signature | Belongs to APPLICATION |
| 8 | GRIEVANCE_FEEDBACK | Phản ánh Kiến nghị | Thông tin phản ánh, khiếu nại của người dân về thủ tục | feedback_id, topic, content, response_status | Belongs to CITIZEN_USER, APPLICATION |
| 9 | GOVERNMENT_OFFICIAL | Cán bộ Nhà nước | Thông tin công chức, viên chức thụ lý hồ sơ | official_id, official_code, agency_name, role | Performs many SYSTEM_AUDIT_LOG |
| 10 | SYSTEM_AUDIT_LOG | Nhật ký Truy vết Hệ thống | Ghi lại mọi thao tác truy cập, xử lý, ký duyệt trên hệ thống | log_id, action_name, action_timestamp | Belongs to GOVERNMENT_OFFICIAL, APPLICATION |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | CITIZEN_USER | one-to-many | APPLICATION | submits | Một công dân có thể nộp nhiều hồ sơ thủ tục hành chính khác nhau |
| 2 | BUSINESS_ENTITY | one-to-many | APPLICATION | owns | Một doanh nghiệp sở hữu nhiều hồ sơ đăng ký kinh doanh/giấy phép |
| 3 | PUBLIC_SERVICE | one-to-many | APPLICATION | defines | Một dịch vụ công quy định cấu hình cho nhiều hồ sơ được khởi tạo |
| 4 | APPLICATION | one-to-many | APPLICATION_DOCUMENT | contains | Một hồ sơ chứa nhiều tài liệu, văn bản điện tử đính kèm |
| 5 | APPLICATION | one-to-one | PAYMENT_TRANSACTION | generates | Một hồ sơ phát sinh tối đa một giao dịch thanh toán lệ phí trực tuyến |
| 6 | APPLICATION | one-to-one | CERTIFICATE_ISSUED | results_in | Một hồ sơ sau khi duyệt sẽ sinh ra một kết quả văn bản số hóa duy nhất |
| 7 | CITIZEN_USER | one-to-many | GRIEVANCE_FEEDBACK | files | Một công dân có thể gửi nhiều phản ánh kiến nghị |
| 8 | APPLICATION | one-to-many | GRIEVANCE_FEEDBACK | referenced_in | Một hồ sơ có thể bị phản ánh nhiều lần về tiến độ hoặc kết quả |
| 9 | GOVERNMENT_OFFICIAL | one-to-many | SYSTEM_AUDIT_LOG | performs | Một cán bộ thực hiện nhiều thao tác xử lý và được ghi nhật ký |
| 10 | APPLICATION | one-to-many | SYSTEM_AUDIT_LOG | tracked_in | Một hồ sơ lưu vết lịch sử luân chuyển qua nhiều bước kiểm tra |
