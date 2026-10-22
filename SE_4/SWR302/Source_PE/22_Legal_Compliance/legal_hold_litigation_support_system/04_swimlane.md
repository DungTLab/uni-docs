# Swimlane Diagram — Legal Hold & Litigation Support System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Legal Hold Workflow"])

    subgraph Lane1["Litigation Paralegal"]
        P1["Create Legal Hold Matter & Select Custodians"]
        P2["Broadcast Legal Hold Notice Email"]
        P3["Monitor Acknowledgment Matrix"]
        P4["Trigger Custodian Departure Protocol"]
    end

    subgraph Lane2["System"]
        S1["Generate Tracked Acknowledgment Links"]
        S2["Execute In-Place Preservation API Call"]
        S3["Suspend Cloud Data Deletion Rules"]
        S4{"Acknowledged in 5 Days?"}
        S5["Log Cryptographic Acknowledgment"]
        S6["Send Reminder & Escalation to Manager"]
        S7["Lock Laptop Data & Alert Counsel"]
    end

    subgraph Lane3["Data Custodian (Employee)"]
        E1["Receive Legal Hold Notice Email"]
        E2["Access SSO Portal & Review Guidelines"]
        E3["Submit Binding Electronic Acknowledgment"]
        E4["Submit Departure Resignation Notice"]
    end

    subgraph Lane4["IT Specialist"]
        T1["Verify O365 In-Place Hold Tag"]
        T2["Export E-Discovery Data Package"]
    end

    Finish(["End Legal Hold Process"])

    Start --> P1 --> S1 --> P2 --> E1 --> E2 --> E3 --> S5 --> S4
    P1 --> S2 --> S3 --> T1
    S4 -->|"Yes"| Finish
    S4 -->|"No"| S6 --> P3
    E4 --> P4 --> S7 --> T2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Litigation Paralegal | Creates new legal hold matter, defines target employee custodians, broadcasts legal hold notice emails, monitors compliance status, and manages departing custodian offboarding protocols. |
| 2 | System | Generates secure tracked links, calls cloud APIs (Office 365 / Google Vault) for in-place preservation, suspends automated deletion schedules, records cryptographic acknowledgments, and sends manager escalation alerts. |
| 3 | Data Custodian (Employee) | Receives legal hold notices, logs into SSO portal, reviews preservation scope, submits binding electronic acknowledgments, and flags impending departures. |
| 4 | IT Specialist | Verifies active in-place cloud preservation tags, monitors storage quotas, and exports defensible e-discovery data packages for external review. |
