# Swimlane Diagram — Dietary Restriction & Allergen Tracking System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Diner / Patient Guest"]
        C1["Build Allergen Matrix & Scan Table QR"]
        C2["Select Safe Substitute Ingredients"]
        C3["Receive Safe Dish with Allergen Seal"]
    end

    subgraph Lane2["Head Chef / Kitchen Staff"]
        K1["Receive Red-Flag Ticket on KDS"]
        K2["Prep Dish at Isolated Station #4 with Purple Utensils"]
    end

    subgraph Lane3["Certified Nutritionist / Dietitian"]
        H1["Decompose Recipe BOM & Tag Big 9 Flags"]
        H2["Audit Allergen Risk Logs & Update Specs"]
    end

    subgraph Lane4["Allergen Tracking Core Engine"]
        S1["Screen POS Order & Match Allergen Flags"]
        S2{"Is Direct Allergen Collision Detected?"}
        S3["Block POS Order & Suggest Safe Substitutes"]
        S4["Approve Order & Print KDS Warning Ticket"]
        S5["Log Safety Screening & Update Analytics"]
    end

    Finish(["End"])

    Start --> H1 --> C1 --> S1 --> S2
    S2 -->|"Yes (Allergen Hazard)"| S3 --> C2 --> S1
    S2 -->|"No (Cleared or Substituted)"| S4 --> K1 --> K2 --> C3 --> S5 --> H2 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Diner / Patient Guest | Builds personal allergen profile (Big 9), scans table QR code menu, selects safe ingredient substitutes, and receives allergen-sealed dishes. |
| **Lane 2** | Head Chef / Kitchen Staff | Receives red-flag warning tickets on kitchen KDS displays, prepares allergen orders at dedicated isolated stations using purple utensils, and attaches safety seals. |
| **Lane 3** | Certified Nutritionist / Dietitian | Decomposes recipe BOMs into sub-ingredients, tags FDA/USDA allergen flags, audits supplier specification sheets, and updates safety matrices. |
| **Lane 4** | Allergen Tracking Core Engine | Performs real-time order screening against diner allergen profiles, detects collisions, blocks unsafe orders, generates safe substitutes, and prints KDS warning tickets. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Recipe Mapping & Profile Setup**: Dietitian maps recipe BOMs to FDA Big 9 allergen flags (`H1`). Diner builds allergen profile and scans table QR code (`C1`).
2. **Real-Time Screening & Collision Check**: Core Engine screens POS order selections against diner profile (`S1`) and evaluates allergen collision status (`S2`).
   - If **Yes (Allergen Hazard)**: Core Engine blocks order and returns safe substitution options (`S3`). Diner selects safe substitute (`C2`), and Core Engine re-screens order (`S1`).
   - If **No (Cleared or Substituted)**: Core Engine approves order and prints red-flag KDS warning ticket (`S4`).
3. **Isolated Kitchen Execution**: Kitchen Chef receives KDS warning ticket (`K1`), cooks meal at dedicated isolated Station #4 using purple color-coded utensils (`K2`), and hands safe dish to diner (`C3`).
4. **Compliance Logging & Audit**: Core Engine logs safety screening data (`S5`), and Dietitian audits allergen compliance reports (`H2`), completing execution (`Finish`).
