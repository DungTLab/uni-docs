# Swimlane Diagram — E-Government Portal

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen / Applicant"]
        A1["Select Public Service"]
        A2["Fill e-Form & Upload Documents"]
        A3["Submit Application Dossier"]
        A4["Pay Administrative Fee"]
        A5["Download Signed Certificate"]
    end

    subgraph Lane2["E-Government Portal System"]
        S1["Verify Identity via VNeID"]
        S2["Validate Form & Scan Viruses"]
        S3["Generate Unique Dossier Code"]
        S4{"Check Form Completeness"}
        S5["Redirect to Payment Gateway"]
        S6["Publish Digital Result to Vault"]
    end

    subgraph Lane3["Government Official / Officer"]
        O1["Receive Application Queue"]
        O2["Review Dossier Legalities"]
        O3{"Approve Application?"}
        O4["Request Document Re-submission"]
        O5["Approve & Digitally Sign Result"]
    end

    subgraph Lane4["Payment Gateway"]
        P1["Process Payment Transaction"]
        P2{"Payment Success?"}
        P3["Issue Payment Receipt"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> A3 --> S2 --> S4
    S4 -->|"No / Invalid"| O4 --> A2
    S4 -->|"Yes"| S3 --> O1 --> O2 --> O3
    O3 -->|"Rejected"| O4
    O3 -->|"Approved"| S5 --> A4 --> P1 --> P2
    P2 -->|"No"| A4
    P2 -->|"Yes"| P3 --> O5 --> S6 --> A5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen / Applicant | Chọn dịch vụ, điền biểu mẫu điện tử, đính kèm giấy tờ, nộp hồ sơ, thanh toán lệ phí và tải về kết quả điện tử. |
| 2 | E-Government Portal System | Xác thực định danh VNeID, tự động kiểm tra định dạng và vi-rút, sinh mã hồ sơ, luân chuyển trạng thái và phát hành kết quả lên ví giấy tờ. |
| 3 | Government Official / Officer | Phân công thụ lý, kiểm tra tính pháp lý của tài liệu, yêu cầu bổ sung nếu thiếu và duyệt ký số kết quả giải quyết. |
| 4 | Payment Gateway | Tiếp nhận yêu cầu thanh toán lệ phí dịch vụ công, xử lý giao dịch qua ngân hàng/ví điện tử và trả biên lai xác nhận. |
