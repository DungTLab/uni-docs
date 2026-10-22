# Swimlane Diagram — Medical Supply Chain Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Supply Requisition"])

    subgraph Lane_Dept["Department / Ward Representative"]
        D1["Identify Medical Consumable Shortage"]
        D2["Submit Ward Replenishment Order"]
        D3["Receive & Inspect Delivered Ward Stock"]
    end

    subgraph Lane_Procurement["Hospital Procurement Officer"]
        P1["Consolidate Ward Requisitions into PO"]
        P2["Verify Contracted Pricing & Approve PO"]
        P3["Dispatch Electronic PO to Vendor"]
    end

    subgraph Lane_System["Medical Supply System"]
        S1["Evaluate Min-Max Safety Stock Levels"]
        S2["Generate PO & Send Vendor Delivery Alert"]
        S3["Perform 3-Way Match & Log Stock GRN"]
        S4["Verify FEFO & Cold-Chain Telemetry"]
        S5["Update Ward Inventory Stock Level"]
    end

    subgraph Lane_Warehouse["Warehouse Manager"]
        W1["Receive Vendor Physical Shipment at Dock"]
        W2["Scan UDI Barcodes & Inspect Batch Lots"]
        W3{"Is Cold Chain Intact & Inspection Passed?"}
        W4["Move Stock to Quarantine Storage Area"]
        W5["Pick & Pack Ward Replenishment Order"]
    end

    Finish(["End Supply Fulfillment Cycle"])

    Start --> D1 --> D2 --> P1 --> P2 --> P3 --> S2 --> W1 --> W2 --> W3
    W3 -->|"Yes"| S3 --> S4 --> W5 --> D3 --> S5 --> Finish
    W3 -->|"No / Damaged"| W4 --> P1
    S1 --> P1
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Department Representative | Identifies consumable shortages in ward closets, submits replenishment requisitions, and inspects delivered stock upon ward closet top-up. |
| 2 | Hospital Procurement Officer | Consolidates departmental requests into formal purchase orders, verifies vendor contract price compliance, approves orders within financial limits, and issues POs to suppliers. |
| 3 | Medical Supply System | Automatically evaluates safety stock reorder thresholds, generates EDI purchase order payloads, executes 3-way matching upon shipment arrival, verifies FEFO picking rules and cold-chain telemetry, and updates stock balances. |
| 4 | Warehouse Manager | Unloads vendor deliveries at receiving dock, scans UDI barcodes, inspects batch lot integrity, moves failed/breached items to quarantine, and picks/packs approved stock for ward distribution. |
