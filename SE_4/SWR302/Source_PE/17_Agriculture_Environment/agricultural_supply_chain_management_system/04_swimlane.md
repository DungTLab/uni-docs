# Swimlane Diagram — Agricultural Supply Chain Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Order Fulfillment"])

    subgraph Lane1["Farm Producer"]
        P1["Log Harvest Batch & Generate GTIN Barcode"]
        P2["Apply Traceability QR Labels to Pallets"]
    end

    subgraph Lane2["Agricultural Supply Chain System"]
        SYS1["Receive Bulk Wholesale Purchase Order"]
        SYS2["Verify Escrow Funding Status"]
        SYS3{"Escrow Funds Verified?"}
        SYS4["Allocate Warehouse Stock & Assign Reefer Route"]
        SYS5["Ingest Real-Time Cold-Chain Telemetry"]
        SYS6{"Cargo Temp <= 4°C Safe Boundary?"}
        SYS7["Flag Temperature Breach & Issue Quarantine"]
        SYS8["Authorize Escrow Payout to Producer"]
    end

    subgraph Lane3["Cold-Chain Carrier"]
        C1["Dispatch Reefer Truck to Warehouse"]
        C2["Load Produce Pallets & Stream Telemetry"]
        C3["Deliver Produce Cargo to Buyer Warehouse"]
    end

    subgraph Lane4["Distributor / Wholesaler"]
        D1["Place Wholesale Purchase Order"]
        D2["Deposit Payment into Escrow Account"]
        D3["Inspect Arrival Quality & Confirm Delivery"]
    end

    Finish(["End Process"])

    Start --> D1 --> D2 --> SYS1 --> SYS2 --> SYS3
    SYS3 -->|"Yes"| P1 --> P2 --> SYS4 --> C1 --> C2 --> SYS5 --> SYS6
    SYS3 -->|"No"| D2
    SYS6 -->|"Yes"| C3 --> D3 --> SYS8 --> Finish
    SYS6 -->|"No"| SYS7 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| Farm Producer | Supplier | Registers harvested crop batches, generates GS1-compliant barcodes, and affixes QR traceability tags onto produce pallets. |
| Agricultural Supply Chain System | Core Platform | Validates buyer escrow deposits, allocates warehouse stock, optimizes carrier dispatch routes, monitors real-time telemetry, and triggers escrow payment releases upon verified delivery. |
| Cold-Chain Carrier | Logistics Provider | Dispatches refrigerated transport, loads produce pallets, streams live GPS and temperature telemetry data, and completes physical cargo delivery. |
| Distributor / Wholesaler | Buyer | Submits wholesale purchase orders, funds third-party escrow accounts, conducts receiving quality inspections upon arrival, and confirms delivery completion. |
