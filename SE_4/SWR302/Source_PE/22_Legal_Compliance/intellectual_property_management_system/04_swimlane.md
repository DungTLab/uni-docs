# Swimlane Diagram — Intellectual Property Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Intellectual Property Patent Lifecycle"])

    subgraph Lane_Inventor["Inventor / R&D Engineer"]
        I1["Submit Invention Disclosure Form (IDF) & Schematics"]
        I2["Review Patent Draft Claims & Approve Application"]
    end

    subgraph Lane_Attorney["Patent & Trademark Attorney"]
        A1["Review AI Prior Art Hits & Draft Application Claims"]
        A2["Draft Amendment Response to USPTO 35 U.S.C. 103 Rejection"]
    end

    subgraph Lane_System["Intellectual Property Management System"]
        S1["Apply Cryptographic Timestamp & Execute AI Prior Art Search"]
        S2{"Passes Patentability Review & Office Action Rejection?"}
        S3["Prune Patent Family & File Abandonment Notice"]
        S4["Docket Statutory 3-Month Office Action Deadline"]
        S5["Schedule & Pay Patent Family Annuity Maintenance Fees"]
    end

    subgraph Lane_Gateways["Patent Office & Annuity Gateways (USPTO / CPA Global)"]
        G1["Transmit Electronic Patent Application to USPTO PAIR"]
        G2["Issue Office Action Notice & Execute Annuity Fee Renewals"]
    end

    Finish(["End IP Asset Lifecycle"])

    Start --> I1 --> S1 --> A1 --> I2 --> G1 --> G2 --> S2
    S2 -->|"No / Un-Patentable or Abandoned"| S3 --> Finish
    S2 -->|"Yes / Rejection Overcome or Allowed"| A2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Inventor / R&D Engineer | Submits technical Invention Disclosure Forms (IDF), attaches schematics, receives SHA-256 proof-of-conception timestamps, reviews claim drafts, and approves patent filings. |
| 2 | Patent & Trademark Attorney | Analyzes AI prior art search results, drafts patent application claim specifications, amends claims in response to USPTO examiner rejections (35 U.S.C. 102/103), and files office action responses. |
| 3 | Intellectual Property Management System | Applies cryptographic timestamps to IDFs, executes AI prior art vector searches across 100M+ global patents, calculates AI Novelty Scores (0-100), dockets statutory 3-month USPTO office action deadlines, and automates global patent family annuity fee payments via CPA Global API. |
| 4 | Patent Office & Annuity Gateways (USPTO / CPA Global) | Ingests electronic XML/PDF patent applications (USPTO PAIR / EPO Online), issues official office action notices, processes issue fees, and executes global country patent maintenance fee payments. |
