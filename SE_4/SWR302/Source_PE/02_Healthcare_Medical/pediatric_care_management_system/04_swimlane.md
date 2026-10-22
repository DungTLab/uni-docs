# Swimlane Diagram — Pediatric Care Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Pediatric Consultation & Intake"])

    subgraph Lane1["Parent / Legal Guardian"]
        A1["Register Child & Parent Legal Linkage"]
        A2["Receive Vaccine & Review Growth Booklet"]
    end

    subgraph Lane2["Pediatric Nurse & Vaccine Tech"]
        B1["Log Weight Height & Scan Vaccine Lot Barcode"]
        B2["Administer Vaccine & Log PEWS Score"]
    end

    subgraph Lane3["System / PCMS & Dosing Engine"]
        S1["Compute WHO Growth Z-Score & Cold Chain Temp"]
        S2{"Weight-Based Dose < Max Adult Cap & Cold Chain Safe?"}
        S3["Flag Dosing Error / Cold Chain Breach Alert"]
        S4["Plot Growth Curve & Transmit National Vaccine Registry"]
        S5["Dispatch 7-Day Immunization Recall SMS"]
    end

    subgraph Lane4["Pediatrician & Neonatologist"]
        C1["Prescribe mg/kg Dose & Screen Denver II Milestones"]
        C2["Verify PEWS Triage & Order Infant Nutrition"]
    end

    Finish(["End Care & Growth Cycle"])

    Start --> A1 --> B1 --> S1 --> C1 --> S2
    S2 -->|"No"| S3 --> C1
    S2 -->|"Yes"| B2 --> A2 --> C2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Parent / Legal Guardian | Registers child profile, links parent national IDs, presents child for growth checks and vaccinations, receives milestone guides, and receives SMS reminders. |
| 2 | Pediatric Nurse & Vaccine Tech | Measures child weight/height/head circumference, scans 2D vaccine lot barcodes, checks 2°C-8°C fridge temperatures, administers vaccines, and scores PEWS. |
| 3 | System / PCMS & Dosing Engine | Calculates WHO growth Z-scores, computes weight-based mg/kg drug dosing, checks maximum adult caps, validates cold chain safety, plots curves, and syncs NIIS. |
| 4 | Pediatrician & Neonatologist | Evaluates WHO growth percentiles, prescribes mg/kg medications, performs Denver II developmental screenings, assesses NICU neonates, and clears school health certs. |
