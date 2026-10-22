# Swimlane Diagram — Public Works Project Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Construction Contractor"]
        A1["Submit Milestone Progress & Photos"]
        A2["Submit Payment Disbursement Invoice"]
        A3["Receive Treasury Funds in Bank Account"]
    end

    subgraph Lane2["Public Works Management Platform"]
        S1["Verify Progress vs Baseline Schedule"]
        S2["Check Quality Inspection Results"]
        S3{"Quality & Progress Approved?"}
        S4["Calculate Payment Net of Retention"]
        S5["Generate Encrypted Disbursement Warrant"]
        S6["Update Project Disbursement Rate %"]
    end

    subgraph Lane3["Supervision Consultant"]
        O1["Conduct On-Site Quality Test"]
        O2["Sign Digital Quality Certificate"]
    end

    subgraph Lane4["Public Works Project Manager"]
        E1["Review Milestone Acceptance"]
        E2["Approve PKI Disbursement Warrant"]
    end

    subgraph Lane5["State Treasury System"]
        B1["Audit Fund Availability & Warrant"]
        B2["Execute Direct Investment Fund Transfer"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> O2 --> S2 --> S3
    S3 -->|"No / Defect"| A1
    S3 -->|"Yes / Passed"| A2 --> E1 --> S4 --> E2 --> S5 --> B1 --> B2 --> S6 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Construction Contractor | Cập nhật tiến độ mốc thi công đính kèm ảnh GPS, nộp hồ sơ đề nghị thanh toán khối lượng hoàn thành và nhận tiền giải ngân. |
| 2 | Public Works Management Platform | Kiểm tra tiến độ so với đường găng, kiểm tra phiếu kiểm định chất lượng, tính tiền giảm trừ tạm ứng và xuất lệnh thanh toán. |
| 3 | Supervision Consultant | Khảo sát kiểm tra chất lượng thực địa công trường, thí nghiệm mẫu bê tông và ký số giấy chứng nhận chất lượng. |
| 4 | Public Works Project Manager | Rà soát hồ sơ nghiệm thu mốc khối lượng và ký số phê duyệt Lệnh thanh toán vốn đầu tư công gửi Kho bạc. |
| 5 | State Treasury System | Kiểm soát chi đầu tư công và thực hiện lệnh chuyển tiền giải ngân trực tiếp vào tài khoản ngân hàng của nhà thầu. |
