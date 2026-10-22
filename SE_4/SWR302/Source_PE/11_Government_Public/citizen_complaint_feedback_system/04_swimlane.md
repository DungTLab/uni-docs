# Swimlane Diagram — Citizen Complaint & Feedback System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen Complainant"]
        A1["Submit Formal Complaint Dossier"]
        A2["Participate Field Verification Dialogue"]
        A3["Receive Official Settlement Decision"]
        A4["Submit Satisfaction Rating 1-5 Stars"]
    end

    subgraph Lane2["Citizen Complaint System Core"]
        S1["Verify Complainant Identity via VNeID"]
        S2["Classify Complaint Domain & SLA Timer"]
        S3["Generate Field Inspection Task"]
        S4["Issue Signed Settlement Decision PDF & QR"]
        S5["Publish Resolution Summary on Portal"]
    end

    subgraph Lane3["Public Reception Officer"]
        O1["Review Petition Validity"]
        O2["Assign Handling Officer Specialist"]
    end

    subgraph Lane4["Government Inspector"]
        E1["Conduct Field Verification Inspection"]
        E2["Draft Settlement Decision & Ruling"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> S2 --> O2 --> S3 --> E1 --> A2 --> E2 --> S4 --> S5 --> A3 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen Complainant | Nộp đơn khiếu nại/phản ánh trực tuyến, tham gia đối thoại thực địa, nhận quyết định giải quyết và đánh giá mức độ hài lòng. |
| 2 | Citizen Complaint System Core | Tự động xác thực VNeID, phân loại danh mục và đếm ngược SLA, tạo gói nhiệm vụ xác minh, phát hành quyết định điện tử và công khai. |
| 3 | Public Reception Officer | Rà soát tính hợp lệ của đơn thư ban đầu và phân công cán bộ thụ lý chuyên trách. |
| 4 | Government Inspector | Khảo sát thực địa, tổ chức đối thoại trực tiếp với công dân, biên soạn Báo cáo xác minh và dự thảo Quyết định giải quyết. |
