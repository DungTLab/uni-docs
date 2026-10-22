# Conceptual ERD — Employment Law Compliance System

## Mermaid Code

```mermaid
erDiagram
    GRIEVANCE_COMPLAINT {
        int complaint_id PK
        string incident_number
        date submission_date
        string category
        string submission_mode
        string status
        boolean is_anonymous
    }

    WORKPLACE_INVESTIGATION {
        int investigation_id PK
        int complaint_id FK
        string lead_investigator_name
        date start_date
        date target_completion_date
        string privilege_classification
        string summary_finding
    }

    LABOR_COMPLIANCE_AUDIT {
        int audit_id PK
        string jurisdiction_code
        string facility_location
        date audit_date
        decimal risk_score
        string compliance_status
    }

    OVERTIME_WAGE_RECORD {
        int wage_record_id PK
        string employee_hris_id
        date work_date
        decimal hours_worked
        decimal overtime_hours
        decimal meal_break_penalty_pay
        string audit_flag
    }

    EEO_METRIC_RECORD {
        int eeo_id PK
        string reporting_year
        string job_category
        string gender_diversity_ratio
        string ethnic_diversity_ratio
        string filing_status
    }

    TERMINATION_REVIEW {
        int termination_id PK
        string employee_hris_id
        date termination_date
        string termination_type
        boolean requires_owbpa_disclosure
        decimal severance_amount
        string status
    }

    CORRECTIVE_ACTION_PLAN {
        int cap_id PK
        int investigation_id FK
        string action_description
        string responsible_party
        date target_completion_date
        string verification_status
    }

    UNION_CBA_TERM {
        int cba_id PK
        string union_name
        string agreement_title
        date effective_date
        date expiration_date
        string grievance_procedure_clause
    }

    OSHA_INCIDENT_REPORT {
        int osha_id PK
        string incident_case_number
        date incident_date
        string injury_type
        int lost_workdays_count
        boolean is_recordable
    }

    ACCOMMODATION_REQUEST {
        int accommodation_id PK
        string employee_hris_id
        date request_date
        string disability_type
        string requested_adaptation
        string decision_status
    }

    GRIEVANCE_COMPLAINT ||--o{ WORKPLACE_INVESTIGATION : "triggers"
    WORKPLACE_INVESTIGATION ||--o{ CORRECTIVE_ACTION_PLAN : "results_in"
    LABOR_COMPLIANCE_AUDIT ||--o{ OVERTIME_WAGE_RECORD : "audits"
    LABOR_COMPLIANCE_AUDIT ||--o{ EEO_METRIC_RECORD : "verifies"
    WORKPLACE_INVESTIGATION ||--o{ TERMINATION_REVIEW : "recommends"
    UNION_CBA_TERM ||--o{ GRIEVANCE_COMPLAINT : "governs"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | GRIEVANCE_COMPLAINT | Khiếu nại & Tố giác Lao động | Stores employee grievance logs, harassment reports, discrimination claims, and anonymous hotline submissions. | complaint_id, incident_number, category, status, is_anonymous | Triggers WORKPLACE_INVESTIGATION; governed by UNION_CBA_TERM. |
| 2 | WORKPLACE_INVESTIGATION | Điều tra Nội bộ Môi trường Làm việc | Tracks formal internal workplace investigation details, lead investigators, findings, and privilege legal tags. | investigation_id, lead_investigator_name, target_completion_date, summary_finding | Belongs to GRIEVANCE_COMPLAINT; results in CORRECTIVE_ACTION_PLAN. |
| 3 | LABOR_COMPLIANCE_AUDIT | Kiểm toán Tuân thủ Luật Lao động | Stores facility inspection records, state poster compliance checks, and calculated labor risk scores. | audit_id, jurisdiction_code, facility_location, risk_score | Audits OVERTIME_WAGE_RECORD and EEO_METRIC_RECORD. |
| 4 | OVERTIME_WAGE_RECORD | Hồ sơ Lương Overtime & Nghỉ giải lao | Tracks daily non-exempt employee meal/rest break logs, overtime calculations, and break penalty pay flags. | wage_record_id, employee_hris_id, work_date, meal_break_penalty_pay | Audited by LABOR_COMPLIANCE_AUDIT. |
| 5 | EEO_METRIC_RECORD | Báo cáo Bình đẳng Cơ hội (EEO) | Captures demographic workforce data, job categories, and statutory EEO-1 filing statistics. | eeo_id, reporting_year, job_category, filing_status | Verified by LABOR_COMPLIANCE_AUDIT. |
| 6 | TERMINATION_REVIEW | Thẩm định Sa thải & Trợ cấp | Tracks involuntary employee termination approvals, severance release waivers, and ADEA/OWBPA disclosures. | termination_id, employee_hris_id, termination_type, severance_amount | Recommended by WORKPLACE_INVESTIGATION. |
| 7 | CORRECTIVE_ACTION_PLAN | Kế hoạch Khắc phục Vi phạm (CAP) | Defines mandatory disciplinary warnings, remedial training steps, and post-investigation corrective actions. | cap_id, action_description, target_completion_date, verification_status | Belongs to WORKPLACE_INVESTIGATION. |
| 8 | UNION_CBA_TERM | Thỏa ước Lao động Tập thể (CBA) | Stores labor union agreement clauses, wage scales, seniority rules, and grievance arbitration procedures. | cba_id, union_name, agreement_title, expiration_date | Governs GRIEVANCE_COMPLAINT. |
| 9 | OSHA_INCIDENT_REPORT | Báo cáo An toàn Lao động (OSHA) | Logs workplace injuries, illness incidents, lost workday counts, and OSHA 300 digital recordable events. | osha_id, incident_case_number, injury_type, is_recordable | Independent safety entity. |
| 10 | ACCOMMODATION_REQUEST | Yêu cầu Hỗ trợ Người Khuyết tật | Manages ADA accommodation requests, interactive dialogue records, and medical certification decisions. | accommodation_id, employee_hris_id, requested_adaptation, decision_status | Independent ADA accommodation entity. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | GRIEVANCE_COMPLAINT | one-to-many | WORKPLACE_INVESTIGATION | triggers | A logged grievance complaint triggers an internal workplace investigation. |
| 2 | WORKPLACE_INVESTIGATION | one-to-many | CORRECTIVE_ACTION_PLAN | results_in | An investigation finding results in one or more corrective action plans. |
| 3 | LABOR_COMPLIANCE_AUDIT | one-to-many | OVERTIME_WAGE_RECORD | audits | A labor compliance audit inspects daily overtime and wage records. |
| 4 | LABOR_COMPLIANCE_AUDIT | one-to-many | EEO_METRIC_RECORD | verifies | A labor compliance audit verifies statutory EEO workforce reports. |
| 5 | WORKPLACE_INVESTIGATION | one-to-many | TERMINATION_REVIEW | recommends | An investigation may recommend an involuntary employee termination review. |
| 6 | UNION_CBA_TERM | one-to-many | GRIEVANCE_COMPLAINT | governs | A union collective bargaining agreement governs applicable employee grievances. |
