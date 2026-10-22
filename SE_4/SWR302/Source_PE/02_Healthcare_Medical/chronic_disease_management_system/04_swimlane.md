# Swimlane Diagram — Chronic Disease Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Chronic Program Enrollment"])

    subgraph Lane_Patient["Chronic Disease Patient"]
        P1["Take Home Glucose & BP Measurements"]
        P2["Receive Medication & Refill Reminders"]
        P3["Participate in Outreach Call & Complete Drills"]
    end

    subgraph Lane_Navigator["Chronic Care Navigator"]
        N1["Review Multi-Patient Risk Stratification Roster"]
        N2{"Is Patient High Risk Tier / MPR < 60%?"}
        N3["Initiate Proactive Tele-Outreach Call"]
        N4["Schedule Routine 6-Month Review"]
    end

    subgraph Lane_System["Chronic Disease System"]
        S1["Ingest Home RPM Telemetry via Cellular Gateway"]
        S2["Calculate Time-In-Range & 180-Day MPR Score"]
        S3["Evaluate HEDIS Care Gaps & Risk Stratification"]
        S4["Update Care Plan & Export Quality Reports"]
    end

    subgraph Lane_Doctor["Primary Care Physician"]
        D1["Set Target Clinical Boundaries HbA1c/BP"]
        D2["Adjust Chronic Medication & E-Prescriptions"]
        D3["Sign Revised Chronic Care Plan"]
    end

    Finish(["End Care Management Cycle"])

    Start --> D1 --> P1 --> S1 --> S2 --> S3 --> N1 --> N2
    N2 -->|"Yes"| N3 --> D2 --> D3 --> S4 --> Finish
    N2 -->|"No / Stable"| N4 --> P2 --> P3 --> S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Chronic Disease Patient | Performs daily home health measurements (glucose, BP), receives automated medication/refill alarms, participates in outreach calls, and completes self-management exercises. |
| 2 | Chronic Care Navigator | Reviews daily multi-patient risk stratification rosters, identifies high-risk non-adherent patients, conducts proactive tele-outreach calls, and coordinates gap closure. |
| 3 | Chronic Disease System | Ingests home RPM device telemetry, calculates continuous Time-in-Range (TIR) and Medication Possession Ratio (MPR %), evaluates HEDIS care gaps, computes risk tiers, and exports quality reports. |
| 4 | Primary Care Physician | Establishes clinical target boundaries (HbA1c, BP), adjusts chronic medication dosages, issues e-prescriptions, and authorizes revised chronic care plans. |
