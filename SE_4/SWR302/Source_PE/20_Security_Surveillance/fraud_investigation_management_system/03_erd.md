# Conceptual ERD — Fraud Investigation Management System

## Mermaid Code

```mermaid
erDiagram
    FRAUD_CASE {
        int case_id PK
        string case_number
        string case_title
        string fraud_category
        datetime opened_timestamp
        datetime closed_timestamp
        string lead_investigator
        string case_status
    }

    FRAUD_ALERT {
        int alert_id PK
        int case_id FK
        string source_system
        decimal transaction_amount
        int anomaly_score
        datetime alert_timestamp
        string alert_status
    }

    SUSPECT_ENTITY {
        int suspect_id PK
        string full_name_or_company
        string tax_id_ssn_hash
        string account_number
        string ip_address
        string risk_level
    }

    FRAUDULENT_TRANSACTION {
        int transaction_id PK
        int case_id FK
        string transaction_reference_code
        decimal amount
        datetime transaction_timestamp
        string sender_account
        string beneficiary_account
    }

    LINK_ANALYSIS_NODE {
        int node_id PK
        int case_id FK
        string entity_type
        string entity_identifier
        int degree_of_separation
        string node_label
    }

    FRAUD_EVIDENCE {
        int evidence_id PK
        int case_id FK
        string evidence_type
        string file_name
        string sha256_hash
        datetime custody_logged_timestamp
        string evidence_vault_path
    }

    SAR_REGULATORY_FILING {
        int filing_id PK
        int case_id FK
        string bsa_tracking_number
        decimal reported_amount
        datetime filing_timestamp
        string counsel_digital_signature
        string filing_status
    }

    INTERVIEW_RECORD {
        int interview_id PK
        int case_id FK
        string interviewee_name
        string interviewee_role
        datetime interview_timestamp
        string transcript_summary
    }

    ACCOUNT_FREEZE_ORDER {
        int freeze_id PK
        int case_id FK
        string account_number
        decimal frozen_balance
        datetime freeze_executed_timestamp
        string core_banking_receipt_code
    }

    FINANCIAL_RECOVERY_LOG {
        int recovery_id PK
        int case_id FK
        decimal gross_fraud_loss
        decimal recovered_amount
        decimal net_unrecoverable_loss
        date recovery_date
    }

    FRAUD_CASE ||--o{ FRAUD_ALERT : "originates_from"
    FRAUD_CASE ||--o{ FRAUDULENT_TRANSACTION : "contains_transaction"
    FRAUD_CASE ||--o{ LINK_ANALYSIS_NODE : "mapped_in"
    FRAUD_CASE ||--o{ FRAUD_EVIDENCE : "stores_evidence"
    FRAUD_CASE ||--o{ SAR_REGULATORY_FILING : "filed_in"
    FRAUD_CASE ||--o{ INTERVIEW_RECORD : "documents"
    FRAUD_CASE ||--o{ ACCOUNT_FREEZE_ORDER : "executes"
    FRAUD_CASE ||--o{ FINANCIAL_RECOVERY_LOG : "measures_loss"
    SUSPECT_ENTITY ||--o{ FRAUD_CASE : "investigated_in"
    SUSPECT_ENTITY ||--o{ FRAUDULENT_TRANSACTION : "initiates"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | FRAUD_CASE | Hồ sơ Vụ việc Gian lận | Master fraud investigation case record tracking case numbers, categories, investigators, open/closed dates, and status. | case_id, case_number, case_title, fraud_category, lead_investigator, case_status | Primary entity; originates from FRAUD_ALERT; files SAR_REGULATORY_FILING. |
| 2 | FRAUD_ALERT | Cảnh báo Gian lận Đầu vào | Stores automated transaction fraud alerts, velocity scores, anomaly scores, and intake status. | alert_id, source_system, transaction_amount, anomaly_score, alert_status | Originates FRAUD_CASE. |
| 3 | SUSPECT_ENTITY | Đối tượng Nghi vấn (Suspect) | Archives suspect individuals, shell companies, hashed SSNs/TINs, bank accounts, and risk levels. | suspect_id, full_name_or_company, tax_id_ssn_hash, account_number, risk_level | Investigated in FRAUD_CASE; initiates FRAUDULENT_TRANSACTION. |
| 4 | FRAUDULENT_TRANSACTION | Giao dịch Gian lận | Tracks individual fraudulent wire transfers, payment reference codes, transaction amounts, and beneficiary accounts. | transaction_id, transaction_reference_code, amount, sender_account, beneficiary_account | Contained in FRAUD_CASE; initiated by SUSPECT_ENTITY. |
| 5 | LINK_ANALYSIS_NODE | Nút Đồ thị Mối quan hệ (Graph) | Stores multi-entity relationship graph nodes (Accounts, IPs, Companies, Phone Numbers) and degrees of separation. | node_id, entity_type, entity_identifier, degree_of_separation, node_label | Mapped in FRAUD_CASE. |
| 6 | FRAUD_EVIDENCE | Trích xuất Bằng chứng Kỹ thuật số | Archives digital evidence items (bank statements, IP logs, email transcripts), SHA-256 hashes, and chain of custody logs. | evidence_id, evidence_type, file_name, sha256_hash, custody_logged_timestamp | Stored in FRAUD_CASE. |
| 7 | SAR_REGULATORY_FILING | Báo cáo Giao dịch Đáng ngờ (SAR) | Stores regulatory FinCEN SAR/STR filings, BSA tracking numbers, reported amounts, digital signatures, and status. | filing_id, bsa_tracking_number, reported_amount, filing_timestamp, filing_status | Filed in FRAUD_CASE. |
| 8 | INTERVIEW_RECORD | Biên bản Phỏng vấn Đối tượng | Archives witness and suspect interview transcripts, audio recording paths, interviewers, and summary notes. | interview_id, interviewee_name, interviewee_role, interview_timestamp, transcript_summary | Documented in FRAUD_CASE. |
| 9 | ACCOUNT_FREEZE_ORDER | Lệnh Phong tỏa Tài khoản | Archives core banking account freeze execution commands, frozen balance amounts, and core banking receipt codes. | freeze_id, account_number, frozen_balance, freeze_executed_timestamp, core_banking_receipt_code | Executed in FRAUD_CASE. |
| 10 | FINANCIAL_RECOVERY_LOG | Nhật ký Thu hồi Tài sản | Tracks gross financial losses, frozen funds recovered, bank clawbacks, and net unrecoverable losses. | recovery_id, gross_fraud_loss, recovered_amount, net_unrecoverable_loss, recovery_date | Measured in FRAUD_CASE. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | FRAUD_ALERT | one-to-one | FRAUD_CASE | originates_from | A confirmed fraud alert originates a formal fraud case file. |
| 2 | FRAUD_CASE | one-to-many | FRAUDULENT_TRANSACTION | contains_transaction | A fraud case contains one or more fraudulent transaction records. |
| 3 | FRAUD_CASE | one-to-many | LINK_ANALYSIS_NODE | mapped_in | A fraud case maps entity nodes in an interactive link analysis graph. |
| 4 | FRAUD_CASE | one-to-many | FRAUD_EVIDENCE | stores_evidence | A fraud case stores digital evidence items with chain of custody hashes. |
| 5 | FRAUD_CASE | one-to-many | SAR_REGULATORY_FILING | filed_in | A fraud case produces regulatory Suspicious Activity Reports (SAR/STR). |
| 6 | FRAUD_CASE | one-to-many | ACCOUNT_FREEZE_ORDER | executes | A fraud case executes core banking account freeze orders. |
| 7 | FRAUD_CASE | one-to-many | FINANCIAL_RECOVERY_LOG | measures_loss | A fraud case measures gross fraud exposure and financial recoveries. |
| 8 | SUSPECT_ENTITY | many-to-many | FRAUD_CASE | investigated_in | A suspect entity is investigated across one or more fraud cases. |
