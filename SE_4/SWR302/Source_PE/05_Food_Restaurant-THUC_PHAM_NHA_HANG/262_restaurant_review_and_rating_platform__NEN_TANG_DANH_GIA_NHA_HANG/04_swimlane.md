# Swimlane Diagram — Restaurant Review & Rating Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Diner / Food Reviewer"]
        C1["Dine at Restaurant & Scan POS Receipt"]
        C2["Submit Multi-Criteria Rating & Food Photos"]
        C3["Receive Verified Buyer Badge & Foodie Points"]
    end

    subgraph Lane2["Restaurant Owner / Merchant"]
        M1["Claim & Verify Business Listing"]
        M2["Publish Official Merchant Response"]
    end

    subgraph Lane3["Content Moderator / Auditor"]
        K1["Inspect Flagged Review in Audit Queue"]
        K2["Approve or Remove Quarantined Review"]
    end

    subgraph Lane4["Platform Core Engine"]
        S1["Verify POS Receipt QR Code API"]
        S2{"Is AI Spam Score >= 0.70?"}
        S3["Quarantine Review & Flag Moderator Ticket"]
        S4["Publish Review & Recalculate Ratings"]
        S5["Update Merchant Sentiment Analytics"]
    end

    Finish(["End"])

    Start --> M1 --> C1 --> C2 --> S1 --> S2
    S2 -->|"Yes (Spam Flagged)"| S3 --> K1 --> K2 --> S4
    S2 -->|"No (Clean Review)"| S4 --> C3 --> M2 --> S5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Diner / Food Reviewer | Dines at restaurant, scans POS receipt, submits multi-criteria ratings (Food, Service, Ambience, Value) with photos, and earns Verified Buyer badges. |
| **Lane 2** | Restaurant Owner / Merchant | Claims and verifies business listing ownership, receives review notifications, and publishes official public merchant responses. |
| **Lane 3** | Content Moderator / Auditor | Inspects quarantined review tickets flagged by AI or users, evaluates guidelines, and approves or permanently removes reviews. |
| **Lane 4** | Platform Core Engine | Validates POS receipt QR codes, runs AI NLP spam screening, calculates aggregate star ratings, awards gamification points, and updates sentiment analytics. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Merchant Listing Claim & Review Submission**: Merchant claims business listing (`M1`). Diner dines and submits multi-criteria rating, text, photos, and receipt scan (`C1`, `C2`).
2. **Receipt & AI Spam Verification**: Core Engine verifies POS receipt QR code (`S1`) and evaluates AI spam probability score (`S2`).
   - If **Yes (Spam Score >= 0.70)**: Core Engine quarantines review (`S3`). Moderator inspects ticket (`K1`) and approves or removes review (`K2`).
   - If **No (Clean Review)**: Core Engine publishes review immediately (`S4`).
3. **Badge Award & Merchant Response**: Diner receives green Verified Buyer badge and foodie points (`C3`). Merchant publishes official response (`M2`).
4. **Analytics Update**: Core Engine updates aggregate star ratings and merchant sentiment analytics (`S5`), completing execution (`Finish`).
