# Swimlane Diagram — Loyalty Program Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Loyalty Transaction & Reward Cycle"])

    subgraph Lane_Member["Loyalty Member"]
        M1["Present Digital Wallet Barcode Pass at Checkout"]
        M2["Select Reward Item from Catalog on Mobile App"]
    end

    subgraph Lane_Cashier["POS Store Cashier"]
        C1["Scan Member Barcode & Complete Checkout"]
        C2["Scan Single-Use Reward Voucher Code"]
    end

    subgraph Lane_System["Loyalty Program Management System"]
        S1["Calculate Real-Time Points (Base x Tier Multiplier)"]
        S2["Credit Points Balance & Push Apple Wallet Pass Update"]
        S3{"Passes Fraud Checks & Has Sufficient Points Balance?"}
        S4["Freeze Account & Dispatch Security Anomaly Alert"]
        S5["Deduct Points Balance & Generate Reward Voucher"]
    end

    subgraph Lane_Fulfillment["Digital Reward Fulfillment API"]
        F1["Fulfill Third-Party Digital Gift Card (TangoCard)"]
        F2["Return Voucher Claim Code & Webhook Receipt"]
    end

    Finish(["End Loyalty Cycle"])

    Start --> M1 --> C1 --> S1 --> S2 --> M2 --> S3
    S3 -->|"No / Fraud or Low Points"| S4 --> Finish
    S3 -->|"Yes / Approved"| S5 --> F1 --> F2 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Loyalty Member | Presents digital wallet pass barcode at retail checkout, views points balance, selects reward items from mobile app catalog, and receives digital reward vouchers. |
| 2 | POS Store Cashier | Scans member barcode passes at retail POS, processes checkout transactions, and scans single-use reward voucher codes to apply checkout discounts. |
| 3 | Loyalty Program Management System | Calculates real-time points accrual using tier and promotional multipliers, credits member balances, pushes digital wallet pass updates, evaluates fraud velocity rules, verifies points balances, deducts points, and registers voucher codes. |
| 4 | Digital Reward Fulfillment API | Fulfills third-party digital gift cards and partner reward vouchers (TangoCard API), returning digital claim codes and delivery receipt webhooks. |
