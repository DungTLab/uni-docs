# Use Case Diagram — Solar Farm Management System

## Mermaid Code

```mermaid
flowchart LR
    actor_1["Solar Operator"]
    actor_2["Solar Controller Device"]
    actor_3["Solar Specialist"]
    actor_4["Enterprise ERP System"]
    actor_5["Regulatory Oversight Agency"]
    actor_6["Weather & Environmental API"]
    actor_7["Emergency Notification Service"]
    actor_8["Customer Billing Gateway"]

    subgraph SystemBoundary["Solar Farm Management System"]
        UC01(["Monitor Solar Sensor Telemetry"])
        UC02(["Execute Remote Solar Control"])
        UC03(["Detect Abnormal Solar Faults"])
        UC04(["Optimize Solar Resource Allocation"])
        UC05(["Dispatch Solar Maintenance Ticket"])
        UC06(["Forecast Solar Operational Demand"])
        UC07(["Configure Solar Safety Thresholds"])
        UC08(["Log Solar Audit Trail"])
        UC09(["Analyze Solar Performance Metrics"])
        UC10(["Export Solar Regulatory Report"])
        UC11(["Calibrate Solar Field Sensors"])
        UC12(["Manage Solar Asset Inventory"])
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
| 1 | Solar Operator | Primary | Main operator controlling Solar Farm Management System | UC01, UC02, UC03, UC04, UC05, UC06, UC07, UC08, UC09, UC10, UC12 |
| 2 | Solar Controller Device | Supporting System | Automated IoT hardware monitoring Solar Farm Management System | UC01, UC02, UC03, UC09, UC11 |
| 3 | Solar Specialist | Primary | Field engineer handling physical assets for Solar Farm Management System | UC05, UC11 |
| 4 | Enterprise ERP System | Supporting System | Enterprise software platform for asset planning | UC04, UC12 |
| 5 | Regulatory Oversight Agency | Regulatory | Government authority supervising safety and environmental compliance | UC08, UC10 |
| 6 | Weather & Environmental API | Supporting System | External service supplying environmental data | UC06 |
| 7 | Emergency Notification Service | Supporting System | System broadcasting critical safety alerts | UC01, UC02 |
| 8 | Customer Billing Gateway | Supporting System | Billing engine computing utility tariffs | UC01, UC02 |

## Use Case Table | Bảng Use Case

| # | UC ID | Use Case Name | Primary Actor | Secondary Actor | Description | Priority |
|---|-------|---------------|---------------|-----------------|-------------|----------|
| 1 | UC01 | Monitor Solar Sensor Telemetry | Solar Operator | Solar Controller Device | Monitors real-time telemetry and operational state for Solar Farm Management System. | High |
| 2 | UC02 | Execute Remote Solar Control | Solar Operator | Solar Controller Device | Sends automated control commands to field devices. | High |
| 3 | UC03 | Detect Abnormal Solar Faults | Solar Controller Device | Solar Operator | Fires automated warnings when operational thresholds are breached. | High |
| 4 | UC04 | Optimize Solar Resource Allocation | Solar Operator | Enterprise ERP System | Balances operational load and resource distribution. | High |
| 5 | UC05 | Dispatch Solar Maintenance Ticket | Solar Operator | Solar Specialist | Generates work orders for scheduled and corrective field maintenance. | Medium |
| 6 | UC06 | Forecast Solar Operational Demand | Solar Operator | Weather & Environmental API | Predicts future demand patterns using historical sensor telemetry. | High |
| 7 | UC07 | Configure Solar Safety Thresholds | Solar Operator | None | Sets warning parameters and automatic trip limits. | Medium |
| 8 | UC08 | Log Solar Audit Trail | Solar Operator | Regulatory Oversight Agency | Records all critical operator actions for safety compliance. | Medium |
| 9 | UC09 | Analyze Solar Performance Metrics | Solar Operator | Solar Controller Device | Evaluates efficiency, uptime, and energy consumption metrics. | Medium |
| 10 | UC10 | Export Solar Regulatory Report | Solar Operator | Regulatory Oversight Agency | Generates standardized compliance documents. | Low |
| 11 | UC11 | Calibrate Solar Field Sensors | Solar Specialist | Solar Controller Device | Performs remote or on-site sensor zero-point calibration. | Medium |
| 12 | UC12 | Manage Solar Asset Inventory | Solar Operator | Enterprise ERP System | Tracks lifecycle, depreciation, and warranty for system hardware. | Low |

## Use Case Specification | Đặc tả Use Case

---

### UC01 — Monitor Solar Sensor Telemetry

| Field | Detail |
|-------|--------|
| **UC ID** | UC01 |
| **Use Case Name** | Monitor Solar Sensor Telemetry |
| **Actor(s)** | Primary: Solar Operator / Secondary: Solar Controller Device |
| **Description** | Monitors real-time telemetry and operational state for Solar Farm Management System. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Solar Operator initiates monitor solar sensor telemetry request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC02 — Execute Remote Solar Control

| Field | Detail |
|-------|--------|
| **UC ID** | UC02 |
| **Use Case Name** | Execute Remote Solar Control |
| **Actor(s)** | Primary: Solar Operator / Secondary: Solar Controller Device |
| **Description** | Sends automated control commands to field devices. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Solar Operator initiates execute remote solar control request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC03 — Detect Abnormal Solar Faults

| Field | Detail |
|-------|--------|
| **UC ID** | UC03 |
| **Use Case Name** | Detect Abnormal Solar Faults |
| **Actor(s)** | Primary: Solar Controller Device / Secondary: Solar Operator |
| **Description** | Fires automated warnings when operational thresholds are breached. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Solar Controller Device initiates detect abnormal solar faults request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC04 — Optimize Solar Resource Allocation

| Field | Detail |
|-------|--------|
| **UC ID** | UC04 |
| **Use Case Name** | Optimize Solar Resource Allocation |
| **Actor(s)** | Primary: Solar Operator / Secondary: Enterprise ERP System |
| **Description** | Balances operational load and resource distribution. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Solar Operator initiates optimize solar resource allocation request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

