# Swimlane Diagram — Organic Food Marketplace

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Organic Buyer / Consumer"]
        C1["Browse Seasonal Organic Produce or Subscribe CSA"]
        C2["Place Order & Pay via Escrow Protection"]
        C3["Scan QR Code on Delivery & Confirm Quality"]
    end

    subgraph Lane2["Local Organic Farm / Producer"]
        F1["List Crop Harvest & Attach USDA Organic Cert"]
        F2["Pack Insulated Fresh Box with QR Batch Tags"]
    end

    subgraph Lane3["Marketplace Quality Inspector"]
        I1["Verify USDA NOP Cert & Conduct Bio-Audit"]
    end

    subgraph Lane4["Organic Marketplace Core Engine"]
        S1["Verify USDA NOP Cert API & Activate Storefront"]
        S2{"Is Escrow Payment & Delivery Confirmed?"}
        S3["Hold Escrow & Open Buyer Dispute Investigation"]
        S4["Dispatch Cold-Chain Delivery Courier 2°C-4°C"]
        S5["Release Farmer Escrow Payout & Log Analytics"]
    end

    Finish(["End"])

    Start --> F1 --> I1 --> S1 --> C1 --> C2 --> S4 --> F2 --> C3 --> S2
    S2 -->|"No (Damaged / Quality Claim)"| S3 --> Finish
    S2 -->|"Yes (Delivery Approved)"| S5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Organic Buyer / Consumer | Browses seasonal organic produce or subscribes to CSA harvest boxes, places escrow orders, scans farm-to-fork QR codes on delivery, and confirms freshness. |
| **Lane 2** | Local Organic Farm / Producer | Lists crop harvest yields, attaches USDA organic certificates, packs insulated fresh boxes, and affixes QR batch provenance tags. |
| **Lane 3** | Marketplace Quality Inspector | Conducts on-farm bio-audits, verifies synthetic chemical-free practices, and checks USDA NOP organic registration compliance. |
| **Lane 4** | Organic Marketplace Core Engine | Verifies USDA NOP database APIs, holds buyer payments in escrow, dispatches 2°C-4°C cold-chain couriers, and releases farmer payouts upon approval. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Farm Onboarding & Listing**: Organic Farmer lists crop harvest (`F1`). Quality Inspector audits bio standards (`I1`). Core Engine verifies USDA NOP cert API (`S1`).
2. **Order Placement & Escrow**: Organic Buyer browses produce or CSA boxes (`C1`) and places order with escrow payment (`C2`). Core Engine holds funds in escrow and dispatches cold-chain courier (`S4`).
3. **Fulfillment & Delivery**: Farmer packs insulated fresh box with QR tags (`F2`). Buyer receives delivery, scans QR code, and inspects quality (`C3`).
4. **Escrow Audit & Release**: Core Engine checks delivery approval status (`S2`).
   - If **No (Damaged / Quality Claim)**: Core Engine holds escrow and opens dispute investigation (`S3`).
   - If **Yes (Delivery Approved)**: Core Engine releases net farmer payout and updates analytics (`S5`), completing execution (`Finish`).
