# Swimlane Diagram — Dispute Resolution Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Dispute Resolution"])

    subgraph Lane1["In-House Litigation Counsel"]
        C1["Log Dispute Claim & Details"]
        C2["Perform Liability Exposure Assessment"]
        C3["Conduct Settlement Mediation Session"]
        C4["Finalize Settlement Agreement & Release"]
    end

    subgraph Lane2["System"]
        S1["Calculate Loss Reserve & EMV"]
        S2["Validate Pre-Approved Settlement Limit"]
        S3{"Limit Exceeded?"}
        S4["Send Authorization Task to General Counsel"]
        S5["Generate Settlement Payout Instruction"]
        S6["Update Case Status to RESOLVED"]
    end

    subgraph Lane3["General Counsel"]
        G1["Review Exposure & Settlement Terms"]
        G2{"Approve Settlement?"}
        G3["Sign Executive Settlement Authorization"]
        G4["Reject Settlement & Direct Litigation"]
    end

    subgraph Lane4["Finance Manager"]
        F1["Book Contingent Loss Reserve"]
        F2["Disburse Settlement Escrow Wire"]
    end

    Finish(["End Case Resolution"])

    Start --> C1 --> C2 --> S1 --> F1 --> C3 --> S2 --> S3
    S3 -->|"No"| C4
    S3 -->|"Yes"| S4 --> G1 --> G2
    G2 -->|"Approved"| G3 --> C4
    G2 -->|"Rejected"| G4 --> Finish
    C4 --> S5 --> F2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | In-House Litigation Counsel | Logs initial dispute claim, performs risk exposure analysis, conducts settlement negotiation sessions, and drafts final settlement release agreements. |
| 2 | System | Computes expected financial loss reserves, checks settlement authorization caps, routes executive sign-off tasks, triggers escrow wire tasks, and updates case audit history. |
| 3 | General Counsel | Reviews high-exposure dispute settlements, evaluates litigation strategy, grants executive approval or rejects settlements to proceed to court trial. |
| 4 | Finance Manager | Books GAAP/IFRS contingent loss reserves in corporate financial ledgers and executes wire payouts into third-party settlement escrow accounts. |
