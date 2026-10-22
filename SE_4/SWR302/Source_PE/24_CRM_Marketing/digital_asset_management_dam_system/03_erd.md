# Conceptual ERD — Digital Asset Management (DAM) System

## Mermaid Code

```mermaid
erDiagram
    USER_ACCOUNT {
        int user_id PK
        string full_name
        string username
        string user_role
        string department_code
        boolean is_active
    }

    ASSET_TAXONOMY_SCHEMA {
        int taxonomy_id PK
        string schema_name
        text controlled_vocabulary_json
        string iptc_exif_mapping_rules
    }

    MASTER_MEDIA_ASSET {
        int asset_id PK
        int taxonomy_id FK
        int uploader_user_id FK
        string file_title
        string original_filename
        string file_format_extension
        bigint file_size_bytes
        string storage_s3_uri
        string current_active_version
        boolean is_drm_protected
    }

    AI_AUTO_TAG_METADATA {
        int tag_id PK
        int asset_id FK
        string detected_tag_name
        decimal confidence_percentage
        string tag_category_type
        text extracted_ocr_text
        text dominant_hex_colors_csv
    }

    MEDIA_RENDITION_FILE {
        int rendition_id PK
        int asset_id FK
        string rendition_format
        int target_width_px
        int target_height_px
        string cdn_delivery_url
        datetime generated_at
    }

    ASSET_VERSION_RECORD {
        int version_id PK
        int asset_id FK
        int designer_user_id FK
        string version_number_slug
        string storage_s3_version_uri
        text version_change_notes
        datetime checked_in_at
    }

    FILE_CHECKOUT_LOCK {
        int lock_id PK
        int asset_id FK
        int locked_by_user_id FK
        string lock_status
        datetime locked_at
    }

    DRM_LICENSE_CONTRACT {
        int drm_id PK
        int asset_id FK
        string licensing_vendor_name
        string territory_scope_restrictions
        date expiration_date
        boolean is_expired
    }

    MODEL_RELEASE_DOCUMENT {
        int release_id PK
        int drm_id FK
        string talent_name
        string model_release_pdf_uri
        date signed_date
    }

    ADOBE_PLUGIN_SESSION {
        int session_id PK
        int designer_user_id FK
        string adobe_app_name
        string plugin_version
        datetime authenticated_at
    }

    ASSET_UTILIZATION_METRIC {
        int metric_id PK
        int asset_id FK
        int total_downloads_count
        int total_cms_embeds_count
        date period_month
    }

    DAM_STORAGE_COST_LOG {
        int storage_log_id PK
        bigint total_s3_storage_bytes
        bigint glacier_archive_bytes
        decimal total_monthly_storage_cost
        date calculated_date
    }

    ASSET_TAXONOMY_SCHEMA ||--o{ MASTER_MEDIA_ASSET : "structures"
    MASTER_MEDIA_ASSET ||--o{ AI_AUTO_TAG_METADATA : "auto-tagged in"
    MASTER_MEDIA_ASSET ||--o{ MEDIA_RENDITION_FILE : "transcoded into"
    MASTER_MEDIA_ASSET ||--o{ ASSET_VERSION_RECORD : "versioned in"
    MASTER_MEDIA_ASSET ||--o| FILE_CHECKOUT_LOCK : "locked via"
    MASTER_MEDIA_ASSET ||--o| DRM_LICENSE_CONTRACT : "governed by"
    MASTER_MEDIA_ASSET ||--o{ ASSET_UTILIZATION_METRIC : "tracked in"
    DRM_LICENSE_CONTRACT ||--o{ MODEL_RELEASE_DOCUMENT : "includes"
    USER_ACCOUNT ||--o{ MASTER_MEDIA_ASSET : "uploads"
    USER_ACCOUNT ||--o{ ASSET_VERSION_RECORD : "checks in"
    USER_ACCOUNT ||--o{ FILE_CHECKOUT_LOCK : "locks"
    USER_ACCOUNT ||--o{ ADOBE_PLUGIN_SESSION : "authenticates"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | USER_ACCOUNT | Tài khoản Nguời dùng | System credentials and access roles for DAM admins, creative designers, content authors, and CMOs. | `user_id`, `username`, `user_role`, `department_code` | Uploads `MASTER_MEDIA_ASSET`, checks in `ASSET_VERSION_RECORD`, locks `FILE_CHECKOUT_LOCK`. |
| 2 | ASSET_TAXONOMY_SCHEMA | Schema Taxonomy Metadata | Controlled metadata taxonomy definition storing controlled vocabulary, IPTC/EXIF mappings, and facet rules. | `taxonomy_id`, `schema_name`, `controlled_vocabulary_json`, `iptc_exif_mapping_rules` | Structures `MASTER_MEDIA_ASSET`. |
| 3 | MASTER_MEDIA_ASSET | Master Asset Truyền thông | Master media file record storing title, format extension, file size, S3 URI, active version, and DRM status. | `asset_id`, `taxonomy_id`, `uploader_user_id`, `file_title`, `file_format_extension`, `storage_s3_uri`, `current_active_version` | Structured by `ASSET_TAXONOMY_SCHEMA`, auto-tagged in `AI_AUTO_TAG_METADATA`, transcoded into `MEDIA_RENDITION_FILE`, versioned in `ASSET_VERSION_RECORD`, locked via `FILE_CHECKOUT_LOCK`, governed by `DRM_LICENSE_CONTRACT`. |
| 4 | AI_AUTO_TAG_METADATA | Metadata AI Auto-Tag | AI computer vision metadata tag record storing detected object tags, confidence %, OCR text, and dominant colors. | `tag_id`, `asset_id`, `detected_tag_name`, `confidence_percentage`, `extracted_ocr_text`, `dominant_hex_colors_csv` | Belongs to `MASTER_MEDIA_ASSET`. |
| 5 | MEDIA_RENDITION_FILE | File Rendition Truyền thông | Transcoded dynamic media rendition file (WebP, AVIF, MP4, HLS) storing dimensions, format, and CDN delivery URL. | `rendition_id`, `asset_id`, `rendition_format`, `target_width_px`, `target_height_px`, `cdn_delivery_url` | Belongs to `MASTER_MEDIA_ASSET`. |
| 6 | ASSET_VERSION_RECORD | Bản ghi Version Asset | Historical asset version tree record tracking version numbers (`v1.0`, `v2.0`), S3 version URIs, and check-in notes. | `version_id`, `asset_id`, `designer_user_id`, `version_number_slug`, `storage_s3_version_uri`, `version_change_notes` | Belongs to `MASTER_MEDIA_ASSET`, checked in by `USER_ACCOUNT`. |
| 7 | FILE_CHECKOUT_LOCK | Lock Check-Out File | Exclusive file check-out lock entity tracking lock status (`LOCKED_BY_USER`), locking user ID, and timestamps. | `lock_id`, `asset_id`, `locked_by_user_id`, `lock_status`, `locked_at` | Belongs to `MASTER_MEDIA_ASSET`, locked by `USER_ACCOUNT`. |
| 8 | DRM_LICENSE_CONTRACT | Hợp đồng License DRM | Digital Rights Management legal contract recording vendor names, territory scope restrictions, expiration dates, and expired flags. | `drm_id`, `asset_id`, `licensing_vendor_name`, `territory_scope_restrictions`, `expiration_date`, `is_expired` | Governs `MASTER_MEDIA_ASSET`, includes `MODEL_RELEASE_DOCUMENT`. |
| 9 | MODEL_RELEASE_DOCUMENT | Văn bản Release Model | Talent model release contract document linking model names and release PDF storage URIs to DRM contracts. | `release_id`, `drm_id`, `talent_name`, `model_release_pdf_uri`, `signed_date` | Belongs to `DRM_LICENSE_CONTRACT`. |
| 10 | ADOBE_PLUGIN_SESSION | Session Plugin Adobe CC | Desktop Adobe Creative Cloud plugin integration session tracking Adobe app names (Photoshop/InDesign) and versions. | `session_id`, `designer_user_id`, `adobe_app_name`, `plugin_version`, `authenticated_at` | Belongs to `USER_ACCOUNT`. |
| 11 | ASSET_UTILIZATION_METRIC | Chỉ số Sử dụng Asset | Monthly asset utilization telemetry recording download counts, CMS embed counts, and page load impacts. | `metric_id`, `asset_id`, `total_downloads_count`, `total_cms_embeds_count`, `period_month` | Belongs to `MASTER_MEDIA_ASSET`. |
| 12 | DAM_STORAGE_COST_LOG | Sổ cái Chi phí Storage DAM | Monthly cloud object storage cost log tracking total S3 storage bytes, Glacier archive bytes, and monthly storage costs. | `storage_log_id`, `total_s3_storage_bytes`, `glacier_archive_bytes`, `total_monthly_storage_cost` | Standalone cloud infrastructure accounting entity. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Label | Business Explanation |
|---|-------------|-------------|-----------|-------|----------------------|
| 1 | ASSET_TAXONOMY_SCHEMA | 1 to 0..N | MASTER_MEDIA_ASSET | structures | A metadata taxonomy schema structures master media assets. |
| 2 | MASTER_MEDIA_ASSET | 1 to 0..N | AI_AUTO_TAG_METADATA | auto-tagged in | A master media asset is auto-tagged in AI computer vision metadata. |
| 3 | MASTER_MEDIA_ASSET | 1 to 0..N | MEDIA_RENDITION_FILE | transcoded into | A master media asset is transcoded into dynamic web renditions (WebP, MP4). |
| 4 | MASTER_MEDIA_ASSET | 1 to 1..N | ASSET_VERSION_RECORD | versioned in | A master media asset is versioned in historical version records. |
| 5 | MASTER_MEDIA_ASSET | 1 to 0..1 | FILE_CHECKOUT_LOCK | locked via | A master media asset is locked via a file check-out lock. |
| 6 | MASTER_MEDIA_ASSET | 1 to 0..1 | DRM_LICENSE_CONTRACT | governed by | A master media asset is governed by a DRM license contract. |
| 7 | MASTER_MEDIA_ASSET | 1 to 0..N | ASSET_UTILIZATION_METRIC | tracked in | A master media asset is tracked in asset utilization metrics. |
| 8 | DRM_LICENSE_CONTRACT | 1 to 0..N | MODEL_RELEASE_DOCUMENT | includes | A DRM license contract includes signed talent model release documents. |
| 9 | USER_ACCOUNT | 1 to 0..N | MASTER_MEDIA_ASSET | uploads | A creative designer user account uploads master media assets. |
| 10 | USER_ACCOUNT | 1 to 0..N | ASSET_VERSION_RECORD | checks in | A creative designer user account checks in asset versions. |
| 11 | USER_ACCOUNT | 1 to 0..N | FILE_CHECKOUT_LOCK | locks | A creative designer user account locks files during check-out. |
| 12 | USER_ACCOUNT | 1 to 0..N | ADOBE_PLUGIN_SESSION | authenticates | A designer user account authenticates desktop Adobe CC plugin sessions. |
