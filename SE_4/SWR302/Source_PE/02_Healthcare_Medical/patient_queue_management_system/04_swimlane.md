# Swimlane Diagram — Patient Queue Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Patient Queue Entry"])

    subgraph Lane1["Outpatient / Patient"]
        A1["Scan Citizen ID at Kiosk & Get Ticket"]
        A2["Monitor Mobile Zalo Queue Position"]
        A3["Enter Consultation Room Upon Audio Call"]
    end

    subgraph Lane2["Kiosk Clerk / Receptionist"]
        B1["Assist Express Priority Overrides"]
        B2["Reactivate Returned No-Show Tickets"]
    end

    subgraph Lane3["System / PQMS Engine"]
        S1["Evaluate Priority Rules & Assign Ticket Tier"]
        S2{"Patient Responds Within 3 Audio Calls?"}
        S3["Mark Ticket No-Show & Hold for 30 Mins"]
        S4["Update TV Matrix & Synthesize Audio Call"]
        S5["Dispatch Zalo SMS Alert & Queue Transfer"]
    end

    subgraph Lane4["Doctor / Room Staff"]
        C1["Click Call Next on Room Terminal"]
        C2["Examine Patient & Transfer Queue to Lab"]
    end

    Finish(["End Patient Queue Cycle"])

    Start --> A1 --> S1 --> A2 --> C1 --> S2
    S2 -->|"No"| S3 --> B2 --> C1
    S2 -->|"Yes"| S4 --> A3 --> C2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Outpatient / Patient | Scans Citizen ID (CCCD) or VSS insurance card at kiosk to receive a queue ticket, monitors live queue countdown on smartphone, and enters room upon audio callout. |
| 2 | Kiosk Clerk / Receptionist | Assists walk-in patients, manages express priority overrides for vulnerable patients (elderly, disabled), and reactivates returned no-show tickets. |
| 3 | System / PQMS Engine | Evaluates priority rules (Priority 1-4), updates central TV display matrix, synthesizes multi-lingual voice audio calls, dispatches Zalo/SMS proximity alerts, and logs no-shows. |
| 4 | Doctor / Room Staff | Triggers "Call Next Patient" from room call software terminal, conducts clinical examination, and transfers patient queue to diagnostic labs/radiology or cashier. |
