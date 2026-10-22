# Swimlane Diagram — Electronic Health Record (EHR) System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start: Patient Consultation at Specialist Clinic"])

    subgraph Lane1["Patient / Citizen"]
        C1["Present National Health ID or Scan QR Code"]
        C2{"Active HIE Access Consent Granted?"}
        C3["Approve Digital Consent Request on Mobile Portal"]
        C4["Review Consultation Summary & Active E-Rx"]
    end

    subgraph Lane2["Attending Physician"]
        P1["Request Cross-Facility Health Record Lookup"]
        P2["Review Longitudinal Timeline & Allergy History"]
        P3["Document SOAP Clinical Notes & ICD-10 Diagnosis"]
        P4["Issue Electronic Prescription (e-Rx)"]
    end

    subgraph Lane3["Electronic Health Record System"]
        S1["Verify Patient Identity in Master Patient Index"]
        S2{"Validate Privacy Consent Policy"}
        S3["Request External FHIR Bundles from Federated HIE"]
        S4["Aggregate Medical History & Highlight Risk Alerts"]
        S5["Run Automated Drug Interaction & Allergy Safety Check"]
        S6["Seal Encounter Note & Log Access Audit Trail"]
    end

    subgraph Lane4["External Facility / LIS / PACS Node"]
        G1["Receive HIE Query & Validate Authorization Token"]
        G2["Package LOINC Lab Values & DICOM Imaging Metadata"]
        G3["Transmit Encrypted FHIR Document Bundle"]
    end

    Finish(["End: Encounter Committed & Synchronized to EHR"])

    Start --> C1 --> S1 --> S2
    S2 -->|"Yes: Authorized"| S3
    S2 -->|"No: Consent Missing"| C2
    C2 -->|"Grant Access"| C3 --> S2
    C2 -->|"Emergency Break-Glass"| P1 --> S3
    S3 --> G1 --> G2 --> G3 --> S4 --> P2 --> P3 --> S5 --> P4 --> S6 --> C4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Patient / Citizen | Presents National Health ID at clinic intake, grants or confirms digital consent permissions via mobile portal if consent is missing, and receives final consultation summary and active e-prescriptions. |
| 2 | Attending Physician | Requests cross-facility health record lookup, reviews historical clinical timeline, past lab trends and allergy alerts, documents SOAP notes and ICD-10 diagnoses, and issues electronic prescriptions. |
| 3 | Electronic Health Record System | Validates patient identity in Master Patient Index (MPI), enforces privacy consent policies, orchestrates federated FHIR bundle requests, aggregates longitudinal data, executes safety checks, seals signed notes, and logs immutable access audit records. |
| 4 | External Facility / LIS / PACS Node | Receives secure HIE data queries from external hospital EHR, verifies authorization tokens, extracts LOINC lab results and DICOM imaging URLs, and returns encrypted FHIR document bundles. |
