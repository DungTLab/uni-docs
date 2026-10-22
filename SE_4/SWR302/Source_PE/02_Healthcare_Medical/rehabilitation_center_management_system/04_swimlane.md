# Swimlane Diagram — Rehabilitation Center Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Inpatient Rehabilitation Cycle"])

    subgraph Lane1["Rehab Patient / Caregiver"]
        A1["Admit to Rehab & Set ADL Recovery Goals"]
        A2["Perform ADL Retraining & Robotic Gait Sessions"]
        A3["Complete Caregiver Training & Home Discharge"]
    end

    subgraph Lane2["Multidisciplinary Rehab Team PT/OT/SLP"]
        B1["Evaluate FIM 18 Items & IDDSI Swallowing"]
        B2["Execute PT OT SLP Therapy & Splint Fitting"]
        B3["Conduct Home Environment Barrier Survey"]
    end

    subgraph Lane3["System / FIM & IRF Engine"]
        S1["Compute Admission FIM Score & CMG Tier"]
        S2{"FIM Gain Target Achieved & Home Safe?"}
        S3["Flag Progress Plateau & Alert Physiatrist"]
        S4["Compile IRF-PAI Dataset & CMS Claim"]
        S5["Generate Home Modification Architectural Specs"]
    end

    subgraph Lane4["Physiatrist PM&R Doctor"]
        C1["Formulate MDT Care Plan & 3-Hr Schedule"]
        C2["Lead Weekly Case Conference & Adjust Plan"]
    end

    Finish(["End Community Discharge Cycle"])

    Start --> A1 --> C1 --> B1 --> S1 --> C2 --> B2 --> S2
    S2 -->|"No"| S3 --> C2
    S2 -->|"Yes"| B3 --> S5 --> A2 --> S4 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Rehab Patient / Caregiver | Admits to rehabilitation facility, sets personal ADL recovery goals, participates in daily intensive PT/OT/SLP sessions, undergoes caregiver training, and discharges home. |
| 2 | Multidisciplinary Rehab Team PT/OT/SLP | Evaluates 18 FIM functional items, assesses swallowing IDDSI levels, conducts daily ADL retraining, fabricates custom splints/AFO orthoses, and conducts home barrier surveys. |
| 3 | System / FIM & IRF Engine | Calculates total FIM baseline/discharge scores (40 -> 90), computes CMS Case-Mix Group (CMG) tiers, enforces CMS 3-hour daily therapy rules, and generates IRF-PAI datasets. |
| 4 | Physiatrist PM&R Doctor | Formulates multidisciplinary rehabilitation care plans, sets weight-bearing restrictions, leads weekly interdisciplinary team case conferences, and approves IRF-PAI CMS claims. |
