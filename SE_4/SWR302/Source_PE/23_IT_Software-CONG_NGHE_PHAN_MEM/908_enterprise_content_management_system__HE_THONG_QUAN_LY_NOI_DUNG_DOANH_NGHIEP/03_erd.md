# Conceptual ERD — Enterprise Content Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    ENTERPRISE_DOCUMENT["ENTERPRISE_DOCUMENT"]
    DOCUMENT_VERSION["DOCUMENT_VERSION"]
    CONTENT_FOLDER["CONTENT_FOLDER"]
    METADATA_INDEX["METADATA_INDEX"]
    APPROVAL_WORKFLOW["APPROVAL_WORKFLOW"]
    DIGITAL_SIGNATURE_REC["DIGITAL_SIGNATURE_REC"]
    RETENTION_POLICY["RETENTION_POLICY"]
    DOCUMENT_ACL["DOCUMENT_ACL"]
    OCR_SEARCH_INDEX["OCR_SEARCH_INDEX"]
    CONTENT_AUDIT_LOG["CONTENT_AUDIT_LOG"]

    %% Các khối hình thoi quan hệ
    rel_contains{"contains"}
    rel_manages{"manages"}
    rel_records{"records"}
    rel_links_to{"links_to"}
    rel_tracks{"tracks"}
    rel_associates{"associates"}
    rel_evaluates{"evaluates"}
    rel_generates{"generates"}
    rel_audits{"audits"}

    %% Kết nối 1-N
    ENTERPRISE_DOCUMENT ---|1| rel_contains ---|N| DOCUMENT_VERSION
    ENTERPRISE_DOCUMENT ---|1| rel_manages ---|N| CONTENT_FOLDER
    DOCUMENT_VERSION ---|1| rel_records ---|N| METADATA_INDEX
    CONTENT_FOLDER ---|1| rel_links_to ---|N| APPROVAL_WORKFLOW
    METADATA_INDEX ---|1| rel_tracks ---|N| DIGITAL_SIGNATURE_REC
    APPROVAL_WORKFLOW ---|1| rel_associates ---|N| RETENTION_POLICY
    DIGITAL_SIGNATURE_REC ---|1| rel_evaluates ---|N| DOCUMENT_ACL
    RETENTION_POLICY ---|1| rel_generates ---|N| OCR_SEARCH_INDEX
    ENTERPRISE_DOCUMENT ---|1| rel_audits ---|N| CONTENT_AUDIT_LOG

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | ENTERPRISE_DOCUMENT | Thực thể ENTERPRISE_DOCUMENT | Quản lý thông tin chi tiết cho enterprise_document | id (PK), name, status, created_at | Links with related entities |
| 2 | DOCUMENT_VERSION | Thực thể DOCUMENT_VERSION | Quản lý thông tin chi tiết cho document_version | id (PK), name, status, created_at | Links with related entities |
| 3 | CONTENT_FOLDER | Thực thể CONTENT_FOLDER | Quản lý thông tin chi tiết cho content_folder | id (PK), name, status, created_at | Links with related entities |
| 4 | METADATA_INDEX | Thực thể METADATA_INDEX | Quản lý thông tin chi tiết cho metadata_index | id (PK), name, status, created_at | Links with related entities |
| 5 | APPROVAL_WORKFLOW | Thực thể APPROVAL_WORKFLOW | Quản lý thông tin chi tiết cho approval_workflow | id (PK), name, status, created_at | Links with related entities |
| 6 | DIGITAL_SIGNATURE_REC | Thực thể DIGITAL_SIGNATURE_REC | Quản lý thông tin chi tiết cho digital_signature_rec | id (PK), name, status, created_at | Links with related entities |
| 7 | RETENTION_POLICY | Thực thể RETENTION_POLICY | Quản lý thông tin chi tiết cho retention_policy | id (PK), name, status, created_at | Links with related entities |
| 8 | DOCUMENT_ACL | Thực thể DOCUMENT_ACL | Quản lý thông tin chi tiết cho document_acl | id (PK), name, status, created_at | Links with related entities |
| 9 | OCR_SEARCH_INDEX | Thực thể OCR_SEARCH_INDEX | Quản lý thông tin chi tiết cho ocr_search_index | id (PK), name, status, created_at | Links with related entities |
| 10 | CONTENT_AUDIT_LOG | Thực thể CONTENT_AUDIT_LOG | Quản lý thông tin chi tiết cho content_audit_log | id (PK), name, status, created_at | Links with related entities |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | ENTERPRISE_DOCUMENT | 1 to Many | DOCUMENT_VERSION | relates_to | Quản lý mối quan hệ giữa ENTERPRISE_DOCUMENT và DOCUMENT_VERSION |
| 2 | DOCUMENT_VERSION | 1 to Many | CONTENT_FOLDER | relates_to | Quản lý mối quan hệ giữa DOCUMENT_VERSION và CONTENT_FOLDER |
| 3 | CONTENT_FOLDER | 1 to Many | METADATA_INDEX | relates_to | Quản lý mối quan hệ giữa CONTENT_FOLDER và METADATA_INDEX |
| 4 | METADATA_INDEX | 1 to Many | APPROVAL_WORKFLOW | relates_to | Quản lý mối quan hệ giữa METADATA_INDEX và APPROVAL_WORKFLOW |
| 5 | APPROVAL_WORKFLOW | 1 to Many | DIGITAL_SIGNATURE_REC | relates_to | Quản lý mối quan hệ giữa APPROVAL_WORKFLOW và DIGITAL_SIGNATURE_REC |
| 6 | DIGITAL_SIGNATURE_REC | 1 to Many | RETENTION_POLICY | relates_to | Quản lý mối quan hệ giữa DIGITAL_SIGNATURE_REC và RETENTION_POLICY |
| 7 | RETENTION_POLICY | 1 to Many | DOCUMENT_ACL | relates_to | Quản lý mối quan hệ giữa RETENTION_POLICY và DOCUMENT_ACL |
| 8 | DOCUMENT_ACL | 1 to Many | OCR_SEARCH_INDEX | relates_to | Quản lý mối quan hệ giữa DOCUMENT_ACL và OCR_SEARCH_INDEX |
| 9 | OCR_SEARCH_INDEX | 1 to Many | CONTENT_AUDIT_LOG | relates_to | Quản lý mối quan hệ giữa OCR_SEARCH_INDEX và CONTENT_AUDIT_LOG |
