# Conceptual ERD — Parametric Insurance System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    CUSTOMER["CUSTOMER"]
    AGENT["AGENT"]
    PRODUCT_CATALOG["PRODUCT_CATALOG"]
    POLICY["POLICY"]
    INSURED_SUBJECT["INSURED_SUBJECT"]
    UNDERWRITING_RECORD["UNDERWRITING_RECORD"]
    PREMIUM_TRANSACTION["PREMIUM_TRANSACTION"]
    CLAIM_DOSSIER["CLAIM_DOSSIER"]
    SURVEY_DOCUMENT["SURVEY_DOCUMENT"]
    SETTLEMENT_PAYOUT["SETTLEMENT_PAYOUT"]
    COMMISSION_RECORD["COMMISSION_RECORD"]

    %% Các khối hình thoi quan hệ
    rel_holds{"holds"}
    rel_services{"services"}
    rel_defines_terms_for{"defines_terms_for"}
    rel_covers{"covers"}
    rel_undergoes{"undergoes"}
    rel_generates{"generates"}
    rel_incurs{"incurs"}
    rel_includes{"includes"}
    rel_settled_by{"settled_by"}
    rel_earns{"earns"}
    rel_triggers{"triggers"}

    %% Kết nối 1-1
    CLAIM_DOSSIER ---|1| rel_settled_by ---|1| SETTLEMENT_PAYOUT

    %% Kết nối 1-N
    CUSTOMER ---|1| rel_holds ---|N| POLICY
    AGENT ---|1| rel_services ---|N| POLICY
    PRODUCT_CATALOG ---|1| rel_defines_terms_for ---|N| POLICY
    POLICY ---|1| rel_covers ---|N| INSURED_SUBJECT
    POLICY ---|1| rel_undergoes ---|N| UNDERWRITING_RECORD
    POLICY ---|1| rel_generates ---|N| PREMIUM_TRANSACTION
    POLICY ---|1| rel_incurs ---|N| CLAIM_DOSSIER
    CLAIM_DOSSIER ---|1| rel_includes ---|N| SURVEY_DOCUMENT
    AGENT ---|1| rel_earns ---|N| COMMISSION_RECORD
    PREMIUM_TRANSACTION ---|1| rel_triggers ---|N| COMMISSION_RECORD

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | CUSTOMER | Khách hàng | Lưu trữ thông tin định danh người mua bảo hiểm | customer_id, full_name, national_id, ekyc_status | Holds many POLICY |
| 2 | AGENT | Đại lý bảo hiểm | Quản lý mã đại lý và thông tin người tư vấn sản phẩm | agent_id, agent_code, license_number, status | Services many POLICY, Earns COMMISSION_RECORD |
| 3 | PRODUCT_CATALOG | Danh mục sản phẩm | Định nghĩa các gói sản phẩm và quy tắc biểu phí chuẩn | product_id, product_code, base_premium_rate | Defines terms for many POLICY |
| 4 | POLICY | Hợp đồng bảo hiểm | Dữ liệu chính của chứng nhận/hợp đồng bảo hiểm | policy_id, policy_number, sum_insured, policy_status | Belongs to CUSTOMER, AGENT, PRODUCT; Has many SUBJECT... |
| 5 | INSURED_SUBJECT | Đối tượng bảo hiểm | Thông tin tài sản/đối tượng cụ thể được bảo vệ | subject_id, policy_id, subject_type, estimated_value | Belongs to POLICY |
| 6 | UNDERWRITING_RECORD | Hồ sơ thẩm định | Ghi nhận đánh giá rủi ro và quyết định của Underwriter | record_id, policy_id, risk_rating, decision_status | Belongs to POLICY |
| 7 | PREMIUM_TRANSACTION | Giao dịch phí | Lịch sử thu phí bảo hiểm từ khách hàng | transaction_id, policy_id, amount, payment_status | Belongs to POLICY; Triggers COMMISSION_RECORD |
| 8 | CLAIM_DOSSIER | Hồ sơ bồi thường | Thông tin yêu cầu bồi thường sự cố tổn thất | claim_id, claim_number, estimated_loss, claim_status | Belongs to POLICY; Has SURVEY_DOCUMENT, SETTLEMENT_PAYOUT |
| 9 | SURVEY_DOCUMENT | Tài liệu giám định | Bằng chứng hình ảnh, báo cáo hiện trường tổn thất | document_id, claim_id, document_type, file_path | Belongs to CLAIM_DOSSIER |
| 10 | SETTLEMENT_PAYOUT | Lệnh chi trả | Phương án bồi thường và chi trả tài chính cuối cùng | payout_id, claim_id, approved_amount, payout_status | Has one-to-one with CLAIM_DOSSIER |
| 11 | COMMISSION_RECORD | Hoa hồng đại lý | Khoản hoa hồng đại lý được hưởng theo giao dịch | commission_id, agent_id, transaction_id, commission_amount | Belongs to AGENT, POLICY, TRANSACTION |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | CUSTOMER | one-to-many | POLICY | holds | Một khách hàng sở hữu một hoặc nhiều hợp đồng bảo hiểm. |
| 2 | AGENT | one-to-many | POLICY | services | Một đại lý tư vấn và phục vụ nhiều hợp đồng bảo hiểm. |
| 3 | PRODUCT_CATALOG | one-to-many | POLICY | defines_terms_for | Một gói sản phẩm tiêu chuẩn quy định điều khoản cho nhiều hợp đồng. |
| 4 | POLICY | one-to-many | INSURED_SUBJECT | covers | Một hợp đồng bảo vệ một hoặc nhiều đối tượng/tài sản bảo hiểm. |
| 5 | POLICY | one-to-many | UNDERWRITING_RECORD | undergoes | Một hợp đồng trải qua quá trình đánh giá thẩm định rủi ro. |
| 6 | POLICY | one-to-many | PREMIUM_TRANSACTION | generates | Một hợp đồng phát sinh các giao dịch thu phí định kỳ. |
| 7 | POLICY | one-to-many | CLAIM_DOSSIER | incurs | Một hợp đồng có thể phát sinh các vụ việc yêu cầu bồi thường. |
| 8 | CLAIM_DOSSIER | one-to-many | SURVEY_DOCUMENT | includes | Một hồ sơ bồi thường chứa nhiều chứng từ và báo cáo giám định. |
| 9 | CLAIM_DOSSIER | one-to-one | SETTLEMENT_PAYOUT | settled_by | Mỗi hồ sơ bồi thường được giải quyết bằng duy nhất một phương án chi trả. |
| 10 | AGENT | one-to-many | COMMISSION_RECORD | earns | Một đại lý nhận nhiều khoản hoa hồng từ doanh số bán hàng. |
| 11 | PREMIUM_TRANSACTION | one-to-many | COMMISSION_RECORD | triggers | Mỗi giao dịch thu phí thành công kích hoạt tính toán hoa hồng tương ứng. |
