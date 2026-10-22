# Swimlane Diagram — Elderly Care Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Daily Elderly Care Monitoring"])

    subgraph Lane1["Elderly Resident & Family Proxy"]
        A1["Wear BLE SOS Pendant & Complete Daily Mood Check-In"]
        A2["Review Family Portal Daily Care Summary & Photos"]
    end

    subgraph Lane2["Geriatric Care Nurse & Staff"]
        B1["Perform ADL Assessment & Administer eMAR Medications"]
        B2["Assess Post-Fall Trauma & Dispatch 911 EMS"]
    end

    subgraph Lane3["ECMS Core & IoT Fall Radar Engine"]
        S1["Process mmWave Fall Radar & BLE Vitals Telemetry"]
        S2{"Fall Incident or Acute Vitals Crash Detected?"}
        S3["Log Routine Care Compliance & Update eMAR Record"]
        S4["Trigger Red Fall Alert Siren & Flash Nurse Station"]
        S5["Sync FHIR Health Summary & LTC Insurance Billing"]
    end

    subgraph Lane4["Assisted Living Admin & LTC Insurance"]
        C1["Adjudicate Monthly LTC Insurance & Medicaid Claims"]
    end

    Finish(["End Shift Care Cycle & Sync Family Portal Log"])

    Start --> A1 --> S1 --> S2
    S2 -->|"No"| B1 --> S3 --> A2 --> S5 --> C1 --> Finish
    S2 -->|"Yes"| S4 --> B2 --> S5 --> C1 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Elderly Resident & Family Proxy | Wears BLE SOS pendant, completes daily mood check-in, receives personal hygiene assistance, and views daily care updates/photos on the family portal app. |
| 2 | Geriatric Care Nurse & Staff | Performs Barthel Index ADL assessments, administers scheduled eMAR medications using barcode scanners, responds to fall alerts, and performs post-fall trauma care. |
| 3 | ECMS Core & IoT Fall Radar Engine | Processes continuous mmWave ceiling fall radar data, detects bed exit/fall incidents, triggers red siren alerts on nurse stations, and syncs FHIR summaries. |
| 4 | Assisted Living Admin & LTC Insurance | Manages nursing home room allocations, monitors care task compliance SLAs, and submits monthly Long-Term Care (LTC) insurance claims to payers. |
