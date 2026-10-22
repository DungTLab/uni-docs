# Context Diagram — Bakery Management System

## Mermaid Code

```mermaid
flowchart LR
    Customer["Retail Bakery Customer"]
    Baker["Head Baker / Pastry Chef"]
    Cashier["Bakery Store Cashier"]
    Manager["Bakery Operations Manager"]
    Admin["System Administrator"]
    POSScale["Bakery POS & Label Scale System"]
    SupplierPortal["Baking Ingredient Supplier Portal"]
    PayGW["Payment Gateway API"]

    System(("Bakery Management System"))

    Customer -->|"submits custom cake orders, specs and deposit payments"| System
    System -->|"returns cake pickup receipts, order status and price quotes"| Customer

    Baker -->|"inputs baking formulas, dough scaling ratios and batch logs"| System
    System -->|"provides daily production sheets, oven timers and prep sheets"| Baker

    Cashier -->|"scans barcode price labels and processes cake pickups"| System
    System -->|"returns product prices, end-of-day discounts and order lookup"| Cashier

    Manager -->|"configures shelf-life rules, markdown discounts and B2B pricing"| System
    System -->|"provides daily stale waste loss, production yield and margin reports"| Manager

    Admin -->|"manages security permissions, scale label templates and settings"| System
    System -->|"provides system health logs and scale integration audit trails"| Admin

    POSScale -->|"transmits weighted item PLUs and barcode label prints"| System
    System -->|"returns updated bakery item prices and tare weight matrices"| POSScale

    System -->|"sends bulk flour and dairy purchase requisitions"| SupplierPortal
    SupplierPortal -->|"returns ingredient price lists and delivery confirmations"| SupplierPortal
    SupplierPortal -->|"acknowledges purchase requisition"| System

    System -->|"requests custom cake deposit charge authorizations"| PayGW
    PayGW -->|"returns payment clearance tokens and deposit receipts"| System
```

## Actor & Interaction Table | Bang Actor & Tuong tac

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Retail Bakery Customer | Primary | Custom cake design choices (flavor, tiers, inscription, photo), deposit payments, pickup date/time | Cake order confirmation chits, pickup reminders, custom cake price quotes | Orders custom cakes online or at retail bakery counter. |
| 2 | Head Baker / Pastry Chef | Primary | Dough formulas (Baker's Percentage), batch mixing logs, oven timer completions, stale waste logs | Daily production sheets, scaled ingredient prep sheets, cake assembly guides | Manages baking production, dough proofing, and pastry decoration. |
| 3 | Bakery Store Cashier | Primary | Scanned barcode price labels, custom cake pickup claim codes, retail cash/card payments | Item prices, applied end-of-day markdown discounts, custom cake pickup details | Operates front-of-house bakery retail POS counter. |
| 4 | Bakery Operations Manager | Primary | Shelf-life expiration rules, end-of-day markdown discount percentages, wholesale B2B pricing | Stale bread waste analytics, daily production yield, product profit margins | Oversees bakery profitability, waste reduction, and wholesale accounts. |
| 5 | System Administrator | Primary | User credentials, security role permissions, barcode label scale templates, system settings | Integration health logs, device scale status alerts, audit trail logs | Manages IT infrastructure, label scale pairings, and user permissions. |
| 6 | Bakery POS & Label Scale System | Supporting | Weighted item PLUs, printed barcode label payloads, transaction totals | Item pricing master, tare weight matrices, barcode format rules | Integrated printing scales generating barcode price labels for bread/pastries. |
| 7 | Baking Ingredient Supplier Portal | Supporting | Ingredient wholesale price lists, delivery confirmations, lot numbers | Automated bulk purchasing requisitions (Flour, Yeast, Butter, Sugar) | External supplier portal for ordering commercial baking supplies. |
| 8 | Payment Gateway API | Supporting | Custom cake deposit authorization tokens, card transaction approvals | Pre-order deposit charge requests, refund instructions | Clears credit card deposit payments for pre-ordered custom cakes. |

## System Boundary Description | Mo ta Pham vi He thong

The **Bakery Management System** automates retail bakery operations, custom cake pre-orders, dough formula scaling using Baker's Percentages, oven batch production scheduling, barcode weight labeling, and shelf-life expiration management. Inside its boundary, the system handles custom cake design tracking, automated dough recipe scaling based on target loaf yield, barcode price label printing integration, end-of-day stale waste logging, wholesale B2B order management, and daily baking profitability analytics.

The system boundary excludes physical baking inside ovens, manual dough kneading, physical counter bread packaging, and direct credit card clearing (handled by Payment Gateways).
