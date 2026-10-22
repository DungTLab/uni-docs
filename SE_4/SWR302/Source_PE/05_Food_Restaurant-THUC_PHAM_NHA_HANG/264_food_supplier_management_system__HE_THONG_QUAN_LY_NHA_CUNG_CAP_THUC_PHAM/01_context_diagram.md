# Context Diagram — Food Supplier Management System

## Mermaid Code

```mermaid
flowchart LR
    Vendor["Food Supplier Vendor"]
    Procurement["Restaurant Procurement Manager"]
    Receiving["Receiving Dock Inspector"]
    APSpecialist["Accounts Payable Specialist"]
    Admin["System Administrator"]
    B2BPortal["Supplier B2B Portal & Catalog API"]
    ColdChain["Cold Chain Temperature Telemetry API"]
    PaymentGW["Banking & ERP Payment Gateway"]

    System(("Food Supplier Management System"))

    Vendor -->|"submits price catalogs, ASN ship notices and HACCP certs"| System
    System -->|"returns purchase orders, delivery schedules and payment advice"| Vendor

    Procurement -->|"creates POs, manages supplier contracts and reviews SLA scorecards"| System
    System -->|"provides price variance alerts, spending analytics and contract renewals"| Procurement

    Receiving -->|"inputs dock receiving receipts, temperature logs and quality grades"| System
    System -->|"provides expected PO manifests, receiving specs and RTV debit forms"| Receiving

    APSpecialist -->|"performs 3-way invoice matching and approves vendor payments"| System
    System -->|"provides matched invoices, discount opportunities and payment batches"| APSpecialist

    Admin -->|"manages security permissions, ERP tokens and approval thresholds"| System
    System -->|"provides system health logs and integration telemetry audit trails"| Admin

    System -->|"syncs purchase orders and catalog prices with supplier B2B portals"| B2BPortal
    B2BPortal -->|"returns order acknowledgments and real-time inventory availability"| B2BPortal
    B2BPortal -->|"acknowledges PO sync"| System

    ColdChain -->|"transmits real-time refrigerated truck container temperature logs"| System
    System -->|"acknowledges telemetry and triggers cold chain breach alerts"| ColdChain

    System -->|"requests electronic ACH/wire payment disbursements"| PaymentGW
    PaymentGW -->|"returns payment clearance tokens and bank settlement receipts"| System
```

## Actor & Interaction Table | Bang Actor & Tuong tac

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Food Supplier Vendor | Primary | Wholesale price catalogs, Advance Shipping Notices (ASN), HACCP/ISO food safety certificates, invoices | Electronic Purchase Orders (PO), delivery slot confirmations, remittance advice | External commercial food distributor (e.g., Sysco, US Foods, Local Organic Farms). |
| 2 | Restaurant Procurement Manager | Primary | Purchase orders, supplier contract terms, RFP bidding requests, SLA evaluation criteria | Supplier performance scorecards, purchase price variance alerts, procurement analytics | Oversees corporate sourcing, supplier negotiations, and purchasing contracts. |
| 3 | Receiving Dock Inspector | Primary | Receiving dock receipts, delivered temperature logs, freshness inspections, RTV claims | Expected PO delivery manifests, receiving quality specifications, RTV debit forms | Inspects incoming food shipments at warehouse/restaurant receiving docks. |
| 4 | Accounts Payable Specialist | Primary | Supplier invoices, payment approvals, 3-way match overrides, early payment discount selections | Matched PO-Receiving-Invoice batches, vendor aging reports, payment disbursement files | Manages vendor billing reconciliation, invoice matching, and AP disbursements. |
| 5 | System Administrator | Primary | User credentials, security role policies, ERP integration tokens, approval limits | System health metrics, integration telemetry logs, security audit trails | Manages software infrastructure, system settings, and security permissions. |
| 6 | Supplier B2B Portal & Catalog API | Supporting | Electronic catalog price matrices, stock availability feeds, PO acknowledgments | Automated purchase orders (EDI 850 / API), PO revision requests | Integrates with supplier ERP/EDI systems for automated purchase order routing. |
| 7 | Cold Chain Temperature Telemetry API | Supporting | Continuous transit container temperature logs (e.g., 2°C - 4°C), GPS transit coordinates | Telemetry acknowledgments, temperature breach thresholds | Ingests IoT temperature sensor pings from refrigerated delivery trucks. |
| 8 | Banking & ERP Payment Gateway | Supporting | Electronic ACH clearance tokens, wire transfer confirmations, bank receipts | Vendor payment disbursement batches, electronic remittance instructions | Executes electronic funds transfers (ACH, Wire) to vendor bank accounts. |

## System Boundary Description | Mo ta Pham vi He thong

The **Food Supplier Management System** manages wholesale food procurement, supplier onboarding, food safety compliance, receiving dock inspections, cold chain tracking, 3-way invoice matching, and supplier performance scorecards. Inside its boundary, the system handles purchase order creation, wholesale price matrix management, HACCP certificate expiration tracking, dock receiving temperature validation, Return-to-Vendor (RTV) debit memo generation, automated 3-way matching (PO vs Receiving Receipt vs Invoice), and supplier SLA performance rating (On-Time Delivery, Quality Yield, Price Compliance).

The system boundary excludes physical unloading of trucks, physical food cooking, bank wire clearing (handled by Banking Gateways), and physical farming operations.
