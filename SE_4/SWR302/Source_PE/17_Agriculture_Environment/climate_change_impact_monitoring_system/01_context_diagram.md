# Context Diagram — Climate Change Impact Monitoring System

## Mermaid Code

```mermaid
flowchart LR
    PolicyOfficer["Climate Adaptation Policy Officer"]
    ClimateScientist["Climate Scientist / Researcher"]
    DisasterManager["Disaster Risk Reduction Manager"]
    CMIP6Feed["CMIP6 Climate Projections (IPCC / NASA)"]
    SeaLevelRadar["Sea Level Rise & Coastal Radar Array"]
    WeatherSensors["Extreme Weather & Drought Telemetry Network"]
    UNFCCCPanel["UNFCCC & IPCC Climate Regulatory Panel"]
    AgriGISPortal["Agricultural Yield & Food Security Portal"]
    InfrastructureDB["Urban & Infrastructure Vulnerability DB"]
    AlertGateway["Automated Climate Hazard & Early Warning Gateway"]

    System(("Climate Change Impact Monitoring System"))

    PolicyOfficer -->|"submits National Adaptation Plans (NAP), carbon budgets, and policy targets"| System
    System -->|"returns Climate Risk Vulnerability Index (CRVI) scorecards and adaptation ROI"| PolicyOfficer
    ClimateScientist -->|"submits regional downscaling models, bias correction parameters, and thresholds"| System
    System -->|"returns multi-ensemble temperature projections, drought indices, and biome shift maps"| ClimateScientist
    DisasterManager -->|"submits disaster hazard thresholds and emergency response plans"| System
    System -->|"returns coastal inundation maps, heatwave severity indices, and loss-and-damage logs"| DisasterManager
    CMIP6Feed -->|"transmits CMIP6 climate model ensembles (SSP1-1.9 to SSP5-8.5) and GCM rasters"| System
    System -->|"sends regional spatial bounding box and downscaling parameters"| CMIP6Feed
    SeaLevelRadar -->|"transmits real-time mean sea level (m), tidal gauge surge, and coastal erosion rates"| System
    System -->|"sends sensor polling frequencies and tide-gauge zero-calibration queries"| SeaLevelRadar
    WeatherSensors -->|"transmits surface temperature, extreme precipitation (mm), SPEI drought index, and wind"| System
    System -->|"sends telemetry aggregation rules and anomaly thresholds"| WeatherSensors
    System -->|"transmits statutory Biennial Transparency Reports (BTR) and Loss & Damage accounting"| UNFCCCPanel
    UNFCCCPanel -->|"returns Paris Agreement 1.5C/2C targets, COP mandates, and reporting guidelines"| System
    System -->|"transmits climate-adjusted crop yield predictions and drought vulnerability maps"| AgriGISPortal
    AgriGISPortal -->|"returns regional crop acreage, historical harvest yields, and irrigation data"| System
    System -->|"transmits coastal flooding inundation rasters and heat stress infrastructure risks"| InfrastructureDB
    InfrastructureDB -->|"returns urban building inventory, sea wall asset data, and population grids"| System
    System -->|"dispatches urgent flash flood alerts, extreme heatwave alarms, and sea surge warnings"| AlertGateway
    AlertGateway -->|"delivers push notification, SMS, and public broadcasting receipts"| System
```

## Actor & Interaction Table | Bang Actor & Tuong tac

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Climate Adaptation Policy Officer | Primary | National Adaptation Plans (NAP), resilience intervention budgets, policy target years | Climate Risk Vulnerability Index (CRVI) ratings, adaptation cost-benefit models, loss-and-damage totals | Policy maker designing national and regional climate adaptation strategies |
| 2 | Climate Scientist / Researcher | Primary | Downscaling algorithms, bias correction grids, biome displacement thresholds, GCM parameters | Ensemble climate projections, multi-decadal temperature/precipitation trend anomalies, biome maps | Domain scientist modeling climate change impacts, downscaling GCM models, and studying ecology |
| 3 | Disaster Risk Reduction Manager | Primary | Disaster hazard thresholds, emergency evacuation triggers, relief asset inventories | High-resolution coastal inundation maps, heatwave severity heatmaps, flash flood risk vectors | Emergency management official coordinating disaster preparedness and climate hazard response |
| 4 | CMIP6 Climate Projections (IPCC / NASA) | Supporting System | CMIP6 Shared Socioeconomic Pathways (SSP1-1.9, SSP2-4.5, SSP5-8.5), GCM netCDF grid rasters | Regional GIS bounding boxes, spatial resolution queries ($1\text{ km}^2$ downscaled grids) | International climate data repository providing Earth System Model (ESM) global projections |
| 5 | Sea Level Rise & Coastal Radar Array | Supporting System | Real-time mean sea level ($m$), storm surge height ($m$), coastal erosion velocity ($m/year$), salinity | Radar sensor polling intervals, acoustic tide gauge zeroing signals, battery health status | Satellite radar altimetry and coastal acoustic tide gauge stations tracking sea level rise |
| 6 | Extreme Weather & Drought Telemetry Network | Supporting System | Surface air temperature ($^\circ C$), 24h precipitation ($mm$), SPEI drought index, maximum wind speed ($knots$) | Telemetry aggregation interval commands, sensor anomaly filtering thresholds | Meteorological station network measuring extreme weather parameters and drought indices |
| 7 | UNFCCC & IPCC Climate Regulatory Panel | Regulatory System | Paris Agreement global temperature targets ($1.5^\circ C / 2.0^\circ C$), BTR disclosure guidelines | Statutory Biennial Transparency Reports (BTR), Loss and Damage Fund claims, adaptation progress | International climate body (UN Framework Convention on Climate Change / IPCC) overseeing compliance |
| 8 | Agricultural Yield & Food Security Portal | Supporting System | Regional crop acreage GIS layers, historical crop harvest yields ($\text{t/ha}$), irrigation infrastructure | Climate-adjusted crop yield predictions ($2030-2050$), heat-stress yield loss models, drought maps | Agricultural GIS system supplying crop baseline data and consuming climate-yield impact models |
| 9 | Urban & Infrastructure Vulnerability DB | Supporting System | Urban building GIS polygons, sea wall structural assets, population density grids, road networks | Coastal flooding inundation rasters, urban heat island maps, infrastructure vulnerability scores | Municipal GIS database managing urban infrastructure assets and population vulnerability |
| 10 | Automated Climate Hazard Alert Gateway | Supporting System | SMS delivery receipts, emergency broadcast status logs | High-priority sea surge warnings, extreme heatwave alarms, flash flood inundation notifications | Public warning gateway broadcasting emergency alerts to government agencies and the public |

## System Boundary Description | Mo ta Pham vi He thong

The **Climate Change Impact Monitoring System** is an enterprise geospatial environmental analytics platform designed to monitor long-term climate change impacts, ingest CMIP6 global climate model projections (SSP scenarios), assess sea level rise and coastal inundation, track extreme weather anomalies, evaluate agricultural crop yield sensitivity, calculate the Climate Risk Vulnerability Index (CRVI), and report to the UNFCCC.

The **system boundary** encompasses climate impact zone GIS registration, CMIP6 global model spatial downscaling ($1\text{ km}$ resolution), sea level rise projection modeling, SPEI drought index calculation, agricultural crop yield climate sensitivity simulation, coastal inundation GIS mapping, Loss & Damage Fund accounting, and statutory UNFCCC Biennial Transparency Report (BTR) generation.

**External to the system boundary** are physical planetary orbital dynamics, physical greenhouse gas atmospheric absorption physics, physical ocean thermal expansion, legal international treaty negotiations, and physical storm surge wave mechanics. System interfaces operate via HTTPS REST APIs, OGC WMS/WCS spatial rasters, netCDF data formats, and encrypted UN compliance reporting portals.
