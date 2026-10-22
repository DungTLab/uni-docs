# Swimlane Diagram — School Canteen Ordering System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Parent / Guardian"]
        C1["Top-Up Meal Wallet & Pre-Order Weekly Lunches"]
        C2["Set Allergen Rules & Daily Spending Limit"]
        C3["Receive Evening Meal Consumption Alert"]
    end

    subgraph Lane2["Student / Pupil"]
        K1["Tap RFID Card at Canteen Fast-Lane"]
        K2["Collect Pre-Ordered Lunch Tray"]
    end

    subgraph Lane3["School Canteen Server"]
        B1["Receive Batch Cooking Sheet"]
        B2["Verify Student Photo & Hand Lunch Tray"]
    end

    subgraph Lane4["School Canteen Core Engine"]
        S1["Verify Meal Pre-Order & Wallet Balance"]
        S2{"Passes Allergen & Daily Spending Cap Check?"}
        S3["Block POS Transaction & Play Red Alert"]
        S4["Deduct Wallet Balance & Log Caloric Intake"]
        S5["Dispatch Consumption Alert to Parent App"]
    end

    Finish(["End"])

    Start --> C1 --> C2 --> S1 --> K1 --> S2
    S2 -->|"No (Restriction Triggered)"| S3 --> B2
    S2 -->|"Yes (Approved)"| S4 --> B1 --> B2 --> K2 --> S5 --> C3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Parent / Guardian | Tops up student meal wallet, pre-orders weekly lunches, configures allergen restriction rules, sets daily spending caps, and receives meal consumption alerts. |
| **Lane 2** | Student / Pupil | Taps RFID student ID card at canteen fast-lane counters, collects pre-ordered lunch trays, and selects safe counter snacks. |
| **Lane 3** | School Canteen Server | Receives daily batch cooking sheets, verifies student photos on POS screens, and hands meal trays to students. |
| **Lane 4** | School Canteen Core Engine | Validates RFID card taps, checks parent allergen and spending cap rules, deducts wallet balances, logs caloric intake, and dispatches parent notifications. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Pre-Ordering & Rule Setup**: Parent tops up wallet, pre-orders weekly lunches (`C1`), and configures allergen/spending cap rules (`C2`). Core Engine validates wallet funds (`S1`).
2. **RFID Card Tap & Rule Check**: Student taps RFID card at canteen counter (`K1`). Core Engine evaluates allergen restrictions and daily spending caps (`S2`).
   - If **No (Restriction Triggered)**: Core Engine blocks transaction and plays red alert (`S3`). Server assists student with an alternative (`B2`).
   - If **Yes (Approved)**: Core Engine deducts wallet balance and logs caloric intake (`S4`).
3. **Meal Serving & Pickup**: Kitchen Chef batch cooks lunches (`B1`). Server verifies student photo and hands tray (`B2`). Student collects lunch tray (`K2`).
4. **Parent Notification & Logging**: Core Engine dispatches meal consumption alert to parent app (`S5`), and Parent receives evening summary (`C3`), completing execution (`Finish`).
