# Swimlane Diagram — Compliance Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Compliance Governance & Control Testing Cycle"])

    subgraph Lane_Employee["Corporate Employee"]
        E1["Review Code of Conduct Policy & Pass Quiz"]
        E2["Apply Digital Signature & Submit Policy Attestation"]
    end

    subgraph Lane_Auditor["Compliance Audit Manager"]
        A1["Inspect Control Sample Items & Audit Effectiveness"]
        A2["Assign CAPA Remediation Plan & Perform Re-Test"]
    end

    subgraph Lane_System["Compliance Management System"]
        S1["Ingest Statutory Rule Updates & Map Policy Gaps"]
        S2{"Passes Control Testing & Attestation Requirements?"}
        S3["Log Control Deficiency & Trigger Remediation Task"]
        S4["Issue Attestation Certificate & Update HRIS Record"]
        S5["Update Enterprise Compliance Risk Scorecard"]
    end

    subgraph Lane_RegFeed["Regulatory Change Feed (Thomson Reuters)"]
        R1["Stream Statutory Regulatory Update Alerts"]
        R2["Return Regulatory Rule Changes & Effective Dates"]
    end

    Finish(["End Compliance Cycle"])

    Start --> R1 --> R2 --> S1 --> E1 --> E2 --> S2
    S2 -->|"No / Control Deficiency"| S3 --> A1 --> A2 --> S5
    S2 -->|"Yes / Compliant"| S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Employee | Reviews mandatory corporate policies, completes comprehension quizzes, applies digital signature attestations, and declares conflicts of interest or gifts. |
| 2 | Compliance Audit Manager | Audits internal control effectiveness sample items, logs control deficiencies, assigns Corrective and Preventive Action (CAPA) plans, and performs verification re-tests. |
| 3 | Compliance Management System | Ingests statutory regulatory updates, maps rule changes to policies, tracks employee policy attestations, evaluates control testing sample results, logs deficiencies, issues attestation certificates, and updates compliance scorecards. |
| 4 | Regulatory Change Feed (Thomson Reuters) | Streams real-time statutory regulatory intelligence updates, statutory rule changes, and compliance effective dates across jurisdictions. |
