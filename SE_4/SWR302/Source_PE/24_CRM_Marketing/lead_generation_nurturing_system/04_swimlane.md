# Swimlane Diagram — Lead Generation & Nurturing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Inbound Lead Capture"])

    subgraph Lane_Prospect["Inbound Prospect"]
        P1["Submit Gated Whitepaper Web Lead Form"]
        P2["Receive Instant Email with PDF Download"]
    end

    subgraph Lane_System["Lead Generation & Nurturing System"]
        S1["Ingest Form Payload & Validate Syntax"]
        S2["Calculate Demographic Grade & Behavioral Score"]
        S3{"Is Total Lead Score >= MQL Threshold (80 Pts)?"}
        S4["Enroll Lead in Automated Nurture Drip Workflow"]
        S5["Route MQL to SDR & Push CRM Lead Payload"]
        S6["Start 5-Minute SDR Response SLA Countdown"]
    end

    subgraph Lane_Enrichment["B2B Data Enrichment API"]
        E1["Query Domain & Return Firmographic Company Data"]
    end

    subgraph Lane_SDR["Sales Development Rep (SDR)"]
        R1["Receive Real-Time MQL Alert & Contact Card"]
        R2["Initiate Outbound Call within 5-Min SLA Window"]
    end

    Finish(["End Lead Qualification Handoff Cycle"])

    Start --> P1 --> S1 --> E1 --> S2 --> S3
    S3 -->|"No / Under Threshold"| S4 --> P2 --> Finish
    S3 -->|"Yes / MQL Qualified"| S5 --> S6 --> R1 --> R2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Inbound Prospect | Fills out landing page web lead forms or submits instant social lead ads, receiving automated gated PDF download links and nurture communications. |
| 2 | Lead Generation & Nurturing System | Ingests lead payloads, validates email syntax, deduplicates entries, queries B2B enrichment APIs, computes dynamic lead scores, evaluates MQL thresholds (Score >= 80), enrolls unqualified leads in nurture drips, routes qualified MQLs via round-robin, and monitors 5-minute SDR response SLAs. |
| 3 | B2B Data Enrichment API | Receives corporate email domain queries (Clearbit/ZoomInfo API), searches enterprise firmographic databases, and returns company size, revenue, industry, and tech stack tags. |
| 4 | Sales Development Rep (SDR) | Receives real-time MQL desktop/mobile alerts and enriched CRM contact cards, placing outbound discovery calls to qualified prospects within the 5-minute SLA target. |
