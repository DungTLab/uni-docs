# Swimlane Diagram — Police Crime Reporting System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Reporting Citizen"]
        A1["Submit Crime Report & Evidence"]
        A2["Receive Case Tracking Code"]
        A3["Provide Additional Statement"]
    end

    subgraph Lane2["Police Crime System Core"]
        S1["Anonymize & Log Incident CAD"]
        S2["Verify Suspect Record in VNeID"]
        S3["Generate Formal Arrest Warrant File"]
        S4["Encapsulate e-Case File PDF/A"]
        S5["Sync Transfer to Procuracy System"]
    end

    subgraph Lane3["Police Detective (Investigator)"]
        O1["Assign Lead Detective to Case"]
        O2["Log Physical Evidence & Biometrics"]
        O3["Draft Arrest Warrant & Indictment"]
        O4["Finalize Investigation Conclusion"]
    end

    subgraph Lane4["Public Prosecutor (Procuracy)"]
        B1["Review Arrest Warrant Request"]
        B2{"Procuracy Approved Warrant?"}
        B3["Sign Digital Warrant Approval"]
        B4["Accept Transferred Case File"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> O1 --> O2 --> O3 --> S3 --> B1 --> B2
    B2 -->|"No"| O3
    B2 -->|"Yes"| B3 --> O4 --> S4 --> S5 --> B4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Reporting Citizen | Trình báo vụ án tố giác tội phạm, tải lên bằng chứng hình ảnh/video, nhận mã theo dõi và phối hợp cung cấp lời khai. |
| 2 | Police Crime System Core | Khởi tạo vụ án trên CAD, tự động tra cứu nhân thân nghi phạm trên VNeID, đóng gói hồ sơ vụ án mã hóa và truyền sang Viện Kiểm sát. |
| 3 | Police Detective (Investigator) | Phân công điều tra viên chính, thu thập và niêm phong vật chứng hiện trường, lập lệnh bắt bị can và hoàn thành kết luận điều tra. |
| 4 | Public Prosecutor (Procuracy) | Thẩm định tính pháp lý của hồ sơ xin lệnh bắt tạm giam, ký số phê duyệt lệnh bắt và tiếp nhận hồ sơ vụ án đề nghị truy tố. |
