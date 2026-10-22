# Swimlane Diagram — Aquaculture Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Pond Water Monitoring"])

    subgraph Lane1["Water Quality IoT Sensors"]
        S1["Measure Dissolved Oxygen & Water Temp"]
        S2["Transmit Telemetry Stream over LoRaWAN"]
    end

    subgraph Lane2["Aquaculture Management System"]
        SYS1["Process Sensor Data & Compute Ammonia Toxicity"]
        SYS2{"Dissolved Oxygen < 4.0 mg/L Safe Limit?"}
        SYS3["Trigger Low DO Hypoxia Emergency Alarm"]
        SYS4["Dispatch Relay CLOSE Command to Aerators"]
        SYS5["Halt Automatic Feeders During Low DO"]
        SYS6["Update Pond Oxygen Heatmap to Normal"]
    end

    subgraph Lane3["Aerator & Auto-Feeder Actuators"]
        A1["Close Motor Relays & Start Paddlewheel Aerators"]
        A2["Halt Auto-Feeder Timer Relays"]
        A3["Increase Dissolved Oxygen through Water Circulation"]
    end

    subgraph Lane4["Pond Operator / Technician"]
        OP1["Receive Low DO Emergency SMS Notification"]
        OP2["Inspect Aerator Motor Currents in Field"]
        OP3["Apply Agricultural Lime to Balance Water pH"]
    end

    Finish(["End Process"])

    Start --> S1 --> S2 --> SYS1 --> SYS2
    SYS2 -->|"Yes — Oxygen Drop"| SYS3 --> SYS4 --> A1 --> A3 --> S1
    SYS4 --> SYS5 --> A2
    SYS3 --> OP1 --> OP2 --> OP3 --> Finish
    SYS2 -->|"No — Oxygen Safe"| SYS6 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| Water Quality IoT Sensors | Submerged Sensor Array | Continuously measures Dissolved Oxygen (DO mg/L), water temperature, pH, and transmits wireless telemetry streams over LoRaWAN. |
| Aquaculture Management System | Central Core Platform | Evaluates oxygen drop thresholds, triggers emergency SMS alarms, sends automated relay signals to activate paddlewheels, halts feeders during hypoxia, and maintains live pond heatmaps. |
| Aerator & Auto-Feeder Actuators | Field Electrical Relays | Receives start signals to energize paddlewheel aerator motors, halts feed dispensers during low DO conditions, and circulates pond water to restore oxygenation. |
| Pond Operator / Technician | Field Technician | Receives emergency SMS notifications, performs physical inspections of aerator motor currents, and applies agricultural lime to stabilize pH and water quality. |
