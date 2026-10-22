# Swimlane Diagram — Public Transportation Route Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Transit Commuter"]
        A1["Open Transit App to Search Bus Route"]
        A2["Check Real-Time Bus ETA & Stop Location"]
        A3["Tap Electronic Fare Card at Bus Entry"]
    end

    subgraph Lane2["Public Transportation Route Platform"]
        S1["Ingest Vehicle GPS Telemetry GTFS-RT"]
        S2["Calculate Estimated Arrival Time ETA AI"]
        S3["Broadcast Live ETA to Station LED & Mobile"]
        S4{"Traffic Congestion / Flooding Detected?"}
        S5["Publish Emergency Detour Route Update"]
        S6["Validate Fare Payment & Reconcile Subsidy"]
    end

    subgraph Lane3["Transport Dispatcher"]
        O1["Monitor Headway Spacing & Dispatch Rules"]
        O2["Approve Emergency Reroute Order"]
    end

    subgraph Lane4["Bus / Metro Driver"]
        B1["Receive Dispatch Order on DDU Screen"]
        B2["Drive Vehicle via Detour Instructions"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2 --> S3 --> A2 --> S4
    S4 -->|"Yes / Congestion"| O1 --> O2 --> S5 --> B1 --> B2 --> S6
    S4 -->|"No / Smooth"| S6 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Transit Commuter | Mở ứng dụng tra cứu tuyến xe buýt, xem thời gian dự kiến xe đến trạm (ETA) và quẹt thẻ vé điện tử khi lên xe. |
| 2 | Public Transportation Route Platform | Thu thập dữ liệu GPS GTFS-RT, tính toán thời gian ETA bằng AI, hiển thị thông tin lên nhà chờ và tự động gạch nợ vé. |
| 3 | Transport Dispatcher | Giám sát giãn cách các xe buýt trên tuyến, phê duyệt lệnh phân luồng chuyển hướng khi có kẹt xe hoặc ngập lụt. |
| 4 | Bus / Metro Driver | Nhận lệnh điều độ và sơ đồ chỉ đường né kẹt xe trên màn hình hiển thị DDU để điều khiển xe an toàn. |
