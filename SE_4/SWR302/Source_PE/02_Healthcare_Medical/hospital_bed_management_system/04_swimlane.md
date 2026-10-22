# Swimlane Diagram — Hospital Bed Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Patient Admission Request"])

    subgraph Lane_Admissions["Admissions Officer / Triage"]
        A1["Receive Admission Order & Patient Parameters"]
        A2["Query Available Ward Beds in System"]
        A3{"Is Target Bed Clean & Ready?"}
        A4["Confirm Patient Bed Allocation"]
        A5["Select Alternative Available Ward Bed"]
    end

    subgraph Lane_System["Bed Management System"]
        S1["Filter Beds by Gender & Isolation Criteria"]
        S2["Check Real-Time Bed Status & Reservations"]
        S3["Update Bed Status to Occupied"]
        S4["Auto-Generate Cleaning Task Ticket"]
        S5["Calculate Turnaround SLA Metrics"]
    end

    subgraph Lane_Nurse["Ward Nurse Staff"]
        N1["Receive Patient & Update Ward Bed Layout"]
        N2["Monitor Inpatient Stay & Care Delivery"]
        N3["Execute Discharge Order & Release Bed"]
        N4["Perform Final Inspection of Cleaned Bed"]
    end

    subgraph Lane_Housekeeping["Housekeeping Staff"]
        H1["Receive Priority Cleaning Dispatch Ticket"]
        H2["Accept Cleaning Task on Mobile App"]
        H3["Sanitize Room, Mattress & Replace Linens"]
        H4["Mark Cleaning Task as Complete"]
    end

    Finish(["End Bed Turnaround Cycle"])

    Start --> A1 --> A2 --> S1 --> S2 --> A3
    A3 -->|"Yes"| A4 --> S3 --> N1 --> N2 --> N3 --> S4
    A3 -->|"No / Dirty"| A5 --> A2
    S4 --> H1 --> H2 --> H3 --> H4 --> N4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Admissions Officer / Triage | Ingests admission orders, queries the system for available beds matching clinical requirements, evaluates bed readiness, confirms placement, or selects alternative beds. |
| 2 | Bed Management System | Processes automated matching filters (gender, isolation, telemetry), verifies live bed status, updates database records to `Occupied`, triggers housekeeping work orders upon discharge, and computes turnaround SLA metrics. |
| 3 | Ward Nurse Staff | Welcomes patient to ward unit, manages bed placement, executes discharge orders when care is finished, releases bed, and conducts final inspection of sanitized beds. |
| 4 | Housekeeping Staff | Receives automated cleaning task tickets, accepts work orders via mobile app, sanitizes mattress/room according to biohazard protocols, and updates task status to `Clean & Pending Inspection`. |
