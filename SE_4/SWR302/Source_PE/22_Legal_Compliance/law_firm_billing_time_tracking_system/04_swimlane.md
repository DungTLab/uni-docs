# Swimlane Diagram — Law Firm Billing & Time Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Law Firm Billing & Realization Cycle"])

    subgraph Lane_Attorney["Timekeeper Attorney"]
        A1["Approve Passive Desktop Time Entries & Select UTBMS Codes"]
        A2["Adjust Work-In-Progress (WIP) Narratives to Fix Block Billing"]
    end

    subgraph Lane_Partner["Managing Partner / Billing Specialist"]
        P1["Conduct Monthly Proforma Pre-Bill Review & Apply Write-Downs"]
        P2["Authorize IOLTA Retainer Trust Fund Earned Fee Drawdown"]
    end

    subgraph Lane_System["Law Firm Billing & Time Tracking System"]
        S1["Convert Desktop Activity to Billable 10th-Hr Time Entries"]
        S2{"Passes LEDES Validation & Client Billing Guidelines?"}
        S3["Flag Syntax Error & Block Billing Violation for Corrections"]
        S4["Compile Invoice & Export Validated LEDES 1998B File"]
        S5["Execute IOLTA Trust Transfer & Calculate Realization Rate"]
    end

    subgraph Lane_Clearinghouse["Legal e-Billing Clearinghouse (CounselLink)"]
        C1["Receive LEDES 1998B Invoice File & Audit Line Items"]
        C2["Issue Electronic Invoice Approval & Payment Clearance"]
    end

    Finish(["End Billing & Cash Collection Cycle"])

    Start --> A1 --> S1 --> P1 --> S2
    S2 -->|"No / Syntax Error or Block Billing"| S3 --> A2 --> P1
    S2 -->|"Yes / Approved & Valid"| S4 --> C1 --> C2 --> P2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Timekeeper Attorney | Approves suggested passive desktop activity time entries, selects UTBMS task codes, records billable hours in 10th-of-an-hour increments, and adjusts narratives to resolve block billing flags. |
| 2 | Managing Partner / Billing Specialist | Conducts monthly pre-bill proforma reviews, applies fee write-downs and write-offs, authorizes formal invoice generation, and approves IOLTA retainer trust fund earned fee drawdowns. |
| 3 | Law Firm Billing & Time Tracking System | Converts desktop window activity into billable time entries, validates narratives against corporate billing rules, compiles itemized invoices, formats pipe-delimited LEDES 1998B text files, executes IOLTA trust-to-operating bank transfers, and computes law firm realization rates. |
| 4 | Legal e-Billing Clearinghouse (CounselLink) | Receives electronic LEDES 1998B fee invoices via REST APIs, audits UTBMS line items against client rate caps, and issues electronic invoice approval and payment clearance tokens. |
