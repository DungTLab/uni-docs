# Conceptual ERD — Genomics & Biobank Management System

## Mermaid Code

```mermaid
erDiagram
    BIOSPECIMEN_DONOR {
        int donor_id PK
        string donor_code
        string national_id
        date date_of_birth
        string gender
        string ethnicity
        string primary_clinical_diagnosis
        string donor_status
    }

    DONOR_INFORMED_CONSENT {
        int consent_id PK
        int donor_id FK
        string consent_type
        boolean broad_consent_flag
        date withdrawal_date
        datetime signed_at
        string consent_status
    }

    BIOSPECIMEN_SAMPLE {
        int sample_id PK
        string sample_barcode
        int donor_id FK
        string tissue_type
        datetime collection_datetime
        decimal initial_volume_ml
        string sample_status
    }

    CRYO_VIAL_ALIQUOT {
        int aliquot_id PK
        int sample_id FK
        string 2d_matrix_barcode
        string derivative_type
        decimal concentration_ng_ul
        decimal rin_quality_score
        decimal current_volume_ul
        string aliquot_status
    }

    CRYO_FREEZER_STORAGE_LOCATION {
        int location_id PK
        int aliquot_id FK
        string freezer_unit_code
        string rack_number
        string box_number
        string well_position
        decimal target_temp_c
        string location_status
    }

    FREEZER_TELEMETRY_LOG {
        int telemetry_id PK
        string freezer_unit_code
        decimal measured_temp_c
        decimal liquid_nitrogen_level_pct
        datetime logged_at
        boolean alarm_flag
    }

    NGS_SEQUENCING_RUN {
        int run_id PK
        int aliquot_id FK
        string sequencer_platform
        string run_folder_name
        decimal total_reads_millions
        decimal q30_percentage
        datetime run_datetime
        string run_status
    }

    GENOMIC_VARIANT_CALL {
        int variant_id PK
        int run_id FK
        string chromosome
        int position
        string ref_allele
        string alt_allele
        string gene_symbol
        decimal vcf_quality
    }

    CLINICAL_VARIANT_ANNOTATION {
        int annotation_id PK
        int variant_id FK
        string acmg_classification
        string clinvar_id
        string hgvs_c_notation
        string hgvs_p_notation
        int annotated_by_user_id FK
        datetime annotated_at
    }

    PHARMACOGENOMIC_MATCH {
        int pgx_id PK
        int donor_id FK
        string gene_symbol
        string diplotype_phenotype
        string cpic_drug_name
        string clinical_recommendation
    }

    SPECIMEN_REQUISITION_ORDER {
        int order_id PK
        int researcher_user_id FK
        string irb_approval_num
        int requested_sample_count
        string purpose_summary
        string order_status
        datetime requested_at
    }

    ISO_20387_BIOBANK_AUDIT {
        int audit_id PK
        date audit_date
        int total_samples_stored
        int freeze_thaw_cycle_count
        decimal storage_compliance_pct
        int audited_by_user_id FK
    }

    BIOSPECIMEN_DONOR ||--o{ DONOR_INFORMED_CONSENT : "gives_consent"
    BIOSPECIMEN_DONOR ||--o{ BIOSPECIMEN_SAMPLE : "provides"
    BIOSPECIMEN_DONOR ||--o{ PHARMACOGENOMIC_MATCH : "matched_for"
    BIOSPECIMEN_SAMPLE ||--o{ CRYO_VIAL_ALIQUOT : "extracted_into"
    CRYO_VIAL_ALIQUOT ||--o{ CRYO_FREEZER_STORAGE_LOCATION : "stored_at"
    CRYO_VIAL_ALIQUOT ||--o{ NGS_SEQUENCING_RUN : "sequenced_in"
    NGS_SEQUENCING_RUN ||--o{ GENOMIC_VARIANT_CALL : "calls_variant"
    GENOMIC_VARIANT_CALL ||--o{ CLINICAL_VARIANT_ANNOTATION : "annotated_in"
    SPECIMEN_REQUISITION_ORDER ||--o{ CRYO_VIAL_ALIQUOT : "requisitions"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | BIOSPECIMEN_DONOR | Người Hiến Mẫu Sinh học | Master biobank donor record storing donor code, national ID, DOB, gender, ethnicity, primary clinical diagnosis, and status. | donor_id, donor_code, national_id, dob, gender, ethnicity, primary_clinical_diagnosis, donor_status | Has DONOR_INFORMED_CONSENT, BIOSPECIMEN_SAMPLE, PHARMACOGENOMIC_MATCH. |
| 2 | DONOR_INFORMED_CONSENT | Hồ sơ Đồng ý Hiến Mẫu (Consent) | Donor informed consent agreement record tracking consent type, broad consent flag, withdrawal date, signed timestamp, and status. | consent_id, donor_id, consent_type, broad_consent_flag, withdrawal_date, signed_at, consent_status | Belongs to BIOSPECIMEN_DONOR. |
| 3 | BIOSPECIMEN_SAMPLE | Mẫu Sinh học Gốc (Sample) | Primary biospecimen sample record tracking sample barcode, donor ID, tissue type (Blood, Tissue, FFPE, Plasma), collection datetime, volume, and status. | sample_id, sample_barcode, donor_id, tissue_type, collection_datetime, initial_volume_ml, sample_status | Belongs to BIOSPECIMEN_DONOR; Has CRYO_VIAL_ALIQUOT. |
| 4 | CRYO_VIAL_ALIQUOT | Mẫu Chia Nhỏ (Aliquot 2D) | Biospecimen derivative aliquot record tracking 2D matrix barcode, derivative type (DNA, RNA, Buffy Coat), concentration ng/μL, RIN score, current volume, and status. | aliquot_id, sample_id, 2d_matrix_barcode, derivative_type, concentration_ng_ul, rin_quality_score, current_volume_ul, aliquot_status | Belongs to BIOSPECIMEN_SAMPLE; Has STORAGE_LOCATION, NGS_RUN; Requisitioned in ORDER. |
| 5 | CRYO_FREEZER_STORAGE_LOCATION | Vị trí Tủ Đông Siêu Âm | 4-tier storage grid coordinate record tracking freezer unit code, rack number, box number, well position (A1-H12), target temperature °C, and status. | location_id, aliquot_id, freezer_unit_code, rack_number, box_number, well_position, target_temp_c, location_status | Belongs to CRYO_VIAL_ALIQUOT. |
| 6 | FREEZER_TELEMETRY_LOG | Nhật ký Nhiệt độ Tủ đông IoT | IoT telemetry log recording freezer unit code, measured temperature °C (-80°C / -196°C), liquid nitrogen level %, logged timestamp, and alarm flag. | telemetry_id, freezer_unit_code, measured_temp_c, liquid_nitrogen_level_pct, logged_at, alarm_flag | Standalone IoT freezer telemetry record. |
| 7 | NGS_SEQUENCING_RUN | Lượt Giải Trình Tự Gen NGS | Next-Generation Sequencing run record tracking aliquot ID, sequencer platform, run folder name, total reads, Q30 score %, run datetime, and status. | run_id, aliquot_id, sequencer_platform, run_folder_name, total_reads_millions, q30_percentage, run_datetime, run_status | Belongs to CRYO_VIAL_ALIQUOT; Has GENOMIC_VARIANT_CALL. |
| 8 | GENOMIC_VARIANT_CALL | Biến thể Gen (Variant Call) | Indexed genomic variant call record tracking chromosome, position, ref/alt alleles, gene symbol, VCF quality score, and run ID. | variant_id, run_id, chromosome, position, ref_allele, alt_allele, gene_symbol, vcf_quality | Belongs to NGS_SEQUENCING_RUN; Has CLINICAL_VARIANT_ANNOTATION. |
| 9 | CLINICAL_VARIANT_ANNOTATION | Chú giải Biến thể Lâm sàng | Clinical variant annotation record tracking ACMG pathogenicity classification (5 tiers), ClinVar ID, HGVS c./p. notations, annotator user ID, and timestamp. | annotation_id, variant_id, acmg_classification, clinvar_id, hgvs_c_notation, hgvs_p_notation, annotated_by_user_id, annotated_at | Belongs to GENOMIC_VARIANT_CALL. |
| 10 | PHARMACOGENOMIC_MATCH | Khớp Dược lý Truyền nhiễm (PGx) | Pharmacogenomic match record tracking donor ID, gene symbol (CYP2D6, CYP2C19), diplotype phenotype, CPIC drug name, and clinical recommendation. | pgx_id, donor_id, gene_symbol, diplotype_phenotype, cpic_drug_name, clinical_recommendation | Belongs to BIOSPECIMEN_DONOR. |
| 11 | SPECIMEN_REQUISITION_ORDER | Đơn Yêu cầu Mẫu Nghiên cứu | Research specimen requisition order tracking researcher user ID, IRB approval number, requested sample count, purpose summary, request timestamp, and status. | order_id, researcher_user_id, irb_approval_num, requested_sample_count, purpose_summary, order_status, requested_at | Requisitions CRYO_VIAL_ALIQUOT. |
| 12 | ISO_20387_BIOBANK_AUDIT | Báo cáo Kiểm toán ISO 20387 | ISO 20387 biobank quality audit record tracking audit date, total samples stored, freeze-thaw cycle count, storage compliance %, and auditor user ID. | audit_id, audit_date, total_samples_stored, freeze_thaw_cycle_count, storage_compliance_pct, audited_by_user_id | Standalone biobank quality audit record. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | BIOSPECIMEN_DONOR | one-to-many | DONOR_INFORMED_CONSENT | gives_consent | One biobank donor gives one or more informed consent agreements over time. |
| 2 | BIOSPECIMEN_DONOR | one-to-many | BIOSPECIMEN_SAMPLE | provides | One biobank donor provides primary biospecimen samples (Blood, Tissue, FFPE). |
| 3 | BIOSPECIMEN_DONOR | one-to-many | PHARMACOGENOMIC_MATCH | matched_for | One biobank donor is matched for pharmacogenomic CPIC drug-gene recommendations. |
| 4 | BIOSPECIMEN_SAMPLE | one-to-many | CRYO_VIAL_ALIQUOT | extracted_into | One primary biospecimen sample is extracted and aliquoted into multiple 2D matrix cryo-vials. |
| 5 | CRYO_VIAL_ALIQUOT | one-to-one | CRYO_FREEZER_STORAGE_LOCATION | stored_at | One cryo-vial aliquot is mapped to an exact 4-tier grid location (Freezer/Rack/Box/Well). |
| 6 | CRYO_VIAL_ALIQUOT | one-to-many | NGS_SEQUENCING_RUN | sequenced_in | One DNA/RNA cryo-vial aliquot is sequenced in NGS genomic sequencing runs. |
| 7 | NGS_SEQUENCING_RUN | one-to-many | GENOMIC_VARIANT_CALL | calls_variant | One NGS sequencing run calls multiple genomic variants (SNVs and Indels). |
| 8 | GENOMIC_VARIANT_CALL | one-to-many | CLINICAL_VARIANT_ANNOTATION | annotated_in | One genomic variant call is annotated with ACMG pathogenicity classifications. |
| 9 | SPECIMEN_REQUISITION_ORDER | many-to-many | CRYO_VIAL_ALIQUOT | requisitions | One research requisition order requisitions multiple cryo-vial aliquots. |
