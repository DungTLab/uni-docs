# Conceptual ERD — Cybersecurity Incident Management System

## Mermaid Code

```mermaid
erDiagram
    INCIDENT_CASE {
        int case_id PK
        string case_number
        string title
        string severity_rating
        datetime discovery_timestamp
        string incident_type
        string status
        string lead_investigator_name
    }

    INCIDENT_ALERT {
        int alert_id PK
        int case_id FK
        string source_siem_system
        string alert_name
        datetime alert_timestamp
        string raw_payload_json
        string mitre_technique_id
    }

    COMPROMISED_ASSET {
        int asset_id PK
        int case_id FK
        string hostname
        string ip_address
        string mac_address
        string asset_criticality
        string isolation_status
    }

    INCIDENT_PLAYBOOK {
        int playbook_id PK
        string playbook_name
        string threat_category
        string execution_mode
        string playbook_script_json
    }

    CONTAINMENT_ACTION {
        int action_id PK
        int case_id FK
        int playbook_id FK
        datetime execution_timestamp
        string action_type
        string target_entity
        string execution_result
    }

    FORENSIC_EVIDENCE {
        int evidence_id PK
        int case_id FK
        string evidence_type
        string file_name
        string sha256_hash
        datetime collection_timestamp
        string custodian_name
    }

    BREACH_NOTIFICATION {
        int notification_id PK
        int case_id FK
        string regulatory_body_name
        datetime statutory_deadline
        datetime submission_timestamp
        int affected_user_count
        string compliance_status
    }

    INCIDENT_TASK {
        int task_id PK
        int case_id FK
        string task_description
        string assigned_to_user
        date due_date
        string task_status
    }

    LESSONS_LEARNED {
        int review_id PK
        int case_id FK
        date review_date
        string root_cause_summary
        string preventative_action_items
    }

    REGULATORY_FILING {
        int filing_id PK
        int notification_id FK
        string filing_reference_number
        string legal_counsel_approval
        string document_path
    }

    INCIDENT_CASE ||--o{ INCIDENT_ALERT : "ingests"
    INCIDENT_CASE ||--o{ COMPROMISED_ASSET : "involves"
    INCIDENT_CASE ||--o{ CONTAINMENT_ACTION : "executes"
    INCIDENT_CASE ||--o{ FORENSIC_EVIDENCE : "collects"
    INCIDENT_CASE ||--o{ BREACH_NOTIFICATION : "requires"
    INCIDENT_CASE ||--o{ INCIDENT_TASK : "assigns"
    INCIDENT_CASE ||--o{ LESSONS_LEARNED : "reviewed_in"
    INCIDENT_PLAYBOOK ||--o{ CONTAINMENT_ACTION : "defines_steps"
    BREACH_NOTIFICATION ||--o{ REGULATORY_FILING : "generates"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | INCIDENT_CASE | Hồ sơ Sự cố An ninh | Master case file record tracking incident numbers, severity, discovery timestamps, status, and lead investigators. | case_id, case_number, severity_rating, status, lead_investigator_name | Primary entity; ingests alerts, involves assets, executes actions. |
| 2 | INCIDENT_ALERT | Cảnh báo An ninh SIEM | Stores raw SIEM alert payloads, alert names, timestamps, and mapped MITRE ATT&CK technique IDs. | alert_id, source_siem_system, alert_name, mitre_technique_id | Belongs to INCIDENT_CASE. |
| 3 | COMPROMISED_ASSET | Thiết bị / Tài sản bị Thâm nhập | Tracks compromised host computer names, IP addresses, asset criticality ratings, and network isolation status. | asset_id, hostname, ip_address, asset_criticality, isolation_status | Belongs to INCIDENT_CASE. |
| 4 | INCIDENT_PLAYBOOK | Kịch bản Ứng phó Tự động (SOAR) | Stores pre-configured automated SOAR response playbooks (Ransomware, Phishing, Data Exfiltration). | playbook_id, playbook_name, threat_category, execution_mode | Defines steps for CONTAINMENT_ACTION. |
| 5 | CONTAINMENT_ACTION | Hành động Khoanh vùng & Nguồn chặn | Logs executed containment actions (Host Isolation, Account Lockout, IP Block), timestamps, and API results. | action_id, execution_timestamp, action_type, target_entity, execution_result | Belongs to INCIDENT_CASE and INCIDENT_PLAYBOOK. |
| 6 | FORENSIC_EVIDENCE | Bằng chứng Pháp lý Kỹ thuật số | Archives memory dumps, disk image file names, SHA-256 cryptographic hashes, collection dates, and custodians. | evidence_id, evidence_type, sha256_hash, collection_timestamp, custodian_name | Belongs to INCIDENT_CASE. |
| 7 | BREACH_NOTIFICATION | Báo cáo Vi phạm cho Cơ quan Quản lý | Tracks statutory breach notification requirements, regulatory deadlines, affected user counts, and status. | notification_id, regulatory_body_name, statutory_deadline, compliance_status | Belongs to INCIDENT_CASE; generates REGULATORY_FILING. |
| 8 | INCIDENT_TASK | Nhiệm vụ Điều tra & Phân công | Tracks assigned investigation sub-tasks, responsible technical team members, due dates, and completion status. | task_id, task_description, assigned_to_user, due_date, task_status | Belongs to INCIDENT_CASE. |
| 9 | LESSONS_LEARNED | Báo cáo Rút kinh nghiệm Post-Incident | Stores post-incident review summaries, root cause analysis notes, and preventative security action items. | review_id, review_date, root_cause_summary, preventative_action_items | Belongs to INCIDENT_CASE. |
| 10 | REGULATORY_FILING | Hồ sơ Khai báo Pháp lý | Stores final regulatory filing documents, legal counsel approvals, and agency confirmation reference numbers. | filing_id, filing_reference_number, legal_counsel_approval, document_path | Belongs to BREACH_NOTIFICATION. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | INCIDENT_CASE | one-to-many | INCIDENT_ALERT | ingests | An incident case ingests one or more correlated SIEM security alerts. |
| 2 | INCIDENT_CASE | one-to-many | COMPROMISED_ASSET | involves | An incident case involves one or more compromised host assets. |
| 3 | INCIDENT_CASE | one-to-many | CONTAINMENT_ACTION | executes | An incident case executes multiple automated containment actions. |
| 4 | INCIDENT_CASE | one-to-many | FORENSIC_EVIDENCE | collects | An incident case collects multiple forensic evidence artifacts. |
| 5 | INCIDENT_CASE | one-to-many | BREACH_NOTIFICATION | requires | A critical incident case may require statutory breach notifications. |
| 6 | INCIDENT_CASE | one-to-many | INCIDENT_TASK | assigns | An incident case assigns response sub-tasks to analysts. |
| 7 | INCIDENT_CASE | one-to-many | LESSONS_LEARNED | reviewed_in | An incident case is reviewed in post-incident lessons learned sessions. |
| 8 | INCIDENT_PLAYBOOK | one-to-many | CONTAINMENT_ACTION | defines_steps | An automated playbook defines the steps for containment actions. |
| 9 | BREACH_NOTIFICATION | one-to-many | REGULATORY_FILING | generates | A breach notification requirement generates formal regulatory filings. |
