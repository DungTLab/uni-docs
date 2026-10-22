# Swimlane Diagram — Medical Research Collaboration Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Multi-Center Research Protocol"])

    subgraph Lane1["Principal Investigator"]
        A1["Formulate Protocol & Attach IRB Approval"]
        A2["Co-Author Manuscript & Assign CRediT Roles"]
    end

    subgraph Lane2["Site Clinical Coordinator"]
        B1["Screen Subjects & Enter De-Identified eCRFs"]
        B2["Resolve Data Queries & Log Consent Dates"]
    end

    subgraph Lane3["System / MRCP & Federated Engine"]
        S1["Deploy CDISC ODM eCRF Schema Across Sites"]
        S2{"Log-Rank p-Value < 0.05 & Privacy Budget OK?"}
        S3["Flag Inconclusive Result & Expand Sample Size"]
        S4["Run Federated Computation & Render Kaplan-Meier"]
        S5["Mint CrossRef DOI & Submit Journal Package"]
    end

    subgraph Lane4["Biostatistician & Journal Editor"]
        C1["Execute R/Python Survival Analysis Scripts"]
        C2["Conduct Blinded Peer Review & Accept Paper"]
    end

    Finish(["End Manuscript Publication & DOI Minting"])

    Start --> A1 --> S1 --> B1 --> B2 --> S4 --> C1 --> S2
    S2 -->|"No"| S3 --> B1
    S2 -->|"Yes"| A2 --> S5 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Principal Investigator | Formulates multi-center research protocols, attaches IRB approvals, assigns research sites, co-authors medical manuscripts, and manages CRediT author roles. |
| 2 | Site Clinical Coordinator | Screens subjects against inclusion criteria, records informed consents, inputs de-identified eCRF data, and resolves electronic data queries (DCF). |
| 3 | System / MRCP & Federated Engine | Deploys CDISC ODM eCRF schemas, executes federated queries with differential privacy ($\epsilon$), aggregates datasets, renders Kaplan-Meier curves, and mints DOIs. |
| 4 | Biostatistician & Journal Editor | Executes R/Python biostatistical analysis scripts, tests log-rank hypotheses, conducts blinded peer-review evaluations, and approves final manuscript publication. |
