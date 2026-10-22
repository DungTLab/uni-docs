# Swimlane Diagram — Marketing Automation Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Nurture Campaign Execution"])

    subgraph Lane_Manager["Demand Gen Manager"]
        M1["Publish Visual Drip Campaign Workflow"]
        M2["Set Lead Scoring Rules & MQL Thresholds"]
    end

    subgraph Lane_Platform["Marketing Automation Platform"]
        S1["Ingest Form Submission & Trigger Entry Node"]
        S2["Evaluate Time Delay & Decision Node Split"]
        S3["Update Behavioral Lead Score (+25 Points)"]
        S4{"Is Lead Score >= MQL Threshold (80 Pts)?"}
        S5["Package MQL Contact Payload & Handoff to CRM"]
        S6["Enroll Contact in Continued Nurture Branch"]
    end

    subgraph Lane_MTA["Email MTA Service"]
        E1["Receive SMTP Mail Payload & Sign DKIM"]
        E2["Dispatch Email & Return Open/Click Webhooks"]
    end

    subgraph Lane_SalesRep["Sales Representative"]
        R1["Receive MQL Alert & Activity Timeline in CRM"]
        R2["Initiate Follow-Up Call & Sales Outreach"]
    end

    Finish(["End Automation Cycle"])

    Start --> M1 --> M2 --> S1 --> E1 --> E2 --> S2 --> S3 --> S4
    S4 -->|"Yes"| S5 --> R1 --> R2 --> Finish
    S4 -->|"No / Under Threshold"| S6 --> S2
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Demand Gen Manager | Publishes visual drip campaign workflows, establishes lead scoring point values, and sets MQL qualification thresholds. |
| 2 | Marketing Automation Platform | Ingests landing page form submissions, evaluates workflow delay timers and decision splits, updates behavioral lead scores, evaluates MQL thresholds, packages MQL contact payloads for CRM handoff, and routes unqualified contacts to continued nurture branches. |
| 3 | Email MTA Service | Receives mass email SMTP dispatches, signs DKIM/SPF headers, dispatches messages to recipients, and streams open/click/bounce webhooks back to the platform. |
| 4 | Sales Representative | Receives real-time MQL alerts and contact digital body language timelines in the CRM, initiating prompt sales outreach calls to qualified leads. |
