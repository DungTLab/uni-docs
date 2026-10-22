# Swimlane Diagram — Personalization Engine

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Personalization Web Request"])

    subgraph Lane_Visitor["Target Web Visitor"]
        V1["Request Web Page (https://company.com/homepage)"]
        V2["Receive Dynamic Personalized Page Content (< 50 ms)"]
    end

    subgraph Lane_CDP["CDP & Privacy Registry"]
        C1["Verify Cookie Privacy Tracking Consent Status"]
        C2["Return Real-Time Customer Segment Tags (VIP_BUYER)"]
    end

    subgraph Lane_Engine["CDN Edge Worker & Personalization Engine"]
        E1["Extract Visitor IP Location, Device & Weather Context"]
        E2["Scan Personalization Rule Matrix & Priority Rules"]
        E3{"Passes Privacy Consent & Decisioning Latency (< 50 ms)?"}
        E4["Serve Non-Personalized Default Fallback Experience"]
        E5["Compile Dynamic JSON Experience Payload"]
    end

    subgraph Lane_AI["AI ML Recommendation Engine"]
        R1["Execute Collaborative Filtering & Vector Search"]
        R2["Apply Merchandising Boost & Pin Rules to Items"]
    end

    Finish(["End Personalization Cycle"])

    Start --> V1 --> C1 --> C2 --> E1 --> E2 --> R1 --> R2 --> E3
    E3 -->|"No / Consent Denied or Timeout"| E4 --> Finish
    E3 -->|"Yes / Cleared"| E5 --> V2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Target Web Visitor | Requests dynamic web pages or mobile app views, transmitting IP location, device context, and clickstream events, and receiving sub-50ms personalized experiences. |
| 2 | CDP & Privacy Registry | Verifies visitor cookie privacy tracking consent flags, returning real-time customer segment tags (`VIP_BUYER`, `CHURN_RISK`) and brand affinity context to the edge worker. |
| 3 | CDN Edge Worker & Personalization Engine | Intercepts HTTP requests at the CDN edge, extracts real-time visitor context (weather, device, referrer), scans priority rule matrices, verifies sub-50ms latency SLAs, serves fallback default content if consent is denied, and compiles dynamic JSON experience payloads. |
| 4 | AI ML Recommendation Engine | Runs collaborative filtering and vector search embeddings, applying merchandiser product boost and pin rules to generate personalized item recommendation carousels. |
