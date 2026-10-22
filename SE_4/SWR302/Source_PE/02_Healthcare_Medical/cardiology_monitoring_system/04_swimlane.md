# Swimlane Diagram — Cardiology Monitoring System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start ECG Monitoring"])

    subgraph Lane_Patient["Cardiology Patient"]
        P1["Wear 12-Lead Electrodes / Holter Patch"]
        P2["Perform Baseline Rest / Exercise Session"]
        P3["Press Symptom Event Marker Button"]
    end

    subgraph Lane_System["Cardiology Monitoring System"]
        S1["Ingest Multi-Channel Digital Waveforms"]
        S2["Filter Baseline Noise & Compute P-QRS-T Intervals"]
        S3["Run AI Arrhythmia & ST-Segment Analysis"]
        S4{"Is Rhythm Lethal Arrhythmia (VF/Asystole)?"}
        S5["Dispatch Code Blue Alarm & Pre-Event Strip"]
        S6["Compile Report Draft & Attach Strip Samples"]
        S7["Export HL7 Report to Hospital EHR"]
    end

    subgraph Lane_Doctor["Cardiologist / Electrophysiologist"]
        D1["Review Arrhythmia Events & ST Shifts"]
        D2["Annotate Key 6-Second ECG Strips"]
        D3["Confirm Diagnosis & Sign Diagnostic Report"]
    end

    subgraph Lane_Emergency["Crash Cart / Code Blue Team"]
        E1["Receive Emergency Alarm & Patient GPS"]
        E2["Dispatch Code Blue Resuscitation Team"]
    end

    Finish(["End Diagnostic Monitoring Cycle"])

    Start --> P1 --> P2 --> S1 --> S2 --> S3 --> S4
    S4 -->|"Yes"| S5 --> E1 --> E2 --> Finish
    S4 -->|"No"| P3 --> S6 --> D1 --> D2 --> D3 --> S7 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Cardiology Patient | Wears 12-lead electrodes or ambulatory Holter patches, performs resting or exercise tests, and presses event buttons during symptom episodes. |
| 2 | Cardiology Monitoring System | Ingests multi-lead voltage signals, filters baseline noise, calculates P-QRS-T intervals, executes AI arrhythmia detection, evaluates ST elevation/depression, dispatches emergency Code Blue alerts when lethal rhythms occur, and exports HL7 reports. |
| 3 | Cardiologist / Electrophysiologist | Reviews automated arrhythmia findings, inspects ST-segment shifts, annotates representative 6-second ECG strip samples, enters diagnostic impressions, and applies digital signatures. |
| 4 | Crash Cart / Code Blue Team | Receives automated emergency alarms, location coordinates, and pre-event ECG tracings when lethal cardiac arrest rhythms occur, dispatching resuscitation teams immediately. |
