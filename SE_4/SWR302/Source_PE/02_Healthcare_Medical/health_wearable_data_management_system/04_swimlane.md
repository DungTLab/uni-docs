# Swimlane Diagram — Health Wearable Data Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Wearable Data Ingestion & Monitoring"])

    subgraph Lane1["Consumer Wearable User"]
        A1["Pair BLE Smartwatch or CGM Sensor"]
        A2["View Vitals Dashboard & Receive Alerts"]
    end

    subgraph Lane2["Remote RPM Nurse / Doctor"]
        B1["Configure Customized Vital Threshold Rules"]
        B2["Review ECG PDF Strip & Log Clinical Action"]
    end

    subgraph Lane3["System / IoT & AI Telemetry Engine"]
        S1["Ingest BLE Stream & Calculate 24-Hr Vitals"]
        S2{"AFib / Hypo / Hard Fall Anomaly Detected?"}
        S3["Update Vitals Dashboard & Log History"]
        S4["Sound Critical Alarm & Start 30s Cancel Timer"]
        S5["Transform Vitals into HL7 FHIR Observation"]
    end

    subgraph Lane4["EMS Dispatch & Emergency Team"]
        C1["Receive EMS Dispatch Payload & GPS Position"]
        C2["Dispatch 115 Ambulance to Patient Location"]
    end

    Finish(["End Clinical Response & Safety Cycle"])

    Start --> A1 --> B1 --> S1 --> S2
    S2 -->|"No"| S3 --> A2 --> S5
    S2 -->|"Yes"| S4 --> C1 --> C2 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Consumer Wearable User | Pairs Bluetooth smartwatches or medical CGM sensors, sets health goals, views real-time vital sign dashboards, receives wellness insights, and manages data consent. |
| 2 | Remote RPM Nurse / Doctor | Configures patient-specific vital alarm thresholds, triages RPM alarm queues, reviews 6-Lead ECG PDF strips, logs clinical actions, and exports CPT billing reports. |
| 3 | System / IoT & AI Telemetry Engine | Ingests real-time BLE/cloud telemetry, executes AI arrhythmia (AFib) and hypoglycemia detection algorithms, sounds 30-second emergency cancel timers, and maps FHIR resources. |
| 4 | EMS Dispatch & Emergency Team | Receives automated emergency dispatch payloads (GPS coordinates, live vitals, ECG strip), dispatches 115 ambulances, and confirms incident ticket numbers. |
