# Swimlane Diagram — Agricultural Subsidy Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Application Process"])

    subgraph FarmerLane["Farmer / Agricultural Producer"]
        A1["Select Subsidy Program & Parcel"]
        A2["Upload Land Titles & Invoices"]
        A3["Submit Application Claim"]
        A4["Receive Payment Notification"]
        A5["File Appeal Request"]
    end

    subgraph SystemLane["Subsidy Management System"]
        S1["Validate Cadastral & ID Records"]
        S2{"Automated Check Passed?"}
        S3["Calculate Entitlement Amount"]
        S4["Generate Payout Batch"]
        S5["Log Transaction & Update Status"]
    end

    subgraph InspectorLane["Field Inspector"]
        I1["Receive Inspection Task"]
        I2["Perform On-Site Parcel Verification"]
        I3{"Crop & Area Valid?"}
        I4["Submit Inspection Approval"]
        I5["Submit Discrepancy Report"]
    end

    subgraph FinanceLane["Finance Officer"]
        F1["Review Approved File Bundle"]
        F2{"Budget & Rules OK?"}
        F3["Authorize Payout Batch Signature"]
        F4["Reject or Request Adjustment"]
    end

    subgraph BankLane["Bank / Payment Gateway"]
        B1["Receive Payment Batch Payload"]
        B2["Execute Direct Account Credit"]
        B3["Return Payment Confirmation Code"]
    end

    Finish(["End Process"])

    Start --> A1 --> A2 --> A3 --> S1 --> S2
    S2 -->|"Yes"| I1
    S2 -->|"No"| A5
    I1 --> I2 --> I3
    I3 -->|"Yes"| I4 --> S3 --> F1
    I3 -->|"No"| I5 --> F4
    F1 --> F2
    F2 -->|"Yes"| F3 --> S4 --> B1
    F2 -->|"No"| F4 --> A5
    B1 --> B2 --> B3 --> S5 --> A4 --> Finish
    A5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Farmer / Agricultural Producer | Initiates claim by selecting eligible subsidy programs, linking registered land parcels, uploading proof documentation, receiving electronic payout alerts, or submitting appeals for disputed decisions |
| 2 | Subsidy Management System | Performs automated cross-system identity and land registry validation, dispatches field verification tasks, calculates subsidy entitlement amounts according to official formulas, compiles payment batches, and maintains audit trails |
| 3 | Field Inspector | Receives mobile inspection tasks, conducts physical on-site or drone parcel reviews, verifies crop condition against claims, and logs approval or discrepancy findings into the system |
| 4 | Finance Officer | Conducts final administrative and compliance review of verified evaluation bundles, checks remaining budget allocations, and executes cryptographic signatures to authorize payment batches |
| 5 | Bank / Payment Gateway | Ingests secure ISO 20022 payment batches, processes direct fund credits into farmer bank accounts, and returns electronic bank confirmation codes to complete reconciliation |
