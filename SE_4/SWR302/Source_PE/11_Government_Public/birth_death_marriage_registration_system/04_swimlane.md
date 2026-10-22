# Swimlane Diagram — Birth Death & Marriage Registration System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Citizen Applicant"]
        A1["Submit Birth Registration File"]
        A2["Enter Hospital Birth Notice Code"]
        A3["Review Draft Birth Certificate"]
        A4["Receive Digital Certificate PDF & PIN"]
    end

    subgraph Lane2["Civil Status Registration System"]
        S1["Verify Hospital Birth Data Online"]
        S2["Query VNeID Core for 12-digit PIN"]
        S3{"PIN & Verification Passed?"}
        S4["Embed Newborn PIN into Certificate"]
        S5["Generate Signed Digital Certificate"]
        S6["Sync Birth Event to VNeID Database"]
    end

    subgraph Lane3["Hospital / Medical Center"]
        H1["Issue Electronic Birth Notice"]
        H2["Push Birth Telemetry to System"]
    end

    subgraph Lane4["Civil Registrar Officer"]
        O1["Review Birth Dossier & Parents ID"]
        O2["Approve & Apply Digital Signature"]
    end

    Finish(["End"])

    Start --> H1 --> H2 --> A1 --> A2 --> S1 --> O1 --> S2 --> S3
    S3 -->|"No"| A1
    S3 -->|"Yes"| S4 --> O2 --> S5 --> S6 --> A3 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Citizen Applicant | Nộp đơn khai sinh cho con, nhập mã chứng sinh bệnh viện, kiểm tra dự thảo giấy khai sinh và nhận bản điện tử kèm số PIN. |
| 2 | Civil Status Registration System | Đối soát giấy chứng sinh điện tử từ bệnh viện, kết nối CSDL dân cư xin cấp Số định danh cá nhân 12 số, ký số bản án và đồng bộ dữ liệu. |
| 3 | Hospital / Medical Center | Khởi tạo giấy chứng sinh điện tử ngay khi trẻ ra đời và đẩy dữ liệu lên trục liên thông dịch vụ công. |
| 4 | Civil Registrar Officer | Kiểm tra thông tin nhân thân của cha mẹ, thẩm định tính hợp pháp của tên gọi và thực hiện duyệt ký số Giấy khai sinh. |
