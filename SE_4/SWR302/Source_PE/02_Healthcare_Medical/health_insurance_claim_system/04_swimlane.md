# Swimlane Diagram — Health Insurance Claim System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Health Insurance Claim Lifecycle"])

    subgraph Lane1["Policyholder / Provider"]
        A1["Submit GOP Request or EDI 837 Claim"]
        A2["Receive EOB & Check Payment Receipt"]
    end

    subgraph Lane2["System / Adjudication & FWA Engine"]
        S1["Verify Member Eligibility & Plan Limits"]
        S2["Run Automated Adjudication Fee Rules"]
        S3{"FWA Risk Score > 70 or Manual Exception?"}
        S4["Calculate Copay, Deductible & Net Payout"]
        S5["Generate EOB Statement & Remittance EDI 835"]
    end

    subgraph Lane3["Claims Adjudicator"]
        B1["Review Flagged Claim & Medical Notes"]
        B2["Approve, Adjust, or Deny Claim"]
    end

    subgraph Lane4["Finance & Banking Gateway"]
        P1["Batch Approved Claims for Settlement"]
        P2["Execute EFT Direct Deposit to Bank Account"]
    end

    Finish(["End Health Insurance Claim Lifecycle"])

    Start --> A1 --> S1 --> S2 --> S3
    S3 -->|"Yes"| B1 --> B2 --> S4
    S3 -->|"No"| S4 --> S5 --> P1 --> P2 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Policyholder / Provider | Submits pre-authorization GOP requests or electronic EDI 837 claim packages, receives Explanation of Benefits (EOB) statements, and checks bank deposit receipts. |
| 2 | System / Adjudication & FWA Engine | Verifies member policy eligibility, runs automated adjudication rules against contracted fee schedules, calculates deductibles and copays, screens FWA fraud scores, and generates EOBs. |
| 3 | Claims Adjudicator | Reviews complex claims or high-risk FWA alerts flagged by the system, inspects hospital medical necessity notes, and issues manual approvals or denial decisions. |
| 4 | Finance & Banking Gateway | Batches approved claims for payment, generates Electronic Funds Transfer (EFT / ACH) instructions, and executes direct deposit payouts to hospital or member bank accounts. |
