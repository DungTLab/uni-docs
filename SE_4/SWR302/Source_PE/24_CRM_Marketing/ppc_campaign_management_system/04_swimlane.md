# Swimlane Diagram — PPC Campaign Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start PPC Campaign Launch"])

    subgraph Lane_Creative["Ad Creative Specialist"]
        C1["Compose RSA Headlines & Description Variations"]
        C2["Verify Ad Strength Score Rating"]
    end

    subgraph Lane_Buyer["PPC Media Buyer"]
        B1["Structure Campaign Ad Groups & Keyword Bids"]
        B2["Set Negative Keywords & Smart Bidding Rules"]
    end

    subgraph Lane_System["PPC Campaign Management System"]
        S1["Execute Automated Negative Conflict Scan"]
        S2{"Passes Negative Conflict & Ad Strength Tests?"}
        S3["Flag Conflict Error & Block Ad Dispatch"]
        S4["Package Campaign Payload & Encrypt Hash Tokens"]
        S5["Detect Bot Clicks & Dispatch IP Block List"]
        S6["Ingest Impression Telemetry & Compute ROAS"]
    end

    subgraph Lane_GoogleAPI["Google Ads API"]
        G1["Receive Campaign Creation Payload"]
        G2["Execute Auction Bidding & Return Click Webhooks"]
    end

    Finish(["End PPC Campaign Operations Cycle"])

    Start --> C1 --> C2 --> B1 --> B2 --> S1 --> S2
    S2 -->|"No"| S3 --> B1
    S2 -->|"Yes / Clean"| S4 --> G1 --> G2 --> S5 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Ad Creative Specialist | Composes Responsive Search Ad (RSA) headlines and descriptions, verifies Ad Strength ratings, and configures ad extension assets. |
| 2 | PPC Media Buyer | Structures search/display campaigns, assigns keyword match types, configures negative keyword lists, and sets Smart Bidding strategy rules (Target CPA/ROAS). |
| 3 | PPC Campaign Management System | Executes automated negative keyword conflict scans, validates Ad Strength thresholds, packages campaign API dispatches, detects click fraud bot activity, pushes IP exclusions, and computes real-time ROAS metrics. |
| 4 | Google Ads API | Ingests campaign structure payloads, executes auction bidding for search queries, dispatches ad impressions, and streams click/cost/conversion telemetry webhooks back to the system. |
