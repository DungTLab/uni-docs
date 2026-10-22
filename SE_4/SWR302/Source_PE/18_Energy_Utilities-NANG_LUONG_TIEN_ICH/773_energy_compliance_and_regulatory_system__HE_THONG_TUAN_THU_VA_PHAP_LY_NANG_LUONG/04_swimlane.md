# Swimlane Diagram — Energy Compliance & Regulatory System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Energy Operator"]
        A1["Step 1: Initiate Operational Request"]
        A2["Step 2: Review Telemetry Parameters"]
    end

    subgraph Lane2["Energy Controller Device"]
        S1["Process 1: Validate Telemetry Threshold"]
        S2{"Decision: Threshold Safe?"}
        S3["Process 2: Execute Automated Actuation"]
    end

    subgraph Lane3["Energy Specialist"]
        B1["Step 3: Perform Physical Verification"]
    end

    subgraph Lane4["Regulatory Agency"]
        C1["Step 4: Receive Audit Confirmation"]
    end

    Finish(["End"])

    Start --> A1 --> S1 --> S2
    S2 -->|"Yes"| S3 --> C1 --> Finish
    S2 -->|"No"| A2 --> B1 --> S1
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Energy Operator | Responsible for step execution in operational phase 1 |
| 2 | Energy Controller Device | Responsible for step execution in operational phase 2 |
| 3 | Energy Specialist | Responsible for step execution in operational phase 3 |
| 4 | Regulatory Agency | Responsible for step execution in operational phase 4 |
