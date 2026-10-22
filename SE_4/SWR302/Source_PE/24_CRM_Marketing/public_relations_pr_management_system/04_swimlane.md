# Swimlane Diagram — Public Relations (PR) Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start PR Distribution & Pitching Lifecycle"])

    subgraph Lane_PRManager["PR Manager"]
        M1["Draft Press Release & Set Embargo Timer"]
        M2["Send Targeted 1-to-1 Email Pitches to Media List"]
    end

    subgraph Lane_Journalist["Journalist / Media Reporter"]
        J1["Receive Pitch Email, Open & Click Press Kit Link"]
        J2["Publish Media Article Coverage on TechCrunch"]
    end

    subgraph Lane_System["Public Relations Management System"]
        S1["Verify Legal Sign-Off & SEC Reg FD Compliance"]
        S2{"Passes Legal Compliance & Embargo Expiration?"}
        S3["Block Distribution & Flag Legal Clearance Error"]
        S4["Auto-Publish Press Room & Transmit Wire Payload"]
        S5["Aggregate News Clipping & Calculate AVE & SOV"]
    end

    subgraph Lane_Wire["Press Release Wire Network"]
        W1["Syndicate Press Release to 4,500 Newsrooms"]
        W2["Return Wire Confirmation & Syndicated Reach Receipts"]
    end

    Finish(["End PR Campaign Cycle"])

    Start --> M1 --> S1 --> S2
    S2 -->|"No / Un-Approved"| S3 --> Finish
    S2 -->|"Yes / Cleared"| M2 --> J1 --> S4 --> W1 --> W2 --> J2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | PR Manager | Drafts press releases, sets media embargo timers, obtains legal sign-offs, builds media lists, and sends personalized 1-to-1 email pitches to targeted journalists. |
| 2 | Journalist / Media Reporter | Receives email pitches, opens emails, clicks tracked press kit links, accesses the online press room, and publishes media coverage articles. |
| 3 | Public Relations Management System | Verifies legal sign-offs and SEC Reg FD compliance, enforces media embargo timers, publishes releases to the corporate press room upon embargo expiration, transmits payloads to wire networks, aggregates online news clippings, classifies sentiment, and calculates Advertising Value Equivalency (AVE). |
| 4 | Press Release Wire Network | Syndicates official press releases across 4,500 newsrooms, financial portals, and search engines (PR Newswire / BusinessWire), returning distribution receipts. |
