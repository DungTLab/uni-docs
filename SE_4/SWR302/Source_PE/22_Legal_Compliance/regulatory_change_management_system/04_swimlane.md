# Swimlane Diagram — Regulatory Change Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Statutory Regulatory Change Lifecycle"])

    subgraph Lane_Owner["Business Unit Process Owner"]
        O1["Receive Operational Change Task & Update Procedures"]
        O2["Upload Implementation Evidence & Submit Sign-Off"]
    end

    subgraph Lane_Analyst["Regulatory Compliance Specialist"]
        A1["Review AI Gap Analysis & Map Rule-to-Policy Matrix"]
        A2["Verify Implementation Evidence & Certify Audit Binder"]
    end

    subgraph Lane_System["Regulatory Change Management System"]
        S1["Ingest Statutory Rule Updates & Run AI Semantic Gap Search"]
        S2{"Passes AI Gap Analysis & Operational Verification?"}
        S3["Flag Regulatory Compliance Gap & Trigger Policy Task"]
        S4["Dispatch Operational CAPA Task & Start SLA Timer"]
        S5["Update Statutory Readiness & fine Liability Scorecard"]
    end

    subgraph Lane_Feed["Global Regulatory Feed (Thomson Reuters)"]
        F1["Stream Real-Time Statutory Rule Updates & Gazette Notices"]
        F2["Return Statutory Rule Texts & Mandatory Effective Dates"]
    end

    Finish(["End Regulatory Change Cycle"])

    Start --> F1 --> F2 --> S1 --> A1 --> S2
    S2 -->|"No / Gap Identified"| S3 --> S4 --> O1 --> O2 --> A2 --> S5
    S2 -->|"Yes / Compliant"| S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Business Unit Process Owner | Receives mandatory operational change tasks, updates business unit procedures and controls, uploads implementation evidence logs, and submits task completion sign-offs. |
| 2 | Regulatory Compliance Specialist | Reviews AI semantic gap analysis reports, maintains bi-directional rule-to-policy traceability matrices, triggers policy revisions, verifies process owner implementation evidence, and certifies audit evidence binders. |
| 3 | Regulatory Change Management System | Ingests real-time statutory regulatory updates, runs AI NLP semantic gap searches against internal policy vector embeddings, flags compliance gaps, dispatches CAPA operational change tasks, tracks statutory compliance deadlines, and computes non-compliance fine liability scorecards. |
| 4 | Global Regulatory Feed (Thomson Reuters) | Streams real-time statutory regulatory intelligence updates, government gazette notices, legal amendments, and mandatory statutory effective dates. |
