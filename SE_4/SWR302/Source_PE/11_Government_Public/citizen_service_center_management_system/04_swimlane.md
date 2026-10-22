# Swimlane Diagram — Citizen Service Center Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen / Business Applicant"]
        A1["Take Smart Queue Ticket at Kiosk / VNeID"]
        A2["Submit Paper & Digital Dossier Files"]
        A3["Pay Administrative Fee via VietQR Scan"]
        A4["Rate Officer Service Satisfaction Stars"]
    end

    subgraph Lane2["Citizen Service Center Platform"]
        S1["Verify Identity via VNeID Database API"]
        S2["Generate Application Code & SLA Timer"]
        S3["Issue Fee QR & Settle E-Receipt"]
        S4["Route E-Dossier to Department API"]
        S5["Store PKI E-Outcome in Citizen Vault"]
    end

    subgraph Lane3["One-Stop Desk Officer"]
        O1["Inspect Dossier Components & Issue Receipt"]
        O2["Hand Over Outcome / Register Courier"]
    end

    subgraph Lane4["Specialized Department (Sở / Ngành)"]
        B1["Examine Technical Dossier & Approve"]
        B2["Apply PKI Executive Digital Signature"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> S2 --> S3 --> A3 --> S4 --> B1 --> B2 --> S5 --> O2 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen / Business Applicant | Lấy số thứ tự thông minh tại Kiosk/VNeID, nộp hồ sơ tại Quầy Một cửa, quét mã VietQR nộp lệ phí và chấm điểm hài lòng. |
| 2 | Citizen Service Center Platform | Xác thực VNeID công dân, tính hạn xử lý SLA, xuất mã QR thu phí, luân chuyển hồ sơ điện tử liên thông và lưu kết quả PKI. |
| 3 | One-Stop Desk Officer | Kiểm tra tính hợp lệ thành phần hồ sơ, cấp Giấy tiếp nhận hẹn trả kết quả và trao kết quả bản giấy cho công dân. |
| 4 | Specialized Department (Sở / Ngành) | Thẩm định nội dung chuyên môn hồ sơ, phê duyệt và ký số PKI phát hành Kết quả thủ tục hành chính điện tử. |
