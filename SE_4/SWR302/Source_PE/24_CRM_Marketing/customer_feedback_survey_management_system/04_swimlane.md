# Swimlane Diagram — Customer Feedback & Survey Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Survey Campaign Execution"])

    subgraph Lane_CXManager["CX Manager"]
        C1["Publish Survey Questionnaire & Set Event Triggers"]
        C2["Review Detractor Closed-Loop Recovery Status"]
    end

    subgraph Lane_System["Customer Feedback System"]
        S1["Verify Fatigue Rules & Trigger Dispatch"]
        S2["Receive Submitted Ratings & Run NLP Text Analytics"]
        S3{"Is Respondent an NPS Detractor (Score <= 6)?"}
        S4["Trigger High-Priority Detractor Ticket Payload"]
        S5["Update NPS Gauge Dashboard & Sync CRM Profile"]
    end

    subgraph Lane_Respondent["End Customer"]
        R1["Receive Email / SMS Survey Invitation"]
        R2["Submit 0-10 NPS Rating & Verbatim Comment"]
    end

    subgraph Lane_Helpdesk["Helpdesk Ticketing Platform"]
        H1["Ingest Detractor Ticket Payload (24-Hr SLA)"]
        H2["Execute Support Outreach & Close Ticket Recovery"]
    end

    Finish(["End Survey & Recovery Cycle"])

    Start --> C1 --> S1 --> R1 --> R2 --> S2 --> S3
    S3 -->|"Yes"| S4 --> H1 --> H2 --> C2 --> S5 --> Finish
    S3 -->|"No / Promoter"| S5
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | CX Manager | Publishes survey questionnaires, configures event trigger delays, monitors detractor recovery SLA compliance, and reviews NPS/CSAT executive trend dashboards. |
| 2 | Customer Feedback System | Evaluates survey fatigue rules, dispatches invitations, ingests customer ratings, executes NLP sentiment analytics, evaluates Detractor status (NPS 0-6), dispatches Helpdesk recovery payloads, and updates CRM contact profiles. |
| 3 | End Customer | Receives survey invitations via Email, SMS, or In-App widgets, submits 0-10 NPS or 1-5 CSAT ratings, and writes open-ended verbatim feedback comments. |
| 4 | Helpdesk Ticketing Platform | Receives automated high-priority detractor ticket payloads, assigns 24-hour closed-loop recovery SLAs to support managers, tracks agent outreach, and streams ticket closure webhooks back to the system. |
