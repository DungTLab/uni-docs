# Swimlane Diagram — Business License & Permit System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Enterprise Applicant"]
        A1["Submit Enterprise Registration Application"]
        A2["Pay Registration Fee Online"]
        A3["Receive Signed Digital Certificate PDF & QR"]
    end

    subgraph Lane2["Business Licensing Platform Core"]
        S1["Verify Enterprise Name Uniqueness"]
        S2["Query Tax API for 10-digit Tax Code"]
        S3{"Tax Code & Name Unique?"}
        S4["Generate Digital Business License PDF"]
        S5["Sync Master Record to National Registry DB"]
    end

    subgraph Lane3["Licensing Officer (Sở KH&ĐT)"]
        O1["Review Legal Dossier & Corporate Charter"]
        O2["Approve Business Registration Order"]
        O3["Sign Digital PKI Certificate"]
    end

    subgraph Lane4["National Business Registry DB"]
        B1["Assign National Unique Business ID"]
        B2["Publish Enterprise Announcement"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> S3
    S3 -->|"No"| A1
    S3 -->|"Yes"| O1 --> O2 --> B1 --> S4 --> O3 --> S5 --> B2 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Enterprise Applicant | Nộp hồ sơ đăng ký thành lập doanh nghiệp trực tuyến, nộp lệ phí công bố và nhận Giấy phép kinh doanh điện tử. |
| 2 | Business Licensing Platform Core | Kiểm tra trùng tên doanh nghiệp, liên thông API Thuế sinh Mã số thuế 10 số, tạo Giấy phép điện tử và đồng bộ CSDL Quốc gia. |
| 3 | Licensing Officer (Sở KH&ĐT) | Thẩm định tính hợp lệ của Điều lệ và hồ sơ nhân thân, duyệt quyết định cấp phép và ký số pháp lý PKI. |
| 4 | National Business Registry DB | Cấp mã số doanh nghiệp quốc gia duy nhất và tự động công bố thông tin đăng ký doanh nghiệp trên Cổng thông tin. |
