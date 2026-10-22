# Swimlane Diagram — Physiotherapy Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Physiotherapy Rehabilitation Cycle"])

    subgraph Lane1["Patient / Sports Athlete"]
        A1["Check In & Report Current VAS Pain"]
        A2["Perform In-Clinic Therapy & Home Exercises"]
    end

    subgraph Lane2["Physical Therapy Receptionist"]
        B1["Schedule Sessions & Reserve Equipment Room"]
        B2["Process CPT Insurance Billing & Collect Copay"]
    end

    subgraph Lane3["System / PT & ROM Engine"]
        S1["Compute ROM Deficits & Baseline Functional Score"]
        S2{"Post-Op Restrictions or Pain Spike > 8/10?"}
        S3["Flag Restrictive Protocol & Alert Therapist"]
        S4["Publish HEP Video Playlist to Patient App"]
        S5["Plot ROM & Outcome Score Recovery Curves"]
    end

    subgraph Lane4["Physical Therapist"]
        C1["Conduct ROM Goniometry & MMT Strength Test"]
        C2["Formulate Treatment Plan & Modalities"]
        C3["Execute Therapy Session & Log Modalities"]
    end

    Finish(["End Physiotherapy Rehabilitation Cycle"])

    Start --> A1 --> B1 --> C1 --> S1 --> S2
    S2 -->|"Yes"| S3 --> C2
    S2 -->|"No"| C2 --> S4 --> C3 --> B2 --> A2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient / Sports Athlete | Checks in at reception, reports current VAS pain levels, performs in-clinic exercises and modalities under therapist supervision, and completes assigned home exercise videos. |
| 2 | Physical Therapy Receptionist | Schedules treatment sessions, reserves specialized treatment rooms and equipment (Hydrotherapy, Traction), processes CPT billing claims, and collects copays. |
| 3 | System / PT & ROM Engine | Calculates joint Range of Motion (ROM) deficits, scores functional outcome scales (Oswestry, DASH), screens post-op weight-bearing restrictions, publishes HEP video playlists, and plots recovery graphs. |
| 4 | Physical Therapist | Conducts initial musculoskeletal assessments, measures goniometer ROM angles and MMT muscle strength, formulates multi-week treatment plans, and executes modalities (Ultrasound, TENS, Manual Therapy). |
