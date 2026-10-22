# Swimlane Diagram — Kitchen Order Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["POS & Order Source"]
        P1["Submit Customer Dine-In / Delivery Order"]
        P2["Transmit Ticket JSON to Kitchen Server"]
    end

    subgraph Lane2["Station Line Cook (Station KDS)"]
        K1["Receive Routed Items on Station Screen"]
        K2["Tap Item to Change Status to Cooking"]
        K3["Finish Preparation & Mark Item Ready"]
    end

    subgraph Lane3["Head Chef / Expeditor (Pass KDS)"]
        E1["Monitor Master Ticket & Station Timers"]
        E2{"Are All Ticket Items Ready?"}
        E3["Inspect Dish Quality & Bump Ticket"]
    end

    subgraph Lane4["Food Runner / Waiter (Runner App)"]
        R1["Receive Order Ready Notification & Chime"]
        R2["Pick Up Tray at Pass & Confirm Delivery"]
    end

    Finish(["End"])

    Start --> P1 --> P2 --> K1 --> E1
    K1 --> K2 --> K3 --> E2
    E2 -->|"No (Items Still Cooking)"| E1
    E2 -->|"Yes (All Items Ready)"| E3 --> R1 --> R2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | POS & Order Source | Submits customer food order from front-of-house POS or online delivery app, transmitting structured order JSON payloads to the kitchen network server. |
| **Lane 2** | Station Line Cook (Station KDS) | Receives parsed item cards at station displays (Grill, Saute, Pantry), updates cooking status (`In Cooking`), prepares dishes, and flags items `Ready`. |
| **Lane 3** | Head Chef / Expeditor (Pass KDS) | Monitors real-time station prep timers on master screen, identifies bottlenecks, verifies plated dish quality, and bumps completed tickets at pass counter. |
| **Lane 4** | Food Runner / Waiter (Runner App) | Receives automated food-ready chime alerts on runner station or handheld POS, picks up prepared trays from pass counter, and confirms delivery to guest table. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Order Transmission & Routing**: POS System submits customer order (`P1`, `P2`). Kitchen Order Management System parses order JSON and dispatches items to station screens (`K1`) and master expeditor display (`E1`).
2. **Station Cooking Workflow**: Station Line Cook views routed items, taps item to set status to `In Cooking` (`K2`), cooks according to specs, and taps `Ready` (`K3`).
3. **Expeditor Quality Check Loop**: Expeditor evaluates master ticket readiness status (`E2`).
   - If **No**: System displays pending items in yellow/red on master screen while cook finishes (`E1`).
   - If **Yes**: Head Chef inspects assembled dishes at pass counter, verifies order accuracy, and bumps ticket (`E3`).
4. **Food Dispatch & Delivery**: Bumping ticket triggers runner ready chime and mobile alert (`R1`). Food Runner picks up food tray at pass counter, confirms pickup (`R2`), and delivers meal to guest table, ending the kitchen process (`Finish`).
