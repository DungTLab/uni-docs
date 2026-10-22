# Swimlane Diagram — Medical Staff Scheduling System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Monthly Roster Planning Cycle"])

    subgraph Lane1["Medical Staff Member"]
        A1["Submit Shift Preferences & Blackout Dates"]
        A2["Receive Mobile Roster & Request Peer Swap"]
        A3["Clock In via Biometric Scanner at Shift Start"]
    end

    subgraph Lane2["System / Roster & Rule Engine"]
        S1["Load Par Levels & Run Constraint Solver"]
        S2{"Fatigue Rules & Rest Intervals Passed?"}
        S3["Highlight Scheduling Conflict & Vacancies"]
        S4["Publish Roster & Push Mobile App Sync"]
        S5["Match Attendance & Calculate Payroll Differential"]
    end

    subgraph Lane3["Roster Administrator / Chief Doctor"]
        W1["Configure Par Levels & Skill Requirements"]
        W2["Review Solver Draft & Adjust Coverage"]
        W3["Approve Published Roster & Swap Tickets"]
    end

    subgraph Lane4["HR & Payroll System"]
        P1["Receive Verified Work Hours & Overtime Log"]
        P2["Process Payroll & Issue Wage Payments"]
    end

    Finish(["End Monthly Roster Cycle"])

    Start --> A1 --> W1 --> S1 --> S2
    S2 -->|"No"| S3 --> W2 --> S1
    S2 -->|"Yes"| W3 --> S4 --> A2 --> A3 --> S5 --> P1 --> P2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Medical Staff Member | Submits monthly shift preferences and blackout dates, views published rosters on mobile app, initiates peer shift swaps, and completes biometric clock-in at shift start. |
| 2 | System / Roster & Rule Engine | Loads department staffing par levels, runs AI/heuristic constraint solver, audits ACGME fatigue and rest rules, broadcasts roster updates to mobile apps, and calculates overtime/night pay differentials. |
| 3 | Roster Administrator / Chief Doctor | Configures department shift templates and skill matrices, evaluates draft roster solver outputs, resolves staffing vacancy conflicts, and approves published rosters and shift swaps. |
| 4 | HR & Payroll System | Ingests verified work hour logs, overtime hours, and night shift differential summaries from the scheduling system to process monthly staff payroll disbursement. |
