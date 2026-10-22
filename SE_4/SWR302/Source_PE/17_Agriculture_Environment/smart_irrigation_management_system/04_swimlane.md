# Swimlane Diagram — Smart Irrigation Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Irrigation Monitoring"])

    subgraph Lane1["IoT Sensors & Valve Actuators"]
        S1["Transmit Soil Moisture VWC & Flow Rate"]
        S2["Execute Relay Open Command on Solenoid Valve"]
        S3["Execute Relay Close Command on Solenoid Valve"]
    end

    subgraph Lane2["Smart Irrigation System"]
        SYS1["Process Soil Telemetry & ET0 Deficit"]
        SYS2{"Soil Moisture < Target Threshold?"}
        SYS3{"Check Weather Forecast Rain > 70%?"}
        SYS4["Delay Schedule & Log Rain Hold"]
        SYS5["Calculate Water Volume & Dispatch Open Signal"]
        SYS6["Monitor Line Flow & Pressure Stability"]
        SYS7{"Pressure Drop > 30% Leak Anomaly?"}
        SYS8["Trigger Pipe Burst Isolation & Stop Pump"]
    end

    subgraph Lane3["Agronomist"]
        AG1["Configure Target Moisture & MAD Limits"]
        AG2["Review Water Efficiency Analytics Reports"]
    end

    subgraph Lane4["Irrigation Operator"]
        OP1["Receive High-Priority Pipe Burst Alert"]
        OP2["Execute Physical Pipe Repair in Field"]
        OP3["Reset Zone Interlock & Confirm System Recovery"]
    end

    Finish(["End Process"])

    Start --> AG1 --> S1 --> SYS1 --> SYS2
    SYS2 -->|"Yes"| SYS3
    SYS2 -->|"No"| AG2 --> Finish
    SYS3 -->|"Yes"| SYS4 --> Finish
    SYS3 -->|"No"| SYS5 --> S2 --> SYS6 --> SYS7
    SYS7 -->|"Normal Flow"| S3 --> AG2 --> Finish
    SYS7 -->|"Leak Detected"| SYS8 --> OP1 --> OP2 --> OP3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| IoT Sensors & Valve Actuators | Field Hardware Array | Measures soil volumetric water content (VWC) at multi-depth root zones, transmits wireless telemetry, and actuates physical solenoid valves. |
| Smart Irrigation System | Automated Core Platform | Processes soil moisture telemetry, computes water deficits, checks weather rain delays, commands valve switching, monitors hydraulic pressure stability, and isolates line leaks. |
| Agronomist | Technical Specialist | Establishes target soil moisture limits, defines crop management allowable depletion (MAD) thresholds, and reviews water utilization reports. |
| Irrigation Operator | Maintenance Technician | Receives automated pipe rupture alerts, performs physical field pipe repairs, and resets system safety interlocks to restore normal operations. |
