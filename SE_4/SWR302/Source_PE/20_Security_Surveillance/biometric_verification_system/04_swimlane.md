# Swimlane Diagram — Biometric Verification System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Biometric Authentication"])

    subgraph Lane1["Enrolled Subject"]
        U1["Present Fingerprint / Face to Scanner"]
    end

    subgraph Lane2["Biometric Scanner Hardware"]
        S1["Capture Multi-Spectral Raw Image"]
        S2["Extract Feature Minutiae Vector"]
    end

    subgraph Lane3["AI Liveness Module"]
        L1["Analyze Skin Reflectance & IR Depth"]
        L2{"Liveness Verified?"}
        L3["Trigger Spoof Attack Alert"]
    end

    subgraph Lane4["System"]
        M1["Decrypt Stored User Template"]
        M2["Compute Match Probability Score"]
        M3{"Score >= Threshold?"}
        M4["Issue Biometric Access Token"]
        M5["Trigger Match Failure & PIN Fallback"]
    end

    Finish(["End Biometric Transaction"])

    Start --> U1 --> S1 --> L1 --> L2
    L2 -->|"Yes"| S2 --> M1 --> M2 --> M3
    L2 -->|"No"| L3 --> Finish
    M3 -->|"Yes"| M4 --> Finish
    M3 -->|"No"| M5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Enrolled Subject | Presents finger, iris, or facial trait to biometric scanner hardware. |
| 2 | Biometric Scanner Hardware | Captures multi-spectral raw optical/IR frames, checks image quality, and extracts minutiae feature vectors. |
| 3 | AI Liveness Module | Analyzes 3D depth, skin reflectance, and micro-expressions to reject fake presentation spoof attacks (photos/masks). |
| 4 | System | Decrypts pre-stored user templates from HSM vault, computes match probability scores against target thresholds, and issues access tokens or fallback prompts. |
