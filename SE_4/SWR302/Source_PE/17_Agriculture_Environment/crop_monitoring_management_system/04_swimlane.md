# Swimlane Diagram — Crop Monitoring & Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Monitoring Cycle"])

    subgraph Lane1["Satellite & Drone Imaging Service"]
        IMG1["Capture Multispectral Satellite Raster"]
        IMG2["Transmit Raw Spectral Band Data"]
    end

    subgraph Lane2["Crop Monitoring System"]
        SYS1["Compute NDVI & Vegetation Stress Index"]
        SYS2{"NDVI Score < 0.35 threshold?"}
        SYS3["Flag Parcel Sub-Zone as Stressed"]
        SYS4["Run AI Diagnostic Inference on Leaf Images"]
        SYS5["Generate Fertilizer & Pest Prescription"]
        SYS6["Update Crop Health Map to Normal"]
    end

    subgraph Lane3["Agronomist"]
        AG1["Review Stress Heatmap & AI Diagnosis"]
        AG2{"Confirm AI Pest Diagnosis?"}
        AG3["Manually Re-classify Disease Profile"]
        AG4["Approve Chemical Dosage Advisory"]
    end

    subgraph Lane4["Field Operator"]
        OP1["Receive Field Treatment Task"]
        OP2["Apply Prescribed Agrochemical Dosage"]
        OP3["Log Chemical Application & Photo Proof"]
    end

    Finish(["End Process"])

    Start --> IMG1 --> IMG2 --> SYS1 --> SYS2
    SYS2 -->|"Yes"| SYS3 --> SYS4 --> AG1 --> AG2
    SYS2 -->|"No"| SYS6 --> Finish
    AG2 -->|"Yes"| AG4 --> SYS5 --> OP1 --> OP2 --> OP3 --> SYS6 --> Finish
    AG2 -->|"No"| AG3 --> AG4
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| Satellite & Drone Imaging Service | Remote Sensing Provider | Captures macro-level multispectral satellite bands and micro-level drone flight images over target farm parcel coordinates. |
| Crop Monitoring System | Automated Core Platform | Processes raw spectral feeds to compute NDVI indices, evaluates vegetation stress thresholds, triggers AI diagnostic models, formulates chemical advisories, and maintains live field health maps. |
| Agronomist | Technical Crop Expert | Analyzes automated stress alerts, verifies AI-generated pest disease diagnoses, adjusts chemical dosage formulations, and authorizes treatment dispatches. |
| Field Operator | Ground Execution Staff | Receives approved chemical treatment tasks on mobile devices, applies prescribed pesticides/fertilizers in the target field zone, and submits execution logs with photo proof. |
