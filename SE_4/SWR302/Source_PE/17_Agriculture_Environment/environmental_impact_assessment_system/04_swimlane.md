# Swimlane Diagram — Environmental Impact Assessment System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start EIA Application Workflow"])

    subgraph ProponentLane["Project Developer / Proponent"]
        A1["Submit Project Profile & Scoping Report"]
        A2["Contract Consultant & Fund Baseline Survey"]
        A3["Host Public Town Hall Hearing"]
        A4["Revise EIA Dossier per Council Notes"]
        A5["Receive EIA Approval License"]
    end

    subgraph SystemLane["Environmental Impact Assessment System"]
        S1["Classify EIA Tier Level & Ingest GIS"]
        S2["Run Dispersion Simulation Engine"]
        S3{"Major Pollution Threshold Exceeded?"}
        S4["Compile Appraisal Dossier Bundle"]
        S5["Publish Approved License & EMP Entry"]
    end

    subgraph PublicLane["Public Community & Affected Residents"]
        P1["Access Online EIA Executive Summary"]
        P2["Submit Written Community Comments"]
    end

    subgraph ConsultantLane["Environmental Consultant"]
        C1["Execute Baseline Air/Water Surveys"]
        C2["Formulate Environmental Management Plan"]
    end

    subgraph AgencyLane["EIA Appraisal Council & Regulatory Agency"]
        G1["Review Preliminary Dossier"]
        G2["Convene Appraisal Council Assembly"]
        G3{"Approve EIA Report?"}
        G4["Issue Digital EIA License"]
        G5["Issue Rejection Notice"]
    end

    Finish(["End Workflow"])

    Start --> A1 --> S1 --> A2 --> C1 --> S2 --> S3
    S3 -->|"Yes"| C2 --> S2
    S3 -->|"No"| P1 --> P2 --> A3 --> C2 --> S4 --> G1 --> G2 --> G3
    G3 -->|"Yes"| A4 --> G4 --> S5 --> A5 --> Finish
    G3 -->|"No"| G5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Project Developer / Proponent | Submits project investment profile and scoping report, contracts accredited environmental consultants, funds baseline monitoring surveys, hosts public town hall hearings, revises EIA reports per council feedback, and receives official EIA licenses |
| 2 | Environmental Impact Assessment System | Classifies project EIA risk tiers (Group I/II), runs mathematical air/water dispersion simulation models, verifies QCVN legal pollution thresholds, compiles appraisal dossier packages, and publishes approved EIA licenses to the public registry |
| 3 | Public Community & Affected Residents | Accesses non-technical project EIA executive summaries online, submits written community feedback regarding environmental risks, and participates in public consultation town hall meetings |
| 4 | Environmental Consultant | Conducts baseline air, water, soil, and noise field sampling surveys, processes laboratory test assays, formats computer dispersion models, and formulates the Environmental Management Plan (EMP) |
| 5 | EIA Appraisal Council & Regulatory Agency | Reviews preliminary EIA dossiers, convenes formal appraisal council assemblies, questions developers, votes on technical report approval, and digitally signs official legal EIA Approval Licenses |
