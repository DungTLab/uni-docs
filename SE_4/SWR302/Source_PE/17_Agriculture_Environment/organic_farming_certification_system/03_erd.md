# Conceptual ERD — Organic Farming Certification System

## Mermaid Code

```mermaid
erDiagram
    ORGANIC_PRODUCER {
        int producer_id PK
        string business_name
        string tax_id
        string contact_person
        string phone_number
        string email
        string business_address
        datetime registration_date
    }

    FARM_SITE {
        int site_id PK
        int producer_id FK
        string site_name
        decimal total_area_hectares
        decimal organic_area_hectares
        string geo_coordinates
        decimal buffer_zone_meters
        string site_status
    }

    CERTIFICATION_APPLICATION {
        int application_id PK
        int producer_id FK
        int site_id FK
        string target_standard_code
        datetime submission_date
        decimal fee_amount
        string application_status
    }

    ORGANIC_SYSTEM_PLAN {
        int osp_id PK
        int application_id FK
        string crop_rotation_plan
        string pest_management_strategy
        string soil_fertility_plan
        string water_source
        string osp_status
    }

    FARM_INSPECTION_RECORD {
        int inspection_id PK
        int application_id FK
        int inspector_id FK
        datetime inspection_date
        string inspection_type
        boolean buffer_zone_valid
        string overall_result
    }

    LAB_TEST_SAMPLE {
        int sample_id PK
        int inspection_id FK
        string sample_type
        string sample_code
        string testing_lab_name
        decimal pesticide_residue_ppm
        string test_result
    }

    AGRICULTURAL_INPUT_LOG {
        int input_id PK
        int site_id FK
        string input_type
        string commercial_brand
        string manufacturer
        boolean omri_listed_status
        datetime application_date
    }

    NON_COMPLIANCE_NOTICE {
        int notice_id PK
        int inspection_id FK
        string severity_level
        string violation_clause
        string corrective_action_required
        date deadline_date
        string resolution_status
    }

    ORGANIC_CERTIFICATE {
        int certificate_id PK
        int application_id FK
        string certificate_number
        string standard_type
        date issue_date
        date expiration_date
        string qr_code_url
        string certificate_status
    }

    INSPECTOR_USER {
        int inspector_id PK
        string full_name
        string iso_qualification_code
        string organization
        string phone_number
        boolean is_active
    }

    AUDIT_LOG {
        int log_id PK
        int user_id FK
        string action_type
        string entity_affected
        datetime timestamp
        string ip_address
        string change_summary
    }

    ORGANIC_PRODUCER ||--o{ FARM_SITE : "operates"
    ORGANIC_PRODUCER ||--o{ CERTIFICATION_APPLICATION : "submits"
    FARM_SITE ||--o{ CERTIFICATION_APPLICATION : "certified_under"
    FARM_SITE ||--o{ AGRICULTURAL_INPUT_LOG : "records_input"
    CERTIFICATION_APPLICATION ||--|| ORGANIC_SYSTEM_PLAN : "includes"
    CERTIFICATION_APPLICATION ||--o{ FARM_INSPECTION_RECORD : "undergoes"
    INSPECTOR_USER ||--o{ FARM_INSPECTION_RECORD : "conducts"
    FARM_INSPECTION_RECORD ||--o{ LAB_TEST_SAMPLE : "yields"
    FARM_INSPECTION_RECORD ||--o{ NON_COMPLIANCE_NOTICE : "generates"
    CERTIFICATION_APPLICATION ||--|| ORGANIC_CERTIFICATE : "results_in"
    INSPECTOR_USER ||--o{ AUDIT_LOG : "triggers"
```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | ORGANIC_PRODUCER | Chủ Thể Sản Xuất Hữu Cơ | Stores corporate profile, business registration, tax identity, and primary contact for certified producers | producer_id, business_name, tax_id, contact_person, phone_number | Operates FARM_SITE, submits CERTIFICATION_APPLICATION |
| 2 | FARM_SITE | Trang Trại / Vùng Trồng | Represents physical farm land sites, total/organic acreage, GIS polygon coordinates, and buffer zones | site_id, producer_id, site_name, total_area_hectares, buffer_zone_meters | Belongs to PRODUCER, certified under APPLICATION, records INPUT_LOG |
| 3 | CERTIFICATION_APPLICATION | Hồ Sơ Đăng Ký Cấp Chứng Nhận | Central application claim capturing target organic standards (USDA, EU, TCVN), submission dates, and status | application_id, producer_id, site_id, target_standard_code, application_status | Submits by PRODUCER, includes OSP, undergoes INSPECTION, results in CERTIFICATE |
| 4 | ORGANIC_SYSTEM_PLAN | Kế Hoạch Sản Xuất Hữu Cơ | Document detailing producer crop rotations, soil fertility management, pest controls, and seed sources | osp_id, application_id, crop_rotation_plan, pest_management_strategy, osp_status | Included in CERTIFICATION_APPLICATION |
| 5 | FARM_INSPECTION_RECORD | Biên Bản Đánh Giá Thực Địa | Records on-site physical farm audit findings, buffer zone measurements, storage checks, and inspection status | inspection_id, application_id, inspector_id, inspection_date, overall_result | Undergone by APPLICATION, conducted by INSPECTOR, yields SAMPLE, generates NOTICE |
| 6 | LAB_TEST_SAMPLE | Mẫu Kiếm Nghiệm Phân Tích | Captures laboratory test results for soil, water, and tissue samples (pesticide residues in ppm, heavy metals) | sample_id, inspection_id, sample_type, sample_code, pesticide_residue_ppm, test_result | Yielded by FARM_INSPECTION_RECORD |
| 7 | AGRICULTURAL_INPUT_LOG | Nhật Ký Vẫn Tư Đầu Vào | Logs all fertilizers, composts, bio-pesticides, and seeds applied on farm sites, tracking OMRI listing status | input_id, site_id, input_type, commercial_brand, omri_listed_status, application_date | Recorded on FARM_SITE |
| 8 | NON_COMPLIANCE_NOTICE | Thông Báo Vi Phạm Hữu Cơ | Records formal non-compliance issues identified during inspections, required corrective actions, and deadlines | notice_id, inspection_id, severity_level, violation_clause, deadline_date, resolution_status | Generated by FARM_INSPECTION_RECORD |
| 9 | ORGANIC_CERTIFICATE | Chứng Nhận Sản Phẩm Hữu Cơ | Stores issued digital organic certificates, unique certificate numbers, issue/expiry dates, and QR code URLs | certificate_id, application_id, certificate_number, standard_type, issue_date, expiration_date, qr_code_url | Results from CERTIFICATION_APPLICATION |
| 10 | INSPECTOR_USER | Chuyên Viên Đánh Giá Hữu Cơ | Stores certified organic inspector profiles, ISO 19011 auditor qualification codes, and organizational affiliation | inspector_id, full_name, iso_qualification_code, organization, is_active | Conducts FARM_INSPECTION_RECORD, triggers AUDIT_LOG |
| 11 | AUDIT_LOG | Nhật Ký Hệ Thống & Kiểm Toán | Immutable audit trail tracking user actions, certification decision changes, and security events | log_id, user_id, action_type, entity_affected, timestamp | Triggered by INSPECTOR_USER and other system users |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | ORGANIC_PRODUCER | one-to-many | FARM_SITE | operates | An organic producer can operate multiple physical farm land sites and production parcels |
| 2 | ORGANIC_PRODUCER | one-to-many | CERTIFICATION_APPLICATION | submits | A producer can submit multiple certification applications across different standards and years |
| 3 | FARM_SITE | one-to-many | CERTIFICATION_APPLICATION | certified_under | A farm site can be certified under multiple target standards (e.g., USDA Organic + EU Organic) |
| 4 | FARM_SITE | one-to-many | AGRICULTURAL_INPUT_LOG | records_input | A farm site records multiple agricultural inputs (fertilizers, composts, seeds) applied over time |
| 5 | CERTIFICATION_APPLICATION | one-to-one | ORGANIC_SYSTEM_PLAN | includes | A certification application includes exactly one complete Organic System Plan (OSP) document |
| 6 | CERTIFICATION_APPLICATION | one-to-many | FARM_INSPECTION_RECORD | undergoes | An application undergoes one or more initial, surveillance, or spot farm inspections |
| 7 | INSPECTOR_USER | one-to-many | FARM_INSPECTION_RECORD | conducts | A certified organic inspector conducts multiple physical on-site farm inspections |
| 8 | FARM_INSPECTION_RECORD | one-to-many | LAB_TEST_SAMPLE | yields | A farm inspection can yield multiple soil, water, or plant tissue samples for lab residue testing |
| 9 | FARM_INSPECTION_RECORD | one-to-many | NON_COMPLIANCE_NOTICE | generates | A farm inspection can generate non-compliance notices for identified standard violations |
| 10 | CERTIFICATION_APPLICATION | one-to-one | ORGANIC_CERTIFICATE | results_in | An approved certification application results in exactly one official Organic Certificate |
| 11 | INSPECTOR_USER | one-to-many | AUDIT_LOG | triggers | User actions and certification decisions trigger immutable security audit log entries |
