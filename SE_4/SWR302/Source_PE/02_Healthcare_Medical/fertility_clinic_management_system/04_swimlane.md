# Swimlane Diagram — Fertility Clinic Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start IVF Treatment Cycle"])

    subgraph Lane1["Patient Couple / Female Partner"]
        A1["Register Couple Profile & Sign Legal Consents"]
        A2["Administer COH Hormone Injections & HCG Trigger"]
    end

    subgraph Lane2["IVF Nurse & Cycle Coordinator"]
        B1["Log TVUS Follicle Counts & E2 P4 Lab Values"]
        B2["Schedule OPU Procedure & Track Day 14 Beta-hCG"]
    end

    subgraph Lane3["System / IVF & Embryology Engine"]
        S1["Verify Legal Marriage Cert & Calculate HCG Hour"]
        S2{"Day 14 Beta-hCG Level > 5 mIU/mL?"}
        S3["Log Non-Pregnancy & Schedule Review Consultation"]
        S4["Grade Day 5 Blastocysts & Map Cryo Grid Coordinates"]
        S5["Confirm Clinical Pregnancy & Schedule 6-Wk OB Scan"]
    end

    subgraph Lane4["Clinical Embryologist & IVF Physician"]
        C1["Perform Transvaginal OPU & Process Semen Sample"]
        C2["Execute ICSI Micro-Injection & Embryo Transfer"]
    end

    Finish(["End Pregnancy & Birth Outcome Cycle"])

    Start --> A1 --> S1 --> B1 --> A2 --> B2 --> C1 --> C2 --> S4 --> B2 --> S2
    S2 -->|"No"| S3 --> Finish
    S2 -->|"Yes"| S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient Couple / Female Partner | Registers couple profiles, signs legal ART spouse consent forms, receives controlled ovarian stimulation hormone injections, and attends TVUS monitoring. |
| 2 | IVF Nurse & Cycle Coordinator | Tracks daily hormone injection calendars, logs TVUS follicle growth counts (R/L ovaries), schedules OPU retrieval times, and monitors Day 14 Beta-hCG blood tests. |
| 3 | System / IVF & Embryology Engine | Validates legal marriage certs, calculates HCG trigger hour (36h prior to OPU), ingests time-lapse morphokinetics, grades Day 5 blastocysts, and maps cryo-tank grid locations. |
| 4 | Clinical Embryologist & IVF Physician | Conducts transvaginal ultrasound-guided OPU retrieval, processes WHO semen samples, performs ICSI micro-injections, executes Embryo Transfer (SET), and prescribes luteal support. |
