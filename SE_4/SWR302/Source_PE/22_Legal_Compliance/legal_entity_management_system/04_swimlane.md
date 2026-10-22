# Swimlane Diagram — Legal Entity Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Statutory Annual Filing"])

    subgraph Lane1["Corporate Paralegal"]
        P1["Initiate Annual Report Filing Review"]
        P2["Verify Incumbency & Registered Agent Info"]
        P3["Submit Electronic Filing Payload"]
        P4["Upload Filing Receipt to Minute Book"]
    end

    subgraph Lane2["System"]
        S1["Retrieve Upcoming Filing Deadlines"]
        S2["Validate Entity Data & Tax Calculation"]
        S3{"Validation Passed?"}
        S4["Transmit Filing to State Registry API"]
        S5["Update Entity Good Standing Status"]
        S6["Send Error Alert to Paralegal"]
    end

    subgraph Lane3["State Commercial Registry"]
        R1["Process Corporate Annual Report"]
        R2{"Registry Approval?"}
        R3["Issue Certificate of Good Standing"]
        R4["Issue Filing Rejection Notice"]
    end

    Finish(["End Filing Process"])

    Start --> P1 --> S1 --> P2 --> S2 --> S3
    S3 -->|"Yes"| P3 --> S4 --> R1 --> R2
    S3 -->|"No"| S6 --> P2
    R2 -->|"Approved"| R3 --> S5 --> P4 --> Finish
    R2 -->|"Rejected"| R4 --> S6
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Paralegal | Reviews upcoming state filing tasks, verifies active officer lists and registered agent addresses, triggers electronic filing transmission, and archives confirmation receipts. |
| 2 | System | Automates deadline tracking, computes statutory franchise tax fees, validates data integrity, handles API payloads to state portals, and updates internal legal entity health status. |
| 3 | State Commercial Registry | State authority platform that processes incoming corporate annual reports, validates fees, issues Certificate of Good Standing, or rejects invalid filings. |
