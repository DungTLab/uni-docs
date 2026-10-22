# Swimlane Diagram — Access Control Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Badge Access Flow"])

    subgraph Lane1["Employee / Cardholder"]
        E1["Tap RFID Badge on Door Reader"]
    end

    subgraph Lane2["Door Controller"]
        C1["Read Card Number & Facility Code"]
        C2["Check Local Permission & Schedule"]
        C3{"Permission Valid?"}
        C4["Pulse Strike Relay & Flash Green LED"]
        C5["Flash Red LED & Sound Beep"]
    end

    subgraph Lane3["System"]
        S1["Verify Anti-Passback Status"]
        S2{"Anti-Passback OK?"}
        S3["Log ACCESS GRANTED Event"]
        S4["Log ACCESS DENIED Event"]
        S5["Trigger Anti-Passback Violation Alert"]
    end

    subgraph Lane4["Security Administrator"]
        A1["Monitor Door Status Console"]
        A2["Review Access Violation Log"]
    end

    Finish(["End Door Access Transaction"])

    Start --> E1 --> C1 --> C2 --> C3
    C3 -->|"Yes"| S1 --> S2
    C3 -->|"No"| C5 --> S4 --> A1 --> Finish
    S2 -->|"Yes"| C4 --> S3 --> A1 --> Finish
    S2 -->|"No"| C5 --> S5 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Employee / Cardholder | Swipes physical RFID smartcard badge at the door reader to request access. |
| 2 | Door Controller | Hardware panel reading card data, verifying local memory permissions, checking time schedules, and energizing door strike relays. |
| 3 | System | Validates anti-passback sequence rules, logs access granted/denied transaction entries, and generates real-time security alerts. |
| 4 | Security Administrator | Monitors live door event dashboards, receives anti-passback violation alerts, and manages access exceptions. |
