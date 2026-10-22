# Swimlane Diagram — Public Health Information System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen / Patient"]
        A1["Visit Hospital with Symptoms"]
        A2["Provide VNeID for EHR Lookup"]
        A3["Receive Treatment & Isolation Guidance"]
        A4["Check Vaccine Pass QR on App"]
    end

    subgraph Lane2["Hospital / Clinic System"]
        S1["Query National EHR Profile"]
        S2["Diagnose Notifiable Disease ICD-10"]
        S3["Submit Urgent Case Report within 24h"]
    end

    subgraph Lane3["Public Health System Core"]
        C1["Ingest Case Report & Match Location"]
        C2["Check Outbreak Threshold R0"]
        C3{"Outbreak Triggered?"}
        C4["Generate GIS Heatmap Alert"]
        C5["Issue Geo-fenced Health Alert SMS"]
        C6["Issue QR Immunization Certificate"]
    end

    subgraph Lane4["CDC Epidemiologist"]
        E1["Receive Emergency Outbreak Alert"]
        E2["Conduct Field Contact Tracing"]
        E3["Issue Medical Quarantine Order"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> S3 --> C1 --> C2 --> C3
    C3 -->|"No"| C6 --> A4 --> Finish
    C3 -->|"Yes"| C4 --> E1 --> E2 --> E3 --> C5 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen / Patient | Đến khám bệnh tại cơ sở y tế, xuất trình VNeID tra cứu hồ sơ EHR, nhận hướng dẫn điều trị cách ly và kiểm tra chứng nhận tiêm vắc-xin. |
| 2 | Hospital / Clinic System | Tra cứu hồ sơ sức khỏe quốc gia, chẩn đoán ca bệnh truyền nhiễm theo mã ICD-10 và gửi báo cáo khai báo ca bệnh bắt buộc lên hệ thống. |
| 3 | Public Health System Core | Tiếp nhận báo cáo ca bệnh, tự động tính toán chỉ số bùng phát dịch R0, dựng bản đồ nhiệt GIS, gửi tin cảnh báo khẩn cấp và cấp mã QR vắc-xin. |
| 4 | CDC Epidemiologist | Nhận cảnh báo ổ dịch khẩn cấp, tiến hành điều tra dịch tễ truy vết F1/F2 tại thực địa và ban hành quyết định cách ly y tế. |
