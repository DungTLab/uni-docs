# Swimlane Diagram — Drug Adverse Reaction Reporting System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start ADR Reporting Cycle"])

    subgraph Lane1["Reporter / Clinician"]
        A1["Submit Initial ADR Incident Report"]
        A2["Provide De-Challenge & Re-Challenge Follow-Up"]
    end

    subgraph Lane2["Pharmacovigilance Officer"]
        B1["Code Symptoms with MedDRA Terminology"]
        B2["Execute Naranjo Causality Assessment Score"]
        B3["Validate Statistical Safety Signal Dossier"]
    end

    subgraph Lane3["System / MedDRA & Signal Engine"]
        S1["Verify 4 Mandatory ICSR Elements"]
        S2["Calculate PRR/ROR Disproportionality"]
        S3{"Signal Validated & Case Serious?"}
        S4["Compile E2B R3 XML Safety Payload"]
        S5["Publish Drug Safety Advisory Alert"]
    end

    subgraph Lane4["Drug Regulatory Authority / WHO"]
        W1["Ingest E2B R3 XML ICSR Submission"]
        W2["Issue Mandatory Drug Recall / Label Change"]
    end

    Finish(["End ADR Reporting Cycle"])

    Start --> A1 --> S1 --> B1 --> B2 --> A2 --> S2 --> S3
    S3 -->|"Yes"| B3 --> S4 --> W1 --> S5 --> W2 --> Finish
    S3 -->|"No"| S4 --> W1 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Reporter / Clinician | Submits initial spontaneous adverse drug reaction incident report, detailing patient demographics, suspect drug lot numbers, reaction symptoms, and de-challenge responses. |
| 2 | Pharmacovigilance Officer | Reviews incoming cases, standardizes verbatim symptoms to MedDRA (LLT/PT) terms, scores Naranjo causality, and validates statistical safety signal dossiers. |
| 3 | System / MedDRA & Signal Engine | Validates 4 mandatory ICSR elements, auto-maps MedDRA hierarchies, runs PRR/ROR statistical disproportionality algorithms, builds E2B(R3) XML payloads, and dispatches public safety advisories. |
| 4 | Drug Regulatory Authority / WHO | Ingests E2B(R3) XML safety reports via AS2 gateway (FDA FAERS, EMA, WHO VigiBase), issues electronic acknowledgments (ACK), and orders mandatory drug batch recalls or black box label changes. |
