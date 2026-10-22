# Swimlane Diagram — Veterinary Clinic Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Veterinary Clinic Visit"])

    subgraph Lane1["Pet Owner & Patient"]
        A1["Check In at Desk & Provide Pet Symptoms"]
        A2["Approve Surgical Consent & Collect Dispensed Meds"]
    end

    subgraph Lane2["Clinic Receptionist"]
        B1["Verify Microchip ID & Assign Exam Room Queue"]
        B2["Process Itemized Payment & Submit Insurance Claim"]
    end

    subgraph Lane3["Veterinarian & Vet Nurse"]
        C1["Perform Physical Exam & Document SOAP Record"]
        C2{"Inpatient Surgery or Boarding Required?"}
        C3["Administer Outpatient Vaccine & Prescribe Meds"]
        C4["Perform Sterile Surgery & Assign Kennel Boarding"]
    end

    subgraph Lane4["VCMS System & Diagnostic Lab"]
        S1["Ingest Blood Chemistry & Cytology Lab Results"]
        S2["Calculate Weight-Based Drug Dosage & Generate Invoice"]
        S3["Update Mobile Passport & Sync National Registry"]
    end

    Finish(["End Visit, Dispense Meds & Print Invoice"])

    Start --> A1 --> B1 --> C1 --> S1 --> C2
    C2 -->|"No"| C3 --> S2 --> B2 --> A2 --> S3 --> Finish
    C2 -->|"Yes"| C4 --> S2 --> B2 --> A2 --> S3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Pet Owner & Patient | Checks in at clinic desk, provides pet medical history, approves surgical consent forms, collects dispensed pharmaceuticals, and receives discharge instructions. |
| 2 | Clinic Receptionist | Verifies pet 15-digit microchip ID, manages exam room queues, generates itemized clinic invoices, collects payments, and submits electronic pet insurance claims. |
| 3 | Veterinarian & Vet Nurse | Conducts physical exams (Temp, HR, Weight), documents SOAP notes, performs in-house lab tests/cytology, executes sterile surgeries, and administers vaccines. |
| 4 | VCMS System & Diagnostic Lab | Ingests diagnostic lab results, calculates weight-based drug doses ($mg/kg$), updates mobile vaccination passports, and syncs microchip IDs with national registries. |
