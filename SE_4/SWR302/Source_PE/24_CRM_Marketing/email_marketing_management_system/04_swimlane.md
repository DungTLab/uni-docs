# Swimlane Diagram — Email Marketing Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Mass Email Broadcast"])

    subgraph Lane_Specialist["Email Campaign Specialist"]
        S1["Select Audience Segment & HTML Template"]
        S2["Schedule Mass Broadcast Launch Time"]
    end

    subgraph Lane_System["Email Marketing Management System"]
        E1["Verify SPF/DKIM Domain Authentication"]
        E2["Filter Queue against Global Suppression List"]
        E3{"Is Recipient Opted-Out or Suppressed?"}
        E4["Bypass Dispatch & Suppress Queue Item"]
        E5["Sign Cryptographic DKIM Header & Queue Mail"]
        E6["Ingest Delivery Webhooks & Update Heatmaps"]
    end

    subgraph Lane_MTA["SMTP MTA Gateway"]
        M1["Execute Throttled SMTP Parallel Dispatches"]
        M2["Stream Real-Time Bounce & Open Webhooks"]
    end

    subgraph Lane_Recipient["Email Recipient / ISP"]
        R1["Deliver to Inbox & Render Interactive HTML"]
        R2["Click Link / Submit One-Click Unsubscribe"]
    end

    Finish(["End Broadcast Cycle"])

    Start --> S1 --> S2 --> E1 --> E2 --> E3
    E3 -->|"Yes"| E4 --> Finish
    E3 -->|"No / Clean"| E5 --> M1 --> R1 --> R2 --> M2 --> E6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Email Campaign Specialist | Selects audience segments, chooses responsive HTML templates, configures broadcast schedules, and approves final dispatches. |
| 2 | Email Marketing Management System | Verifies domain SPF/DKIM authentication, filters subscriber queues against global suppression lists, signs cryptographic DKIM headers, ingests delivery webhooks, updates heatmaps, and handles unsubscribes. |
| 3 | SMTP MTA Gateway | Receives MIME email payloads, executes throttled parallel SMTP dispatches according to IP warm-up rules, and streams real-time delivery, open, and bounce webhooks. |
| 4 | Email Recipient / ISP | Receives email in inbox, renders HTML layouts, interacts with links, and submits one-click unsubscribe requests or ISP feedback loop complaints. |
