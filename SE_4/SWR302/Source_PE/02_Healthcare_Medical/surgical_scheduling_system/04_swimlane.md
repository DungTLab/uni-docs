# Swimlane Diagram — Surgical Scheduling System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Surgical Booking Cycle"])

    subgraph Lane1["Attending Surgeon"]
        A1["Submit Surgical Booking & CPT Codes"]
        A2["Perform Surgery & Log Incision Cut Time"]
    end

    subgraph Lane2["Anesthesiologist & CSSD"]
        B1["Evaluate ASA Status & NPO Clearance"]
        B2["Sterilize Trays & Dispatch Pick Ticket"]
    end

    subgraph Lane3["System / OR & Conflict Engine"]
        S1["Verify OR Block & Check Staff Conflict"]
        S2{"Pre-Op Anesthesia Cleared & Trays Ready?"}
        S3["Flag Delay Warning & Alert OR Manager"]
        S4["Update Live Intra-Op Status Display Board"]
        S5["Log Turnover Timer & Update OR Metrics"]
    end

    subgraph Lane4["OR Suite Manager & PACU Nurse"]
        W1["Roster Surgical Team & Assign Room"]
        W2["Receive Patient & Monitor Aldrete Score"]
    end

    Finish(["End Surgery & PACU Recovery Cycle"])

    Start --> A1 --> S1 --> W1 --> B1 --> B2 --> S2
    S2 -->|"No"| S3 --> B1
    S2 -->|"Yes"| A2 --> S4 --> W2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Attending Surgeon | Submits surgical case booking requests with CPT procedure codes and estimated duration, performs surgery in OR room, and logs incision cut time. |
| 2 | Anesthesiologist & CSSD | Evaluates pre-op anesthesia fitness (ASA 1-5 classification), verifies NPO fasting status, sterilizes instrument trays, and dispatches case cart pick tickets to OR. |
| 3 | System / OR & Conflict Engine | Checks master OR block schedules for room/staff conflicts, evaluates anesthesia clearance tokens, updates real-time status display boards, and tracks room turnover timers. |
| 4 | OR Suite Manager & PACU Nurse | Rosters surgical team members (surgeons, anesthesiologist, scrub nurses), manages emergency bumps, receives post-op patients in PACU, and monitors Aldrete recovery scores. |
