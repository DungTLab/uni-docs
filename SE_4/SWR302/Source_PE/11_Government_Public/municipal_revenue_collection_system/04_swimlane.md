# Swimlane Diagram — Municipal Revenue Collection System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["City Taxpayer"]
        A1["Receive E-Payment Notice & QR Code"]
        A2["Scan VietQR on Mobile Banking App"]
        A3["Confirm E-Payment Transaction"]
        A4["Download Signed Electronic Receipt"]
    end

    subgraph Lane2["Municipal Revenue Platform Core"]
        S1["Assess Annual Land & Non-Tax Fees"]
        S2["Generate Payment Ref ID & VietQR"]
        S3["Receive Real-time Bank Payment Callback"]
        S4["Auto-Deduct Taxpayer Debt Ledger"]
        S5["Generate Signed Electronic Receipt PDF"]
    end

    subgraph Lane3["Commercial Bank System"]
        B1["Process Interbank Money Transfer"]
        B2["Transmit Instant Payment Confirmation"]
    end

    subgraph Lane4["State Treasury System"]
        T1["Ingest Revenue Fund Account Credit"]
        T2["Reconcile Daily Revenue Batch Log"]
    end

    Finish(["End"])

    Start --> S1 --> S2 --> A1 --> A2 --> A3 --> B1 --> B2 --> S3 --> S4 --> S5 --> A4 --> T1 --> T2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | City Taxpayer | Nhận Thông báo nộp tiền điện tử chứa mã VietQR, mở ứng dụng ngân hàng quét mã thanh toán và tải biên lai thu tiền. |
| 2 | Municipal Revenue Platform Core | Tính toán các khoản thuế/phí, sinh mã định danh khoản nộp ID & VietQR, nhận kết quả gạch nợ tự động và xuất biên lai điện tử. |
| 3 | Commercial Bank System | Xử lý chuyển tiền thu hộ ngân sách nhà nước và gửi tín hiệu báo có thời gian thực (Callback) về hệ thống. |
| 4 | State Treasury System | Ghi nhận tiền vào tài khoản thu ngân sách tại Kho bạc và thực hiện đối soát tự động cuối ngày. |
