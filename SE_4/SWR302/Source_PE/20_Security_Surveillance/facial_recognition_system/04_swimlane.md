# Swimlane Diagram — Facial Recognition System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Live Video Facial Stream Handling"])

    subgraph Lane1["4K Facial Camera"]
        C1["Stream 4K RTSP Video Stream"]
        C2["Capture Dual RGB & IR Frame Crop"]
    end

    subgraph Lane2["AI Liveness Engine"]
        L1["Analyze 3D Depth Map & Skin IR Reflectance"]
        L2{"Liveness Verified (Real Human)?"}
        L3["Block Transaction & Trigger Spoof Alert"]
    end

    subgraph Lane3["Facial Vector Engine"]
        V1["Extract 512-Dimensional Vector Embedding"]
        V2["Execute 1:N Cosine Similarity GPU Search"]
        V3{"Cosine Match Score >= 85%?"}
        V4["Execute GDPR Background Face Blurring"]
    end

    subgraph Lane4["System"]
        S1["Retrieve POI Candidate Profile & Reference Photo"]
        S2["Sound Console Alarm & Render Side-by-Side Popup"]
    end

    subgraph Lane5["Biometric Security Officer"]
        O1["Review Side-by-Side Photos & Confirm Match"]
        O2["Dispatch Floor Guard to Location Zone"]
    end

    Finish(["End Facial Recognition Cycle"])

    Start --> C1 --> C2 --> L1 --> L2
    L2 -->|"Real Human"| V1 --> V2 --> V3
    L2 -->|"Spoof Attempt"| L3 --> Finish
    V3 -->|"Match Hit (>= 85%)"| S1 --> S2 --> O1 --> O2 --> Finish
    V3 -->|"No Match (< 85%)"| V4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | 4K Facial Camera | Ingests live 4K RTSP video streams and captures dual RGB and Infrared (IR) face frame crops. |
| 2 | AI Liveness Engine | Analyzes 3D depth geometry and sub-dermal skin IR reflectance, blocking photo or mask presentation spoof attacks. |
| 3 | Facial Vector Engine | Extracts non-reversible 512-dimensional vector embeddings, executes 1:N GPU similarity searches, and applies GDPR face blurring for non-matches. |
| 4 | System | Retrieves POI candidate profile metadata, sounds console alarms, and renders side-by-side photo comparison modals. |
| 5 | Biometric Security Officer | Reviews side-by-side candidate photos, verifies biometric match hits, and dispatches floor guards to the camera zone. |
