# Conceptual ERD — E-Discovery & Legal Hold System

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

    LITIGATION_HOLD_MATTER_PROFILE {
        int matter_id PK
        int managing_director_user_id FK
        string matter_title
        string case_docket_number
        decimal estimated_discovery_budget
        string hold_status
        datetime created_at
    }

    CUSTODIAN_HOLD_NOTICE_RECORD {
        int notice_id PK
        int matter_id FK
        int custodian_user_id FK
        string hold_notice_title
        boolean is_digitally_acknowledged
        datetime acknowledged_at
        datetime last_recertified_at
    }

    INPLACE_PRESERVATION_LOCK_LOG {
        int preservation_id PK
        int notice_id FK
        string target_cloud_platform_name
        string cloud_account_identifier
        string api_preservation_lock_guid
        boolean is_lock_active
    }

    TARGETED_ESI_COLLECTION_JOB {
        int collection_id PK
        int matter_id FK
        string targeted_search_keywords
        date search_start_date
        date search_end_date
        bigint collected_data_bytes
        datetime executed_at
    }

    ESI_PROCESSED_DOCUMENT_CONTAINER {
        int esi_doc_id PK
        int collection_id FK
        string original_file_name
        string md5_hash_checksum
        boolean is_duplicate_suppressed
        boolean is_email_thread_inclusive
        string storage_s3_uri
    }

    DOCUMENT_REVIEW_CODING_ITEM {
        int review_id PK
        int esi_doc_id FK
        int reviewer_user_id FK
        string responsiveness_code
        boolean is_privileged
        boolean has_redactions
        datetime reviewed_at
    }

    TAR_PREDICTIVE_CODING_MODEL {
        int tar_id PK
        int matter_id FK
        int trained_seed_count
        decimal model_precision_f1_score
        decimal model_recall_percentage
        datetime model_trained_at
    }

    BATES_PRODUCTION_PACKAGE {
        int production_id PK
        int matter_id FK
        string bates_prefix_code
        int starting_bates_number
        int ending_bates_number
        int total_produced_documents_count
        datetime exported_at
    }

    CONCORDANCE_LOAD_FILE_RECORD {
        int loadfile_id PK
        int production_id FK
        string opt_image_loadfile_s3_uri
        string dat_metadata_loadfile_s3_uri
        datetime generated_at
    }

    ATTORNEY_PRIVILEGE_LOG_ENTRY {
        int priv_log_id PK
        int production_id FK
        int esi_doc_id FK
        string bates_control_number
        string document_date
        string author_name
        text privilege_justification_narrative
    }

    EXECUTIVE_EDISCOVERY_SCORECARD {
        int scorecard_id PK
        int active_litigation_holds_count
        decimal custodian_acknowledgment_rate_percent
        bigint total_processed_esi_bytes
        int average_review_docs_per_hour
        date period_quarter
    }

    LITIGATION_HOLD_MATTER_PROFILE ||--o{ CUSTODIAN_HOLD_NOTICE_RECORD : "dispatches"
    LITIGATION_HOLD_MATTER_PROFILE ||--o{ TARGETED_ESI_COLLECTION_JOB : "collects"
    LITIGATION_HOLD_MATTER_PROFILE ||--o{ TAR_PREDICTIVE_CODING_MODEL : "trains"
    LITIGATION_HOLD_MATTER_PROFILE ||--o{ BATES_PRODUCTION_PACKAGE : "produces"
    CUSTODIAN_HOLD_NOTICE_RECORD ||--o{ INPLACE_PRESERVATION_LOCK_LOG : "locks"
    TARGETED_ESI_COLLECTION_JOB ||--o{ ESI_PROCESSED_DOCUMENT_CONTAINER : "ingests"
    ESI_PROCESSED_DOCUMENT_CONTAINER ||--o| DOCUMENT_REVIEW_CODING_ITEM : "reviewed in"
    BATES_PRODUCTION_PACKAGE ||--o| CONCORDANCE_LOAD_FILE_RECORD : "accompanied by"
    BATES_PRODUCTION_PACKAGE ||--o{ ATTORNEY_PRIVILEGE_LOG_ENTRY : "logs privilege in"
    USER_ACCOUNT ||--o{ LITIGATION_HOLD_MATTER_PROFILE : "manages"
    USER_ACCOUNT ||--o{ CUSTODIAN_HOLD_NOTICE_RECORD : "acknowledges hold in"
    USER_ACCOUNT ||--o{ DOCUMENT_REVIEW_CODING_ITEM : "codes"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | USER_ACCOUNT | Tài khoản Nguời dùng | System credentials and access roles for e-discovery directors, forensic analysts, custodians, and admins. | `user_id`, `username`, `user_role`, `department_code` | Manages `LITIGATION_HOLD_MATTER_PROFILE`, acknowledges hold in `CUSTODIAN_HOLD_NOTICE_RECORD`, codes `DOCUMENT_REVIEW_CODING_ITEM`. |
| 2 | LITIGATION_HOLD_MATTER_PROFILE | Profile Matter Litigation Hold | Central litigation hold matter record tracking matter titles, docket numbers, discovery budgets, and hold status (`ACTIVE/RELEASED`). | `matter_id`, `managing_director_user_id`, `matter_title`, `case_docket_number`, `estimated_discovery_budget`, `hold_status` | Dispatches `CUSTODIAN_HOLD_NOTICE_RECORD`, collects `TARGETED_ESI_COLLECTION_JOB`, trains `TAR_PREDICTIVE_CODING_MODEL`, produces `BATES_PRODUCTION_PACKAGE`, managed by `USER_ACCOUNT`. |
| 3 | CUSTODIAN_HOLD_NOTICE_RECORD | Bản ghi Hold Notice Custodian | Custodian legal hold notice record tracking digital signature acknowledgments, acknowledgment timestamps, and 90-day re-certifications. | `notice_id`, `matter_id`, `custodian_user_id`, `hold_notice_title`, `is_digitally_acknowledged`, `acknowledged_at`, `last_recertified_at` | Belongs to `LITIGATION_HOLD_MATTER_PROFILE`, locks `INPLACE_PRESERVATION_LOCK_LOG`, acknowledged by `USER_ACCOUNT`. |
| 4 | INPLACE_PRESERVATION_LOCK_LOG | Nhật ký Lock In-Place Preservation | Cloud API in-place electronic data preservation log tracking platform (M365/Slack/Google), account IDs, and preservation lock GUIDs. | `preservation_id`, `notice_id`, `target_cloud_platform_name`, `cloud_account_identifier`, `api_preservation_lock_guid`, `is_lock_active` | Belongs to `CUSTODIAN_HOLD_NOTICE_RECORD`. |
| 5 | TARGETED_ESI_COLLECTION_JOB | Job Collection ESI Mục tiêu | Targeted ESI data collection job recording search keywords, date range filters, collected byte counts, and execution timestamps. | `collection_id`, `matter_id`, `targeted_search_keywords`, `search_start_date`, `search_end_date`, `collected_data_bytes` | Belongs to `LITIGATION_HOLD_MATTER_PROFILE`, ingests `ESI_PROCESSED_DOCUMENT_CONTAINER`. |
| 6 | ESI_PROCESSED_DOCUMENT_CONTAINER | Container Document ESI Đã xử lý | ESI document processing record storing original file names, MD5 hashes, duplicate suppression flags, and email thread inclusive flags. | `esi_doc_id`, `collection_id`, `original_file_name`, `md5_hash_checksum`, `is_duplicate_suppressed`, `is_email_thread_inclusive` | Belongs to `TARGETED_ESI_COLLECTION_JOB`, reviewed in `DOCUMENT_REVIEW_CODING_ITEM`. |
| 7 | DOCUMENT_REVIEW_CODING_ITEM | Mục Coding Review Document | Document review coding log recording reviewer user ID, responsiveness status (`RESPONSIVE/NON_RESPONSIVE`), privilege flags, and redaction flags. | `review_id`, `esi_doc_id`, `reviewer_user_id`, `responsiveness_code`, `is_privileged`, `has_redactions`, `reviewed_at` | Belongs to `ESI_PROCESSED_DOCUMENT_CONTAINER`, coded by `USER_ACCOUNT`. |
| 8 | TAR_PREDICTIVE_CODING_MODEL | Model Predictive Coding TAR | Technology-Assisted Review (TAR / CAL) machine learning model record tracking trained seed counts, F1-score precision, and recall %. | `tar_id`, `matter_id`, `trained_seed_count`, `model_precision_f1_score`, `model_recall_percentage` | Belongs to `LITIGATION_HOLD_MATTER_PROFILE`. |
| 9 | BATES_PRODUCTION_PACKAGE | Package Production Bates | Discovery production export package recording Bates prefix codes (`ACME`), starting/ending Bates numbers, and produced document counts. | `production_id`, `matter_id`, `bates_prefix_code`, `starting_bates_number`, `ending_bates_number`, `total_produced_documents_count` | Belongs to `LITIGATION_HOLD_MATTER_PROFILE`, accompanied by `CONCORDANCE_LOAD_FILE_RECORD`, logs privilege in `ATTORNEY_PRIVILEGE_LOG_ENTRY`. |
| 10 | CONCORDANCE_LOAD_FILE_RECORD | File Load Concordance & DAT | Production load file record storing Concordance OPT image load file S3 URIs and DAT metadata load file S3 URIs. | `loadfile_id`, `production_id`, `opt_image_loadfile_s3_uri`, `dat_metadata_loadfile_s3_uri` | Belongs to `BATES_PRODUCTION_PACKAGE`. |
| 11 | ATTORNEY_PRIVILEGE_LOG_ENTRY | Mục Entry Log Privilege | Court-compliant privilege log entry recording Bates control numbers, document dates, authors, and privilege justification narratives. | `priv_log_id`, `production_id`, `esi_doc_id`, `bates_control_number`, `document_date`, `author_name`, `privilege_justification_narrative` | Belongs to `BATES_PRODUCTION_PACKAGE` and `ESI_PROCESSED_DOCUMENT_CONTAINER`. |
| 12 | EXECUTIVE_EDISCOVERY_SCORECARD | Scorecard Executive E-Discovery | Executive e-discovery scorecard aggregating active litigation holds, custodian acknowledgment rates %, processed ESI bytes, and review velocity. | `scorecard_id`, `active_litigation_holds_count`, `custodian_acknowledgment_rate_percent`, `total_processed_esi_bytes`, `average_review_docs_per_hour` | Standalone executive performance entity. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Label | Business Explanation |
|---|-------------|-------------|-----------|-------|----------------------|
| 1 | LITIGATION_HOLD_MATTER_PROFILE | 1 to 0..N | CUSTODIAN_HOLD_NOTICE_RECORD | dispatches | A litigation hold matter profile dispatches custodian hold notice records. |
| 2 | LITIGATION_HOLD_MATTER_PROFILE | 1 to 0..N | TARGETED_ESI_COLLECTION_JOB | collects | A litigation hold matter profile collects targeted ESI data collection jobs. |
| 3 | LITIGATION_HOLD_MATTER_PROFILE | 1 to 0..N | TAR_PREDICTIVE_CODING_MODEL | trains | A litigation hold matter profile trains TAR predictive coding models. |
| 4 | LITIGATION_HOLD_MATTER_PROFILE | 1 to 0..N | BATES_PRODUCTION_PACKAGE | produces | A litigation hold matter profile produces Bates-stamped production packages. |
| 5 | CUSTODIAN_HOLD_NOTICE_RECORD | 1 to 0..N | INPLACE_PRESERVATION_LOCK_LOG | locks | A custodian hold notice record locks cloud accounts via in-place preservation logs. |
| 6 | TARGETED_ESI_COLLECTION_JOB | 1 to 0..N | ESI_PROCESSED_DOCUMENT_CONTAINER | ingests | A targeted ESI collection job ingests ESI processed document containers. |
| 7 | ESI_PROCESSED_DOCUMENT_CONTAINER | 1 to 0..1 | DOCUMENT_REVIEW_CODING_ITEM | reviewed in | An ESI processed document container is reviewed in document review coding items. |
| 8 | BATES_PRODUCTION_PACKAGE | 1 to 1 | CONCORDANCE_LOAD_FILE_RECORD | accompanied by | A Bates production package is accompanied by Concordance load files. |
| 9 | BATES_PRODUCTION_PACKAGE | 1 to 0..N | ATTORNEY_PRIVILEGE_LOG_ENTRY | logs privilege in | A Bates production package logs withheld documents in privilege log entries. |
| 10 | USER_ACCOUNT | 1 to 0..N | LITIGATION_HOLD_MATTER_PROFILE | manages | An e-discovery director user account manages litigation hold matter profiles. |
| 11 | USER_ACCOUNT | 1 to 0..N | CUSTODIAN_HOLD_NOTICE_RECORD | acknowledges hold in | A custodian employee user account acknowledges hold notice records. |
| 12 | USER_ACCOUNT | 1 to 0..N | DOCUMENT_REVIEW_CODING_ITEM | codes | A forensic analyst user account codes document review items. |
