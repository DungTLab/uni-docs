# Swimlane Diagram — GDPR & Data Privacy Compliance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start GDPR Privacy Request & Breach Response Lifecycle"])

    subgraph Lane_Subject["Data Subject (EU Resident)"]
        S1["Submit DSAR Access / Erasure Request via Privacy Portal"]
        S2["Complete MFA Identity Verification & Download Export"]
    end

    subgraph Lane_DPO["Data Protection Officer (DPO)"]
        D1["Review ROPA Mapping & Evaluate DPIA High-Risk Activities"]
        D2["Approve Partial Erasure Plan Respecting Tax Overrides"]
    end

    subgraph Lane_System["GDPR & Data Privacy Compliance System"]
        Sys1["Execute Automated PII Discovery & Initialize 30-Day SLA"]
        Sys2{"Passes Identity Verification & Legal Retention Check?"}
        Sys3["Reject Request & Log Un-Verified Access Security Alert"]
        Sys4["Compile Encrypted PII Export & Execute Automated Deletion"]
        Sys5["Start 72-Hour Breach Countdown & Submit DPA Notification"]
    end

    subgraph Lane_DPA["European Data Protection Authority (CNIL / ICO)"]
        A1["Receive Statutory 72-Hour Data Breach Notification Payload"]
        A2["Issue Official DPA Breach Receipt (#DPA-INC-88412)"]
    end

    Finish(["End Data Privacy Cycle"])

    Start --> S1 --> Sys1 --> S2 --> Sys2
    Sys2 -->|"No / Identity Unverified"| Sys3 --> Finish
    Sys2 -->|"Yes / Verified"| D2 --> Sys4 --> D1 --> Sys5 --> A1 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Data Subject (EU Resident) | Submits Data Subject Access Requests (DSAR: Access, Erasure, Rectification) via self-service privacy portals, completes MFA identity verification, and downloads compiled personal data export ZIP archives. |
| 2 | Data Protection Officer (DPO) | Maintains Record of Processing Activities (ROPA), conducts Data Privacy Impact Assessments (DPIA), evaluates statutory tax retention overrides, and approves partial erasure execution plans. |
| 3 | GDPR & Data Privacy Compliance System | Scans enterprise databases for PII data discovery, tracks 30-day statutory DSAR SLA timers, verifies requester identity via MFA, redacts third-party PII, compiles data exports, executes database erasure scripts, enforces OneTrust consent opt-outs, and manages 72-hour data breach notification timers. |
| 4 | European Data Protection Authority (CNIL / ICO) | Receives statutory 72-hour data breach notifications (GDPR Art 33), evaluates breach severity, and issues official DPA confirmation receipts. |
