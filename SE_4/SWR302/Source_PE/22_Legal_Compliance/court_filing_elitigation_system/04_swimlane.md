# Swimlane Diagram — Court Filing & E-Litigation System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Court E-Filing & Litigation Service Lifecycle"])

    subgraph Lane_Attorney["Lead Trial Attorney"]
        A1["Format Court Motion Package & Apply Digital Bar Signature"]
        A2["Present Digital Trial Exhibits in Courtroom Hearing"]
    end

    subgraph Lane_Clerk["Electronic Filing Clerk"]
        C1["Upload Draft PDF & Verify PDF/A Compliance & Redactions"]
        C2["Review NEF Receipt & Dispatch Certified e-Service to Opposing"]
    end

    subgraph Lane_System["Court Filing & E-Litigation System"]
        S1["Execute Technical PDF/A-1b & Margin/PII Validation Audit"]
        S2{"Passes PDF/A Technical Validation & Fee Settlement?"}
        S3["Flag Un-Redacted PII Error & Block E-Filing Transmission"]
        S4["Transmit Filing Package & Ingest Official NEF Receipt"]
        S5["Auto-Docket Response Deadline & File Proof of Service Affidavit"]
    end

    subgraph Lane_Gateways["Court CM/ECF Gateway & Pay.gov (Federal ECF / Pay.gov)"]
        G1["Authorize $50 Motion Filing Fee Settlement via Pay.gov API"]
        G2["Issue Official NEF Docket Receipt (Docket Entry #42)"]
    end

    Finish(["End E-Litigation Cycle"])

    Start --> A1 --> C1 --> S1 --> S2
    S2 -->|"No / PII Error or Non-Compliant PDF"| S3 --> C1
    S2 -->|"Yes / Compliant & Valid"| G1 --> S4 --> G2 --> C2 --> S5 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Lead Trial Attorney | Formats court motion packages, applies cryptographic bar certificate digital signatures, presents indexed digital trial exhibits in courtroom hearings, and executes emergency writs. |
| 2 | Electronic Filing Clerk | Uploads draft pleading PDFs, verifies PDF/A compliance and PII redactions, reviews official NEF docket receipts, and dispatches certified e-service to opposing counsel. |
| 3 | Court Filing & E-Litigation System | Executes technical PDF/A-1b, margin, and PII redaction validation audits, calculates court filing fees, dispatches electronic filing payloads to CM/ECF, ingests NEF receipts, auto-dockets procedural motion response deadlines, tracks certified e-service email delivery, and generates Proof of Service affidavits. |
| 4 | Court CM/ECF Gateway & Pay.gov (Federal ECF / Pay.gov) | Settles statutory court filing fees via Pay.gov API, processes electronic PDF/A pleading packages, and issues official Notice of Electronic Filing (NEF) docket receipts (`Docket Entry #42`). |
