# Swimlane Diagram — Diagnostic Imaging Archive System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Diagnostic Imaging Exam"])

    subgraph Lane_Tech["Radiologic Technologist"]
        T1["Query RIS Modality Worklist & Perform Scan"]
        T2["Verify DICOM Header Metadata & Image QA"]
        T3["Transmit DICOM Series to PACS Server"]
    end

    subgraph Lane_Modality["Imaging Modality Gateway"]
        M1["Execute CT / MRI / X-Ray Acquisition"]
        M2["Package Standardized DICOM PDU C-STORE Files"]
        M3["Send DICOM C-STORE Payload over TCP/IP"]
    end

    subgraph Lane_PACS["Diagnostic Imaging Archive System"]
        S1["Ingest C-STORE Stream & Send C-STORE-RSP"]
        S2["Index DICOM Instance in PACS Hot NVMe Storage"]
        S3["Route DICOM Series to AI CAD Engine"]
        S4{"Is DICOM Transfer & AI CAD Analysis Complete?"}
        S5["Render 2D/3D Viewport & Overlay AI CAD Badges"]
        S6["Export HL7 Report & Archive Study to Cloud VNA"]
    end

    subgraph Lane_Radiologist["Radiologist"]
        R1["Open Study in Diagnostic Web Viewer"]
        R2["Review 2D Slices, 3D MPR & AI CAD Annotations"]
        R3["Bookmark Key Images & Sign-Off Radiology Report"]
    end

    Finish(["End Imaging Diagnostic Cycle"])

    Start --> T1 --> M1 --> M2 --> M3 --> S1 --> S2 --> S3 --> S4
    S4 -->|"Yes"| S5 --> R1 --> R2 --> R3 --> S6 --> Finish
    S4 -->|"No / Error"| T2 --> T3 --> S1
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Radiologic Technologist | Queries RIS Modality Worklists, performs imaging scan, verifies DICOM metadata consistency, and initiates C-STORE transfer. |
| 2 | Imaging Modality Gateway | Executes physical CT/MRI/X-Ray image acquisition, packages standardized DICOM PDU files, and streams C-STORE instance data over TCP/IP network. |
| 3 | Diagnostic Imaging Archive System | Ingests DICOM C-STORE streams, sends DICOM storage acknowledgments, indexes DICOM headers in hot NVMe storage, routes images to AI CAD engines, renders 2D/3D viewports, dispatches signed reports, and archives studies to Cloud VNA. |
| 4 | Radiologist | Opens studies in diagnostic web viewports, evaluates 2D/3D MPR reconstructions, inspects AI CAD overlays, bookmarks key images, and signs radiology diagnostic reports. |
