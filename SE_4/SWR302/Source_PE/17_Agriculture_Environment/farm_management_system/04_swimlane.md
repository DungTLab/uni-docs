# Swimlane Diagram — Farm Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Inspection Flow"])

    subgraph Lane1["IoT Sensor Network"]
        S1["Measure Soil Moisture & Humidity"]
        S2["Detect Environmental Anomaly"]
    end

    subgraph Lane2["Farm Management System"]
        SYS1["Analyze Telemetry Data"]
        SYS2{"Pest Risk > Threshold?"}
        SYS3["Generate High Risk Pest Alert"]
        SYS4["Auto-Generate Chemical Spraying Task"]
        SYS5["Update Zone Health Status to Normal"]
    end

    subgraph Lane3["Farm Manager"]
        M1["Review Pest Alert & Recommendation"]
        M2{"Approve Spraying Task?"}
        M3["Cancel Alert & Log Reason"]
    end

    subgraph Lane4["Field Worker"]
        W1["Receive Mobile Task Notification"]
        W2["Retrieve Agrochemicals from Warehouse"]
        W3["Execute Spraying in Field Zone"]
        W4["Submit Photo & Dosage Log"]
    end

    Finish(["End Process"])

    Start --> S1 --> S2 --> SYS1 --> SYS2
    SYS2 -->|"Yes"| SYS3 --> M1 --> M2
    SYS2 -->|"No"| SYS5 --> Finish
    M2 -->|"Yes"| SYS4 --> W1 --> W2 --> W3 --> W4 --> SYS5 --> Finish
    M2 -->|"No"| M3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| IoT Sensor Network | Sensor Hardware | Continuously collects field soil moisture, ambient humidity, and temperature data; detects microclimatic anomalies conducive to fungal/pest outbreaks. |
| Farm Management System | Core Platform | Ingests telemetry metrics, executes threat detection algorithms against pre-set threshold rules, generates automated task dispatches, and updates zone health records upon task completion. |
| Farm Manager | Primary Supervisor | Evaluates system-generated pest risk alerts, reviews recommended pesticide dosages, approves or rejects chemical spraying tasks, or logs justification for manual dismissal. |
| Field Worker | Operational Staff | Receives push notifications on mobile devices, checks out required agrochemicals from inventory, performs spraying in target field zones, and logs dosage and photo confirmation to resolve the alert. |
