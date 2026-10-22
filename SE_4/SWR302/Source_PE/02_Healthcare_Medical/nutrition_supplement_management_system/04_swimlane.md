# Swimlane Diagram — Nutrition Supplement Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Clinical Nutrition Assessment"])

    subgraph Lane_Dietitian["Clinical Dietitian"]
        D1["Perform Nutritional Risk Screening NRS-2002"]
        D2["Calculate Energy REE & Protein Targets"]
        D3["Formulate Custom 3-in-1 TPN Admixture"]
        D4["Adjust Electrolytes / Amino Acid Ratios"]
    end

    subgraph Lane_System["Nutrition Supplement Management System"]
        S1["Ingest Lab Biomarkers & Calculate BMI"]
        S2["Check TPN Osmolarity & Calcium-Phosphate Curve"]
        S3{"Passes Osmolarity & Ca-PO4 Chemical Stability?"}
        S4["Route Authorized Order to Automated Compounder"]
        S5["Verify Bag Weight & Update Patient Caloric Deficit"]
    end

    subgraph Lane_Physician["Attending Physician / Intensivist"]
        P1["Review Clinical Indication & Lab Electrolytes"]
        P2["Authorize & Digitally Sign TPN Prescription"]
    end

    subgraph Lane_Compounding["TPN Compounding Machine"]
        C1["Ingest Formulation File & Barcode Check Bags"]
        C2["Sterile Micro-Pump Mix into TPN Bag"]
        C3["Perform Gravimetric Weight Check & Print Barcode"]
    end

    Finish(["End Parenteral Nutrition Cycle"])

    Start --> D1 --> S1 --> D2 --> D3 --> S2 --> S3
    S3 -->|"Yes"| P1 --> P2 --> S4 --> C1 --> C2 --> C3 --> S5 --> Finish
    S3 -->|"No / Precipitate"| D4 --> S2
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Clinical Dietitian | Conducts nutritional risk screenings (NRS-2002), calculates resting energy expenditure (REE) and protein targets, formulates custom TPN admixtures, and adjusts electrolyte ratios. |
| 2 | Nutrition Supplement Management System | Ingests laboratory biomarkers, calculates BMI, checks TPN osmolarity and calcium-phosphate solubility stability curves, routes authorized orders, and tracks cumulative energy deficits. |
| 3 | Attending Physician / Intensivist | Reviews clinical indications for parenteral nutrition, evaluates serum lab electrolytes, and authorizes custom TPN prescriptions with digital signatures. |
| 4 | TPN Compounding Machine | Ingests electronic formulation files, validates raw container barcodes, executes micro-pump sterile mixing into TPN bags, and verifies gravimetric bag weight. |
