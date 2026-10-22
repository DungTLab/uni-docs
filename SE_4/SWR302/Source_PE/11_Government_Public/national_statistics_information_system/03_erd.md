# Conceptual ERD — National Statistics Information System

## Mermaid Code

```mermaid
erDiagram
    STATISTICAL_SURVEY {
        int survey_id PK
        string survey_code
        string survey_title
        string survey_type
        int target_sample_size
        date start_date
        date end_date
        string status
    }

    SURVEY_RESPONDENT {
        int respondent_id PK
        string respondent_code
        string respondent_type
        string province_code
        string district_code
        string enumeration_area_code
    }

    CAPI_DATA_COLLECTION {
        int capi_id PK
        int survey_id FK
        int respondent_id FK
        string enumerator_badge_no
        decimal gps_latitude
        decimal gps_longitude
        string payload_json_data
        datetime submitted_timestamp
        string verification_status
    }

    CPI_BASKET_ITEM {
        int item_id PK
        string item_code
        string item_name_vietnamese
        string category_group_name
        decimal base_year_price
        decimal current_month_price
        decimal item_weight_ratio
    }

    MACRO_ECONOMIC_INDICATOR {
        int indicator_id PK
        string indicator_code
        string indicator_name
        string calculation_period
        decimal indicator_value
        string measurement_unit
        date published_date
    }

    POPULATION_CENSUS_RECORD {
        int census_id PK
        int survey_id FK
        int province_code
        int total_population_count
        int male_count
        int female_count
        decimal urban_ratio_percentage
    }

    STATISTICAL_MICRODATA {
        int microdata_id PK
        int survey_id FK
        string dataset_name
        string anonymization_algorithm
        decimal reidentification_risk_score
        string access_permission_level
    }

    LINE_MINISTRY_FEED {
        int feed_id PK
        string ministry_name
        string feed_data_type
        datetime ingested_timestamp
        int total_records_ingested
        string data_checksum
    }

    STATISTICAL_ANALYST {
        int analyst_id PK
        string employee_code
        string full_name
        string assigned_department
    }

    STATISTICS_AUDIT_LOG {
        int audit_id PK
        int survey_id FK
        string action_taken
        string performed_by
        datetime timestamp
    }

    STATISTICAL_SURVEY ||--o{ CAPI_DATA_COLLECTION : "collects_via"
    SURVEY_RESPONDENT ||--o{ CAPI_DATA_COLLECTION : "responds_in"
    STATISTICAL_SURVEY ||--o{ POPULATION_CENSUS_RECORD : "aggregates_into"
    STATISTICAL_SURVEY ||--o{ STATISTICAL_MICRODATA : "generates"
    CPI_BASKET_ITEM ||--o{ MACRO_ECONOMIC_INDICATOR : "computes_into"
    LINE_MINISTRY_FEED ||--o{ MACRO_ECONOMIC_INDICATOR : "supplies_data_for"
    STATISTICAL_ANALYST ||--o{ STATISTICAL_SURVEY : "manages"
    STATISTICAL_ANALYST ||--o{ MACRO_ECONOMIC_INDICATOR : "approves"
    STATISTICAL_SURVEY ||--o{ STATISTICS_AUDIT_LOG : "tracked_in"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | STATISTICAL_SURVEY | Cuộc Điều tra Thống kê | Thông tin các cuộc tổng điều tra (Dân số, Doanh nghiệp, Nông nghiệp, Giá) | survey_id, survey_code, survey_title, target_sample_size, status | Collects via CAPI_DATA_COLLECTION, aggregates into POPULATION_CENSUS_RECORD |
| 2 | SURVEY_RESPONDENT | Đơn vị Điều tra (Đối tượng) | Thông tin mã định danh và địa bàn của hộ gia đình, doanh nghiệp trả lời | respondent_id, respondent_code, respondent_type, enumeration_area_code | Responds in CAPI_DATA_COLLECTION |
| 3 | CAPI_DATA_COLLECTION | Phiếu Điều tra CAPI Điện tử | Dữ liệu phiếu điều tra thô thu thập qua ứng dụng CAPI máy tính bảng đính kèm GPS | capi_id, enumerator_badge_no, gps_latitude, payload_json_data, verification_status | Belongs to STATISTICAL_SURVEY, SURVEY_RESPONDENT |
| 4 | CPI_BASKET_ITEM | Mặt hàng Rổ Giá CPI | Danh mục 752 mặt hàng đại diện tính CPI, trọng số và giá chu kỳ | item_id, item_code, item_name_vietnamese, item_weight_ratio | Computes into MACRO_ECONOMIC_INDICATOR |
| 5 | MACRO_ECONOMIC_INDICATOR | Chỉ số Kinh tế Vĩ mô | Các chỉ số vĩ mô đã tính toán (CPI, GDP, Tốc độ tăng trưởng, Lạm phát) | indicator_id, indicator_code, indicator_name, indicator_value, published_date | Computed from CPI_BASKET_ITEM, LINE_MINISTRY_FEED |
| 6 | POPULATION_CENSUS_RECORD | Số liệu Thống kê Dân số | Kết quả tổng hợp quy mô dân số, tháp dân số theo từng Tỉnh/Thành | census_id, total_population_count, male_count, female_count | Belongs to STATISTICAL_SURVEY |
| 7 | STATISTICAL_MICRODATA | Tập Dữ liệu Vi mô (Microdata) | Tập dữ liệu vi mô đã qua thuật toán xử lý ẩn danh hóa phục vụ Open Data | microdata_id, dataset_name, anonymization_algorithm, reidentification_risk_score | Belongs to STATISTICAL_SURVEY |
| 8 | LINE_MINISTRY_FEED | Luồng Dữ liệu Hành chính Bộ/Ngành | Dữ liệu tích hợp liên thông tự động từ Thuế, Hải quan, Bảo hiểm Xã hội | feed_id, ministry_name, feed_data_type, total_records_ingested | Supplies data for MACRO_ECONOMIC_INDICATOR |
| 9 | STATISTICAL_ANALYST | Chuyên viên Thống kê GSO | Thông tin chuyên viên Tổng cục Thống kê phụ trách thiết kế và duyệt chỉ số | analyst_id, employee_code, full_name, assigned_department | Manages SURVEY, approves MACRO_ECONOMIC_INDICATOR |
| 10 | STATISTICS_AUDIT_LOG | Nhật ký Kiểm toán Thống kê | Lưu toàn bộ lịch sử thu thập dữ liệu CAPI, làm sạch, tính toán CPI/GDP | audit_id, action_taken, performed_by, timestamp | Belongs to STATISTICAL_SURVEY |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | STATISTICAL_SURVEY | one-to-many | CAPI_DATA_COLLECTION | collects_via | Một cuộc điều tra thu thập hàng triệu phiếu điều tra điện tử CAPI |
| 2 | SURVEY_RESPONDENT | one-to-many | CAPI_DATA_COLLECTION | responds_in | Một đối tượng điều tra trả lời thông tin trong các phiếu điều tra CAPI |
| 3 | STATISTICAL_SURVEY | one-to-many | POPULATION_CENSUS_RECORD | aggregates_into | Một cuộc tổng điều tra dân số tổng hợp thành các bản ghi số liệu dân số |
| 4 | STATISTICAL_SURVEY | one-to-many | STATISTICAL_MICRODATA | generates | Một cuộc điều tra sản xuất ra các tập dữ liệu vi mô mở cho nghiên cứu |
| 5 | CPI_BASKET_ITEM | one-to-many | MACRO_ECONOMIC_INDICATOR | computes_into | Các mặt hàng trong rổ giá tính toán ra các chỉ số CPI và lạm phát |
| 6 | LINE_MINISTRY_FEED | one-to-many | MACRO_ECONOMIC_INDICATOR | supplies_data_for | Các luồng dữ liệu từ Bộ/Ngành cung cấp số liệu đầu vào tính toán GDP |
| 7 | STATISTICAL_ANALYST | one-to-many | STATISTICAL_SURVEY | manages | Một chuyên viên thống kê GSO thiết kế và quản lý nhiều cuộc điều tra |
| 8 | STATISTICAL_ANALYST | one-to-many | MACRO_ECONOMIC_INDICATOR | approves | Một chuyên viên thẩm định và duyệt phát hành các chỉ số vĩ mô |
| 9 | STATISTICAL_SURVEY | one-to-many | STATISTICS_AUDIT_LOG | tracked_in | Mọi thao tác xử lý dữ liệu điều tra được ghi nhật ký kiểm toán bất biến |
