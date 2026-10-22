# Swimlane Diagram — Anti-Bribery & Corruption Compliance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Anti-Bribery Due Diligence & Payment Lifecycle"])

    subgraph Lane_Employee["Corporate Employee / Vendor"]
        E1["Submit Gift Pre-Approval Request or Vendor DDQ Form"]
        E2["Disclose Ultimate Beneficial Owners (UBO) & Direct Ownership"]
    end

    subgraph Lane_Auditor["Due Diligence Auditor / ABC Officer"]
        A1["Review PEP Screening Hits & UBO Ownership Structure"]
        A2["Resolve Red Flags & Certify Compliance Clearance"]
    end

    subgraph Lane_System["Anti-Bribery & Corruption Compliance System"]
        S1["Execute Automated PEP & Sanctions Screening via API"]
        S2{"Passes PEP Sanctions Screening & UBO Verification?"}
        S3["Enforce SAP AP Payment Block & Flag Red Flag Alert"]
        S4["Calculate Cumulative Gift Cap & Issue Pre-Approval Token"]
        S5["Update Enterprise FCPA Risk Scorecard & Clear Vendor"]
    end

    subgraph Lane_External["Refinitiv World-Check & ERP AP System (Sanctions / SAP)"]
        R1["Stream Global Sanctions & PEP Profile Screening Hits"]
        R2["Enforce Compliance Payment Hold on Vendor AP Invoices"]
    end

    Finish(["End ABC Compliance Cycle"])

    Start --> E1 --> E2 --> S1 --> R1 --> S2
    S2 -->|"No / PEP Hit or Red Flag"| S3 --> R2 --> A1 --> A2 --> S5
    S2 -->|"Yes / Cleared"| S4 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Employee / Vendor | Submits gift/hospitality pre-approval requests, completes Due Diligence Questionnaires (DDQ), and discloses Ultimate Beneficial Ownership (UBO) equity. |
| 2 | Due Diligence Auditor / ABC Officer | Reviews automated PEP/Sanctions screening hit reports, audits multi-tiered UBO corporate ownership structures, resolves red flags, and certifies compliance clearance. |
| 3 | Anti-Bribery & Corruption Compliance System | Runs automated fuzzy screening against global sanctions and PEP databases via API, evaluates monetary gift policy caps ($> \$50$), enforces automated SAP Accounts Payable payment holds, tracks 72-hour whistleblower incident timers, and computes executive FCPA scorecards. |
| 4 | Refinitiv World-Check & ERP AP System (Sanctions / SAP) | Streams real-time OFAC, EU, UN sanctions and PEP profile hits, and enforces automated SAP AP payment block codes (`COMPLIANCE_HOLD`) on un-cleared vendor invoices. |
