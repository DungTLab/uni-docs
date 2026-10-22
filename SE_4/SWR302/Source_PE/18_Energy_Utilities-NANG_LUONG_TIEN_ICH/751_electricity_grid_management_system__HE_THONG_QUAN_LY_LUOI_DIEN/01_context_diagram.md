# Context Diagram — Electricity Grid Management System

## Mermaid Code

```mermaid
flowchart LR
    Actor1["Grid Dispatcher"]
    Actor2["Substation Automation Controller"]
    Actor3["Field Maintenance Engineer"]
    Actor4["Independent Power Producer"]
    Actor5["Energy Regulatory Authority"]
    Actor6["Outage Management System"]
    Actor7["Billing & Revenue System"]
    Actor8["Weather Forecast Service"]

    System(("Electricity Grid Management System"))

    Actor1 -->|"submits grid switching command"| System
    System -->|"receives real-time network topology and voltage map"| Actor1
    Actor2 -->|"transmits IEC 61850 telemetry metrics"| System
    System -->|"receives breaker trip or close control signals"| Actor2
    Actor3 -->|"submits line repair clearance report"| System
    System -->|"receives maintenance work orders"| Actor3
    Actor4 -->|"sends power generation telemetry"| System
    System -->|"receives generation curtailment instructions"| Actor4
    Actor5 -->|"sends compliance audit requests"| System
    System -->|"receives SAIDI/SAIFI grid reliability reports"| Actor5
    Actor6 -->|"submits customer outage reports"| System
    System -->|"receives fault location telemetry"| Actor6
    Actor7 -->|"submits rate tariff structures"| System
    System -->|"receives bulk energy transmission logs"| Actor7
    Actor8 -->|"transmits weather and storm forecast data"| System
    System -->|"receives geographic region queries"| Actor8
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Grid Dispatcher | Primary | submits grid switching command | receives real-time network topology and voltage map | Monitors overall power grid stability and controls breakers |
| 2 | Substation Automation Controller | Supporting System | transmits IEC 61850 telemetry metrics | receives breaker trip or close control signals | Automated RTU/IED devices inside electrical substations |
| 3 | Field Maintenance Engineer | Primary | submits line repair clearance report | receives maintenance work orders | On-site technician performing physical repairs on power lines |
| 4 | Independent Power Producer | Supporting System | sends power generation telemetry | receives generation curtailment instructions | External solar, wind, or thermal power plant injecting power into grid |
| 5 | Energy Regulatory Authority | Regulatory | sends compliance audit requests | receives SAIDI/SAIFI grid reliability reports | Government body enforcing national electrical safety and reliability standards |
| 6 | Outage Management System | Supporting System | submits customer outage reports | receives fault location telemetry | Enterprise IT system tracking customer power disruptions |
| 7 | Billing & Revenue System | Supporting System | submits rate tariff structures | receives bulk energy transmission logs | Financial platform managing wholesale wheeling charges |
| 8 | Weather Forecast Service | Supporting System | transmits weather and storm forecast data | receives geographic region queries | External API providing wind and solar irradiance data |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The Electricity Grid Management System (EGMS) monitors and controls high-voltage power transmission lines, substations, transformer stations, and distribution feeders in real-time. It processes telemetry data from SCADA and smart sensors, executes load balancing, isolates faults automatically, and integrates with wholesale power markets and distributed energy resources.
