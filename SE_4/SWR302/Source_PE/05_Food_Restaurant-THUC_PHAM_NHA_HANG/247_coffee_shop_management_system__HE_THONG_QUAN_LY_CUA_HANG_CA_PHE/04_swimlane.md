# Swimlane Diagram — Coffee Shop Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Customer (Guest)"]
        C1["Select Beverage & Customization Options"]
        C2["Select Payment Method & Pay Bill"]
        C3["Receive Receipt & Pickup Buzzer Ticket"]
        C4["Pick Up Prepared Beverage & Enjoy"]
    end

    subgraph Lane2["Cashier / POS Counter"]
        K1["Input Order Items & Modifiers on POS"]
        K2["Scan Loyalty Member Barcode"]
        K3["Collect Payment & Open Cash Drawer"]
    end

    subgraph Lane3["Barista (Bar Display System)"]
        B1["Receive Drink Ticket on BDS & Print Label"]
        B2["Grind Beans & Extract Espresso Shot"]
        B3["Steam Milk & Assemble Drink"]
        B4["Mark Drink Ready & Trigger Buzzer Alert"]
    end

    subgraph Lane4["Coffee Shop Core Engine"]
        S1["Validate Stock & Apply Member Stamps"]
        S2["Route Ticket to BDS & Print Cup Label"]
        S3["Deduct Raw Coffee Bean Stock Balance"]
        S4{"Is Bean Inventory Below Threshold?"}
        S5["Trigger Automated Supplier Reorder Alert"]
    end

    Finish(["End"])

    Start --> C1 --> K1 --> K2 --> S1 --> K3 --> C2 --> S2 --> B1 --> B2 --> B3 --> B4 --> S3 --> S4
    S4 -->|"Yes"| S5 --> C3 --> C4 --> Finish
    S4 -->|"No"| C3 --> C4 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Customer (Guest) | Selects coffee beverages, specifies customization parameters (milk, sweetness, ice, shots), completes payment checkout, receives pickup buzzer ticket, and collects completed drink. |
| **Lane 2** | Cashier / POS Counter | Enters drink items and custom modifiers into counter POS, scans customer loyalty barcode, accepts cash/card/QR payments, and opens cash drawer. |
| **Lane 3** | Barista (Bar Display System) | Reads drink specifications on BDS display and sticky cup label, grinds coffee beans, pulls espresso shots, steams milk, assembles drink, and triggers pickup buzzer when ready. |
| **Lane 4** | Coffee Shop Core Engine | Validates menu item stock availability, adds loyalty member stamps, routes drink tickets to BDS screens, decrements raw coffee bean balances perpetually, and triggers reorder alerts. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Ordering & Customization**: Customer selects coffee items and custom options (`C1`). Cashier inputs choices into counter POS (`K1`) and scans customer loyalty barcode (`K2`).
2. **Loyalty & Payment Checkout**: System checks member stamp balance (`S1`). Customer selects tender method and completes payment (`K3`, `C2`).
3. **Bar Dispatch & Extraction**: System routes paid order to Bar Display System (BDS) screen and prints sticky cup label (`S2`). Barista reads ticket (`B1`), grinds fresh beans, pulls espresso shots (`B2`), steams milk, and finishes assembly (`B3`).
4. **Pickup Alert & Inventory Depletion**: Barista taps BDS marking drink `Ready` (`B4`), triggering customer pickup buzzer. System decrements raw coffee bean inventory based on recipe shot weights (`S3`).
5. **Reorder Check & Completion**: System checks coffee bean inventory against minimum reorder safety thresholds (`S4`).
   - If **Yes**: System sends automated reorder alert to bean supplier (`S5`).
   - Customer collects drink at pickup counter (`C3`, `C4`), completing the store workflow (`Finish`).
