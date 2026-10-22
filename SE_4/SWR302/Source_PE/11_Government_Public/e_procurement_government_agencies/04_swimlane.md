# Swimlane Diagram — E-Procurement for Government Agencies

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Commercial Bidder"]
        A1["Download Free E-HSMT Bidding Package"]
        A2["Encrypt & Submit E-HSDT Bid Proposal"]
        A3["Attend Online Public Bid Opening"]
        A4["Sign Digital E-Procurement Contract"]
    end

    subgraph Lane2["E-Procurement Platform Core"]
        S1["Verify Bank Digital Bid Security"]
        S2["Store Dual-Layer Encrypted Bid Payload"]
        S3["Run AI Collusive Rigging Scanner"]
        S4{"AI Collusion Detected?"}
        S5["Auto-Decrypt Bids & Generate Opening Record"]
        S6["Publish Winning Award Result & Notify Treasury"]
    end

    subgraph Lane3["Evaluation Board"]
        O1["Evaluate Technical Proposals Pass/Fail"]
        O2["Evaluate Financial Prices & Rank Bidders"]
    end

    subgraph Lane4["Government Bidding Agency"]
        E1["Publish E-HSMT Package Notices"]
        E2["Approve Contract Award Decision PKI"]
    end

    Finish(["End"])

    Start --> E1 --> A1 --> A2 --> S1 --> S2 --> S3 --> S4
    S4 -->|"Yes / Collusion"| S3
    S4 -->|"No / Clean"| A3 --> S5 --> O1 --> O2 --> E2 --> S6 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Commercial Bidder | Tải E-HSMT miễn phí, mã hóa và nộp E-HSDT trực tuyến, tham dự mở thầu online và ký số hợp đồng trúng thầu. |
| 2 | E-Procurement Platform Core | Xác thực bảo lãnh ngân hàng, lưu tệp mã hóa, chạy AI quét thông thầu, tự động giải mã mở thầu và công khai kết quả. |
| 3 | Evaluation Board | Đánh giá hồ sơ đề xuất kỹ thuật (Đạt/Không đạt), thẩm định giá đề xuất tài chính và xếp hạng nhà thầu trúng thầu. |
| 4 | Government Bidding Agency | Đăng tải E-HSMT công khai và ký số PKI Quyết định phê duyệt kết quả lựa chọn nhà thầu. |
