# Swimlane Diagram — Court Case Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Litigant / Defense Lawyer"]
        A1["Submit Lawsuit Petition & Evidence"]
        A2["Pay Advance Court Fee Online"]
        A3["Receive Summons & Access Case Files"]
        A4["Attend Hearing / Join Virtual Court"]
        A5["Receive Digital Verdict PDF"]
    end

    subgraph Lane2["Court Case Portal System"]
        S1["Verify Digital Signature & Form"]
        S2["Generate Advance Fee Payment Notice"]
        S3["Execute Random Judge Assignment"]
        S4["Stream Secure Video & Record Minutes"]
        S5["Publish Redacted Verdict to Web"]
    end

    subgraph Lane3["Court Clerk / Registrar"]
        O1["Verify Initial Claim Eligibility"]
        O2["Schedule Hearing & Courtroom Slot"]
        O3["Serve Electronic Summons Notice"]
        O4["Draft Official Trial Minutes"]
    end

    subgraph Lane4["Presiding Judge"]
        E1["Accept Case & Review Evidence"]
        E2["Preside Over Trial Hearing"]
        E3{"Verdict Decision Reached?"}
        E4["Issue Signed Digital Verdict"]
        E5["Transfer Case to Enforcement"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> S2 --> A2 --> S3 --> E1 --> O2 --> S4 --> O3 --> A3 --> A4 --> E2 --> O4 --> E3
    E3 -->|"No / Delay"| O2
    E3 -->|"Yes"| E4 --> S5 --> A5 --> E5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Litigant / Defense Lawyer | Nộp đơn khởi kiện và chứng cứ điện tử, nộp tiền tạm ứng án phí, nhận giấy triệu tập, tham gia phiên tòa trực tiếp/trực tuyến và nhận bản án. |
| 2 | Court Case Portal System | Kiểm tra định dạng và chữ ký số, tính tạm ứng án phí, phân công Thẩm phán ngẫu nhiên, truyền luồng video phiên tòa trực tuyến và công khai bản án. |
| 3 | Court Clerk / Registrar | Kiểm tra tính hợp lệ của đơn, xếp lịch phiên tòa và phòng xử án, tống đạt giấy triệu tập và lập biên bản phiên tòa điện tử. |
| 4 | Presiding Judge | Thụ lý vụ án, điều hành phiên tòa xét xử, thảo luận tuyên án, ký số bản án chính thức và chuyển bản án sang cơ quan thi hành án. |
