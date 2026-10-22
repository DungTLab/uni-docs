# Conceptual ERD — Connected Car Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    VEHICLE_MODEL["VEHICLE_MODEL"]
    VEHICLE_OWNER["VEHICLE_OWNER"]
    CONNECTED_VEHICLE["CONNECTED_VEHICLE"]
    TELEMATICS_TCU["TELEMATICS_TCU"]
    CAN_TELEMETRY_LOG["CAN_TELEMETRY_LOG"]
    REMOTE_COMMAND["REMOTE_COMMAND"]
    MAINTENANCE_ALERT["MAINTENANCE_ALERT"]
    EV_BATTERY_STATUS["EV_BATTERY_STATUS"]
    CRASH_ECALL_INCIDENT["CRASH_ECALL_INCIDENT"]
    DRIVING_BEHAVIOR_SCORE["DRIVING_BEHAVIOR_SCORE"]
    OTA_FIRMWARE_PACKAGE["OTA_FIRMWARE_PACKAGE"]

    %% Các khối hình thoi quan hệ
    rel_defines_specs{"defines_specs"}
    rel_owns{"owns"}
    rel_equipped_with{"equipped_with"}
    rel_streams{"streams"}
    rel_receives{"receives"}
    rel_issues{"issues"}
    rel_flags{"flags"}
    rel_monitors_power{"monitors_power"}
    rel_experiences{"experiences"}
    rel_assessed_for{"assessed_for"}
    rel_receives_updates{"receives_updates"}

    %% Kết nối 1-1
    CONNECTED_VEHICLE ---|1| rel_equipped_with ---|1| TELEMATICS_TCU
    CONNECTED_VEHICLE ---|1| rel_monitors_power ---|1| EV_BATTERY_STATUS

    %% Kết nối 1-N
    VEHICLE_MODEL ---|1| rel_defines_specs ---|N| CONNECTED_VEHICLE
    VEHICLE_OWNER ---|1| rel_owns ---|N| CONNECTED_VEHICLE
    CONNECTED_VEHICLE ---|1| rel_streams ---|N| CAN_TELEMETRY_LOG
    CONNECTED_VEHICLE ---|1| rel_receives ---|N| REMOTE_COMMAND
    VEHICLE_OWNER ---|1| rel_issues ---|N| REMOTE_COMMAND
    CONNECTED_VEHICLE ---|1| rel_flags ---|N| MAINTENANCE_ALERT
    CONNECTED_VEHICLE ---|1| rel_experiences ---|N| CRASH_ECALL_INCIDENT
    CONNECTED_VEHICLE ---|1| rel_assessed_for ---|N| DRIVING_BEHAVIOR_SCORE
    VEHICLE_MODEL ---|1| rel_receives_updates ---|N| OTA_FIRMWARE_PACKAGE

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | VEHICLE_MODEL | Dòng Xe / Model | Vehicle manufacturer model specifications (Make, Model, Year, Powertrain, Battery size). | model_id (PK), make_name, model_name, powertrain_type, battery_capacity_kwh | Defines specs for Connected Vehicles, receives OTA Firmware Packages |
| 2 | VEHICLE_OWNER | Chủ Sở hữu Xe | Vehicle owner or primary driver profile managing connected vehicle controls. | owner_id (PK), full_name, email, phone_number, emergency_contact_phone | Owns Connected Vehicles, issues Remote Commands |
| 3 | CONNECTED_VEHICLE | Xe kết nối (VIN) | Physical connected car instance identified by 17-character VIN and telematics status. | vehicle_id (PK), model_id (FK), owner_id (FK), vin, license_plate, connection_status | Defined by Model, owned by Owner, equipped with TCU, streams Telemetry |
| 4 | TELEMATICS_TCU | Bộ Telematics TCU | On-board Telematics Control Unit hardware equipped with cellular eSIM and firmware. | tcu_id (PK), vehicle_id (FK), serial_number, ime_number, esim_iccid, firmware_version | Equipped on Connected Vehicle |
| 5 | CAN_TELEMETRY_LOG | Nhật ký Telemetry CAN | High-frequency CAN bus sensor log recording vehicle speed, RPM, GPS coordinates, and fuel level. | telemetry_id (PK), vehicle_id (FK), speed_kmh, fuel_level_pct, latitude, longitude | Streamed from Connected Vehicle |
| 6 | REMOTE_COMMAND | Lệnh Điều khiển Từ xa | Remote command issued from mobile app (Lock/Unlock, Pre-Climate, Remote Start, Horn). | command_id (PK), vehicle_id (FK), owner_id (FK), command_type, execution_status | Issued by Owner, received by Connected Vehicle |
| 7 | MAINTENANCE_ALERT | Báo động Bảo trì DTC | Diagnostic Trouble Code (DTC) alert flagging engine, brake, or battery sensor faults. | alert_id (PK), vehicle_id (FK), dtc_code, subsystem_affected, severity_level | Flagged by Connected Vehicle |
| 8 | EV_BATTERY_STATUS | Trạng thái Pin EV | Real-time high-voltage EV battery status tracking State of Charge (SOC), SOH, and range. | battery_id (PK), vehicle_id (FK), state_of_charge_pct, battery_health_soh, remaining_range_km | Monitors Connected Vehicle power |
| 9 | CRASH_ECALL_INCIDENT | Sự cố Va chạm eCall | Severe collision incident record logging impact delta-V, airbag deployment, and crash GPS. | incident_id (PK), vehicle_id (FK), impact_delta_v, airbags_deployed_count, latitude | Experienced by Connected Vehicle |
| 10 | DRIVING_BEHAVIOR_SCORE | Đánh giá Hành vi Lái xe | Telematics evaluation of driver safety based on hard braking, rapid acceleration, and speeding. | score_id (PK), vehicle_id (FK), hard_braking_events, rapid_accel_events, safety_score_rating | Assessed for Connected Vehicle |
| 11 | OTA_FIRMWARE_PACKAGE | Bản Firmware OTA | Containerized binary firmware update package released by vehicle OEM for ECU flashing. | package_id (PK), model_id (FK), version_code, target_ecu_name, binary_file_hash | Updates Vehicle Model |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | VEHICLE_MODEL | one-to-many | CONNECTED_VEHICLE | defines_specs | A Vehicle Model defines specifications for multiple Connected Vehicles. |
| 2 | VEHICLE_OWNER | one-to-many | CONNECTED_VEHICLE | owns | A Vehicle Owner owns one or multiple Connected Vehicles. |
| 3 | CONNECTED_VEHICLE | one-to-one | TELEMATICS_TCU | equipped_with | A Connected Vehicle is equipped with a single Telematics TCU unit. |
| 4 | CONNECTED_VEHICLE | one-to-many | CAN_TELEMETRY_LOG | streams | A Connected Vehicle streams high-frequency CAN Telemetry Logs. |
| 5 | CONNECTED_VEHICLE | one-to-many | REMOTE_COMMAND | receives | A Connected Vehicle receives multiple Remote Commands over time. |
| 6 | VEHICLE_OWNER | one-to-many | REMOTE_COMMAND | issues | A Vehicle Owner issues multiple Remote Commands. |
| 7 | CONNECTED_VEHICLE | one-to-many | MAINTENANCE_ALERT | flags | A Connected Vehicle flags multiple Diagnostic Maintenance Alerts. |
| 8 | CONNECTED_VEHICLE | one-to-one | EV_BATTERY_STATUS | monitors_power | A Connected Vehicle monitors its power via an EV Battery Status record. |
| 9 | CONNECTED_VEHICLE | one-to-many | CRASH_ECALL_INCIDENT | experiences | A Connected Vehicle can experience Crash eCall Incidents. |
| 10 | CONNECTED_VEHICLE | one-to-many | DRIVING_BEHAVIOR_SCORE | assessed_for | A Connected Vehicle is assessed for periodic Driving Behavior Scores. |
| 11 | VEHICLE_MODEL | one-to-many | OTA_FIRMWARE_PACKAGE | receives_updates | A Vehicle Model receives multiple OTA Firmware Packages. |
