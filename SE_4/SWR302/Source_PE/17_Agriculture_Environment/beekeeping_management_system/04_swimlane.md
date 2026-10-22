# Swimlane Diagram — Beekeeping Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Beekeeping Operational Cycle"])

    subgraph SensorSatelliteLane["Smart Beehives & Satellite Bloom Feeds"]
        S1["Measure Hive Weight, Brood Temp & Buzz Spectrum"]
        S2["Map Satellite Floral Bloom & Weather Feeds"]
    end

    subgraph SystemLane["Beekeeping Management System"]
        M1["Ingest Telemetry & Run Acoustic Swarm AI"]
        M2{"Sustained 450Hz Swarm Shift or Pesticide Warning?"}
        M3["Generate Nectar Flow Migration Plan"]
        M4["Register Honey Batch & QR Traceability Code"]
        M5["Dispatch Emergency Swarm or Pesticide Alarm"]
    end

    subgraph BeekeeperLane["Beekeeper / Apiary Manager"]
        B1["Execute Artificial Hive Split / Screen Hives"]
        B2["Execute Night Migration Transport"]
        B3["Harvest Honey Supers & Deliver to Extraction"]
    end

    subgraph ClientLane["Commercial Farmer (Pollination Client)"]
        C1["Contract Hive Pollination Service"]
        C2["Verify Colony Strength (Frames of Bees)"]
    end

    subgraph PlantVetLane["Honey Processing Plant & Vet Authority"]
        V1["Extract & Test Honey Moisture & HMF"]
        V2["Issue Veterinary Health Certificate"]
    end

    Finish(["End Operational Cycle"])

    Start --> S1 --> M1 --> M2
    S2 --> M1
    M2 -->|"Swarm Shift 450Hz / Pesticide Spray"| M5 --> B1 --> Finish
    M2 -->|"Normal Nectar Flow"| M3 --> B2 --> C1 --> C2 --> B3 --> V1 --> M4 --> V2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Smart Beehives & Satellite Bloom Feeds | Smart hive scale sondes measure total hive weight, brood temperature ($35^\circ C$), and acoustic buzz spectrum ($Hz$), while satellite remote sensing maps NDVI floral bloom availability |
| 2 | Beekeeping Management System | Ingests telemetry, executes FFT acoustic swarm detection AI models, evaluates pesticide spray buffer alerts, generates transhumance migration plans, and registers honey QR traceability barcodes |
| 3 | Beekeeper / Apiary Manager | Receives emergency pre-swarm acoustic alerts, performs artificial hive splits, executes night migratory hive transport to peak nectar flows, and harvests honey supers |
| 4 | Commercial Farmer (Pollination Client) | Contracts commercial beehive pollination services for crop bloom periods, verifies colony strength ($\ge 8$ frames of bees), and coordinates spray-free windows |
| 5 | Honey Processing Plant & Vet Authority | Extracts honey, tests moisture content ($<18.5\%$) and HMF levels ($<40\text{ mg/kg}$), verifies laboratory purity, and issues veterinary health certificates |
