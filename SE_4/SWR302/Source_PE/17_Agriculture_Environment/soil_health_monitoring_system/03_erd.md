# Conceptual ERD — Soil Health Monitoring System

## Mermaid Code

```mermaid
erDiagram
    SOIL_PLOT {
        int plot_id PK
        int farmer_id FK
        string plot_code
        decimal area_hectares
        string geo_polygon
        string soil_textural_class
        string primary_crop
        string plot_status
    }

    SAMPLING_ZONE {
        int zone_id PK
        int plot_id FK
        string zone_name
        decimal depth_layer_cm
        decimal latitude
        decimal longitude
        decimal grid_resolution_meters
    }

    SOIL_SENSOR_PROBE {
        int sensor_id PK
        int zone_id FK
        string serial_number
        string sensor_type
        decimal installation_depth_cm
        datetime calibration_date
        string sensor_status
    }

    SOIL_TELEMETRY_LOG {
        int telemetry_id PK
        int sensor_id FK
        decimal nitrogen_ppm
        decimal phosphorus_ppm
        decimal potassium_ppm
        decimal soil_ph
        decimal electrical_conductivity_ds_m
        decimal moisture_pct
        datetime recorded_at
    }

    LAB_SOIL_SAMPLE {
        int sample_id PK
        int zone_id FK
        int sampler_id FK
        datetime collection_date
        string testing_lab_name
        decimal organic_matter_pct
        decimal cation_exchange_capacity
        string test_result
    }

    SOIL_HEALTH_INDEX_RECORD {
        int shi_id PK
        int plot_id FK
        datetime calculated_at
        int overall_shi_score
        int physical_sub_index
        int chemical_sub_index
        int biological_sub_index
        string soil_tier_category
    }

    NUTRIENT_RECOMMENDATION {
        int recommendation_id PK
        int plot_id FK
        string crop_type
        decimal target_nitrogen_kg_ha
        decimal target_phosphorus_kg_ha
        decimal target_potassium_kg_ha
        decimal lime_ton_ha
        string shapefile_url
    }

    SOIL_DEGRADATION_EVENT {
        int degradation_id PK
        int plot_id FK
        string degradation_type
        string severity_level
        decimal affected_area_ha
        datetime detected_at
        string remediation_plan
    }

    CARBON_SEQUESTRATION_LOG {
        int carbon_id PK
        int plot_id FK
        decimal baseline_soc_stock_t_ha
        decimal current_soc_stock_t_ha
        decimal annual_sequestration_rate
        datetime verified_at
    }

    REGENERATIVE_PRACTICE {
        int practice_id PK
        int plot_id FK
        string practice_type
        date start_date
        string cover_crop_species
        string tillage_method
        string organic_amendment_type
    }

    SYSTEM_USER {
        int user_id PK
        string username
        string email
        string user_role
        string organization
        boolean is_active
    }

    SOIL_PLOT ||--o{ SAMPLING_ZONE : "divided_into"
    SAMPLING_ZONE ||--o{ SOIL_SENSOR_PROBE : "houses"
    SOIL_SENSOR_PROBE ||--o{ SOIL_TELEMETRY_LOG : "measures"
    SAMPLING_ZONE ||--o{ LAB_SOIL_SAMPLE : "sampled_at"
    SOIL_PLOT ||--o{ SOIL_HEALTH_INDEX_RECORD : "evaluated_in"
    SOIL_PLOT ||--o{ NUTRIENT_RECOMMENDATION : "receives"
    SOIL_PLOT ||--o{ SOIL_DEGRADATION_EVENT : "suffers"
    SOIL_PLOT ||--o{ CARBON_SEQUESTRATION_LOG : "tracks"
    SOIL_PLOT ||--o{ REGENERATIVE_PRACTICE : "implements"
    SYSTEM_USER ||--o{ LAB_SOIL_SAMPLE : "collects"
    SYSTEM_USER ||--o{ SOIL_HEALTH_INDEX_RECORD : "calculates"
```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | SOIL_PLOT | Thửa Đất Nông Nghiệp | Stores farm plot master records, GIS boundary polygons, soil texture (clay, loam, sand), primary crop, and area | plot_id, farmer_id, plot_code, area_hectares, geo_polygon, soil_textural_class, plot_status | Divided into SAMPLING_ZONE, receives RECOMMENDATION, tracks CARBON |
| 2 | SAMPLING_ZONE | Vùng / Lưới Lấy Mẫu Đất | Represents grid sampling nodes, depth layers ($0-15\text{ cm}, 15-30\text{ cm}$), GIS point coordinates, and grid resolution | zone_id, plot_id, zone_name, depth_layer_cm, latitude, longitude, grid_resolution_meters | Belongs to SOIL_PLOT, houses SENSOR_PROBE, sampled at LAB_SAMPLE |
| 3 | SOIL_SENSOR_PROBE | Cảm Biến Đất Trong Đất | Stores submersible soil sensor sonde profiles, installation depth, sensor types (NPK, pH, EC, VWC), and calibration dates | sensor_id, zone_id, serial_number, sensor_type, installation_depth_cm, sensor_status | Housed in SAMPLING_ZONE, measures TELEMETRY_LOG |
| 4 | SOIL_TELEMETRY_LOG | Nhật Ký Dữ Liệu Cảm Biến Đất | High-frequency telemetry storing soil NPK ($mg/kg$), pH, electrical conductivity ($dS/m$), moisture ($\%$), and temperature | telemetry_id, sensor_id, nitrogen_ppm, soil_ph, electrical_conductivity_ds_m, moisture_pct, recorded_at | Measured by SOIL_SENSOR_PROBE |
| 5 | LAB_SOIL_SAMPLE | Mẫu Đất Kiểm Nghiệm Lab | Captures certified laboratory test results for Soil Organic Matter (SOM %), CEC, micronutrients, and heavy metal assays | sample_id, zone_id, sampler_id, collection_date, organic_matter_pct, test_result | Sampled at SAMPLING_ZONE, collected by SYSTEM_USER |
| 6 | SOIL_HEALTH_INDEX_RECORD | Kết Quả Chỉ Số Sức Khỏe Đất | Stores computed Soil Health Index (SHI $0-100$) scores, physical/chemical/biological sub-indices, and soil tier categories | shi_id, plot_id, calculated_at, overall_shi_score, physical_sub_index, chemical_sub_index, soil_tier_category | Evaluated for SOIL_PLOT, calculated by SYSTEM_USER |
| 7 | NUTRIENT_RECOMMENDATION | Khuyến Cáo Phân Bón Biến Thiên | Formulates variable-rate N-P-K ($kg/ha$) and lime ($tons/ha$) application prescriptions, target crop goals, and shapefile URLs | recommendation_id, plot_id, crop_type, target_nitrogen_kg_ha, lime_ton_ha, shapefile_url | Received by SOIL_PLOT |
| 8 | SOIL_DEGRADATION_EVENT | Biến Cố Suy Thoái Đất | Records soil degradation incidents (acidification, salinization, compaction, erosion), severity levels, and remediation plans | degradation_id, plot_id, degradation_type, severity_level, affected_area_ha, remediation_plan | Suffered by SOIL_PLOT |
| 9 | CARBON_SEQUESTRATION_LOG | Nhật Ký Tích Lũy Carbon Đất | Tracks baseline and current Soil Organic Carbon (SOC) stocks ($t C/ha$) and annual carbon sequestration rates ($t CO_2e/ha/yr$) | carbon_id, plot_id, baseline_soc_stock_t_ha, current_soc_stock_t_ha, annual_sequestration_rate, verified_at | Tracked for SOIL_PLOT |
| 10 | REGENERATIVE_PRACTICE | Thực Hành Nông Nghiệp Tái Sinh | Logs adoption of regenerative practices (no-till, cover crops, green manure, biochar, reduced chemical inputs) | practice_id, plot_id, practice_type, start_date, cover_crop_species, tillage_method | Implemented on SOIL_PLOT |
| 11 | SYSTEM_USER | Người Dùng Hệ Thống | Stores user accounts, roles (Farmer, Agronomist, Extension Officer, Admin), organizations, and expert qualifications | user_id, username, user_role, organization, is_active | Collects LAB_SOIL_SAMPLE, calculates SOIL_HEALTH_INDEX_RECORD |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | SOIL_PLOT | one-to-many | SAMPLING_ZONE | divided_into | A farm soil plot is divided into multiple spatial grid sampling zones and depth layers |
| 2 | SAMPLING_ZONE | one-to-many | SOIL_SENSOR_PROBE | houses | A sampling zone houses multi-depth in-situ IoT soil sensor sondes (15cm, 30cm, 60cm) |
| 3 | SOIL_SENSOR_PROBE | one-to-many | SOIL_TELEMETRY_LOG | measures | A soil sensor probe measures high-frequency NPK, pH, EC, and moisture telemetry over time |
| 4 | SAMPLING_ZONE | one-to-many | LAB_SOIL_SAMPLE | sampled_at | Physical soil core samples are collected at specific registered sampling zones for lab analysis |
| 5 | SOIL_PLOT | one-to-many | SOIL_HEALTH_INDEX_RECORD | evaluated_in | A soil plot undergoes periodic Soil Health Index (SHI) evaluations over crop seasons |
| 6 | SOIL_PLOT | one-to-many | NUTRIENT_RECOMMENDATION | receives | A soil plot receives variable-rate lime and N-P-K fertilizer recommendations per crop cycle |
| 7 | SOIL_PLOT | one-to-many | SOIL_DEGRADATION_EVENT | suffers | A soil plot can suffer from degradation events such as salinization, acidification, or erosion |
| 8 | SOIL_PLOT | one-to-many | CARBON_SEQUESTRATION_LOG | tracks | A soil plot tracks Soil Organic Carbon (SOC) stock changes over multi-year horizons |
| 9 | SOIL_PLOT | one-to-many | REGENERATIVE_PRACTICE | implements | A soil plot implements regenerative farming practices (cover crops, no-till, biochar) |
| 10 | SYSTEM_USER | one-to-many | LAB_SOIL_SAMPLE | collects | A sampling technician user collects field soil core samples for laboratory analysis |
| 11 | SYSTEM_USER | one-to-many | SOIL_HEALTH_INDEX_RECORD | calculates | An agronomist user calculates and validates Soil Health Index (SHI) scorecards |
