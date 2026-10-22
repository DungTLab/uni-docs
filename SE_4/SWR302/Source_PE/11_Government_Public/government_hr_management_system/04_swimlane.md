# Swimlane Diagram — Government HR Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Civil Servant Employee"]
        A1["Submit Digital HR Dossier Updates VNeID"]
        A2["Submit Annual Self-Evaluation Report"]
        A3["Receive Approved Salary Slip & Digital ID"]
    end

    subgraph Lane2["Government HR Platform Core"]
        S1["Verify Identity via VNeID Database API"]
        S2["Calculate Auto-Payroll & Salary Coefficient"]
        S3["Package Treasury Payroll File PKI Sign"]
        S4["Sync National Civil Servant Database"]
    end

    subgraph Lane3["Ministry HR Director"]
        O1["Review & Digitally Sign Dossier Record"]
        O2["Audit & Approve Annual KPI Ratings"]
        O3["Issue Cadre Transfer / Promotion Order"]
    end

    subgraph Lane4["Ministry of Home Affairs (MOHA)"]
        B1["Verify Position Headcount Quota Limits"]
        B2["Authorize Civil Service Salary Budget"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> O1 --> A2 --> O2 --> S2 --> B1 --> B2 --> S3 --> O3 --> S4 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Civil Servant Employee | Nộp cập nhật hồ sơ công chức qua VNeID, nộp bản tự đánh giá công tác năm và nhận phiếu lương điện tử. |
| 2 | Government HR Platform Core | Xác thực dữ liệu VNeID, tự động tính lương ngạch bậc, đóng gói file chi trả chuyển Kho bạc và đồng bộ CSDL Quốc gia. |
| 3 | Ministry HR Director | Thẩm định và ký số phê duyệt hồ sơ cán bộ, duyệt xếp loại chất lượng KPI và ban hành quyết định điều động. |
| 4 | Ministry of Home Affairs (MOHA) | Kiểm tra định mức chỉ tiêu biên chế vị trí việc làm và phê duyệt ngân sách lương công chức toàn quốc. |
