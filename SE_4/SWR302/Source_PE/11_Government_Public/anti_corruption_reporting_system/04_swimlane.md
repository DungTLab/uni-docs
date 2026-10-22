# Swimlane Diagram — Anti-Corruption Reporting System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Anonymous Whistleblower"]
        A1["Submit Encrypted Corruption Tip-Off & Evidence"]
        A2["Receive Anonymous PGP Token Code"]
        A3["Check Case Progress Anonymously via Token"]
    end

    subgraph Lane2["Anti-Corruption Platform Core"]
        S1["Strip IP Address & EXIF Photo Metadata"]
        S2["Encrypt Tip-Off Payload via PGP 4096-bit"]
        S3["Run AI Unexplained Wealth & AML Cross-Check"]
        S4{"Evidence Solid & Fraud Verified?"}
        S5["Generate Automated Emergency Asset Freeze API"]
        S6["Publish Case Progress on Secure Token Portal"]
    end

    subgraph Lane3["Anti-Corruption Officer"]
        O1["Review Anonymous Tip-Off Queue"]
        O2["Assign Case Specialist & Verify Bank Accounts"]
    end

    subgraph Lane4["Anti-Corruption Police (C03)"]
        B1["Issue Bank Account Asset Freeze Orders"]
        B2["Initiate Formal Criminal Prosecution"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2 --> A2 --> O1 --> O2 --> S3 --> S4
    S4 -->|"No / Unsubstantiated"| S6
    S4 -->|"Yes / Valid"| B1 --> S5 --> B2 --> S6 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Anonymous Whistleblower | Nộp đơn tố cáo tham nhũng kèm bằng chứng, nhận mã Token PGP ẩn danh và tra cứu tiến độ xử lý mà không lộ danh tính. |
| 2 | Anti-Corruption Platform Core | Xóa bỏ IP/Metadata, mã hóa PGP 4096-bit, chạy AI đối soát tài sản bất minh rửa tiền và gửi lệnh phong tỏa tài sản. |
| 3 | Anti-Corruption Officer | Rà soát danh sách đơn tố cáo, phân công cán bộ thụ lý chuyên trách và đối soát tài khoản ngân hàng cán bộ. |
| 4 | Anti-Corruption Police (C03) | Ban hành lệnh phong tỏa tài khoản khẩn cấp, khởi tố vụ án hình sự và chuyển hồ sơ sang Viện Kiểm sát. |
