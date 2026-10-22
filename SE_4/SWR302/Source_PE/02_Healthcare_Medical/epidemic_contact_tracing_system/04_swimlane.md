# Swimlane Diagram — Epidemic Contact Tracing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Epidemic Contact Tracing Cycle"])

    subgraph Lane1["Index Case / Exposed Contact"]
        A1["Provide Interview Timeline & Venue Check-Ins"]
        A2["Receive Exposure Alert & Log Daily Vitals"]
    end

    subgraph Lane2["Contact Tracing Officer / CDC"]
        B1["Map F0 Exposure Timeline & Infectious Window"]
        B2["Categorize F1 F2 Contacts & Build Tree Graph"]
    end

    subgraph Lane3["System / ECTS & Matching Engine"]
        S1["Query Venue QR & Flight Manifest Overlaps"]
        S2{"F1 PCR Test Result Positive?"}
        S3["Auto-Upgrade F1 to F0 & Cascade Linked F2s"]
        S4["Dispatch Zalo SMS Push & Issue PCR Barcode"]
        S5["Monitor Geofence Radius & Log 50m Breaches"]
    end

    subgraph Lane4["Quarantine Officer & PHEOC"]
        C1["Assign Home vs Facility Quarantine Order"]
        C2["Verify 14-Day Check-Ins & Issue Clearance"]
    end

    Finish(["End Contact Quarantine & Clearance Cycle"])

    Start --> A1 --> B1 --> S1 --> B2 --> C1 --> S4 --> A2 --> S5 --> S2
    S2 -->|"Yes"| S3 --> B1
    S2 -->|"No"| C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Index Case / Exposed Contact | Provides exposure timeline details during interviews, receives Zalo/SMS exposure alerts, logs daily temperature check-ins on mobile app, and honors quarantine. |
| 2 | Contact Tracing Officer / CDC | Registers confirmed F0 index cases, calculates infectious window, interviews index cases, categorizes exposed contacts into F1 and F2 risk tiers, and builds transmission trees. |
| 3 | System / ECTS & Matching Engine | Runs spatio-temporal venue QR check-in overlap analysis, dispatches automated exposure push alerts, monitors 50-meter GPS geofences, and auto-upgrades positive F1s to F0 status. |
| 4 | Quarantine Officer & PHEOC | Assigns centralized facility vs home quarantine orders, enforces isolation rules, monitors daily temperature logs, audits tracing speed SLAs, and issues clearance certificates. |
