# Swimlane Diagram — National Healthcare Insurance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Insured Patient"]
        A1["Scan VNeID Digital Insurance Card QR"]
        A2["Receive Medical Examination & Treatment"]
        A3["Pay Co-Payment Share at Hospital Counter"]
    end

    subgraph Lane2["Hospital HIS System"]
        S1["Verify Card Validity & Benefit Level 1-4"]
        S2["Calculate Co-Payment Share %"]
        S3["Package & Push Medical Claim XML Files"]
    end

    subgraph Lane3["National Health Insurance Portal (VSS)"]
        O1["Run 200+ Automated Claim Rules Check"]
        O2["Run AI Fraud Abuse Scanner"]
        O3{"AI Fraud Risk Flagged?"}
        O4["Reimburse Approved Medical Funds to Hospital"]
    end

    subgraph Lane4["VSS Electronic Claim Examiner"]
        B1["Audit Suspicious High-Cost XML Claims"]
        B2["Approve or Adjust Reimbursable Amounts"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> S2 --> A3 --> S3 --> O1 --> O2 --> O3
    O3 -->|"Yes / Risk > 80%"| B1 --> B2 --> O4 --> Finish
    O3 -->|"No / Clean"| O4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Insured Patient | Quét mã QR thẻ BHYT trên ứng dụng VNeID tại quầy đón tiếp, khám chữa bệnh và nộp phần tiền đồng chi trả. |
| 2 | Hospital HIS System | Tự động xác thực thẻ BHYT và mức hưởng, tính số tiền đồng chi trả, đóng gói 5 tệp XML chi phí KCB đẩy lên Cổng VSS. |
| 3 | National Health Insurance Portal (VSS) | Chạy 200+ quy tắc giám định tự động, quét AI chống lạm dụng/trục lợi Quỹ BHYT và duyệt chuyển tiền quyết toán cho bệnh viện. |
| 4 | VSS Electronic Claim Examiner | Thẩm định trực tiếp các hồ sơ bị AI cảnh báo nghi vấn trục lợi, phê duyệt lại số tiền Quỹ BHYT chi trả. |
