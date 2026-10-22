# Swimlane Diagram — Medical Tourism Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Medical Tourism Journey"])

    subgraph Lane_Patient["International Medical Tourist"]
        P1["Submit Inquiry, Passport & Medical History"]
        P2["Participate in Pre-Travel Video Tele-Consult"]
        P3["Arrive at Airport & Receive VIP Pickup"]
        P4["Undergo Surgery & Post-Op Clinic Visit"]
    end

    subgraph Lane_Coordinator["Medical Tourism Coordinator"]
        C1["Translate Records & Issue Multi-Currency Quote"]
        C2["Generate Embassy Medical Visa Invitation Package"]
        C3["Book Partner Hotel, Flights & Airport Transfers"]
    end

    subgraph Lane_System["Medical Tourism Management System"]
        S1["Translate Medical Records & Process FX Lock"]
        S2["Dispatch Visa Package & Verify Insurance GOP"]
        S3["Update Concierge Logistics & Driver Dispatch"]
        S4{"Is Patient Approved Fit-to-Fly Post-Op?"}
        S5["Issue IATA Fit-to-Fly Certificate & Handoff Summary"]
        S6["Extend Hotel Stay & Schedule Re-Evaluation"]
    end

    subgraph Lane_Surgeon["Attending Specialist Surgeon"]
        D1["Review Translated Records & Sign Pre-Travel Acceptance"]
        D2["Perform Inpatient Surgical Procedure"]
        D3["Evaluate Wound Healing & Perform Fit-to-Fly Exam"]
    end

    Finish(["End Medical Tourism Journey"])

    Start --> P1 --> C1 --> S1 --> D1 --> P2 --> C2 --> S2 --> C3 --> S3 --> P3 --> D2 --> P4 --> D3 --> S4
    S4 -->|"Yes"| S5 --> Finish
    S4 -->|"No / Extend"| S6 --> D3
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | International Medical Tourist | Submits medical history, passport scans, participates in pre-travel video consultations, arrives at airport for VIP concierge pickup, undergoes surgery, and attends post-op evaluation visits. |
| 2 | Medical Tourism Coordinator | Translates medical records, generates multi-currency package quotes, issues official embassy medical visa invitation packages, and books partner hotels, flights, and airport transfers. |
| 3 | Medical Tourism Management System | Translates medical records, locks multi-currency foreign exchange rates, transmits visa packages, verifies insurance GOPs, dispatches driver transport, and issues IATA Fit-to-Fly certificates. |
| 4 | Attending Specialist Surgeon | Reviews translated medical records, signs pre-travel clinical acceptance, performs inpatient surgeries, evaluates wound healing, and conducts Fit-to-Fly examinations. |
