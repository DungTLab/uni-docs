# Swimlane Diagram — Inpatient Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Inpatient Admission"])

    subgraph Lane1["Inpatient / Family"]
        A1["Pay Advance Deposit & Check In Bed"]
        A2["Receive Daily Ward Care & eMAR Meds"]
        A3["Settle Remaining Balance & Depart Ward"]
    end

    subgraph Lane2["Admission Clerk & Ward Nurse"]
        B1["Assign Ward Room Bed & Issue Wristband"]
        B2["Execute 5-Rights eMAR Barcode Admin"]
    end

    subgraph Lane3["System / IPD & eMAR Engine"]
        S1["Reserve Bed & Track Daily Midnight Fees"]
        S2{"Clinical Deterioration or ICU Escalation?"}
        S3["Execute Inter-Ward Transfer to ICU Bed"]
        S4["Calculate VSS Net Bill & Release Bed"]
    end

    subgraph Lane4["Attending Physician"]
        C1["Chart Daily SOAP Rounds & Issue CPOE"]
        C2["Sign Discharge Summary & ICD-10 Codes"]
    end

    Finish(["End Inpatient Discharge & Settlement"])

    Start --> A1 --> B1 --> S1 --> C1 --> B2 --> S2
    S2 -->|"Yes"| S3 --> C1
    S2 -->|"No"| C2 --> S4 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Inpatient / Family | Pays advance financial deposits, checks into assigned ward bed, receives daily bedside nursing and eMAR medication care, settles final bills, and departs ward. |
| 2 | Admission Clerk & Ward Nurse | Assigns available ward/room/bed numbers, prints barcode ID wristbands, conducts daily nursing vitals checks, and administers 5-Rights eMAR medications. |
| 3 | System / IPD & eMAR Engine | Reserves hospital beds, accumulates daily midnight bed/nursing fees, evaluates clinical deterioration alerts, manages ICU ward transfers, and calculates net VSS insurance bills. |
| 4 | Attending Physician | Conducts daily physician ward rounds, charts SOAP progress notes, issues CPOE orders for IV fluids and lab tests, and signs official clinical discharge summaries with ICD-10 codes. |
