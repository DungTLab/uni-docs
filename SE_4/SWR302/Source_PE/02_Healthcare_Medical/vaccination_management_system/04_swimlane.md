# Swimlane Diagram — Vaccination Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Vaccination Clinic Session"])

    subgraph Lane1["Patient / Parent"]
        A1["Book Appointment & Complete Pre-Screening"]
        A2["Present Booking QR Code at Reception"]
        A3["Wait 30 Minutes in Observation Room"]
        A4["Receive Digital QR Vaccine Certificate"]
    end

    subgraph Lane2["Screening Physician"]
        B1["Examine Recipient & Check Vitals/Temp"]
        B2["Evaluate Contraindications & Allergy History"]
        B3["Issue Clinical Vaccination Clearance"]
    end

    subgraph Lane3["System / Cold-Chain & QR Engine"]
        S1["Verify Reservation & Route to Screening"]
        S2{"Recipient Cleared & Temperature < 37.5C?"}
        S3["Verify FEFO Lot Barcode & 5-Rights"]
        S4["Reschedule Appointment & Defer Dose"]
        S5["Start 30-Min Timer & Issue QR Passport"]
    end

    subgraph Lane4["Vaccination Nurse"]
        N1["Scan Vaccine Vial 2D DataMatrix Barcode"]
        N2["Administer Injection at Anatomical Site"]
        N3["Monitor Observation Room & Check Out"]
    end

    Finish(["End Vaccination Session"])

    Start --> A1 --> A2 --> S1 --> B1 --> B2 --> B3 --> S2
    S2 -->|"Yes"| N1 --> S3 --> N2 --> N3 --> A3 --> S5 --> A4 --> Finish
    S2 -->|"No"| S4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient / Parent | Books vaccination appointment, completes digital health declaration, presents QR token at clinic reception, waits in 30-minute observation room, and receives verifiable digital QR immunization passport. |
| 2 | Screening Physician | Measures recipient vitals/temperature, evaluates allergy/contraindication history against vaccine product guidelines, and issues formal electronic vaccination clearance. |
| 3 | System / Cold-Chain & QR Engine | Verifies appointment token, evaluates screening contraindication rules, enforces 2D barcode FEFO batch validation, manages automated 30-minute observation timer, and issues cryptographically signed QR passports. |
| 4 | Vaccination Nurse | Scans vaccine packaging 2D DataMatrix barcode to verify 5-Rights, injects vaccine at correct anatomical site, records dose details, and monitors recipient in the observation room. |
