# Swimlane Diagram — Government Audit & Inspection System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Audited Government Entity"]
        A1["Upload General Ledger & Voucher Files"]
        A2["Submit Explanation for Audit Findings"]
        A3["Deposit Recovered Funds to State Treasury"]
    end

    subgraph Lane2["State Audit Platform Core"]
        S1["Verify Treasury Ledger Integrity"]
        S2["Run AI Budget Overspending Scanner"]
        S3{"AI Financial Anomaly Flagged?"}
        S4["Track Recommendation Recovery %"]
        S5["Publish Public Audit Summary Portal"]
    end

    subgraph Lane3["State Auditor / Lead Inspector"]
        O1["Conduct On-Site Fieldwork Audit"]
        O2["Draft Audit Exception Findings"]
        O3["Draft Final Audit Report & PKI Sign"]
    end

    subgraph Lane4["State Audit Office (KTNN)"]
        E1["Approve Annual Government Audit Plan"]
        E2["Monitor 100% Recommendation Fulfillment"]
    end

    Finish(["End"])

    Start --> E1 --> A1 --> S1 --> S2 --> S3
    S3 -->|"Yes / Anomaly"| O1 --> O2 --> A2 --> O3 --> S4 --> A3 --> E2 --> S5 --> Finish
    S3 -->|"No / Clean"| O3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Audited Government Entity | Tải lên dữ liệu sổ cái kế toán, nộp văn bản giải trình đối với các sai phạm và nộp lại tiền vi phạm vào Kho bạc. |
| 2 | State Audit Platform Core | Kiểm tra tính toàn vẹn dữ liệu Kho bạc, chạy AI quét chi vượt dự toán, theo dõi % thu hồi nộp NSNN và công khai báo cáo. |
| 3 | State Auditor / Lead Inspector | Khảo sát kiểm tra thực địa, lập Biên bản sai phạm tài chính, dự thảo Báo cáo kiểm toán và ký số PKI phát hành. |
| 4 | State Audit Office (KTNN) | Phê duyệt Kế hoạch kiểm toán ngân sách hàng năm và giám sát đôn đốc thực hiện 100% các kiến nghị sau kiểm toán. |
