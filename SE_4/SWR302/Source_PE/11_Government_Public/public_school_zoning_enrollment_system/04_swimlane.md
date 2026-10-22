# Swimlane Diagram — Public School Zoning & Enrollment System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Parent / Applicant"]
        A1["Log in via VNeID Account"]
        A2["Select Primary School Choice"]
        A3["Confirm Online Admission Acceptance"]
    end

    subgraph Lane2["Enrollment Platform Core"]
        S1["Verify Household Residence Coordinates GIS"]
        S2["Calculate Distance to District Schools"]
        S3["Run Automatic Placement Placement Engine"]
        S4{"Capacity & Priority Validated?"}
        S5["Publish Admitted Student Roster"]
        S6["Issue Student National ID Code"]
    end

    subgraph Lane3["District Education Bureau"]
        O1["Draw School Zoning Polygons GIS"]
        O2["Approve District Placement Results"]
    end

    subgraph Lane4["Public School Officer"]
        E1["Verify Original Paper Documents"]
        E2["Assign Student to Class 1A / 6A"]
    end

    Finish(["End"])

    Start --> O1 --> A1 --> S1 --> S2 --> A2 --> S3 --> O2 --> S4
    S4 -->|"No / Overcapacity"| O2
    S4 -->|"Yes / Admitted"| S5 --> A3 --> E1 --> E2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Parent / Applicant | Đăng nhập tài khoản VNeID, chọn nguyện vọng đăng ký tuyển sinh đúng tuyến và bấm xác nhận nhập học trực tuyến. |
| 2 | Enrollment Platform Core | Tự động đối soát tọa độ GIS nơi ở, tính khoảng cách đường bộ tới trường, phân bổ học sinh tự động và cấp mã định danh. |
| 3 | District Education Bureau | Vẽ bản đồ ranh giới phân tuyến địa lý cho từng trường công lập và ký phê duyệt danh sách trúng tuyển toàn quận. |
| 4 | Public School Officer | Kiểm tra đối chiếu hồ sơ bản cứng khi phụ huynh mang học bạ đến trường và xếp học sinh vào các lớp học chính thức. |
