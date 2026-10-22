# Swimlane Diagram — Election Campaign Finance Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Individual Donor"]
        A1["Transfer Donation to Dedicated Campaign Account"]
        A2["Provide Identity VNeID & Tax Code"]
        A3["Receive Official E-Contribution Receipt"]
    end

    subgraph Lane2["Election Campaign Finance Platform"]
        S1["Verify Donor Identity & Nationality VNeID"]
        S2["Check Cumulative Individual Spending Cap"]
        S3{"Donor Cap & Foreign Check Valid?"}
        S4["Record Contribution & Credit Campaign Ledger"]
        S5["Publish Anonymized Contribution on Open Portal"]
    end

    subgraph Lane3["Candidate Treasurer"]
        O1["Register Dedicated Campaign Bank Account"]
        O2["Upload Expense Invoices & Advertising Logs"]
    end

    subgraph Lane4["National Election Council (NEC)"]
        B1["Monitor Real-time Campaign Inflow & Outflow"]
        B2["Approve Public Financial Disclosure PDF"]
    end

    Finish(["End"])

    Start --> O1 --> A1 --> A2 --> S1 --> S2 --> S3
    S3 -->|"No / Excess Cap"| A1
    S3 -->|"Yes / Valid"| S4 --> O2 --> B1 --> S5 --> B2 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Individual Donor | Chuyển tiền ủng hộ vào tài khoản chuyên dùng của ứng viên, cung cấp định danh VNeID và nhận biên nhận điện tử. |
| 2 | Election Campaign Finance Platform | Tự động xác thực VNeID, kiểm tra trần đóng góp tối đa và dòng tiền nước ngoài, ghi sổ và công khai công khai minh bạch. |
| 3 | Candidate Treasurer | Đăng ký tài khoản ngân hàng bầu cử chuyên dùng duy nhất và tải lên các hóa đơn chứng từ chi tiêu vận động. |
| 4 | National Election Council (NEC) | Giám sát thời gian thực dòng tiền thu chi chiến dịch bầu cử và phê duyệt xuất bản báo cáo công khai tài chính. |
