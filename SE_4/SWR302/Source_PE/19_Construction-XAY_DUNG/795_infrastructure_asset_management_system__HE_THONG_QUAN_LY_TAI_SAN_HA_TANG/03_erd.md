# Conceptual ERD — Infrastructure Asset Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    INFRASTRUC_MAIN["INFRASTRUC_MAIN"]
    INFRASTRUC_DETAIL["INFRASTRUC_DETAIL"]
    INFRASTRUC_INSPECTION["INFRASTRUC_INSPECTION"]
    INFRASTRUC_RECORD["INFRASTRUC_RECORD"]
    INFRASTRUC_COMPLIANCE["INFRASTRUC_COMPLIANCE"]
    INFRASTRUC_REPORT["INFRASTRUC_REPORT"]

    %% Các khối hình thoi quan hệ
    rel_contains{"contains"}
    rel_requires{"requires"}
    rel_generates{"generates"}
    rel_verifies{"verifies"}
    rel_produces{"produces"}

    %% Kết nối 1-N
    INFRASTRUC_MAIN ---|1| rel_contains ---|N| INFRASTRUC_DETAIL
    INFRASTRUC_MAIN ---|1| rel_requires ---|N| INFRASTRUC_INSPECTION
    INFRASTRUC_MAIN ---|1| rel_generates ---|N| INFRASTRUC_RECORD
    INFRASTRUC_MAIN ---|1| rel_verifies ---|N| INFRASTRUC_COMPLIANCE
    INFRASTRUC_MAIN ---|1| rel_produces ---|N| INFRASTRUC_REPORT

```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | INFRASTRUC_MAIN | Entity infrastruc_main | Stores infrastruc_main data for Infrastructure Asset Management System | id | Main core entity |
| 2 | INFRASTRUC_DETAIL | Entity infrastruc_detail | Stores infrastruc_detail data for Infrastructure Asset Management System | detail_id | Main core entity |
| 3 | INFRASTRUC_INSPECTION | Entity infrastruc_inspection | Stores infrastruc_inspection data for Infrastructure Asset Management System | inspect_id | Main core entity |
| 4 | INFRASTRUC_RECORD | Entity infrastruc_record | Stores infrastruc_record data for Infrastructure Asset Management System | record_id | Main core entity |
| 5 | INFRASTRUC_COMPLIANCE | Entity infrastruc_compliance | Stores infrastruc_compliance data for Infrastructure Asset Management System | comp_id | Main core entity |
| 6 | INFRASTRUC_REPORT | Entity infrastruc_report | Stores infrastruc_report data for Infrastructure Asset Management System | report_id | Main core entity |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | INFRASTRUC_MAIN | one-to-many | INFRASTRUC_DETAIL | contains | Thanh phan chinh bao gom nhieu chi tiet nghiep vu |
| 2 | INFRASTRUC_MAIN | one-to-many | INFRASTRUC_INSPECTION | requires | Thanh phan chinh yeu cau cac dot kiem tra kiem dinh |
| 3 | INFRASTRUC_MAIN | one-to-many | INFRASTRUC_RECORD | generates | Thanh phan chinh xuat cac ban ghi thong ke |
| 4 | INFRASTRUC_MAIN | one-to-many | INFRASTRUC_COMPLIANCE | verifies | Thanh phan chinh kiem tra tinh tuan thu quy chuan |
| 5 | INFRASTRUC_MAIN | one-to-many | INFRASTRUC_REPORT | produces | Thanh phan chinh xuat cac bao cao tong hop |
