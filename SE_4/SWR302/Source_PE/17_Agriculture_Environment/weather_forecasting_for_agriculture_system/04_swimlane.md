# Swimlane Diagram — Weather Forecasting for Agriculture System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Daily Forecasting Cycle"])

    subgraph FarmerLane["Farmer / Agri-Producer"]
        A1["Register Farm Coordinates & Crop Profile"]
        A2["View Hyper-Local Forecast Dashboard"]
        A3["Review Irrigation & Spraying Advice"]
        A4["Authorize Smart Irrigation Execution"]
        A5["Receive Emergency Frost Alert"]
    end

    subgraph SystemLane["Weather Forecasting for Agriculture System"]
        S1["Downscale Macro Model via AI & Station Data"]
        S2["Calculate ET0 & Soil Moisture Depletion"]
        S3{"Extreme Weather Detected?"}
        S4["Compute Optimal Spraying Windows"]
        S5["Generate Automated Irrigation Schedule"]
    end

    subgraph MetIngestionLane["Hydro-Met Data Ingestion Engine"]
        M1["Decode Global GRIB2 GFS/ECMWF Feeds"]
        M2["Collect IoT Station Telemetry"]
        M3["Integrate Satellite Soil Moisture"]
    end

    subgraph AnalystLane["Agrometeorologist / Analyst"]
        G1["Monitor Downscaling Model Bias"]
        G2["Calibrate Kriging Parameters"]
        G3["Publish Regional Climate Bulletin"]
    end

    subgraph GatewayLane["Smart Irrigation & Alert Gateway"]
        W1["Dispatch Emergency Frost/Rain SMS"]
        W2["Execute Smart Irrigation Valves"]
    end

    Finish(["End Cycle"])

    Start --> A1 --> M1 --> M2 --> M3 --> S1 --> G1 --> G2 --> S1
    S1 --> S2 --> S3
    S3 -->|"Yes"| W1 --> A5 --> Finish
    S3 -->|"No"| S4 --> S5 --> A2 --> A3 --> A4 --> W2 --> Finish
    S5 --> G3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Farmer / Agri-Producer | Registers farm GPS coordinates and crop profiles, monitors 7-day microclimate forecasts, receives spraying and irrigation advice, authorizes smart pump execution, and receives urgent frost/storm alerts |
| 2 | Weather Forecasting for Agriculture System | Downscales global weather models using station telemetry, computes reference evapotranspiration ($\text{ET}_0$), evaluates severe weather risks, generates crop spraying windows, and builds automated irrigation schedules |
| 3 | Hydro-Met Data Ingestion Engine | Decodes raw GRIB2/NetCDF weather feeds from NOAA/ECMWF, streams real-time microclimate sensor telemetry from field weather stations, and ingests satellite land surface temperature data |
| 4 | Agrometeorologist / Analyst | Monitors forecasting model bias, calibrates downscaling kriging parameters against physical weather stations, and publishes weekly regional agricultural climate bulletins |
| 5 | Smart Irrigation & Alert Gateway | Dispatches high-priority SMS and push notification alerts during extreme frost/heat events, and transmits automated run-time commands to field irrigation valves |
