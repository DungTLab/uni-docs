# Swimlane Diagram — Social Welfare Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Welfare Beneficiary (Citizen)"]
        A1["Submit Assistance Application"]
        A2["Cooperate in Field Poverty Survey"]
        A3["Receive Decision Notification"]
        A4["Receive Direct Bank Transfer"]
    end

    subgraph Lane2["Social Welfare System"]
        S1["Verify Demographic via VNeID"]
        S2["Calculate Poverty Index Score"]
        S3{"Eligibility Passed?"}
        S4["Generate Digital Welfare Decision"]
        S5["Batch Order Monthly Payment"]
        S6["Sync Subsidized Health Card"]
    end

    subgraph Lane3["Social Worker (Field Officer)"]
        O1["Receive Assigned Field Survey Task"]
        O2["Survey Living Condition Criteria"]
        O3["Upload Household Score & Photos"]
    end

    subgraph Lane4["Bank / Payment Partner"]
        B1["Receive Monthly Disbursement File"]
        B2{"Account Transfer Success?"}
        B3["Confirm Payment Receipt to System"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> O2 --> O3 --> S2 --> S3
    S3 -->|"No"| A3 --> Finish
    S3 -->|"Yes"| S4 --> S6 --> S5 --> B1 --> B2
    B2 -->|"No"| S5
    B2 -->|"Yes"| B3 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Welfare Beneficiary (Citizen) | Nộp đơn trợ cấp, hợp tác cùng cán bộ khảo sát thực địa, nhận quyết định phê duyệt và nhận tiền trợ cấp qua tài khoản ngân hàng. |
| 2 | Social Welfare System | Xác thực định danh VNeID, tự động tính điểm nghèo đa chiều, kiểm tra điều kiện chính sách, xuất quyết định chi trả và gạch nợ thành công. |
| 3 | Social Worker (Field Officer) | Nhận danh sách khảo sát thực địa, đến từng gia đình chấm điểm tiêu chí và tải bằng chứng hình ảnh lên hệ thống. |
| 4 | Bank / Payment Partner | Tiếp nhận danh sách lệnh chi trả tiền trợ cấp hàng tháng, thực hiện chuyển khoản đến người thụ hưởng và đối soát kết quả. |
