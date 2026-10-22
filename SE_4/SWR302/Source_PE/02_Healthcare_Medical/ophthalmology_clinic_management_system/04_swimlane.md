# Swimlane Diagram — Ophthalmology Clinic Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Ophthalmic Visit"])

    subgraph Lane1["Ophthalmic Patient"]
        A1["Check In & State Vision Symptoms"]
        A2["Undergo Visual Acuity & Auto-Refraction"]
        A3["Review Spectacle Rx & Order Glasses"]
    end

    subgraph Lane2["Optometrist / Refractionist"]
        B1["Measure Visual Acuity & Auto-Refraction"]
        B2["Measure IOP Tonometry & Pachymetry"]
    end

    subgraph Lane3["System / Diagnostic & Optical Engine"]
        S1["Verify Check-In & Route to Refraction"]
        S2["Calculate Corneal-Adjusted True IOP"]
        S3{"IOP > 21 mmHg or Retinal Pathology?"}
        S4["Issue Spectacle Rx & Route to Optical Shop"]
        S5["Trigger OCT DICOM & Glaucoma Referral"]
        S6["Generate Optical Invoice & Recall Alert"]
    end

    subgraph Lane4["Ophthalmologist"]
        C1["Conduct Slit-Lamp & Retinal Exam"]
        C2["Analyze OCT DICOM Scans & Compute IOL"]
        C3["Formulate Ocular Treatment & Surgery Plan"]
    end

    Finish(["End Ophthalmic Visit Cycle"])

    Start --> A1 --> S1 --> A2 --> B1 --> B2 --> S2 --> S3
    S3 -->|"No"| S4 --> A3 --> S6 --> Finish
    S3 -->|"Yes"| S5 --> C1 --> C2 --> C3 --> S4 --> A3 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Ophthalmic Patient | Checks in at reception, states vision complaints, undergoes automated refraction testing, reviews optical prescriptions, and selects spectacle frames/lenses at the optical shop. |
| 2 | Optometrist / Refractionist | Measures uncorrected and best-corrected visual acuity (VA OD/OS), captures auto-refraction parameters (Sphere, Cylinder, Axis, PD), and logs Tonometry intraocular pressure (IOP). |
| 3 | System / Diagnostic & Optical Engine | Verifies patient check-in, auto-calculates corneal-adjusted true IOP, evaluates glaucoma threshold alerts, streams OCT DICOM image scans, issues optical prescriptions, and dispatches optical shop job orders. |
| 4 | Ophthalmologist | Conducts slit-lamp biomicroscopy exams, inspects OCT retinal layer thickness scans, evaluates perimetry charts, calculates IOL power for cataract surgery, and prescribes ocular medications. |
