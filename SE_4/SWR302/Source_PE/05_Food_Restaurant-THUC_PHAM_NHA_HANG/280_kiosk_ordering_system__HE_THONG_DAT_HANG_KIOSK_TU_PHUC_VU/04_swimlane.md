# Swimlane Diagram — Kiosk Ordering System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Self-Service Kiosk Customer"]
        C1["Tap Touchscreen Menu & Select Combo Modifiers"]
        C2["Tap EMV Chip or Contactless NFC Credit Card"]
        C3["Collect Thermal Receipt & Pickup Number Token"]
    end

    subgraph Lane2["Restaurant Store Manager"]
        M1["Toggle 86 Out-of-Stock Items & Review Sales"]
    end

    subgraph Lane3["Kiosk Maintenance Field Technician"]
        T1["Replace Thermal Paper Roll & Run Diagnostics"]
    end

    subgraph Lane4["Kiosk Ordering Core Engine"]
        S1["Render Visual Menu & AI Upsell Recommendations"]
        S2{"Is Payment Approved by Gateway API?"}
        S3["Display Payment Declined & Retain Cart"]
        S4["Inject Paid Order into Kitchen KDS & Print Receipt"]
        S5["Sync POS Catalog & Buffer Offline Orders"]
    end

    Finish(["End"])

    Start --> C1 --> S1 --> C2 --> S2
    S2 -->|"No (Declined / Timeout)"| S3 --> Finish
    S2 -->|"Yes (Payment Approved)"| S4 --> C3 --> S5 --> M1 --> T1 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Self-Service Kiosk Customer | Taps touchscreen to select menu items, customizes modifiers, accepts combo upsells, taps card for EMV/NFC payment, and collects receipts. |
| **Lane 2** | Restaurant Store Manager | Toggles 86 out-of-stock items, configures kiosk menu layouts, monitors receipt paper status, and reviews sales analytics. |
| **Lane 3** | Kiosk Maintenance Field Technician | Performs hardware diagnostics, calibrates touchscreen touch grid, replaces thermal receipt paper rolls, and updates firmware. |
| **Lane 4** | Kiosk Ordering Core Engine | Renders visual UI, generates AI upsell prompts, processes EMV payment authorizations, injects orders into KDS screens, and buffers offline queues. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Menu Browsing & Upsell Rendering**: Customer taps touchscreen menu and configures item modifiers (`C1`). Core Engine renders high-res visual UI and presents AI combo upsell prompts (`S1`).
2. **Payment Authorization**: Customer inserts EMV chip card or taps Apple/Google Pay NFC (`C2`). Core Engine communicates with Payment Gateway (`S2`).
   - If **No (Declined / Timeout)**: Core Engine displays decline message and retains cart items (`S3`).
   - If **Yes (Payment Approved)**: Core Engine injects order ticket into kitchen KDS screens within 1 second and triggers receipt print job (`S4`).
3. **Receipt & Pickup Token**: Customer receives printed thermal receipt containing order summary and large pickup token number (`C3`).
4. **POS Inventory Sync & Hardware Check**: Core Engine syncs stock depletion counts with POS catalog (`S5`). Store Manager monitors stock 86 status (`M1`), and Technician runs hardware maintenance diagnostics (`T1`), completing execution (`Finish`).
