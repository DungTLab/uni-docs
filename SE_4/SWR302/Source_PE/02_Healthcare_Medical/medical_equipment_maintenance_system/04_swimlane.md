# Swimlane Diagram — Medical Equipment Maintenance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Medical Equipment Maintenance Cycle"])

    subgraph Lane1["Clinical User / Nurse"]
        A1["Scan Device QR Code & Report Fault"]
        A2["Tag Device Out-of-Service & Quarantine"]
        A3["Verify Repair & Sign Work Order"]
    end

    subgraph Lane2["BMET Technician"]
        B1["Accept Dispatched Work Order"]
        B2["Perform Troubleshooting & Replace Parts"]
        B3["Execute IEC 62353 Electrical Safety Test"]
    end

    subgraph Lane3["System / CMMS & PM Engine"]
        S1["Parse QR Code & Assign Work Order Priority"]
        S2{"IEC 62353 Safety Test Passed & SLA Met?"}
        S3["Issue Safety Certificate & Clear Out-of-Service"]
        S4["Escalate to OEM Vendor Support"]
        S5["Update Downtime Metrics & Reorder Parts"]
    end

    subgraph Lane4["OEM Vendor / Department Head"]
        W1["Dispatch Specialized OEM Factory Engineer"]
        W2["Approve Major Repair Quote & Sign FSR"]
    end

    Finish(["End Maintenance Work Order Cycle"])

    Start --> A1 --> S1 --> A2 --> B1 --> B2 --> B3 --> S2
    S2 -->|"No"| S4 --> W1 --> W2 --> B3
    S2 -->|"Yes"| S3 --> A3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Clinical User / Nurse | Scans broken medical device QR code, reports fault symptoms, tags the device Out-of-Service, and verifies/signs the work order upon repair completion. |
| 2 | BMET Technician | Receives dispatched work order, troubleshoots malfunction, replaces faulty spare parts, performs post-repair IEC 62353 electrical safety and calibration testing, and logs labor. |
| 3 | System / CMMS & PM Engine | Parses device QR tags, assigns work order priority tiers (Priority 1 Life Support), evaluates electrical safety pass/fail tokens, updates asset downtime metrics, and triggers spare part reorders. |
| 4 | OEM Vendor / Department Head | Receives escalated complex repairs, dispatches factory-certified OEM service engineers, evaluates major component replacement quotes, and approves Field Service Reports (FSR). |
