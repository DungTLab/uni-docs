# Swimlane Diagram — Oncology Patient Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Oncology Intake & MDT Staging"])

    subgraph Lane1["Cancer Patient & Caregiver"]
        A1["Attend Intake & Sign Chemotherapy Consents"]
        A2["Receive IV Infusion & Log ESAS Symptoms"]
    end

    subgraph Lane2["Oncology Nurse Specialist"]
        B1["Check Pre-Chemo Blood ANC & Platelet Counts"]
        B2["Execute Double-Witness Nurse Verification"]
    end

    subgraph Lane3["System / OPMS & Dose Engine"]
        S1["Calculate BSA m2 & Match Somatic Biomarkers"]
        S2{"Pre-Chemo ANC >= 1.5 & Platelets >= 100?"}
        S3["Hold Chemo Cycle & Alert Medical Oncologist"]
        S4["Unlock IV Pump & Verify LINAC Gy Fractionation"]
        S5["Render 5-Yr Survival Curve & Survivorship Care Plan"]
    end

    subgraph Lane4["Medical & Radiation Oncologist"]
        C1["Formulate MDT Multimodal Plan & Stage TNM"]
        C2["Prescribe BSA-Calculated Chemo & RT Gy Dose"]
    end

    Finish(["End Survivorship & Remission Tracking"])

    Start --> A1 --> C1 --> S1 --> C2 --> B1 --> S2
    S2 -->|"No"| S3 --> C2
    S2 -->|"Yes"| B2 --> A2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Cancer Patient & Caregiver | Attends oncology intake, signs chemotherapy procedural consents, receives IV chemotherapy infusions in chairs, logs home ESAS symptom scores, and tracks survivorship. |
| 2 | Oncology Nurse Specialist | Checks pre-chemo blood counts (ANC/Platelets), executes independent double-witness nurse verification (7 safety checks), operates IV pumps, and logs CTCAE toxicity grades. |
| 3 | System / OPMS & Dose Engine | Calculates Body Surface Area (BSA m²), checks organ safety dosing rules, matches somatic biomarkers to targeted drugs, unlocks IV pumps, and exports 5-year survival statistics. |
| 4 | Medical & Radiation Oncologist | Formulates Multidisciplinary Tumor Board (MDT) plans, stages TNM (AJCC 8th ed), prescribes BSA-calculated chemotherapy protocols (FOLFOX), and plans DICOM-RT Gray doses. |
