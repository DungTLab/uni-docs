# Context Diagram — Electronic Health Record (EHR) System

## Mermaid Code

```mermaid
flowchart LR
    Patient["Patient / Citizen"]
    Physician["Physician / Clinical Specialist"]
    Nurse Caregiver["Nurse / Care Coordinator"]
    HMS["Hospital Management System (HMS)"]
    LIS["Laboratory Information System (LIS)"]
    PACS["PACS / Radiology Imaging System"]
    PharmacyGateway["Pharmacy & E-Prescription Gateway"]
    InsurancePortal["National Health Insurance Portal"]
    MOHDataCenter["Ministry of Health Data Center"]

    System(("Electronic Health Record System"))

    Patient -->|"grants access consent & views health profile"| System
    System -->|"provides unified health history & immunization records"| Patient

    Physician -->|"inputs clinical encounter notes & diagnosis"| System
    System -->|"displays longitudinal medical timeline & allergy alerts"| Physician

    NurseCaregiver -->|"enters vital sign trends & care plan updates"| System
    System -->|"delivers daily care instructions & drug administration logs"| NurseCaregiver

    HMS -->|"transmits patient registration & admission/discharge events"| System
    System -->|"returns centralized patient EHR ID & consent token"| HMS

    LIS -->|"pushes laboratory test values & pathology reports"| System
    System -->|"acknowledges lab result ingestion & links to encounter"| LIS

    PACS -->|"sends DICOM image metadata & viewable study URLs"| System
    System -->|"acknowledges radiology imaging link receipt"| PACS

    System -->|"transmits validated e-prescription details"| PharmacyGateway
    PharmacyGateway -->|"returns prescription fulfillment & dispensing status"| System

    System -->|"submits clinical summary for insurance claim audit"| InsurancePortal
    InsurancePortal -->|"returns claim pre-authorization & coverage status"| System

    System -->|"exports anonymized disease surveillance data"| MOHDataCenter
    MOHDataCenter -->|"acknowledges health statistics compliance"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Patient / Citizen | Primary | Privacy consent preferences, emergency contact details, self-reported symptoms, wearable fitness metrics. | Unified lifetime health timeline, lab test results, vaccination passports, doctor consultation summaries. | Individual subject of care controlling health record consent and accessing personal health record (PHR) portal. |
| 2 | Physician / Clinical Specialist | Primary | Clinical consultation notes, ICD-10/ICD-11 diagnostic codes, treatment plans, e-prescriptions, referral letters. | Comprehensive patient health history, vital sign trends, drug-allergy conflict alerts, past diagnostic reports. | Licensed medical doctor reviewing historical records and entering new clinical encounters. |
| 3 | Nurse / Care Coordinator | Primary | Triage vital signs, nursing assessment notes, medication administration logs, chronic care plan tracking. | Care plan objectives, active medication schedules, patient allergy warnings, physician ordering notes. | Healthcare professional executing care plans and capturing routine clinical observations. |
| 4 | Hospital Management System (HMS) | Supporting | Patient Master Index (MPI) demographics, hospital admission/discharge/transfer (ADT) events, clinic visit schedules. | Global EHR identifier (UUID), cross-institutional medical history index, patient consent status. | Enterprise hospital administrative software integrating encounter events with the central EHR repository. |
| 5 | Laboratory Information System (LIS) | Supporting | Structured lab test results (Loinc codes), specimen status, blood chemistry values, microbiology reports. | Encounter binding tokens, lab order confirmation acknowledgments. | External laboratory system pushing automated lab test values into the patient's record. |
| 6 | PACS / Radiology Imaging System | Supporting | DICOM image study metadata, radiologist interpretation notes, viewable image web URLs (WADO-RS). | Patient global EHR ID, imaging order binding references. | Picture Archiving and Communication System transmitting X-ray, MRI, and CT scan reports. |
| 7 | Pharmacy & E-Prescription Gateway | Supporting | Drug dispensing confirmation status, medication fulfillment timestamps, pharmacy provider details. | Structured electronic prescriptions (e-Rx), active medication lists, drug interaction alerts. | Centralized or regional pharmacy gateway processing electronic prescription orders. |
| 8 | National Health Insurance Portal | Supporting | Insurance coverage validation, benefit package limits, claim audit pre-authorizations. | Clinical diagnosis codes, procedure codes (CPT), itemized encounter summary documents. | Governmental or private health insurance system auditing clinical necessity for reimbursement. |
| 9 | Ministry of Health Data Center | Regulatory | Mandatory disease tracking standards, reporting data schemas, public health notifications. | Anonymized communicable disease statistics, national vaccination indexes, population health metrics. | Governmental health authority managing national electronic health registries and epidemic surveillance. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Electronic Health Record (EHR) System** serves as a centralized, standardized, longitudinal health information repository that aggregates patient health data across lifetime encounters, clinical specialties, laboratories, imaging centers, and hospitals.

**Inside System Scope:**
- Aggregation and maintenance of patient longitudinal health records, Master Patient Index (MPI), and consent management.
- Electronic documentation of clinical encounters, ICD-10 diagnostic coding, clinical progress notes, and treatment plans.
- HL7 FHIR-compliant integration of diagnostic data (LIS lab results, PACS radiology metadata, pathology documents).
- Centralized medication profile management, drug-allergy and drug-drug interaction safety checks, and e-prescription issuance.
- Granular patient privacy consent tracking (HIPAA/GDPR compliant) and cross-institutional Health Information Exchange (HIE).
- Comprehensive immutable audit logging of every record access, edit, export, and consent modification.

**Outside System Scope:**
- Direct physical execution of laboratory specimen testing and raw DICOM image file rendering (delegated to LIS and PACS hardware).
- Hospital financial ledger management, bed allocation, and cashier billing (delegated to enterprise HMS).
- Retail drug inventory management and physical drug picking (delegated to Pharmacy Management Systems).
- Direct adjudication of insurance financial payouts (delegated to Health Insurance Portals).
