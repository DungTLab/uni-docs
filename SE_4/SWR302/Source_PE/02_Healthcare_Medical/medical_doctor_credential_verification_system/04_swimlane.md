# Swimlane Diagram — Medical Doctor Credential Verification System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Medical Credential Verification Cycle"])

    subgraph Lane1["Applicant Physician"]
        A1["Submit License Application & Diploma Scans"]
        A2["Complete CME Courses & Log Renewal Hours"]
    end

    subgraph Lane2["Primary Source Verifier / University"]
        B1["Verify Degree Serial & Graduation Registry"]
        B2["Confirm Accredited CME Credit Attendance"]
    end

    subgraph Lane3["System / PSV & Blockchain Engine"]
        S1["Query Medical School API & NPDB Registry"]
        S2{"Diploma Authentic & CME >= 48 Hours?"}
        S3["Flag Credential Fraud & Block Application"]
        S4["Issue W3C Verifiable Credential & DID QR"]
        S5["Publish Revocation Status to Blockchain"]
    end

    subgraph Lane4["Medical Board Registrar & Public"]
        C1["Approve License & Define Practice Scope"]
        C2["Scan Public QR Code to Verify Doctor Status"]
    end

    Finish(["End License Issuance & Verification Cycle"])

    Start --> A1 --> S1 --> B1 --> S2
    S2 -->|"No"| S3 --> S5 --> Finish
    S2 -->|"Yes"| C1 --> S4 --> A2 --> B2 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Applicant Physician | Submits medical practicing license applications (CCHN), uploads diploma scans, completes accredited CME courses, logs renewal credit hours, and holds digital QR credentials. |
| 2 | Primary Source Verifier / University | Accredited medical universities and specialty boards that verify diploma serial numbers, graduation registries, academic standing, and accredited CME course attendance. |
| 3 | System / PSV & Blockchain Engine | Executes automated Primary Source Verification (PSV) queries, checks NPDB malpractice background registries, formats W3C Verifiable Credentials, and publishes DID hashes on blockchain. |
| 4 | Medical Board Registrar & Public | Medical Board registrars approving practicing licenses, defining scope of practice, registering disciplinary suspensions, and patients/insurance providers scanning public QR codes. |
