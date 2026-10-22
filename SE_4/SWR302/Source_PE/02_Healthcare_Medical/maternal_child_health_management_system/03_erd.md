# Conceptual ERD — Maternal & Child Health Management System

## Mermaid Code

```mermaid
erDiagram
    MATERNAL_MOTHER {
        int mother_id PK
        string mother_code
        string full_name
        date date_of_birth
        string blood_type
        string phone_number
        string emergency_contact
        string mother_status
    }

    PREGNANCY_EPISODE {
        int pregnancy_id PK
        int mother_id FK
        date lmp_date
        date edd_date
        int gravida_count
        int para_count
        boolean high_risk_flag
        string pregnancy_status
    }

    ANC_VISIT_RECORD {
        int anc_id PK
        int pregnancy_id FK
        int visit_number
        decimal gestational_age_weeks
        decimal weight_kg
        int bp_systolic
        int bp_diastolic
        decimal fundal_height_cm
        int fetal_heart_rate_bpm
        date visit_date
    }

    DELIVERY_RECORD {
        int delivery_id PK
        int pregnancy_id FK
        datetime delivery_datetime
        string delivery_mode
        decimal labor_duration_hours
        int maternal_blood_loss_ml
        int attending_midwife_id FK
        string delivery_status
    }

    DIGITAL_PARTOGRAPH {
        int partograph_id PK
        int delivery_id FK
        datetime time_logged
        decimal cervical_dilation_cm
        string fetal_head_descent
        int contraction_frequency_10min
        string amniotic_fluid_status
    }

    CHILD_INFANT {
        int child_id PK
        int mother_id FK
        int delivery_id FK
        string birth_certificate_number
        string child_name
        string gender
        datetime birth_datetime
        int birth_weight_grams
        decimal birth_length_cm
    }

    APGAR_SCORE_LOG {
        int apgar_id PK
        int child_id FK
        int time_interval_min
        int appearance_score
        int pulse_score
        int grimace_score
        int activity_score
        int respiration_score
        int total_apgar_score
    }

    NEWBORN_SCREENING_LOG {
        int screening_id PK
        int child_id FK
        string g6pd_status
        string cht_status
        string hearing_test_result
        datetime screened_at
    }

    CHILD_GROWTH_RECORD {
        int growth_id PK
        int child_id FK
        int age_months
        decimal weight_kg
        decimal height_cm
        decimal muac_cm
        string who_zscore_category
        date recorded_at
    }

    VACCINATION_LOG {
        int vaccine_log_id PK
        int child_id FK
        string vaccine_name
        int dose_number
        date target_due_date
        date administered_date
        string batch_lot_number
        string vaccine_status
    }

    POSTNATAL_CARE_PNC {
        int pnc_id PK
        int mother_id FK
        int visit_day_postpartum
        string maternal_fundal_involution
        string breast_feeding_status
        int depression_phq9_score
        date pnc_date
    }

    MCH_REMINDER_NOTIFICATION {
        int notification_id PK
        int mother_id FK
        string reminder_type
        date scheduled_send_date
        string delivery_channel
        string delivery_status
    }

    MATERNAL_MOTHER ||--o{ PREGNANCY_EPISODE : "undergoes"
    MATERNAL_MOTHER ||--o{ CHILD_INFANT : "mother_of"
    MATERNAL_MOTHER ||--o{ POSTNATAL_CARE_PNC : "receives_pnc"
    MATERNAL_MOTHER ||--o{ MCH_REMINDER_NOTIFICATION : "notified_via"
    PREGNANCY_EPISODE ||--o{ ANC_VISIT_RECORD : "monitored_in"
    PREGNANCY_EPISODE ||--o{ DELIVERY_RECORD : "delivers_in"
    DELIVERY_RECORD ||--o{ DIGITAL_PARTOGRAPH : "charts"
    DELIVERY_RECORD ||--o{ CHILD_INFANT : "results_in"
    CHILD_INFANT ||--o{ APGAR_SCORE_LOG : "evaluated_by"
    CHILD_INFANT ||--o{ NEWBORN_SCREENING_LOG : "screened_in"
    CHILD_INFANT ||--o{ CHILD_GROWTH_RECORD : "tracks"
    CHILD_INFANT ||--o{ VACCINATION_LOG : "vaccinated_in"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | MATERNAL_MOTHER | Người Mẹ Master Profile | Master maternal profile storing mother code, full name, DOB, blood type (ABO/Rh), phone, and emergency contact. | mother_id, mother_code, full_name, dob, blood_type, phone_number, mother_status | Has many PREGNANCY_EPISODE, CHILD_INFANT, POSTNATAL_CARE_PNC, MCH_REMINDER_NOTIFICATION, etc. |
| 2 | PREGNANCY_EPISODE | Đợt Thai kỳ (Pregnancy) | Specific pregnancy episode record storing Last Menstrual Period (LMP), Estimated Due Date (EDD), GPAL counts, and high-risk flags. | pregnancy_id, mother_id, lmp_date, edd_date, gravida_count, para_count, high_risk_flag, pregnancy_status | Belongs to MATERNAL_MOTHER; Has many ANC_VISIT_RECORD and DELIVERY_RECORD entities. |
| 3 | ANC_VISIT_RECORD | Phám Thăm Khám Thai (ANC) | Antenatal care visit record logging gestational age weeks, maternal weight, BP systolic/diastolic, fundal height, and fetal heart rate. | anc_id, pregnancy_id, visit_number, gestational_age_weeks, weight_kg, bp_systolic, bp_diastolic, visit_date | Belongs to PREGNANCY_EPISODE. |
| 4 | DELIVERY_RECORD | Nhật ký Sinh nở (Delivery) | Labor and delivery operation record logging delivery datetime, delivery mode (Normal, C-Section), labor duration, blood loss, and midwife ID. | delivery_id, pregnancy_id, delivery_datetime, delivery_mode, labor_duration_hours, maternal_blood_loss_ml, delivery_status | Belongs to PREGNANCY_EPISODE; Has many DIGITAL_PARTOGRAPH and CHILD_INFANT entities. |
| 5 | DIGITAL_PARTOGRAPH | Biểu đồ Chuyển dạ Partograph | Digital WHO Partograph time entry tracking cervical dilation (cm), fetal head descent, contraction frequency per 10 mins, and amniotic fluid. | partograph_id, delivery_id, time_logged, cervical_dilation_cm, fetal_head_descent, contraction_frequency_10min | Belongs to DELIVERY_RECORD. |
| 6 | CHILD_INFANT | Trẻ em / Trẻ sơ sinh Profile | Master child record storing birth certificate number, child name, gender, birth datetime, birth weight, birth length, linked to mother. | child_id, mother_id, delivery_id, birth_certificate_number, child_name, gender, birth_weight_grams, birth_length_cm | Belongs to MATERNAL_MOTHER and DELIVERY_RECORD; Has APGAR_SCORE_LOG, CHILD_GROWTH_RECORD, VACCINATION_LOG, etc. |
| 7 | APGAR_SCORE_LOG | Chỉ số APGAR Sơ sinh | Newborn APGAR score evaluation record logging Appearance, Pulse, Grimace, Activity, and Respiration sub-scores at 1, 5, and 10 minutes. | apgar_id, child_id, time_interval_min, appearance_score, pulse_score, grimace_score, activity_score, total_apgar_score | Belongs to CHILD_INFANT. |
| 8 | NEWBORN_SCREENING_LOG | Sàng lọc Sơ sinh (Metabolic) | Newborn screening record logging G6PD enzyme deficiency, Congenital Hypothyroidism (CHT), and OAE hearing test results. | screening_id, child_id, g6pd_status, cht_status, hearing_test_result, screened_at | Belongs to CHILD_INFANT. |
| 9 | CHILD_GROWTH_RECORD | Nhật ký Phát triển WHO | Child physical growth record tracking age months, weight kg, height cm, MUAC cm, and WHO Z-score categories (WAZ, HAZ, WHZ). | growth_id, child_id, age_months, weight_kg, height_cm, muac_cm, who_zscore_category, recorded_at | Belongs to CHILD_INFANT. |
| 10 | VACCINATION_LOG | Nhật ký Tiêm chủng Trẻ em | Childhood vaccine log tracking vaccine name, dose number, target due date, administration date, batch lot number, and status. | vaccine_log_id, child_id, vaccine_name, dose_number, target_due_date, administered_date, batch_lot_number, vaccine_status | Belongs to CHILD_INFANT. |
| 11 | POSTNATAL_CARE_PNC | Thăm khám Sau sinh (PNC) | Maternal postpartum care record tracking visit day (Day 3, 7, 42), fundal involution, breast feeding status, and PHQ-9 depression score. | pnc_id, mother_id, visit_day_postpartum, maternal_fundal_involution, breast_feeding_status, depression_phq9_score, pnc_date | Belongs to MATERNAL_MOTHER. |
| 12 | MCH_REMINDER_NOTIFICATION | Nhắc lịch SMS / Zalo | Automated reminder notification log tracking reminder type (ANC, Vaccine, PNC), scheduled date, delivery channel, and delivery status. | notification_id, mother_id, reminder_type, scheduled_send_date, delivery_channel, delivery_status | Belongs to MATERNAL_MOTHER. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | MATERNAL_MOTHER | one-to-many | PREGNANCY_EPISODE | undergoes | One mother undergoes one or more pregnancy episodes over time. |
| 2 | MATERNAL_MOTHER | one-to-many | CHILD_INFANT | mother_of | One mother is the biological parent of one or more children. |
| 3 | MATERNAL_MOTHER | one-to-many | POSTNATAL_CARE_PNC | receives_pnc | One mother receives postnatal care (PNC) postpartum follow-up visits. |
| 4 | MATERNAL_MOTHER | one-to-many | MCH_REMINDER_NOTIFICATION | notified_via | One mother is notified via SMS/Zalo for ANC and vaccine appointment reminders. |
| 5 | PREGNANCY_EPISODE | one-to-many | ANC_VISIT_RECORD | monitored_in | One pregnancy episode is monitored in multiple Antenatal Care (ANC) visits. |
| 6 | PREGNANCY_EPISODE | one-to-one | DELIVERY_RECORD | delivers_in | One pregnancy episode concludes in exactly one labor and delivery record. |
| 7 | DELIVERY_RECORD | one-to-many | DIGITAL_PARTOGRAPH | charts | One delivery record charts labor progress entries on a WHO digital partograph. |
| 8 | DELIVERY_RECORD | one-to-many | CHILD_INFANT | results_in | One delivery record results in the birth of one or more infants (twins). |
| 9 | CHILD_INFANT | one-to-many | APGAR_SCORE_LOG | evaluated_by | One infant is evaluated by APGAR scores at 1, 5, and 10 minutes post-birth. |
| 10 | CHILD_INFANT | one-to-many | NEWBORN_SCREENING_LOG | screened_in | One infant undergoes metabolic and hearing newborn screening tests. |
| 11 | CHILD_INFANT | one-to-many | CHILD_GROWTH_RECORD | tracks | One child tracks physical growth measurements against WHO Z-score curves up to 5 years. |
| 12 | CHILD_INFANT | one-to-many | VACCINATION_LOG | vaccinated_in | One child is vaccinated in national expanded immunization schedule doses. |
