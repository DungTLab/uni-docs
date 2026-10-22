# Swimlane Diagram — Marketing Budget & Spend Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Marketing Budget & Spend Lifecycle"])

    subgraph Lane_CampaignManager["Marketing Campaign Manager"]
        M1["Submit Purchase Order (PO) Request & Attach Vendor Quote"]
        M2["Match Vendor Invoice against Approved PO & Deliverables"]
    end

    subgraph Lane_BudgetManager["Marketing Operations / Budget Manager"]
        B1["Configure Hierarchical Budget Allocation & GL Codes"]
        B2["Audit Month-End GAAP Accruals & Reallocate Budget Funds"]
    end

    subgraph Lane_System["Marketing Budget & Spend Management System"]
        S1["Ingest Daily Digital Ad Spend via APIs (Google / Meta)"]
        S2["Verify Remaining Campaign Budget & Threshold Rules"]
        S3{"Passes Budget Availability & Multi-Tier PO Approvals?"}
        S4["Reject PO Request & Prompt Budget Reallocation"]
        S5["Issue Official PO Number & Update Committed Spend"]
        S6["Calculate Month-End GAAP Accruals & Compute CAC / ROMI"]
    end

    subgraph Lane_ERP["Enterprise ERP & Financial Accounting System"]
        E1["Receive Approved PO Requisitions & Encumber Funds"]
        E2["Post Month-End Accrual Journal Entries & Clear Payments"]
    end

    Finish(["End Marketing Spend Cycle"])

    Start --> B1 --> S1 --> M1 --> S2 --> S3
    S3 -->|"No / Insufficient Budget"| S4 --> B2
    S3 -->|"Yes / Approved"| S5 --> E1 --> M2 --> S6 --> E2 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Marketing Campaign Manager | Submits PO requests with vendor quotes and GL expense codes, monitors campaign budget balances, matches vendor invoices against approved POs, and verifies proof of performance deliverables. |
| 2 | Marketing Operations / Budget Manager | Configures multi-level budget trees (BU -> Channel -> Campaign -> GL), monitors spend pacing velocity, audits month-end GAAP marketing accruals, and executes mid-quarter budget reallocations. |
| 3 | Marketing Budget & Spend Management System | Ingests daily digital ad spend APIs (Google/Meta/LinkedIn), verifies budget availability, routes POs through multi-tier threshold approval chains, issues PO numbers, updates committed vs actual spend, computes month-end un-invoiced accruals, and calculates CAC and ROMI multipliers. |
| 4 | Enterprise ERP & Financial Accounting System | Receives approved PO requisitions, encumbers committed funds in GL accounts, accepts month-end GAAP accrual journal entries, and clears vendor invoice payments (SAP / NetSuite). |
