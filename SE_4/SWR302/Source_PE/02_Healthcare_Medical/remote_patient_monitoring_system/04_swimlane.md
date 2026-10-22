# Swimlane Diagram — Remote Patient Monitoring System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Continuous RPM Monitoring"])

    subgraph Lane1["Patient & IoT Wearables"]
        A1["Wear BLE Sensors & Perform Daily Vital Readings"]
        A2["Join WebRTC Telehealth Visit & Follow Instructions"]
    end

    subgraph Lane2["RPMS Gateway & AI Engine"]
        S1["Ingest Real-Time Vital Telemetry & Filter Noise"]
        S2{"Vital Sign Exceeds Critical Threshold?"}
        S3["Log Normal Vital Baseline & Update Dashboard"]
        S4["Generate Red Alert & Dispatch Multi-Channel Push"]
        S5["Sync Clinical SOAP Note to Hospital EHR via FHIR"]
    end

    subgraph Lane3["Remote Care Manager Nurse"]
        B1["Acknowledge Triage Alert & Review Vital Trends"]
        B2["Launch Virtual Telehealth Visit & Assess Patient"]
    end

    subgraph Lane4["Attending Physician & EMS"]
        C1["Approve Emergency EMS Dispatch & Adjust Care Plan"]
        C2["Paramedic Unit Dispatched to Patient GPS Location"]
    end

    Finish(["End Incident & Resume Baseline Monitoring"])

    Start --> A1 --> S1 --> S2
    S2 -->|"No"| S3 --> Finish
    S2 -->|"Yes"| S4 --> B1 --> B2 --> A2 --> C1 --> C2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient & IoT Wearables | Wears cellular/BLE medical sensors, performs daily vital sign measurements (BP, SpO2, weight), receives alert notifications, and participates in virtual telehealth calls. |
| 2 | RPMS Gateway & AI Engine | Continuously ingests vital sign telemetry streams, applies noise filtering, checks values against personalized thresholds, generates multi-channel alerts, and syncs FHIR summaries to EHR. |
| 3 | Remote Care Manager Nurse | Monitors real-time clinical triage dashboard, acknowledges vital alerts within SLA, launches emergency WebRTC telehealth video visits, and logs SOAP progress notes. |
| 4 | Attending Physician & EMS | Reviews critical arrhythmia/deterioration alerts, approves emergency EMS dispatch payloads, adjusts clinical care plan orders, and signs off on monthly billing logs. |
