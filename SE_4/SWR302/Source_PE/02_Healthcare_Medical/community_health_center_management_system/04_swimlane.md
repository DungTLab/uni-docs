# Swimlane Diagram — Community Health Center Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Commune Health Station Care"])

    subgraph Lane1["Commune Resident / Patient"]
        A1["Scan Citizen ID / VSS Card at Station"]
        A2["Receive Essential Drugs & Follow-Up Advice"]
    end

    subgraph Lane2["Health Station Nurse / Midwife"]
        B1["Log Baseline Vitals & Check EPI/NCD Status"]
        B2["Administer Vaccine or Dispense Essential Meds"]
    end

    subgraph Lane3["System / CHC & VSS Engine"]
        S1["Verify Primary BHYT & Load Family Record"]
        S2{"Condition Exceeds Station Capability or Severe?"}
        S3["Issue BHYT Hospital Referral Form"]
        S4["Decrement Drug Inventory & Queue VSS XML"]
        S5["Schedule Next NCD Checkup & Send SMS"]
    end

    subgraph Lane4["Health Station Doctor"]
        C1["Examine Patient & Code ICD-10 Diagnosis"]
        C2["Prescribe Essential Drugs or Transfer Order"]
    end

    Finish(["End Commune Health Station Visit"])

    Start --> A1 --> S1 --> B1 --> C1 --> C2 --> S2
    S2 -->|"Yes"| S3 --> B1
    S2 -->|"No"| S4 --> B2 --> A2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Commune Resident / Patient | Scans Citizen ID or VSS insurance card at station desk, receives primary consultation and vaccinations, receives essential drug packs, and departs. |
| 2 | Health Station Nurse / Midwife | Records baseline vital signs (BP, HR, Temp, Glucose), verifies child EPI vaccine schedules, dispenses prescribed essential drugs, and logs vaccination/ANC details. |
| 3 | System / CHC & VSS Engine | Validates primary BHYT Tuyến Xã coverage, loads electronic Family Health Records, screens referral thresholds, decrements drug stock, queues VSS XML files, and sends SMS reminders. |
| 4 | Health Station Doctor | Examines primary care patients, codes ICD-10 diagnoses, manages chronic NCD control targets, prescribes essential drugs from station inventory, or issues upper-level hospital referrals. |
