# Swimlane Diagram — Disaster Relief Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Affected Citizen (Victim)"]
        A1["Press Emergency SOS Button App"]
        A2["Send Auto GPS Location Coordinates"]
        A3["Board Rescue Boat & Arrive Shelter"]
    end

    subgraph Lane2["Disaster Relief Platform Core"]
        S1["Parse GPS Payload & Mark Red SOS"]
        S2["Calculate Safe Boat Route via Flood Map"]
        S3["Update SOS Status to Rescued"]
        S4["Deduct Warehouse Inventory Supplies"]
    end

    subgraph Lane3["Rescue Team Lead"]
        O1["Receive Priority SOS Target Mobile"]
        O2["Navigate Rescue Boat to Victim GPS"]
        O3["Extract Victim & Transport to Shelter"]
    end

    subgraph Lane4["Disaster Relief Officer"]
        B1["Assign Rescue Boat Crew"]
        B2["Distribute Aid Supplies at Shelter"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> B1 --> O1 --> O2 --> O3 --> S3 --> A3 --> B2 --> S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Affected Citizen (Victim) | Bấm nút SOS khẩn cấp trên ứng dụng di động, gửi tọa độ GPS tự động, bước lên canô cứu hộ và di chuyển đến điểm sơ tán. |
| 2 | Disaster Relief Platform Core | Bóc tách tọa độ GPS, đánh dấu điểm SOS đỏ trên bản đồ, tính tuyến đường canô an toàn, cập nhật trạng thái đã cứu và trừ kho nhu yếu phẩm. |
| 3 | Rescue Team Lead | Nhận vị trí SOS cứu hộ ưu tiên trên thiết bị di động, điều khiển canô tiếp cận tọa độ nạn nhân, đưa nạn nhân lên xuồng an toàn. |
| 4 | Disaster Relief Officer | Phân công đội canô cứu hộ và thực hiện phát nhu yếu phẩm cứu trợ cho người dân tại điểm sơ tán. |
