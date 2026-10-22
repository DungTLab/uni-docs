# Swimlane Diagram — Immigration & Visa Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Foreign Traveler (Applicant)"]
        A1["Submit e-Visa Application & Photo"]
        A2["Pay e-Visa Processing Fee"]
        A3["Download e-Visa Approval Letter"]
        A4["Scan Passport at Airport e-Gate"]
        A5["Cross Border Gate into Country"]
    end

    subgraph Lane2["Immigration Portal & Core"]
        S1["Extract Passport OCR Data"]
        S2["Validate Payment Transaction"]
        S3["Run Automated Security & Interpol Match"]
        S4{"Security Match Found?"}
        S5["Generate Signed e-Visa Document"]
        S6["Log Border Entry Stamp & Expiry"]
    end

    subgraph Lane3["Consular Officer / Security"]
        O1["Review High-Risk Flagged Dossier"]
        O2{"Approve Visa Request?"}
        O3["Issue Visa Rejection Notice"]
        O4["Approve e-Visa Issuance"]
    end

    subgraph Lane4["Border Control Officer / e-Gate"]
        B1["Scan Passport ICAO Biometric Chip"]
        B2["Perform 1:1 Facial Recognition"]
        B3{"Biometrics & Visa Valid?"}
        B4["Open e-Gate Barrier / Stamp Passport"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> S2 --> S3 --> S4
    S4 -->|"Yes"| O1 --> O2
    O2 -->|"No"| O3 --> Finish
    O2 -->|"Yes"| O4 --> S5
    S4 -->|"No"| S5 --> A3 --> A4 --> B1 --> B2 --> B3
    B3 -->|"No"| O1
    B3 -->|"Yes"| B4 --> S6 --> A5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Foreign Traveler (Applicant) | Nộp đơn e-Visa trực tuyến, tải ảnh hộ chiếu, nộp lệ phí, nhận công văn duyệt e-Visa, quét hộ chiếu tại cửa khẩu nhập cảnh. |
| 2 | Immigration Portal & Core | Bóc tách OCR dữ liệu hộ chiếu, tự động đối soát CSDL an ninh và Interpol, khởi tạo file e-Visa điện tử và ghi nhật ký xuất nhập cảnh. |
| 3 | Consular Officer / Security | Xét duyệt thủ công các hồ sơ nghi vấn hoặc thuộc danh sách cảnh báo, quyết định từ chối hoặc cấp phép thị thực. |
| 4 | Border Control Officer / e-Gate | Đọc chip sinh trắc học trên hộ chiếu, đối soát nhận diện khuôn mặt 1:1, kiểm tra hạn visa và điều khiển mở cổng tự động e-Gate. |
