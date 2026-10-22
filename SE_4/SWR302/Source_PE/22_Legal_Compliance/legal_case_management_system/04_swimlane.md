# Swimlane Diagram — Legal Case Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Legal Case Litigation Lifecycle"])

    subgraph Lane_Litigator["Senior Litigator"]
        L1["Open New Legal Matter Draft & Input Adverse Parties"]
        L2["Prepare PDF/A Pleading Package & Apply Digital Signature"]
    end

    subgraph Lane_Compliance["Corporate Compliance Director"]
        C1["Review Conflict Check Report & Configure Ethical Wall"]
        C2["Issue Corporate Litigation Hold to Custodians"]
    end

    subgraph Lane_System["Legal Case Management System"]
        S1["Execute Automated Conflict-of-Interest Search Engine"]
        S2{"Passes Conflict Check & Document Redaction Validation?"}
        S3["Flag Adverse Conflict & Place Matter on Compliance Hold"]
        S4["Calculate Procedural Court Deadlines & Transmit E-Filing"]
        S5["Audit Outside Counsel LEDES Invoice & Update Legal Exposure"]
    end

    subgraph Lane_Court["Court E-Filing & Judiciary Portal"]
        E1["Receive E-Filing Package & Verify Bar Credentials"]
        E2["Issue Electronic Court Docket Receipt (NEF Entry #42)"]
    end

    Finish(["End Legal Matter Cycle"])

    Start --> L1 --> S1 --> S2
    S2 -->|"No / Conflict Detected"| S3 --> C1 --> L2
    S2 -->|"Yes / Cleared"| L2 --> S4 --> E1 --> E2 --> C2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Senior Litigator | Opens new legal matter drafts, inputs adverse party entities, prepares PDF/A court pleading packages, applies digital signatures, and manages case dockets. |
| 2 | Corporate Compliance Director | Reviews automated conflict-of-interest evaluation reports, configures ethical wall isolation barriers for conflicted attorneys, and issues corporate litigation hold notices. |
| 3 | Legal Case Management System | Runs automated conflict search algorithms across historical matters and CRM entities, calculates procedural court deadlines (Rules of Civil Procedure), validates document PDF/A formatting and PII redactions, audits outside counsel LEDES fee invoices, and computes legal exposure $. |
| 4 | Court E-Filing & Judiciary Portal | Ingests electronic court filings via REST APIs (Federal ECF/CM), verifies attorney bar registration credentials, and issues electronic Notice of Electronic Filing (NEF) docket receipts. |
