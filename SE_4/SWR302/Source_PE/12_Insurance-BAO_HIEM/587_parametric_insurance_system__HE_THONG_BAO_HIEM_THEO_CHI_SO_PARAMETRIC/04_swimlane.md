# Swimlane Diagram — Parametric Insurance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Application"])

    subgraph Lane_Customer["Customer"]
        C1["Fill Application & Details"]
        C2["Perform eKYC Identification"]
        C3["Pay Premium via Payment Gateway"]
        C4["Receive Digital Policy"]
        C5["Submit Loss Event Claim"]
        C6["Receive Settlement Payout"]
    end

    subgraph Lane_Agent["Insurance Agent"]
        A1["Advise Product & Premium Quote"]
        A2["Submit Lead & Policy Application"]
    end

    subgraph Lane_System["System Workflow"]
        S1["Verify Input & Auto Risk Scoring"]
        S2{"Requires Underwriter Review?"}
        S3["Generate Payment Bill Invoice"]
        S4["Verify Payment & Activate Policy"]
        S5["Log Claim Dossier & Notify Adjuster"]
        S6["Trigger Payment API Transfer"]
    end

    subgraph Lane_Underwriter["Underwriter Specialist"]
        U1["Assess Specific Risk Factors"]
        U2{"Approve Coverage?"}
        U3["Reject Policy Request"]
    end

    subgraph Lane_Adjuster["Claims Adjuster"]
        J1["Survey Damage & Verify Proofs"]
        J2{"Approve Settlement Amount?"}
        J3["Issue Claim Refusal Notice"]
    end

    Finish(["End Process"])

    Start --> C1
    C1 --> A1 --> A2 --> C2
    C2 --> S1 --> S2

    S2 -- "No (Standard Risk)" --> S3
    S2 -- "Yes (High Risk)" --> U1

    U1 --> U2
    U2 -- "Approved" --> S3
    U2 -- "Rejected" --> U3 --> Finish

    S3 --> C3 --> S4 --> C4

    C4 -. "Loss Event Occurs" .-> C5
    C5 --> S5 --> J1 --> J2

    J2 -- "Approved" --> S6 --> C6 --> Finish
    J2 -- "Rejected" --> J3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Customer | Khai báo thông tin đề nghị, thực hiện xác thực eKYC, thanh toán phí bảo hiểm, nhận đơn điện tử và nộp yêu cầu bồi thường khi tổn thất xảy ra. |
| 2 | Insurance Agent | Tư vấn gói sản phẩm, tính toán phí bảo hiểm sơ bộ và hỗ trợ nộp hồ sơ đăng ký lên hệ thống. |
| 3 | System Workflow | Kiểm tra dữ liệu tự động, tính điểm rủi ro, phát hành chứng nhận hợp đồng, tạo hóa đơn thu phí và kết nối API chi trả bồi thường với ngân hàng. |
| 4 | Underwriter Specialist | Thẩm định chi tiết các trường hợp rủi ro cao, đưa ra quyết định chấp thuận bảo hiểm, tăng phí hoặc từ chối cấp đơn. |
| 5 | Claims Adjuster | Giám định hiện trường tổn thất, xác định mức độ thiệt hại thực tế, đối soát với điều khoản hợp đồng và phê duyệt phương án chi trả bồi thường. |
