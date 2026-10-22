# Swimlane Diagram — Agricultural Loan & Credit System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Loan Application Cycle"])

    subgraph BorrowerLane["Farmer / Agri-Borrower"]
        F1["Submit Digital Loan Application"]
        F2["Sign Electronic Loan Contract"]
        F3["Pay Harvest Bullet Installment"]
        F4["Request Disaster Loan Restructuring"]
    end

    subgraph SystemLane["Agricultural Loan & Credit System"]
        M1["Query CIC Credit Score & Satellite NDVI"]
        M2["Compute Hybrid Credit Score & LTV Cap"]
        M3{"Credit Score >= 600 & LTV Valid?"}
        M4["Generate Crop-Cycle Repayment Schedule"]
        M5["Process Disaster Restructuring & Insurance Claim"]
    end

    subgraph OfficerLane["Credit Officer / Underwriter"]
        A1["Review Underwriting Dossier & Risk Tier"]
        A2["Approve Loan Credit Limit & Interest Rate"]
        A3["Approve 12-Month Extension & Restructuring"]
    end

    subgraph SatelliteLane["Satellite Yield & Risk Engine"]
        S1["Fetch Historical Satellite NDVI & Flood Risk"]
        S2["Verify Crop Loss % for Restructuring"]
    end

    subgraph BankingLane["Core Banking & Payment Disbursement"]
        C1["Execute Direct Account Disbursement"]
        C2["Process Harvest Direct Debit Payment"]
    end

    Finish(["End Credit Operational Cycle"])

    Start --> F1 --> M1
    S1 --> M1 --> M2 --> M3
    M3 -->|"Approved"| A1 --> A2 --> F2 --> C1 --> M4 --> F3 --> C2 --> Finish
    M3 -->|"Rejected"| Finish
    F3 -->|"Crop Failure Event"| F4 --> S2 --> M5 --> A3 --> M4
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Farmer / Agri-Borrower | Submits digital loan application with farm GIS plot polygons, signs electronic loan contract via e-KYC/OTP, pays interest/principal installments, and requests restructuring during disaster events |
| 2 | Agricultural Loan & Credit System | Queries CIC credit bureau and satellite crop risk feeds, calculates hybrid credit scores ($300-850$), checks LTV limits ($70\%$), generates crop-cycle aligned repayment schedules, and processes restructuring |
| 3 | Credit Officer / Underwriter | Reviews credit scoring dossiers, approves credit limits and interest rates, verifies collateral land title encumbrances, and authorizes loan restructuring plans |
| 4 | Satellite Yield & Risk Engine | Provides historical multi-temporal NDVI vegetation indices, flood/drought risk scores, and verifies crop vegetation loss ($>60\%$) to confirm natural disaster damage |
| 5 | Core Banking & Payment Disbursement | Executes automated principal fund transfers to borrower bank accounts via Core Banking APIs and processes automated direct debit repayments following harvest |
