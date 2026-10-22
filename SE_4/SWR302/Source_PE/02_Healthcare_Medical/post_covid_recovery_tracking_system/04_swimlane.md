# Swimlane Diagram — Post-COVID Recovery Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Long-COVID Recovery Tracking"])

    subgraph Lane_Patient["Post-COVID Patient"]
        P1["Wear Smartwatch & Complete Daily Symptom Diary"]
        P2["Perform 6-Minute Walk Test & Tele-Rehab Exercises"]
        P3["Initiate 48-Hour Low-Stimulation Bed Rest"]
    end

    subgraph Lane_Therapist["Rehabilitation Physical Therapist"]
        T1["Assign Activity Pacing Energy Envelope & 6MWT Goals"]
        T2["Pause Active Physical Tele-Rehab Exercises"]
        T3["Re-Calibrate Daily Step Cap Limit & Pacing Rules"]
    end

    subgraph Lane_System["Post-COVID Recovery Tracking System"]
        S1["Ingest Continuous Smartwatch SpO2 & Heart Rate"]
        S2["Cross-Correlate Activity Spikes with 48-Hr Fatigue Drops"]
        S3{"Is PEM Flare Crisis Detected (Post-Activity Crash)?"}
        S4["Update Multi-Organ Recovery Trends & Disability FCE"]
    end

    subgraph Lane_Doctor["Multidisciplinary Specialist Physician"]
        D1["Review PFT DLCO, MoCA Cognitive & POTS Trends"]
        D2["Sign Functional Capacity Disability Evaluation Report"]
    end

    Finish(["End Recovery Cycle"])

    Start --> P1 --> S1 --> S2 --> S3
    S3 -->|"Yes"| T2 --> P3 --> T3 --> S4
    S3 -->|"No / Tolerated"| T1 --> P2 --> D1 --> D2 --> S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Post-COVID Patient | Wears smartwatch telemetry devices, completes daily symptom diaries (fatigue, brain fog, dyspnea), performs 6-minute walk tests, executes tele-rehab exercises, and follows rest pacing. |
| 2 | Rehabilitation Physical Therapist | Establishes activity pacing energy envelopes, sets 6MWT endurance targets, pauses active exercises during Post-Exertional Malaise (PEM) crashes, and re-calibrates step caps. |
| 3 | Post-COVID Recovery Tracking System | Ingests smartwatch $SpO_2$ and heart rate data, cross-correlates physical activity spikes with 48-hour fatigue crashes to detect PEM crises, and updates longitudinal multi-organ recovery trends. |
| 4 | Multidisciplinary Specialist Physician | Reviews longitudinal PFT lung diffusion, MoCA cognitive scores, and POTS dysautonomia trends, authorizing Functional Capacity Evaluation disability reports. |
