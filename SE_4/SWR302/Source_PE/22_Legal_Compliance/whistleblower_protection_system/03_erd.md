# Conceptual ERD — Whistleblower Protection System

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

    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT {
        int tip_id PK
        string intake_channel_type
        string misconduct_category
        string 16digit_access_token_hash
        string ip_address_status
        string tip_status
        datetime submitted_at
    }

    ZERO_KNOWLEDGE_IDENTITY_ALIAS {
        int alias_id PK
        int tip_id FK
        string cryptographic_alias_handle
        string public_key_pem
        boolean is_identity_anonymized
    }

    EU_DIRECTIVE_SLA_TRACKER {
        int sla_id PK
        int tip_id FK
        date statutory_7day_ack_due_date
        boolean acknowledged_within_7days
        date statutory_3month_feedback_due_date
        boolean feedback_provided_within_3months
    }

    FORENSIC_INVESTIGATION_CASE {
        int case_id PK
        int tip_id FK
        int lead_investigator_user_id FK
        string case_title
        string investigation_severity_level
        string case_status
        datetime assigned_at
    }

    HR_ANTI_RETALIATION_MONITORING_LOCK {
        int lock_id PK
        int protected_employee_user_id FK
        int tip_id FK
        boolean is_hr_file_locked
        string adverse_hr_action_alerts_json
        datetime locked_at
    }

    WITNESS_INTERVIEW_TRANSCRIPT {
        int interview_id PK
        int case_id FK
        string witness_alias_name
        string audio_recording_s3_uri
        text anonymized_transcript_html
        datetime interviewed_at
    }

    FORENSIC_EVIDENCE_SHA256_VAULT {
        int evidence_id PK
        int case_id FK
        string original_filename
        string sha256_hash_checksum
        string encrypted_document_s3_uri
        boolean is_chain_of_custody_intact
        datetime ingested_at
    }

    ANONYMOUS_ACCESS_TOKEN_MESSAGE {
        int message_id PK
        int tip_id FK
        string sender_type
        text encrypted_message_body
        boolean is_read
        datetime sent_at
    }

    REMEDIATION_CORRECTIVE_ACTION_TASK {
        int task_id PK
        int case_id FK
        string remediation_title
        string assigned_department
        string task_status
        date target_completion_date
    }

    SEC_FORM_TCR_REGULATORY_FILING {
        int tcr_id PK
        int case_id FK
        int cro_user_id FK
        string sec_tcr_accession_number
        string tcr_form_document_s3_uri
        datetime filed_at
    }

    EXECUTIVE_WHISTLEBLOWER_SCORECARD {
        int scorecard_id PK
        int total_tips_received_ytd
        decimal eu_7day_sla_compliance_percent
        int total_hr_retaliation_alerts_blocked
        decimal average_investigation_cycle_days
        date period_quarter
    }

    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT ||--o| ZERO_KNOWLEDGE_IDENTITY_ALIAS : "anonymized via"
    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT ||--o| EU_DIRECTIVE_SLA_TRACKER : "tracked in"
    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT ||--o| FORENSIC_INVESTIGATION_CASE : "investigated in"
    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT ||--o| HR_ANTI_RETALIATION_MONITORING_LOCK : "protects via"
    ANONYMOUS_WHISTLEBLOWER_TIP_REPORT ||--o{ ANONYMOUS_ACCESS_TOKEN_MESSAGE : "communicates in"
    FORENSIC_INVESTIGATION_CASE ||--o{ WITNESS_INTERVIEW_TRANSCRIPT : "includes"
    FORENSIC_INVESTIGATION_CASE ||--o{ FORENSIC_EVIDENCE_SHA256_VAULT : "vaults"
    FORENSIC_INVESTIGATION_CASE ||--o{ REMEDIATION_CORRECTIVE_ACTION_TASK : "triggers"
    FORENSIC_INVESTIGATION_CASE ||--o| SEC_FORM_TCR_REGULATORY_FILING : "discloses via"
    USER_ACCOUNT ||--o{ FORENSIC_INVESTIGATION_CASE : "leads"
    USER_ACCOUNT ||--o{ HR_ANTI_RETALIATION_MONITORING_LOCK : "protects"
    USER_ACCOUNT ||--o{ SEC_FORM_TCR_REGULATORY_FILING : "files"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | USER_ACCOUNT | Tài khoản Nguời dùng | System credentials and access roles for chief ethics officers, forensic investigators, CROs, and admins. | `user_id`, `username`, `user_role`, `department_code` | Leads `FORENSIC_INVESTIGATION_CASE`, protects `HR_ANTI_RETALIATION_MONITORING_LOCK`, files `SEC_FORM_TCR_REGULATORY_FILING`. |
| 2 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | Báo cáo Tip Whistleblower Nặc danh | Anonymous whistleblower tip report record tracking intake channels (Tor/Hotline), misconduct categories, 16-digit access token hashes, IP stripping status, and tip status. | `tip_id`, `intake_channel_type`, `misconduct_category`, `16digit_access_token_hash`, `ip_address_status`, `tip_status`, `submitted_at` | Anonymized via `ZERO_KNOWLEDGE_IDENTITY_ALIAS`, tracked in `EU_DIRECTIVE_SLA_TRACKER`, investigated in `FORENSIC_INVESTIGATION_CASE`, protects via `HR_ANTI_RETALIATION_MONITORING_LOCK`, communicates in `ANONYMOUS_ACCESS_TOKEN_MESSAGE`. |
| 3 | ZERO_KNOWLEDGE_IDENTITY_ALIAS | Alias Identity Zero-Knowledge | Zero-knowledge identity pseudonym record tracking cryptographic alias handles (`Informant #Alpha-884`), public keys PEM, and anonymization flags. | `alias_id`, `tip_id`, `cryptographic_alias_handle`, `public_key_pem`, `is_identity_anonymized` | Belongs to `ANONYMOUS_WHISTLEBLOWER_TIP_REPORT`. |
| 4 | EU_DIRECTIVE_SLA_TRACKER | Tracker SLA Chỉ thị Whistleblower EU | EU Whistleblower Directive 2019/1937 SLA log tracking 7-day acknowledgment due dates, 7-day compliance flags, 3-month feedback due dates, and compliance flags. | `sla_id`, `tip_id`, `statutory_7day_ack_due_date`, `acknowledged_within_7days`, `statutory_3month_feedback_due_date`, `feedback_provided_within_3months` | Belongs to `ANONYMOUS_WHISTLEBLOWER_TIP_REPORT`. |
| 5 | FORENSIC_INVESTIGATION_CASE | Case Điều tra Forensic | Whistleblower forensic investigation case record tracking case titles, lead investigator user IDs, severity levels (Level 1/2/3), and status (`ACTIVE/CLOSED`). | `case_id`, `tip_id`, `lead_investigator_user_id`, `case_title`, `investigation_severity_level`, `case_status` | Belongs to `ANONYMOUS_WHISTLEBLOWER_TIP_REPORT`, includes `WITNESS_INTERVIEW_TRANSCRIPT`, vaults `FORENSIC_EVIDENCE_SHA256_VAULT`, triggers `REMEDIATION_CORRECTIVE_ACTION_TASK`, discloses via `SEC_FORM_TCR_REGULATORY_FILING`, led by `USER_ACCOUNT`. |
| 6 | HR_ANTI_RETALIATION_MONITORING_LOCK | Lock Chống Trả thù HR | HR anti-retaliation protection lock record tracking protected employee user IDs, file lock status, adverse HR action alert logs JSON, and lock timestamps. | `lock_id`, `protected_employee_user_id`, `tip_id`, `is_hr_file_locked`, `adverse_hr_action_alerts_json`, `locked_at` | Belongs to `ANONYMOUS_WHISTLEBLOWER_TIP_REPORT`, protects `USER_ACCOUNT`. |
| 7 | WITNESS_INTERVIEW_TRANSCRIPT | Transcript Phỏng vấn Nhân chứng | Confidential witness interview transcript record tracking witness alias names, audio recording S3 URIs, anonymized transcript HTML, and interview timestamps. | `interview_id`, `case_id`, `witness_alias_name`, `audio_recording_s3_uri`, `anonymized_transcript_html`, `interviewed_at` | Belongs to `FORENSIC_INVESTIGATION_CASE`. |
| 8 | FORENSIC_EVIDENCE_SHA256_VAULT | Vault Bằng chứng SHA-256 | AES-256 encrypted forensic evidence vault record tracking original filenames, SHA-256 checksum hashes, encrypted document S3 URIs, and chain-of-custody validity. | `evidence_id`, `case_id`, `original_filename`, `sha256_hash_checksum`, `encrypted_document_s3_uri`, `is_chain_of_custody_intact`, `ingested_at` | Belongs to `FORENSIC_INVESTIGATION_CASE`. |
| 9 | ANONYMOUS_ACCESS_TOKEN_MESSAGE | Tin nhắn Access Token Nặc danh | Anonymous two-way message record tracking tip IDs, sender types (`INFORMANT/INVESTIGATOR`), encrypted message bodies, read flags, and timestamps. | `message_id`, `tip_id`, `sender_type`, `encrypted_message_body`, `is_read`, `sent_at` | Belongs to `ANONYMOUS_WHISTLEBLOWER_TIP_REPORT`. |
| 10 | REMEDIATION_CORRECTIVE_ACTION_TASK | Task Sửa đổi Khắc phục | Corporate remediation and corrective action task record tracking task titles, assigned departments, status (`PENDING/COMPLETED`), and target completion dates. | `task_id`, `case_id`, `remediation_title`, `assigned_department`, `task_status`, `target_completion_date` | Belongs to `FORENSIC_INVESTIGATION_CASE`. |
| 11 | SEC_FORM_TCR_REGULATORY_FILING | Filing Quy định SEC Form TCR | Statutory SEC Form TCR (Tip, Complaint or Referral) filing log recording accession numbers, TCR form document S3 URIs, CRO user IDs, and filing timestamps. | `tcr_id`, `case_id`, `cro_user_id`, `sec_tcr_accession_number`, `tcr_form_document_s3_uri`, `filed_at` | Belongs to `FORENSIC_INVESTIGATION_CASE`, filed by `USER_ACCOUNT`. |
| 12 | EXECUTIVE_WHISTLEBLOWER_SCORECARD | Scorecard Executive Ethics | Executive whistleblower governance scorecard aggregating total tips YTD, EU 7-day SLA compliance %, HR retaliation alerts blocked, and investigation cycle days. | `scorecard_id`, `total_tips_received_ytd`, `eu_7day_sla_compliance_percent`, `total_hr_retaliation_alerts_blocked`, `average_investigation_cycle_days` | Standalone executive performance entity. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Label | Business Explanation |
|---|-------------|-------------|-----------|-------|----------------------|
| 1 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | 1 to 1 | ZERO_KNOWLEDGE_IDENTITY_ALIAS | anonymized via | An anonymous tip report is anonymized via a zero-knowledge identity alias. |
| 2 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | 1 to 1 | EU_DIRECTIVE_SLA_TRACKER | tracked in | An anonymous tip report is tracked in an EU Directive SLA log. |
| 3 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | 1 to 0..1 | FORENSIC_INVESTIGATION_CASE | investigated in | An anonymous tip report is investigated in a forensic investigation case. |
| 4 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | 1 to 0..1 | HR_ANTI_RETALIATION_MONITORING_LOCK | protects via | An anonymous tip report protects the informant via an HR anti-retaliation lock. |
| 5 | ANONYMOUS_WHISTLEBLOWER_TIP_REPORT | 1 to 0..N | ANONYMOUS_ACCESS_TOKEN_MESSAGE | communicates in | An anonymous tip report communicates in anonymous access token messages. |
| 6 | FORENSIC_INVESTIGATION_CASE | 1 to 0..N | WITNESS_INTERVIEW_TRANSCRIPT | includes | A forensic investigation case includes witness interview transcripts. |
| 7 | FORENSIC_INVESTIGATION_CASE | 1 to 0..N | FORENSIC_EVIDENCE_SHA256_VAULT | vaults | A forensic investigation case vaults evidence with SHA-256 hashes. |
| 8 | FORENSIC_INVESTIGATION_CASE | 1 to 0..N | REMEDIATION_CORRECTIVE_ACTION_TASK | triggers | A forensic investigation case triggers remediation corrective action tasks. |
| 9 | FORENSIC_INVESTIGATION_CASE | 1 to 0..1 | SEC_FORM_TCR_REGULATORY_FILING | discloses via | A forensic investigation case discloses via SEC Form TCR regulatory filings. |
| 10 | USER_ACCOUNT | 1 to 0..N | FORENSIC_INVESTIGATION_CASE | leads | A forensic investigator user account leads investigation cases. |
| 11 | USER_ACCOUNT | 1 to 0..N | HR_ANTI_RETALIATION_MONITORING_LOCK | protects | A whistleblower employee user account is protected in HR monitoring locks. |
| 12 | USER_ACCOUNT | 1 to 0..N | SEC_FORM_TCR_REGULATORY_FILING | files | A CRO user account files SEC Form TCR regulatory filings. |
