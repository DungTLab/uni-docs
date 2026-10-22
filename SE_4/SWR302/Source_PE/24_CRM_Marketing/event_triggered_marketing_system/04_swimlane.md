# Swimlane Diagram — Event-Triggered Marketing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Real-Time Event Trigger Cycle"])

    subgraph Lane_User["End User"]
        U1["Perform Behavioral Action (Cart Abandon / App Open)"]
        U2["Receive Sub-Second Mobile Push Notification"]
    end

    subgraph Lane_Kafka["Event Streaming Bus (Kafka)"]
        K1["Publish Event JSON Payload to Kafka Topic"]
        K2["Acknowledge Partition Offset Receipt"]
    end

    subgraph Lane_System["Event-Triggered Marketing System"]
        S1["Ingest Event Payload & Enrich with CDP Profile"]
        S2["Evaluate In-Memory CEP Rule Matrix (< 100 ms)"]
        S3{"Passes Sub-Second CEP Rules & TCPA Quiet Hours?"}
        S4["Defer SMS or Log Suppression Event"]
        S5["Generate Single-Use Dynamic Promo Code"]
        S6["Package Action Payload & Measure Latency (< 500 ms)"]
    end

    subgraph Lane_Gateway["Execution Gateway (Mobile Push / SMS)"]
        G1["Receive API Action Payload & Deliver Push Alert"]
        G2["Return Execution Latency Webhook"]
    end

    Finish(["End Real-Time Event Dispatch Cycle"])

    Start --> U1 --> K1 --> K2 --> S1 --> S2 --> S3
    S3 -->|"No / Quiet Hours"| S4 --> Finish
    S3 -->|"Yes / Cleared"| S5 --> S6 --> G1 --> U2 --> G2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | End User | Performs real-time behavioral or transactional actions (app login, cart abandon, milestone reached), generating event telemetry and receiving sub-second push notifications. |
| 2 | Event Streaming Bus (Kafka) | Receives real-time event JSON payloads on active Kafka topics, acknowledges partition offsets, and streams messages to the system in sub-milliseconds. |
| 3 | Event-Triggered Marketing System | Ingests event payloads, merges customer CDP context, evaluates in-memory Complex Event Processing (CEP) rules ($< 100 \text{ ms}$), verifies TCPA quiet hours and rate limits, generates single-use dynamic promo codes, packages action payloads, and tracks sub-second SLA latency. |
| 4 | Execution Gateway (Mobile Push / SMS) | Receives action payloads via APIs (Firebase/Twilio), delivers instant iOS/Android mobile push notifications or SMS texts, and streams execution latency webhooks back to the system. |
