# Swimlane Diagram — Public Safety & Emergency Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Reporting Citizen"]
        A1["Dial 112 Emergency or Press SOS App"]
        A2["Provide Incident Details & Location"]
        A3["Receive Safety Instructions & ETA"]
    end

    subgraph Lane2["Emergency CAD System"]
        S1["Extract Phone GPS & Cell Coordinates"]
        S2["Triage Triage Priority Code Red/Yellow"]
        S3["Locate Nearest Available Units on GIS"]
        S4["Calculate Optimal Route & ETA"]
        S5["Trigger Traffic Signal Green Waves"]
        S6["Send Cell Broadcast Mass SMS"]
    end

    subgraph Lane3["Dispatch Operator"]
        O1["Answer 112 Emergency Call"]
        O2["Categorize Incident Type & Priority"]
        O3["Confirm & Fire Dispatch Order"]
        O4["Monitor Live CCTV Streams"]
    end

    subgraph Lane4["First Responder Unit (Police/Fire/Med)"]
        B1["Receive Dispatch Order & Route"]
        B2["Confirm Vehicle Departure"]
        B3["Navigate to Scene via GPS"]
        B4["Arrive & Execute Rescue Action"]
        B5["Submit Casualty & Status Report"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> A2 --> O2 --> S2 --> S3 --> O3 --> B1 --> B2 --> S4 --> S5 --> B3 --> B4 --> O4 --> B5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Reporting Citizen | Gọi 112 hoặc bấm SOS trên ứng dụng, cung cấp địa điểm và mô tả sự cố khẩn cấp, nhận hướng dẫn an toàn. |
| 2 | Emergency CAD System | Tự động định vị GPS cuộc gọi, phân loại ưu tiên, tìm đơn vị gần nhất trên GIS, dẫn đường xe, ưu tiên đèn giao thông và phát tin khẩn cấp. |
| 3 | Dispatch Operator | Nghe cuộc gọi khẩn cấp, xác định loại sự cố và mức độ nguy hiểm, phê duyệt lệnh xuất xe và theo dõi camera khu vực. |
| 4 | First Responder Unit | Nhận lệnh điều động và lộ trình dẫn đường, xuất xe di chuyển tới hiện trường, thực hiện cứu hộ và cập nhật báo cáo thương vong. |
