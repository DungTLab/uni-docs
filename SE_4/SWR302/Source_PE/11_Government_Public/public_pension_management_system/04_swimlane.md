# Swimlane Diagram — Public Pension Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Pension Beneficiary (Retiree)"]
        A1["Receive Proof of Life Verification Prompt"]
        A2["Perform Facial Scan on Mobile App"]
        A3["Receive Monthly Pension Direct Deposit"]
    end

    subgraph Lane2["Public Pension Platform Core"]
        S1["Verify Facial Biometrics via VNeID API"]
        S2{"Biometrics Match & Alive?"}
        S3["Extend Pension Eligibility Status 6-Months"]
        S4["Compile Monthly Pension Payout Payroll"]
        S5["Generate Bank Transfer Instruction Batch"]
        S6["Mark Disbursement Complete & Send Payslip"]
    end

    subgraph Lane3["Public Pension Officer"]
        O1["Review Biometric Exception Queue"]
        O2["Approve Monthly Pension Disbursement"]
    end

    subgraph Lane4["Commercial Paying Bank"]
        B1["Process Instant Bulk Direct Deposits"]
        B2["Return Account Credit Status Callback"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2
    S2 -->|"No"| O1 --> S3
    S2 -->|"Yes"| S3 --> S4 --> O2 --> S5 --> B1 --> B2 --> S6 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Pension Beneficiary (Retiree) | Nhận thông báo nhắc lịch xác thực sinh trắc học, thực hiện quét khuôn mặt trên ứng dụng di động và nhận tiền lương hưu. |
| 2 | Public Pension Platform Core | Truy vấn CSDL VNeID đối soát khuôn mặt, gia hạn trạng thái còn sống, lập bảng lương hưu hàng tháng và xuất lệnh chuyển tiền. |
| 3 | Public Pension Officer | Rà soát danh sách ngoại lệ không xác thực được sinh trắc học và phê duyệt bảng chi trả lương hưu hàng tháng. |
| 4 | Commercial Paying Bank | Thực hiện lệnh chuyển khoản tự động số lượng lớn vào tài khoản cá nhân người hưởng và trả về tín hiệu báo có. |
