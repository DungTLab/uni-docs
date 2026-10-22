# Swimlane Diagram — Data Breach Response Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Cyber Breach Incident Response Lifecycle"])

    subgraph Lane_Forensic["Forensic Security Lead"]
        F1["Isolate Compromised Systems & Ingest SIEM Exfiltration Logs"]
        F2["Verify Containment & Submit Exfiltrated Database Schema"]
    end

    subgraph Lane_Counsel["Privacy Counsel / Incident Commander"]
        C1["Review Multi-Jurisdictional Breach Laws & Privilege Logs"]
        C2["Approve DPA Notification Filing & Mass Customer Email"]
    end

    subgraph Lane_System["Data Breach Response Management System"]
        S1["Activate 72-Hour Statutory GDPR Clock & Parse PII Records"]
        S2{"Passes PII Impact Threshold & DPA Notification Verification?"}
        S3["Flag Low Exposure Encrypted Safe Harbor & Continue Monitoring"]
        S4["Transmit Regulatory DPA Filing Payload & Ingest Receipt"]
        S5["Deploy Mass Customer Notification & Update Cyber Claim Ledger"]
    end

    subgraph Lane_External["Data Protection Regulators & Experian (DPA / Experian API)"]
        G1["Issue Official DPA Accession Receipt (IE-DPC-2026-88412)"]
        G2["Generate 23,000 Experian Credit Monitoring Redemption Codes"]
    end

    Finish(["End Breach Response Cycle"])

    Start --> F1 --> F2 --> S1 --> S2
    S2 -->|"No / Encrypted Safe Harbor"| S3 --> Finish
    S2 -->|"Yes / PII Compromised & Reportable"| C1 --> C2 --> S4 --> G1 --> G2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Forensic Security Lead | Isolates compromised network systems, ingests SIEM security alert logs, conducts forensic root cause analysis, and submits exfiltrated database schemas. |
| 2 | Privacy Counsel / Incident Commander | Evaluates multi-jurisdictional breach notification laws across 50 US States and GDPR, maintains attorney-client privilege logs, approves DPA notification filings, and signs off on mass customer notices. |
| 3 | Data Breach Response Management System | Activates real-time 72-hour statutory GDPR countdown clocks, parses exfiltrated database schemas to categorize compromised PII fields by jurisdiction, formats DPA filings, transmits electronic payloads via API, ingests Experian credit monitoring codes, deploys mass customer notifications, and calculates cyber insurance claim ledgers. |
| 4 | Data Protection Regulators & Experian (DPA / Experian API) | Ingests electronic breach notifications, issues official DPA accession receipts (`IE-DPC-2026-88412`), and generates 12-month Experian credit monitoring redemption codes for affected data subjects. |
