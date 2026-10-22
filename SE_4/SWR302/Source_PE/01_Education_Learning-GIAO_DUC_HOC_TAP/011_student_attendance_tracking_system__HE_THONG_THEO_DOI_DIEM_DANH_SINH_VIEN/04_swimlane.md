# Swimlane Diagram — Student Attendance Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["User / Student"]
        A1["Submit Application & Upload Files"]
        A2["Initiate Digital Payment"]
    end

    subgraph Lane2["System"]
        S1["Validate Input Data & File Format"]
        S2{"Is Data Valid?"}
        S3["Generate Invoice & Dispatch Alert"]
        S4["Mark Paid & Finalize Record"]
    end

    subgraph Lane3["Admin / Evaluator"]
        M1["Inspect Documents & Verify Criteria"]
        M2{"Approve Request?"}
    end

    subgraph Lane4["Payment Gateway"]
        P1["Process Payment Transaction"]
        P2{"Transaction Success?"}
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2
    S2 -->|"Yes"| M1 --> M2
    S2 -->|"No"| A1
    M2 -->|"Yes"| S3 --> A2 --> P1 --> P2
    M2 -->|"No"| S1
    P2 -->|"Yes"| S4 --> Finish
    P2 -->|"No"| A2
```

## Flow Description | Mô tả luồng

| # | Lane / Actor | Role in Flow |
|---|--------------|--------------|
| 1 | User / Student | Khởi tạo yêu cầu, nộp hồ sơ và thanh toán |
| 2 | System | Kiểm tra dữ liệu, điều phối luồng và ghi nhận CSDL |
| 3 | Admin / Evaluator | Thẩm định nội dung và ra quyết định phê duyệt |
| 4 | Payment Gateway | Xử lý giao dịch thanh toán tài chính |
