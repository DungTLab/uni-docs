# Context Diagram — Menu Management System

## Mermaid Code

```mermaid
flowchart LR
    Chef["Menu Administrator / Chef"]
    Manager["Restaurant Manager"]
    Franchise["Multi-Store Franchise Manager"]
    Admin["System Administrator"]
    POS["Point of Sale System"]
    App["Digital QR Ordering App"]
    DeliveryAPI["Third-Party Delivery Platforms"]
    Signage["Digital Kiosks & Signage Displays"]

    System(("Menu Management System"))

    Chef -->|"inputs dish items, modifiers and recipes"| System
    System -->|"returns menu preview and allergen validations"| Chef

    Manager -->|"updates localized item availability and prices"| System
    System -->|"provides store menu performance analytics"| Manager

    Franchise -->|"configures global brand menu catalogs"| System
    System -->|"returns multi-store menu compliance reports"| Franchise

    Admin -->|"manages integration API keys and user permissions"| System
    System -->|"provides sync transaction logs and audit trails"| Admin

    System -->|"pushes centralized menu updates and pricing"| POS
    POS -->|"returns sales volume feedback"| System

    System -->|"syncs digital menu cards and dietary tags"| App
    App -->|"returns customer menu view metrics"| System

    System -->|"transmits menu catalogs and availability locks"| DeliveryAPI
    DeliveryAPI -->|"returns channel menu publishing confirmation"| DeliveryAPI
    DeliveryAPI -->|"acknowledges menu sync status"| System

    System -->|"streams high-res dish media and daily specials"| Signage
    Signage -->|"returns display sync confirmation"| System
```

## Actor & Interaction Table | Bang Actor & Tuong tac

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Menu Administrator / Chef | Primary | Dish descriptions, pricing strategies, modifier options, recipe pairings, dietary tags | Visual menu preview, allergen warnings, missing pricing alerts | Configures dish creation, modifier groups, and culinary specifications. |
| 2 | Restaurant Manager | Primary | Localized item 86ing, store-level price overrides, operating hours | Store active menu catalog, sales contribution reports, stockout alerts | Manages day-to-day item availability and local store pricing rules. |
| 3 | Multi-Store Franchise Manager | Primary | Master brand catalogs, regional pricing tiers, mandatory core dishes | Franchise menu compliance reports, cross-store item velocity metrics | Oversees multi-unit franchise menu consistency and regional variations. |
| 4 | System Administrator | Primary | User credentials, security roles, API integration keys, system backup parameters | Omnichannel sync logs, API error alerts, security audit trails | Manages platform infrastructure and API connection credentials. |
| 5 | Point of Sale (POS) System | Supporting | Sales transaction item volume, raw cashier feedback | Centralized menu structure, price matrix, modifier rules, PLU codes | Integrates via REST or WebSocket to receive instantaneous menu syncs. |
| 6 | Digital QR Ordering App | Supporting | Customer menu view logs, search queries | Live digital menu catalogs, dietary filters, high-res photos, combo options | Mobile web and app guest ordering interfaces. |
| 7 | Third-Party Delivery Platforms | Supporting | Channel sync acknowledgments, platform publishing statuses | Channel-specific menus, delivery pricing markups, item stockout locks | Integrates with platforms like GrabFood, Foodpanda, UberEats via APIs. |
| 8 | Digital Kiosks & Signage Displays | Supporting | Display sync acknowledgments, screen telemetry status | High-definition dish photos, promotional videos, dayparting menus | Self-service ordering kiosks and digital overhead menu boards. |

## System Boundary Description | Mo ta Pham vi He thong

The **Menu Management System** serves as the single source of truth for restaurant menu engineering, multi-channel pricing, modifier group configurations, and omnichannel catalog synchronization. Inside its boundary, the system handles menu category structuring, item pricing matrices, daypart schedule automation (Breakfast/Lunch/Dinner), combo meal bundle rules, nutritional and allergen tagging, multilingual translations, and instant menu distribution to POS, QR apps, delivery APIs, and digital signage.

The system boundary excludes actual order transaction processing and cash collection (handled by POS Systems), physical kitchen preparation (handled by Kitchen Order Management Systems), and direct third-party delivery rider dispatching (handled by Delivery Platforms).
