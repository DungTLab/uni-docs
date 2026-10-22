# Swimlane Diagram — Fisheries Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Fishing Vessel Captain"]
        A1["Transmit VMS Satellite GPS Pings"]
        A2["Log Catch Volume in e-Logbook App"]
        A3["Submit e-CDT Catch Certificate Request"]
    end

    subgraph Lane2["Fisheries Management Platform"]
        S1["Verify VMS Continuous Signal Transmission"]
        S2["Cross-Check EEZ Boundary Crossing"]
        S3{"Boundary Breach or VMS Disconnection?"}
        S4["Verify Landed Weight vs e-Logbook"]
        S5["Issue Approved e-CDT Certificate & QR Code"]
    end

    subgraph Lane3["Fisheries Patrol Officer (Kiểm ngư)"]
        O1["Receive Red Alarm EEZ Breach Alert"]
        O2["Dispatch Patrol Boat to Intercept Vessel"]
    end

    subgraph Lane4["Fishing Port Authority"]
        B1["Inspect Landed Seafood Weight at Dock"]
        B2["Confirm Legitimate Port Logbook Receipt"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2 --> S3
    S3 -->|"Yes / IUU Breach"| O1 --> O2 --> Finish
    S3 -->|"No / Clean"| A2 --> B1 --> B2 --> S4 --> A3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Fishing Vessel Captain | Bật thiết bị VMS định vị vệ tinh liên tục, ghi chép nhật ký khai thác e-Logbook và xin cấp chứng nhận e-CDT. |
| 2 | Fisheries Management Platform | Kiểm tra kết nối VMS thời gian thực, đối soát đường phân định vùng biển, đối chiếu sản lượng bốc dỡ và cấp chứng nhận e-CDT. |
| 3 | Fisheries Patrol Officer (Kiểm ngư) | Nhận báo động Đỏ khi tàu cá xâm phạm vùng biển nước ngoài hoặc ngắt VMS, điều động tàu kiểm ngư truy bắt. |
| 4 | Fishing Port Authority | Giám sát bốc dỡ hải sản thực tế tại cầu cảng cá và xác nhận tính hợp lệ của Nhật ký khai thác e-Logbook. |
