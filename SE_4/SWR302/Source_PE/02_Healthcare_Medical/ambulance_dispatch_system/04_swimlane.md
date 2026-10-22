# Swimlane Diagram — Ambulance Dispatch System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Emergency Call Intake"])

    subgraph Lane1["Emergency 115 Caller"]
        A1["Call 115 Hotline & Report Location & Symptoms"]
        A2["Receive Telephone CPR Guidance while En-Route"]
    end

    subgraph Lane2["115 Call Taker & Dispatcher"]
        B1["Execute AMPDS Triage & Calculate Priority Code"]
        B2["Provide Telephone First-Aid & Monitor GIS CAD"]
    end

    subgraph Lane3["System / CAD & Telemetry Engine"]
        S1["Query AVL GPS Fleet & Auto-Assign Fastest ALS Unit"]
        S2{"Response Time < 8 Mins & ALS Unit Assigned?"}
        S3["Reroute Secondary Unit & Escalate SLA Warning"]
        S4["Stream Live 12-Lead ECG & Request Green Corridor"]
        S5["Sync ePCR to ER EHR & Reset Unit Status Available"]
    end

    subgraph Lane4["Mobile Paramedic & Hospital ED Team"]
        C1["Roll Out Ambulance & Drive Emergency Corridor"]
        C2["Render Pre-Hospital Care & Execute ER Handover"]
    end

    Finish(["End Hospital Handover & Unit Reset"])

    Start --> A1 --> B1 --> S1 --> S2
    S2 -->|"No"| S3 --> S1
    S2 -->|"Yes"| C1 --> B2 --> A2 --> S4 --> C2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Emergency 115 Caller | Calls 115 emergency hotline, reports incident location and patient symptoms, receives telephone CPR/first-aid instructions, and awaits ambulance arrival. |
| 2 | 115 Call Taker & Dispatcher | Answers 115 calls, executes AMPDS protocol triage (Red/Orange/Yellow/Green), provides telephone CPR instructions, and monitors CAD GIS fleet maps. |
| 3 | System / CAD & Telemetry Engine | Queries Automatic Vehicle Location (AVL) GPS pings, auto-assigns the fastest ALS unit, requests traffic light green corridors, streams 12-lead ECGs, and syncs ePCR records. |
| 4 | Mobile Paramedic & Hospital ED Team | Rolls out ambulance (< 90s chute time), navigates green corridor, renders pre-hospital ALS care, streams ECGs, transfers patient custody, and signs digital handovers. |
