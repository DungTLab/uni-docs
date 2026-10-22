# Swimlane Diagram — Brand Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Brand Asset & Clearance Lifecycle"])

    subgraph Lane_Manager["Brand Manager"]
        M1["Publish Interactive Brand Style Guide & Color Tokens"]
        M2["Review Co-Branding Submission & Sign Off"]
    end

    subgraph Lane_Agency["External Agency Partner"]
        A1["Upload Co-Branding Creative & Partner Logo"]
        A2["Request Official Co-Branding Clearance"]
    end

    subgraph Lane_System["Brand Management System"]
        S1["Verify Vector Logo Assets & Sizing Ratios"]
        S2{"Passes AI Brand Audit & Co-Branding Rules?"}
        S3["Flag Rule Violation & Prompt Geometry Fix"]
        S4["Package Approved Brand Kits & Generate Certificates"]
        S5["Update Brand Health Equity & SOV Dashboard"]
    end

    subgraph Lane_AI["Brand Compliance AI Engine"]
        E1["Scan Visual Logo Clear Space & Color Histogram"]
        E2["Scan Copy Text for Brand Voice & Banned Words"]
    end

    Finish(["End Brand Governance Cycle"])

    Start --> M1 --> A1 --> A2 --> S1 --> E1 --> E2 --> S2
    S2 -->|"No"| S3 --> A1
    S2 -->|"Yes / Approved"| M2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Brand Manager | Publishes interactive Brand Style Guides and CSS color tokens, sets clear space rules, reviews agency co-branding submissions, and signs off on brand clearance certificates. |
| 2 | External Agency Partner | Uploads co-branding marketing collateral and partner logos, generating logo lockups and submitting clearance requests. |
| 3 | Brand Management System | Verifies vector logo visual assets, validates visual $1:1$ height ratios, packages approved brand kits, generates cryptographic clearance certificates, and updates executive Brand Health Equity dashboards. |
| 4 | Brand Compliance AI Engine | Audits ad creative using computer vision AI (logo clear space boundaries, color pixel histograms) and NLP text analytics (banned words, brand voice tone). |
