# Conceptual ERD — Compliance Management System

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

    COMPLIANCE_FRAMEWORK_MATRIX {
        int framework_id PK
        string framework_name
        string regulatory_body_authority
        string compliance_version_slug
        boolean is_active
    }

    CORPORATE_POLICY_DOCUMENT {
        int policy_id PK
        int framework_id FK
        int author_user_id FK
        string policy_number_slug
        string policy_title
        string current_version_number
        date mandatory_attestation_due_date
        boolean requires_annual_signoff
    }

    EMPLOYEE_POLICY_ATTESTATION {
        int attestation_id PK
        int policy_id FK
        int employee_user_id FK
        boolean is_comprehension_quiz_passed
        string digital_signature_hash
        datetime attested_at
    }

    REGULATORY_CHANGE_FEED_LOG {
        int reg_change_id PK
        string regulation_name
        string issuing_jurisdiction
        text rule_change_summary
        date mandatory_effective_date
        string mapped_policy_id_fk
        datetime ingested_at
    }

    INTERNAL_CONTROL_DEFINITION {
        int control_id PK
        int framework_id FK
        string control_code_number
        string control_objective_title
        string test_frequency_schedule
        string control_owner_department
    }

    CONTROL_TESTING_AUDIT_LOG {
        int test_id PK
        int control_id FK
        int auditor_user_id FK
        int population_sample_size
        int failed_sample_items_count
        string overall_test_result
        datetime tested_at
    }

    CONTROL_DEFICIENCY_RECORD {
        int deficiency_id PK
        int test_id FK
        string deficiency_severity_level
        text deficiency_description
        string remediation_status
    }

    WHISTLEBLOWER_INCIDENT_REPORT {
        int incident_id PK
        int investigator_user_id FK
        string encrypted_ticket_uuid
        string allegation_category_type
        string incident_severity_level
        string investigation_status
        datetime reported_at
    }

    CORRECTIVE_ACTION_CAPA_PLAN {
        int capa_id PK
        int deficiency_id FK
        int process_owner_user_id FK
        string remediation_plan_objective
        date remediation_deadline_date
        boolean is_retest_verified
        datetime closed_at
    }

    GIFT_HOSPITALITY_DISCLOSURE {
        int gift_id PK
        int employee_user_id FK
        string gift_description
        decimal estimated_monetary_value
        string donor_vendor_name
        string approval_status
        datetime declared_at
    }

    EXECUTIVE_COMPLIANCE_SCORECARD {
        int scorecard_id PK
        decimal overall_compliance_readiness_percent
        decimal policy_attestation_rate_percent
        int open_control_deficiencies_count
        int open_whistleblower_incidents_count
        date period_quarter
    }

    COMPLIANCE_FRAMEWORK_MATRIX ||--o{ CORPORATE_POLICY_DOCUMENT : "governs"
    COMPLIANCE_FRAMEWORK_MATRIX ||--o{ INTERNAL_CONTROL_DEFINITION : "defines"
    CORPORATE_POLICY_DOCUMENT ||--o{ EMPLOYEE_POLICY_ATTESTATION : "attested in"
    CORPORATE_POLICY_DOCUMENT ||--o{ REGULATORY_CHANGE_FEED_LOG : "updated by"
    INTERNAL_CONTROL_DEFINITION ||--o{ CONTROL_TESTING_AUDIT_LOG : "audited in"
    CONTROL_TESTING_AUDIT_LOG ||--o| CONTROL_DEFICIENCY_RECORD : "identifies"
    CONTROL_DEFICIENCY_RECORD ||--o| CORRECTIVE_ACTION_CAPA_PLAN : "remediated via"
    USER_ACCOUNT ||--o{ CORPORATE_POLICY_DOCUMENT : "authors"
    USER_ACCOUNT ||--o{ EMPLOYEE_POLICY_ATTESTATION : "attests to"
    USER_ACCOUNT ||--o{ CONTROL_TESTING_AUDIT_LOG : "audits"
    USER_ACCOUNT ||--o{ WHISTLEBLOWER_INCIDENT_REPORT : "investigates"
    USER_ACCOUNT ||--o{ CORRECTIVE_ACTION_CAPA_PLAN : "owns remediation in"
    USER_ACCOUNT ||--o{ GIFT_HOSPITALITY_DISCLOSURE : "declares"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | USER_ACCOUNT | Tài khoản Nguời dùng | System credentials and access roles for compliance officers, auditors, employees, and admins. | `user_id`, `username`, `user_role`, `department_code` | Authors `CORPORATE_POLICY_DOCUMENT`, attests in `EMPLOYEE_POLICY_ATTESTATION`, audits `CONTROL_TESTING_AUDIT_LOG`, investigates `WHISTLEBLOWER_INCIDENT_REPORT`, owns remediation in `CORRECTIVE_ACTION_CAPA_PLAN`. |
| 2 | COMPLIANCE_FRAMEWORK_MATRIX | Ma trận Framework Tuân thủ | Central compliance framework definition entity storing regulatory framework names (ISO 27001, SOX, GDPR) and active status. | `framework_id`, `framework_name`, `regulatory_body_authority`, `compliance_version_slug`, `is_active` | Governs `CORPORATE_POLICY_DOCUMENT`, defines `INTERNAL_CONTROL_DEFINITION`. |
| 3 | CORPORATE_POLICY_DOCUMENT | Văn bản Chính sách Doanh nghiệp | Corporate policy document record tracking policy numbers, titles, current versions, attestation due dates, and sign-off flags. | `policy_id`, `framework_id`, `author_user_id`, `policy_number_slug`, `policy_title`, `current_version_number`, `requires_annual_signoff` | Governed by `COMPLIANCE_FRAMEWORK_MATRIX`, attested in `EMPLOYEE_POLICY_ATTESTATION`, updated by `REGULATORY_CHANGE_FEED_LOG`, authored by `USER_ACCOUNT`. |
| 4 | EMPLOYEE_POLICY_ATTESTATION | Xác nhận Attestation Chính sách | Employee policy sign-off attestation record tracking quiz completion, cryptographic signature hashes, and attestation timestamps. | `attestation_id`, `policy_id`, `employee_user_id`, `is_comprehension_quiz_passed`, `digital_signature_hash`, `attested_at` | Belongs to `CORPORATE_POLICY_DOCUMENT`, attested by `USER_ACCOUNT`. |
| 5 | REGULATORY_CHANGE_FEED_LOG | Nhật ký Regulatory Change Feed | Statutory regulatory update log recording regulation names, jurisdictions, rule change summaries, and effective dates. | `reg_change_id`, `regulation_name`, `issuing_jurisdiction`, `rule_change_summary`, `mandatory_effective_date` | Updates `CORPORATE_POLICY_DOCUMENT`. |
| 6 | INTERNAL_CONTROL_DEFINITION | Định nghĩa Kiểm soát Nội bộ | Internal control definition record tracking control code numbers, control objectives, test schedules, and owner departments. | `control_id`, `framework_id`, `control_code_number`, `control_objective_title`, `test_frequency_schedule`, `control_owner_department` | Belongs to `COMPLIANCE_FRAMEWORK_MATRIX`, audited in `CONTROL_TESTING_AUDIT_LOG`. |
| 7 | CONTROL_TESTING_AUDIT_LOG | Nhật ký Audit Kiểm thử Control | Internal control effectiveness testing audit log recording sample sizes, failed sample items, and overall test results (`PASSED/FAILED`). | `test_id`, `control_id`, `auditor_user_id`, `population_sample_size`, `failed_sample_items_count`, `overall_test_result` | Belongs to `INTERNAL_CONTROL_DEFINITION`, identifies `CONTROL_DEFICIENCY_RECORD`, audited by `USER_ACCOUNT`. |
| 8 | CONTROL_DEFICIENCY_RECORD | Bản ghi Control Deficiency | Control deficiency record logging deficiency severity levels (Material Weakness, Significant Deficiency) and remediation status. | `deficiency_id`, `test_id`, `deficiency_severity_level`, `deficiency_description`, `remediation_status` | Belongs to `CONTROL_TESTING_AUDIT_LOG`, remediated via `CORRECTIVE_ACTION_CAPA_PLAN`. |
| 9 | WHISTLEBLOWER_INCIDENT_REPORT | Báo cáo Whistleblower Incidents | Confidential ethics incident report tracking encrypted ticket UUIDs, allegation types, severity levels, and investigation status. | `incident_id`, `investigator_user_id`, `encrypted_ticket_uuid`, `allegation_category_type`, `incident_severity_level`, `investigation_status` | Investigated by `USER_ACCOUNT`. |
| 10 | CORRECTIVE_ACTION_CAPA_PLAN | Kế hoạch Remediation CAPA | Corrective and Preventive Action (CAPA) remediation plan record tracking objectives, process owners, deadlines, and re-test verifications. | `capa_id`, `deficiency_id`, `process_owner_user_id`, `remediation_plan_objective`, `remediation_deadline_date`, `is_retest_verified` | Remediates `CONTROL_DEFICIENCY_RECORD`, owned by `USER_ACCOUNT`. |
| 11 | GIFT_HOSPITALITY_DISCLOSURE | Kê khai Quà tặng & Giải trí | Employee gift, hospitality, and entertainment disclosure log tracking gift descriptions, monetary values, donor names, and approval status. | `gift_id`, `employee_user_id`, `gift_description`, `estimated_monetary_value`, `donor_vendor_name`, `approval_status` | Declared by `USER_ACCOUNT`. |
| 12 | EXECUTIVE_COMPLIANCE_SCORECARD | Scorecard Executive Tuân thủ | Executive compliance scorecard aggregating compliance readiness %, attestation rates %, open control deficiencies, and active incidents. | `scorecard_id`, `overall_compliance_readiness_percent`, `policy_attestation_rate_percent`, `open_control_deficiencies_count`, `open_whistleblower_incidents_count` | Standalone executive performance entity. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Label | Business Explanation |
|---|-------------|-------------|-----------|-------|----------------------|
| 1 | COMPLIANCE_FRAMEWORK_MATRIX | 1 to 0..N | CORPORATE_POLICY_DOCUMENT | governs | A compliance framework matrix governs corporate policy documents. |
| 2 | COMPLIANCE_FRAMEWORK_MATRIX | 1 to 1..N | INTERNAL_CONTROL_DEFINITION | defines | A compliance framework matrix defines internal control definitions. |
| 3 | CORPORATE_POLICY_DOCUMENT | 1 to 0..N | EMPLOYEE_POLICY_ATTESTATION | attested in | A corporate policy document is attested in employee policy attestations. |
| 4 | CORPORATE_POLICY_DOCUMENT | 1 to 0..N | REGULATORY_CHANGE_FEED_LOG | updated by | A corporate policy document is updated by regulatory change feeds. |
| 5 | INTERNAL_CONTROL_DEFINITION | 1 to 0..N | CONTROL_TESTING_AUDIT_LOG | audited in | An internal control definition is audited in control testing logs. |
| 6 | CONTROL_TESTING_AUDIT_LOG | 1 to 0..1 | CONTROL_DEFICIENCY_RECORD | identifies | A control testing audit log identifies control deficiency records. |
| 7 | CONTROL_DEFICIENCY_RECORD | 1 to 0..1 | CORRECTIVE_ACTION_CAPA_PLAN | remediated via | A control deficiency record is remediated via a CAPA plan. |
| 8 | USER_ACCOUNT | 1 to 0..N | CORPORATE_POLICY_DOCUMENT | authors | A compliance officer user account authors corporate policy documents. |
| 9 | USER_ACCOUNT | 1 to 0..N | EMPLOYEE_POLICY_ATTESTATION | attests to | A corporate employee user account attests to policy sign-offs. |
| 10 | USER_ACCOUNT | 1 to 0..N | CONTROL_TESTING_AUDIT_LOG | audits | A compliance auditor user account audits internal controls. |
| 11 | USER_ACCOUNT | 1 to 0..N | WHISTLEBLOWER_INCIDENT_REPORT | investigates | An ethics investigator user account investigates whistleblower reports. |
| 12 | USER_ACCOUNT | 1 to 0..N | CORRECTIVE_ACTION_CAPA_PLAN | owns remediation in | A process owner user account owns remediation in CAPA plans. |
| 13 | USER_ACCOUNT | 1 to 0..N | GIFT_HOSPITALITY_DISCLOSURE | declares | A corporate employee user account declares gift disclosures. |
