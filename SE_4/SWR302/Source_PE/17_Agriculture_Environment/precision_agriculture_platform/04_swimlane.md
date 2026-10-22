# Swimlane Diagram — Precision Agriculture Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Precision Operation"])

    subgraph Lane1["Agronomist / Farm Manager"]
        A1["Define Field GIS Boundaries & Soil Zones"]
        A2["Review Crop Vigor & NDVI Anomaly Alerts"]
        A3["Compute Variable Rate (VRA) Prescription"]
        A4["Approve & Sign VRA Task Map"]
    end

    subgraph Lane2["IoT & Drone Sensor Network"]
        B1["Ingest Real-time Soil Moisture & Weather"]
        B2["Upload Multispectral Drone Imagery Tile"]
    end

    subgraph Lane3["Precision Platform Engine"]
        C1["Process NDVI & Soil Fertility Rasters"]
        C2{"NDVI Anomaly or Moisture Below Threshold?"}
        C3["Convert VRA Prescription to ISO-XML Format"]
        C4["Trigger Automated Irrigation Valve Signal"]
    end

    subgraph Lane4["Variable Rate Machinery Operator"]
        D1["Receive ISO-XML Prescription on Tractor Terminal"]
        D2["Execute Variable Rate Chemical Spraying Field Task"]
        D3["Transmit Telematics & Actual Rate Coverage Logs"]
    end

    subgraph Lane5["Environmental Auditor"]
        E1["Receive Nitrogen Application & Water Logs"]
        E2["Audit Compliance Against Ecological Quotas"]
    end

    Finish(["End Operation Lifecycle"])

    Start --> A1 --> B1
    B2 --> C1 --> C2
    B1 --> C2
    C2 -->|"Yes (Stress Detected)"| A2 --> A3 --> A4 --> C3
    C2 -->|"Moisture Deficit"| C4 --> Finish
    C2 -->|"No (Normal Vigor)"| Finish
    C3 --> D1 --> D2 --> D3 --> E1 --> E2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Agronomist / Farm Manager | Defines field GIS boundaries, monitors crop vigor alerts, computes VRA chemical prescriptions, and signs task maps. |
| 2 | IoT & Drone Sensor Network | Collects real-time ground soil moisture/weather data and captures high-resolution multispectral aerial imagery. |
| 3 | Precision Platform Engine | Core analytics engine computing NDVI rasters, evaluating anomaly thresholds, firing automated irrigation signals, and rendering ISO-XML maps. |
| 4 | Variable Rate Machinery Operator | Loads ISO-XML task files onto tractor terminals, drives field spraying routes, and streams actual applied rate telematics. |
| 5 | Environmental Auditor | Inspects logged chemical application rates and water extraction volumes to verify compliance with environmental conservation rules. |
