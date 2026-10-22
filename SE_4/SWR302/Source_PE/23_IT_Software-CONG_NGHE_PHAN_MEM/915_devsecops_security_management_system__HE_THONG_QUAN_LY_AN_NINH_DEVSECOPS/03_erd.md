# Conceptual ERD — DevSecOps Security Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    SECURITY_POLICY_GATE["SECURITY_POLICY_GATE"]
    SAST_SCAN_RESULT["SAST_SCAN_RESULT"]
    SCA_DEPENDENCY_ITEM["SCA_DEPENDENCY_ITEM"]
    SECRET_LEAK_ALERT["SECRET_LEAK_ALERT"]
    SBOM_MANIFEST["SBOM_MANIFEST"]
    DAST_SCAN_TARGET["DAST_SCAN_TARGET"]
    VULNERABILITY_ITEM["VULNERABILITY_ITEM"]
    EXEMPTION_WHITELIST["EXEMPTION_WHITELIST"]
    REMEDIATION_SLA["REMEDIATION_SLA"]
    DEVSECOPS_AUDIT_LOG["DEVSECOPS_AUDIT_LOG"]

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
    SECURITY_POLICY_GATE ---|1| rel_contains ---|N| SAST_SCAN_RESULT
    SECURITY_POLICY_GATE ---|1| rel_manages ---|N| SCA_DEPENDENCY_ITEM
    SAST_SCAN_RESULT ---|1| rel_records ---|N| SECRET_LEAK_ALERT
    SCA_DEPENDENCY_ITEM ---|1| rel_links_to ---|N| SBOM_MANIFEST
    SECRET_LEAK_ALERT ---|1| rel_tracks ---|N| DAST_SCAN_TARGET
    SBOM_MANIFEST ---|1| rel_associates ---|N| VULNERABILITY_ITEM
    DAST_SCAN_TARGET ---|1| rel_evaluates ---|N| EXEMPTION_WHITELIST
    VULNERABILITY_ITEM ---|1| rel_generates ---|N| REMEDIATION_SLA
    SECURITY_POLICY_GATE ---|1| rel_audits ---|N| DEVSECOPS_AUDIT_LOG

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | SECURITY_POLICY_GATE | Thực thể SECURITY_POLICY_GATE | Quản lý thông tin chi tiết cho security_policy_gate | id (PK), name, status, created_at | Links with related entities |
| 2 | SAST_SCAN_RESULT | Thực thể SAST_SCAN_RESULT | Quản lý thông tin chi tiết cho sast_scan_result | id (PK), name, status, created_at | Links with related entities |
| 3 | SCA_DEPENDENCY_ITEM | Thực thể SCA_DEPENDENCY_ITEM | Quản lý thông tin chi tiết cho sca_dependency_item | id (PK), name, status, created_at | Links with related entities |
| 4 | SECRET_LEAK_ALERT | Thực thể SECRET_LEAK_ALERT | Quản lý thông tin chi tiết cho secret_leak_alert | id (PK), name, status, created_at | Links with related entities |
| 5 | SBOM_MANIFEST | Thực thể SBOM_MANIFEST | Quản lý thông tin chi tiết cho sbom_manifest | id (PK), name, status, created_at | Links with related entities |
| 6 | DAST_SCAN_TARGET | Thực thể DAST_SCAN_TARGET | Quản lý thông tin chi tiết cho dast_scan_target | id (PK), name, status, created_at | Links with related entities |
| 7 | VULNERABILITY_ITEM | Thực thể VULNERABILITY_ITEM | Quản lý thông tin chi tiết cho vulnerability_item | id (PK), name, status, created_at | Links with related entities |
| 8 | EXEMPTION_WHITELIST | Thực thể EXEMPTION_WHITELIST | Quản lý thông tin chi tiết cho exemption_whitelist | id (PK), name, status, created_at | Links with related entities |
| 9 | REMEDIATION_SLA | Thực thể REMEDIATION_SLA | Quản lý thông tin chi tiết cho remediation_sla | id (PK), name, status, created_at | Links with related entities |
| 10 | DEVSECOPS_AUDIT_LOG | Thực thể DEVSECOPS_AUDIT_LOG | Quản lý thông tin chi tiết cho devsecops_audit_log | id (PK), name, status, created_at | Links with related entities |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | SECURITY_POLICY_GATE | 1 to Many | SAST_SCAN_RESULT | relates_to | Quản lý mối quan hệ giữa SECURITY_POLICY_GATE và SAST_SCAN_RESULT |
| 2 | SAST_SCAN_RESULT | 1 to Many | SCA_DEPENDENCY_ITEM | relates_to | Quản lý mối quan hệ giữa SAST_SCAN_RESULT và SCA_DEPENDENCY_ITEM |
| 3 | SCA_DEPENDENCY_ITEM | 1 to Many | SECRET_LEAK_ALERT | relates_to | Quản lý mối quan hệ giữa SCA_DEPENDENCY_ITEM và SECRET_LEAK_ALERT |
| 4 | SECRET_LEAK_ALERT | 1 to Many | SBOM_MANIFEST | relates_to | Quản lý mối quan hệ giữa SECRET_LEAK_ALERT và SBOM_MANIFEST |
| 5 | SBOM_MANIFEST | 1 to Many | DAST_SCAN_TARGET | relates_to | Quản lý mối quan hệ giữa SBOM_MANIFEST và DAST_SCAN_TARGET |
| 6 | DAST_SCAN_TARGET | 1 to Many | VULNERABILITY_ITEM | relates_to | Quản lý mối quan hệ giữa DAST_SCAN_TARGET và VULNERABILITY_ITEM |
| 7 | VULNERABILITY_ITEM | 1 to Many | EXEMPTION_WHITELIST | relates_to | Quản lý mối quan hệ giữa VULNERABILITY_ITEM và EXEMPTION_WHITELIST |
| 8 | EXEMPTION_WHITELIST | 1 to Many | REMEDIATION_SLA | relates_to | Quản lý mối quan hệ giữa EXEMPTION_WHITELIST và REMEDIATION_SLA |
| 9 | REMEDIATION_SLA | 1 to Many | DEVSECOPS_AUDIT_LOG | relates_to | Quản lý mối quan hệ giữa REMEDIATION_SLA và DEVSECOPS_AUDIT_LOG |
