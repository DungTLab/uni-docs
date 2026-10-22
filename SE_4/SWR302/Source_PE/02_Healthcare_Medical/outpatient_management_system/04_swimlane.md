# Swimlane Diagram — Outpatient Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Outpatient Visit"])

    subgraph Lane1["Outpatient / Patient"]
        A1["Scan Citizen ID / VSS Card at Kiosk"]
        A2["Proceed to Triage & Consultation Room"]
        A3["Pay Co-Pay & Receive Medications"]
    end

    subgraph Lane2["Receptionist & Triage Nurse"]
        B1["Issue Queue Ticket & Check Referral"]
        B2["Measure Vital Signs & Log Chief Complaint"]
    end

    subgraph Lane3["System / OPD & Billing Engine"]
        S1["Verify VSS Card & Assign Room Queue"]
        S2["Display Queue Call Number on TV Screen"]
        S3{"Requires Diagnostic Tests or Inpatient Admission?"}
        S4["Stream Lab/X-Ray Orders & e-Prescription"]
        S5["Calculate VSS Co-Pay & Issue Receipt"]
    end

    subgraph Lane4["Consultation Physician"]
        C1["Examine Patient & Chart SOAP Notes"]
        C2["Code ICD-10 & Prescribe e-Medications"]
    end

    Finish(["End Outpatient Visit Cycle"])

    Start --> A1 --> S1 --> B1 --> B2 --> S2 --> C1 --> C2 --> S3
    S3 -->|"Yes"| S4 --> C1
    S3 -->|"No"| S4 --> S5 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Outpatient / Patient | Scans Citizen ID or VSS insurance card at self-kiosk, receives queue ticket, proceeds to triage and doctor consultation rooms, pays co-pays, and collects dispensed medications. |
| 2 | Receptionist & Triage Nurse | Verifies primary clinic referral letters, issues queue tickets, measures baseline vital signs (BP, HR, Temp, SpO2, Weight), logs chief complaints, and sorts queue priorities. |
| 3 | System / OPD & Billing Engine | Validates VSS insurance coverage rates, displays queue call numbers on clinic room TV screens, routes CPOE diagnostic test orders to LIS/RIS, calculates co-pays, and generates VSS claims. |
| 4 | Consultation Physician | Calls next patient into room, conducts physical examination, documents clinical SOAP notes, codes primary ICD-10 diagnoses, orders lab/radiology tests, and writes e-prescriptions. |
