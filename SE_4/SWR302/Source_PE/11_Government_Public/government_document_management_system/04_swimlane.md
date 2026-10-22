# Swimlane Diagram — Government Document Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Drafting Officer"]
        A1["Draft Official Document & Select Format"]
        A2["Submit Document Draft for Review"]
        A3["Track Approval Status & Revisions"]
    end

    subgraph Lane2["Government Document System Core"]
        S1["Validate Document Template & Attributes"]
        S2["Route Draft to Executive Inbox"]
        S3["Verify Government PKI CA Token"]
        S4["Embed PAdES Digital Signature & Stamp"]
        S5["Package Edoc Payload & Send via VDES"]
    end

    subgraph Lane3["Government Executive (Approver)"]
        O1["Review Draft Content & Annexes"]
        O2["Approve & Sign via USB Token PKI"]
    end

    subgraph Lane4["Inter-Agency Exchange Network (VDES)"]
        B1["Transmit Edoc Package to Destination Agency"]
        B2["Return Delivery Timestamp Confirmation"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> A2 --> S2 --> O1 --> O2 --> S3 --> S4 --> S5 --> B1 --> B2 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Drafting Officer | Soạn thảo văn bản hành chính theo thể thức chuẩn, đính kèm phụ lục và trình duyệt lên lãnh đạo cấp trên. |
| 2 | Government Document System Core | Tự động kiểm tra mẫu thể thức, luân chuyển văn bản, xác thực Token chữ ký số PKI, vẽ hình chữ ký số và phát hành VDES. |
| 3 | Government Executive (Approver) | Xem xét nội dung dự thảo, bấm ký số phê duyệt bằng USB Token/SIM PKI chuyên dùng Chính phủ. |
| 4 | Inter-Agency Exchange Network (VDES) | Truyền tải gói tin văn bản điện tử sang cơ quan nhận và trả về thời gian xác nhận đã giao văn bản. |
