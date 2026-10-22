# Swimlane Diagram — Smart City Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["IoT Device / Camera / Resident"]
        A1["Stream IoT Sensor Telemetry & Videos"]
        A2["Submit Citizen Incident Photo & GPS"]
    end

    subgraph Lane2["Smart City Platform Core Engine"]
        S1["Validate Data Stream & Run AI Vision"]
        S2{"Incident or Anomaly Detected?"}
        S3["Raise Alarm Ticket on GIS Command Map"]
        S4["Calculate Optimal Traffic Signal Timing"]
        S5["Publish Status Update to Citizen App"]
    end

    subgraph Lane3["Urban Operations Center Operator"]
        O1["Review Alarms on Command Center Screen"]
        O2["Confirm Incident Category & Priority"]
        O3["Assign Field Task to Response Unit"]
    end

    subgraph Lane4["Municipal Field Response Team"]
        B1["Receive Mobile Dispatch Orders"]
        B2["Navigate to Location via GPS"]
        B3["Repair Infrastructure at Scene"]
        B4["Upload Before/After Photos Completed"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2
    A2 --> S1
    S2 -->|"No"| S4 --> Finish
    S2 -->|"Yes"| S3 --> O1 --> O2 --> O3 --> B1 --> B2 --> B3 --> B4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | IoT Device / Camera / Resident | Truyền dữ liệu cảm biến thời gian thực, luồng video AI hoặc gửi ảnh phản ánh sự cố kèm tọa độ GPS. |
| 2 | Smart City Platform Core Engine | Chuẩn hóa luồng dữ liệu, chạy thuật toán AI nhận diện bất thường, sinh vé cảnh báo trên bản đồ GIS và tính toán chu kỳ đèn. |
| 3 | Urban Operations Center Operator | Rà soát cảnh báo trên màn hình trung tâm IOC, xác nhận phân loại sự cố và phát lệnh điều động đội ứng cứu. |
| 4 | Municipal Field Response Team | Nhận lệnh điều động trên ứng dụng di động, di chuyển tới hiện trường sửa chữa hạ tầng và tải ảnh hoàn thành. |
