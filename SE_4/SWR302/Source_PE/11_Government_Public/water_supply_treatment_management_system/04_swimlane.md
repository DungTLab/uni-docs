# Swimlane Diagram — Water Supply & Treatment Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Smart Water Meter / AMR"]
        A1["Measure Hourly Water Flow Volume"]
        A2["Stream Meter Reading via NB-IoT Network"]
    end

    subgraph Lane2["Water Management Platform Core"]
        S1["Parse Meter Reading & Verify Validity"]
        S2["Check Continuous Flow Anomaly"]
        S3{"Night Flow Anomaly Detected?"}
        S4["Flag AI Pipe Leak Location Warning"]
        S5["Calculate Progressive Tier Water Bill"]
        S6["Issue Electronic Water Bill Invoice"]
    end

    subgraph Lane3["Water Utility Officer / Operator"]
        O1["Monitor SCADA Pipe Pressure Dashboard"]
        O2["Dispatch Maintenance Crew to Leak Spot"]
        O3["Approve Auto-debit Billing Batch"]
    end

    subgraph Lane4["Maintenance Repair Team"]
        B1["Receive Mobile Leak Repair Task"]
        B2["Locate Physical Pipe Burst via Acoustic Reader"]
        B3["Repair Pipe & Restore Water Flow"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> S3
    S3 -->|"Yes"| S4 --> O1 --> O2 --> B1 --> B2 --> B3 --> Finish
    S3 -->|"No"| S5 --> O3 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Smart Water Meter / AMR | Đo thể tích nước tiêu thụ theo giờ, đóng gói và gửi dữ liệu chỉ số nước qua mạng không dây NB-IoT/LoRaWAN. |
| 2 | Water Management Platform Core | Giải mã chỉ số nước, kiểm tra dấu hiệu thất thoát bất thường, khoanh vùng vị trí bể ống AI và tự động tính hóa đơn tiền nước. |
| 3 | Water Utility Officer / Operator | Giám sát bảng điều khiển áp lực SCADA, điều động đội sửa chữa ống nước bể và phê duyệt phát hành hóa đơn tiền nước. |
| 4 | Maintenance Repair Team | Nhận lệnh sửa chữa trên ứng dụng di động, dò tìm chính xác vị trí vỡ ống bằng thiết bị âm thanh và khắc phục sự cố rò rỉ. |
