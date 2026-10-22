# Conceptual ERD — Telecom Billing & Revenue Management System

## Mermaid Code

```mermaid
graph TD
    %% Định dạng hình dạng: [] là Thực thể (Hình chữ nhật), {} là Mối quan hệ (Hình thoi)

    SUBSCRIBER_ACCOUNT["SUBSCRIBER_ACCOUNT"]
    BILLING_CYCLE["BILLING_CYCLE"]
    TARIFF_PLAN["TARIFF_PLAN"]
    CDR_RECORD["CDR_RECORD"]
    INVOICE["INVOICE"]
    PAYMENT_TRANSACTION["PAYMENT_TRANSACTION"]
    DISPUTE_CLAIM["DISPUTE_CLAIM"]
    GL_POSTING_LOG["GL_POSTING_LOG"]

    %% Các khối hình thoi quan hệ
    rel_generates{"generates"}
    rel_receives{"receives"}
    rel_groups{"groups"}
    rel_applies_to{"applies_to"}
    rel_settled_by{"settled_by"}
    rel_subject_of{"subject_of"}
    rel_exports{"exports"}

    %% Kết nối 1-N
    SUBSCRIBER_ACCOUNT ---|1| rel_generates ---|N| CDR_RECORD
    SUBSCRIBER_ACCOUNT ---|1| rel_receives ---|N| INVOICE
    BILLING_CYCLE ---|1| rel_groups ---|N| INVOICE
    TARIFF_PLAN ---|1| rel_applies_to ---|N| SUBSCRIBER_ACCOUNT
    INVOICE ---|1| rel_settled_by ---|N| PAYMENT_TRANSACTION
    INVOICE ---|1| rel_subject_of ---|N| DISPUTE_CLAIM
    BILLING_CYCLE ---|1| rel_exports ---|N| GL_POSTING_LOG

```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | SUBSCRIBER_ACCOUNT | Tài khoản Thuê bao | Main customer telecom account | account_id PK, customer_name string, account_status string | generates, receives, applies_to |
| 2 | BILLING_CYCLE | Chu kỳ Tính cước | Defines recurring monthly billing periods | cycle_id PK, cycle_name string, start_date date | groups, exports |
| 3 | TARIFF_PLAN | Gói Cước | Pricing rules for voice, data, and SMS | tariff_id PK, tariff_name string, voice_rate decimal | applies_to |
| 4 | CDR_RECORD | Bản ghi CDR | Call Detail Record for usage charging | cdr_id PK, account_id FK, call_type string | generates |
| 5 | INVOICE | Hóa đơn Viễn thông | Monthly subscriber bill statement | invoice_id PK, account_id FK, cycle_id FK | receives, groups, settled_by, subject_of |
| 6 | PAYMENT_TRANSACTION | Giao dịch Thanh toán | Payment details collected against invoices | payment_id PK, invoice_id FK, payment_method string | settled_by |
| 7 | DISPUTE_CLAIM | Khiếu nại Cước | Customer bill dispute records | dispute_id PK, invoice_id FK, dispute_reason string | subject_of |
| 8 | GL_POSTING_LOG | Nhật ký Sổ kế toán | Audit log of financial journal postings | posting_id PK, cycle_id FK, total_revenue decimal | exports |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | SUBSCRIBER_ACCOUNT | one-to-many | CDR_RECORD | generates | One subscriber generates multiple CDR records |
| 2 | SUBSCRIBER_ACCOUNT | one-to-many | INVOICE | receives | One subscriber receives multiple monthly invoices |
| 3 | BILLING_CYCLE | one-to-many | INVOICE | groups | One billing cycle generates multiple invoices |
| 4 | TARIFF_PLAN | one-to-many | SUBSCRIBER_ACCOUNT | applies_to | One tariff plan is applied to subscriber accounts |
| 5 | INVOICE | one-to-many | PAYMENT_TRANSACTION | settled_by | An invoice is settled by payment transactions |
| 6 | INVOICE | one-to-many | DISPUTE_CLAIM | subject_of | An invoice can be the subject of a dispute claim |
| 7 | BILLING_CYCLE | one-to-many | GL_POSTING_LOG | exports | A billing cycle exports data to GL posting logs |
