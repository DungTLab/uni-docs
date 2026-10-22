# Swimlane Diagram — Government Open Data Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Agency Data Publisher"]
        A1["Upload Raw Government Dataset & DCAT Metadata"]
        A2["Review AI PII Sanitization Audit Report"]
        A3["Digitally Sign PKI Open Data Publication"]
    end

    subgraph Lane2["Government Open Data Platform"]
        S1["Run AI PII Scanner & Mask Sensitive Fields"]
        S2["Convert Data Format CSV JSON Parquet"]
        S3["Publish Dataset to CKAN Open Catalog"]
        S4["Validate Rate Limits & Return API Response"]
    end

    subgraph Lane3["Data Consumer / Developer"]
        B1["Query Open Data Catalog DCAT"]
        B2["Request Public Open API Key"]
        B3["Invoke Open API & Download Data Files"]
    end

    subgraph Lane4["Ministry of Information & Comms (MIC)"]
        O1["Audit Dataset Update Frequency Adherence"]
        O2["Publish National Open Data Index Ranking"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> A3 --> S2 --> S3 --> B1 --> B2 --> S4 --> B3 --> O1 --> O2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Agency Data Publisher | Nạp tệp dữ liệu thô và metadata DCAT, xem báo cáo làm sạch PII của AI và ký số PKI xuất bản dữ liệu mở. |
| 2 | Government Open Data Platform | Chạy AI che giấu PII cá nhân, tự động chuyển đổi định dạng CSV/JSON/Parquet và đăng công khai trên catalog CKAN. |
| 3 | Data Consumer / Developer | Tra cứu danh mục dữ liệu mở, xin cấp khóa API Key, gọi lệnh API hoặc tải tệp dữ liệu về máy để phát triển ứng dụng. |
| 4 | Ministry of Information & Comms (MIC) | Kiểm toán tần suất cập nhật dữ liệu đúng hạn của các cơ quan và công khai Bảng xếp hạng Chỉ số Dữ liệu Mở Quốc gia. |
