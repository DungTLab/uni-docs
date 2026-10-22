# Swimlane Diagram — Recipe & Ingredient Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Executive Chef / R&D Specialist"]
        C1["Input Master Recipe & Ingredient BOM"]
        C2["Define Prep Steps & Yield Shrinkage"]
        C3["Publish Recipe Version v1.0"]
    end

    subgraph Lane2["Cost Controller / Accountant"]
        A1["Review Theoretical Cost & Selling Price"]
        A2{"Is Food Cost % Within Target SLA?"}
        A3["Approve Recipe Costing & Publish Menu PLU"]
        A4["Perform Stock Take Audit & Audit Variance"]
    end

    subgraph Lane3["POS Sales Engine"]
        P1["Transmit Sold Dish Orders Real-Time"]
    end

    subgraph Lane4["Recipe & Ingredient Core Engine"]
        S1["Convert UOM & Calculate Portion Cost"]
        S2["Map PLU to BOM & Deplete Perpetual Stock"]
        S3["Generate TvA Variance & Stock Reorder Alert"]
    end

    Finish(["End"])

    Start --> C1 --> C2 --> S1 --> A1 --> A2
    A2 -->|"No (Food Cost % Too High)"| C1
    A2 -->|"Yes (Cost % Optimal)"| A3 --> C3 --> P1 --> S2 --> A4 --> S3 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Executive Chef / R&D Specialist | Inputs master recipe details, itemizes Bill of Materials (BOM) ingredients, defines yield factors and prep steps, and publishes recipe versions. |
| **Lane 2** | Cost Controller / Accountant | Evaluates theoretical food cost percentages against target SLAs, approves recipe costing, conducts stock take audits, and analyzes variance losses. |
| **Lane 3** | POS Sales Engine | Transmits real-time sold dish line item transaction payloads from restaurant POS terminals to trigger inventory stock depletion. |
| **Lane 4** | Recipe & Ingredient Core Engine | Converts UOM ratios, computes portion costs, maps POS PLUs to BOM components, perpetually decrements stock balances, and calculates TvA variances. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Recipe Creation & BOM Building**: Chef inputs master recipe details, specifies raw ingredients, net weights, and waste factors (`C1`, `C2`).
2. **UOM Conversion & Cost Calculation**: Core Engine converts purchasing UOMs to recipe UOMs and computes theoretical portion cost (`S1`). Cost Controller evaluates theoretical Food Cost % against target company SLA (`A1`, `A2`).
3. **Approval & Version Publishing**:
   - If **No (Food Cost % Too High)**: Cost Controller returns recipe to Chef to revise portion sizes or substitute ingredients (`C1`).
   - If **Yes (Cost % Optimal)**: Cost Controller approves costing (`A3`), and Chef publishes recipe version (`C3`).
4. **POS Depletion & Stock Audit**: POS Sales Engine sends sold dish orders (`P1`). Core Engine perpetually decrements raw ingredient stock based on BOM recipes (`S2`). Cost Controller conducts periodic stock take audits (`A4`). Core Engine calculates Theoretical vs Actual (TvA) variance cost and triggers reorder alerts (`S3`), ending the workflow (`Finish`).
