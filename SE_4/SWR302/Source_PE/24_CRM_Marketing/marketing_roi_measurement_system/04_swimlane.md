# Swimlane Diagram — Marketing ROI Measurement System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Financial Marketing ROI Audit"])

    subgraph Lane_Analyst["Marketing ROI Analyst"]
        A1["Configure Data-Driven Attribution Weights"]
        A2["Reconcile Ad Network API Spend with ERP Invoices"]
    end

    subgraph Lane_System["Marketing ROI Measurement System"]
        S1["Ingest Ad Spend APIs & ERP Ledger Costs"]
        S2["Map CRM Closed Sales to Customer Touchpoint Paths"]
        S3["Execute Shapley Machine Learning Attribution"]
        S4["Deduct COGS & Calculate Net ROMI Percentage"]
        S5{"Passes GAAP Audit & Hurdle Rate (Net ROMI >= 50%)?"}
        S6["Flag Unprofitable Channel & Alert Executives"]
        S7["Generate Financial Audit Trail & Executive PDF"]
    end

    subgraph Lane_Integrations["ERP & Ad Network APIs"]
        I1["Stream Ad Spend & General Ledger Invoices"]
        I2["Stream CRM Closed-Won Sales Revenue Payload"]
    end

    subgraph Lane_CFO["Chief Financial Officer (CFO)"]
        F1["Review Audited ROMI & Payback Period Metrics"]
        F2["Sign Off Financial Audit & Approve Budget"]
    end

    Finish(["End ROI Measurement Cycle"])

    Start --> A1 --> A2 --> S1 --> I1 --> I2 --> S2 --> S3
    S3 -->|"No / Unprofitable"| S6 --> F1
    S3 -->|"Yes / Approved"| S4 --> S7 --> F1 --> F2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Marketing ROI Analyst | Configures data-driven attribution model weights, reconciles API reported ad spend against audited ERP general ledger vendor invoices, and sets financial hurdle rates. |
| 2 | Marketing ROI Measurement System | Ingests ad spend APIs and ERP ledger costs, maps CRM closed sales revenue to touchpoint paths, executes machine learning Shapley attribution, deducts product COGS, calculates Net ROMI percentages, checks hurdle rates, and generates GAAP audit trails. |
| 3 | ERP & Ad Network APIs | Streams raw digital ad spend telemetry, general ledger accounts payable invoices, and CRM closed-won sales deal payloads into the system. |
| 4 | Chief Financial Officer (CFO) | Reviews audited Net ROMI scorecards, blended CAC payback periods, and net profit margins, signing off on financial audit reports and approving future channel budget allocations. |
