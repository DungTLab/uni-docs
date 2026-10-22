# Conceptual ERD — IT Risk Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    IT_RISK_ITEM["IT_RISK_ITEM"]
    RISK_MATRIX_SCORE["RISK_MATRIX_SCORE"]
    MITIGATION_ACTION_PLAN["MITIGATION_ACTION_PLAN"]
    KRI_INDICATOR["KRI_INDICATOR"]
    CONTROL_ASSESSMENT["CONTROL_ASSESSMENT"]
    RESIDUAL_RISK_RECORD["RESIDUAL_RISK_RECORD"]
    THREAT_MAPPING_LINK["THREAT_MAPPING_LINK"]
    MONTE_CARLO_SIMULATION["MONTE_CARLO_SIMULATION"]
    RISK_HEATMAP_CONFIG["RISK_HEATMAP_CONFIG"]
    RISK_AUDIT_LOG["RISK_AUDIT_LOG"]

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
    IT_RISK_ITEM ---|1| rel_contains ---|N| RISK_MATRIX_SCORE
    IT_RISK_ITEM ---|1| rel_manages ---|N| MITIGATION_ACTION_PLAN
    RISK_MATRIX_SCORE ---|1| rel_records ---|N| KRI_INDICATOR
    MITIGATION_ACTION_PLAN ---|1| rel_links_to ---|N| CONTROL_ASSESSMENT
    KRI_INDICATOR ---|1| rel_tracks ---|N| RESIDUAL_RISK_RECORD
    CONTROL_ASSESSMENT ---|1| rel_associates ---|N| THREAT_MAPPING_LINK
    RESIDUAL_RISK_RECORD ---|1| rel_evaluates ---|N| MONTE_CARLO_SIMULATION
    THREAT_MAPPING_LINK ---|1| rel_generates ---|N| RISK_HEATMAP_CONFIG
    IT_RISK_ITEM ---|1| rel_audits ---|N| RISK_AUDIT_LOG

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | IT_RISK_ITEM | Thực thể IT_RISK_ITEM | Quản lý thông tin chi tiết cho it_risk_item | id (PK), name, status, created_at | Links with related entities |
| 2 | RISK_MATRIX_SCORE | Thực thể RISK_MATRIX_SCORE | Quản lý thông tin chi tiết cho risk_matrix_score | id (PK), name, status, created_at | Links with related entities |
| 3 | MITIGATION_ACTION_PLAN | Thực thể MITIGATION_ACTION_PLAN | Quản lý thông tin chi tiết cho mitigation_action_plan | id (PK), name, status, created_at | Links with related entities |
| 4 | KRI_INDICATOR | Thực thể KRI_INDICATOR | Quản lý thông tin chi tiết cho kri_indicator | id (PK), name, status, created_at | Links with related entities |
| 5 | CONTROL_ASSESSMENT | Thực thể CONTROL_ASSESSMENT | Quản lý thông tin chi tiết cho control_assessment | id (PK), name, status, created_at | Links with related entities |
| 6 | RESIDUAL_RISK_RECORD | Thực thể RESIDUAL_RISK_RECORD | Quản lý thông tin chi tiết cho residual_risk_record | id (PK), name, status, created_at | Links with related entities |
| 7 | THREAT_MAPPING_LINK | Thực thể THREAT_MAPPING_LINK | Quản lý thông tin chi tiết cho threat_mapping_link | id (PK), name, status, created_at | Links with related entities |
| 8 | MONTE_CARLO_SIMULATION | Thực thể MONTE_CARLO_SIMULATION | Quản lý thông tin chi tiết cho monte_carlo_simulation | id (PK), name, status, created_at | Links with related entities |
| 9 | RISK_HEATMAP_CONFIG | Thực thể RISK_HEATMAP_CONFIG | Quản lý thông tin chi tiết cho risk_heatmap_config | id (PK), name, status, created_at | Links with related entities |
| 10 | RISK_AUDIT_LOG | Thực thể RISK_AUDIT_LOG | Quản lý thông tin chi tiết cho risk_audit_log | id (PK), name, status, created_at | Links with related entities |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | IT_RISK_ITEM | 1 to Many | RISK_MATRIX_SCORE | relates_to | Quản lý mối quan hệ giữa IT_RISK_ITEM và RISK_MATRIX_SCORE |
| 2 | RISK_MATRIX_SCORE | 1 to Many | MITIGATION_ACTION_PLAN | relates_to | Quản lý mối quan hệ giữa RISK_MATRIX_SCORE và MITIGATION_ACTION_PLAN |
| 3 | MITIGATION_ACTION_PLAN | 1 to Many | KRI_INDICATOR | relates_to | Quản lý mối quan hệ giữa MITIGATION_ACTION_PLAN và KRI_INDICATOR |
| 4 | KRI_INDICATOR | 1 to Many | CONTROL_ASSESSMENT | relates_to | Quản lý mối quan hệ giữa KRI_INDICATOR và CONTROL_ASSESSMENT |
| 5 | CONTROL_ASSESSMENT | 1 to Many | RESIDUAL_RISK_RECORD | relates_to | Quản lý mối quan hệ giữa CONTROL_ASSESSMENT và RESIDUAL_RISK_RECORD |
| 6 | RESIDUAL_RISK_RECORD | 1 to Many | THREAT_MAPPING_LINK | relates_to | Quản lý mối quan hệ giữa RESIDUAL_RISK_RECORD và THREAT_MAPPING_LINK |
| 7 | THREAT_MAPPING_LINK | 1 to Many | MONTE_CARLO_SIMULATION | relates_to | Quản lý mối quan hệ giữa THREAT_MAPPING_LINK và MONTE_CARLO_SIMULATION |
| 8 | MONTE_CARLO_SIMULATION | 1 to Many | RISK_HEATMAP_CONFIG | relates_to | Quản lý mối quan hệ giữa MONTE_CARLO_SIMULATION và RISK_HEATMAP_CONFIG |
| 9 | RISK_HEATMAP_CONFIG | 1 to Many | RISK_AUDIT_LOG | relates_to | Quản lý mối quan hệ giữa RISK_HEATMAP_CONFIG và RISK_AUDIT_LOG |
