# Conceptual ERD — Agricultural Equipment Management System

## Mermaid Code

```mermaid
erDiagram
    EQUIPMENT_ASSET {
        int equipment_id PK
        string asset_tag
        string serial_number
        string make
        string model
        int model_year
        string category
        decimal purchase_price
        decimal engine_hours_total
        string current_status
    }

    EQUIPMENT_OPERATOR {
        int operator_id PK
        string full_name
        string license_number
        string license_class
        string phone_number
        boolean is_active
    }

    DISPATCH_ASSIGNMENT {
        int assignment_id PK
        int equipment_id FK
        int operator_id FK
        int field_parcel_id
        string task_type
        datetime scheduled_start
        datetime scheduled_end
        decimal actual_hours
        string assignment_status
    }

    TELEMETRY_LOG {
        int telemetry_id PK
        int equipment_id FK
        int engine_rpm
        decimal coolant_temp
        decimal fuel_level_pct
        decimal engine_hours
        decimal latitude
        decimal longitude
        datetime recorded_at
    }

    WORK_ORDER {
        int work_order_id PK
        int equipment_id FK
        int technician_id FK
        string order_type
        string priority
        string description
        datetime scheduled_date
        datetime completion_date
        decimal total_cost
        string order_status
    }

    SPARE_PART {
        int part_id PK
        int supplier_id FK
        string part_number
        string part_name
        string category
        int quantity_in_stock
        int reorder_level
        decimal unit_cost
    }

    PART_USAGE {
        int usage_id PK
        int work_order_id FK
        int part_id FK
        int quantity_used
        decimal unit_price
    }

    FUEL_TRANSACTION {
        int fuel_id PK
        int equipment_id FK
        int operator_id FK
        decimal fuel_liters
        decimal cost_per_liter
        decimal total_cost
        decimal odometer_hours
        datetime dispensed_at
    }

    MAINTENANCE_SCHEDULE {
        int schedule_id PK
        int equipment_id FK
        string service_name
        decimal interval_hours
        int interval_days
        decimal last_service_hours
        decimal next_service_due_hours
    }

    RENTAL_CONTRACT {
        int contract_id PK
        int equipment_id FK
        string client_name
        datetime start_date
        datetime end_date
        decimal hourly_rate
        decimal deposit_amount
        string payment_status
    }

    SUPPLIER {
        int supplier_id PK
        string supplier_name
        string contact_person
        string phone
        string email
        string address
    }

    EQUIPMENT_ASSET ||--o{ DISPATCH_ASSIGNMENT : "assigned_to"
    EQUIPMENT_OPERATOR ||--o{ DISPATCH_ASSIGNMENT : "operates"
    EQUIPMENT_ASSET ||--o{ TELEMETRY_LOG : "streams"
    EQUIPMENT_ASSET ||--o{ WORK_ORDER : "undergoes"
    EQUIPMENT_ASSET ||--o{ FUEL_TRANSACTION : "consumes"
    EQUIPMENT_OPERATOR ||--o{ FUEL_TRANSACTION : "dispenses_to"
    EQUIPMENT_ASSET ||--o{ MAINTENANCE_SCHEDULE : "governed_by"
    EQUIPMENT_ASSET ||--o{ RENTAL_CONTRACT : "leased_under"
    WORK_ORDER ||--o{ PART_USAGE : "requires"
    SPARE_PART ||--o{ PART_USAGE : "used_in"
    SUPPLIER ||--o{ SPARE_PART : "supplies"
```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | EQUIPMENT_ASSET | Thiết Bị / Máy Móc | Stores master profile, technical specs, engine hours, category, valuation, and current status of machinery | equipment_id, asset_tag, serial_number, make, model, engine_hours_total, current_status | Has many DISPATCH_ASSIGNMENT, TELEMETRY_LOG, WORK_ORDER, FUEL_TRANSACTION |
| 2 | EQUIPMENT_OPERATOR | Người Điều Khiển | Stores driver profiles, heavy equipment operator licenses, certifications, and active status | operator_id, full_name, license_number, license_class, phone_number | Operates DISPATCH_ASSIGNMENT, dispenses to FUEL_TRANSACTION |
| 3 | DISPATCH_ASSIGNMENT | Lịch Phân Công Vận Hành | Tracks operational scheduling, field parcel assignments, driver tasks, scheduled dates, and actual hours | assignment_id, equipment_id, operator_id, field_parcel_id, task_type, assignment_status | Belongs to EQUIPMENT_ASSET and EQUIPMENT_OPERATOR |
| 4 | TELEMETRY_LOG | Nhật Ký Telemetry CAN-Bus | High-frequency telemetry storing CAN-bus engine RPM, coolant temperature, fuel rate, and GPS location | telemetry_id, equipment_id, engine_rpm, coolant_temp, fuel_level_pct, recorded_at | Belongs to EQUIPMENT_ASSET |
| 5 | WORK_ORDER | Lệnh Bảo Trì & Sửa Chữa | Captures preventive maintenance and corrective repair work orders, labor hours, technician IDs, and costs | work_order_id, equipment_id, technician_id, order_type, total_cost, order_status | Belongs to EQUIPMENT_ASSET, requires PART_USAGE |
| 6 | SPARE_PART | Phụ Tùng Thay Thế | Inventory catalog of spare parts, filters, belts, oil types, stock quantities, and reorder levels | part_id, supplier_id, part_number, part_name, quantity_in_stock, reorder_level | Supplied by SUPPLIER, used in PART_USAGE |
| 7 | PART_USAGE | Chi Tiết Sử Dụng Phụ Tùng | Records specific spare parts and quantities consumed during the execution of a repair work order | usage_id, work_order_id, part_id, quantity_used, unit_price | Belongs to WORK_ORDER and SPARE_PART |
| 8 | FUEL_TRANSACTION | Giao Dịch Cấp Nhiên Liệu | Records diesel refuel transactions, liters dispensed, cost per liter, and engine hour readings | fuel_id, equipment_id, operator_id, fuel_liters, total_cost, dispensed_at | Belongs to EQUIPMENT_ASSET and EQUIPMENT_OPERATOR |
| 9 | MAINTENANCE_SCHEDULE | Lịch Bảo Trì Định Kỳ | Configures preventive maintenance rules (e.g., 250h oil change) and tracks next due engine hours | schedule_id, equipment_id, service_name, interval_hours, next_service_due_hours | Governs EQUIPMENT_ASSET |
| 10 | RENTAL_CONTRACT | Hợp Đồng Cho Thuê Máy | Manages external machinery rental contracts, client details, billing rates, deposits, and payment status | contract_id, equipment_id, client_name, hourly_rate, deposit_amount, payment_status | Leases EQUIPMENT_ASSET |
| 11 | SUPPLIER | Nhà Cung Cấp Phụ Tùng | Stores OEM parts suppliers, dealer contacts, phone numbers, and address details | supplier_id, supplier_name, contact_person, phone, email | Supplies SPARE_PART |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | EQUIPMENT_ASSET | one-to-many | DISPATCH_ASSIGNMENT | assigned_to | Equipment asset can be assigned to multiple field operation tasks or rental bookings over time |
| 2 | EQUIPMENT_OPERATOR | one-to-many | DISPATCH_ASSIGNMENT | operates | A certified driver operator can be assigned to operate multiple machinery dispatch tasks |
| 3 | EQUIPMENT_ASSET | one-to-many | TELEMETRY_LOG | streams | An equipment asset streams thousands of real-time CAN-bus engine and GPS telemetry log records |
| 4 | EQUIPMENT_ASSET | one-to-many | WORK_ORDER | undergoes | An equipment asset undergoes multiple preventive maintenance and repair work orders over its lifecycle |
| 5 | EQUIPMENT_ASSET | one-to-many | FUEL_TRANSACTION | consumes | An equipment asset consumes fuel across multiple logged refuel transactions |
| 6 | EQUIPMENT_OPERATOR | one-to-many | FUEL_TRANSACTION | dispenses_to | A driver operator receives fuel across multiple authorized refueling transactions |
| 7 | EQUIPMENT_ASSET | one-to-many | MAINTENANCE_SCHEDULE | governed_by | Equipment asset is governed by defined preventive maintenance service schedules (e.g., 250h, 500h) |
| 8 | EQUIPMENT_ASSET | one-to-many | RENTAL_CONTRACT | leased_under | An equipment asset can be leased to third parties under multiple rental contracts over time |
| 9 | WORK_ORDER | one-to-many | PART_USAGE | requires | A maintenance work order can require multiple spare parts and materials during repair execution |
| 10 | SPARE_PART | one-to-many | PART_USAGE | used_in | A spare part item in inventory can be used across multiple maintenance work order repairs |
| 11 | SUPPLIER | one-to-many | SPARE_PART | supplies | An OEM spare parts supplier supplies multiple part catalog items to the farm inventory |
