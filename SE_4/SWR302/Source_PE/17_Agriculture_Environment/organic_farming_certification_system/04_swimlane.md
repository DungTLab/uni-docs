# Swimlane Diagram — Organic Farming Certification System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Organic Certification Process"])

    subgraph ApplicantLane["Organic Farm Applicant / Producer"]
        A1["Submit Certification App & OSP"]
        A2["Prepare Farm & Buffer Zones"]
        A3["Accompany Inspector on Site Audit"]
        A4["Submit Corrective Action Plan"]
        A5["Receive Digital QR Certificate"]
    end

    subgraph SystemLane["Organic Farming Certification System"]
        S1["Validate Application Completeness"]
        S2["Process Testing Lab Residue Data"]
        S3{"Major Non-Compliance Found?"}
        S4["Compile Audit File Bundle"]
        S5["Generate QR Code & Public Entry"]
    end

    subgraph LabLane["Soil & Water Testing Laboratory"]
        L1["Receive Blind Tissue/Soil Samples"]
        L2["Perform LC-MS/MS Residue Screening"]
        L3["Upload Certified Lab Test Report"]
    end

    subgraph InspectorLane["Organic Certification Inspector"]
        I1["Review OSP & Schedule Inspection"]
        I2["Conduct On-Site Farm Inspection"]
        I3["Collect Soil & Tissue Samples"]
        I4["Issue Inspection Findings Report"]
    end

    subgraph BoardLane["Certification Decision Board"]
        G1["Conduct Dossier Evaluation"]
        G2{"Transition & Standards Met?"}
        G3["Sign & Authorize Organic License"]
        G4["Issue Rejection or Suspension"]
    end

    Finish(["End Process"])

    Start --> A1 --> S1 --> I1 --> A2 --> I2 --> I3 --> L1 --> L2 --> L3 --> S2 --> I4 --> S3
    S3 -->|"Yes"| A4 --> I4
    S3 -->|"No"| S4 --> G1 --> G2
    G2 -->|"Yes"| G3 --> S5 --> A5 --> Finish
    G2 -->|"No"| G4 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Organic Farm Applicant / Producer | Submits organic certification application and Organic System Plan (OSP), prepares land and buffer zones, accompanies inspector during on-site audit, submits corrective action plans for identified non-compliances, and receives official digital QR certificate |
| 2 | Organic Farming Certification System | Validates document completeness, ingests laboratory residue assay reports, tracks non-compliance resolution workflows, compiles complete audit file bundles for decision board review, and generates public QR code verification entries |
| 3 | Soil & Water Testing Laboratory | Receives blind soil, water, and plant tissue samples from inspectors, performs LC-MS/MS multi-residue pesticide and heavy metal screening, and uploads certified digital lab test reports |
| 4 | Organic Certification Inspector | Reviews applicant OSP, conducts physical farm inspection, inspects buffer zones and input storage sheds, collects geotagged soil/tissue samples, and files comprehensive inspection reports |
| 5 | Certification Decision Board | Evaluates complete audit dossiers, verifies 36-month transition compliance, reviews lab test results, votes on certification approval, and digitally signs official organic licenses |
