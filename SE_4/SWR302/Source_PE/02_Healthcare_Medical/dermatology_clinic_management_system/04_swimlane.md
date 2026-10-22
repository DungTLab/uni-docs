# Swimlane Diagram — Dermatology Clinic Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Dermatology Consultation Cycle"])

    subgraph Lane1["Dermatology Patient"]
        A1["Register Skin Concerns & Sign Procedural Consent"]
        A2["Apply Numbing Cream & Attend Follow-up"]
    end

    subgraph Lane2["Aesthetic Nurse & Laser Tech"]
        B1["Capture Standardized Baseline Photos & Numbing Timer"]
        B2["Execute Laser Procedure & Post-Laser Cooling"]
    end

    subgraph Lane3["System / DCMS & AI Image Engine"]
        S1["Verify Fitzpatrick Type & Laser Safety Fluence"]
        S2{"AI Melanoma Risk Score > 75% or TDS > 5.4?"}
        S3["Flag Suspicious Lesion & Order Punch Biopsy"]
        S4["Calculate Scar Improvement & Render Photo Comparison"]
        S5["Deduct Treatment Package Session & Update POS"]
    end

    subgraph Lane4["Consultant Dermatologist & Surgeon"]
        C1["Perform Digital Dermoscopy & AI Lesion Screening"]
        C2["Prescribe Laser Fluence, Injectable Grids & Regimens"]
    end

    Finish(["End Treatment & Skin Maintenance Cycle"])

    Start --> A1 --> B1 --> C1 --> S1 --> S2
    S2 -->|"Yes"| S3 --> C2
    S2 -->|"No"| C2 --> A2 --> B2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Dermatology Patient | Registers skin chief complaints, signs procedure-specific informed consents, receives topical numbing cream, attends treatment visits, and reviews photo comparisons. |
| 2 | Aesthetic Nurse & Laser Tech | Captures standardized baseline clinical photos, monitors topical numbing timers (30-45m), operates medical lasers/IPL under doctor orders, and logs post-laser cooling. |
| 3 | System / DCMS & AI Image Engine | Validates laser parameter fluence against Fitzpatrick skin safety limits, computes AI melanoma risk scores, renders before/after split-screen photo comparisons, and deducts package sessions. |
| 4 | Consultant Dermatologist & Surgeon | Conducts 100x digital dermoscopy, evaluates ABCD lesion criteria, prescribes laser parameters (J/cm²), maps facial injection grids (Botox/Filler), and performs Mohs surgeries. |
