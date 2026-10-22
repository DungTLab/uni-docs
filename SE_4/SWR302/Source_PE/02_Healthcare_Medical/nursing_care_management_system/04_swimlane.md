# Swimlane Diagram — Nursing Care Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Bedside Nursing Shift"])

    subgraph Lane1["Staff Nurse / Bedside Nurse"]
        A1["Review Bed Board & Receive SBAR Handoff"]
        A2["Conduct Bedside Assessment & Chart Vitals"]
        A3["Scan Patient Wristband & Drug Barcode"]
        A4["Document Nursing Notes & Perform Care Plan"]
    end

    subgraph Lane2["System / eMAR & NEWS2 Engine"]
        S1["Compute NEWS2 Score & Morse Risk Rating"]
        S2{"NEWS2 Score >= 5 or Risk High?"}
        S3["Verify 5-Rights & Log BCMA Clearance"]
        S4["Dispatch High-Risk Deterioration Alert"]
        S5["Update Ward Flowsheet & Cost Center Billing"]
    end

    subgraph Lane3["Charge Nurse / Supervisor"]
        W1["Review Ward Acuity & Staff Allocation"]
        W2["Monitor High-Risk Patient Safety Bundles"]
    end

    subgraph Lane4["Attending Physician"]
        P1["Receive Emergency Rapid Response Alert"]
        P2["Update Clinical Orders & Treatment Plan"]
    end

    Finish(["End Shift Care Cycle"])

    Start --> A1 --> A2 --> S1 --> S2
    S2 -->|"No"| A3 --> S3 --> A4 --> S5 --> Finish
    S2 -->|"Yes"| S4 --> W1 --> W2 --> P1 --> P2 --> A3
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Staff Nurse / Bedside Nurse | Receives SBAR shift handoff, performs physical bedside assessments, charts vital signs, executes Barcode Medication Administration (BCMA), and documents nursing progress notes. |
| 2 | System / eMAR & NEWS2 Engine | Computes NEWS2 early warning scores, evaluates Morse/Braden risk ratings, verifies 5-Rights barcode safety clearance, dispatches deterioration alerts, and updates electronic flowsheets. |
| 3 | Charge Nurse / Supervisor | Reviews overall ward patient acuity, balances nurse-to-patient shift workloads, and monitors high-risk safety bundle compliance (e.g., fall wristbands, pressure injury prevention). |
| 4 | Attending Physician | Receives rapid response deterioration alerts triggered by high NEWS2 scores, evaluates patient clinical status, and updates medical orders or treatment plans accordingly. |
