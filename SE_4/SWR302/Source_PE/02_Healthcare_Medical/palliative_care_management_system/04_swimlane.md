# Swimlane Diagram — Palliative Care Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Palliative Care Cycle"])

    subgraph Lane1["Patient / Family Caregiver"]
        A1["Register ACP & Input Self ESAS Pain"]
        A2["Receive Home Nursing Care & Pain Relief"]
        A3["Receive Post-Demise Bereavement Support"]
    end

    subgraph Lane2["Hospice Nurse / Social Worker"]
        B1["Conduct Bedside ESAS & Home Visit"]
        B2["Administer Opioid SC Syringe Driver"]
        B3["Log Grief Counseling & Support Session"]
    end

    subgraph Lane3["System / ESAS & Narcotic Engine"]
        S1["Compute ESAS Distress & OME Dosage"]
        S2{"ESAS Pain Score >= 7 or Acute Crisis?"}
        S3["Alert Physician for Opioid Dose Titration"]
        S4["Verify Dual-Signature Narcotic Audit"]
        S5["Schedule 1 3 6 12-Month Bereavement Logs"]
    end

    subgraph Lane4["Palliative Physician"]
        C1["Formulate MDT Care Plan & DNR Directive"]
        C2["Prescribe Opioids & PKI Digital Sign"]
    end

    Finish(["End Palliative & Bereavement Cycle"])

    Start --> A1 --> C1 --> B1 --> S1 --> S2
    S2 -->|"Yes"| S3 --> C2 --> S4 --> B2
    S2 -->|"No"| B2 --> A2 --> S5 --> B3 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient / Family Caregiver | Enrolls in palliative care, registers Advance Care Planning (ACP) preferences, submits self-reported ESAS pain scores, receives home care, and accesses family bereavement support. |
| 2 | Hospice Nurse / Social Worker | Conducts bedside ESAS symptom checks, administers subcutaneous opioid syringe drivers, logs dual-signature narcotic audits, and provides family grief counseling. |
| 3 | System / ESAS & Narcotic Engine | Calculates Total ESAS Distress scores, computes Oral Morphine Equivalent (OME) dosages, screens narcotic safety thresholds, alerts physicians for severe pain, and schedules bereavement logs. |
| 4 | Palliative Physician | Formulates multidisciplinary care plans across 4 WHO domains, signs legally binding DNR/MOLST directives, titrates opioid analgesics, and executes PKI digital signatures. |
