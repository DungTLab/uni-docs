# Swimlane Diagram — Patient Feedback & Complaint System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Patient Feedback Submission"])

    subgraph Lane_Patient["Patient / Hospital Visitor"]
        P1["Submit Post-Discharge Survey / Grievance"]
        P2["Receive Instant Intake Receipt & Tracking No"]
        P3["Receive Formal Resolution Letter"]
    end

    subgraph Lane_Ombudsman["Patient Ombudsman"]
        O1["Review Intake Queue & Contact Patient"]
        O2["Conduct Clinical Chart & Staff Interviews"]
        O3["Draft Official Resolution Response Letter"]
    end

    subgraph Lane_System["Patient Feedback & Complaint System"]
        S1["Ingest Survey & Run NLP Sentiment Analysis"]
        S2{"Is Grievance High-Liability Legal Risk?"}
        S3["Escalate Case to Legal Risk Management"]
        S4["Assign Case SLA Countdown & Task Department"]
        S5["Calculate NPS & Update Quality Heatmaps"]
    end

    subgraph Lane_DeptManager["Department Head"]
        D1["Investigate Root Cause & Staff Performance"]
        D2["Execute Departmental Corrective Actions"]
    end

    Finish(["End Grievance Resolution Cycle"])

    Start --> P1 --> S1 --> S2
    S2 -->|"Yes"| S3 --> O1 --> O2 --> D1 --> D2 --> O3 --> P3 --> S5 --> Finish
    S2 -->|"No / Standard"| S4 --> P2 --> O1 --> O2 --> D1 --> D2 --> O3 --> P3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Patient / Hospital Visitor | Submits post-discharge surveys or formal grievances, receives instant intake receipt confirmations with tracking numbers, and receives final resolution letters. |
| 2 | Patient Ombudsman | Reviews intake queues, conducts patient phone interviews, reviews clinical charts, interviews staff, and drafts official resolution response letters. |
| 3 | Patient Feedback & Complaint System | Ingests feedback data, executes NLP sentiment analysis, evaluates legal liability threat keywords, escalates high-risk cases to Legal, manages SLA countdowns, and calculates NPS metrics. |
| 4 | Department Head | Investigates root causes of departmental complaints, conducts staff coaching, executes corrective actions, and submits remediation evidence. |
