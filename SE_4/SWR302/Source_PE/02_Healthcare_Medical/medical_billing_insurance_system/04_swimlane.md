# Swimlane Diagram — Medical Billing & Insurance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start: Patient Check-In & Service Completion"])

    subgraph Lane1["Patient / Policyholder"]
        C1["Present Insurance Card at Intake Counter"]
        C2["Review Itemized Bill & Pay Co-Pay Balance"]
        C3["Receive Official E-Invoice & Payment Receipt"]
    end

    subgraph Lane2["Medical Coder / Cashier"]
        P1["Initiate Real-Time Insurance Eligibility Check"]
        P2["Assign ICD-10 & CPT Codes to Captured Charges"]
        P3["Collect Patient Co-Pay & Issue Cashier Receipt"]
        P4["Investigate Denial CARC Code & File Claim Appeal"]
    end

    subgraph Lane3["Medical Billing & Insurance System"]
        S1["Verify Active Policy & Calculate Co-Pay / Deductible"]
        S2["Run Automated Claim Scrubber Validation"]
        S3["Generate EDI 837 Electronic Claim Batch"]
        S4["Parse EDI 835 ERA Payload & Match Claim Numbers"]
        S5{"Claim Approved by Payer?"}
        S6["Post Payment to Ledger & Issue Fiscal E-Invoice"]
    end

    subgraph Lane4["Insurance Clearinghouse / Payer"]
        G1["Receive EDI 270 Eligibility Inquiry & Return 271"]
        G2["Adjudicate EDI 837 Claim File against Policy Rules"]
        G3["Dispatch EDI 835 Remittance Advice & EFT Funds"]
    end

    Finish(["End: Revenue Cycle Transaction Completed"])

    Start --> C1 --> P1 --> S1 --> G1 --> S1 --> P2 --> S2 --> P3 --> C2 --> S3 --> G2 --> G3 --> S4 --> S5
    S5 -->|"No: Claim Denied"| P4 --> S3
    S5 -->|"Yes: Approved"| S6 --> C3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Patient / Policyholder | Presents insurance card during check-in, reviews itemized bill preview, pays out-of-pocket co-payment/deductible balance, and receives official fiscal e-invoice and payment receipts. |
| 2 | Medical Coder / Cashier | Initiates electronic eligibility check, maps clinical encounter documentation into standardized ICD-10 and CPT codes, collects patient co-payments at POS terminal, and investigates denied claims to file appeals. |
| 3 | Medical Billing & Insurance System | Performs automated coverage calculations, executes claim scrubbing validation, compiles ANSI X12 837 claim batches, parses incoming 835 ERA remittance advice files, posts approved payments, and dispatches tax e-invoices. |
| 4 | Insurance Clearinghouse / Payer | Processes real-time EDI 270/271 eligibility inquiries, adjudicates electronic EDI 837 medical claims, calculates benefit payouts, and dispatches EDI 835 remittance advice with EFT bank deposits. |
