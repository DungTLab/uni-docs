# Swimlane Diagram — Employment Law Compliance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Workplace Grievance Intake"])

    subgraph Lane1["Employee"]
        E1["Submit Harassment Grievance Complaint"]
        E2["Provide Witness List & Evidence"]
    end

    subgraph Lane2["System"]
        S1["Encrypt & Triage Incident Ticket"]
        S2["Check for HR Officer Conflict"]
        S3{"Conflict Detected?"}
        S4["Assign Complaint to CHRO"]
        S5["Assign Complaint to HR Compliance Officer"]
        S6["Generate Disciplinary Clearance Document"]
        S7["Update Investigation Status to CLOSED"]
    end

    subgraph Lane3["HR Compliance Officer"]
        H1["Conduct Witness Interviews & Gather Proof"]
        H2["Formulate Summary Investigation Findings"]
        H3{"Complaint Substantiated?"}
        H4["Issue Corrective Action Plan (CAP)"]
        H5["Close Ticket as Unsubstantiated"]
    end

    subgraph Lane4["CHRO"]
        C1["Approve Disciplinary Action & Termination"]
    end

    Finish(["End Grievance Investigation"])

    Start --> E1 --> S1 --> S2 --> S3
    S3 -->|"Yes"| S4 --> H1
    S3 -->|"No"| S5 --> H1
    E2 --> H1 --> H2 --> H3
    H3 -->|"Yes"| H4 --> C1 --> S6 --> S7 --> Finish
    H3 -->|"No"| H5 --> S7 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Employee | Submits confidential workplace grievance or harassment complaint with supporting witness details and documentary proof. |
| 2 | System | Encrypts grievance data, checks for conflict of interest (e.g., if HR officer is named), assigns investigator, generates disciplinary documentation, and updates audit records. |
| 3 | HR Compliance Officer | Conducts formal witness interviews, evaluates evidence, formulates investigation summary findings, and drafts corrective action plans. |
| 4 | CHRO | Executive approval authority for severe corrective action plans, suspensions, or involuntary employee terminations. |
