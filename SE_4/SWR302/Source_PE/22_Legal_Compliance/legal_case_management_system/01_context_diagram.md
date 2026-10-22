# Context Diagram — Legal Case Management System

## Mermaid Code

```mermaid
flowchart LR
    GeneralCounsel["General Counsel / Chief Legal Officer (CLO)"]
    SeniorLitigator["Senior Litigator / Managing Attorney"]
    ExternalCounsel["External Law Firm Partner / Co-Counsel"]
    ComplianceOfficer["Corporate Compliance & Risk Director"]
    CourtEFilingSystem["Court E-Filing & Judiciary Portal"]
    CRMSystem["Enterprise CRM & Client Account Registry"]
    EDiscoveryPlatform["E-Discovery & Evidence Management Platform"]
    LegalTimeBillingSubsys["Time Tracking & External Legal Billing Subsystem"]
    DocumentRepository["Secure Legal Document Repository & Vault"]
    AuditGovernanceBoard["Legal Audit & Bar Association Governance Board"]

    System(("Legal Case Management System"))

    GeneralCounsel -->|"inputs case litigation budgets, risk thresholds & legal strategy directives"| System
    System -->|"provides legal exposure scorecards, case win/loss ratios & external spend analytics"| GeneralCounsel

    SeniorLitigator -->|"submits case filings, court motion logs, witness rosters & trial schedules"| System
    System -->|"delivers case docket alerts, court deadline calendars & trial briefing binders"| SeniorLitigator

    ExternalCounsel -->|"submits case status updates, invoice fee notes & joint defense work product"| System
    System -->|"returns case assignment clearances, shared discovery files & court calendar sync"| ExternalCounsel

    ComplianceOfficer -->|"configures regulatory breach rules, litigation hold triggers & risk categories"| System
    System -->|"provides compliance litigation risk reports, audit trails & hold status logs"| ComplianceOfficer

    System -->|"submits e-filed court pleadings, motions & case filing fee payments"| CourtEFilingSystem
    CourtEFilingSystem -->|"returns court docket numbers, electronic service receipts & hearing notices"| System

    CRMSystem -->|"transmits corporate client profiles, counterparty records & contract IDs"| System
    System -->|"returns linked case matter records, litigation history & dispute tags"| CRMSystem

    System -->|"submits evidence collection queries & legal hold keyword parameters"| EDiscoveryPlatform
    EDiscoveryPlatform -->|"returns processed discovery document sets, privilege logs & bates numbers"| System

    ExternalCounsel -->|"submits LEDES time entry files & electronic fee invoices"| LegalTimeBillingSubsys
    LegalTimeBillingSubsys -->|"returns UTBMS code validation, invoice fee approvals & payment clearances"| System

    System -->|"submits confidential case filings & work product for encrypted storage"| DocumentRepository
    DocumentRepository -->|"returns versioned case documents, access audit logs & download URLs"| System

    System -->|"submits attorney conflict-of-interest logs & ethical wall compliance records"| AuditGovernanceBoard
    AuditGovernanceBoard -->|"acknowledges ethical compliance clearance & bar audit certification"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | General Counsel / Chief Legal Officer (CLO) | Primary | Litigation budget limits, financial exposure risk thresholds, external counsel allocation rules. | Legal exposure scorecards, litigation win/loss ratios, external legal spend analytics, matter portfolio summaries. | Chief Legal Officer overseeing corporate litigation strategy, financial risk, and external legal spend. |
| 2 | Senior Litigator / Managing Attorney | Primary | Court pleadings, motion filings, witness rosters, trial strategy notes, task assignments. | Case docket alerts, court deadline calendars (Rules of Civil Procedure), trial briefing binders. | Lead attorney managing active litigation matters, court appearances, and trial preparation. |
| 3 | External Law Firm Partner / Co-Counsel | Primary | External case status notes, electronic fee invoices (LEDES format), joint defense work product. | Case assignment clearances, shared discovery files, court calendar synchronization, matter budgets. | External outside counsel collaborating on active court cases and submitting legal invoices. |
| 4 | Corporate Compliance & Risk Director | Primary | Regulatory violation logs, litigation hold triggers, risk category classifications. | Compliance litigation risk reports, litigation hold compliance status logs, matter audit trails. | Compliance officer tracking regulatory investigations, internal audits, and litigation hold enforcement. |
| 5 | Court E-Filing & Judiciary Portal | Supporting | Court docket numbers, electronic service of process receipts, judge assignment notices, hearing dates. | Electronic court pleadings (PDF/A format), motions, case filing fee payment tokens. | Government judiciary portal handling electronic court filings and issuing official court dockets. |
| 6 | Enterprise CRM & Client Account Registry | Supporting | Corporate client master profiles, counterparty entity records, linked commercial contract IDs. | Linked legal matter records, historical dispute logs, pending litigation risk tags. | Enterprise CRM synchronizing corporate client records and tracking active litigation disputes. |
| 7 | E-Discovery & Evidence Management Platform | Supporting | Processed discovery document sets, privilege logs, Bates-stamped evidence files, search hit counts. | Evidence collection parameters, custodian lists, legal hold search keyword queries. | E-discovery platform processing digital evidence, custodian emails, and document review sets. |
| 8 | Time Tracking & External Legal Billing Subsystem | Supporting | UTBMS task code validation rules, LEDES 1998B fee invoice format rules, billing rate caps. | Approved LEDES fee invoices, timekeeper rate compliance reports, payment authorization logs. | Legal e-billing subsystem auditing outside counsel invoices against UTBMS billing guidelines. |
| 9 | Secure Legal Document Repository & Vault | Supporting | Encrypted file binary streams, document version histories, access audit logs, check-out locks. | Confidential case briefs, motion drafts, client communications, secure download tokens. | Encrypted legal document repository securing confidential work product and attorney-client communications. |
| 10 | Legal Audit & Bar Association Governance Board | Supporting / Regulatory | Ethical wall rules, attorney conflict-of-interest check standards, bar association audit guidelines. | Conflict-of-interest check logs, ethical wall isolation verifications, bar compliance audit reports. | Regulatory body enforcing conflict-of-interest checks, ethical walls, and legal professional standards. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Legal Case Management System** is an enterprise legal matter management, court e-filing, conflict-of-interest checking, e-billing audit, and litigation hold tracking platform designed to manage court cases, arbitration, and regulatory inquiries.

**Inside System Scope:**
- Centralized matter tracking managing case dockets, parties, judges, motion calendars, and court deadlines.
- Automated attorney conflict-of-interest checking across corporate entities, counterparties, and affiliates.
- Integration with Court E-Filing portals for electronic submission of pleadings, motions, and service of process.
- LEDES/UTBMS e-billing audit engine validating outside counsel invoices against corporate billing guidelines.
- Ethical wall governance isolating confidential case files between internal legal teams and outside partners.

**Outside System Scope:**
- Direct physical attendance at court trials (handled by Trial Attorneys in physical courtrooms).
- Direct physical hosting of government court databases (handled by Judicial Information Systems).
- Direct physical execution of police search warrants (handled by Law Enforcement Agencies).
