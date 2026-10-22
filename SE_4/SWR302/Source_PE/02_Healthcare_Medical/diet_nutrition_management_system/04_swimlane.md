# Swimlane Diagram — Diet & Nutrition Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Dietary Care Cycle"])

    subgraph Lane1["Patient / Inpatient"]
        A1["Check In & State Food Preferences"]
        A2["Consume Meal Tray & Log Intake %"]
    end

    subgraph Lane2["Clinical Dietitian"]
        B1["Assess BMR/TDEE & Prescribe Diet"]
        B2["Formulate Customized Meal Plan"]
    end

    subgraph Lane3["System / Macro & Kitchen Engine"]
        S1["Calculate Energy Target & Macro Ratios"]
        S2{"Allergen Conflict or Drug Interaction?"}
        S3["Substitute Food Item & Clear Safety Check"]
        S4["Generate Kitchen Tray Tickets & Barcode"]
        S5["Update Weight Progress & Malnutrition Log"]
    end

    subgraph Lane4["Hospital Kitchen Chef"]
        W1["Assemble Therapeutic Meal Trays"]
        W2["Scan Tray Barcode & Dispatch to Bed"]
    end

    Finish(["End Dietary Care Cycle"])

    Start --> A1 --> B1 --> S1 --> B2 --> S2
    S2 -->|"Yes"| S3 --> B2
    S2 -->|"No"| S4 --> W1 --> W2 --> A2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Patient / Inpatient | Checks in to outpatient clinic or hospital bed, states food preferences and allergies, consumes served therapeutic meal trays, and logs food intake percentage via mobile app. |
| 2 | Clinical Dietitian | Conducts anthropometric nutritional assessments, calculates BMR and TDEE energy needs, prescribes therapeutic diets (Diabetic, Renal, TPN), and formulates customized macro meal plans. |
| 3 | System / Macro & Kitchen Engine | Computes daily calorie and macronutrient targets, screens recipes against food allergy profiles and drug-nutrient interactions, generates kitchen tray tickets, and logs weight trends. |
| 4 | Hospital Kitchen Chef | Receives electronic diet orders, prints room tray assembly tickets, prepares texture-modified therapeutic meals, scans tray barcodes, and dispatches food carts to inpatient beds. |
