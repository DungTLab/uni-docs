# Swimlane Diagram — Genomics & Biobank Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Biospecimen Accession & Genomics Cycle"])

    subgraph Lane1["Biobank Specimen Technician"]
        A1["Accession Specimen & Scan 2D Matrix Cryo-Vial"]
        A2["Assign 4-Tier Grid Location in -196C LN2 Tank"]
    end

    subgraph Lane2["Bioinformatician & Geneticist"]
        B1["Ingest NGS VCF & Calculate Q30 Depth Metrics"]
        B2["Classify ACMG Pathogenicity & Match PGx Drugs"]
    end

    subgraph Lane3["System / Genomics & Cryo Engine"]
        S1["Verify Donor Consent & Cold Ischemia Time"]
        S2{"NGS Q30 Score > 85% & Depth > 100x?"}
        S3["Flag Low Coverage & Prompt Re-Sequencing"]
        S4["Annotate ClinVar & Format HGVS Nomenclature"]
        S5["Validate IRB Approval & Issue Pick List"]
    end

    subgraph Lane4["Principal Investigator & Doctor"]
        C1["Submit Specimen Requisition & IRB Approval"]
        C2["Receive Precision Oncology & Variant Report"]
    end

    Finish(["End Clinical Reporting & Research Release Cycle"])

    Start --> A1 --> S1 --> A2 --> B1 --> S2
    S2 -->|"No"| S3 --> B1
    S2 -->|"Yes"| S4 --> B2 --> C1 --> S5 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Biobank Specimen Technician | Accessions incoming biospecimens, verifies tissue types, scans 2D matrix barcode cryo-vials, aliquots DNA/RNA, and assigns precise 4-tier grid locations in -80°C/-196°C freezers. |
| 2 | Bioinformatician & Geneticist | Ingests NGS sequencing VCF files, evaluates coverage depth metrics, annotates variants against ClinVar/gnomAD, scores ACMG pathogenicity, and matches PGx drug-gene recommendations. |
| 3 | System / Genomics & Cryo Engine | Validates donor informed consent scope, tracks cold ischemia time, monitors IoT freezer telemetry, formats HGVS nomenclature, verifies IRB approvals, and generates cryo-box pick lists. |
| 4 | Principal Investigator & Doctor | Submits research sample requisitions, attaches IRB approvals and MTAs, orders precision oncology tumor panels, and receives actionable genomic mutation reports. |
