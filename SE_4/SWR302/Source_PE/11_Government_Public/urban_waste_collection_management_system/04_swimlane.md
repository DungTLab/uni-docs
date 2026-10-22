# Swimlane Diagram — Urban Waste Collection Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Smart Waste Bin IoT Sensor"]
        A1["Measure Fill Level via Ultrasonic Sensor"]
        A2["Stream Telemetry Payload via LoRaWAN"]
    end

    subgraph Lane2["Waste Management Platform Core"]
        S1["Parse Fill Percentage & Temperature"]
        S2{"Fill Level > 80%?"}
        S3["Flag Bin Red Status for Collection"]
        S4["Run AI VRP Route Optimization Algorithm"]
        S5["Publish Dynamic Navigation Route to Driver"]
        S6["Record Net Tonnage from Weighbridge"]
    end

    subgraph Lane3["Waste Operations Manager"]
        O1["Review Citywide Bin Fill GIS Heatmap"]
        O2["Approve Automated Dispatch Routes"]
        O3["Monitor Fleet Collection SLA Performance"]
    end

    subgraph Lane4["Garbage Truck Driver"]
        B1["Receive Mobile Route Navigation"]
        B2["Drive to Target Bins & Empty Bin"]
        B3["Confirm Bin Pickup on Tablet"]
        B4["Drive Truck to Landfill Weighbridge"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2
    S2 -->|"No"| A1
    S2 -->|"Yes"| S3 --> O1 --> S4 --> O2 --> S5 --> B1 --> B2 --> B3 --> B4 --> S6 --> O3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Smart Waste Bin IoT Sensor | Đo khoảng cách mức rác bằng cảm biến siêu âm, truyền gói tin dữ liệu qua mạng LoRaWAN/NB-IoT về trung tâm. |
| 2 | Waste Management Platform Core | Bóc tách dữ liệu % rác, phát hiện thùng rác đã đầy, chạy thuật toán AI tối ưu hóa tuyến đường gom rác và ghi nhận khối lượng rác tại trạm cân. |
| 3 | Waste Operations Manager | Rà soát bản đồ nhiệt thùng rác trên GIS, phê duyệt lộ trình điều động đội xe rác và giám sát hiệu năng SLA thu gom. |
| 4 | Garbage Truck Driver | Nhận lộ trình dẫn đường di chuyển trên máy tính bảng cabin, thực hiện trút rác các thùng đầy, xác nhận hoàn thành và đổ rác tại bãi cân. |
