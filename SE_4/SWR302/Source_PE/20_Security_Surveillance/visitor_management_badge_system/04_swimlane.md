# Swimlane Diagram — Visitor Management & Badge System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Visitor Check-In Workflow"])

    subgraph Lane1["Visitor / Guest"]
        V1["Scan Pre-Registration QR at Kiosk"]
        V2["Scan Driver License ID on Reader"]
        V3["Sign Touchscreen Digital NDA"]
    end

    subgraph Lane2["Self-Service Kiosk"]
        K1["Verify Pre-Registration Appointment"]
        K2["Capture Webcam Photo"]
    end

    subgraph Lane3["System"]
        S1["Execute Denied Party Watchlist API"]
        S2{"Watchlist Clear?"}
        S3["Generate Thermal Badge Payload & QR Pass"]
        S4["Send Arrival SMS/Teams Alert to Host"]
        S5["Trigger Discrete Security Desk Alert"]
        S6["Mark Status to ON-SITE"]
    end

    subgraph Lane4["Thermal Printer"]
        P1["Print Self-Adhesive Photo Badge"]
    end

    subgraph Lane5["Employee Host"]
        H1["Receive Arrival SMS Notification"]
        H2["Proceed to Lobby to Escort Guest"]
    end

    Finish(["End Check-In Process"])

    Start --> V1 --> K1 --> V2 --> S1 --> S2
    S2 -->|"Yes"| V3 --> K2 --> S3 --> P1 --> S4 --> H1 --> H2 --> S6 --> Finish
    S2 -->|"Watchlist Hit"| S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Visitor / Guest | Scans pre-arrival QR code at kiosk, presents driver license ID for OCR scanning, and signs digital legal NDA. |
| 2 | Self-Service Kiosk | Verifies pre-registration records, guides user touch interactions, and captures webcam visitor photos. |
| 3 | System | Executes denied party watchlist screening, generates turnstile QR passes, dispatches host SMS/Teams notifications, and updates active guest rosters. |
| 4 | Thermal Printer | Receives badge payloads and prints self-adhesive thermal visitor photo badges. |
| 5 | Employee Host | Receives real-time SMS/Teams guest arrival alerts and proceeds to lobby workspace to escort visitor. |
