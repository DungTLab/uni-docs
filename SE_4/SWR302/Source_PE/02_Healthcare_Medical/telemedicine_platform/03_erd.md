# Conceptual ERD — Telemedicine Platform

## Mermaid Code

```mermaid
erDiagram
    PATIENT_USER {
        int patient_id PK
        string full_name
        string email
        string phone
        date date_of_birth
        string gender
        string emergency_contact
    }

    DOCTOR_PROFILE {
        int doctor_id PK
        string full_name
        string medical_license_no
        string specialty_name
        int years_experience
        decimal consultation_fee
        decimal average_rating
        string status
    }

    DOCTOR_AVAILABILITY {
        int slot_id PK
        int doctor_id FK
        date slot_date
        time start_time
        time end_time
        boolean is_booked
    }

    VIRTUAL_APPOINTMENT {
        int appointment_id PK
        int patient_id FK
        int doctor_id FK
        int slot_id FK
        datetime scheduled_time
        string consultation_type
        string chief_complaint
        string booking_status
    }

    CONSULTATION_SESSION {
        int session_id PK
        int appointment_id FK
        datetime actual_start_time
        datetime actual_end_time
        string soap_subjective
        string soap_objective
        string icd10_diagnosis
        string clinical_summary
    }

    VIDEO_CALL_LOG {
        int call_id PK
        int session_id FK
        string room_session_token
        int duration_seconds
        string video_quality_grade
        int disconnect_count
    }

    SYMPTOM_ATTACHMENT {
        int attachment_id PK
        int appointment_id FK
        string file_name
        string file_type
        string storage_url
        datetime uploaded_at
    }

    TELE_PRESCRIPTION {
        int rx_id PK
        int session_id FK
        datetime issued_at
        string digital_signature_hash
        string fulfillment_type
        string rx_status
    }

    PRESCRIPTION_ITEM {
        int rx_item_id PK
        int rx_id FK
        string drug_name
        string generic_name
        string dosage_form
        string frequency
        int duration_days
    }

    HOME_LAB_ORDER {
        int lab_order_id PK
        int session_id FK
        string lab_panel_name
        datetime scheduled_draw_time
        string sample_collection_address
        string order_status
    }

    CONSULTATION_BILLING {
        int billing_id PK
        int appointment_id FK
        decimal total_amount
        decimal platform_fee
        decimal doctor_payout
        string payment_status
        string transaction_ref
    }

    DOCTOR_RATING_REVIEW {
        int review_id PK
        int appointment_id FK
        int patient_id FK
        int doctor_id FK
        int star_rating
        string written_review
        datetime created_at
    }

    MEDICATION_DELIVERY {
        int delivery_id PK
        int rx_id FK
        string pharmacy_name
        string delivery_address
        string courier_tracking_no
        string delivery_status
    }

    TELEMED_AUDIT_LOG {
        int audit_id PK
        int appointment_id FK
        datetime timestamp
        string action_type
        string performed_by
        string ip_address
    }

    PATIENT_USER ||--o{ VIRTUAL_APPOINTMENT : "books"
    PATIENT_USER ||--o{ DOCTOR_RATING_REVIEW : "writes"

    DOCTOR_PROFILE ||--o{ DOCTOR_AVAILABILITY : "publishes"
    DOCTOR_PROFILE ||--o{ VIRTUAL_APPOINTMENT : "accepts"
    DOCTOR_PROFILE ||--o{ DOCTOR_RATING_REVIEW : "receives"

    DOCTOR_AVAILABILITY ||--o| VIRTUAL_APPOINTMENT : "allocated_to"

    VIRTUAL_APPOINTMENT ||--|| CONSULTATION_SESSION : "initiates"
    VIRTUAL_APPOINTMENT ||--o{ SYMPTOM_ATTACHMENT : "includes"
    VIRTUAL_APPOINTMENT ||--|| CONSULTATION_BILLING : "generates"
    VIRTUAL_APPOINTMENT ||--o| DOCTOR_RATING_REVIEW : "rated_by"
    VIRTUAL_APPOINTMENT ||--o{ TELEMED_AUDIT_LOG : "audited_by"

    CONSULTATION_SESSION ||--|| VIDEO_CALL_LOG : "records"
    CONSULTATION_SESSION ||--o| TELE_PRESCRIPTION : "prescribes"
    CONSULTATION_SESSION ||--o{ HOME_LAB_ORDER : "requests"

    TELE_PRESCRIPTION ||--o{ PRESCRIPTION_ITEM : "contains"
    TELE_PRESCRIPTION ||--o| MEDICATION_DELIVERY : "fulfilled_by"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | PATIENT_USER | Bệnh nhân / Người dùng | Registered patient account holding contact info, demographic data, and emergency contact details. | patient_id, full_name, email, phone | Books Appointments, writes Reviews. |
| 2 | DOCTOR_PROFILE | Bác sĩ Tư vấn | Verified tele-physician profile storing medical license numbers, specialty, experience years, consultation fees, and rating scores. | doctor_id, full_name, medical_license_no, consultation_fee, average_rating | Publishes Availability, accepts Appointments, receives Reviews. |
| 3 | DOCTOR_AVAILABILITY | Lịch Rảnh Bác sĩ | Granular time slots published by doctors specifying open consultation windows. | slot_id, slot_date, start_time, end_time, is_booked | Belongs to Doctor Profile, allocated to Appointment. |
| 4 | VIRTUAL_APPOINTMENT | Lịch hẹn Kham từ xa | Core appointment booking record binding patient, doctor, scheduled time slot, and booking status. | appointment_id, scheduled_time, consultation_type, booking_status | Booked by Patient, initiates Consultation Session, generates Billing. |
| 5 | CONSULTATION_SESSION | Phiên Khám từ xa | Active clinical encounter session capturing actual call start/end times, SOAP notes, and ICD-10 diagnoses. | session_id, actual_start_time, soap_subjective, icd10_diagnosis | Initiated by Appointment, records Video Call Log, prescribes E-Rx. |
| 6 | VIDEO_CALL_LOG | Nhật ký Cuộc gọi Video | Technical WebRTC telemetry record capturing room tokens, call duration, video resolution grades, and connection drops. | call_id, room_session_token, duration_seconds, video_quality_grade | Linked to Consultation Session. |
| 7 | SYMPTOM_ATTACHMENT | Ảnh / File Triệu chứng | Pre-consultation symptom photos, diagnostic lab PDFs, or wearable vital sign images uploaded by the patient. | attachment_id, file_name, file_type, storage_url | Included in Virtual Appointment. |
| 8 | TELE_PRESCRIPTION | Đơn thuốc Điện tử (e-Rx) | Digitally signed electronic prescription issued during a tele-consultation session. | rx_id, issued_at, digital_signature_hash, fulfillment_type | Prescribed in Consultation Session, contains Items, fulfilled by Delivery. |
| 9 | PRESCRIPTION_ITEM | Chi tiết Đơn thuốc | Line items in an e-prescription detailing drug name, generic compound, dosage form, and treatment duration. | rx_item_id, drug_name, dosage_form, frequency, duration_days | Belongs to Tele-Prescription. |
| 10 | HOME_LAB_ORDER | Đơn Xét nghiệm Tai nhà | Requisition order for home phlebotomy blood/urine collection requested during consultation. | lab_order_id, lab_panel_name, scheduled_draw_time, order_status | Requested in Consultation Session. |
| 11 | CONSULTATION_BILLING | Thanh toán Phí Khám | Financial billing record tracking consultation fees, platform commissions, doctor payouts, and payment escrow statuses. | billing_id, total_amount, platform_fee, doctor_payout, payment_status | Generated by Virtual Appointment. |
| 12 | DOCTOR_RATING_REVIEW | Đánh giá & Bình luận | Post-consultation patient review storing 1-5 star ratings and feedback notes for the doctor. | review_id, star_rating, written_review, created_at | Written by Patient, rates Appointment and Doctor Profile. |
| 13 | MEDICATION_DELIVERY | Giao thuốc Tận nhà | Delivery tracking record for home-delivered prescription medications processed by partner pharmacies. | delivery_id, pharmacy_name, delivery_address, courier_tracking_no | Fulfills Tele-Prescription. |
| 14 | TELEMED_AUDIT_LOG | Nhật ký Thao tác Hệ thống | Immutable security log tracking video room access, consent acknowledgments, e-Rx signatures, and file views. | audit_id, timestamp, action_type, performed_by | Audits Virtual Appointment lifecycle. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | PATIENT_USER | one-to-many | VIRTUAL_APPOINTMENT | books | A patient can book multiple virtual appointments over time. |
| 2 | PATIENT_USER | one-to-many | DOCTOR_RATING_REVIEW | writes | A patient can write reviews for completed doctor consultation appointments. |
| 3 | DOCTOR_PROFILE | one-to-many | DOCTOR_AVAILABILITY | publishes | A doctor publishes multiple open time slots on their availability calendar. |
| 4 | DOCTOR_PROFILE | one-to-many | VIRTUAL_APPOINTMENT | accepts | A doctor accepts and conducts multiple virtual patient appointments. |
| 5 | DOCTOR_PROFILE | one-to-many | DOCTOR_RATING_REVIEW | receives | A doctor receives patient star ratings and feedback reviews. |
| 6 | DOCTOR_AVAILABILITY | one-to-one | VIRTUAL_APPOINTMENT | allocated_to | An availability time slot is allocated to at most one confirmed appointment booking. |
| 7 | VIRTUAL_APPOINTMENT | one-to-one | CONSULTATION_SESSION | initiates | A confirmed appointment initiates exactly one live clinical consultation session. |
| 8 | VIRTUAL_APPOINTMENT | one-to-many | SYMPTOM_ATTACHMENT | includes | An appointment can include multiple uploaded pre-consultation symptom photos or files. |
| 9 | VIRTUAL_APPOINTMENT | one-to-one | CONSULTATION_BILLING | generates | An appointment generates exactly one financial consultation billing record. |
| 10 | VIRTUAL_APPOINTMENT | one-to-one | DOCTOR_RATING_REVIEW | rated_by | A completed appointment can be rated by one patient review. |
| 11 | VIRTUAL_APPOINTMENT | one-to-many | TELEMED_AUDIT_LOG | audited_by | An appointment lifecycle maintains an immutable security audit trail. |
| 12 | CONSULTATION_SESSION | one-to-one | VIDEO_CALL_LOG | records | A consultation session records technical telemetry for one WebRTC video call session. |
| 13 | CONSULTATION_SESSION | one-to-one | TELE_PRESCRIPTION | prescribes | A consultation session can result in one digitally signed e-prescription. |
| 14 | CONSULTATION_SESSION | one-to-many | HOME_LAB_ORDER | requests | A consultation session can request one or more home laboratory collection orders. |
| 15 | TELE_PRESCRIPTION | one-to-many | PRESCRIPTION_ITEM | contains | An e-prescription contains one or more prescribed medication line items. |
| 16 | TELE_PRESCRIPTION | one-to-one | MEDICATION_DELIVERY | fulfilled_by | An e-prescription designated for home delivery is fulfilled by one medication delivery tracker. |
