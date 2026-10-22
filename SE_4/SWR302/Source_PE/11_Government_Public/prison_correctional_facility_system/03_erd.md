# Conceptual ERD — Prison & Correctional Facility System

## Mermaid Code

```mermaid
erDiagram
    INMATE_RECORD {
        int inmate_id PK
        string inmate_code_number
        string national_pin
        string full_name
        date date_of_birth
        string conviction_crime_name
        int sentence_months
        date admission_date
        date expected_release_date
        string custody_status
    }

    CELL_BLOCK {
        int cell_id PK
        string cell_code
        string block_name
        string security_classification_level
        int max_capacity_inmates
        int current_occupancy_count
    }

    INMATE_FAMILY_VISIT {
        int visit_id PK
        int inmate_id FK
        string visitor_full_name
        string visitor_national_pin
        string relationship_type
        date visit_date
        string visit_permit_qr_code
        string approval_status
    }

    SENTENCE_REDUCTION_EVALUATION {
        int evaluation_id PK
        int inmate_id FK
        string evaluation_quarter
        string merit_ranking_category
        int reduction_months_proposed
        string court_decision_number
        date approval_date
    }

    INMATE_MEDICAL_RECORD {
        int medical_id PK
        int inmate_id FK
        string diagnosis_summary
        string physical_condition_rating
        string chronic_disease_notes
        date examination_date
    }

    INMATE_TRUST_ACCOUNT {
        int account_id PK
        int inmate_id FK
        decimal current_balance_amount
        decimal monthly_spending_limit
        string status
    }

    VOCATIONAL_TRAINING_LOG {
        int log_id PK
        int inmate_id FK
        string skill_trade_name
        int hours_completed
        decimal monthly_performance_stipend
        date logged_date
    }

    PRISON_GUARD_OFFICER {
        int officer_id PK
        string badge_number
        string full_name
        string assigned_cell_block
        string rank_title
    }

    PERIMETER_SECURITY_ALERT {
        int alert_id PK
        int cell_id FK
        string alert_type
        string camera_location_code
        string ai_threat_level
        datetime detected_at
    }

    CORRECTIONAL_AUDIT_LOG {
        int audit_id PK
        int inmate_id FK
        string action_taken
        string performed_by
        datetime timestamp
    }

    CELL_BLOCK ||--o{ INMATE_RECORD : "houses"
    INMATE_RECORD ||--o{ INMATE_FAMILY_VISIT : "receives_visits"
    INMATE_RECORD ||--o{ SENTENCE_REDUCTION_EVALUATION : "evaluated_for"
    INMATE_RECORD ||--o{ INMATE_MEDICAL_RECORD : "diagnosed_in"
    INMATE_RECORD ||--o| INMATE_TRUST_ACCOUNT : "holds"
    INMATE_RECORD ||--o{ VOCATIONAL_TRAINING_LOG : "participates_in"
    PRISON_GUARD_OFFICER ||--o{ INMATE_RECORD : "supervises"
    CELL_BLOCK ||--o{ PERIMETER_SECURITY_ALERT : "monitored_by"
    INMATE_RECORD ||--o{ CORRECTIONAL_AUDIT_LOG : "tracked_in"
    PRISON_GUARD_OFFICER ||--o{ CELL_BLOCK : "assigned_to"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | INMATE_RECORD | Hồ sơ Phạm nhân | Thông tin cá nhân, mã phạm nhân, tội danh, án phạt tù và ngày chấp hành xong | inmate_id, inmate_code_number, conviction_crime_name, sentence_months, custody_status | Housed in CELL_BLOCK, holds TRUST_ACCOUNT |
| 2 | CELL_BLOCK | Buồng Giam Trại giam | Danh mục nhà giam, buồng giam, cấp độ an ninh bảo vệ và sức chứa | cell_id, cell_code, block_name, security_classification_level, max_capacity_inmates | Houses INMATE_RECORD, monitored by SECURITY_ALERT |
| 3 | INMATE_FAMILY_VISIT | Sổ Thăm gặp Thân nhân | Nhật ký đăng ký lịch thăm gặp phạm nhân của thân nhân và mã QR phép thăm | visit_id, visitor_full_name, relationship_type, visit_permit_qr_code, approval_status | Belongs to INMATE_RECORD |
| 4 | SENTENCE_REDUCTION_EVALUATION | Hồ sơ Xét Giảm án Đặc xá | Kết quả thi đua cải tạo, số tháng đề nghị giảm án và quyết định của Tòa án | evaluation_id, evaluation_quarter, merit_ranking_category, reduction_months_proposed | Belongs to INMATE_RECORD |
| 5 | INMATE_MEDICAL_RECORD | Bệnh án Quân y Trại giam | Hồ sơ theo dõi sức khỏe, bệnh mãn tính và lịch sử khám chữa bệnh tại bệnh xá | medical_id, diagnosis_summary, physical_condition_rating, examination_date | Belongs to INMATE_RECORD |
| 6 | INMATE_TRUST_ACCOUNT | Tài khoản Tiền Lưu ký | Tài khoản tiền điện tử phạm nhân dùng để mua đồ căng tin và nhận tiền thân nhân gửi | account_id, current_balance_amount, monthly_spending_limit, status | Belongs to INMATE_RECORD |
| 7 | VOCATIONAL_TRAINING_LOG | Nhật ký Lao động Hướng nghiệp | Ghi nhận kết quả học nghề, số giờ lao động sản xuất và tiền thưởng thi đua | log_id, skill_trade_name, hours_completed, monthly_performance_stipend | Belongs to INMATE_RECORD |
| 8 | PRISON_GUARD_OFFICER | Cán bộ Quản giáo | Thông tin cán bộ quản giáo, cảnh sát bảo vệ được phân công phụ trách buồng giam | officer_id, badge_number, full_name, assigned_cell_block, rank_title | Supervises INMATE_RECORD, assigned to CELL_BLOCK |
| 9 | PERIMETER_SECURITY_ALERT | Cảnh báo An ninh Hàng rào | Cảnh báo AI camera phát hiện trốn trại, xâm nhập trái phép hoặc xô xát | alert_id, alert_type, camera_location_code, ai_threat_level, detected_at | Belongs to CELL_BLOCK |
| 10 | CORRECTIONAL_AUDIT_LOG | Nhật ký Kiểm toán Trại giam | Lưu toàn bộ vết tiếp nhận, chuyển buồng, điểm danh, kỷ luật, giảm án và lưu ký | audit_id, action_taken, performed_by, timestamp | Belongs to INMATE_RECORD |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | CELL_BLOCK | one-to-many | INMATE_RECORD | houses | Một buồng giam giam giữ nhiều phạm nhân theo hạn mức sức chứa |
| 2 | INMATE_RECORD | one-to-many | INMATE_FAMILY_VISIT | receives_visits | Một phạm nhân nhận được nhiều lượt thăm gặp từ thân nhân gia đình |
| 3 | INMATE_RECORD | one-to-many | SENTENCE_REDUCTION_EVALUATION | evaluated_for | Một phạm nhân được đánh giá thi đua và xét giảm án qua nhiều đợt |
| 4 | INMATE_RECORD | one-to-many | INMATE_MEDICAL_RECORD | diagnosed_in | Một phạm nhân được theo dõi sức khỏe qua nhiều đợt khám tại bệnh xá |
| 5 | INMATE_RECORD | one-to-one | INMATE_TRUST_ACCOUNT | holds | Mỗi phạm nhân sở hữu duy nhất một Tài khoản tiền lưu ký mua căng tin |
| 6 | INMATE_RECORD | one-to-many | VOCATIONAL_TRAINING_LOG | participates_in | Một phạm nhân tham gia nhiều đợt học nghề và lao động hướng nghiệp |
| 7 | PRISON_GUARD_OFFICER | one-to-many | INMATE_RECORD | supervises | Một cán bộ quản giáo trực tiếp quản lý và theo dõi nhiều phạm nhân |
| 8 | CELL_BLOCK | one-to-many | PERIMETER_SECURITY_ALERT | monitored_by | Một khu buồng giam được giám sát an ninh bởi hệ thống camera AI hàng rào |
| 9 | INMATE_RECORD | one-to-many | CORRECTIONAL_AUDIT_LOG | tracked_in | Mọi thao tác trên hồ sơ phạm nhân được ghi lại nhật ký kiểm toán bất biến |
| 10 | PRISON_GUARD_OFFICER | one-to-many | CELL_BLOCK | assigned_to | Một cán bộ quản giáo được phân công phụ trách quản lý các buồng giam |
