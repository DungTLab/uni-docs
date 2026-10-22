# Swimlane Diagram — Corporate Secretary Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Corporate Governance & Subsidiary Filing Lifecycle"])

    subgraph Lane_Director["Board Director / Attorney-in-Fact"]
        D1["Review Board Resolution & Apply Cryptographic Digital Signature"]
        D2["Execute Commercial Contract under Active POA Signing Limit"]
    end

    subgraph Lane_Secretary["Corporate Secretary / Subsidiary Specialist"]
        S1["Compile Delaware Annual Report Data & Calculate Minimum Tax"]
        S2["Auto-Generate Certified Incumbency Certificate & Apply Seal"]
    end

    subgraph Lane_System["Corporate Secretary Management System"]
        Sys1["Execute Pre-Filing Data Validation & Calculate Franchise Tax"]
        Sys2{"Passes Pre-Filing Validation & Good Standing Check?"}
        Sys3["Flag Discrepancy Error & Block Electronic Submission"]
        Sys4["Transmit Filing Payload & Ingest Good Standing Receipt"]
        Sys5["Update Digital Minute Book & Refresh Entity Scorecard"]
    end

    subgraph Lane_Gateways["Delaware SOS & DocuSign Gateways (Delaware API / DocuSign)"]
        G1["Capture Cryptographic Digital Signature on Resolution Envelope"]
        G2["Issue Official Delaware Filing Receipt (Conf # DE-2027-884120)"]
    end

    Finish(["End Corporate Governance Cycle"])

    Start --> S1 --> Sys1 --> Sys2
    Sys2 -->|"No / Data Error or Lapsed Standing"| Sys3 --> S1
    Sys2 -->|"Yes / Validated & Good Standing"| D1 --> G1 --> S2 --> G2 --> Sys4 --> Sys5 --> D2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Board Director / Attorney-in-Fact | Reviews subsidiary board resolutions, applies cryptographic digital signatures to unanimous written consents (UWC), and executes commercial contracts within POA monetary signing caps. |
| 2 | Corporate Secretary / Subsidiary Specialist | Compiles annual report metadata, calculates Delaware franchise tax using minimum-tax algorithms, manages registered agent renewals, issues certified Incumbency Certificates, and applies corporate seal stamps. |
| 3 | Corporate Secretary Management System | Executes pre-filing validation checks, calculates franchise tax methods, transmits electronic annual report payloads to state registries via API, tracks Power of Attorney (POA) expiration dates, archives digital minute books, and updates global entity scorecards. |
| 4 | Delaware SOS & DocuSign Gateways (Delaware API / DocuSign) | Captures legally binding digital signatures on resolution envelopes via DocuSign API, processes state annual report filings and franchise tax payments, and issues official Certificates of Good Standing receipts. |
