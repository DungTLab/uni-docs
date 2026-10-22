# Swimlane Diagram — Contract Template & Clause Library System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Contract Assembly & Clause Negotiation Lifecycle"])

    subgraph Lane_Manager["Commercial Contract Manager"]
        M1["Input Deal Variables & Select Template Schema"]
        M2["Select Pre-Approved Fallback A/B Option in Negotiation"]
    end

    subgraph Lane_Curator["Senior Clause Curator / CLO"]
        C1["Curate Master Clause Library & Define Fallback Risk Rules"]
        C2["Review High-Risk Non-Standard Clause & Sign Off"]
    end

    subgraph Lane_System["Contract Template & Clause Library System"]
        S1["Assemble Dynamic DOCX Draft & Evaluate Logic Rules"]
        S2{"Passes Clause Risk Threshold & CLO Approval Matrix?"}
        S3["Flag High Legal Risk Hold & Route to CLO for Sign-Off"]
        S4["Recalculate Contract Risk Score & Auto-Approve Fallback"]
        S5["Update Template Governance Scorecard & Export Final DOCX"]
    end

    subgraph Lane_Integrations["AI NLP Engine & MS Word Add-In (OpenAI / Word Add-In)"]
        G1["Render Interactive Task Pane Sidebar inside MS Word"]
        G2["Execute AI Semantic Matching on Third-Party Contract Paper"]
    end

    Finish(["End Contract Authoring Cycle"])

    Start --> C1 --> M1 --> S1 --> G1 --> M2 --> G2 --> S2
    S2 -->|"No / High Risk Score > 50"| S3 --> C2 --> S5
    S2 -->|"Yes / Approved Risk < 50"| S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Commercial Contract Manager | Inputs deal metadata into the assembly questionnaire, selects pre-approved Fallback A or Fallback B clauses during counterparty negotiations, and ingests third-party paper. |
| 2 | Senior Clause Curator / CLO | Curates master primary terms, defines fallback clause drafting rationale, reviews high-risk contract deviations ($> 50/100$), and signs off on non-standard terms. |
| 3 | Contract Template & Clause Library System | Evaluates conditional template assembly logic rules, generates customized DOCX contract drafts, calculates composite contract legal risk scores, enforces approval delegation matrices, tracks clause usage frequency, and exports governance scorecards. |
| 4 | AI NLP Engine & MS Word Add-In (OpenAI / Word Add-In) | Renders interactive sidebar task panes inside MS Word and Google Docs, and executes AI NLP semantic matching to extract and classify clauses in third-party paper. |
