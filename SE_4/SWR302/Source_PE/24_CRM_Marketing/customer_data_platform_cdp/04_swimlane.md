# Swimlane Diagram — Customer Data Platform (CDP)

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start CDP Data Unification & Activation"])

    subgraph Lane_Analyst["Marketing Analytics Specialist"]
        A1["Build Real-Time Dynamic Audience Segment Rules"]
        A2["Select Activation Target & Sync Schedule"]
    end

    subgraph Lane_CDP["Customer Data Platform (CDP)"]
        S1["Ingest Streaming SDK & Batch Snowflake Telemetry"]
        S2["Execute Identity Resolution & Stitch Device Cookies"]
        S3["Update 360-Degree Single Customer View SCV Profile"]
        S4{"Passes Identity Match & Privacy Consent Filters?"}
        S5["Suppress Opted-Out Profiles & Hash Identifiers SHA-256"]
        S6["Execute Reverse ETL Pipeline Push to Ad Networks"]
    end

    subgraph Lane_DataStores["Snowflake & CRM Systems"]
        D1["Stream Batch Transactions & CRM Customer Accounts"]
        D2["Receive Real-Time 360-Degree SCV Profile Sync"]
    end

    subgraph Lane_AdNetworks["Destination Ad Network APIs"]
        N1["Receive Reverse ETL SHA-256 Hashed Audience Payload"]
        N2["Match Profiles & Return Audience Match Rate Stats"]
    end

    Finish(["End CDP Activation Cycle"])

    Start --> D1 --> S1 --> S2 --> S3
    S3 -->|"No / Opted Out"| S5
    S3 -->|"Yes / Cleared"| S3 --> S3
    S1 --> S3 --> S5 --> S6 --> N1 --> N2 --> D2 --> Finish
    A1 --> A2 --> S6
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Marketing Analytics Specialist | Configures real-time dynamic audience segment rules, inspects 360-degree Single Customer View (SCV) profiles, selects destination activation targets, and schedules reverse ETL pipelines. |
| 2 | Customer Data Platform (CDP) | Ingests real-time streaming SDK clickstream events and batch Snowflake data, executes deterministic/probabilistic identity resolution, stitches cookies, maintains single customer view profiles, re-evaluates segment rules, enforces privacy consent filters, applies SHA-256 hashing, and executes reverse ETL dispatches. |
| 3 | Snowflake & CRM Systems | Supplies historical batch transaction tables and CRM sales account records to the CDP, receiving real-time 360-degree profile synchronization payloads. |
| 4 | Destination Ad Network APIs | Ingests reverse ETL SHA-256 hashed audience lists from the CDP (Meta Ads, Google Ads), matches profiles against network users, and returns match rate telemetry. |
