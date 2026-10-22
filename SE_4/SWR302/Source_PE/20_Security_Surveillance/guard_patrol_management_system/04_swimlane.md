# Swimlane Diagram — Guard Patrol Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Guard Patrol Tour Cycle"])

    subgraph Lane1["On-Duty Patrol Guard"]
        G1["Clock In Shift & Start Patrol Tour on Mobile App"]
        G2["Tap Mobile Phone against NFC Checkpoint Tag"]
        G3["Capture Photo of Broken Door Lock"]
    end

    subgraph Lane2["Guard Mobile App"]
        M1["Read Cryptographic NFC UID & Current GPS"]
        M2["Validate Checkpoint Sequence & Display Prompt"]
        M3["Transmit Incident Report Payload to System"]
    end

    subgraph Lane3["System"]
        S1["Stream Live GPS Breadcrumb to GIS Dashboard"]
        S2["Validate Tag UID & Clear SLA Countdown Timer"]
        S3["Detect Missed Checkpoint SLA Expiration"]
        S4["Sound Console Alarm & Render Red Alert"]
    end

    subgraph Lane4["Control Room Operator"]
        O1["Monitor Real-Time Guard GPS Breadcrumb Map"]
        O2["Radio Check Guard & Resolve SLA Breach"]
    end

    Finish(["End Patrol Tour Cycle"])

    Start --> G1 --> G2 --> M1 --> M2 --> S1 --> S2 --> G3 --> M3 --> O1 --> Finish
    S2 -->|"SLA Expired"| S3 --> S4 --> O2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | On-Duty Patrol Guard | Clocks in shift on mobile app, taps smartphone against physical NFC tags, captures field incident photos, and conducts patrols. |
| 2 | Guard Mobile App | Reads cryptographic NFC tag UIDs, validates checkpoint sequence, displays inspection prompts, captures GPS coordinates, and streams data. |
| 3 | System | Streams real-time guard GPS breadcrumb trails to control dashboards, clears SLA timers upon tag scans, and triggers alerts for missed checkpoints. |
| 4 | Control Room Operator | Monitors live guard tracking maps, responds to missed checkpoint SLA alerts, and conducts radio safety checks. |
