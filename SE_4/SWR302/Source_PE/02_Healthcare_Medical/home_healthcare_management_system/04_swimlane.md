# Swimlane Diagram — Home Healthcare Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Home Health Care Cycle"])

    subgraph Lane1["Home Health Patient / Family"]
        A1["Receive Home Admission & Sign OASIS Care Plan"]
        A2["Take Daily Cellular BP / Glucose Vitals"]
        A3["Confirm Mobile EVV Signature at Visit End"]
    end

    subgraph Lane2["Visiting Nurse"]
        B1["Arrive at Patient Home & Clock-In EVV"]
        B2["Perform Clinical Care & Chart Vitals"]
        B3["Clock-Out EVV & Submit Mileage Log"]
    end

    subgraph Lane3["System / EVV & RPM Engine"]
        S1["Verify Geo-Fence GPS & Match Scheduled Visit"]
        S2{"Cellular RPM Vital Breach or Emergency?"}
        S3["Log EVV Compliance & Update Visit Status"]
        S4["Dispatch High-Priority Nursing Alert"]
        S5["Submit Medicare Episode Claim & Billing"]
    end

    subgraph Lane4["Agency Coordinator / Physician"]
        W1["Review OASIS Assessment & Sign Form 485"]
        W2["Review Alert & Adjust Medical Orders"]
    end

    Finish(["End Home Health Care Cycle"])

    Start --> A1 --> W1 --> B1 --> S1 --> B2 --> A2 --> S2
    S2 -->|"No"| B3 --> A3 --> S3 --> S5 --> Finish
    S2 -->|"Yes"| S4 --> W2 --> B3 --> A3 --> S3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Home Health Patient / Family | Receives initial home admission, signs OASIS Form 485 care plan, takes daily cellular vital measurements, and provides mobile signature upon nurse visit completion. |
| 2 | Visiting Nurse | Arrives at patient residence, executes mobile Electronic Visit Verification (EVV) clock-in, performs clinical care/wound charting, clocks out via EVV, and logs travel mileage. |
| 3 | System / EVV & RPM Engine | Validates mobile EVV GPS geo-fence coordinates, ingests daily cellular RPM vital signs telemetry, evaluates physiological alerts, logs 21st Century Cures Act compliance, and submits Medicare episode billing claims. |
| 4 | Agency Coordinator / Physician | Reviews OASIS clinical admission assessments, applies digital signature to Form 485 Home Health Plans of Care, evaluates RPM vital breach alerts, and updates clinical orders. |
