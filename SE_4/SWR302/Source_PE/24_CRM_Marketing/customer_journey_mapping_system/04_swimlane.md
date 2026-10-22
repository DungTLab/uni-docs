# Swimlane Diagram — Customer Journey Mapping System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Customer Journey Tracking"])

    subgraph Lane_Architect["Customer Journey Architect"]
        A1["Build Visual Journey Blueprint & Define Stages"]
        A2["Review AI Bottleneck Diagnoses & Optimize Map"]
    end

    subgraph Lane_System["Customer Journey Mapping System"]
        S1["Ingest Streaming Event & Map to Journey Touchpoint"]
        S2["Update Live Customer Journey Stage & Velocity"]
        S3{"Is Customer Stalled in Stage (> 5 Days)?"}
        S4["Evaluate Next Best Action NBA & Trigger Payload"]
        S5["Detect Friction Bottleneck Node (57% Drop-off)"]
        S6["Render Visual Journey Health & Emotion Curve"]
    end

    subgraph Lane_Integrations["CDP & Marketing Orchestrator"]
        I1["Stream Real-Time Customer CDP Behavioral Events"]
        I2["Dispatch NBA Campaign (Personalized ROI Email)"]
    end

    subgraph Lane_Customer["Target Customer"]
        C1["Interact with Web/App Touchpoints & Advance Stage"]
        C2["Receive NBA Message & Resume Journey Progress"]
    end

    Finish(["End Journey Optimization Cycle"])

    Start --> A1 --> S1 --> I1 --> C1 --> S2 --> S3
    S3 -->|"Yes / Stalled"| S4 --> I2 --> C2 --> S2
    S3 -->|"No / Progressing"| S5 --> A2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Customer Journey Architect | Designs visual journey blueprint maps across lifecycle stages, specifies channel touchpoints, reviews AI bottleneck friction diagnoses, and optimizes blueprint paths. |
| 2 | Customer Journey Mapping System | Ingests real-time CDP event streams, maps user behaviors to journey stages, tracks stage velocity, monitors for stalled customer progress ($> 5$ days), evaluates Next Best Action (NBA) rules, detects high-friction drop-off nodes, and renders emotion curves. |
| 3 | CDP & Marketing Orchestrator | Streams continuous customer behavioral event logs into the system and receives Next Best Action (NBA) trigger payloads to dispatch stage-appropriate marketing messages. |
| 4 | Target Customer | Interacts with digital and physical touchpoints, generates CDP telemetry events, receives personalized Next Best Action prompts when stalled, and advances through lifecycle stages. |
