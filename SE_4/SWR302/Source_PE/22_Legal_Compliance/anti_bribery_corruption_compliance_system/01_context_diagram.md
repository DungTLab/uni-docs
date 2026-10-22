# Context Diagram — Anti-Bribery & Corruption Compliance System

## Mermaid Code

```mermaid
flowchart LR
    ABCOfficer["Chief Anti-Bribery & Corruption (ABC) Officer"]
    DueDiligenceAuditor["Vendor Due Diligence Auditor / Risk Analyst"]
    CorporateEmployee["Corporate Employee / Procurement Officer"]
    CRO["Chief Risk Officer (CRO) / Audit Committee Chair"]
    SanctionsPEPFeed["Global Sanctions & PEP Monitoring Feed"]
    ERPAPSystem["Enterprise ERP & Accounts Payable System"]
    WhistleblowerFCPA Gateway["Whistleblower Hotline & FCPA Incident Gateway"]
    IntermediaryPortal["Third-Party Intermediary Due Diligence Portal"]
    GiftApprovalRegistry["Gift, Entertainment & Travel Approval Registry"]
    DOJSECBoard["US Department of Justice (DOJ) & SEC Compliance Board"]

    System(("Anti-Bribery & Corruption Compliance System"))

    ABCOfficer -->|"inputs FCPA/UK Bribery Act rules, PEP risk thresholds & vendor due diligence policies"| System
    System -->|"provides FCPA risk heatmaps, high-risk vendor audit logs & compliance readiness scorecards"| ABCOfficer

    DueDiligenceAuditor -->|"submits third-party background checks, red flag evaluations & audit findings"| System
    System -->|"delivers automated PEP/Sanctions screening hits, vendor risk scores & approval workflows"| DueDiligenceAuditor

    CorporateEmployee -->|"submits gift/hospitality pre-approval requests, travel logs & FCPA attestations"| System
    System -->|"delivers gift approval/rejection receipts, FCPA policy rules & travel expense caps"| CorporateEmployee

    CRO -->|"configures enterprise bribery risk tolerance limits & audit committee reporting rules"| System
    System -->|"provides residual corruption risk metrics, FCPA violation trends & executive board decks"| CRO

    SanctionsPEPFeed -->|"streams real-time global sanctions lists (OFAC/EU/UN), PEP profiles & adverse media"| System
    System -->|"returns PEP screening queries, sanctions matching parameters & keyword filters"| SanctionsPEPFeed

    ERPAPSystem -->|"streams vendor invoice payment requests, general ledger ledgers & bank accounts"| System
    System -->|"returns compliance payment hold flags, cleared vendor screening tokens & AP audit logs"| ERPAPSystem

    WhistleblowerFCPA Gateway -->|"streams confidential bribery reports, FCPA hotline tickets & extortion feeds"| System
    System -->|"returns FCPA investigation assignments & resolution outcome logs"| WhistleblowerFCPA Gateway

    System -->|"submits third-party due diligence questionnaires & beneficial ownership queries"| IntermediaryPortal
    IntermediaryPortal -->|"returns completed DDQ forms, ultimate beneficial owner (UBO) records & DPA sign-offs"| System

    System -->|"submits employee gift declarations for automated monetary threshold validation"| GiftApprovalRegistry
    GiftApprovalRegistry -->|"returns pre-approval receipts & cumulative gift ledger reports"| System

    System -->|"submits FCPA compliance evidence binders & internal investigation audit logs"| DOJSECBoard
    DOJSECBoard -->|"acknowledges FCPA compliance audit submissions & regulatory clearance certifications"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Chief Anti-Bribery & Corruption (ABC) Officer | Primary | FCPA and UK Bribery Act compliance rules, Politically Exposed Person (PEP) risk thresholds, third-party due diligence rules, approval limits. | Enterprise FCPA risk heatmaps, high-risk vendor audit logs, compliance readiness scorecards, ABC audit coverage. | Executive leading corporate Anti-Bribery & Corruption (ABC) compliance, FCPA governance, and vendor risk control. |
| 2 | Vendor Due Diligence Auditor / Risk Analyst | Primary | Third-party background check reviews, red flag evaluations, ultimate beneficial ownership (UBO) audits, remediation plans. | Automated PEP/Sanctions screening hits, vendor risk scores (High/Med/Low), multi-tier approval workflows. | Analyst auditing third-party sales agents, distributors, and vendors for bribery risks before contracting. |
| 3 | Corporate Employee / Procurement Officer | Primary | Gift/entertainment/hospitality pre-approval requests, government official travel logs, annual FCPA policy attestations. | Gift approval/rejection notifications, FCPA policy guidelines, monetary gift expense caps, attestation receipts. | Corporate employee requesting pre-approval for gifts, travel, or hospitality extended to government officials. |
| 4 | Chief Risk Officer (CRO) / Audit Committee Chair | Primary | Enterprise corruption risk tolerance limits, audit committee reporting templates, FCPA escalation triggers. | Residual bribery risk metrics, FCPA violation trend reports, executive committee decks, regulatory exposure. | Executive evaluating enterprise anti-bribery risk management (ERM) and audit committee reporting. |
| 5 | Global Sanctions & PEP Monitoring Feed | Supporting | Real-time sanctions lists (OFAC, EU, UN, UK HMT), Politically Exposed Persons (PEP) profiles, adverse news feeds. | PEP screening query payloads, sanctions matching parameters, fuzzy search keyword filters. | External registry (Refinitiv World-Check / LexisNexis) streaming real-time sanctions, PEP, and adverse media data. |
| 6 | Enterprise ERP & Accounts Payable System | Supporting | Vendor invoice payment requests, general ledger expense accounts, vendor bank account details. | Compliance payment hold flags, cleared FCPA vendor screening tokens, AP payment audit logs. | ERP accounting system (SAP / NetSuite) blocking AP payments to un-cleared high-risk vendors or government entities. |
| 7 | Whistleblower Hotline & FCPA Incident Gateway | Supporting | Confidential bribery allegations, FCPA hotline tickets, commercial extortion reports, incident timestamps. | FCPA investigation assignment tasks, case status updates, investigation resolution outcome logs. | Confidential ethics hotline streaming FCPA, UK Bribery Act, and commercial extortion incident reports. |
| 8 | Third-Party Intermediary Due Diligence Portal | Supporting | Due Diligence Questionnaire (DDQ) responses, Ultimate Beneficial Owner (UBO) declarations, signed ABC DPAs. | Third-party DDQ invitations, beneficial ownership verification queries, compliance certification links. | Portal collecting due diligence questionnaires and beneficial ownership disclosures from sales intermediaries. |
| 9 | Gift, Entertainment & Travel Approval Registry | Supporting | Employee gift declarations, government official hospitality logs, cumulative annual gift totals. | Pre-approval receipts, monetary threshold validation receipts, cumulative gift ledger reports. | Registry managing pre-approvals for gifts, travel, and entertainment extended to government officials ($> \$50$). |
| 10 | US Department of Justice (DOJ) & SEC Compliance Board | Supporting / Regulatory | FCPA corporate enforcement guidelines, DOJ evaluation of corporate compliance programs, audit directives. | FCPA compliance evidence binders, internal investigation audit logs, regulatory clearance certificates. | Enforcement authorities (DOJ & SEC) enforcing Foreign Corrupt Practices Act (FCPA) and UK Bribery Act. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Anti-Bribery & Corruption Compliance System** is an enterprise FCPA, UK Bribery Act, third-party vendor due diligence, PEP/sanctions screening, gift/hospitality pre-approval, and whistleblower incident management platform designed to prevent bribery.

**Inside System Scope:**
- Automated screening of third-party vendors and sales intermediaries against OFAC, EU, UN sanctions, and PEP databases.
- Ultimate Beneficial Ownership (UBO) and Due Diligence Questionnaire (DDQ) workflow detecting hidden government ownership.
- Pre-approval workflow for gifts, entertainment, and travel extended to foreign government officials ($> \$50$).
- Automated Accounts Payable (AP) payment holds blocking disbursements to un-screened or red-flagged vendors.
- FCPA whistleblower incident triage and confidential investigation management meeting DOJ compliance standards.

**Outside System Scope:**
- Direct physical police investigations of foreign bribery operations (handled by Law Enforcement Agencies).
- Direct physical inspection of vendor factory premises (handled by On-Site Auditing Teams).
- Direct legal defense representation in federal criminal FCPA trials (handled by Defense Trial Counsel).
