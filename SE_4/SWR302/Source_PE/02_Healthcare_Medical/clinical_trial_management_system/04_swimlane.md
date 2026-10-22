# Swimlane Diagram — Clinical Trial Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Clinical Trial Protocol Cycle"])

    subgraph Lane1["Trial Subject / Patient"]
        A1["Sign Electronic Informed Consent eICF"]
        A2["Attend Visit & Complete ePRO Log"]
    end

    subgraph Lane2["Principal Investigator / Site Coordinator"]
        B1["Verify Screening & Eligibility Criteria"]
        B2["Dispense RTSM IP Kit to Subject"]
        B3["Enter Clinical eCRF Data & Report SAE"]
    end

    subgraph Lane3["System / RTSM & EDC Engine"]
        S1["Verify Approved ICF & Generate Screening ID"]
        S2["Execute Double-Blind Randomization & Assign Kit"]
        S3{"Candidate Eligible & IP Kit Available?"}
        S4["Calculate Visit Window Offsets & Reconcile SAE"]
        S5["Publish CDISC SDTM Data Package & Close Site"]
    end

    subgraph Lane4["CRA Monitor / Sponsor Manager"]
        W1["Configure Protocol Schedule of Assessments"]
        W2["Perform CRA SDV Visit & Audit CAPA Issues"]
    end

    Finish(["End Clinical Trial Protocol Cycle"])

    Start --> W1 --> A1 --> S1 --> B1 --> S3
    S3 -->|"No"| B1
    S3 -->|"Yes"| S2 --> B2 --> A2 --> B3 --> W2 --> S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Trial Subject / Patient | Reviews trial information, signs electronic Informed Consent Forms (eICF), attends scheduled protocol visits, and logs daily electronic Patient-Reported Outcomes (ePRO). |
| 2 | Principal Investigator / Site Coordinator | Screens candidate eligibility against inclusion/exclusion rules, dispenses double-blind IP drug kits assigned by RTSM, enters eCRF clinical data, and files 24-hour SAE reports. |
| 3 | System / RTSM & EDC Engine | Verifies valid ICF signatures, executes double-blind randomization algorithms, selects matching IP drug kits, calculates visit window offsets, reconciles SAE safety records, and exports CDISC SDTM datasets. |
| 4 | CRA Monitor / Sponsor Manager | Configures trial protocols and Schedules of Assessments (SoA), approves site activations, conducts CRA site monitoring visits (SIV, IMV), performs Source Data Verification (SDV), and audits CAPA issues. |
