# Swimlane Diagram — ESG Reporting System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Corporate ESG Reporting & Carbon Accounting Lifecycle"])

    subgraph Lane_Specialist["ESG Reporting Specialist"]
        S1["Ingest IoT Smart Meter Telemetry & Calculate Scope 1/2/3"]
        S2["Tag Disclosure Text with EFRAG iXBRL Taxonomy Concepts"]
    end

    subgraph Lane_CSO["Chief Sustainability Officer / CLO"]
        C1["Execute Double Materiality Assessment (Financial vs Impact)"]
        C2["Audit Greenwashing Risk & Approve Final CSRD Disclosure"]
    end

    subgraph Lane_System["ESG Reporting System"]
        Sys1["Apply EPA/DEFRA Emission Factors & Aggregate Footprint"]
        Sys2{"Passes iXBRL Taxonomy Validation & ISAE 3000 Assurance?"}
        Sys3["Flag Tagging Error or Calculation Discrepancy"]
        Sys4["Format ESRS iXBRL Package & Submit to EU ESAP Gateway"]
        Sys5["Update Net-Zero Dashboard & Stream Data Feed to MSCI/CDP"]
    end

    subgraph Lane_Gateways["EU CSRD Gateway & Independent Auditor (EU ESAP / Big Four)"]
        G1["Issue Independent Limited Assurance Statement (ISAE 3000)"]
        G2["Issue Official EU ESAP Accession Receipt (EU-CSRD-2026-884120)"]
    end

    Finish(["End ESG Reporting Cycle"])

    Start --> C1 --> S1 --> Sys1 --> S2
    Sys2 -->|"No / Validation Error"| Sys3 --> S2
    Sys2 -->|"Yes / Validated & Assured"| G1 --> S2 --> C2 --> Sys4 --> G2 --> Sys5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | ESG Reporting Specialist | Ingests facility IoT energy telemetry, utility bills, and travel logs, calculates Scope 1, 2, and 3 carbon footprints, and tags disclosure text with machine-readable EFRAG ESRS iXBRL taxonomy concepts. |
| 2 | Chief Sustainability Officer / CLO | Conducts double materiality assessments evaluating financial vs. impact materiality across 10 ESRS topics, audits greenwashing risks, and approves final CSRD disclosures. |
| 3 | ESG Reporting System | Applies EPA eGRID and DEFRA emission factors to calculate carbon footprints ($\text{tCO}_2\text{e}$), validates iXBRL taxonomy schemas, compiles ISAE 3000 audit binders, transmits electronic iXBRL filings to EU ESAP via API, and streams ESG feeds to MSCI and CDP. |
| 4 | EU CSRD Gateway & Independent Auditor (EU ESAP / Big Four) | Audits raw calculation workpapers to issue ISAE 3000 / ISO 14064 Independent Limited Assurance Statements, processes statutory ESRS iXBRL filings, and issues official EU ESAP accession receipts (`EU-CSRD-2026-884120`). |
