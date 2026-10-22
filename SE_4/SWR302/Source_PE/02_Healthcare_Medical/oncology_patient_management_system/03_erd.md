# Conceptual ERD — Oncology Patient Management System

## Mermaid Code

```mermaid
erDiagram
    ONCOLOGY_PATIENT {
        int patient_id PK
        string patient_code
        string national_id
        string full_name
        date date_of_birth
        string gender
        decimal body_surface_area_m2
        int ecog_status
        string patient_status
    }

    CANCER_DIAGNOSIS_STAGING {
        int diagnosis_id PK
        int patient_id FK
        string primary_site_icd_o
        string histology_type
        string t_stage
        string n_stage
        string m_stage
        string ajcc_overall_stage
        date diagnosed_date
    }

    MDT_TUMOR_BOARD_DECISION {
        int mdt_id PK
        int patient_id FK
        datetime meeting_date
        string consensus_recommendation
        int lead_oncologist_id FK
        string mdt_status
    }

    CHEMOTHERAPY_REGIMEN_MASTER {
        int regimen_id PK
        string regimen_code
        string regimen_name
        string cancer_type
        int total_cycles_planned
        int cycle_interval_days
    }

    PATIENT_CHEMO_CYCLE_LOG {
        int cycle_log_id PK
        int patient_id FK
        int regimen_id FK
        int cycle_number
        date planned_date
        date actual_given_date
        decimal dose_reduction_pct
        string cycle_status
    }

    CHEMO_MEDICATION_ORDER {
        int order_id PK
        int cycle_log_id FK
        string drug_name
        decimal calculated_dose_mg
        string route_of_admin
        int infusion_duration_mins
        string order_status
    }

    CHEMO_INFUSION_VERIFICATION {
        int infusion_id PK
        int order_id FK
        string chair_number
        int primary_nurse_id FK
        int witness_nurse_id FK
        datetime start_time
        datetime end_time
        string infusion_status
    }

    RADIOTHERAPY_TREATMENT_PLAN {
        int rt_plan_id PK
        int patient_id FK
        string target_volume_ctv
        decimal total_prescribed_dose_gy
        int fraction_count
        decimal dose_per_fraction_gy
        string rt_status
    }

    RT_FRACTIONATION_SESSION {
        int session_id PK
        int rt_plan_id FK
        int fraction_number
        decimal delivered_dose_gy
        string linac_machine_code
        int linac_operator_id FK
        datetime delivered_at
    }

    SOMATIC_BIOMARKER_PROFILE {
        int biomarker_id PK
        int patient_id FK
        string gene_mutation_name
        string test_method
        decimal pdl1_tps_percentage
        string targeted_drug_matched
        datetime tested_at
    }

    TOXICITY_ADVERSE_EVENT_LOG {
        int toxicity_id PK
        int patient_id FK
        int cycle_log_id FK
        string adverse_event_name
        int ctcae_grade_1_to_5
        string action_taken
        datetime reported_at
    }

    SURVIVORSHIP_CARE_PLAN {
        int survivorship_id PK
        int patient_id FK
        date end_of_treatment_date
        string recurrence_risk_level
        string follow_up_schedule_summary
        string status
    }

    ONCOLOGY_PATIENT ||--o{ CANCER_DIAGNOSIS_STAGING : "diagnosed_with"
    ONCOLOGY_PATIENT ||--o{ MDT_TUMOR_BOARD_DECISION : "evaluated_in"
    ONCOLOGY_PATIENT ||--o{ PATIENT_CHEMO_CYCLE_LOG : "executes_cycle"
    ONCOLOGY_PATIENT ||--o{ RADIOTHERAPY_TREATMENT_PLAN : "planned_for"
    ONCOLOGY_PATIENT ||--o{ SOMATIC_BIOMARKER_PROFILE : "profiled_for"
    ONCOLOGY_PATIENT ||--o{ TOXICITY_ADVERSE_EVENT_LOG : "logs_toxicity"
    ONCOLOGY_PATIENT ||--o{ SURVIVORSHIP_CARE_PLAN : "monitored_in"
    CHEMOTHERAPY_REGIMEN_MASTER ||--o{ PATIENT_CHEMO_CYCLE_LOG : "enrolled_in"
    PATIENT_CHEMO_CYCLE_LOG ||--o{ CHEMO_MEDICATION_ORDER : "prescribes_drug"
    CHEMO_MEDICATION_ORDER ||--o{ CHEMO_INFUSION_VERIFICATION : "verified_by"
    RADIOTHERAPY_TREATMENT_PLAN ||--o{ RT_FRACTIONATION_SESSION : "delivers_fraction"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | ONCOLOGY_PATIENT | Bệnh nhân Ung thư Master | Master oncology patient record storing patient code, national ID, full name, DOB, gender, BSA m², ECOG status (0-4), and status. | patient_id, patient_code, national_id, full_name, dob, gender, body_surface_area_m2, ecog_status, patient_status | Has DIAGNOSIS, MDT, CHEMO_CYCLE, RT_PLAN, BIOMARKER, TOXICITY, SURVIVORSHIP. |
| 2 | CANCER_DIAGNOSIS_STAGING | Chẩn đoán & Phân loại TNM | Cancer diagnosis and staging record storing primary site ICD-O-3, histology type, T stage, N stage, M stage, AJCC overall stage, and date. | diagnosis_id, patient_id, primary_site_icd_o, histology_type, t_stage, n_stage, m_stage, ajcc_overall_stage, diagnosed_date | Belongs to ONCOLOGY_PATIENT. |
| 3 | MDT_TUMOR_BOARD_DECISION | Quyết định Hội chẩn MDT | Multidisciplinary Tumor Board meeting decision record tracking meeting date, consensus recommendation, lead oncologist ID, and status. | mdt_id, patient_id, meeting_date, consensus_recommendation, lead_oncologist_id, mdt_status | Belongs to ONCOLOGY_PATIENT. |
| 4 | CHEMOTHERAPY_REGIMEN_MASTER | Danh mục Phác đồ Hóa trị | Chemotherapy protocol master record storing regimen code, regimen name (FOLFOX, AC-T), cancer type, total cycles planned, and cycle interval. | regimen_id, regimen_code, regimen_name, cancer_type, total_cycles_planned, cycle_interval_days | Has PATIENT_CHEMO_CYCLE_LOG. |
| 5 | PATIENT_CHEMO_CYCLE_LOG | Chu kỳ Hóa trị Bệnh nhân | Patient chemotherapy cycle log tracking patient ID, regimen ID, cycle number, planned date, actual date, dose reduction %, and status. | cycle_log_id, patient_id, regimen_id, cycle_number, planned_date, actual_given_date, dose_reduction_pct, cycle_status | Belongs to ONCOLOGY_PATIENT and CHEMOTHERAPY_REGIMEN_MASTER; Has CHEMO_MEDICATION_ORDER. |
| 6 | CHEMO_MEDICATION_ORDER | Chỉ định Thuốc Hóa trị | Specific chemotherapy drug order tracking cycle log ID, drug name (Oxaliplatin, 5-FU), BSA-calculated dose mg, route, duration mins, and status. | order_id, cycle_log_id, drug_name, calculated_dose_mg, route_of_admin, infusion_duration_mins, order_status | Belongs to PATIENT_CHEMO_CYCLE_LOG; Has CHEMO_INFUSION_VERIFICATION. |
| 7 | CHEMO_INFUSION_VERIFICATION | Xác nhận An toàn Truyền Hóa chất | Independent double-witness IV infusion verification record tracking chair number, primary nurse ID, witness nurse ID, start/end times, and status. | infusion_id, order_id, chair_number, primary_nurse_id, witness_nurse_id, start_time, end_time, infusion_status | Belongs to CHEMO_MEDICATION_ORDER. |
| 8 | RADIOTHERAPY_TREATMENT_PLAN | Kế hoạch Xạ trị (RT Plan) | Radiotherapy treatment plan record tracking target volume CTV/PTV, total prescribed Gray dose (Gy), fraction count, dose/fraction, and status. | rt_plan_id, patient_id, target_volume_ctv, total_prescribed_dose_gy, fraction_count, dose_per_fraction_gy, rt_status | Belongs to ONCOLOGY_PATIENT; Has RT_FRACTIONATION_SESSION. |
| 9 | RT_FRACTIONATION_SESSION | Buổi Phân liều Xạ trị (LINAC) | Linear accelerator radiation fraction session record tracking fraction number, delivered dose Gy, LINAC machine code, operator ID, and timestamp. | session_id, rt_plan_id, fraction_number, delivered_dose_gy, linac_machine_code, linac_operator_id, delivered_at | Belongs to RADIOTHERAPY_TREATMENT_PLAN. |
| 10 | SOMATIC_BIOMARKER_PROFILE | Chỉ dấu Sinh học Phân tử (NGS) | Somatic tumor biomarker profile record tracking gene mutation (EGFR, ALK, BRAF), test method, PD-L1 TPS %, matched targeted drug, and date. | biomarker_id, patient_id, gene_mutation_name, test_method, pdl1_tps_percentage, targeted_drug_matched, tested_at | Belongs to ONCOLOGY_PATIENT. |
| 11 | TOXICITY_ADVERSE_EVENT_LOG | Nhật ký Độc tính CTCAE | Chemotherapy and radiotherapy adverse event log tracking event name (Neutropenia, Mucositis), NCI CTCAE grade 1-5, action taken, and timestamp. | toxicity_id, patient_id, cycle_log_id, adverse_event_name, ctcae_grade_1_to_5, action_taken, reported_at | Belongs to ONCOLOGY_PATIENT and PATIENT_CHEMO_CYCLE_LOG. |
| 12 | SURVIVORSHIP_CARE_PLAN | Kế hoạch Theo dõi Tái phát | Post-treatment survivorship care plan record tracking end of treatment date, recurrence risk level, follow-up schedule summary, and status. | survivorship_id, patient_id, end_of_treatment_date, recurrence_risk_level, follow_up_schedule_summary, status | Belongs to ONCOLOGY_PATIENT. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | ONCOLOGY_PATIENT | one-to-many | CANCER_DIAGNOSIS_STAGING | diagnosed_with | One oncology patient is diagnosed with primary cancer sites and AJCC TNM stages. |
| 2 | ONCOLOGY_PATIENT | one-to-many | MDT_TUMOR_BOARD_DECISION | evaluated_in | One oncology patient is evaluated in Multidisciplinary Tumor Board (MDT) reviews. |
| 3 | ONCOLOGY_PATIENT | one-to-many | PATIENT_CHEMO_CYCLE_LOG | executes_cycle | One oncology patient executes multiple chemotherapy treatment cycles over time. |
| 4 | ONCOLOGY_PATIENT | one-to-many | RADIOTHERAPY_TREATMENT_PLAN | planned_for | One oncology patient has planned radiotherapy treatment courses (external beam RT). |
| 5 | ONCOLOGY_PATIENT | one-to-many | SOMATIC_BIOMARKER_PROFILE | profiled_for | One oncology patient is profiled for somatic genomic mutations and PD-L1 expression. |
| 6 | ONCOLOGY_PATIENT | one-to-many | TOXICITY_ADVERSE_EVENT_LOG | logs_toxicity | One oncology patient logs NCI CTCAE v5.0 treatment toxicity and adverse events. |
| 7 | ONCOLOGY_PATIENT | one-to-one | SURVIVORSHIP_CARE_PLAN | monitored_in | One oncology patient is monitored in a post-treatment Survivorship Care Plan. |
| 8 | CHEMOTHERAPY_REGIMEN_MASTER | one-to-many | PATIENT_CHEMO_CYCLE_LOG | enrolled_in | One chemotherapy master protocol (FOLFOX, AC-T) enrolls multiple patient chemo cycles. |
| 9 | PATIENT_CHEMO_CYCLE_LOG | one-to-many | CHEMO_MEDICATION_ORDER | prescribes_drug | One chemo cycle log prescribes specific BSA-calculated IV chemotherapy drug orders. |
| 10 | CHEMO_MEDICATION_ORDER | one-to-one | CHEMO_INFUSION_VERIFICATION | verified_by | One chemotherapy drug order is verified by independent double-witness oncology nurses. |
| 11 | RADIOTHERAPY_TREATMENT_PLAN | one-to-many | RT_FRACTIONATION_SESSION | delivers_fraction | One radiotherapy plan delivers multiple daily radiation fraction sessions on LINAC. |
