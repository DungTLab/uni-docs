# Swimlane Diagram — Account-Based Marketing (ABM) System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start ABM Target Account Campaign Lifecycle"])

    subgraph Lane_Buyer["Target Account Decision Maker"]
        B1["View IP-Targeted B2B Display Ad on WSJ Site"]
        B2["Click Ad & Visit Dynamic 1-to-1 Account Landing Page"]
    end

    subgraph Lane_AE["Enterprise Account Executive (AE)"]
        A1["Receive Real-Time Account Spike Alert in Slack"]
        A2["Launch Targeted 1-to-1 Outreach to CISO Persona"]
    end

    subgraph Lane_System["Account-Based Marketing System"]
        S1["Ingest Third-Party Intent Data & Perform Reverse IP Match"]
        S2["Calculate Composite Engagement Score & Map Buying Committee"]
        S3{"Passes Intent Surge & Buying Committee Coverage (> 75%)?"}
        S4["Maintain Baseline Ad Nurturing & Continue Intent Monitoring"]
        S5["Serve Personalized Page Tokens & Trigger Real-Time AE Alert"]
        S6["Calculate Multi-Touch Revenue Attribution & ABM ROMI"]
    end

    subgraph Lane_AdExchange["Programmatic Ad Exchange (Demandbase)"]
        E1["Serve Display Ads to Corporate IP Range (198.51.100.0/24)"]
        E2["Return Ad Impressions & Account Click Telemetry Webhooks"]
    end

    Finish(["End ABM Campaign Cycle"])

    Start --> S1 --> E1 --> B1 --> E2 --> S2 --> S3
    S3 -->|"No / Low Intent"| S4 --> Finish
    S3 -->|"Yes / High Intent"| B2 --> S5 --> A1 --> A2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Target Account Decision Maker | Views IP-targeted B2B display ads on premium publisher sites, clicks ad links, visits personalized 1-to-1 account web landing pages, and downloads case studies. |
| 2 | Enterprise Account Executive (AE) | Receives real-time Slack/email engagement spike alerts, reviews buying committee coverage maps, and executes targeted 1-to-1 outreach to unengaged decision-maker personas. |
| 3 | Account-Based Marketing System | Ingests third-party intent data (Bombora), performs reverse IP lookup (Demandbase), calculates composite account engagement scores (0-100), maps buying committee roles, serves dynamic page personalization tokens, dispatches AE alerts, and computes multi-touch ABM revenue attribution. |
| 4 | Programmatic Ad Exchange (Demandbase) | Serves programmatic display ads targeted exclusively at target company corporate IP ranges, returning ad impression and account click telemetry webhooks. |
