# Swimlane Diagram — Hearing & Speech Therapy Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Diagnostic Consultation"])

    subgraph Lane_Patient["Therapy Patient"]
        P1["Attend Assessment Session & Perform Speech Sample"]
        P2["Practice Home Exercises on Portal App"]
        P3["Provide Real-World Hearing & Speech Feedback"]
    end

    subgraph Lane_Therapist["Audiologist / Speech Pathologist"]
        T1["Conduct Pure-Tone & Articulation Testing"]
        T2["Formulate Prescription & Individualized Therapy Plan"]
        T3{"Is Target Gain / Articulation Goal Met?"}
        T4["Fine-Tune Device / Adjust Therapy Plan"]
        T5["Finalize Case Evaluation & Sign-Off Report"]
    end

    subgraph Lane_System["Therapy Management System"]
        S1["Ingest Diagnostic Audiometer & Acoustic Streams"]
        S2["Calculate Target Gain Curves NAL-NL2 / Spectrograms"]
        S3["Dispatch Home Exercises to Patient App"]
        S4["Track Goal Progress & Export Billing CPT Codes"]
    end

    subgraph Lane_Hardware["Hearing Device / Speech Engine"]
        H1["Stream Decibel Thresholds & Audio Waveforms"]
        H2["Upload Prescribed Gain Curves into Hearing Aid"]
        H3["Record Patient Speech Practice & Score Accuracy"]
    end

    Finish(["End Therapy Consultation Cycle"])

    Start --> P1 --> T1 --> H1 --> S1 --> S2 --> T2 --> H2 --> T3
    T3 -->|"Yes"| T5 --> S4 --> Finish
    T3 -->|"No / Partial"| T4 --> S3 --> P2 --> H3 --> P3 --> T3
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Therapy Patient | Attends clinical assessment sessions, vocalizes speech samples, practices assigned exercises on mobile app portal, and provides feedback on real-world hearing clarity. |
| 2 | Audiologist / Speech Pathologist | Conducts pure-tone audiometry and speech articulation assessments, formulates fitting prescriptions/therapy plans, evaluates goal progress, fine-tunes settings, and signs off clinical reports. |
| 3 | Therapy Management System | Ingests audiometer and acoustic audio streams, calculates NAL-NL2/DSL v5 target gain curves, renders voice spectrograms, dispatches home exercises, and exports CPT billing codes. |
| 4 | Hearing Device / Speech Engine | Streams raw decibel thresholds and audio recordings, receives digital hearing aid programming parameters, and records/scores patient home speech practice accuracy. |
