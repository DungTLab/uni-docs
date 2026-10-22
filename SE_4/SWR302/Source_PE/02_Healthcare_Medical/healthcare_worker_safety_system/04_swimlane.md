# Swimlane Diagram — Healthcare Worker Safety System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Safety Incident / Exposure"])

    subgraph Lane_Worker["Healthcare Worker"]
        W1["Report Needlestick / Bloodborne Injury"]
        W2["Double-Press Wearable RTLS Panic Badge"]
        W3["Receive 28-Day HIV PEP Starter Kit"]
    end

    subgraph Lane_OccHealth["Occupational Health Specialist"]
        O1["Query Source Patient Infection Status in EHR"]
        O2{"Is Source Patient HIV Positive or High-Risk?"}
        O3["Initiate 28-Day HIV PEP & Follow-Up Calendar"]
        O4["Order Baseline Serology & Monitor Routine Recovery"]
    end

    subgraph Lane_System["Healthcare Worker Safety System"]
        S1["Log Needlestick Incident & Display First-Aid Guide"]
        S2["Decode RTLS Badge & Pinpoint Room Coordinates"]
        S3["Update Employee Health Record & File OSHA 300"]
    end

    subgraph Lane_Security["Hospital Security Response Team"]
        K1["Receive RTLS Panic Room Alert on Console"]
        K2["Dispatch Officers to Room Coordinates Within 60s"]
        K3["De-escalate Violence & File Incident Report"]
    end

    Finish(["End Worker Safety Incident Workflow"])

    Start --> W1 --> S1 --> O1 --> O2
    O2 -->|"Yes"| O3 --> W3 --> S3 --> Finish
    O2 -->|"No / Low Risk"| O4 --> S3 --> Finish

    Start --> W2 --> S2 --> K1 --> K2 --> K3 --> S3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Healthcare Worker | Reports needlestick injuries, triggers wearable RTLS panic badges during violence threats, performs immediate first aid, and receives PEP starter kits. |
| 2 | Occupational Health Specialist | Queries source patient HIV/HBV/HCV infection status in EHR, evaluates exposure risk tiers, initiates 28-day HIV PEP protocols, and manages follow-up surveillance. |
| 3 | Healthcare Worker Safety System | Logs safety incidents, displays immediate first-aid instructions, decodes RTLS badge room coordinates, updates employee health records, and generates mandatory OSHA 300 log entries. |
| 4 | Hospital Security Response Team | Receives instant RTLS panic room alerts on command center consoles, dispatches security officers within 60 seconds, de-escalates violent threats, and submits security incident logs. |
