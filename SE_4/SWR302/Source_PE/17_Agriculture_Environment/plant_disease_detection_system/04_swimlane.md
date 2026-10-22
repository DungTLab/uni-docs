# Swimlane Diagram — Plant Disease Detection System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Scan Process"])

    subgraph FarmerLane["Farmer / Crop Grower"]
        A1["Capture Leaf Photo & GPS"]
        A2["Submit Scan for Diagnostic"]
        A3["Review Diagnosis & Dosage"]
        A4["Order Chemical & Apply Treatment"]
        A5["File Expert Consultation Ticket"]
    end

    subgraph SystemLane["Plant Disease Detection System"]
        S1["Preprocess Leaf Image & Quality Check"]
        S2["Fetch IoT Sensor Climate Context"]
        S3{"Image Quality Valid?"}
        S4["Score Infection Severity Index"]
        S5["Generate Automated Treatment Plan"]
    end

    subgraph AIEngineLane["AI Vision Diagnostic Engine"]
        E1["Run Neural Network Feature Extraction"]
        E2["Classify Pathogen Species"]
        E3{"Confidence >= 85%?"}
    end

    subgraph AgronomistLane["Agronomist / Plant Pathologist"]
        G1["Receive Inconclusive Scan Queue"]
        G2["Inspect Visual Leaf Symptoms"]
        G3["Validate Disease & Prescribe Remedy"]
    end

    subgraph ExtensionLane["Extension Officer"]
        X1["Monitor Regional Epidemic Map"]
        X2["Issue Community Outbreak Warning"]
    end

    Finish(["End Process"])

    Start --> A1 --> A2 --> S1 --> S3
    S3 -->|"No"| A1
    S3 -->|"Yes"| S2 --> E1 --> E2 --> E3
    E3 -->|"Yes"| S4 --> S5 --> A3 --> A4 --> Finish
    E3 -->|"No"| G1 --> G2 --> G3 --> S5
    A3 -->|"Unclear / Recurring"| A5 --> G2
    S5 --> X1 --> X2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Farmer / Crop Grower | Captures geotagged leaf images via mobile app, submits scan requests, reviews automated diagnosis and dosage instructions, orders agrochemicals, applies treatment, and logs recovery progress or files expert tickets |
| 2 | Plant Disease Detection System | Handles image quality preprocessing, ingests local microclimate IoT sensor telemetry, coordinates AI microservice execution, computes infection severity, generates treatment plans, and triggers outbreak alerts |
| 3 | AI Vision Diagnostic Engine | Ingests preprocessed leaf images, executes deep learning vision inference models, classifies disease pathogens, calculates bounding boxes, and outputs diagnostic confidence scores |
| 4 | Agronomist / Plant Pathologist | Manages low-confidence or high-risk disease scan queues, conducts clinical visual inspection, confirms or corrects pathogen classifications, and formulates specialized treatment prescriptions |
| 5 | Extension Officer | Tracks regional disease density heatmaps, identifies emerging epidemic outbreaks, and issues targeted community advisories to contain local crop disease spread |
