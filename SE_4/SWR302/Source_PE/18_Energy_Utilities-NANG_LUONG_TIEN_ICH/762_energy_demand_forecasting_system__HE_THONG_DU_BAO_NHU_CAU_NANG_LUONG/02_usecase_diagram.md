# Use Case Diagram — Energy Demand Forecasting System

## Mermaid Code

```mermaid
flowchart LR
    actor_1["Energy Operator"]
    actor_2["Energy Controller Device"]
    actor_3["Energy Specialist"]
    actor_4["Enterprise ERP System"]
    actor_5["Regulatory Oversight Agency"]
    actor_6["Weather & Environmental API"]
    actor_7["Emergency Notification Service"]
    actor_8["Customer Billing Gateway"]

    subgraph SystemBoundary["Energy Demand Forecasting System"]
        UC01(["Monitor Energy Sensor Telemetry"])
        UC02(["Execute Remote Energy Control"])
        UC03(["Detect Abnormal Energy Faults"])
        UC04(["Optimize Energy Resource Allocation"])
        UC05(["Dispatch Energy Maintenance Ticket"])
        UC06(["Forecast Energy Operational Demand"])
        UC07(["Configure Energy Safety Thresholds"])
        UC08(["Log Energy Audit Trail"])
        UC09(["Analyze Energy Performance Metrics"])
        UC10(["Export Energy Regulatory Report"])
        UC11(["Calibrate Energy Field Sensors"])
        UC12(["Manage Energy Asset Inventory"])
    end

    actor_1 --> UC01
    UC01 -.->|"<<include>>"| actor_2
    actor_1 --> UC02
    UC02 -.->|"<<include>>"| actor_2
    actor_2 --> UC03
    UC03 -.->|"<<include>>"| actor_1
    actor_1 --> UC04
    UC04 -.->|"<<include>>"| actor_4
    actor_1 --> UC05
    UC05 -.->|"<<include>>"| actor_3
    actor_1 --> UC06
    UC06 -.->|"<<include>>"| actor_6
    actor_1 --> UC07
    actor_1 --> UC08
    UC08 -.->|"<<include>>"| actor_5
    actor_1 --> UC09
    UC09 -.->|"<<include>>"| actor_2
    actor_1 --> UC10
    UC10 -.->|"<<include>>"| actor_5
    actor_3 --> UC11
    UC11 -.->|"<<include>>"| actor_2
    actor_1 --> UC12
    UC12 -.->|"<<include>>"| actor_4
```

## Actor Table | Bảng Actor

| # | Actor | Actor Type | Role Description | Related Use Cases |
|---|-------|------------|------------------|-------------------|
| 1 | Energy Operator | Primary | Main operator controlling Energy Demand Forecasting System | UC01, UC02, UC03, UC04, UC05, UC06, UC07, UC08, UC09, UC10, UC12 |
| 2 | Energy Controller Device | Supporting System | Automated IoT hardware monitoring Energy Demand Forecasting System | UC01, UC02, UC03, UC09, UC11 |
| 3 | Energy Specialist | Primary | Field engineer handling physical assets for Energy Demand Forecasting System | UC05, UC11 |
| 4 | Enterprise ERP System | Supporting System | Enterprise software platform for asset planning | UC04, UC12 |
| 5 | Regulatory Oversight Agency | Regulatory | Government authority supervising safety and environmental compliance | UC08, UC10 |
| 6 | Weather & Environmental API | Supporting System | External service supplying environmental data | UC06 |
| 7 | Emergency Notification Service | Supporting System | System broadcasting critical safety alerts | UC01, UC02 |
| 8 | Customer Billing Gateway | Supporting System | Billing engine computing utility tariffs | UC01, UC02 |

## Use Case Table | Bảng Use Case

| # | UC ID | Use Case Name | Primary Actor | Secondary Actor | Description | Priority |
|---|-------|---------------|---------------|-----------------|-------------|----------|
| 1 | UC01 | Monitor Energy Sensor Telemetry | Energy Operator | Energy Controller Device | Monitors real-time telemetry and operational state for Energy Demand Forecasting System. | High |
| 2 | UC02 | Execute Remote Energy Control | Energy Operator | Energy Controller Device | Sends automated control commands to field devices. | High |
| 3 | UC03 | Detect Abnormal Energy Faults | Energy Controller Device | Energy Operator | Fires automated warnings when operational thresholds are breached. | High |
| 4 | UC04 | Optimize Energy Resource Allocation | Energy Operator | Enterprise ERP System | Balances operational load and resource distribution. | High |
| 5 | UC05 | Dispatch Energy Maintenance Ticket | Energy Operator | Energy Specialist | Generates work orders for scheduled and corrective field maintenance. | Medium |
| 6 | UC06 | Forecast Energy Operational Demand | Energy Operator | Weather & Environmental API | Predicts future demand patterns using historical sensor telemetry. | High |
| 7 | UC07 | Configure Energy Safety Thresholds | Energy Operator | None | Sets warning parameters and automatic trip limits. | Medium |
| 8 | UC08 | Log Energy Audit Trail | Energy Operator | Regulatory Oversight Agency | Records all critical operator actions for safety compliance. | Medium |
| 9 | UC09 | Analyze Energy Performance Metrics | Energy Operator | Energy Controller Device | Evaluates efficiency, uptime, and energy consumption metrics. | Medium |
| 10 | UC10 | Export Energy Regulatory Report | Energy Operator | Regulatory Oversight Agency | Generates standardized compliance documents. | Low |
| 11 | UC11 | Calibrate Energy Field Sensors | Energy Specialist | Energy Controller Device | Performs remote or on-site sensor zero-point calibration. | Medium |
| 12 | UC12 | Manage Energy Asset Inventory | Energy Operator | Enterprise ERP System | Tracks lifecycle, depreciation, and warranty for system hardware. | Low |

## Use Case Specification | Đặc tả Use Case

---

### UC01 — Monitor Energy Sensor Telemetry

| Field | Detail |
|-------|--------|
| **UC ID** | UC01 |
| **Use Case Name** | Monitor Energy Sensor Telemetry |
| **Actor(s)** | Primary: Energy Operator / Secondary: Energy Controller Device |
| **Description** | Monitors real-time telemetry and operational state for Energy Demand Forecasting System. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Energy Operator initiates monitor energy sensor telemetry request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC02 — Execute Remote Energy Control

| Field | Detail |
|-------|--------|
| **UC ID** | UC02 |
| **Use Case Name** | Execute Remote Energy Control |
| **Actor(s)** | Primary: Energy Operator / Secondary: Energy Controller Device |
| **Description** | Sends automated control commands to field devices. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Energy Operator initiates execute remote energy control request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC03 — Detect Abnormal Energy Faults

| Field | Detail |
|-------|--------|
| **UC ID** | UC03 |
| **Use Case Name** | Detect Abnormal Energy Faults |
| **Actor(s)** | Primary: Energy Controller Device / Secondary: Energy Operator |
| **Description** | Fires automated warnings when operational thresholds are breached. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Energy Controller Device initiates detect abnormal energy faults request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC04 — Optimize Energy Resource Allocation

| Field | Detail |
|-------|--------|
| **UC ID** | UC04 |
| **Use Case Name** | Optimize Energy Resource Allocation |
| **Actor(s)** | Primary: Energy Operator / Secondary: Enterprise ERP System |
| **Description** | Balances operational load and resource distribution. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Energy Operator initiates optimize energy resource allocation request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

