# Use Case Diagram — Outage Management System

## Mermaid Code

```mermaid
flowchart LR
    actor_1["Outage Operator"]
    actor_2["Outage Controller Device"]
    actor_3["Outage Specialist"]
    actor_4["Enterprise ERP System"]
    actor_5["Regulatory Oversight Agency"]
    actor_6["Weather & Environmental API"]
    actor_7["Emergency Notification Service"]
    actor_8["Customer Billing Gateway"]

    subgraph SystemBoundary["Outage Management System"]
        UC01(["Monitor Outage Sensor Telemetry"])
        UC02(["Execute Remote Outage Control"])
        UC03(["Detect Abnormal Outage Faults"])
        UC04(["Optimize Outage Resource Allocation"])
        UC05(["Dispatch Outage Maintenance Ticket"])
        UC06(["Forecast Outage Operational Demand"])
        UC07(["Configure Outage Safety Thresholds"])
        UC08(["Log Outage Audit Trail"])
        UC09(["Analyze Outage Performance Metrics"])
        UC10(["Export Outage Regulatory Report"])
        UC11(["Calibrate Outage Field Sensors"])
        UC12(["Manage Outage Asset Inventory"])
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
| 1 | Outage Operator | Primary | Main operator controlling Outage Management System | UC01, UC02, UC03, UC04, UC05, UC06, UC07, UC08, UC09, UC10, UC12 |
| 2 | Outage Controller Device | Supporting System | Automated IoT hardware monitoring Outage Management System | UC01, UC02, UC03, UC09, UC11 |
| 3 | Outage Specialist | Primary | Field engineer handling physical assets for Outage Management System | UC05, UC11 |
| 4 | Enterprise ERP System | Supporting System | Enterprise software platform for asset planning | UC04, UC12 |
| 5 | Regulatory Oversight Agency | Regulatory | Government authority supervising safety and environmental compliance | UC08, UC10 |
| 6 | Weather & Environmental API | Supporting System | External service supplying environmental data | UC06 |
| 7 | Emergency Notification Service | Supporting System | System broadcasting critical safety alerts | UC01, UC02 |
| 8 | Customer Billing Gateway | Supporting System | Billing engine computing utility tariffs | UC01, UC02 |

## Use Case Table | Bảng Use Case

| # | UC ID | Use Case Name | Primary Actor | Secondary Actor | Description | Priority |
|---|-------|---------------|---------------|-----------------|-------------|----------|
| 1 | UC01 | Monitor Outage Sensor Telemetry | Outage Operator | Outage Controller Device | Monitors real-time telemetry and operational state for Outage Management System. | High |
| 2 | UC02 | Execute Remote Outage Control | Outage Operator | Outage Controller Device | Sends automated control commands to field devices. | High |
| 3 | UC03 | Detect Abnormal Outage Faults | Outage Controller Device | Outage Operator | Fires automated warnings when operational thresholds are breached. | High |
| 4 | UC04 | Optimize Outage Resource Allocation | Outage Operator | Enterprise ERP System | Balances operational load and resource distribution. | High |
| 5 | UC05 | Dispatch Outage Maintenance Ticket | Outage Operator | Outage Specialist | Generates work orders for scheduled and corrective field maintenance. | Medium |
| 6 | UC06 | Forecast Outage Operational Demand | Outage Operator | Weather & Environmental API | Predicts future demand patterns using historical sensor telemetry. | High |
| 7 | UC07 | Configure Outage Safety Thresholds | Outage Operator | None | Sets warning parameters and automatic trip limits. | Medium |
| 8 | UC08 | Log Outage Audit Trail | Outage Operator | Regulatory Oversight Agency | Records all critical operator actions for safety compliance. | Medium |
| 9 | UC09 | Analyze Outage Performance Metrics | Outage Operator | Outage Controller Device | Evaluates efficiency, uptime, and energy consumption metrics. | Medium |
| 10 | UC10 | Export Outage Regulatory Report | Outage Operator | Regulatory Oversight Agency | Generates standardized compliance documents. | Low |
| 11 | UC11 | Calibrate Outage Field Sensors | Outage Specialist | Outage Controller Device | Performs remote or on-site sensor zero-point calibration. | Medium |
| 12 | UC12 | Manage Outage Asset Inventory | Outage Operator | Enterprise ERP System | Tracks lifecycle, depreciation, and warranty for system hardware. | Low |

## Use Case Specification | Đặc tả Use Case

---

### UC01 — Monitor Outage Sensor Telemetry

| Field | Detail |
|-------|--------|
| **UC ID** | UC01 |
| **Use Case Name** | Monitor Outage Sensor Telemetry |
| **Actor(s)** | Primary: Outage Operator / Secondary: Outage Controller Device |
| **Description** | Monitors real-time telemetry and operational state for Outage Management System. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Outage Operator initiates monitor outage sensor telemetry request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC02 — Execute Remote Outage Control

| Field | Detail |
|-------|--------|
| **UC ID** | UC02 |
| **Use Case Name** | Execute Remote Outage Control |
| **Actor(s)** | Primary: Outage Operator / Secondary: Outage Controller Device |
| **Description** | Sends automated control commands to field devices. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Outage Operator initiates execute remote outage control request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC03 — Detect Abnormal Outage Faults

| Field | Detail |
|-------|--------|
| **UC ID** | UC03 |
| **Use Case Name** | Detect Abnormal Outage Faults |
| **Actor(s)** | Primary: Outage Controller Device / Secondary: Outage Operator |
| **Description** | Fires automated warnings when operational thresholds are breached. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Outage Controller Device initiates detect abnormal outage faults request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

---

### UC04 — Optimize Outage Resource Allocation

| Field | Detail |
|-------|--------|
| **UC ID** | UC04 |
| **Use Case Name** | Optimize Outage Resource Allocation |
| **Actor(s)** | Primary: Outage Operator / Secondary: Enterprise ERP System |
| **Description** | Balances operational load and resource distribution. |
| **Precondition** | 1. System is operational and authenticated. <br> 2. Primary actor has active session credentials. |
| **Main Flow** | 1. Outage Operator initiates optimize outage resource allocation request. <br> 2. System validates actor authorization. <br> 3. System retrieves current telemetry state. <br> 4. System executes requested action safely. <br> 5. System logs operation status. <br> 6. System displays success confirmation message. |
| **Alternative Flow** | **AF1** — Bulk Processing: If processing multiple assets, System executes queued batch workflow. <br> **AF2** — Offline Mode: If network connection drops, System buffers request locally and syncs upon reconnection. |
| **Exception Flow** | **EX1** — Unauthorized Access: System rejects operation and flags security alert. <br> **EX2** — Sensor Timeout: System returns error notification and dispatches health check. |
| **Postcondition** | Target system state updated cleanly and audit log entry created. |
| **Business Rule** | **BR1**: All operations must comply with IEEE/ISO industry safety standards. <br> **BR2**: Changes must be logged into immutable audit log. |

