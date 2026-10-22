# Conceptual ERD — Diagnostic Imaging Archive System

## Mermaid Code

```mermaid
erDiagram
    USER_ACCOUNT {
        int user_id PK
        string full_name
        string username
        string user_role
        string medical_license_no
        boolean is_active
    }

    PATIENT_IMAGING_MASTER {
        int patient_id PK
        string patient_mrn
        string full_name
        date date_of_birth
        string gender
        datetime created_at
    }

    RADIOLOGY_STUDY_ORDER {
        int order_id PK
        int patient_id FK
        string accession_number
        string modality_code
        string requested_procedure_description
        string ordering_physician_name
        datetime ordered_at
        string priority_level
    }

    DICOM_STUDY_INSTANCE {
        int study_pk PK
        int order_id FK
        int patient_id FK
        string study_instance_uid
        date study_date
        string study_description
        int series_count
        int total_image_count
    }

    DICOM_SERIES_INSTANCE {
        int series_pk PK
        int study_pk FK
        string series_instance_uid
        int series_number
        string modality
        string series_description
        string body_part_examined
        int image_count
    }

    DICOM_IMAGE_SOP_INSTANCE {
        int sop_pk PK
        int series_pk FK
        string sop_instance_uid
        int instance_number
        string transfer_syntax_uid
        int rows_height
        int columns_width
        string file_storage_path
    }

    AI_CAD_FINDING {
        int ai_finding_id PK
        int study_pk FK
        string ai_model_name
        string lesion_type
        decimal confidence_probability_percent
        string roi_bounding_box_coordinates
        datetime processed_at
    }

    KEY_IMAGE_SELECTION {
        int key_image_id PK
        int study_pk FK
        int sop_pk FK
        int radiologist_user_id FK
        string key_image_note
        string bookmark_type
        datetime created_at
    }

    RADIATION_DOSE_RECORD {
        int dose_id PK
        int study_pk FK
        decimal total_dlp_mgy_cm
        decimal ctdi_vol_mgy
        decimal total_fluoroscopy_time_sec
        datetime recorded_at
    }

    STORAGE_LIFECYCLE_TIER {
        int storage_tier_id PK
        int study_pk FK
        string current_tier_name
        string cloud_vna_location_uri
        boolean is_compressed_lossless
        datetime migrated_at
    }

    RADIOLOGY_DIAGNOSTIC_REPORT {
        int report_id PK
        int study_pk FK
        int radiologist_user_id FK
        text findings_text
        text impression_text
        string report_status
        datetime signed_at
        string digital_signature_hash
    }

    IMAGING_AUDIT_LOG {
        int audit_id PK
        int study_pk FK
        int user_id FK
        string action_type
        string client_ip_address
        datetime logged_at
    }

    PATIENT_IMAGING_MASTER ||--o{ RADIOLOGY_STUDY_ORDER : "places"
    PATIENT_IMAGING_MASTER ||--o{ DICOM_STUDY_INSTANCE : "owns"
    RADIOLOGY_STUDY_ORDER ||--o| DICOM_STUDY_INSTANCE : "fulfills"
    DICOM_STUDY_INSTANCE ||--o{ DICOM_SERIES_INSTANCE : "contains"
    DICOM_SERIES_INSTANCE ||--o{ DICOM_IMAGE_SOP_INSTANCE : "contains"
    DICOM_STUDY_INSTANCE ||--o{ AI_CAD_FINDING : "analyzed for"
    DICOM_STUDY_INSTANCE ||--o{ KEY_IMAGE_SELECTION : "bookmarked via"
    DICOM_IMAGE_SOP_INSTANCE ||--o{ KEY_IMAGE_SELECTION : "references"
    DICOM_STUDY_INSTANCE ||--o| RADIATION_DOSE_RECORD : "tracks radiation via"
    DICOM_STUDY_INSTANCE ||--o| STORAGE_LIFECYCLE_TIER : "stored in"
    DICOM_STUDY_INSTANCE ||--o| RADIOLOGY_DIAGNOSTIC_REPORT : "interpreted in"
    DICOM_STUDY_INSTANCE ||--o{ IMAGING_AUDIT_LOG : "audited via"
    USER_ACCOUNT ||--o{ RADIOLOGY_DIAGNOSTIC_REPORT : "interprets and signs"
    USER_ACCOUNT ||--o{ KEY_IMAGE_SELECTION : "bookmarks"
    USER_ACCOUNT ||--o{ IMAGING_AUDIT_LOG : "performs action on"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | USER_ACCOUNT | Tài khoản Nguời dùng | Credentials and access permissions for radiologists, radiologic technologists, physicians, and PACS admins. | `user_id`, `username`, `user_role`, `medical_license_no` | Interprets and signs `RADIOLOGY_DIAGNOSTIC_REPORT`, bookmarks `KEY_IMAGE_SELECTION`, performs `IMAGING_AUDIT_LOG`. |
| 2 | PATIENT_IMAGING_MASTER | Sổ Master Chẩn đoán Hình ảnh | Master patient demographic and medical record index for imaging studies. | `patient_id`, `patient_mrn`, `full_name`, `date_of_birth`, `gender` | Places `RADIOLOGY_STUDY_ORDER`, owns `DICOM_STUDY_INSTANCE`. |
| 3 | RADIOLOGY_STUDY_ORDER | Đơn Đặt Hàng Chẩn đoán | Order requisition created in RIS/EHR containing Accession Number, Modality type, and exam description. | `order_id`, `patient_id`, `accession_number`, `modality_code`, `ordering_physician_name` | Belongs to `PATIENT_IMAGING_MASTER`, fulfills `DICOM_STUDY_INSTANCE`. |
| 4 | DICOM_STUDY_INSTANCE | Nghiên cứu Hình ảnh DICOM | Top-level DICOM Study entity representing a complete imaging session (e.g., CT Chest/Abdomen). | `study_pk`, `patient_id`, `study_instance_uid`, `study_date`, `study_description` | Fulfills `RADIOLOGY_STUDY_ORDER`, contains `DICOM_SERIES_INSTANCE`, summarized in `RADIOLOGY_DIAGNOSTIC_REPORT`. |
| 5 | DICOM_SERIES_INSTANCE | Chuỗi Hình ảnh DICOM | Sub-grouping of DICOM images sharing same modality, acquisition plane, or pulse sequence (e.g., Axial T1 MRI series). | `series_pk`, `study_pk`, `series_instance_uid`, `series_number`, `modality`, `body_part_examined` | Belongs to `DICOM_STUDY_INSTANCE`, contains `DICOM_IMAGE_SOP_INSTANCE`. |
| 6 | DICOM_IMAGE_SOP_INSTANCE | Thể hiện DICOM (SOP) | Individual 2D image slice file storing pixel matrix, spatial position coordinates, and DICOM header tags. | `sop_pk`, `series_pk`, `sop_instance_uid`, `instance_number`, `file_storage_path` | Belongs to `DICOM_SERIES_INSTANCE`, referenced by `KEY_IMAGE_SELECTION`. |
| 7 | AI_CAD_FINDING | Kết quả AI Chẩn đoán | Deep learning AI CAD lesion detection outputs, probability scores, and ROI bounding box coordinates. | `ai_finding_id`, `study_pk`, `ai_model_name`, `lesion_type`, `confidence_probability_percent` | Belongs to `DICOM_STUDY_INSTANCE`. |
| 8 | KEY_IMAGE_SELECTION | Ảnh Chìa khóa Đánh dấu | Radiologist-selected key image snapshots, annotated ROIs, and Key Object Selection (KOS) entries. | `key_image_id`, `study_pk`, `sop_pk`, `radiologist_user_id`, `key_image_note` | Relates `DICOM_STUDY_INSTANCE` and `DICOM_IMAGE_SOP_INSTANCE`. |
| 9 | RADIATION_DOSE_RECORD | Nhật ký Liều Lượng Bức xạ | Radiation Dose Structured Report (RDSR) metrics tracking Dose Length Product (DLP) and CTDIvol. | `dose_id`, `study_pk`, `total_dlp_mgy_cm`, `ctdi_vol_mgy`, `recorded_at` | Belongs to `DICOM_STUDY_INSTANCE`. |
| 10 | STORAGE_LIFECYCLE_TIER | Tầng Vòng đời Lưu trữ | Storage tier management status tracking Hot NVMe, Warm NAS, and Cold Cloud VNA archival URIs. | `storage_tier_id`, `study_pk`, `current_tier_name`, `cloud_vna_location_uri`, `is_compressed_lossless` | Belongs to `DICOM_STUDY_INSTANCE`. |
| 11 | RADIOLOGY_DIAGNOSTIC_REPORT | Báo cáo Chẩn đoán Hình ảnh | Finalized, digitally signed radiology report containing findings text, impression text, and sign-off timestamp. | `report_id`, `study_pk`, `radiologist_user_id`, `findings_text`, `impression_text`, `signed_at` | Belongs to `DICOM_STUDY_INSTANCE`, signed by `USER_ACCOUNT`. |
| 12 | IMAGING_AUDIT_LOG | Nhật ký Kiểm toán DICOM | HIPAA compliance audit trail logging every image view, export, print, and query action with IP address. | `audit_id`, `study_pk`, `user_id`, `action_type`, `client_ip_address`, `logged_at` | Relates `DICOM_STUDY_INSTANCE` and `USER_ACCOUNT`. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Label | Business Explanation |
|---|-------------|-------------|-----------|-------|----------------------|
| 1 | PATIENT_IMAGING_MASTER | 1 to 0..N | RADIOLOGY_STUDY_ORDER | places | A patient places radiology study orders in the system. |
| 2 | PATIENT_IMAGING_MASTER | 1 to 0..N | DICOM_STUDY_INSTANCE | owns | A patient owns multiple DICOM study instances over time. |
| 3 | RADIOLOGY_STUDY_ORDER | 1 to 0..1 | DICOM_STUDY_INSTANCE | fulfills | A radiology study order is fulfilled by a completed DICOM study instance. |
| 4 | DICOM_STUDY_INSTANCE | 1 to 1..N | DICOM_SERIES_INSTANCE | contains | A DICOM study instance contains one or more DICOM series instances. |
| 5 | DICOM_SERIES_INSTANCE | 1 to 1..N | DICOM_IMAGE_SOP_INSTANCE | contains | A DICOM series instance contains multiple individual DICOM SOP image slice files. |
| 6 | DICOM_STUDY_INSTANCE | 1 to 0..N | AI_CAD_FINDING | analyzed for | A DICOM study instance is analyzed for deep learning AI CAD findings. |
| 7 | DICOM_STUDY_INSTANCE | 1 to 0..N | KEY_IMAGE_SELECTION | bookmarked via | A DICOM study instance has radiologist-selected key images bookmarked. |
| 8 | DICOM_IMAGE_SOP_INSTANCE | 1 to 0..N | KEY_IMAGE_SELECTION | references | A key image selection references a specific DICOM SOP image slice. |
| 9 | DICOM_STUDY_INSTANCE | 1 to 0..1 | RADIATION_DOSE_RECORD | tracks radiation via | A DICOM study tracks patient radiation dose exposure via RDSR records. |
| 10 | DICOM_STUDY_INSTANCE | 1 to 0..1 | STORAGE_LIFECYCLE_TIER | stored in | A DICOM study is stored in a specific storage lifecycle tier (Hot/Warm/Cloud VNA). |
| 11 | DICOM_STUDY_INSTANCE | 1 to 0..1 | RADIOLOGY_DIAGNOSTIC_REPORT | interpreted in | A DICOM study instance is interpreted in a final signed radiology diagnostic report. |
| 12 | DICOM_STUDY_INSTANCE | 1 to 0..N | IMAGING_AUDIT_LOG | audited via | All user access actions on a DICOM study are logged in imaging audit logs. |
| 13 | USER_ACCOUNT | 1 to 0..N | RADIOLOGY_DIAGNOSTIC_REPORT | interprets and signs | A radiologist user account interprets and signs final diagnostic reports. |
| 14 | USER_ACCOUNT | 1 to 0..N | KEY_IMAGE_SELECTION | bookmarks | A radiologist user account bookmarks key image snapshots. |
| 15 | USER_ACCOUNT | 1 to 0..N | IMAGING_AUDIT_LOG | performs action on | A user account performs view/export actions tracked in audit logs. |
