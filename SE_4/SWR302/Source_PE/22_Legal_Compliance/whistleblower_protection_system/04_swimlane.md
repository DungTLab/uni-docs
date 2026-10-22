# Swimlane Diagram — Whistleblower Protection System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Whistleblower Reporting & Retaliation Protection Lifecycle"])

    subgraph Lane_Whistleblower["Confidential Whistleblower"]
        W1["Submit Tip Report & Upload Evidence via Tor Portal"]
        W2["Use 16-Digit Access Token for Anonymous Two-Way Chat"]
    end

    subgraph Lane_Officer["Chief Ethics Officer / Forensic Investigator"]
        O1["Triage Pseudonymized Tip & Assign Forensic Team"]
        O2["Audit SHA-256 Vault & Disclose SOX Fraud to SEC"]
    end

    subgraph Lane_System["Whistleblower Protection System"]
        S1["Execute Zero-Knowledge Identity Pseudonymization & IP Stripping"]
        S2{"Passes Identity Anonymization & Retaliation Risk Check?"}
        S3["Flag Un-Anonymized Metadata Error & Strip EXIF Data"]
        S4["Apply Workday HR Lock & Generate SHA-256 Evidence Hash"]
        S5["Submit SEC Form TCR & Export Governance Scorecard"]
    end

    subgraph Lane_Gateways["Workday HR & SEC Gateways (Workday / SEC TCR API)"]
        G1["Freeze Workday HR File against Retaliatory Termination"]
        G2["Issue Official SEC Form TCR Receipt (TCR-2026-88412-001)"]
    end

    Finish(["End Whistleblower Protection Cycle"])

    Start --> W1 --> S1 --> S2
    S2 -->|"No / EXIF Data Present"| S3 --> S1
    S2 -->|"Yes / 100% Anonymized"| O1 --> G1 --> S4 --> W2 --> O2 --> G2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Confidential Whistleblower | Submits anonymous tips via Tor hidden services or encrypted web portals, receives 16-digit access tokens, and communicates anonymously with investigators via two-way encrypted chat. |
| 2 | Chief Ethics Officer / Forensic Investigator | Triages pseudonymized tip reports, enforces EU 7-day acknowledgment SLAs, conducts confidential witness interviews, audits SHA-256 forensic evidence chains of custody, and submits SEC Form TCR disclosures. |
| 3 | Whistleblower Protection System | Executes zero-knowledge identity pseudonymization, strips IP addresses and document EXIF metadata, assigns cryptographic aliases (`Informant #Alpha-88412`), applies Workday HR anti-retaliation locks, hashes evidence with SHA-256, tracks EU 3-month feedback SLAs, and exports ethics scorecards. |
| 4 | Workday HR & SEC Gateways (Workday / SEC TCR API) | Freezes Workday HR employee files against retaliatory demotions or terminations, and ingests statutory SEC Form TCR filings, issuing official SEC accession receipts (`TCR-2026-88412-001`). |
