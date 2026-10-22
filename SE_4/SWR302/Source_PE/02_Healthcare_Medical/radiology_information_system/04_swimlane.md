# Swimlane Diagram — Radiology Information System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Clinical Radiology Requisition"])

    subgraph Lane1["Ordering Physician"]
        A1["Submit Radiology Order & Clinical Info"]
        A2["Receive Critical Alert Notification"]
        A3["Acknowledge Critical Result Receipt"]
        A4["Review Signed Radiology Report"]
    end

    subgraph Lane2["Radiology Receptionist & Technologist"]
        B1["Check In Patient & Confirm Safety Vitals"]
        B2["Call Patient & Query DICOM MWL"]
        B3["Perform Imaging Scan & Log Exposure"]
    end

    subgraph Lane3["System / DICOM MWL & PACS Gateway"]
        S1["Generate Accession & DICOM MWL Payload"]
        S2{"Critical Finding Identified?"}
        S3["Link PACS DICOM Images & Update Worklist"]
        S4["Dispatch Multi-Channel Critical Alert"]
        S5["Publish Report to HIS/EHR & Patient Portal"]
    end

    subgraph Lane4["Consultant Radiologist"]
        C1["Open Unread Study & Launch PACS Viewer"]
        C2["Dictate Findings & Author Impression"]
        C3["Electronically Sign Diagnostic Report"]
    end

    Finish(["End Radiology Diagnostic Flow"])

    Start --> A1 --> S1 --> B1 --> B2 --> B3 --> S3 --> C1 --> C2 --> S2
    S2 -->|"Yes"| S4 --> A2 --> A3 --> C3 --> S5 --> A4 --> Finish
    S2 -->|"No"| C3 --> S5 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Ordering Physician | Submits imaging requisition with clinical indications, receives urgent notifications for critical findings, acknowledges critical alerts, and accesses final signed radiology reports. |
| 2 | Radiology Receptionist & Technologist | Performs patient check-in, verifies pregnancy/contrast safety, queries scanner console via DICOM MWL, performs the imaging scan, and logs radiation exposure parameters. |
| 3 | System / DICOM MWL & PACS Gateway | Generates unique Accession Numbers, populates DICOM Modality Worklists, links incoming DICOM study images from PACS, evaluates critical alert flags, and transmits signed reports to HIS/EHR. |
| 4 | Consultant Radiologist | Opens unread exams from worklist, inspects PACS DICOM images, dictates findings via voice recognition, flags critical findings if present, and applies electronic signature to finalize the report. |
