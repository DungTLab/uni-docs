# Swimlane Diagram — Prison & Correctional Facility System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Inmate Family Visitor"]
        A1["Submit Online Family Visit Request"]
        A2["Verify Family Relationship VNeID"]
        A3["Deposit Trust Fund Money Online"]
        A4["Present Visit QR Code at Prison Gate"]
    end

    subgraph Lane2["Correctional Facility Platform Core"]
        S1["Verify Inmate Disciplinary Record"]
        S2{"Inmate Allowed Family Visit?"}
        S3["Credit Inmate Digital Trust Account"]
        S4["Issue Encrypted Visit Permit QR"]
        S5["Log Completed Visit History"]
    end

    subgraph Lane3["Correctional Guard Officer"]
        O1["Review & Approve Visit Request"]
        O2["Escort Inmate to Visitor Parlor"]
        O3["Monitor 15-Min Meeting Session"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2
    S2 -->|"No / Disciplined"| A1
    S2 -->|"Yes / Eligible"| O1 --> S3 --> A3 --> S4 --> A4 --> O2 --> O3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Inmate Family Visitor | Đăng ký lịch thăm gặp trực tuyến qua VNeID, nộp tiền lưu ký vào tài khoản phạm nhân và xuất trình mã QR tại trại giam. |
| 2 | Correctional Facility Platform Core | Tự động kiểm tra kỷ luật phạm nhân, nạp tiền vào tài khoản lưu ký, phát hành Giấy phép thăm gặp QR và ghi nhật ký. |
| 3 | Correctional Guard Officer | Rà soát phê duyệt lịch hẹn thăm gặp, trích xuất phạm nhân từ buồng giam ra nhà thăm gặp và giám sát buổi gặp 15 phút. |
