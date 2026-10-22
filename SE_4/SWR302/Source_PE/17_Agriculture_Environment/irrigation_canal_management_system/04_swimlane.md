# Swimlane Diagram — Irrigation Canal Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Canal Water Management Cycle"])

    subgraph SensorGateLane["Sensors, SCADA Gates & Salinity Sondes"]
        S1["Measure Canal Water Depth & Flow Velocity"]
        S2["Measure Estuarine Salinity (ppt)"]
        S3["Feedback Sluice Gate Height Position"]
    end

    subgraph SystemLane["Irrigation Canal Management System"]
        M1["Ingest Telemetry & Run 1D Saint-Venant Model"]
        M2{"Estuarine Salinity >1.0 ppt or Flood Overtopping?"}
        M3["Calculate PID Gate Opening & Turnout Delivery"]
        M4["Calculate WUA Volumetric Billing Invoice"]
        M5["Trigger Emergency Tidal Gate Closure & Flood Alert"]
    end

    subgraph EngineerLane["Canal Hydraulic Engineer / Operator"]
        E1["Review 1D Hydrodynamic Flow & Head Loss"]
        E2["Approve Seasonal Water Allocation Plan"]
        E3["Dispatch Canal Desilting Work Order"]
    end

    subgraph WUALane["Water User Association (WUA) Manager"]
        W1["Submit Seasonal Crop Water Demand"]
        W2["Request Farm Turnout Water Order"]
        W3["Pay Volumetric Irrigation Water Fee"]
    end

    subgraph SCADAFloodLane["SCADA Motor Actuators & Flood Alert Gateway"]
        C1["Adjust Motorized Sluice Gate Height"]
        C2["Close Estuarine Barrier Gates 100%"]
        C3["Broadcast Canal Overtopping Flood Alarm"]
    end

    Finish(["End Operational Cycle"])

    Start --> S1 --> M1 --> M2
    S2 --> M1
    S3 --> M1
    M2 -->|"Salinity > 1.0 ppt / Flood Risk"| M5 --> C2 --> C3 --> Finish
    M2 -->|"Normal Flow Conditions"| W1 --> E2 --> M3 --> C1 --> W2 --> M4 --> W3 --> E1 --> E3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Sensors, SCADA Gates & Salinity Sondes | Ultrasonic level sensors measure canal water stage ($m$) and Doppler velocity ($m/s$), estuarine salinity sondes measure salinity ($ppt$), and SCADA gate encoders provide position feedback |
| 2 | Irrigation Canal Management System | Ingests telemetry, runs 1D Saint-Venant unsteady flow hydrodynamic models, calculates PID sluice gate opening commands, manages salinity protection closures, and computes volumetric WUA water billing |
| 3 | Canal Hydraulic Engineer / Operator | Reviews 1D hydrodynamic flow profiles, approves seasonal water allocation plans, monitors head loss, and dispatches drone bathymetry canal desilting work orders |
| 4 | Water User Association (WUA) Manager | Submits seasonal cropping pattern water demands, requests farm turnout water delivery orders, coordinates irrigation turns, and pays volumetric water service fees |
| 5 | SCADA Motor Actuators & Flood Alert Gateway | Motorized check gate actuators adjust sluice gate heights, estuarine barrier gates close 100% during salt intrusion, and flood alert gateways broadcast emergency warnings |
