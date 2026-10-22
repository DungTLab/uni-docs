# Swimlane Diagram — AI-Assisted Diagnosis Support System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start DICOM Diagnostic Study Ingestion"])

    subgraph Lane1["Attending Radiologist / Physician"]
        A1["Open Study Worklist & Request Image Stream"]
        A2["Review AI Lesion Overlay & Toggle Grad-CAM Heatmap"]
        A3["Validate or Overrule AI Impression & Digitally Sign Report"]
    end

    subgraph Lane2["Hospital PACS & Modality"]
        B1["Stream DICOM Slices CT MRI X-Ray to AI System"]
        B2["Store AI DICOM Structured Report SR & Heatmap Overlay"]
    end

    subgraph Lane3["AIDSS Core AI Engine & XAI Module"]
        S1["Segment 3D Lesions & Compute Differential Diagnosis"]
        S2{"STAT Critical Finding Detected Pneumothorax / Hemorrhage?"}
        S3["Generate Standard Worklist AI Findings & BI-RADS Draft"]
        S4["Trigger STAT Emergency Alert & Push Study to Position #1"]
    end

    subgraph Lane4["Emergency Alert & Hospital EHR Sync"]
        C1["Dispatch Immediate SMS Push Alert to ER Trauma Team"]
        C2["Export FHIR DiagnosticReport to Enterprise Hospital EHR"]
    end

    Finish(["End Diagnostic Workflow & Sync Signed Report to EHR"])

    Start --> A1 --> B1 --> S1 --> S2
    S2 -->|"No"| S3 --> A2 --> A3 --> B2 --> C2 --> Finish
    S2 -->|"Yes"| S4 --> C1 --> A2 --> A3 --> B2 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Attending Radiologist / Physician | Opens diagnostic worklist, inspects AI 3D lesion segmentations, toggles Explainable AI (Grad-CAM) heatmaps, validates or overruling AI predictions, and signs structured reports. |
| 2 | Hospital PACS & Modality | Streams DICOM image series (CT, MRI, X-ray) to AIDSS and archives AI-annotated DICOM Structured Reports (SR) and secondary capture heatmap objects. |
| 3 | AIDSS Core AI Engine & XAI Module | Runs 3D U-Net neural networks for automated lesion segmentation, calculates 3D volumes and differential diagnosis probabilities, renders Grad-CAM heatmaps, and identifies STAT critical findings. |
| 4 | Emergency Alert & Hospital EHR Sync | Dispatches STAT emergency SMS/Push alerts for critical findings (Pneumothorax, Brain Hemorrhage) to ER trauma teams and syncs FHIR `DiagnosticReport` resources to enterprise EHRs. |
