# Swimlane Diagram — Telemedicine Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start: Patient Selects Doctor & Desired Time Slot"])

    subgraph Lane1["Patient / Remote Client"]
        C1["Book Virtual Slot & Submit Symptom Photos"]
        C2["Authorize Consultation Fee Escrow Hold"]
        C3["Join WebRTC Video Room & Discuss Symptoms"]
        C4["Receive Home Medication Delivery & Submit Doctor Review"]
    end

    subgraph Lane2["Tele-Physician"]
        P1["Accept Virtual Appointment Queue"]
        P2["Review Pre-Vitals & Past EHR History"]
        P3["Conduct Live Video Examination & Formulate Diagnosis"]
        P4["Issue Digital E-Prescription & Home Lab Requisition"]
    end

    subgraph Lane3["Telemedicine Platform Engine"]
        S1["Lock Availability Slot & Generate WebRTC Room Token"]
        S2["Establish Encrypted Peer-to-Peer Video Stream"]
        S3["Run Automated Drug Interaction & Allergy Check"]
        S4["Sync Consultation SOAP Notes & ICD-10 to Hospital EHR"]
        S5["Settle Payment Escrow & Credit Doctor Payout Wallet"]
    end

    subgraph Lane4["Payment & Pharmacy Delivery Gateway"]
        G1["Process Card Payment Hold Authorization"]
        G2{"Home Delivery Selected?"}
        G3["Dispatch Prescription to Partner Pharmacy Courier"]
    end

    Finish(["End: Telemedicine Encounter Completed"])

    Start --> C1 --> C2 --> G1 --> S1 --> P1 --> P2 --> C3 --> P3 --> P4 --> S3 --> S4 --> G2
    G2 -->|"Yes: Home Delivery"| G3 --> C4
    G2 -->|"No: Self-Pickup"| C4
    C4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Patient / Remote Client | Selects tele-physician and slot, submits pre-consultation symptom photos, authorizes payment escrow hold, joins WebRTC video room for consultation, receives home medication delivery, and submits post-consultation doctor reviews. |
| 2 | Tele-Physician | Accepts appointment queue, reviews pre-consultation vitals and historical EHR records, conducts live WebRTC video consultation, formulates clinical diagnosis (ICD-10), issues digitally signed e-prescriptions, and orders home lab collections. |
| 3 | Telemedicine Platform Engine | Locks schedule slots, generates WebRTC room tokens, orchestrates encrypted P2P video streaming, executes drug interaction checks, synchronizes clinical SOAP notes with central hospital EHRs, and settles payment escrows into doctor payout wallets. |
| 4 | Payment & Pharmacy Delivery Gateway | Authorizes payment card holds in escrow, verifies pharmacy drug inventory, dispatches home prescription deliveries via logistics couriers, and provides real-time shipment tracking. |
