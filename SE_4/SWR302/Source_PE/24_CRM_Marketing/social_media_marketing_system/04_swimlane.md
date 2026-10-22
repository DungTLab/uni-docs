# Swimlane Diagram — Social Media Marketing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Social Post & Inbox Cycle"])

    subgraph Lane_Manager["Social Media Manager"]
        M1["Draft Cross-Platform Post & Attach Media"]
        M2["Schedule Publish Time & Tag Shoppable SKUs"]
        M3["Review Unified Inbox & Send Agent DM Reply"]
    end

    subgraph Lane_System["Social Media Marketing System"]
        S1["Validate Media Aspect Ratio & File Limits"]
        S2{"Passes Network API Media Validation?"}
        S3["Return Aspect Ratio Error & Prompt Crop"]
        S4["Queue Post in Unified Content Calendar"]
        S5["Dispatch Multi-Platform Payload at Scheduled Time"]
        S6["Ingest Inbound DM & Route to Unified Inbox Workspace"]
    end

    subgraph Lane_APIGateway["Social Network API Gateway"]
        A1["Ingest Post Payload & Publish to Feed"]
        A2["Return Publishing Success & Webhooks"]
    end

    subgraph Lane_Follower["Social Follower / Customer"]
        F1["View Published Post & Send Direct Message DM"]
        F2["Receive Agent Reply in Social Messenger App"]
    end

    Finish(["End Social Operation Cycle"])

    Start --> M1 --> M2 --> S1 --> S2
    S2 -->|"No"| S3 --> M1
    S2 -->|"Yes"| S4 --> S5 --> A1 --> A2 --> F1 --> S6 --> M3 --> F2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Social Media Manager | Drafts cross-platform posts, attaches image/video assets, tags shoppable e-commerce SKUs, schedules publishing dates, and responds to customer DMs in the unified inbox. |
| 2 | Social Media Marketing System | Validates media file sizes and aspect ratios, queues posts in the unified content calendar, dispatches multi-platform payloads to social APIs at scheduled times, ingests inbound DMs/comments, and routes them to the unified inbox. |
| 3 | Social Network API Gateway | Receives post publishing payloads (Meta Graph API, X API, TikTok API), publishes content to social feeds, and returns real-time publishing webhooks and engagement telemetry. |
| 4 | Social Follower / Customer | Views published social posts, interacts via comments or direct messages (DMs), and receives real-time agent or automated chatbot replies in their social messenger apps. |
