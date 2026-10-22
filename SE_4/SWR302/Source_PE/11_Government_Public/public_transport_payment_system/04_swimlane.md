# Swimlane Diagram — Public Transport Payment System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Commuter (Passenger)"]
        A1["Tap Smart Card at Entry Turnstile"]
        A2["Board Bus / Metro Train"]
        A3["Tap Smart Card at Exit Turnstile"]
        A4["Check Deduction & Balance Display"]
    end

    subgraph Lane2["Gate Hardware & AFC System"]
        S1["Read Card NFC Data & Verify Balance"]
        S2{"Card Valid & Funds OK?"}
        S3["Record Entry Station Code & Time"]
        S4["Unlock Entry Barrier Gate"]
        S5["Read Exit Station & Compute Distance"]
        S6["Deduct Calculated Trip Fare"]
        S7["Unlock Exit Barrier Gate"]
    end

    subgraph Lane3["Payment Network & Clearing"]
        B1["Batch Daily Transit Transactions"]
        B2["Settle Interbank Funds with Operators"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2
    S2 -->|"No"| A1
    S2 -->|"Yes"| S3 --> S4 --> A2 --> A3 --> S5 --> S6 --> S7 --> A4 --> B1 --> B2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Commuter (Passenger) | Chạm thẻ vé tại cửa vào, di chuyển trên phương tiện công cộng, chạm thẻ tại cửa ra và kiểm tra số tiền khấu trừ. |
| 2 | Gate Hardware & AFC System | Đọc chip NFC thẻ, xác minh điều kiện và số dư, ghi nhận ga vào, mở cửa xoay, tính toán cước phí chặng ra, trừ tiền và mở cửa ra. |
| 3 | Payment Network & Clearing | Gom lô các giao dịch quẹt thẻ trong ngày và thực hiện đối soát quyết toán dòng tiền giữa ngân hàng và đơn vị vận hành. |
