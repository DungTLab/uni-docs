# Swimlane Diagram — ICU Patient Monitoring System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Real-Time ICU Surveillance"])

    subgraph Lane1["Bedside Hardware Devices"]
        H1["Acquire 100Hz ECG, IABP & SpO2 Signals"]
        H2["Stream Ventilator & Infusion Pump Data"]
    end

    subgraph Lane2["System / Alarm & Analytics Engine"]
        S1["Filter Waveforms & Compute Numeric Vitals"]
        S2["Evaluate SOFA Sepsis & Alarm Thresholds"]
        S3{"Tier-1 Crisis Red Alarm Breached?"}
        S4["Filter Transient Spikes & Trigger Yellow Alert"]
        S5["Dispatch Mobile Code Blue & High Red Alarm"]
        S6["Archive High-Density Vital Array to HIS/EHR"]
    end

    subgraph Lane3["ICU Bedside Nurse"]
        N1["Monitor Bedside Waveforms & Adjust Limits"]
        N2["Acknowledge Alarm & Silence Audio"]
        N3["Perform Immediate Resuscitation / Suction"]
    end

    subgraph Lane4["Intensivist Physician"]
        P1["Review 24H Vital Trends & SOFA Score"]
        P2["Adjust Vasopressor Drip & Ventilator Target"]
    end

    Finish(["End Shift Surveillance Cycle"])

    Start --> H1 --> H2 --> S1 --> S2 --> S3
    S3 -->|"No"| S4 --> N1 --> S6 --> Finish
    S3 -->|"Yes"| S5 --> N2 --> N3 --> P1 --> P2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Bedside Hardware Devices | Captures high-speed continuous electrical signals (ECG, IABP, SpO2) and streams mechanical ventilator and infusion pump telemetry parameters into the system. |
| 2 | System / Alarm & Analytics Engine | Filters signal noise, calculates numeric vital signs, computes real-time SOFA organ failure scores, evaluates alarm threshold limits, filters transient false alarms, and dispatches Code Blue alerts. |
| 3 | ICU Bedside Nurse | Monitors continuous bedside telemetry displays, configures patient-specific alarm limits, acknowledges alarm alerts, silences audio, and performs immediate clinical resuscitation. |
| 4 | Intensivist Physician | Evaluates 24-hour vital trend graphs, analyzes APACHE II mortality predictions and SOFA scores, and adjusts vasopressor titration targets or ventilator settings. |
