# Swimlane Diagram — Refugee & Asylum Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Refugee Applicant"]
        A1["Arrive at Border / Camp Reception"]
        A2["Provide Iris & 10 Fingerprint Biometrics"]
        A3["Participate in In-Depth RSD Interview"]
        A4["Receive UN Refugee Identity Card"]
    end

    subgraph Lane2["Refugee Management Platform"]
        S1["Verify Duplicate Biometrics via UNHCR proGres"]
        S2["Check Country of Origin Info COI Database"]
        S3{"Asylum Claim Eligible under 1951 Convention?"}
        S4["Apply Non-Refoulement Legal Protection"]
        S5["Generate UNHCR Card QR Code & PKI Sign"]
    end

    subgraph Lane3["Refugee Resettlement Officer"]
        O1["Conduct In-Depth RSD Interview & Assessment"]
        O2["Assign Camp Shelter & Distribute Aid Kits"]
    end

    subgraph Lane4["UNHCR Official"]
        B1["Approve RSD Status Determination Verdict"]
        B2["Authorize Third-Country Resettlement Roster"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> O1 --> S2 --> S3
    S3 -->|"No / Rejected"| A1
    S3 -->|"Yes / Approved"| B1 --> S4 --> S5 --> A4 --> O2 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Refugee Applicant | Đến trạm tiếp nhận biên giới, cung cấp sinh trắc học mống mắt/vân tay, phỏng vấn RSD và nhận Thẻ định danh UNHCR. |
| 2 | Refugee Management Platform | Kiểm tra trùng lặp CSDL proGres toàn cầu, tra cứu dữ liệu đất nước gốc COI, kích hoạt bảo hộ Không trục xuất và in thẻ. |
| 3 | Refugee Resettlement Officer | Phỏng vấn chuyên sâu thẩm định tư cách tị nạn, bố trí chỗ ở tại trại tạm cư và phân phát các gói hàng viện trợ nhân đạo. |
| 4 | UNHCR Official | Duyệt Quyết định tư cách người tị nạn (RSD) và phê duyệt danh sách chuyển người tị nạn đi tái định cư tại nước thứ ba. |
