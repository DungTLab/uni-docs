# Conceptual ERD — AI-Assisted Diagnosis Support System

## Mermaid Code

```mermaid
erDiagram
    DIAGNOSTIC_IMAGING_STUDY {
        int study_id PK
        string patient_emr_id
        string dicom_study_uid
        string modality_type
        string body_part
        datetime study_date
        int total_slice_count
        string pacs_storage_url
    }

    AI_DIAGNOSTIC_MODEL {
        int model_id PK
        string model_name
        string version_tag
        string architecture_type
        string fda_samd_clearance_number
        decimal accuracy_auc_roc
        datetime deployment_date
        string model_status
    }

    AI_INFERENCE_EXECUTION {
        int execution_id PK
        int study_id FK
        int model_id FK
        datetime execution_timestamp
        int processing_time_ms
        string gpu_node_id
        string execution_status
    }

    AI_LESION_DETECTION {
        int lesion_id PK
        int execution_id FK
        string lesion_label
        string bounding_box_coordinates_json
        decimal confidence_score_pct
        decimal volume_mm3
        decimal hounsfield_unit_mean
        string heatmap_overlay_url
    }

    DIFFERENTIAL_DIAGNOSIS_PREDICTION {
        int prediction_id PK
        int execution_id FK
        string snomed_concept_code
        string icd10_code
        string condition_name
        decimal probability_score_pct
        string clinical_severity_tier
    }

    EXPLAINABLE_AI_MAP {
        int xai_id PK
        int execution_id FK
        string gradcam_heatmap_hash
        string saliency_map_url
        string top_attributing_features_json
        datetime generated_at
    }

    PHYSICIAN_DIAGNOSTIC_VALIDATION {
        int validation_id PK
        int execution_id FK
        int physician_id FK
        string validation_decision
        string overrule_reason
        string concordance_status
        datetime validated_at
    }

    CRITICAL_FINDING_ALERT {
        int alert_id PK
        int execution_id FK
        string alert_severity
        string critical_finding_type
        datetime escalated_at
        datetime physician_acknowledged_at
        string alert_status
    }

    STRUCTURED_RADIOLOGY_REPORT {
        int report_id PK
        int study_id FK
        int validation_id FK
        string radlex_birads_category
        string findings_text
        string impression_text
        datetime signed_at
    }

    FEDERATED_MODEL_WEIGHT_UPDATE {
        int federated_update_id PK
        int model_id FK
        string hospital_node_code
        string weight_delta_hash
        decimal differential_privacy_epsilon
        datetime updated_at
    }

    AI_AUDIT_COMPLIANCE_LOG {
        int audit_id PK
        int execution_id FK
        string fhir_audit_event_id
        string samd_regulatory_version
        datetime logged_at
        string compliance_status
    }

    DIAGNOSTIC_IMAGING_STUDY ||--o{ AI_INFERENCE_EXECUTION : "runs_inference"
    DIAGNOSTIC_IMAGING_STUDY ||--o{ STRUCTURED_RADIOLOGY_REPORT : "generates_report"
    AI_DIAGNOSTIC_MODEL ||--o{ AI_INFERENCE_EXECUTION : "executes_model"
    AI_DIAGNOSTIC_MODEL ||--o{ FEDERATED_MODEL_WEIGHT_UPDATE : "receives_weight_updates"
    AI_INFERENCE_EXECUTION ||--o{ AI_LESION_DETECTION : "detects_lesions"
    AI_INFERENCE_EXECUTION ||--o{ DIFFERENTIAL_DIAGNOSIS_PREDICTION : "predicts_diagnoses"
    AI_INFERENCE_EXECUTION ||--o{ EXPLAINABLE_AI_MAP : "generates_xai_maps"
    AI_INFERENCE_EXECUTION ||--o{ PHYSICIAN_DIAGNOSTIC_VALIDATION : "validated_by_physician"
    AI_INFERENCE_EXECUTION ||--o{ CRITICAL_FINDING_ALERT : "triggers_critical_alerts"
    AI_INFERENCE_EXECUTION ||--o{ AI_AUDIT_COMPLIANCE_LOG : "audited_by_compliance"
    PHYSICIAN_DIAGNOSTIC_VALIDATION ||--o{ STRUCTURED_RADIOLOGY_REPORT : "approves_report"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | DIAGNOSTIC_IMAGING_STUDY | Ca Chụp Chẩn đoán Hình ảnh DICOM | DICOM imaging study record retrieved from PACS storing DICOM UID, modality (CT, MRI, X-ray), body part, study date, slice count, and PACS storage URL. | study_id, patient_emr_id, dicom_study_uid, modality_type, body_part, study_date, total_slice_count, pacs_storage_url | Has AI_INFERENCE_EXECUTION and STRUCTURED_RADIOLOGY_REPORT. |
| 2 | AI_DIAGNOSTIC_MODEL | Mô hình AI Chẩn đoán Y tế | Deep learning neural network model record storing version tag, architecture (3D U-Net, Vision Transformer), FDA SaMD clearance, AUC-ROC accuracy, and status. | model_id, model_name, version_tag, architecture_type, fda_samd_clearance_number, accuracy_auc_roc, deployment_date, model_status | Has AI_INFERENCE_EXECUTION and FEDERATED_MODEL_WEIGHT_UPDATE. |
| 3 | AI_INFERENCE_EXECUTION | Lần Chạy Suy luận AI | Deep learning inference execution record tracking study ID, model ID, execution timestamp, processing time (ms), GPU node ID, and execution status. | execution_id, study_id, model_id, execution_timestamp, processing_time_ms, gpu_node_id, execution_status | Belongs to DIAGNOSTIC_IMAGING_STUDY and AI_DIAGNOSTIC_MODEL; Has LESION, DIAGNOSIS, XAI, VALIDATION, ALERT, AUDIT. |
| 4 | AI_LESION_DETECTION | Tổn thương AI Phân lập | Segmented lesion detection record storing 3D bounding box coordinates, confidence score %, 3D volume ($\text{mm}^3$), Hounsfield density, and Grad-CAM URL. | lesion_id, execution_id, lesion_label, bounding_box_coordinates_json, confidence_score_pct, volume_mm3, hounsfield_unit_mean, heatmap_overlay_url | Belongs to AI_INFERENCE_EXECUTION. |
| 5 | DIFFERENTIAL_DIAGNOSIS_PREDICTION | Dự đoán Chẩn đoán Phân biệt | Computed differential diagnosis prediction record storing SNOMED-CT code, ICD-10 code, condition name, probability score %, and severity tier. | prediction_id, execution_id, snomed_concept_code, icd10_code, condition_name, probability_score_pct, clinical_severity_tier | Belongs to AI_INFERENCE_EXECUTION. |
| 6 | EXPLAINABLE_AI_MAP | Bản đồ Giải thích AI Grad-CAM | Explainable AI (XAI) feature attribution map record storing Grad-CAM heatmap hash, saliency map URL, and top attributing radiomic features JSON. | xai_id, execution_id, gradcam_heatmap_hash, saliency_map_url, top_attributing_features_json, generated_at | Belongs to AI_INFERENCE_EXECUTION. |
| 7 | PHYSICIAN_DIAGNOSTIC_VALIDATION | Phê duyệt Chẩn đoán của Bác sĩ | Clinician validation and overrule record tracking radiologist ID, decision (Accepted, Modified, Overruled), overrule reason, and concordance status. | validation_id, execution_id, physician_id, validation_decision, overrule_reason, concordance_status, validated_at | Belongs to AI_INFERENCE_EXECUTION; Approves STRUCTURED_RADIOLOGY_REPORT. |
| 8 | CRITICAL_FINDING_ALERT | Cảnh báo Ca Bệnh Nguy cấp STAT | STAT emergency alert record generated when AI detects acute life-threatening critical findings (Pneumothorax, Brain Hemorrhage) tracking physician acknowledgment. | alert_id, execution_id, alert_severity, critical_finding_type, escalated_at, physician_acknowledged_at, alert_status | Belongs to AI_INFERENCE_EXECUTION. |
| 9 | STRUCTURED_RADIOLOGY_REPORT | Báo cáo Chẩn đoán Hình ảnh Chuẩn | Standardized structured radiology report (BI-RADS, LUNG-RADS) storing findings text, diagnostic impression, RadLex codes, and digital signature. | report_id, study_id, validation_id, radlex_birads_category, findings_text, impression_text, signed_at | Belongs to DIAGNOSTIC_IMAGING_STUDY and PHYSICIAN_DIAGNOSTIC_VALIDATION. |
| 10 | FEDERATED_MODEL_WEIGHT_UPDATE | Cập nhật Trọng số AI Liên kết | Federated model fine-tuning record storing hospital node code, encrypted weight delta hash ($\Delta W$), differential privacy noise ($\epsilon$), and timestamp. | federated_update_id, model_id, hospital_node_code, weight_delta_hash, differential_privacy_epsilon, updated_at | Belongs to AI_DIAGNOSTIC_MODEL. |
| 11 | AI_AUDIT_COMPLIANCE_LOG | Nhật ký Tuân thủ Kiểm toán AI | FDA SaMD regulatory audit trail logging execution ID, FHIR AuditEvent ID, regulatory software version, logged timestamp, and compliance status. | audit_id, execution_id, fhir_audit_event_id, samd_regulatory_version, logged_at, compliance_status | Belongs to AI_INFERENCE_EXECUTION. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | DIAGNOSTIC_IMAGING_STUDY | one-to-many | AI_INFERENCE_EXECUTION | runs_inference | One DICOM imaging study runs deep learning AI inference executions. |
| 2 | DIAGNOSTIC_IMAGING_STUDY | one-to-many | STRUCTURED_RADIOLOGY_REPORT | generates_report | One DICOM imaging study generates standardized structured radiology reports. |
| 3 | AI_DIAGNOSTIC_MODEL | one-to-many | AI_INFERENCE_EXECUTION | executes_model | One AI diagnostic neural network model executes inference runs across studies. |
| 4 | AI_DIAGNOSTIC_MODEL | one-to-many | FEDERATED_MODEL_WEIGHT_UPDATE | receives_weight_updates | One AI model receives federated weight updates ($\Delta W$) from multi-hospital nodes. |
| 5 | AI_INFERENCE_EXECUTION | one-to-many | AI_LESION_DETECTION | detects_lesions | One AI inference execution detects and segments 3D anatomical lesions. |
| 6 | AI_INFERENCE_EXECUTION | one-to-many | DIFFERENTIAL_DIAGNOSIS_PREDICTION | predicts_diagnoses | One AI inference execution predicts ranked differential diagnosis probabilities. |
| 7 | AI_INFERENCE_EXECUTION | one-to-many | EXPLAINABLE_AI_MAP | generates_xai_maps | One AI inference execution generates Explainable AI (Grad-CAM) saliency maps. |
| 8 | AI_INFERENCE_EXECUTION | one-to-many | PHYSICIAN_DIAGNOSTIC_VALIDATION | validated_by_physician | One AI inference execution is validated or overruled by an attending physician. |
| 9 | AI_INFERENCE_EXECUTION | one-to-many | CRITICAL_FINDING_ALERT | triggers_critical_alerts | Severe AI predictions trigger STAT emergency critical finding alert escalations. |
| 10 | AI_INFERENCE_EXECUTION | one-to-many | AI_AUDIT_COMPLIANCE_LOG | audited_by_compliance | One AI inference execution generates FDA SaMD regulatory compliance audit logs. |
| 11 | PHYSICIAN_DIAGNOSTIC_VALIDATION | one-to-many | STRUCTURED_RADIOLOGY_REPORT | approves_report | Physician diagnostic validation approves final structured radiology reports. |
