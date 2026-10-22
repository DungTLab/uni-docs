# Swimlane Diagram — Livestock & Animal Husbandry Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Animal Health Screening"])

    subgraph Lane1["RFID Tag Reader & Scale"]
        R1["Scan Animal 15-Digit RFID Ear Tag"]
        R2["Capture Automated Body Weight on Chute Scale"]
    end

    subgraph Lane2["Livestock Management System"]
        SYS1["Retrieve Animal Medical History & Milk Records"]
        SYS2["Calculate Average Daily Gain & Compare Weight"]
        SYS3{"Body Temp > 39.5°C or SCC High?"}
        SYS4["Flag Animal as Suspected Contagious Illness"]
        SYS5["Assign Isolated Quarantine Pen & Lock Sales Permit"]
        SYS6["Update Animal Status to Healthy & Release Flag"]
    end

    subgraph Lane3["Veterinarian"]
        V1["Perform Physical Examination & Blood Swab Test"]
        V2{"Confirm Contagious Infectious Disease?"}
        V3["Prescribe Targeted Antibiotic & Log Milk Withdrawal"]
        V4["Execute Emergency Ring Vaccination & Notify Authority"]
    end

    subgraph Lane4["Livestock Farm Manager"]
        M1["Physically Transfer Animal to Quarantine Barn"]
        M2["Enforce Strict Biosecurity Sanitation Barriers"]
    end

    Finish(["End Process"])

    Start --> R1 --> R2 --> SYS1 --> SYS2 --> SYS3
    SYS3 -->|"Yes"| SYS4 --> SYS5 --> M1 --> V1 --> V2
    SYS3 -->|"No"| SYS6 --> Finish
    V2 -->|"Yes"| V3 --> V4 --> M2 --> Finish
    V2 -->|"No"| SYS6 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| RFID Tag Reader & Scale | Hardware Sensor Array | Scans 15-digit ISO RFID ear tags as livestock pass through handling chutes and captures real-time body weight metrics. |
| Livestock Management System | Central Platform | Queries medical history, calculates weight gain trends, evaluates fever and milk quality anomalies, flags contagious disease suspects, isolates permits, and logs withdrawal periods. |
| Veterinarian | Animal Health Officer | Conducts clinical examinations, performs diagnostic blood swabs, confirms infectious disease presence, prescribes antibiotics, and notifies government veterinary authorities. |
| Livestock Farm Manager | Operational Supervisor | Executes physical transfer of sick animals to quarantine barns and enforces biosecurity sanitation barriers across farm premises. |
