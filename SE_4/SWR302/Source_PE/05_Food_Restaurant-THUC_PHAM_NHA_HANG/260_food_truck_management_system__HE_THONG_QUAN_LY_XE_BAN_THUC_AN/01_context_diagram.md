# Context Diagram — Food Truck Management System

## Mermaid Code

```mermaid
flowchart LR
    Customer["Food Truck Customer"]
    Operator["Food Truck Operator / Chef"]
    Manager["Fleet Operations Manager"]
    Admin["System Administrator"]
    POS["Mobile POS & Wireless Card Terminal"]
    GPS["GPS Vehicle Telemetry & Fleet Tracker"]
    SocialMedia["Social Media & Location Broadcast API"]
    PayGW["Mobile Payment & E-Wallet Gateway"]

    System(("Food Truck Management System"))

    Customer -->|"submits pre-orders, tracks live truck GPS and pays for meals"| System
    System -->|"returns live truck map location, pickup alerts and receipts"| Customer

    Operator -->|"inputs daily location check-ins, menu items and stock levels"| System
    System -->|"provides mobile order queue, prep tickets and inventory alerts"| Operator

    Manager -->|"configures parking schedules, catering event bookings and permits"| System
    System -->|"provides route sales yield, fleet GMV and vehicle maintenance metrics"| Manager

    Admin -->|"manages security permissions, GPS tracker bindings and API keys"| System
    System -->|"provides system health logs and telemetry connection audit trails"| Admin

    POS -->|"transmits real-time mobile order sales, payment method and timestamps"| System
    System -->|"returns product prices, item stockout (86) locks and tax calculations"| POS

    GPS -->|"transmits continuous vehicle GPS coordinates, speed and engine telemetry"| System
    System -->|"acknowledges GPS telemetry and updates public live map feed"| GPS

    System -->|"broadcasts live truck location pins and schedule updates"| SocialMedia
    SocialMedia -->|"delivers location posts to X, Instagram and Google Maps"| SocialMedia
    SocialMedia -->|"acknowledges social post publish"| System

    System -->|"requests mobile card/e-wallet payment authorizations"| PayGW
    PayGW -->|"returns payment clearance tokens and digital receipts"| System
```

## Actor & Interaction Table | Bang Actor & Tuong tac

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Food Truck Customer | Primary | Pre-orders (skip-the-line), GPS location queries, mobile card/e-wallet payments, SMS notifications | Live food truck map pins, order readiness SMS alerts, digital receipts, menu items | Interacts via customer mobile app or public web map tracker. |
| 2 | Food Truck Operator / Chef | Primary | Shift check-in location, menu item 86 updates, order prep completion, waste logs | Mobile POS queue, compact kitchen prep tickets, low stock alerts, generator fuel status | Operates inside the mobile food truck kitchen. |
| 3 | Fleet Operations Manager | Primary | Weekly parking schedules, municipal permit details, catering event contracts, route assignments | Location sales yield reports, fleet revenue GMV, fuel efficiency analytics, permit alerts | Oversees a fleet of mobile food trucks and central prep kitchen. |
| 4 | System Administrator | Primary | User credentials, security role policies, GPS hardware device bindings, API keys | System health metrics, hardware connection logs, telemetry audit trails | Manages cloud platform infrastructure, GPS device hardware, and permissions. |
| 5 | Mobile POS & Wireless Terminal | Supporting | Real-time offline/online sales line items, cash/card split tenders, order timestamps | Product prices, item 86 stockout locks, tax rates, e-receipt triggers | Handheld cellular/Wi-Fi POS terminal used on the food truck. |
| 6 | GPS Vehicle Telemetry & Fleet Tracker | Supporting | Continuous GPS latitude/longitude coordinates, vehicle speed, generator runtime hours | GPS ping acknowledgments, geofence trigger configurations | Hardware GPS cellular tracker installed on food truck chassis and generator. |
| 7 | Social Media & Location Broadcast API | Supporting | Social media publishing confirmations, map pin index receipts | Automated live location broadcast posts (Twitter/X, Instagram, Google Maps) | Integrates with social platforms to broadcast current truck parking spot. |
| 8 | Mobile Payment & E-Wallet Gateway | Supporting | Cellular card swipe authorizations, Apple Pay, Google Pay, e-wallet tokens | Payment clearance approvals, transaction receipts, refund tokens | Processes wireless mobile payments over 4G/5G cellular networks. |

## System Boundary Description | Mo ta Pham vi He thong

The **Food Truck Management System** manages mobile food truck fleet operations, real-time GPS location tracking, wireless mobile POS transactions, location-based social media broadcasting, and compact kitchen inventory logistics. Inside its boundary, the system handles real-time vehicle GPS telemetry ingestion, public map pin updating, automated social media location broadcasting, skip-the-line pre-ordering, parking permit and event schedule management, onboard generator fuel and water tank monitoring, and route sales yield analytics.

The system boundary excludes physical vehicle driving by operators, physical food cooking on grills, physical generator mechanical repair, and raw credit card clearance (handled by Mobile Payment Gateways).
