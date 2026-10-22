# Swimlane Diagram — City Budget & Expenditure Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["City Department Head"]
        A1["Formulate Annual Budget Proposal"]
        A2["Submit Payment Warrant Order"]
        A3["Receive Treasury Payment Advice"]
    end

    subgraph Lane2["City Budget Tracking System"]
        S1["Verify Proposal vs Budget Ceiling"]
        S2["Check Real-time Available Balance"]
        S3{"Balance Available?"}
        S4["Generate Digital Encumbrance Record"]
        S5["Deduct Allocation & Update Balance"]
    end

    subgraph Lane3["Municipal Budget Officer"]
        O1["Review Department Proposals"]
        O2["Approve Annual Budget Allocation"]
        O3["Sign Digital Approval Warrant"]
    end

    subgraph Lane4["State Treasury System"]
        B1["Audit Warrant & Invoices Compliance"]
        B2{"Treasury Audit Approved?"}
        B3["Execute Interbank Money Transfer"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> O2 --> A2 --> S2 --> S3
    S3 -->|"No / Overspent"| A2
    S3 -->|"Yes"| S4 --> O3 --> B1 --> B2
    B2 -->|"No"| O3
    B2 -->|"Yes"| B3 --> S5 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | City Department Head | Lập đề xuất dự toán ngân sách năm, tạo Lệnh rút dự toán chi tiêu kèm chứng từ hóa đơn và nhận báo nợ/có từ Kho bạc. |
| 2 | City Budget Tracking System | Kiểm tra hạn mức trần dự toán, đối soát số dư khả dụng thời gian thực, ghi nhận cam kết chi và tự động gạch trừ số dư. |
| 3 | Municipal Budget Officer | Thẩm định dự toán đề xuất của đơn vị, giao dự toán chính thức và ký số duyệt lệnh rút dự toán chuyển Kho bạc. |
| 4 | State Treasury System | Kiểm soát tính hợp pháp của hóa đơn chứng từ chi tiêu công và thực hiện lệnh chuyển tiền giải ngân cho người thụ hưởng. |
