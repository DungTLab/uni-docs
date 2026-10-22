# Swimlane Diagram — Bar & Beverage Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Head Bartender / Mixologist"]
        C1["Input Cocktail Specs & Premix Syrups"]
        C2["Weigh Open Bottles on Bluetooth Scale"]
        C3["Log Spoilage & Broken Bottles"]
    end

    subgraph Lane2["Smart Dispenser & Pour Sensors"]
        P1["Measure Shot Volume & Tap Flow Telemetry"]
    end

    subgraph Lane3["Bar Manager / Beverage Director"]
        A1["Review Shift Overpour Variance Table"]
        A2{"Is Overpour Variance > 8.0% Limit?"}
        A3["Approve Shift Audit & Reorder Spirits"]
    end

    subgraph Lane4["Bar & Beverage System Engine"]
        S1["Reconcile Poured ml vs POS Sold Sales"]
        S2["Flag High Overpour Red Alert & Audit Log"]
        S3["Update Perpetual Inventory & Reorder Trigger"]
    end

    Finish(["End"])

    Start --> C1 --> P1 --> C2 --> S1 --> A1 --> A2
    A2 -->|"Yes (Overpour Breach)"| S2 --> C3 --> A1
    A2 -->|"No (Variance Optimal)"| A3 --> S3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Head Bartender / Mixologist | Inputs cocktail recipes and syrup premixes, weighs open liquor bottles on Bluetooth scales at shift end, and logs spoilage or broken bottles. |
| **Lane 2** | Smart Dispenser & Pour Sensors | Measures real-time shot volumes and draft beer tap flow telemetry, broadcasting pour data to the central system. |
| **Lane 3** | Bar Manager / Beverage Director | Evaluates shift overpour variance tables, investigates poured vs sold discrepancies, approves shift stock audits, and reorders spirits. |
| **Lane 4** | Bar & Beverage System Engine | Reconciles poured milliliters against POS sold transactions, flags overpour red alerts, updates perpetual inventory, and triggers reorders. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Recipe Setup & Telemetry Collection**: Bartender inputs cocktail specs (`C1`). Smart Dispenser sensors capture pour volume telemetry (`P1`). Bartender weighs open bottles on Bluetooth scales at shift end (`C2`).
2. **Reconciliation & Variance Evaluation**: Bar Engine compares total poured volume against POS sold transactions (`S1`). Bar Manager reviews overpour variance table (`A1`) and evaluates threshold (`A2`).
   - If **Yes (Overpour Breach > 8%)**: System flags high overpour red alert (`S2`). Bartender logs detailed spoilage and comp notes (`C3`) for manager re-audit (`A1`).
   - If **No (Variance Optimal)**: Manager approves shift audit (`A3`). System updates perpetual inventory and triggers supplier reorders (`S3`), completing execution (`Finish`).
