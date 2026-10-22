# Conceptual ERD — Geotechnical Investigation Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    GEOTECHNIC_MAIN["GEOTECHNIC_MAIN"]
    GEOTECHNIC_DETAIL["GEOTECHNIC_DETAIL"]
    GEOTECHNIC_INSPECTION["GEOTECHNIC_INSPECTION"]
    GEOTECHNIC_RECORD["GEOTECHNIC_RECORD"]
    GEOTECHNIC_COMPLIANCE["GEOTECHNIC_COMPLIANCE"]
    GEOTECHNIC_REPORT["GEOTECHNIC_REPORT"]

    %% Các khối hình thoi quan hệ
    rel_contains{"contains"}
    rel_requires{"requires"}
    rel_generates{"generates"}
    rel_verifies{"verifies"}
    rel_produces{"produces"}

    %% Kết nối 1-N
    GEOTECHNIC_MAIN ---|1| rel_contains ---|N| GEOTECHNIC_DETAIL
    GEOTECHNIC_MAIN ---|1| rel_requires ---|N| GEOTECHNIC_INSPECTION
    GEOTECHNIC_MAIN ---|1| rel_generates ---|N| GEOTECHNIC_RECORD
    GEOTECHNIC_MAIN ---|1| rel_verifies ---|N| GEOTECHNIC_COMPLIANCE
    GEOTECHNIC_MAIN ---|1| rel_produces ---|N| GEOTECHNIC_REPORT

```

## Entity Description Table | Bang mo ta Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | GEOTECHNIC_MAIN | Entity geotechnic_main | Stores geotechnic_main data for Geotechnical Investigation Management System | id | Main core entity |
| 2 | GEOTECHNIC_DETAIL | Entity geotechnic_detail | Stores geotechnic_detail data for Geotechnical Investigation Management System | detail_id | Main core entity |
| 3 | GEOTECHNIC_INSPECTION | Entity geotechnic_inspection | Stores geotechnic_inspection data for Geotechnical Investigation Management System | inspect_id | Main core entity |
| 4 | GEOTECHNIC_RECORD | Entity geotechnic_record | Stores geotechnic_record data for Geotechnical Investigation Management System | record_id | Main core entity |
| 5 | GEOTECHNIC_COMPLIANCE | Entity geotechnic_compliance | Stores geotechnic_compliance data for Geotechnical Investigation Management System | comp_id | Main core entity |
| 6 | GEOTECHNIC_REPORT | Entity geotechnic_report | Stores geotechnic_report data for Geotechnical Investigation Management System | report_id | Main core entity |

## Relationship Description | Mo ta Quan he

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | GEOTECHNIC_MAIN | one-to-many | GEOTECHNIC_DETAIL | contains | Thanh phan chinh bao gom nhieu chi tiet nghiep vu |
| 2 | GEOTECHNIC_MAIN | one-to-many | GEOTECHNIC_INSPECTION | requires | Thanh phan chinh yeu cau cac dot kiem tra kiem dinh |
| 3 | GEOTECHNIC_MAIN | one-to-many | GEOTECHNIC_RECORD | generates | Thanh phan chinh xuat cac ban ghi thong ke |
| 4 | GEOTECHNIC_MAIN | one-to-many | GEOTECHNIC_COMPLIANCE | verifies | Thanh phan chinh kiem tra tinh tuan thu quy chuan |
| 5 | GEOTECHNIC_MAIN | one-to-many | GEOTECHNIC_REPORT | produces | Thanh phan chinh xuat cac bao cao tong hop |
