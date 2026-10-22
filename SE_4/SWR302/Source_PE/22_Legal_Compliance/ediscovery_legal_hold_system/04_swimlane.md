# Swimlane Diagram — E-Discovery & Legal Hold System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start E-Discovery & Legal Hold Lifecycle"])

    subgraph Lane_Custodian["Corporate Data Custodian"]
        C1["Receive Legal Hold Notice & Apply Digital Signature"]
        C2["Verify Data Location & Acknowledge Re-Certification"]
    end

    subgraph Lane_Analyst["Senior Forensic Analyst"]
        A1["Train TAR Machine Learning Model on Seed Coding Set"]
        A2["Burn-In Redactions & Export Bates Production Package"]
    end

    subgraph Lane_System["E-Discovery & Legal Hold System"]
        S1["Issue Legal Hold Notice & Invoke Cloud Preservation APIs"]
        S2{"Passes TAR Predictive Relevance & Redaction Validation?"}
        S3["Suppress Non-Responsive Docs & Continue CAL Model Training"]
        S4["Execute ESI Processing, MD5 Deduplication & TAR Scoring"]
        S5["Generate Concordance Load Files & Court Privilege Log"]
    end

    subgraph Lane_Cloud["Microsoft 365 & Cloud Preservation APIs"]
        E1["Apply In-Place Email & File Preservation Locks"]
        E2["Confirm Mailbox Preservation Active (Guid: e3b0c442)"]
    end

    Finish(["End Discovery Production Cycle"])

    Start --> S1 --> E1 --> E2 --> C1 --> C2 --> S4 --> A1 --> S2
    S2 -->|"No / Non-Responsive"| S3 --> A1
    S2 -->|"Yes / Responsive & Verified"| A2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Data Custodian | Receives legally binding litigation hold notices, applies trackable digital signatures, verifies data storage locations, and completes periodic re-certifications. |
| 2 | Senior Forensic Analyst | Codes initial seed sets for Technology-Assisted Review (TAR / CAL), reviews high-probability responsive documents, applies redaction masks, burns in redactions, and exports Bates production packages. |
| 3 | E-Discovery & Legal Hold System | Dispatches legal hold notices, invokes cloud preservation APIs, processes raw ESI containers (MD5 de-duplication, email threading), scores document responsiveness via Continuous Active Learning (CAL 0-100), validates redaction burn-in security, generates Concordance load files, and compiles privilege logs. |
| 4 | Microsoft 365 & Cloud Preservation APIs | Accepts API in-place preservation commands (`New-ComplianceHoldPolicy`), applies mailbox and OneDrive locks, and preserves deleted items without user awareness. |
