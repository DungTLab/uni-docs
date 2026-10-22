# Use Case Diagram — Food Nutrition Label Generation System

## Mermaid Code

```mermaid
flowchart LR
    actor_Developer["Product Developer"]
    actor_Nutritionist["Nutrition Specialist"]
    actor_Designer["Packaging Designer"]
    actor_Admin["System Administrator"]
    actor_USDA["USDA FoodData API"]
    actor_Printer["Packaging Printer API"]

    subgraph SystemBoundary["Food Nutrition Label Generation System"]
        UC01(["UC01: Calculate Recipe Nutrient Values"])
        UC02(["UC02: Render FDA Nutrition Facts Format"])
        UC03(["UC03: Render EU & UK Traffic Light Format"])
        UC04(["UC04: Auto-Generate Ingredient List by Weight"])
        UC05(["UC05: Identify FALCPA & EU Allergen Flags"])
        UC06(["UC06: Validate Health & Nutrient Claims"])
        UC07(["UC07: Adjust Cooking Moisture & Fat Loss"])
        UC08(["UC08: Sync Raw Ingredients from USDA API"])
        UC09(["UC09: Export Print-Ready Vector Artworks"])
        UC10(["UC10: Calculate %DV & Serving Sizes RACC"])
        UC11(["UC11: Reconcile Multi-Language Translations"])
        UC12(["UC12: Audit Compliance vs FDA 21 CFR 101"])
        UC13(["UC13: Manage Dual-Column Labeling Format"])
        UC14(["UC14: Generate Front-of-Package Nutri-Score"])
        UC15(["UC15: View Recipe Cost & Label Analytics"])
        UC16(["UC16: Manage Roles & Jurisdiction Rules"])
    end

    actor_USDA --> UC08
    actor_Printer --> UC09

    actor_Developer --> UC01
    actor_Developer --> UC07
    actor_Developer --> UC10
    actor_Developer --> UC15

    actor_Nutritionist --> UC04
    actor_Nutritionist --> UC05
    actor_Nutritionist --> UC06
    actor_Nutritionist --> UC11
    actor_Nutritionist --> UC12
    actor_Nutritionist --> UC13
    actor_Nutritionist --> UC14

    actor_Designer --> UC02
    actor_Designer --> UC03
    actor_Designer --> UC09

    actor_Admin --> UC08
    actor_Admin --> UC16

    UC01 -.->|"<<include>>"| UC07
    UC01 -.->|"<<include>>"| UC10
    UC02 -.->|"<<include>>"| UC04
    UC02 -.->|"<<include>>"| UC05
    UC06 -.->|"<<extend>>"| UC01
```

## Actor Table | Bang Actor

| # | Actor | Actor Type | Role Description | Related Use Cases |
|---|-------|------------|------------------|-------------------|
| 1 | USDA FoodData API | Secondary System | Official agricultural database API supplying standardized raw ingredient nutritional profiles and FDC codes. | UC08 |
| 2 | Packaging Printer API | Secondary System | Commercial print press hardware API receiving print-ready vector nutrition panels (PDF/EPS) for package printing. | UC09 |
| 3 | Product Developer | Primary | Food R&D developer inputting recipe ingredient formulations, batch weights, cooking loss factors, and serving sizes. | UC01, UC07, UC10, UC15 |
| 4 | Nutrition Specialist | Primary | Regulatory nutritionist auditing label compliance against FDA 21 CFR 101, validating health claims, and managing allergens. | UC04, UC05, UC06, UC11, UC12, UC13, UC14 |
| 5 | Packaging Designer | Primary | Graphic designer selecting label formats (FDA, EU, UK Traffic Light), adjusting font layouts, and exporting vector artwork. | UC02, UC03, UC09 |
| 6 | System Administrator | Primary | Technical manager configuring regulatory jurisdiction rules (FDA 2016, EU 1169/2011), USDA API keys, user security roles, and audit logs. | UC08, UC16 |

## Use Case Table | Bang Use Case

| # | UC ID | Use Case Name | Primary Actor | Secondary Actor | Description | Priority |
|---|-------|---------------|---------------|-----------------|-------------|----------|
| 1 | UC01 | Calculate Recipe Nutrient Values | Product Developer | None | Aggregates 30+ macronutrients and micronutrients across all formulation ingredients, calculating exact values per serving. | High |
| 2 | UC02 | Render FDA Nutrition Facts Format | Packaging Designer | None | Renders official FDA 2016 vertical, tabular, or linear Nutrition Facts panel layouts matching strict font and border specifications. | High |
| 3 | UC03 | Render EU & UK Traffic Light Format | Packaging Designer | None | Generates European Union (EU 1169/2011) and UK Traffic Light (Red/Amber/Green) front-of-pack nutrition label graphics per 100g/100ml. | High |
| 4 | UC04 | Auto-Generate Ingredient List by Weight | Nutrition Specialist | None | Automatically sorts and formats the product ingredient declaration in descending order of predominance by weight, grouping sub-ingredients. | High |
| 5 | UC05 | Identify FALCPA & EU Allergen Flags | Nutrition Specialist | None | Scans recipe ingredients to identify major allergens (Milk, Eggs, Fish, Crustaceans, Tree Nuts, Peanuts, Wheat, Soy, Sesame), generating bold "Contains" statements. | High |
| 6 | UC06 | Validate Health & Nutrient Claims | Nutrition Specialist | None | Evaluates product formulation against regulatory thresholds to validate nutrient claims (e.g., "Low Sodium", "High Protein", "Gluten-Free"). | High |
| 7 | UC07 | Adjust Cooking Moisture & Fat Loss | Product Developer | None | Applies cooking yield factors (Moisture Evaporation loss %, Fat Absorption %) to accurately reflect nutritional changes after baking/frying. | High |
| 8 | UC08 | Sync Raw Ingredients from USDA API | System Admin | USDA FoodData API | Synchronizes raw ingredient nutrient profiles, FDC IDs, density values, and moisture levels from the USDA FoodData Central database. | High |
| 9 | UC09 | Export Print-Ready Vector Artworks | Packaging Designer | Packaging Printer API | Exports 100% vector nutrition panels and barcodes in PDF, EPS, and SVG formats for seamless integration into commercial packaging artwork. | High |
| 10 | UC10 | Calculate %DV & Serving Sizes RACC | Product Developer | None | Calculates Percent Daily Values (%DV) using FDA 2016 daily reference values and assigns Reference Amounts Customarily Consumed (RACC) serving sizes. | High |
| 11 | UC11 | Reconcile Multi-Language Translations | Nutrition Specialist | None | Translates ingredient lists and nutrition declarations into multiple target languages (e.g., English, Spanish, French, Vietnamese). | Medium |
| 12 | UC12 | Audit Compliance vs FDA 21 CFR 101 | Nutrition Specialist | None | Audits nutrition labels against FDA 21 CFR Part 101 regulatory rules, checking font size ratios, mandatory nutrients, and rounding rules. | High |
| 13 | UC13 | Manage Dual-Column Labeling Format | Nutrition Specialist | None | Generates dual-column nutrition panels displaying nutrient values "Per Serving" AND "Per Container" for multi-serving snack packages. | High |
| 14 | UC14 | Generate Front-of-Package Nutri-Score | Nutrition Specialist | None | Calculates European Nutri-Score grades (A/B/C/D/E) and Chilean black warning octagons for front-of-package food labeling. | Medium |
| 15 | UC15 | View Recipe Cost & Label Analytics | Product Developer | None | Displays analytical dashboards on recipe formulation cost per serving, nutritional balance breakdown, label audit status, and active SKUs. | High |
| 16 | UC16 | Manage Roles & Jurisdiction Rules | System Admin | None | Configures user security access roles, regulatory jurisdiction rule sets (FDA, EU, Health Canada, ANZFA), and audit logging policies. | High |

## Use Case Specification | Dac ta Use Case

---

### UC01 — Calculate Recipe Nutrient Values

| Field | Detail |
|-------|--------|
| **UC ID** | UC01 |
| **Use Case Name** | Calculate Recipe Nutrient Values |
| **Actor(s)** | Primary: Product Developer <br> Secondary: None |
| **Description** | Aggregates macronutrients and micronutrients (Calories, Total Fat, Saturated Fat, Trans Fat, Cholesterol, Sodium, Total Carbohydrates, Fiber, Total Sugars, Added Sugars, Protein, Vitamin D, Calcium, Iron, Potassium) across all formulation ingredients. |
| **Precondition** | 1. Formulation ingredient weights (grams) are entered. <br> 2. All ingredients are mapped to nutrient database profiles (UC08). |
| **Main Flow** | 1. Product Developer opens "Recipe Nutritional Formulation Workbench" for a new product (e.g., Artisan Chocolate Chip Cookies). <br> 2. Developer enters recipe ingredient quantities (Total Batch Weight = 1,000g): <br>    - Unbleached Wheat Flour: 400g <br>    - Unsalted Butter: 200g <br>    - Semi-Sweet Chocolate Chips: 200g <br>    - Cane Sugar: 100g <br>    - Whole Eggs: 90g <br>    - Baking Soda & Salt: 10g. <br> 3. Developer inputs Cooking Loss Factor (UC07): `12% Moisture Evaporation Loss during baking (Baked Batch Weight = 880g)`. <br> 4. Developer inputs Serving Parameters: `Serving Size = 30g (1 Cookie); Servings per Container = 29`. <br> 5. System queries ingredient nutrient database and sums nutrient mass values for all 1,000g of raw ingredients. <br> 6. System applies 12% moisture reduction factor, re-adjusting nutrient concentrations per 100g of baked product. <br> 7. System calculates Unrounded Nutrient Values per 30g Serving: <br>    - Calories: 142.6 kcal <br>    - Total Fat: 7.23g (Saturated Fat: 4.41g, Trans Fat: 0.08g) <br>    - Sodium: 84.2mg <br>    - Total Carbs: 18.1g (Fiber: 0.92g, Total Sugars: 9.8g, Added Sugars: 9.2g) <br>    - Protein: 1.95g. <br> 8. System applies FDA 21 CFR 101.9 mandatory rounding rules (UC10/UC12) (e.g., 142.6 kcal rounds to **140 Calories**; 7.23g fat rounds to **7g Total Fat**). <br> 9. System displays unrounded vs rounded nutrient summary table and saves formulation profile. |
| **Alternative Flow** | **AF1** — Chemical lab test override: <br> 1. Developer has official lab chemistry report for Trans Fat. <br> 2. Developer overrides calculated Trans Fat value with certified lab analytical result (0.00g). |
| **Exception Flow** | **EX1** — Unmapped ingredient nutrient gap: <br> 1. Semi-Sweet Chocolate Chips is missing Vitamin D value. <br> 2. System flags "NUTRIENT DATA GAP: Vitamin D missing for Chocolate Chips", prompting developer to input vendor specification value before finalizing label. |
| **Postcondition** | Recipe nutrient mass is aggregated, cooking loss is compensated, FDA rounding rules are applied, and serving totals are saved. |
| **Business Rule** | **BR1**: Recipe nutrient calculations MUST account for cooking moisture loss and fat absorption to reflect actual consumed food composition. <br> **BR2**: Mandatory FDA 2016 nutrients (Calories, Fat, Sat Fat, Trans Fat, Cholesterol, Sodium, Carbs, Fiber, Sugars, Added Sugars, Protein, Vit D, Calcium, Iron, Potassium) MUST be 100% populated without unmapped gaps. |

---

### UC02 — Render FDA Nutrition Facts Format

| Field | Detail |
|-------|--------|
| **UC ID** | UC02 |
| **Use Case Name** | Render FDA Nutrition Facts Format |
| **Actor(s)** | Primary: Packaging Designer <br> Secondary: None |
| **Description** | Renders official FDA 2016 compliant Nutrition Facts panel graphics in Vertical, Tabular, or Linear formats, enforcing mandatory typography, bolding, line rules, and % Daily Value layout standards. |
| **Precondition** | 1. Recipe nutrient values per serving are calculated and rounded (UC01). <br> 2. FDA 2016 regulatory ruleset is active (UC12). |
| **Main Flow** | 1. Packaging Designer opens "Label Layout Studio" for Artisan Chocolate Chip Cookies. <br> 2. Designer selects Target Regulatory Jurisdiction: `US FDA 2016 (21 CFR 101.9)`. <br> 3. Designer selects Label Layout Format: `Standard Vertical Box` (or Tabular for wide packages / Linear for small packages). <br> 4. System renders high-precision FDA 2016 Nutrition Facts Panel graphic displaying: <br>    - Title: **Nutrition Facts** (Bold, 16pt+ Franklin Gothic / Helvetica font) <br>    - Serving Info: **29 servings per container** <br>    - Serving Size: **Serving size 1 cookie (30g)** <br>    - Calories: **Calories 140** (Extra-large bold 22pt font) <br>    - % Daily Value Column Header: **% Daily Value*** <br>    - Mandatory Nutrients with %DV: <br>      - **Total Fat** 7g (9% DV) <br>      - Saturated Fat 4.5g (23% DV) <br>      - *Trans* Fat 0g <br>      - **Cholesterol** 15mg (5% DV) <br>      - **Sodium** 85mg (4% DV) <br>      - **Total Carbohydrate** 18g (7% DV) <br>      - Dietary Fiber 1g (4% DV) <br>      - Total Sugars 10g <br>      - Includes 9g Added Sugars (18% DV) <br>      - **Protein** 2g <br>    - Micronutrients: Vitamin D 0mcg (0% DV), Calcium 15mg (2% DV), Iron 0.8mg (4% DV), Potassium 45mg (0% DV) <br>    - Standard Footnote: "* The % Daily Value (DV) tells you how much a nutrient in a serving of food contributes to a daily diet. 2,000 calories a day is used for general nutrition advice." <br> 5. System validates font sizes, line weights (7pt thick line under Calories), and border padding. <br> 6. Designer previews graphic and approves layout for print artwork export (UC09). |
| **Alternative Flow** | **AF1** — Small package simplified format (< 12 sq inches printable area): <br> 1. Package area is small lip balm or snack stick. <br> 2. System renders FDA Linear Format: "Calories 140, Total Fat 7g (9% DV), Sat Fat 4.5g..." in a single continuous text row. |
| **Exception Flow** | **EX1** — Non-compliant font scaling error: <br> 1. Designer resizes label box causing "Calories" font to fall below mandatory 16pt minimum. <br> 2. System flags red layout warning "FDA VIOLATION: Calories font height must be >= 16pt", preventing artwork export until scale is corrected. |
| **Postcondition** | FDA 2016 Nutrition Facts panel graphic is rendered with 100% typographical accuracy and ready for artwork export. |
| **Business Rule** | **BR1**: FDA 2016 labels MUST feature "Calories" in prominent bold font (minimum 16pt height) and include "Added Sugars" as a distinct indented sub-line. <br> **BR2**: Border rules, thick dividing lines, font families, and indentations MUST strictly adhere to FDA 21 CFR 101.9 Appendix illustrations. |

---

### UC03 — Render EU & UK Traffic Light Format

| Field | Detail |
|-------|--------|
| **UC ID** | UC03 |
| **Use Case Name** | Render EU & UK Traffic Light Format |
| **Actor(s)** | Primary: Packaging Designer <br> Secondary: None |
| **Description** | Generates European Union (EU 1169/2011) compliant nutrition tables per 100g/100ml and UK Front-of-Pack Traffic Light color-coded summary graphics (Red/Amber/Green). |
| **Precondition** | 1. Recipe nutrient values (UC01) are calculated per 100g. <br> 2. Target market includes EU or UK distribution. |
| **Main Flow** | 1. Packaging Designer opens "Multi-Market Label Studio". <br> 2. Designer selects Target Regulatory Jurisdiction: `European Union (EU No 1169/2011)` and `UK Front-of-Pack Traffic Light`. <br> 3. System calculates nutrient values strictly per **100g** of product (EU mandatory standard): <br>    - Energy: 1988 kJ / 475 kcal <br>    - Fat: 24.1g (of which saturates: 14.7g) <br>    - Carbohydrate: 60.3g (of which sugars: 32.7g) <br>    - Protein: 6.5g <br>    - Salt: 0.70g (EU uses Salt = Sodium x 2.5, NOT Sodium). <br> 4. System renders EU Mandatory Nutrition Table: `Energy | Fat | of which saturates | Carbohydrate | of which sugars | Protein | Salt` in 6pt font table. <br> 5. System calculates UK Traffic Light Color-Coding Thresholds per 100g: <br>    - Energy: 143 kcal / 6% Reference Intake (Gray) <br>    - Fat (24.1g > 17.5g threshold) -> **HIGH / RED** <br>    - Saturates (14.7g > 5.0g threshold) -> **HIGH / RED** <br>    - Sugars (32.7g > 22.5g threshold) -> **HIGH / RED** <br>    - Salt (0.70g <= 1.5g threshold) -> **MEDIUM / AMBER**. <br> 6. System renders UK Traffic Light Front-of-Pack Graphic featuring 5 color-coded lozenge badges (1 Gray, 3 Red, 1 Amber) with % Reference Intake (%RI). <br> 7. Designer previews graphics and saves EU/UK packaging artwork layout. |
| **Alternative Flow** | **AF1** — Liquid beverage formatting (per 100ml): <br> 1. Product is an organic juice beverage. <br> 2. System calculates nutrient values per 100ml instead of per 100g. |
| **Exception Flow** | **EX1** — Incorrect use of "Sodium" instead of "Salt": <br> 1. User attempts to export EU label with "Sodium". <br> 2. System auto-converts Sodium (g) to Salt (g) via formula `Salt = Sodium * 2.5` to comply with EU 1169/2011 Article 30. |
| **Postcondition** | EU 100g nutrition table and UK Traffic Light color-coded lozenge graphics are generated in compliance with European laws. |
| **Business Rule** | **BR1**: EU nutrition declarations MUST express energy in both kilojoules (kJ) and kilocalories (kcal) per 100g or 100ml. <br> **BR2**: EU labels MUST declare "Salt" (calculated as Sodium x 2.5), whereas US FDA labels declare "Sodium" in milligrams. |

---

### UC04 — Auto-Generate Ingredient List by Weight

| Field | Detail |
|-------|--------|
| **UC ID** | UC04 |
| **Use Case Name** | Auto-Generate Ingredient List by Weight |
| **Actor(s)** | Primary: Nutrition Specialist <br> Secondary: None |
| **Description** | Automatically parses, sorts, and formats the product ingredient declaration in descending order of predominance by weight, expanding multi-component sub-ingredients in parentheses. |
| **Precondition** | 1. Recipe formulation ingredient weights and component breakdowns are loaded (UC01). <br> 2. Ingredient common/usual names are configured. |
| **Main Flow** | 1. Nutrition Specialist opens "Ingredient List & Allergen Generator". <br> 2. System analyzes raw formulation weights (Total Batch = 1,000g): <br>    - Wheat Flour: 400g (40.0%) <br>    - Butter: 200g (20.0%) <br>    - Chocolate Chips: 200g (20.0%) [Sub-ingredients: Cane Sugar, Chocolate Liquor, Cocoa Butter, Soy Lecithin, Vanilla] <br>    - Cane Sugar: 100g (10.0%) <br>    - Whole Eggs: 90g (9.0%) <br>    - Baking Soda: 6g (0.6%) <br>    - Sea Salt: 4g (0.4%). <br> 3. System sorts ingredients in descending order of weight (400g > 200g > 200g > 100g > 90g > 6g > 4g). <br> 4. System expands sub-ingredients inside parenthetical brackets for composite items (Chocolate Chips). <br> 5. System consolidates duplicate ingredients across components (e.g., Sugar in dough + Sugar in chocolate chips). <br> 6. System generates Legal Ingredient Declaration Text: <br>    `INGREDIENTS: Unbleached Wheat Flour, Unsalted Butter (Cream, Natural Flavor), Semi-Sweet Chocolate Chips (Cane Sugar, Chocolate Liquor, Cocoa Butter, Soy Lecithin, Vanilla Extract), Cane Sugar, Whole Eggs, Contains 2% or less of: Baking Soda, Sea Salt.` <br> 7. System verifies compliance with FDA 21 CFR 101.4 common name rules. <br> 8. Specialist reviews generated text and approves ingredient declaration for label rendering. |
| **Alternative Flow** | **AF1** — "Contains 2% or less of" grouping: <br> 1. Ingredients weighing <= 2% of total batch (Baking Soda 0.6%, Salt 0.4%) are grouped after the "Contains 2% or less of:" phrase in any order. |
| **Exception Flow** | **EX1** — Un-declared sub-ingredient in composite item: <br> 1. Chocolate Chips supplier spec is missing sub-ingredient breakdown. <br> 2. System flags warning "COMPOSITE INGREDIENT MISSING SUB-LIST: Chocolate Chips requires sub-ingredients", blocking label approval. |
| **Postcondition** | Legal ingredient declaration is auto-sorted by weight, sub-ingredients are expanded, and text is approved for packaging artwork. |
| **Business Rule** | **BR1**: Ingredients MUST be listed in strict descending order of predominance by weight as added to the food batch. <br> **BR2**: Multi-component ingredients (e.g., Chocolate, Margarine, Cheese) MUST declare all sub-components in parentheses following their common name. |

---

### UC05 — Identify FALCPA & EU Allergen Flags

| Field | Detail |
|-------|--------|
| **UC ID** | UC05 |
| **Use Case Name** | Identify FALCPA & EU Allergen Flags |
| **Actor(s)** | Primary: Nutrition Specialist <br> Secondary: None |
| **Description** | Scans all formulation ingredients and sub-components against US FALCPA / FASTER Act (9 Major Allergens) and EU 14 Major Allergens, generating bold "Contains" allergen warning statements. |
| **Precondition** | 1. Recipe formulation and sub-ingredient list (UC04) are generated. <br> 2. Master allergen taxonomy database (US 9 Major & EU 14 Major) is active. |
| **Main Flow** | 1. System initiates automated "Allergen Screening Audit" on the formulation ingredient list: <br>    `INGREDIENTS: Unbleached Wheat Flour, Unsalted Butter (Cream), Semi-Sweet Chocolate Chips (Soy Lecithin), Whole Eggs...` <br> 2. System screens ingredients against US FALCPA / FASTER Act 9 Major Allergens: <br>    - Wheat Flour -> Detects **WHEAT** <br>    - Unsalted Butter (Cream) -> Detects **MILK** <br>    - Soy Lecithin -> Detects **SOY** <br>    - Whole Eggs -> Detects **EGG** <br>    - (Peanuts, Tree Nuts, Fish, Crustaceans, Sesame -> NOT DETECTED). <br> 3. System generates US Compliant Bold Allergen Warning Statement: <br>    **CONTAINS: WHEAT, MILK, EGG, SOY.** <br> 4. System screens ingredients against EU 14 Major Allergens (adding Celery, Mustard, Lupin, Molluscs, Sulphites, Gluten): <br>    - Identifies **GLUTEN (from Wheat)**. <br> 5. System options for EU format: Renders ingredient text with allergens in **BOLD CAPITAL LETTERS**: <br>    `INGREDIENTS: Unbleached **WHEAT** Flour, Unsalted Butter (**MILK**), Semi-Sweet Chocolate Chips (**SOY** Lecithin), Whole **EGG**s...` <br> 6. Specialist reviews allergen flags, confirms accuracy against facility cross-contact risk assessments, and approves allergen warnings. |
| **Alternative Flow** | **AF1** — Precautionary "May Contain" cross-contact statement: <br> 1. Product is manufactured on shared equipment processing Peanuts. <br> 2. Specialist adds Precautionary Statement: **May Contain: Peanuts, Tree Nuts.** |
| **Exception Flow** | **EX1** — Hidden allergen in artificial flavor: <br> 1. Natural Flavoring contains a hidden Milk derivative (Whey). <br> 2. System allergen database identifies Whey under Natural Flavor, auto-triggering **MILK** allergen warning flag. |
| **Postcondition** | Major food allergens are accurately identified, and legal bold "Contains" warning statements are generated for package printing. |
| **Business Rule** | **BR1**: US FALCPA / FASTER Act requires explicit declaration of 9 Major Allergens (Milk, Egg, Peanut, Tree Nuts, Wheat, Soy, Fish, Crustacean Shellfish, Sesame). <br> **BR2**: Allergen declarations MUST use the clear common name of the food source (e.g., "Milk" instead of "Casein"; "Wheat" instead of "Spelt"). |
