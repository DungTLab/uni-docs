# Conceptual ERD — Drone-based Crop Surveillance System

## Mermaid Code

```mermaid
erDiagram
    FARM_PARCEL {
        int parcel_id PK
        int farmer_id FK
        string parcel_name
        decimal area_hectares
        string geo_boundary_polygon
        string crop_type
        decimal elevation_mean
    }

    DRONE_EQUIPMENT {
        int drone_id PK
        string serial_number
        string model_name
        string payload_type
        int max_flight_time_minutes
        string battery_status
        string hardware_status
    }

    FLIGHT_MISSION {
        int mission_id PK
        int parcel_id FK
        int drone_id FK
        int pilot_id FK
        string mission_name
        decimal planned_altitude_meters
        decimal overlap_percentage
        string flight_status
        datetime scheduled_at
    }

    RAW_AERIAL_IMAGE {
        int image_id PK
        int mission_id FK
        string camera_band
        decimal latitude
        decimal longitude
        decimal altitude
        string image_path
        datetime captured_at
    }

    ORTHOMOSAIC_MAP {
        int map_id PK
        int mission_id FK
        string map_type
        decimal resolution_cm_per_pixel
        string geotiff_file_path
        string bounding_box
        datetime generated_at
    }

    VEGETATION_INDEX_DATA {
        int index_id PK
        int map_id FK
        string index_type
        decimal mean_val
        decimal min_val
        decimal max_val
        decimal stress_area_hectares
        datetime calculated_at
    }

    CROP_STRESS_ANOMALY {
        int anomaly_id PK
        int map_id FK
        int index_id FK
        string stress_type
        string severity_level
        string anomaly_polygon
        string recommended_action
    }

    STAND_COUNT_RECORD {
        int count_id PK
        int mission_id FK
        int total_plants_counted
        decimal target_density_per_ha
        decimal emergence_rate_pct
        string map_layer_url
    }

    PRESCRIPTION_MAP {
        int prescription_id PK
        int mission_id FK
        string target_input_type
        string application_rate_unit
        string shapefile_url
        datetime created_at
    }

    DRONE_DOCKING_STATION {
        int dock_id PK
        int current_drone_id FK
        string location_name
        decimal latitude
        decimal longitude
        string charging_status
        string weather_station_status
    }

    FLIGHT_PILOT_USER {
        int pilot_id PK
        string full_name
        string license_number
        date license_expiry_date
        string user_role
        string phone_number
        boolean is_active
    }

    FARM_PARCEL ||--o{ FLIGHT_MISSION : "targets"
    DRONE_EQUIPMENT ||--o{ FLIGHT_MISSION : "executes"
    FLIGHT_PILOT_USER ||--o{ FLIGHT_MISSION : "pilots"
    FLIGHT_MISSION ||--o{ RAW_AERIAL_IMAGE : "captures"
    FLIGHT_MISSION ||--o{ ORTHOMOSAIC_MAP : "generates"
    FLIGHT_MISSION ||--o{ STAND_COUNT_RECORD : "yields"
    FLIGHT_MISSION ||--o{ PRESCRIPTION_MAP : "produces"
    ORTHOMOSAIC_MAP ||--o{ VEGETATION_INDEX_DATA : "contains"
    ORTHOMOSAIC_MAP ||--o{ CROP_STRESS_ANOMALY : "displays"
    VEGETATION_INDEX_DATA ||--o{ CROP_STRESS_ANOMALY : "identifies"
    DRONE_EQUIPMENT ||--|| DRONE_DOCKING_STATION : "housed_in"
```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | FARM_PARCEL | Thửa Đất Nông Nghiệp | Represents geographic farm fields, area in hectares, crop varieties, and spatial GIS polygon boundaries | parcel_id, parcel_name, area_hectares, geo_boundary_polygon, crop_type | Has many FLIGHT_MISSION |
| 2 | DRONE_EQUIPMENT | Thiết Bị Drone / UAV | Stores unmanned aerial vehicle hardware specifications, sensor payload types, battery state, and status | drone_id, serial_number, model_name, payload_type, hardware_status | Executes FLIGHT_MISSION, housed in DRONE_DOCKING_STATION |
| 3 | FLIGHT_MISSION | Sứ Mệnh Bay Giám Sát | Central record capturing flight plans, altitude targets, overlap ratios, waypoint status, and timestamps | mission_id, parcel_id, drone_id, pilot_id, planned_altitude_meters, flight_status | Targets PARCEL, executed by DRONE, piloted by PILOT, yields IMAGES and MAPS |
| 4 | RAW_AERIAL_IMAGE | Ảnh Hàng Không Thô | Stores metadata for individual geotagged photos captured during flight (multispectral bands, RTK GPS, orientation) | image_id, mission_id, camera_band, latitude, longitude, image_path | Belongs to FLIGHT_MISSION |
| 5 | ORTHOMOSAIC_MAP | Bản Đồ Ghép Cánh Đồng | Stores stitched 2D GeoTIFF orthomosaics, spatial resolution ($cm/\text{pixel}$), bounding box, and DEM rasters | map_id, mission_id, map_type, resolution_cm_per_pixel, geotiff_file_path | Belongs to FLIGHT_MISSION, contains VEGETATION_INDEX and ANOMALY |
| 6 | VEGETATION_INDEX_DATA | Dữ Liệu Chỉ Số Thực Vật | Holds calculated multispectral indices (NDVI, NDRE, MSAVI), mean values, and stressed area metrics | index_id, map_id, index_type, mean_val, stress_area_hectares | Belongs to ORTHOMOSAIC_MAP, identifies CROP_STRESS_ANOMALY |
| 7 | CROP_STRESS_ANOMALY | Dấu Hiệu Bệnh & Stress Cây | Captures georeferenced stress anomaly polygons (nitrogen deficit, water stress, pest infection clusters) | anomaly_id, map_id, index_id, stress_type, severity_level, anomaly_polygon | Belongs to ORTHOMOSAIC_MAP and VEGETATION_INDEX |
| 8 | STAND_COUNT_RECORD | Báo Cáo Mật Độ Cây Trồng | Stores AI seedling count results, plant emergence rates, target plant density per hectare, and count layers | count_id, mission_id, total_plants_counted, emergence_rate_pct | Yielded by FLIGHT_MISSION |
| 9 | PRESCRIPTION_MAP | Bản Đồ Phân Bón Biến Thiên | Captures Variable Rate Application (VRA) fertilizer/pesticide shapefiles for smart tractor machinery | prescription_id, mission_id, target_input_type, shapefile_url | Produced by FLIGHT_MISSION |
| 10 | DRONE_DOCKING_STATION | Trạm Sạc & Trạm Bay Tự Động | Stores autonomous drone docking hardware status, weather sensor telemetry, charging state, and drone shelter status | dock_id, current_drone_id, location_name, charging_status | Houses DRONE_EQUIPMENT |
| 11 | FLIGHT_PILOT_USER | Phi Công / Operator | Stores remote pilot credentials, FAA Part 107 / CAAV licenses, license expiration dates, and contact info | pilot_id, full_name, license_number, user_role, is_active | Pilots FLIGHT_MISSION |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | FARM_PARCEL | one-to-many | FLIGHT_MISSION | targets | A farm parcel can be the target of multiple drone surveillance flight missions over time |
| 2 | DRONE_EQUIPMENT | one-to-many | FLIGHT_MISSION | executes | A drone aircraft can execute multiple surveillance flight missions across different fields |
| 3 | FLIGHT_PILOT_USER | one-to-many | FLIGHT_MISSION | pilots | A certified remote pilot can supervise and execute multiple drone flight missions |
| 4 | FLIGHT_MISSION | one-to-many | RAW_AERIAL_IMAGE | captures | A flight mission captures hundreds of individual geotagged raw aerial images |
| 5 | FLIGHT_MISSION | one-to-many | ORTHOMOSAIC_MAP | generates | A flight mission generates RGB, multispectral, and DEM orthomosaic map layers |
| 6 | FLIGHT_MISSION | one-to-many | STAND_COUNT_RECORD | yields | A flight mission yields automated AI plant seedling count and emergence records |
| 7 | FLIGHT_MISSION | one-to-many | PRESCRIPTION_MAP | produces | A flight mission produces variable-rate fertilizer prescription shapefile packages |
| 8 | ORTHOMOSAIC_MAP | one-to-many | VEGETATION_INDEX_DATA | contains | An orthomosaic map contains multiple computed vegetation index layers (NDVI, NDRE) |
| 9 | ORTHOMOSAIC_MAP | one-to-many | CROP_STRESS_ANOMALY | displays | An orthomosaic map displays detected crop stress anomaly polygons and severity clusters |
| 10 | VEGETATION_INDEX_DATA | one-to-many | CROP_STRESS_ANOMALY | identifies | Vegetation index score anomalies identify specific spatial crop stress areas |
| 11 | DRONE_EQUIPMENT | one-to-one | DRONE_DOCKING_STATION | housed_in | An autonomous drone unit can be housed in and managed by one dedicated docking station |
