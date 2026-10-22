# Swimlane Diagram — Public Library Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Library Patron"]
        A1["Search Book on OPAC Catalog"]
        A2["Pick Book from Shelf & Bring to Kiosk"]
        A3["Scan Digital Reader Pass QR"]
        A4["Receive Checked-out Books & Receipt"]
    end

    subgraph Lane2["Self-Check RFID Kiosk / Web"]
        K1["Read Patron QR Token"]
        K2["Scan RFID Tags on Books"]
        K3["Display Loan Summary & Due Date"]
    end

    subgraph Lane3["Library Management System Core"]
        S1["Verify Patron Eligibility & Card Status"]
        S2{"Card Valid & Fines Clean?"}
        S3["Check Maximum Borrow Limit"]
        S4["Record Loan Transaction & Set Due Date"]
        S5["Deactivate Anti-Theft RFID Security Tag"]
    end

    subgraph Lane4["Chief Librarian Staff"]
        O1["Assist Manual Checkout if Exception"]
        O2["Collect Overdue Fines Payment"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> A3 --> K1 --> S1 --> S2
    S2 -->|"No / Fine Owed"| O2 --> S1
    S2 -->|"Yes / Clean"| S3 --> K2 --> K3 --> S4 --> S5 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Library Patron | Tra cứu vị trí sách trên OPAC, lấy sách từ kệ mang đến trạm mượn, quét mã QR thẻ độc giả và nhận sách đã mượn. |
| 2 | Self-Check RFID Kiosk / Web | Đọc mã QR độc giả, quét chíp RFID trên tập sách mượn và hiển thị tóm tắt hạn trả cho độc giả xác nhận. |
| 3 | Library Management System Core | Xác thực thẻ độc giả, kiểm tra hạn mức mượn sách, khởi tạo giao dịch mượn, tính hạn trả và tự động ngắt chíp từ an ninh RFID. |
| 4 | Chief Librarian Staff | Hỗ trợ mượn sách thủ công khi gặp sự cố thẻ và thực hiện thu tiền phạt quá hạn trực tiếp tại quầy. |
