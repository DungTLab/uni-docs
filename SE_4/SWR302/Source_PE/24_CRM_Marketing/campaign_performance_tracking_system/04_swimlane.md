# Swimlane Diagram — Campaign Performance Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Campaign Tracking Cycle"])

    subgraph Lane_MediaSpec["Paid Media Specialist"]
        M1["Generate Standardized UTM Tracking Short Links"]
        M2["Deploy Ad Creatives on Meta & Google Ads"]
    end

    subgraph Lane_System["Campaign Performance Tracking System"]
        S1["Ingest Multi-Channel Daily API Ad Spend & Clicks"]
        S2["Map E-Commerce Checkout Orders to Campaign Links"]
        S3{"Passes ROAS Hurdle & Budget Pacing (< 110%)?"}
        S4["Flag Over-Spend Pacing & Dispatch Alert Notification"]
        S5["Dispatch SHA-256 Encrypted Server CAPI Webhooks"]
        S6["Calculate Real-Time ROAS & Render Scorecards"]
    end

    subgraph Lane_AdNetworks["Digital Ad Network APIs"]
        A1["Stream Daily API Spend, Clicks & Impressions"]
        A2["Receive Server CAPI Webhooks & Match Conversions"]
    end

    subgraph Lane_CMO["Chief Marketing Officer (CMO)"]
        C1["Review Channel ROAS Heatmaps & Re-Allocate Budget"]
        C2["Sign Off Executive Campaign Performance Scorecard"]
    end

    Finish(["End Performance Tracking Cycle"])

    Start --> M1 --> M2 --> S1 --> A1 --> S2 --> S3
    S3 -->|"No / Over-Spend"| S4 --> C1
    S3 -->|"Yes / Approved"| S5 --> A2 --> S6 --> C1 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Paid Media Specialist | Builds standardized UTM tracking short links, deploys ad creatives across Google/Meta/LinkedIn, and configures campaign parameter tags. |
| 2 | Campaign Performance Tracking System | Ingests daily ad spend APIs, maps e-commerce checkout orders to campaign links, evaluates ROAS multipliers, monitors budget pacing ($< 110\%$), dispatches SHA-256 encrypted server-side CAPI webhooks, and renders campaign scorecards. |
| 3 | Digital Ad Network APIs | Streams daily ad spend, impression, and click telemetry via APIs, ingesting server-side CAPI conversion webhooks to optimize ad targeting. |
| 4 | Chief Marketing Officer (CMO) | Reviews channel ROAS heatmaps, re-allocates quarterly marketing budgets to high-performing channels, and signs off on executive campaign scorecards. |
