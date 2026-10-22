# Swimlane Diagram — National Food Security Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Rice Farmer / Enterprise"]
        A1["Plant Rice & Submit Crop Area Data"]
        A2["Submit Rice Export License Quota Request"]
        A3["Deliver Grain to State Reserve Silos"]
    end

    subgraph Lane2["Food Security System Platform"]
        S1["Ingest Satellite Imagery & Estimate Crop Yield"]
        S2["Run AI Drought & Food Shortage Risk Model"]
        S3{"National Food Reserve Above Safety Cap?"}
        S4["Issue Electronic Rice Export Quota License"]
        S5["Trigger Auto Lock on Export Licensing API"]
    end

    subgraph Lane3["Agricultural Inspector"]
        O1["Inspect Crop Health & Soil Moisture"]
        O2["Audit Grain Pesticide Residue Lab Tests"]
    end

    subgraph Lane4["Ministry MARD Director"]
        B1["Issue Emergency Grain Release Orders"]
        B2["Authorize National Reserve Balance Rebalancing"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> S2 --> S3
    S3 -->|"Yes / Safe"| A2 --> O2 --> S4 --> Finish
    S3 -->|"No / Risk High"| S5 --> B1 --> A3 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Rice Farmer / Enterprise | Gieo trồng lúa, nộp số liệu diện tích, nộp đơn xin hạn ngạch xuất khẩu gạo và giao lúa vào kho dự trữ. |
| 2 | Food Security System Platform | Thu thập ảnh vệ tinh dự báo sản lượng lúa, chạy AI dự báo thiếu hụt lương thực và tự động khóa cấp quota khi nguy cơ cao. |
| 3 | Agricultural Inspector | Kiểm tra thực địa tình hình dịch bệnh lúa và kiểm nghiệm dư lượng thuốc BVTV đối với các lô hàng lúa gạo. |
| 4 | Ministry MARD Director | Ban hành Quyết định xuất kho gạo dự trữ cứu trợ khẩn cấp và phê duyệt điều chỉnh hạn ngạch xuất khẩu gạo. |
