# Swimlane Diagram — Omnichannel Customer Support System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Omnichannel Customer Support Cycle"])

    subgraph Lane_Customer["End Customer"]
        C1["Submit Support Request via WhatsApp or Voice Call"]
        C2["Receive AI Auto-Response Answer & Confirm Resolution"]
        C3["Click 5-Star Rating on Post-Resolution CSAT Survey"]
    end

    subgraph Lane_Agent["Omnichannel Customer Support Agent"]
        A1["Receive Softphone Screen Pop & Customer 360 View"]
        A2["Apply Canned Macro Response & Solve Ticket"]
    end

    subgraph Lane_System["Omnichannel Customer Support System"]
        S1["Normalize Ticket Record & Query AI RAG Knowledge Base"]
        S2{"Passes AI Self-Service Deflection (> 85% Relevance)?"}
        S3["Mark Ticket Deflected & Log Self-Service Analytics"]
        S4["Execute Skills-Based Routing & Screen Pop Payload"]
        S5["Dispatch Post-Resolution 1-Click CSAT Survey"]
    end

    subgraph Lane_Gateways["Telephony & Messaging Gateways"]
        G1["Ingest Multi-Channel Webhook (Twilio / WhatsApp API)"]
        G2["Deliver Agent Message & CSAT Survey Payload"]
    end

    Finish(["End Omnichannel Support Cycle"])

    Start --> C1 --> G1 --> S1 --> S2
    S2 -->|"Yes / Deflected"| C2 --> S3 --> Finish
    S2 -->|"No / Escalated"| S4 --> A1 --> A2 --> S5 --> G2 --> C3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | End Customer | Submits support inquiries via WhatsApp, voice call, or email, receives AI auto-responder answers, confirms issue resolution, or interacts with agents and rates post-resolution CSAT surveys. |
| 2 | Omnichannel Customer Support Agent | Receives real-time softphone screen pops and Customer 360 timelines, applies pre-approved canned macro responses, and marks tickets as Solved. |
| 3 | Omnichannel Customer Support System | Normalizes multi-channel tickets into a single queue, queries AI RAG knowledge bases for auto-deflection, evaluates deflection confidence thresholds, executes skills-based agent routing, dispatches post-resolution CSAT surveys, and updates CSAT scorecards. |
| 4 | Telephony & Messaging Gateways | Ingests multi-channel webhooks (Twilio/WhatsApp Business/SendGrid), connects voice call audio trunks, and delivers outbound agent messages and CSAT survey payloads. |
