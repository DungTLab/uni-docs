# Conceptual ERD — Government Procurement System

## Mermaid Code

```mermaid
erDiagram
    CONTRACTOR {
        int contractor_id PK
        string business_registration_no
        string company_name
        string tax_code
        string legal_representative
        string capacity_rating
        datetime registered_at
    }

    PROCURING_ENTITY {
        int agency_id PK
        string agency_code
        string agency_name
        string agency_type
        string address
    }

    PROCUREMENT_PLAN {
        int plan_id PK
        int agency_id FK
        string plan_code
        string plan_title
        decimal total_budget
        int fiscal_year
        string approval_decision_no
    }

    TENDER_NOTICE {
        int tender_id PK
        int plan_id FK
        string tender_code
        string tender_title
        string procurement_method
        datetime bidding_start_time
        datetime bidding_close_time
        decimal estimated_price
    }

    BIDDING_DOCUMENT {
        int document_id PK
        int tender_id FK
        string document_version
        string file_path
        string file_hash
        datetime published_at
    }

    BID_SUBMISSION {
        int submission_id PK
        int tender_id FK
        int contractor_id FK
        string submission_code
        datetime submitted_timestamp
        string encrypted_file_hash
        decimal quoted_bid_price
        string status
    }

    BID_SECURITY {
        int security_id PK
        int submission_id FK
        string guarantee_number
        string issuing_bank
        decimal guarantee_amount
        date expiry_date
        string status
    }

    BID_EVALUATION {
        int evaluation_id PK
        int submission_id FK
        decimal technical_score
        decimal financial_score
        int final_ranking
        string evaluation_status
        datetime evaluated_at
    }

    PROCUREMENT_CONTRACT {
        int contract_id PK
        int tender_id FK
        int contractor_id FK
        string contract_number
        decimal contract_value
        date signing_date
        date completion_date
        string contract_status
    }

    CONTRACT_DISPUTE {
        int dispute_id PK
        int tender_id FK
        int contractor_id FK
        string dispute_title
        string content
        string resolution_status
        datetime filed_at
    }

    PROCURING_ENTITY ||--o{ PROCUREMENT_PLAN : "creates"
    PROCUREMENT_PLAN ||--o{ TENDER_NOTICE : "contains"
    TENDER_NOTICE ||--o{ BIDDING_DOCUMENT : "includes"
    TENDER_NOTICE ||--o{ BID_SUBMISSION : "receives"
    CONTRACTOR ||--o{ BID_SUBMISSION : "submits"
    BID_SUBMISSION ||--o| BID_SECURITY : "backed_by"
    BID_SUBMISSION ||--o| BID_EVALUATION : "evaluated_in"
    TENDER_NOTICE ||--o| PROCUREMENT_CONTRACT : "results_in"
    CONTRACTOR ||--o{ PROCUREMENT_CONTRACT : "signs"
    CONTRACTOR ||--o{ CONTRACT_DISPUTE : "files"
    TENDER_NOTICE ||--o{ CONTRACT_DISPUTE : "disputed_in"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | CONTRACTOR | Nhà thầu Dự thầu | Thông tin năng lực pháp lý, tài chính của doanh nghiệp tham gia đấu thầu | contractor_id, business_registration_no, company_name, tax_code | Submits BID_SUBMISSION, signs PROCUREMENT_CONTRACT |
| 2 | PROCURING_ENTITY | Bên Mời Thầu / Chủ Đầu Tư | Cơ quan nhà nước, đơn vị sự nghiệp phát hành gói thầu | agency_id, agency_code, agency_name, agency_type | Creates PROCUREMENT_PLAN |
| 3 | PROCUREMENT_PLAN | Kế hoạch Lựa chọn Nhà thầu | Kế hoạch tổng thể danh mục dự án và dự toán ngân sách mua sắm | plan_id, plan_code, plan_title, total_budget | Belongs to PROCURING_ENTITY, contains TENDER_NOTICE |
| 4 | TENDER_NOTICE | Thông báo Mời thầu | Gói thầu công khai được mời thầu kèm giá gói thầu và phương thức chọn thầu | tender_id, tender_code, tender_title, bidding_close_time | Belongs to PROCUREMENT_PLAN, receives BID_SUBMISSION |
| 5 | BIDDING_DOCUMENT | Hồ sơ Mời thầu (HSMT) | Bộ tài liệu quy định tiêu chuẩn đánh giá kỹ thuật và tài chính | document_id, document_version, file_path, file_hash | Belongs to TENDER_NOTICE |
| 6 | BID_SUBMISSION | Hồ sơ Dự thầu (E-HSDT) | Bộ hồ sơ đề xuất kỹ thuật và tài chính đã mã hóa do nhà thầu nộp | submission_id, submission_code, quoted_bid_price, status | Belongs to TENDER_NOTICE, CONTRACTOR |
| 7 | BID_SECURITY | Thư Bảo lãnh Dự thầu | Chứng thư bảo lãnh tài chính do ngân hàng phát hành bảo đảm nộp thầu | security_id, guarantee_number, issuing_bank, guarantee_amount | Belongs to BID_SUBMISSION |
| 8 | BID_EVALUATION | Kết quả Đánh giá Hồ sơ Dự thầu | Biên bản chấm điểm kỹ thuật, tài chính và xếp hạng nhà thầu | evaluation_id, technical_score, financial_score, final_ranking | Belongs to BID_SUBMISSION |
| 9 | PROCUREMENT_CONTRACT | Hợp đồng Mua sắm Công | Hợp đồng điện tử ký kết giữa Chủ đầu tư và Nhà thầu trúng thầu | contract_id, contract_number, contract_value, contract_status | Belongs to TENDER_NOTICE, CONTRACTOR |
| 10 | CONTRACT_DISPUTE | Kiến nghị / Khiếu nại Đấu thầu | Đơn kiến nghị về kết quả đấu thầu hoặc vi phạm quy trình | dispute_id, dispute_title, content, resolution_status | Belongs to CONTRACTOR, TENDER_NOTICE |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | PROCURING_ENTITY | one-to-many | PROCUREMENT_PLAN | creates | Một chủ đầu tư tạo nhiều kế hoạch lựa chọn nhà thầu theo năm tài chính |
| 2 | PROCUREMENT_PLAN | one-to-many | TENDER_NOTICE | contains | Một kế hoạch lựa chọn nhà thầu chia thành nhiều gói thầu mời thầu |
| 3 | TENDER_NOTICE | one-to-many | BIDDING_DOCUMENT | includes | Một gói thầu phát hành các phiên bản hồ sơ mời thầu bổ sung |
| 4 | TENDER_NOTICE | one-to-many | BID_SUBMISSION | receives | Một gói thầu tiếp nhận hồ sơ dự thầu từ nhiều nhà thầu tham gia |
| 5 | CONTRACTOR | one-to-many | BID_SUBMISSION | submits | Một nhà thầu nộp nhiều hồ sơ dự thầu cho các gói thầu khác nhau |
| 6 | BID_SUBMISSION | one-to-one | BID_SECURITY | backed_by | Mỗi hồ sơ dự thầu bắt buộc đi kèm một bảo lãnh dự thầu tương ứng |
| 7 | BID_SUBMISSION | one-to-one | BID_EVALUATION | evaluated_in | Mỗi hồ sơ dự thầu sinh ra một kết quả đánh giá chấm thầu |
| 8 | TENDER_NOTICE | one-to-one | PROCUREMENT_CONTRACT | results_in | Một gói thầu sau khi chọn thầu thành công ký kết một hợp đồng chính |
| 9 | CONTRACTOR | one-to-many | PROCUREMENT_CONTRACT | signs | Một nhà thầu trúng thầu có thể ký nhiều hợp đồng mua sắm công |
| 10 | CONTRACTOR | one-to-many | CONTRACT_DISPUTE | files | Một nhà thầu có thể gửi đơn kiến nghị xử lý khiếu nại |
| 11 | TENDER_NOTICE | one-to-many | CONTRACT_DISPUTE | disputed_in | Một gói thầu có thể phát sinh các đơn kiến nghị khiếu nại |
