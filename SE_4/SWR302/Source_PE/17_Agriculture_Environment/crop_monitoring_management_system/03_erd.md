# Conceptual ERD — Crop Monitoring & Management System

## Mermaid Code

```mermaid
erDiagram
    CROP_PARCEL {
        int parcel_id PK
        string parcel_name
        string geo_boundary_polygon
        string soil_type
        decimal area_hectares
        string status
        datetime created_at
    }

    CROP_VARIETY {
        int variety_id PK
        string crop_name
        string variety_name
        decimal optimal_ph_min
        decimal optimal_ph_max
        int maturity_days
    }

    CROP_CYCLE {
        int cycle_id PK
        int parcel_id FK
        int variety_id FK
        date planting_date
        date target_harvest_date
        string status
    }

    SATELLITE_SURVEY {
        int survey_id PK
        int parcel_id FK
        date capture_date
        decimal ndvi_score
        decimal ndre_score
        string image_url
    }

    DRONE_FLIGHT {
        int flight_id PK
        int parcel_id FK
        date flight_date
        decimal area_covered_sqm
        decimal thermal_reading
        string image_path
    }

    SOIL_TELEMETRY {
        int telemetry_id PK
        int parcel_id FK
        decimal nitrogen_level
        decimal phosphorus_level
        decimal potassium_level
        decimal moisture_percentage
        datetime recorded_at
    }

    DISEASE_DIAGNOSIS {
        int diagnosis_id PK
        int cycle_id FK
        string disease_name
        decimal confidence_score
        string severity_level
        datetime diagnosed_at
    }

    ADVISORY_RECOMMENDATION {
        int advisory_id PK
        int cycle_id FK
        string advisory_type
        string dosage_instructions
        datetime created_at
    }

    TREATMENT_LOG {
        int treatment_id PK
        int advisory_id FK
        int applied_by_user_id
        date application_date
        string chemical_used
        string status
    }

    YIELD_PREDICTION {
        int prediction_id PK
        int cycle_id FK
        decimal predicted_yield_tons
        decimal confidence_margin
        date prediction_date
    }

    CROP_PARCEL ||--o{ CROP_CYCLE : "hosts"
    CROP_PARCEL ||--o{ SATELLITE_SURVEY : "scanned by"
    CROP_PARCEL ||--o{ DRONE_FLIGHT : "surveyed by"
    CROP_PARCEL ||--o{ SOIL_TELEMETRY : "monitored by"
    CROP_VARIETY ||--o{ CROP_CYCLE : "defines"
    CROP_CYCLE ||--o{ DISEASE_DIAGNOSIS : "suffers"
    CROP_CYCLE ||--o{ ADVISORY_RECOMMENDATION : "receives"
    CROP_CYCLE ||--o{ YIELD_PREDICTION : "projects"
    ADVISORY_RECOMMENDATION ||--o{ TREATMENT_LOG : "triggers"
```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | CROP_PARCEL | Lô Đất Canh Tác | Physical land parcel defined by GeoJSON boundary coordinates and soil baseline properties | parcel_id, parcel_name, geo_boundary_polygon, area_hectares, status | Has many CROP_CYCLE, SATELLITE_SURVEY, DRONE_FLIGHT, SOIL_TELEMETRY |
| 2 | CROP_VARIETY | Giống Cây Trồng | Taxonomic and agronomic profile detailing optimal growth ranges and maturity timelines | variety_id, crop_name, variety_name, optimal_ph_min, maturity_days | Has many CROP_CYCLE |
| 3 | CROP_CYCLE | Vụ Mùa Cây Trồng | Active cultivation lifecycle instance deployed on a specific crop parcel | cycle_id, parcel_id, variety_id, planting_date, status | Belongs to CROP_PARCEL, CROP_VARIETY; has DISEASE_DIAGNOSIS, ADVISORY_RECOMMENDATION, YIELD_PREDICTION |
| 4 | SATELLITE_SURVEY | Dữ Liệu Vệ Tinh | Multispectral satellite observation record containing calculated NDVI and NDRE health indices | survey_id, parcel_id, capture_date, ndvi_score, image_url | Belongs to CROP_PARCEL |
| 5 | DRONE_FLIGHT | Khảo Sát Drone | High-resolution aerial survey dataset captured by unmanned drones for canopy elevation and stress mapping | flight_id, parcel_id, flight_date, area_covered_sqm, thermal_reading | Belongs to CROP_PARCEL |
| 6 | SOIL_TELEMETRY | Dữ Liệu Cảm Biến Đất | Time-series ground telemetry recording soil NPK nutrient levels, moisture, and temperature | telemetry_id, parcel_id, nitrogen_level, moisture_percentage, recorded_at | Belongs to CROP_PARCEL |
| 7 | DISEASE_DIAGNOSIS | Chẩn Đoán Sâu Bệnh | AI-assisted or agronomist-verified plant disease diagnosis and severity score record | diagnosis_id, cycle_id, disease_name, confidence_score, severity_level | Belongs to CROP_CYCLE |
| 8 | ADVISORY_RECOMMENDATION | Khuyến Cáo Kỹ Thuật | Prescriptive agronomic recommendation specifying required irrigation, fertilizer, or pesticide treatment | advisory_id, cycle_id, advisory_type, dosage_instructions, created_at | Belongs to CROP_CYCLE; has many TREATMENT_LOG |
| 9 | TREATMENT_LOG | Nhật Ký Xử Lý Hóa Chất | Execution record of field chemical applications, operator details, and safety compliance status | treatment_id, advisory_id, applied_by_user_id, application_date, chemical_used | Belongs to ADVISORY_RECOMMENDATION |
| 10 | YIELD_PREDICTION | Dự Báo Sản Lượng | Statistical yield forecast specifying projected crop tonnage and optimal harvest date windows | prediction_id, cycle_id, predicted_yield_tons, confidence_margin, prediction_date | Belongs to CROP_CYCLE |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | CROP_PARCEL | one-to-many | CROP_CYCLE | hosts | Một lô đất canh tác có thể diễn ra nhiều vụ mùa cây trồng theo các năm. |
| 2 | CROP_PARCEL | one-to-many | SATELLITE_SURVEY | scanned by | Một lô đất được quét và lưu trữ nhiều bản ghi ảnh vệ tinh qua các đợt bay. |
| 3 | CROP_PARCEL | one-to-many | DRONE_FLIGHT | surveyed by | Một lô đất được khảo sát bởi nhiều chuyến bay drone thu thập dữ liệu độ phân giải cao. |
| 4 | CROP_PARCEL | one-to-many | SOIL_TELEMETRY | monitored by | Một lô đất liên tục truyền về nhiều bản ghi cảm biến đo độ ẩm và dinh dưỡng NPK. |
| 5 | CROP_VARIETY | one-to-many | CROP_CYCLE | defines | Một giống cây trồng chuẩn có thể áp dụng cho nhiều vụ mùa canh tác khác nhau. |
| 6 | CROP_CYCLE | one-to-many | DISEASE_DIAGNOSIS | suffers | Một vụ mùa cây trồng có thể phát sinh các bản ghi chẩn đoán sâu bệnh hại. |
| 7 | CROP_CYCLE | one-to-many | ADVISORY_RECOMMENDATION | receives | Một vụ mùa nhận được nhiều khuyến cáo tưới tiêu và bón phân từ kỹ sư nông nghiệp. |
| 8 | CROP_CYCLE | one-to-many | YIELD_PREDICTION | projects | Một vụ mùa được chạy các mô hình thuật toán dự báo sản lượng theo từng giai đoạn. |
| 9 | ADVISORY_RECOMMENDATION | one-to-many | TREATMENT_LOG | triggers | Một khuyến cáo kỹ thuật kích hoạt các đợt phun thuốc/bón phân thực tế trên đồng ruộng. |
