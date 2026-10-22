# Swimlane Diagram — Forestry Management Information System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Satellite Remote Sensing System"]
        A1["Stream Sentinel-2 Satellite Multi-Spectral Imagery"]
        A2["Detect Canopy Thermal Spot Abrupt Loss"]
    end

    subgraph Lane2["Forestry Platform Core"]
        S1["Calculate NDVI Index & Overlay Forest GIS"]
        S2["Check Legal Logging Quota Registry"]
        S3{"Is Cleared Area Authorized?"}
        S4["Flag Red Alarm Deforestation Hotspot"]
        S5["Issue FLEGT Timber Origin QR Code"]
    end

    subgraph Lane3["Forest Ranger Officer"]
        O1["Receive GPS Alert Target on Mobile App"]
        O2["Navigate to Plot & Inspect Ground Truth"]
        O3["Issue Seizure Record for Illegal Timber"]
    end

    subgraph Lane4["Forest Enterprise / Owner"]
        B1["Submit Sustainable Harvesting Plan"]
        B2["Attach QR Tags on Cut Logs for Supply Chain"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> S2 --> S3
    S3 -->|"No / Unauthorized"| S4 --> O1 --> O2 --> O3 --> Finish
    S3 -->|"Yes / Authorized"| B1 --> S5 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Satellite Remote Sensing System | Truyền luồng dữ liệu ảnh vệ tinh Sentinel-2 đa phổ và tự động phát hiện sự mất mát đột ngột của tán rừng. |
| 2 | Forestry Platform Core | Tính toán chỉ số thực vật NDVI, đối soát CSDL cấp phép khai thác, cảnh báo phá rừng Đỏ và cấp mã QR truy xuất gỗ. |
| 3 | Forest Ranger Officer | Nhận vị trí GPS điểm phá rừng trên ứng dụng di động, đi xác minh thực địa và lập biên bản tịch thu gỗ lậu. |
| 4 | Forest Enterprise / Owner | Nộp Kế hoạch khai thác rừng trồng hợp pháp và dán nhãn mã QR code lên từng lóng gỗ trước khi xuất bán. |
