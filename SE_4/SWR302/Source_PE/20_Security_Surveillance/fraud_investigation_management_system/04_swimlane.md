# Swimlane Diagram — Fraud Investigation Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Fraud Alert Intake"])

    subgraph Lane1["Transaction Fraud Engine"]
        F1["Flag Suspicious High-Value Wire Transfer Alert"]
        F2["Deliver Anomaly Score & Payload to System"]
    end

    subgraph Lane2["AML Risk Analyst"]
        A1["Triage Alert & Verify BEC Wire Fraud Signal"]
        A2["Escalate Alert to Formal Fraud Case File"]
    end

    subgraph Lane3["System"]
        S1["Query Core Banking Customer KYC & Log Context"]
        S2["Execute Link Analysis Query & Build Graph"]
        S3["Issue Core Banking Account Freeze API Call"]
        S4["Validate SAR Form XML against FinCEN Schema"]
        S5["Transmit Encrypted SAR to FinCEN E-Filing"]
    end

    subgraph Lane4["General Counsel"]
        C1["Review Link Graph & Authorize Account Freeze"]
        C2["Review Case Summary & Digital Sign SAR Form"]
    end

    Finish(["End Fraud Investigation Process"])

    Start --> F1 --> F2 --> S1 --> A1 --> A2 --> S2 --> C1 --> S3 --> C2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Transaction Fraud Engine | Detects suspicious high-value wire transfers, calculates anomaly scores, and posts alert payloads. |
| 2 | AML Risk Analyst | Triages incoming alerts, verifies wire fraud signatures against customer KYC context, and escalates to formal case files. |
| 3 | System | Queries core banking logs, renders interactive link analysis graphs, executes account freeze APIs, validates SAR XML schemas, and transmits filings. |
| 4 | General Counsel | Reviews entity relationship graphs, signs account freeze authorization orders, and digitally signs regulatory FinCEN SAR filings. |
