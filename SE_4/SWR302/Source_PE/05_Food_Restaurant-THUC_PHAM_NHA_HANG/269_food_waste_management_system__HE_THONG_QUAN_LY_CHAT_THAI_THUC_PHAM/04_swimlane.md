# Swimlane Diagram — Food Waste Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Kitchen Staff / Line Cook"]
        C1["Weigh Food Scraps on Smart AI Scale"]
        C2["Confirm AI Food Tag & Select Waste Cause"]
        C3["Package Safe Surplus Food for Donation"]
    end

    subgraph Lane2["Restaurant Sustainability Manager"]
        M1["Approve Surplus Food Donation Dispatch"]
        M2["Review AI Prep Advice & Adjust Station Targets"]
    end

    subgraph Lane3["Waste Hauler / Charity Partner"]
        H1["Arrive at Loading Dock & Verify Food Temp"]
        H2["Sign Digital Donation Receipt or Compost Certificate"]
    end

    subgraph Lane4["Food Waste Core Engine"]
        S1["Capture Scale Weight & Classify Image via AI"]
        S2{"Is Food Item Safe & Unserved Surplus?"}
        S3["Route Food Scraps to Organic Compost Stream"]
        S4["Dispatch Rescue Alert & Calculate CO2 Offset"]
        S5["Generate Daily AI Prep Reduction Recommendations"]
    end

    Finish(["End"])

    Start --> C1 --> S1 --> C2 --> S2
    S2 -->|"No (Food Scraps / Trimmings)"| S3 --> S5
    S2 -->|"Yes (Safe Surplus Food)"| C3 --> M1 --> S4 --> H1 --> H2 --> S5 --> M2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Kitchen Staff / Line Cook | Weighs food waste scraps on smart AI scales, confirms AI food classification tags, selects waste root cause categories, and packages safe surplus food. |
| **Lane 2** | Restaurant Sustainability Manager | Approves surplus food donation dispatches, monitors daily waste monetary loss, and reviews AI prep reduction advice to adjust chef station targets. |
| **Lane 3** | Waste Hauler / Charity Partner | Arrives at kitchen loading docks, verifies food safety temperatures, signs digital donation receipts, and hauls organic compost bins. |
| **Lane 4** | Food Waste Core Engine | Processes AI image recognition, calculates monetary costs and CO2e footprints, dispatches food rescue alerts, and generates machine learning prep recommendations. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Weigh-In & AI Recognition**: Kitchen Cook weighs food scraps on smart AI scale (`C1`). Core Engine captures weight and classifies food image via AI (`S1`). Cook confirms item tag and waste cause (`C2`). Core Engine checks donation eligibility (`S2`).
   - If **No (Food Scraps / Trimmings / Expired)**: Core Engine routes waste to Organic Compost Stream (`S3`).
   - If **Yes (Safe Surplus Food)**: Cook packages surplus food (`C3`). Sustainability Manager approves donation dispatch (`M1`).
2. **Food Rescue & Pickup**: Core Engine dispatches rescue alert and calculates CO2 offset (`S4`). Charity Partner arrives at dock and verifies food temp (`H1`), signing digital donation receipt (`H2`).
3. **AI Optimization & Action**: Core Engine generates daily AI prep reduction recommendations (`S5`), and Sustainability Manager adjusts station prep targets (`M2`), completing execution (`Finish`).
