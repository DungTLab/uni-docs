# Swimlane Diagram — Forestry Resource Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Forestry Management Cycle"])

    subgraph SensorSatelliteLane["Satellites, Drones & Fire Sensors"]
        S1["Stream Landsat/Sentinel Deforestation Alerts"]
        S2["Detect MODIS Thermal Wildfire Hotspots"]
        S3["Capture 3D Airborne LiDAR Point Clouds"]
    end

    subgraph SystemLane["Forestry Resource Management System"]
        M1["Ingest Canopy Loss & Compute FWI Rating"]
        M2{"Wildfire or Illegal Logging Alert?"}
        M3["Calculate Allowable Cut & Timber Volume"]
        M4["Generate FSC Barcode & Transport Pass"]
        M5["Model REDD+ Forest Carbon Stock"]
    end

    subgraph RangerLane["Forest Ranger / Field Officer"]
        R1["Receive Patrol Work Order Dispatch"]
        R2["Investigate Illegal Felling & Seize Logs"]
        R3["Execute Wildfire Suppression Dispatch"]
    end

    subgraph EnterpriseLane["Forest Enterprise Manager"]
        E1["Submit Annual Logging Quota Request"]
        E2["Attach Barcode Tags to Felled Timber Logs"]
        E3["Execute Reforestation Tree Planting"]
    end

    subgraph AuthorityLane["National Forestry Authority & Emergency Alert"]
        G1["Approve Timber Harvest Permit Quotas"]
        G2["Dispatch Emergency Wildfire Warnings"]
    end

    Finish(["End Operational Cycle"])

    Start --> S1 --> M1 --> M2
    S2 --> M1
    S3 --> M3
    M2 -->|"Wildfire FWI Extreme"| G2 --> R3 --> Finish
    M2 -->|"Deforestation Detected"| R1 --> R2 --> Finish
    M2 -->|"Harvest Plan Request"| E1 --> M3 --> G1 --> E2 --> M4 --> E3 --> M5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Satellites, Drones & Fire Sensors | Sentinel-2 and Landsat satellites stream GLAD/RADD canopy loss alerts, MODIS sensors detect thermal wildfire hotspots, and 3D airborne LiDAR drones capture tree height and biomass profiles |
| 2 | Forestry Resource Management System | Ingests satellite canopy loss feeds, computes Fire Weather Index (FWI) ratings, evaluates alert conditions, calculates Annual Allowable Cut (AAC) timber volumes, generates FSC barcodes, and models REDD+ carbon stocks |
| 3 | Forest Ranger / Field Officer | Receives emergency SMART patrol work orders, navigates to target satellite alert coordinates, investigates illegal logging stumps, seizes contraband timber, and leads wildfire suppression teams |
| 4 | Forest Enterprise Manager | Submits annual commercial logging quota applications, attaches physical barcode/RFID tags to felled timber logs at landing sites, and executes post-harvest reforestation tree planting |
| 5 | National Forestry Authority & Emergency Alert | Approves legal timber harvest quotas and FSC permits, and dispatches high-priority emergency wildfire warnings to local communities and fire brigades when FWI reaches Extreme |
