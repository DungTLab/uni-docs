# Swimlane Diagram — Maternal & Child Health Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Maternal & Child Health Lifecycle"])

    subgraph Lane1["Expectant Mother / Parent"]
        A1["Register Pregnancy LMP & Receive MCH Passport"]
        A2["Attend Routine ANC Visits & Receive Vaccine Shots"]
        A3["Bring Child for 0-5 Year Growth & Vaccine Checks"]
    end

    subgraph Lane2["Midwife / L&D Nurse"]
        B1["Log ANC Vitals & Administer Tetanus Shots"]
        B2["Chart Digital Partograph & Assist Delivery"]
        B3["Log 1/5-Min APGAR Score & Birth Record"]
    end

    subgraph Lane3["System / MCH & Growth Engine"]
        S1["Calculate EDD & Generate 40-Wk ANC Calendar"]
        S2["Evaluate High-Risk Screening & BP/Protein Rules"]
        S3{"High-Risk Preeclampsia or Obstructed Labor?"}
        S4["Plot WHO Growth Z-Scores & Schedule Vaccines"]
        S5["Trigger High-Risk Alert & Route to Specialist"]
        S6["Issue Birth Certificate & Sync Vaccine Registry"]
    end

    subgraph Lane4["Obstetrician / Pediatrician"]
        C1["Conduct High-Risk Pregnancy Evaluation"]
        C2["Perform Newborn Screening & WHO Growth Check"]
    end

    Finish(["End Child 5-Year Growth & Immunization Cycle"])

    Start --> A1 --> S1 --> B1 --> S2 --> S3
    S3 -->|"Yes"| S5 --> C1 --> B2
    S3 -->|"No"| B2 --> B3 --> S6 --> C2 --> A3 --> S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Expectant Mother / Parent | Registers pregnancy with LMP date, receives digital MCH Health Passport, attends routine ANC prenatal visits, and brings child for 0-5 year growth checks and vaccinations. |
| 2 | Midwife / L&D Nurse | Records ANC vitals and fundal height, administers maternal Tetanus Toxoid shots, charts labor progress on digital partograph, assists birth delivery, and logs newborn APGAR scores. |
| 3 | System / MCH & Growth Engine | Calculates Estimated Due Date (EDD), generates 40-week ANC visit calendar, evaluates preeclampsia/labor obstruction rules, plots WHO growth Z-scores, and syncs national vaccine registries. |
| 4 | Obstetrician / Pediatrician | Conducts high-risk pregnancy specialist consultations (preeclampsia, gestational diabetes), performs newborn metabolic/hearing screenings, and evaluates child WHO growth curves. |
