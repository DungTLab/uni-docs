# Swimlane Diagram — Military Personnel Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Conscript / Military Officer"]
        A1["Submit Conscription Demographics VNeID"]
        A2["Undergo Annual Medical Fitness Exam"]
        A3["Receive Rank Promotion Decree & Salary"]
    end

    subgraph Lane2["Military Personnel Platform Core"]
        S1["Generate Unique Military Serial Number"]
        S2["Calculate Medical Physical Grade 1-6"]
        S3["Check Promotion Eligibility & Time Served"]
        S4["Auto-Calculate Rank Base Salary & Hazard"]
        S5["Sync Demobilization Data to Reserve Pool"]
    end

    subgraph Lane3["Military Medical Board"]
        O1["Conduct Specialized Health Examinations"]
        O2["Approve Fitness Classification Certificate"]
    end

    subgraph Lane4["Military HR Commander"]
        E1["Approve Conscript Unit Placement"]
        E2["Issue PKI Rank Promotion Decree"]
        E3["Approve Demobilization Orders"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> E1 --> A2 --> O1 --> O2 --> S2 --> S3 --> E2 --> S4 --> A3 --> E3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Conscript / Military Officer | Đăng ký thông tin nghĩa vụ quân sự, kiểm tra sức khỏe quân y hàng năm, nhận quyết định thăng quân hàm và lương. |
| 2 | Military Personnel Platform Core | Tự động sinh Mã số quân nhân duy nhất, phân loại thể lực quân y, kiểm tra tiêu chuẩn thăng cấp, tự động tính lương và đồng bộ dự bị động viên. |
| 3 | Military Medical Board | Khám kiểm tra các chuyên khoa y tế, chẩn đoán sức khỏe và ký sổ phê duyệt Giấy chứng nhận phân loại thể lực. |
| 4 | Military HR Commander | Duyệt phân công quân số về các đơn vị, ký số Quyết định thăng cấp bậc hàm và phê duyệt Quyết định xuất ngũ. |
