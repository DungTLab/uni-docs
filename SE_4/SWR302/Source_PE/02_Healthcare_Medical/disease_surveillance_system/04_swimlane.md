# Swimlane Diagram — Disease Surveillance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Disease Surveillance & Case Intake"])

    subgraph Lane1["Reporting Physician / Facility"]
        A1["Examine Patient & Report Notifiable Disease"]
        A2["Collect Diagnostic Specimen & Send to PHL"]
    end

    subgraph Lane2["Field Epidemiologist"]
        B1["Investigate Index Case & Trace Contacts"]
        B2["Enforce Home Quarantine & Log Daily Temp"]
    end

    subgraph Lane3["System / Analytics & Early Warning Engine"]
        S1["Parse ICD-10 Report & Check SLA Limits"]
        S2["Ingest Lab PCR & Match Genomic Variant"]
        S3{"Anomaly Z-Score > 3.0 or Rt > 1.0?"}
        S4["Highlight GIS Outbreak Cluster Heatmap"]
        S5["Publish Daily Epi-Curve & WHO IHR Report"]
    end

    subgraph Lane4["PHEOC Incident Commander"]
        C1["Escalate PHEOC Alert & Issue Vector Order"]
        C2["Enforce Community Quarantine & Travel Advisory"]
    end

    Finish(["End Outbreak Containment & Reporting Cycle"])

    Start --> A1 --> S1 --> A2 --> S2 --> S3
    S3 -->|"Yes"| S4 --> C1 --> C2 --> B1
    S3 -->|"No"| S4 --> B1 --> B2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Reporting Physician / Facility | Examines patients presenting with infectious symptoms, reports mandatory notifiable cases (ICD-10) within statutory timelines, collects clinical specimens, and dispatches to reference labs. |
| 2 | Field Epidemiologist | Conducts physical field investigations of Index Cases, traces close contacts (F1/F2), logs exposure locations, enforces quarantine orders, and records daily temperature check-ins. |
| 3 | System / Analytics & Early Warning Engine | Parses mandatory case reports, verifies statutory SLA timelines, ingests reference lab PCR results, executes spatial-temporal anomaly detection (Z-scores), plots Epi-Curves, and computes $R_t$. |
| 4 | PHEOC Incident Commander | Reviews spatial outbreak heatmaps and $R_t$ trajectory curves, escalates public health emergency alert levels (PHEOC Level 1-4), orders vector fogging/quarantine containment, and issues travel advisories. |
