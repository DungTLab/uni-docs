# Swimlane Diagram — Security Audit & Compliance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Compliance Audit Workflow"])

    subgraph Lane1["Lead Security Auditor"]
        A1["Define Audit Test Plan & Select Sample"]
        A2["Issue Automated Evidence Request"]
        A3["Review Uploaded Evidence & Test Control"]
        A4["Log Compliance Control Gap & Deficiency"]
    end

    subgraph Lane2["System"]
        S1["Cross-Map Control to ISO 27001 & SOC 2"]
        S2["Hash Evidence Artifact (SHA-256)"]
        S3{"Control Operating Effectively?"}
        S4["Track CAP Remediation SLA Timer"]
        S5["Update Audit Readiness Status to 100%"]
    end

    subgraph Lane3["Business Unit Manager"]
        M1["Receive Evidence Request Task"]
        M2["Upload Evidence File & Working Proof"]
        M3["Submit Corrective Action Plan (CAP)"]
        M4["Execute Remediation & Upload Fix Proof"]
    end

    subgraph Lane4["External Audit Firm"]
        E1["Inspect Verified Working Papers & Issue SOC 2 Report"]
    end

    Finish(["End Audit Attestation Process"])

    Start --> S1 --> A1 --> A2 --> M1 --> M2 --> S2 --> A3 --> S3
    S3 -->|"Passed"| S5 --> E1 --> Finish
    S3 -->|"Failed / Deficiency"| A4 --> M3 --> S4 --> M4 --> A3
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Lead Security Auditor | Defines control test plans, issues evidence requests, reviews uploaded proof, tests control effectiveness, and logs compliance gaps. |
| 2 | System | Cross-maps internal controls to ISO 27001/SOC 2, computes SHA-256 evidence hashes, tracks CAP SLA timers, and compiles audit working papers. |
| 3 | Business Unit Manager | Receives evidence requests, uploads required proof files, submits Corrective Action Plans (CAP) for deficiencies, and executes fixes. |
| 4 | External Audit Firm | Reviews verified working papers, inspects control testing logs, and issues official SOC 2 Type II attestation reports. |
