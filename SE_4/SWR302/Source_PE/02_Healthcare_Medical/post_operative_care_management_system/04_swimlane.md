# Swimlane Diagram — Post-Operative Care Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start PACU Post-Op Admission"])

    subgraph Lane_Nurse["PACU Nurse Staff"]
        N1["Receive Patient from OR & Connect Vitals Monitor"]
        N2["Perform 15-Min Aldrete Emergence Assessment"]
        N3["Check Surgical Wound Dressing & Log Drain Output"]
        N4{"Is Aldrete Score >= 9 & Vitals Stable?"}
        N5["Execute PACU Discharge to Ward / Home"]
    end

    subgraph Lane_Patient["Post-Op Patient"]
        P1["Emerge from Anesthesia in PACU Bay"]
        P2["Report Pain Score & Request PCA Bolus"]
        P3["Perform Incentive Spirometry & Ambulation"]
    end

    subgraph Lane_System["Post-Op Care System"]
        S1["Ingest Real-Time Bedside Vitals Stream"]
        S2["Enforce PCA Pump Lockout & Dose Limits"]
        S3["Compute NEWS2 Score & Sepsis Risk"]
        S4["Verify Discharge Criteria Checklist"]
        S5["Export Recovery Summary to Hospital EHR"]
    end

    subgraph Lane_Doctor["Anesthesiologist / Surgeon"]
        D1["Review Intra-Op Notes & Post-Op Vitals"]
        D2["Adjust Pain Control & Fluid Orders"]
        D3["Sign Electronic PACU Discharge Order"]
    end

    Finish(["End PACU Recovery Cycle"])

    Start --> N1 --> S1 --> P1 --> P2 --> S2 --> N2 --> N3 --> S3 --> D1 --> D2 --> N4
    N4 -->|"Yes"| D3 --> S4 --> N5 --> S5 --> Finish
    N4 -->|"No / Unstable"| P3 --> N2
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | PACU Nurse Staff | Connects patient to monitors upon OR arrival, performs 15-minute Aldrete assessments, monitors wound dressings and drain output, and executes final PACU discharge. |
| 2 | Post-Op Patient | Emerges from anesthesia, self-reports pain/nausea scores, operates PCA IV pump pendant for pain relief, and completes respiratory/physical therapy exercises. |
| 3 | Post-Op Care System | Streams continuous bedside vitals telemetry, enforces PCA pump safety lockout windows, calculates early warning NEWS2 sepsis scores, validates discharge criteria, and exports HL7 recovery summaries to EHR. |
| 4 | Anesthesiologist / Surgeon | Reviews post-op recovery telemetry, adjusts multimodal pain orders and IV fluids, and signs electronic PACU discharge orders when criteria are met. |
