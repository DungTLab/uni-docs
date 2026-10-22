# Conceptual ERD — Multi-tenant SaaS Platform

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    TENANT_ACCOUNT["TENANT_ACCOUNT"]
    TENANT_DATABASE_CONFIG["TENANT_DATABASE_CONFIG"]
    SUBSCRIPTION_PLAN["SUBSCRIPTION_PLAN"]
    TENANT_USER["TENANT_USER"]
    BILLING_INVOICE["BILLING_INVOICE"]
    RESOURCE_USAGE_METER["RESOURCE_USAGE_METER"]
    CUSTOM_DOMAIN_CONFIG["CUSTOM_DOMAIN_CONFIG"]
    FEATURE_TOGGLE_RULE["FEATURE_TOGGLE_RULE"]
    MRR_METRIC_RECORD["MRR_METRIC_RECORD"]
    TENANT_AUDIT_LOG["TENANT_AUDIT_LOG"]

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
    TENANT_ACCOUNT ---|1| rel_contains ---|N| TENANT_DATABASE_CONFIG
    TENANT_ACCOUNT ---|1| rel_manages ---|N| SUBSCRIPTION_PLAN
    TENANT_DATABASE_CONFIG ---|1| rel_records ---|N| TENANT_USER
    SUBSCRIPTION_PLAN ---|1| rel_links_to ---|N| BILLING_INVOICE
    TENANT_USER ---|1| rel_tracks ---|N| RESOURCE_USAGE_METER
    BILLING_INVOICE ---|1| rel_associates ---|N| CUSTOM_DOMAIN_CONFIG
    RESOURCE_USAGE_METER ---|1| rel_evaluates ---|N| FEATURE_TOGGLE_RULE
    CUSTOM_DOMAIN_CONFIG ---|1| rel_generates ---|N| MRR_METRIC_RECORD
    TENANT_ACCOUNT ---|1| rel_audits ---|N| TENANT_AUDIT_LOG

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | TENANT_ACCOUNT | Thực thể TENANT_ACCOUNT | Quản lý thông tin chi tiết cho tenant_account | id (PK), name, status, created_at | Links with related entities |
| 2 | TENANT_DATABASE_CONFIG | Thực thể TENANT_DATABASE_CONFIG | Quản lý thông tin chi tiết cho tenant_database_config | id (PK), name, status, created_at | Links with related entities |
| 3 | SUBSCRIPTION_PLAN | Thực thể SUBSCRIPTION_PLAN | Quản lý thông tin chi tiết cho subscription_plan | id (PK), name, status, created_at | Links with related entities |
| 4 | TENANT_USER | Thực thể TENANT_USER | Quản lý thông tin chi tiết cho tenant_user | id (PK), name, status, created_at | Links with related entities |
| 5 | BILLING_INVOICE | Thực thể BILLING_INVOICE | Quản lý thông tin chi tiết cho billing_invoice | id (PK), name, status, created_at | Links with related entities |
| 6 | RESOURCE_USAGE_METER | Thực thể RESOURCE_USAGE_METER | Quản lý thông tin chi tiết cho resource_usage_meter | id (PK), name, status, created_at | Links with related entities |
| 7 | CUSTOM_DOMAIN_CONFIG | Thực thể CUSTOM_DOMAIN_CONFIG | Quản lý thông tin chi tiết cho custom_domain_config | id (PK), name, status, created_at | Links with related entities |
| 8 | FEATURE_TOGGLE_RULE | Thực thể FEATURE_TOGGLE_RULE | Quản lý thông tin chi tiết cho feature_toggle_rule | id (PK), name, status, created_at | Links with related entities |
| 9 | MRR_METRIC_RECORD | Thực thể MRR_METRIC_RECORD | Quản lý thông tin chi tiết cho mrr_metric_record | id (PK), name, status, created_at | Links with related entities |
| 10 | TENANT_AUDIT_LOG | Thực thể TENANT_AUDIT_LOG | Quản lý thông tin chi tiết cho tenant_audit_log | id (PK), name, status, created_at | Links with related entities |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | TENANT_ACCOUNT | 1 to Many | TENANT_DATABASE_CONFIG | relates_to | Quản lý mối quan hệ giữa TENANT_ACCOUNT và TENANT_DATABASE_CONFIG |
| 2 | TENANT_DATABASE_CONFIG | 1 to Many | SUBSCRIPTION_PLAN | relates_to | Quản lý mối quan hệ giữa TENANT_DATABASE_CONFIG và SUBSCRIPTION_PLAN |
| 3 | SUBSCRIPTION_PLAN | 1 to Many | TENANT_USER | relates_to | Quản lý mối quan hệ giữa SUBSCRIPTION_PLAN và TENANT_USER |
| 4 | TENANT_USER | 1 to Many | BILLING_INVOICE | relates_to | Quản lý mối quan hệ giữa TENANT_USER và BILLING_INVOICE |
| 5 | BILLING_INVOICE | 1 to Many | RESOURCE_USAGE_METER | relates_to | Quản lý mối quan hệ giữa BILLING_INVOICE và RESOURCE_USAGE_METER |
| 6 | RESOURCE_USAGE_METER | 1 to Many | CUSTOM_DOMAIN_CONFIG | relates_to | Quản lý mối quan hệ giữa RESOURCE_USAGE_METER và CUSTOM_DOMAIN_CONFIG |
| 7 | CUSTOM_DOMAIN_CONFIG | 1 to Many | FEATURE_TOGGLE_RULE | relates_to | Quản lý mối quan hệ giữa CUSTOM_DOMAIN_CONFIG và FEATURE_TOGGLE_RULE |
| 8 | FEATURE_TOGGLE_RULE | 1 to Many | MRR_METRIC_RECORD | relates_to | Quản lý mối quan hệ giữa FEATURE_TOGGLE_RULE và MRR_METRIC_RECORD |
| 9 | MRR_METRIC_RECORD | 1 to Many | TENANT_AUDIT_LOG | relates_to | Quản lý mối quan hệ giữa MRR_METRIC_RECORD và TENANT_AUDIT_LOG |
