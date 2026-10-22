# Swimlane Diagram — Grocery Store Inventory System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Stock Clerk / Floor Staff"]
        C1["Scan Pallet Barcodes at Dock Receiving"]
        C2["Execute FEFO Expiry Scan & Stick Markdown Labels"]
        C3["Perform Handheld RFID Shelf Stock Audit Count"]
    end

    subgraph Lane2["Grocery Purchasing Manager"]
        M1["Configure PAR Safety Levels & Review Draft PO"]
        M2["Approve Automated PO & Transmit to Vendor"]
    end

    subgraph Lane3["Wholesale Food Distributor"]
        V1["Deliver Pallets & Send Advance Shipping Notice"]
    end

    subgraph Lane4["Grocery Inventory Core Engine"]
        S1["Match Delivered Pallet Barcodes with Open PO"]
        S2{"Passes Expiry & Shelf-Life Rule Check?"}
        S3["Reject Shipment & Generate RTV Debit Memo"]
        S4["Update Real-Time Stock Balance & Broadcast ESL Prices"]
        S5["Calculate Reorder Quantities & Generate Draft PO"]
    end

    Finish(["End"])

    Start --> V1 --> C1 --> S1 --> S2
    S2 -->|"No (Short-Dated / Damaged)"| S3 --> Finish
    S2 -->|"Yes (Fresh Batch Passed)"| S4 --> C2 --> C3 --> S5 --> M1 --> M2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Stock Clerk / Floor Staff | Scans pallet barcodes at receiving dock, executes FEFO expiry checks, applies clearance markdown labels, and performs handheld RFID shelf audit counts. |
| **Lane 2** | Grocery Purchasing Manager | Configures PAR safety stock reorder levels, reviews automated draft purchase orders, approves POs, and manages vendor pricing. |
| **Lane 3** | Wholesale Food Distributor | Delivers palletized freight to receiving dock and transmits Electronic Advance Shipping Notices (ASN). |
| **Lane 4** | Grocery Inventory Core Engine | Matches received barcodes with open POs, validates batch lot expiry dates, updates stock balances, broadcasts ESL price changes, and generates auto-reorder POs. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Freight Delivery & Dock Scanning**: Wholesale Distributor delivers pallets (`V1`). Stock Clerk scans GS1-128 barcodes on handheld terminal (`C1`). Core Engine matches barcodes with open PO line items (`S1`).
2. **Expiry Rule Validation & Receiving**: Core Engine evaluates batch lot expiry dates against minimum remaining shelf-life rules (`S2`).
   - If **No (Short-Dated / Damaged)**: Core Engine rejects shipment line and generates RTV debit memo (`S3`).
   - If **Yes (Fresh Batch Passed)**: Core Engine updates stock balances and broadcasts ESL tag prices (`S4`).
3. **FEFO Markdown & Stock Audit**: Clerk executes daily FEFO expiry scan, sticking clearance markdown labels on near-expiry items (`C2`), and conducts handheld RFID audit counts (`C3`).
4. **Auto-Reordering & PO Approval**: Core Engine calculates PAR reorder points and generates draft PO (`S5`). Purchasing Manager configures PAR safety levels (`M1`) and approves PO (`M2`), completing execution (`Finish`).
