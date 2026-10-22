# Swimlane Diagram — Agricultural Equipment Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Maintenance & Dispatch Cycle"])

    subgraph OperatorLane["Equipment Operator / Driver"]
        A1["Complete Pre-Op Safety Checklist"]
        A2["Drive Machinery to Field Parcel"]
        A3["Execute Field Task & Log Engine Hours"]
        A4["Refuel Machinery at Smart Station"]
        A5["Report Field Breakdown Fault"]
    end

    subgraph SystemLane["Agricultural Equipment Management System"]
        S1["Verify Operator License & Checklist"]
        S2{"Checklist & Telemetry OK?"}
        S3["Stream CAN-Bus Telemetry & GPS"]
        S4["Calculate Fuel Consumption Rate"]
        S5["Auto-Generate PM Work Order"]
    end

    subgraph TelemetryLane["Tractor Telemetry & CAN-Bus Gateway"]
        T1["Read CAN-Bus ECU Data Streams"]
        T2["Detect DTC Engine Fault Codes"]
    end

    subgraph MechanicLane["Maintenance Mechanic"]
        M1["Receive Maintenance Work Order"]
        M2["Perform Service & Replace Parts"]
        M3["Sign Off Work Order Completion"]
    end

    subgraph ManagerLane["Fleet Manager"]
        G1["Review Fleet Utilization Dashboard"]
        G2["Approve Maintenance Expenditure"]
    end

    Finish(["End Operational Cycle"])

    Start --> A1 --> S1 --> S2
    S2 -->|"Failed Checklist / Overheat"| A5 --> T2 --> M1
    S2 -->|"Passed"| A2 --> T1 --> S3 --> A3 --> A4 --> S4 --> S5
    S5 --> M1 --> M2 --> M3 --> G2 --> Finish
    S4 --> G1 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Equipment Operator / Driver | Conducts mandatory digital pre-operation safety checklists, operates machinery in assigned field parcels, monitors dashboard warnings, refuels at smart dispensers, and reports field breakdown issues |
| 2 | Agricultural Equipment Management System | Validates driver credentials and safety checklists, ingests real-time CAN-bus telemetry, tracks GPS field parcel boundaries, calculates fuel efficiency ($L/\text{ha}$), and auto-generates preventive maintenance work orders |
| 3 | Tractor Telemetry & CAN-Bus Gateway | Reads onboard ECU data via ISOBUS / J1939 protocols, monitors engine RPM, coolant temperature, and fuel rate, and broadcasts Diagnostic Trouble Codes (DTC) upon sensor anomalies |
| 4 | Maintenance Mechanic | Receives scheduled PM work orders or breakdown alerts, conducts physical servicing, replaces filters and spare parts, logs labor hours, and clears fault codes to restore equipment to `AVAILABLE` status |
| 5 | Fleet Manager | Oversees fleet dispatch assignments, monitors real-time fleet utilization dashboards, approves major maintenance expenditures, and analyzes total cost of ownership |
