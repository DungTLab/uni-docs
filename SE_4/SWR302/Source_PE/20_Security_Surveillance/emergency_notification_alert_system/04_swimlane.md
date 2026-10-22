# Swimlane Diagram — Emergency Notification & Alert System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Emergency Alert Launch"])

    subgraph Lane1["Incident Commander"]
        C1["Select Emergency Template & Target Geofence"]
        C2["Click Confirm & Launch Mass Broadcast"]
    end

    subgraph Lane2["System"]
        S1["Query Recipient Contacts in Target Geofence"]
        S2["Dispatch Parallel Multi-Channel Threads (SMS/Voice/Push)"]
        S3["Trigger Workstation Desktop Full-Screen Override"]
        S4["Stream Text-to-Speech Audio to PA Loudspeakers"]
        S5["Aggregate Real-Time Delivery Receipts & Safety Polls"]
    end

    subgraph Lane3["Telephony Gateway"]
        T1["Deliver High-Priority SMS & Mobile Push Alerts"]
    end

    subgraph Lane4["Facility Occupant"]
        O1["Receive Emergency Alert Notification"]
        O2["Tap 'I AM SAFE' or 'NEED ASSISTANCE' Button"]
    end

    subgraph Lane5["Evacuation Officer"]
        V1["Review Live Safety Roster & Missing Person List"]
        V2["Coordinate Rescue for 'Need Assistance' Occupants"]
    end

    Finish(["End Broadcast Cycle"])

    Start --> C1 --> C2 --> S1 --> S2 --> T1 --> O1 --> O2 --> S5 --> V1 --> V2 --> Finish
    S2 --> S3
    S2 --> S4
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Incident Commander | Selects pre-approved emergency templates, defines target geofence boundaries, and launches mass broadcasts. |
| 2 | System | Queries target contacts, dispatches multi-channel threads, forces desktop screen overrides, streams PA audio, and aggregates safety polls. |
| 3 | Telephony Gateway | Processes high-priority shortcode SMS dispatches and mobile push notifications, returning carrier delivery receipts. |
| 4 | Facility Occupant | Receives multi-channel emergency notifications on devices and submits 1-tap safety polling status ("I Am Safe"). |
| 5 | Evacuation Officer | Monitors live occupant safety dashboards, identifies unaccounted occupants, and coordinates emergency rescue for "Need Assistance" flags. |
