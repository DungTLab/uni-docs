# Swimlane Diagram — Dental Clinic Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Dental Visit"])

    subgraph Lane1["Dental Patient"]
        A1["Check In & State Dental Complaint"]
        A2["Review Treatment Plan & Sign Consent"]
        A3["Pay Invoice or Agree Installment Plan"]
    end

    subgraph Lane2["System / Odontogram & Billing Engine"]
        S1["Verify Appointment & Assign Chair"]
        S2["Calculate Treatment Fees & Insurance"]
        S3{"High-Cost Plan or Lab Order Required?"}
        S4["Dispatch Dental Lab Order Ticket"]
        S5["Generate Invoice & Schedule 6M Recall"]
    end

    subgraph Lane3["Dentist / Dental Specialist"]
        B1["Chart Odontogram & Periodontal Probing"]
        B2["Formulate Phased Treatment Plan"]
        B3["Perform Dental Procedure & Log CDT Code"]
    end

    subgraph Lane4["Dental Receptionist"]
        W1["Collect Co-Pay & Process Invoice"]
        W2["Schedule Next Visit / Fitting Slot"]
    end

    Finish(["End Dental Visit Cycle"])

    Start --> A1 --> S1 --> B1 --> B2 --> A2 --> S2 --> S3
    S3 -->|"Yes"| S4 --> B3 --> S5 --> W1 --> A3 --> W2 --> Finish
    S3 -->|"No"| B3 --> S5 --> W1 --> A3 --> W2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Dental Patient | Checks in at front desk, states tooth symptoms/concerns, reviews proposed treatment plan estimates, signs informed consent, and settles invoices via cash, card, or installment plans. |
| 2 | System / Odontogram & Billing Engine | Verifies appointments, assigns operatory chairs, renders digital Odontogram charts, calculates procedure fees and insurance copays, dispatches dental lab work orders, and generates 6-month hygiene recall schedules. |
| 3 | Dentist / Dental Specialist | Conducts clinical exam, charts tooth conditions on interactive Odontogram, measures 1-6mm periodontal pocket depths, formulates phased treatment plans, and executes dental procedures with CDT codes. |
| 4 | Dental Receptionist | Manages front-desk check-in, collects co-pays, issues final invoices, sets up monthly installment payment agreements, and schedules follow-up fitting visits or hygiene recalls. |
