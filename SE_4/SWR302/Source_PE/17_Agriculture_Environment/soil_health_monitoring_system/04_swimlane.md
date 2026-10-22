# Swimlane Diagram — Soil Health Monitoring System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Soil Monitoring Cycle"])

    subgraph SensorLabLane["In-situ Soil IoT Probes & Testing Lab"]
        S1["Measure Soil NPK, pH, EC & Moisture"]
        S2["Stream 15-Min Telemetry Data via MQTT"]
        S3["Perform Lab SOM % & CEC Assays"]
    end

    subgraph SystemLane["Soil Health Monitoring System"]
        M1["Ingest & Calibrate Soil Telemetry Data"]
        M2["Compute Physical, Chemical & Biological Sub-Indices"]
        M3["Calculate Overall Soil Health Index (SHI)"]
        M4{"SHI < 55 or Severe Acidification/Salinization?"}
        M5["Generate VRA Lime & NPK Prescriptions"]
        M6["Model Soil Organic Carbon Sequestration Rate"]
    end

    subgraph AgronomistLane["Agronomist / Soil Scientist"]
        E1["Review Soil Constraint Parameters"]
        E2["Calibrate VRA Application Rates"]
        E3["Formulate Soil Remediation Plan"]
    end

    subgraph FarmerLane["Farmer / Agri-Producer"]
        F1["Review Soil Health Scorecard"]
        F2["Download VRA Prescription Shapefile"]
        F3["Implement Cover Crops & No-Till Practice"]
    end

    subgraph ApplicatorLane["Smart Applicator & Carbon Registry"]
        C1["Execute Precision VRA Spreading in Field"]
        C2["Verify Soil Organic Carbon Credits"]
    end

    Finish(["End Operational Cycle"])

    Start --> S1 --> S2 --> M1 --> S3 --> M2 --> M3 --> M4
    M4 -->|"Normal SHI >= 55"| M5 --> E2 --> F1 --> F2 --> C1 --> M6 --> C2 --> Finish
    M4 -->|"Degraded SHI < 55 / Acidification"| E1 --> E3 --> F3 --> M6 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | In-situ Soil IoT Probes & Testing Lab | Multi-depth submersible soil sondes measure root-zone chemical/physical metrics (NPK, pH, EC, VWC) and stream 15-minute telemetry over TLS MQTT, while wet-chemistry labs process Soil Organic Matter (SOM %) and CEC assays |
| 2 | Soil Health Monitoring System | Ingests and calibrates soil telemetry data, calculates physical, chemical, and biological sub-indices, computes overall Soil Health Index (SHI $0-100$), evaluates acidification/salinization risks, generates VRA prescription shapefiles, and models Soil Organic Carbon (SOC) accumulation |
| 3 | Agronomist / Soil Scientist | Reviews limiting soil constraint parameters, calibrates variable-rate lime and NPK application rates against target yield goals, formulates soil remediation plans, and validates carbon accounting models |
| 4 | Farmer / Agri-Producer | Views digital soil health scorecards, downloads georeferenced VRA fertilizer/lime prescription shapefiles, adopts regenerative practices (no-till, cover crops, biochar), and tracks long-term plot fertility |
| 5 | Smart Applicator & Carbon Registry | Tractor-mounted smart spreaders execute variable-rate lime/NPK application according to prescription shapefiles, while the carbon registry verifies soil organic carbon accumulation for carbon offset credits |
