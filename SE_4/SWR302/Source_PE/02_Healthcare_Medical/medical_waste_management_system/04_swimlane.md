# Swimlane Diagram — Medical Waste Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Medical Waste Collection Cycle"])

    subgraph Lane1["Ward Sanitation Staff"]
        A1["Scan RFID Tag & Weigh Waste Bags on Scale"]
        A2["Transport Sealed Containers to Central Storage"]
    end

    subgraph Lane2["Storage Supervisor & Transporter"]
        B1["Audit Inbound Weight & Assign Storage Bay"]
        B2["Execute Handover & Sign Electronic Manifest"]
    end

    subgraph Lane3["System / MWMS & Manifest Engine"]
        S1["Verify Tare Weight & Check Category Rules"]
        S2{"Weight Variance <= 2.0% & Storage Time < 48h?"}
        S3["Flag Leakage Discrepancy & Storage Alert"]
        S4["Compile Hazardous Manifest & Lock Containers"]
        S5["Ingest Destruction Cert & Reconcile Weight"]
    end

    subgraph Lane4["EHS Officer & DONRE Authority"]
        C1["Issue Statutory Hazardous Waste Manifest"]
        C2["Export Annual Environmental Report to DONRE"]
    end

    Finish(["End Waste Destruction & Reporting Cycle"])

    Start --> A1 --> S1 --> A2 --> B1 --> S2
    S2 -->|"No"| S3 --> B1
    S2 -->|"Yes"| C1 --> S4 --> B2 --> S5 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Ward Sanitation Staff | Collects waste from hospital wards, scans RFID-tagged containers, weighs waste bags on digital smart scales, verifies color categories (Yellow, Red, Black), and moves sealed containers to central storage. |
| 2 | Storage Supervisor & Transporter | Receives inbound containers at central storage, audits weight variance, assigns cold storage bays, verifies driver licenses, loads hazardous trucks, and signs electronic manifests. |
| 3 | System / MWMS & Manifest Engine | Validates tare weights, checks container weight limits, monitors 48-hour maximum storage countdowns, compiles statutory MONRE manifests, tracks GPS transport fleets, and reconciles destruction certificates. |
| 4 | EHS Officer & DONRE Authority | Authorizes statutory hazardous waste manifests, monitors liquid WTP effluent sensor telemetry (QCVN 28), investigates biohazard spills, and exports annual environmental reports to DONRE. |
