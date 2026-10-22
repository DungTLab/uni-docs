# Swimlane Diagram — Citizen Identity Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen"]
        A1["Present Birth Record / Documents"]
        A2["Provide Fingerprint & Face Biometrics"]
        A3["Review & Sign Enrollment Sheet"]
        A4["Receive Activation SMS"]
        A5["Activate VNeID Digital Profile"]
    end

    subgraph Lane2["Registration Station System"]
        S1["Verify Civil Database Demographics"]
        S2["Capture 10 Fingerprints & Iris"]
        S3["Evaluate Biometric Quality Score"]
        S4{"Quality Passed?"}
        S5["Generate National Identity PIN"]
        S6["Issue Digital Identity Credentials"]
    end

    subgraph Lane3["Registration Officer"]
        O1["Verify Physical Paper Documents"]
        O2["Instruct Biometric Positioning"]
        O3{"Approve Identity Application?"}
        O4["Reject Application & State Reason"]
        O5["Sign Off Dossier Verification"]
    end

    subgraph Lane4["National Biometric ABIS Core"]
        B1["Run 1:N Biometric Deduplication"]
        B2{"Duplicate Found?"}
        B3["Flag Potential Identity Fraud"]
        B4["Store Encrypted Biometric Vectors"]
    end

    Finish(["End"])

    Start --> A1 --> O1 --> S1 --> O2 --> A2 --> S2 --> S3 --> S4
    S4 -->|"No"| O2
    S4 -->|"Yes"| A3 --> O3
    O3 -->|"No"| O4 --> Finish
    O3 -->|"Yes"| O5 --> B1 --> B2
    B2 -->|"Yes"| B3 --> Finish
    B2 -->|"No"| B4 --> S5 --> S6 --> A4 --> A5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen | Xuất trình giấy tờ gốc, hợp tác lấy mẫu sinh trắc học, ký xác nhận phiếu thu nhận và kích hoạt tài khoản định danh số. |
| 2 | Registration Station System | Kiểm tra dữ liệu nhân khẩu trên máy trạm, lấy mẫu sinh trắc, đánh giá chất lượng hình ảnh, khởi tạo số định danh 12 số và cấp chứng thư số. |
| 3 | Registration Officer | Kiểm tra đối soát giấy tờ gốc, hướng dẫn công dân lấy sinh trắc học và thực hiện thẩm định phê duyệt hồ sơ. |
| 4 | National Biometric ABIS Core | Hệ thống nhận dạng sinh trắc học tự động quốc gia thực hiện đối soát trùng lặp 1:N toàn bộ database để phát hiện gian lận và mã hóa lưu trữ vector sinh trắc. |
