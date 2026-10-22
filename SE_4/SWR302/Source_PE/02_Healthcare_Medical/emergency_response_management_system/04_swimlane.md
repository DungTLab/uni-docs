# Swimlane Diagram — Emergency Response Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start 115 Emergency Incident"])

    subgraph Lane1["Bystander / Caller"]
        A1["Dial 115 Hotline & State Location"]
        A2["Perform Telephone CPR Assistance"]
    end

    subgraph Lane2["115 CAD Dispatch Operator"]
        B1["Intake Call & Assign MPDS Priority"]
        B2["Dispatch Nearest ALS Ambulance via CAD"]
        B3["Coordinate Multi-Agency Fire/Police Aid"]
    end

    subgraph Lane3["Paramedic / EMT Crew"]
        C1["Arrive Scene & Assign START Triage Tag"]
        C2["Chart Pre-Hospital Care & 12-Lead ECG"]
        C3["Transport Patient to Hospital ER"]
    end

    subgraph Lane4["Hospital ER Trauma Physician"]
        D1["Review Live 12-Lead ECG Telemetry"]
        D2["Reserve Trauma Bay & Activate STEMI Team"]
        D3["Receive Patient & Transfer Care"]
    end

    subgraph Lane5["System / CAD & GIS Engine"]
        S1["Geolocate Caller GPS & Render Map"]
        S2{"STEMI / Red-Tag Critical Incident?"}
        S3["Stream 12-Lead ECG & Pre-Empt Traffic Signals"]
        S4["Generate Transport Invoice & Log ePCR"]
    end

    Finish(["End Emergency Response Cycle"])

    Start --> A1 --> S1 --> B1 --> B2 --> A2 --> C1 --> C2 --> S2
    S2 -->|"Yes"| S3 --> D1 --> D2 --> C3 --> D3 --> S4 --> Finish
    S2 -->|"No"| C3 --> D3 --> S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Bystander / Caller | Dials 115 emergency hotline, provides incident location and victim details, and receives telephone-assisted CPR guidance. |
| 2 | 115 CAD Dispatch Operator | Intakes 115 calls, calculates MPDS triage priority codes, dispatches optimal ALS/BLS ambulances via CAD, and coordinates fire/police mutual aid. |
| 3 | Paramedic / EMT Crew | Responds to dispatch, performs START triage tagging on scene, charts pre-hospital ePCR notes, transmits 12-lead ECGs, and transports victims. |
| 4 | Hospital ER Trauma Physician | Reviews real-time 12-lead ECG telemetry, activates STEMI/trauma teams, reserves ER trauma bays, and receives patient hand-off. |
| 5 | System / CAD & GIS Engine | Geolocates caller GPS pins, calculates ambulance proximity routes, streams cardiac telemetry, preempts traffic signals, and bills transport fees. |
