# Swimlane Diagram — Medical Inventory Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Supply Requisition Process"])

    subgraph Lane1["Department Nurse / Requisitioner"]
        A1["Identify Ward Supply Shortage"]
        A2["Create Electronic Requisition Cart"]
        A3["Review & Submit Supply Requisition"]
        A4["Receive & Inspect Delivered Stock"]
    end

    subgraph Lane2["System / Reorder Engine"]
        S1["Validate Dept Budget & Item Master"]
        S2{"Stock Available in Warehouse?"}
        S3["Allocate Stock using FEFO Priority"]
        S4["Generate Auto Purchase Request"]
        S5["Update Stock Balances & ERP Cost Center"]
    end

    subgraph Lane3["Warehouse Inventory Manager"]
        W1["Receive Requisition Dispatch Order"]
        W2["Pick Stock from Bin Locations"]
        W3["Scan Barcodes & Verify FEFO Lots"]
        W4["Dispatch Items to Clinical Unit"]
    end

    subgraph Lane4["Procurement Specialist"]
        P1["Receive Low-Stock Purchase Request"]
        P2["Consolidate Orders & Issue Vendor PO"]
    end

    Finish(["End Supply Fulfillment"])

    Start --> A1 --> A2 --> A3 --> S1 --> S2
    S2 -->|"Yes"| S3 --> W1 --> W2 --> W3 --> W4 --> A4 --> S5 --> Finish
    S2 -->|"No"| S4 --> P1 --> P2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Department Nurse / Requisitioner | Identifies ward consumable shortages, selects items from catalog, submits digital requisition, and performs final receipt verification upon delivery. |
| 2 | System / Reorder Engine | Validates cost center budget compliance, verifies warehouse stock availability, executes FEFO automated batch allocation, decrements inventory balances, and generates purchasing triggers for out-of-stock items. |
| 3 | Warehouse Inventory Manager | Receives pick tickets, picks items from designated warehouse bins, scans barcodes to confirm FEFO lot compliance, packs supplies, and issues dispatch manifests to ward staff. |
| 4 | Procurement Specialist | Reviews automated purchase requests generated during stockouts, consolidates vendor requests, checks supplier contracts, and issues formal purchase orders for inventory replenishment. |
