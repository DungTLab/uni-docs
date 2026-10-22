# Swimlane Diagram — Health Risk Assessment System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Digital Health Risk Assessment"])

    subgraph Lane1["Patient / Health Plan Subscriber"]
        A1["Complete Digital HRA Questionnaire & Authorize Lab Sync"]
        A2["Join Preventive Telehealth Visit & Set Wellness Goals"]
    end

    subgraph Lane2["HRAS Core Risk Engine & AI Scorer"]
        S1["Ingest Lab Panels & Execute ASCVD FINDRISC PRS Models"]
        S2{"ASCVD or Diabetes Risk Category = High?"}
        S3["Generate Standard Low-Risk Preventive Plan"]
        S4["Flag Profile High-Risk & Trigger Physician Triage"]
        S5["Publish Anonymized Corporate & Actuarial Reports"]
    end

    subgraph Lane3["Preventive Care Physician"]
        B1["Review Risk Scores & Validate Clinical Profile"]
        B2["Conduct Telehealth Consult & Order Diagnostic Screenings"]
    end

    subgraph Lane4["Corporate HR & Insurance Underwriter"]
        C1["Review Corporate Population Health Report & Premium Discounts"]
    end

    Finish(["End Evaluation & Activate Preventative Wellness Plan"])

    Start --> A1 --> S1 --> S2
    S2 -->|"No"| S3 --> S5 --> C1 --> Finish
    S2 -->|"Yes"| S4 --> B1 --> B2 --> A2 --> S5 --> C1 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient / Health Plan Subscriber | Completes dynamic digital HRA lifestyle survey, authorizes diagnostic lab blood panel ingestion, joins virtual preventive consultations, and tracks daily wellness targets. |
| 2 | HRAS Core Risk Engine & AI Scorer | Ingests lab chemistry data, calculates 10-year ASCVD, Framingham, FINDRISC, and Polygenic Risk Scores (PRS), stratifies risk categories, and generates corporate/actuarial reports. |
| 3 | Preventive Care Physician | Reviews automated high-risk flags, validates clinical risk calculations, conducts WebRTC preventive telehealth visits, orders diagnostic screenings, and signs off on care plans. |
| 4 | Corporate HR & Insurance Underwriter | Evaluates anonymized corporate employee population health risk reports, tracks aggregate metabolic syndrome prevalence, and calculates wellness premium discount incentives. |
