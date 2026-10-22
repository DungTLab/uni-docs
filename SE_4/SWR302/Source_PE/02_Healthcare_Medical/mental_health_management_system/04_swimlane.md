# Swimlane Diagram — Mental Health Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Behavioral Care Intake"])

    subgraph Lane1["Mental Health Patient / Client"]
        A1["Complete Self-Reported PHQ-9 & Intake"]
        A2["Join Tele-Therapy Video Room"]
        A3["Review & Sign Personal Safety Plan"]
    end

    subgraph Lane2["System / Crisis Analytics Engine"]
        S1["Compute PHQ-9 & Evaluate Q9 Suicide Flag"]
        S2{"PHQ-9 Q9 > 0 or High Risk?"}
        S3["Schedule Routine Psychotherapy Session"]
        S4["Launch Mandatory C-SSRS & Dispatch 988 Alert"]
        S5["Sync Psychotropic Order & REMS Registry"]
    end

    subgraph Lane3["Clinical Psychologist / Therapist"]
        B1["Administer C-SSRS Suicide Risk Tool"]
        B2["Construct Stanley-Brown Safety Plan"]
        B3["Conduct CBT Therapy & Author BIRP Note"]
    end

    subgraph Lane4["Psychiatrist"]
        C1["Evaluate Involuntary Legal Hold Status"]
        C2["Prescribe Psychotropic Drug & Order Labs"]
    end

    Finish(["End Behavioral Care Cycle"])

    Start --> A1 --> S1 --> S2
    S2 -->|"No"| S3 --> A2 --> B3 --> S5 --> Finish
    S2 -->|"Yes"| S4 --> B1 --> B2 --> A3 --> C1 --> C2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Mental Health Patient / Client | Completes self-reported PHQ-9/GAD-7 intake questionnaires, participates in encrypted tele-health video sessions, and co-signs personal suicide safety plans. |
| 2 | System / Crisis Analytics Engine | Computes psychological screening scores, evaluates suicidal ideation flags (PHQ-9 Question 9), triggers mandatory C-SSRS workflows, dispatches emergency 988 alerts, and syncs e-prescriptions with REMS registries. |
| 3 | Clinical Psychologist / Therapist | Conducts structured C-SSRS suicide risk assessments, formulates Stanley-Brown Safety Planning Interventions, conducts CBT/DBT psychotherapy sessions, and documents BIRP progress notes. |
| 4 | Psychiatrist | Evaluates acute psychiatric emergencies, processes involuntary legal commitment orders (Baker Act/Section 5150), prescribes psychotropic medications, and orders metabolic lab monitoring. |
