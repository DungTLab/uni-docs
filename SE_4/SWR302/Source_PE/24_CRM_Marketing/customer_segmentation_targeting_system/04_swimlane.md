# Swimlane Diagram — Customer Segmentation & Targeting System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Audience Segmentation Workflow"])

    subgraph Lane_Analyst["Data Analyst"]
        A1["Execute k-Means ML Clustering Model"]
        A2["Calculate RFM Quintile Scores & Personas"]
    end

    subgraph Lane_Manager["Marketing Campaign Manager"]
        M1["Build Multi-Condition Dynamic Segment Rules"]
        M2["Select Destination Channel for Segment Export"]
    end

    subgraph Lane_System["Customer Segmentation System"]
        S1["Ingest CDP Events & Run RFM/ML Algorithms"]
        S2["Query Customer Base & Render Segment Preview"]
        S3{"Passes Privacy Consent Filter (GDPR/CCPA)?"}
        S4["Suppress Non-Consented Contacts from Queue"]
        S5["Apply SHA-256 Hashing to Cleared Contacts"]
        S6["Push Segment Tags to CRM & Marketing Automation"]
    end

    subgraph Lane_AdNetwork["Ad Network Gateway"]
        N1["Receive SHA-256 Encrypted Hashed Payload"]
        N2["Match Profiles & Update Custom Audience Size"]
    end

    Finish(["End Segmentation Export Cycle"])

    Start --> A1 --> A2 --> S1 --> M1 --> S2 --> M2 --> S3
    S3 -->|"No / Opted Out"| S4 --> S5
    S3 -->|"Yes / Cleared"| S5 --> N1 --> N2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Data Analyst | Executes unsupervised k-Means machine learning cluster models, calculates RFM quintile scores, and defines customer persona categories. |
| 2 | Marketing Campaign Manager | Configures multi-condition dynamic behavioral segment rules, reviews segment membership previews, and selects target destination channels for campaign export. |
| 3 | Customer Segmentation System | Ingests unified CDP behavioral events, calculates dynamic segment rules, previews audience matches, enforces channel-specific GDPR/CCPA privacy consent filters, applies SHA-256 email hashing, and updates segment tags across connected systems. |
| 4 | Ad Network Gateway | Ingests secure SHA-256 hashed audience lists from the system via HTTPS APIs, matches profiles against ad network users, and updates custom retargeting audience sizes. |
