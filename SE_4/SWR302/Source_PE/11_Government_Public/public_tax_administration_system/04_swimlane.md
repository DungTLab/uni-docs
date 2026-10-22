# Swimlane Diagram — Public Tax Administration System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Taxpayer"]
        A1["Prepare Tax Return Form XML"]
        A2["Sign Electronically via USB Token"]
        A3["Submit Return to eTax Portal"]
        A4["Select Bank & Execute Payment"]
        A5["Receive Tax Clearance & Receipt"]
    end

    subgraph Lane2["Public Tax Portal System"]
        S1["Verify Digital Signature & Format"]
        S2["Calculate Tax Payable & Penalties"]
        S3{"Validation Successful?"}
        S4["Generate Payment Voucher & Notice"]
        S5["Match Payment Confirmation with Ledger"]
    end

    subgraph Lane3["Tax Officer / Inspector"]
        O1["Review Tax Filing Dossier"]
        O2["Cross-check e-Invoice Data"]
        O3{"Risk Detected?"}
        O4["Issue Audit Explanation Demand"]
        O5["Approve Assessment & Clear Record"]
    end

    subgraph Lane4["State Treasury & Commercial Bank"]
        B1["Process Interbank Money Transfer"]
        B2{"Funds Received at Treasury?"}
        B3["Issue Treasury Collection Electronic Notice"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> A3 --> S1 --> S2 --> S3
    S3 -->|"No"| A1
    S3 -->|"Yes"| O1 --> O2 --> O3
    O3 -->|"Yes"| O4 --> A1
    O3 -->|"No"| S4 --> A4 --> B1 --> B2
    B2 -->|"No"| A4
    B2 -->|"Yes"| B3 --> S5 --> O5 --> A5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Taxpayer | Lập tờ khai thuế điện tử, ký số cá nhân/doanh nghiệp, gửi tờ khai, thực hiện lệnh chuyển tiền nộp thuế và nhận chứng từ xác nhận. |
| 2 | Public Tax Portal System | Kiểm tra định dạng và chữ ký số, tự động hạch toán nghĩa vụ thuế, sinh chứng từ nộp tiền và đối soát tự động gạch nợ. |
| 3 | Tax Officer / Inspector | Kiểm tra tính chính xác của tờ khai, đối soát hóa đơn điện tử mua vào/bán ra, yêu cầu giải trình nếu phát hiện rủi ro và phê duyệt hoàn thành nghĩa vụ. |
| 4 | State Treasury & Commercial Bank | Tiếp nhận tiền nộp thuế qua kênh liên ngân hàng, hạch toán vào Ngân sách Nhà nước và gửi thông báo điện tử xác nhận thu tiền. |
