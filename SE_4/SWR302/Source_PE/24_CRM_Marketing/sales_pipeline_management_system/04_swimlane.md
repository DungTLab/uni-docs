# Swimlane Diagram — Sales Pipeline Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Sales Pipeline Deal Cycle"])

    subgraph Lane_AE["Account Executive (AE)"]
        A1["Advance Deal Card to Proposal Stage on Kanban"]
        A2["Build CPQ Quote & Request 15% Discount Approval"]
    end

    subgraph Lane_Buyer["Prospective Buyer"]
        B1["Review Interactive Sales Proposal & CPQ Quote"]
        B2["Execute E-Signature on Contract Package"]
    end

    subgraph Lane_System["Sales Pipeline Management System"]
        S1["Verify Stage Exit Criteria & Mandatory Fields"]
        S2["Auto-Approve Discount & Generate Contract PDF"]
        S3{"Passes Discount Approval & Contract Signature?"}
        S4["Flag Discount Violation & Route to Sales Director"]
        S5["Advance Deal to Closed-Won & Calculate Commission"]
        S6["Update AI Revenue Forecast & Sales Velocity"]
    end

    subgraph Lane_ESign["E-Signature Gateway (DocuSign)"]
        E1["Dispatch DocuSign Envelope to Buyer Email"]
        E2["Return Signed Contract PDF & Completed Webhook"]
    end

    Finish(["End Sales Pipeline Cycle"])

    Start --> A1 --> S1 --> A2 --> S2 --> E1 --> B1 --> B2 --> E2 --> S3
    S3 -->|"No / Rejected"| S4 --> A2
    S3 -->|"Yes / Completed"| S5 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Account Executive (AE) | Advances deal cards across Kanban pipeline stages, verifies mandatory exit criteria, builds multi-line CPQ price quotes, requests discount approvals, and dispatches contracts. |
| 2 | Prospective Buyer | Reviews interactive web proposals and CPQ pricing quotes, approves pricing terms, and signs legal contract agreements via e-signature. |
| 3 | Sales Pipeline Management System | Verifies deal stage exit criteria, enforces automated discount approval workflows, generates contract PDFs, dispatches e-signature packages, processes completed signature webhooks, auto-advances deals to Closed-Won, calculates sales rep commissions, and updates AI revenue forecasts. |
| 4 | E-Signature Gateway (DocuSign) | Delivers e-signature envelope invitations to buyer email addresses, collects cryptographic digital signatures, and streams completed contract PDF webhooks back to the system. |
