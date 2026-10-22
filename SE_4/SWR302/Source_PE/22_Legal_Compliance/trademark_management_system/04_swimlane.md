# Swimlane Diagram — Trademark Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Trademark Clearance & Brand Enforcement Lifecycle"])

    subgraph Lane_BrandMgr["Brand Manager"]
        B1["Submit Proposed Wordmark & Logo Image for Clearance"]
        B2["Review Alternate Brand Proposals if Risk Flagged"]
    end

    subgraph Lane_Counsel["Global Trademark Counsel / Paralegal"]
        C1["Review AI Clearance Search Hits & Nice Class Specs"]
        C2["File ESTTA Opposition Extension & Submit Takedown Notice"]
    end

    subgraph Lane_System["Trademark Management System"]
        S1["Execute AI Phonetic & Visual Logo Clearance Search"]
        S2{"Passes AI Clearance & Marketplace Takedown Verification?"}
        S3["Flag High Confusion Risk & Propose Alternative Mark"]
        S4["Compile TEAS Application Package & Submit to USPTO"]
        S5["Submit Takedown Notice Payload & Update Brand Scorecard"]
    end

    subgraph Lane_APIs["USPTO TEAS & Marketplace APIs (TEAS / Amazon)"]
        G1["Transmit TEAS Application & Issue Serial # (US 98/884,120)"]
        G2["Execute Counterfeit Listing Takedown (ASIN B0884120XX Removed)"]
    end

    Finish(["End Trademark Enforcement Cycle"])

    Start --> B1 --> S1 --> S2
    S2 -->|"No / High Risk Obstacle"| S3 --> B2 --> C1
    S2 -->|"Yes / Cleared & Valid"| C1 --> S4 --> G1 --> C2 --> S5 --> G2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Brand Manager | Submits proposed brand wordmarks and vector logo designs for pre-filing clearance screening, reviews risk reports, and selects alternative brand proposals. |
| 2 | Global Trademark Counsel / Paralegal | Evaluates AI phonetic and computer vision logo clearance search hits, formats Nice Class goods descriptions, files ESTTA opposition extensions, and submits marketplace anti-counterfeiting takedown notices. |
| 3 | Trademark Management System | Executes AI computer vision logo searches across global registries, calculates trademark risk scores (0-100), validates evidentiary specimens of use, compiles TEAS application packages, monitors global trademark gazette publications, dispatches takedown API payloads, and computes brand protection scorecards. |
| 4 | USPTO TEAS & Marketplace APIs (TEAS / Amazon) | Ingests electronic TEAS application filings, issues official serial numbers (`US 98/884,120`) and registration certificates, and executes automated counterfeit listing removals on Amazon Brand Registry. |
