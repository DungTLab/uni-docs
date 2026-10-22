# Swimlane Diagram — Public Utility Billing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Smart IoT Metering Network"]
        A1["Stream Monthly AMR Consumption Reading"]
        A2["Send Meter Tamper & Voltage Telemetry"]
    end

    subgraph Lane2["Public Utility Billing Platform"]
        S1["Verify Consumption Volume & Detect Anomalies"]
        S2["Calculate Tiered Tariff & Apply Welfare Subsidy"]
        S3["Issue Consolidated e-Bill PDF with VietQR"]
        S4["Send SMS & Zalo Billing Notification"]
        S5["Verify Payment & Auto-Settle Overdue Flag"]
        S6["Send Remote AMR Relays Reconnect Signal"]
    end

    subgraph Lane3["Utility Customer"]
        B1["Receive e-Bill Notification"]
        B2["Scan VietQR via VNeID Pay / Banking App"]
        B3["Receive E-Payment Receipt & Service Restored"]
    end

    subgraph Lane4["City Payment Gateway (VNeID Pay)"]
        O1["Process Bank Settlement & Real-time Debit"]
        O2["Return Instant Payment Callback to Platform"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> S3 --> S4 --> B1 --> B2 --> O1 --> O2 --> S5 --> S6 --> B3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Smart IoT Metering Network | Tự động đọc và truyền chỉ số chốt điện/nước hàng tháng từ xa (AMR) về hệ thống trung tâm qua vệ tinh/sóng vô tuyến. |
| 2 | Public Utility Billing Platform | Kiểm tra tính hợp lệ của sản lượng, tính tiền điện nước theo bậc thang, trừ tiền trợ cấp hộ nghèo và phát hành e-Bill. |
| 3 | Utility Customer | Nhận thông báo hóa đơn qua tin nhắn, quét mã VietQR/VNeID Pay để nộp tiền điện nước và nhận biên lai điện tử. |
| 4 | City Payment Gateway (VNeID Pay) | Xử lý quyết toán giao dịch trực tuyến với ngân hàng và gửi tín hiệu gạch nợ tức thì về cho hệ thống hóa đơn. |
