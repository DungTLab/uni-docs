# Swimlane Diagram — Patent Application Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Patent Application Filing & Prosecution Lifecycle"])

    subgraph Lane_Inventor["Corporate Inventor"]
        I1["Review Patent Claim Draft & Execute Digital Inventor Oath"]
        I2["Validate Technical CAD Drawings & Vector Figures"]
    end

    subgraph Lane_Counsel["Patent Prosecution Counsel"]
        A1["Draft Independent & Dependent Claims Specification"]
        A2["File Response Amendment to USPTO 35 U.S.C. 103 Rejection"]
    end

    subgraph Lane_System["Patent Application Management System"]
        S1["Execute Pre-Filing AI Prior Art Search & Validate ADS XML"]
        S2{"Passes Pre-Filing Validation & Office Action Rejection?"}
        S3["Flag Invalid Margins or Missing Antecedent Basis Error"]
        S4["Compile Package & Auto-Docket 3-Month Response Deadline"]
        S5["Process Issue Fee Payment & Issue Granted Patent Record"]
    end

    subgraph Lane_Portals["USPTO & WIPO Patent Portals (EFS-Web / ePCT)"]
        G1["Transmit Electronic Patent Package to USPTO Patent Center"]
        G2["Return Serial Number (US 18/884,120) & Office Action Notice"]
    end

    Finish(["End Patent Prosecution Cycle"])

    Start --> A1 --> I1 --> I2 --> S1 --> S2
    S2 -->|"No / Validation Failed"| S3 --> A1
    S2 -->|"Yes / Approved & Valid"| S4 --> G1 --> G2 --> A2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Inventor | Reviews technical patent claim drafts, validates vector CAD figure drawings, signs statutory USPTO Inventor Oath/Declaration forms, and verifies invention disclosures. |
| 2 | Patent Prosecution Counsel | Drafts independent and dependent patent claim specifications, verifies antecedent basis rules, compiles Information Disclosure Statements (IDS), amends claims in response to USPTO examiner rejections, and files prosecution responses. |
| 3 | Patent Application Management System | Executes pre-filing AI prior art searches, validates PDF page margins/embedded fonts, generates Application Data Sheet (ADS) XML, auto-dockets 3-month statutory office action deadlines, tracks 30-month PCT national phase timelines, and updates prosecution scorecards. |
| 4 | USPTO & WIPO Patent Portals (EFS-Web / ePCT) | Ingests electronic XML/PDF patent application packages via REST APIs, issues official serial numbers (`US 18/884,120`) and filing receipts, transmits office action notices, and issues patent grant certificates. |
