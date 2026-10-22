# Swimlane Diagram — Drug Dispensing Automation System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Order Verification"])

    subgraph Lane_Pharmacist["Hospital Pharmacist"]
        P1["Review Electronic Prescription e-Rx"]
        P2["Verify Clinical Dosing & Approve Order"]
    end

    subgraph Lane_Nurse["Inpatient Ward Nurse"]
        N1["Authenticate at ADC via Fingerprint Biometrics"]
        N2["Select Patient & Pick Drug from Lit LED Bin"]
        N3["Scan Patient Wristband & Drug Pouch Barcode"]
        N4["Administer Medication & Confirm on Terminal"]
    end

    subgraph Lane_System["Drug Dispensing Automation System"]
        S1["Execute Automated Interaction & Allergy Check"]
        S2["Route Verified Order to Ward ADC Queue"]
        S3["Send Solenoid Unlock & Light Bin LED Matrix"]
        S4["Verify BCMA 5-Rights Matching Algorithm"]
        S5["Update EHR MAR & Decrement Perpetual Stock"]
    end

    subgraph Lane_BCMA["BCMA Bedside Scanner"]
        B1["Stream Wristband & Pouch Barcode Data"]
        B2{"Passes 5-Rights Bedside Scan?"}
    end

    Finish(["End Medication Administration Cycle"])

    Start --> P1 --> S1 --> P2 --> S2 --> N1 --> N2 --> S3 --> N3 --> B1 --> B2
    B2 -->|"Yes"| N4 --> S5 --> Finish
    B2 -->|"No / Mismatch"| S4 --> N3
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Hospital Pharmacist | Reviews electronic prescriptions (e-Rx), evaluates clinical dosing safety, checks drug interactions, and approves orders for automated dispensing. |
| 2 | Inpatient Ward Nurse | Authenticates at ward Automated Dispensing Cabinets (ADC) via fingerprint biometrics, selects patient, picks drug from guided LED illuminated bins, and scans barcodes at patient bedside. |
| 3 | Drug Dispensing Automation System | Runs automated drug interaction and allergy checks, routes verified orders to ward ADCs, unlocks cabinet drawers, illuminates bin LEDs, verifies 5-Rights matching algorithms, and updates MAR logs in EHR. |
| 4 | BCMA Bedside Scanner | Streams scanned 2D barcodes from patient wristbands and unit-dose pouches, evaluating 5-Rights safety verification (Right Patient, Drug, Dose, Route, Time). |
