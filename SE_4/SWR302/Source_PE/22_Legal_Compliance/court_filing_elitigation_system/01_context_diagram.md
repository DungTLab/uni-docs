# Context Diagram — Court Filing & E-Litigation System

## Mermaid Code

```mermaid
flowchart LR
    TrialAttorney["Lead Trial Attorney / Litigation Partner"]
    EFilingClerk["Electronic Filing Clerk / Legal Assistant"]
    OpposingCounsel["Opposing Counsel / Co-Counsel"]
    CLO["General Counsel / Chief Legal Officer (CLO)"]
    GovernmentCMECFGateway["Government Court CM/ECF E-Filing Gateway"]
    CertifiedEServiceProvider["Certified Electronic Service of Process Provider"]
    FilingFeePaymentGateway["Court Filing Fee Payment Gateway (Pay.gov)"]
    PDF ValidationEngine["Legal Redaction & PDF/A Technical Validation Engine"]
    TrialExhibitSubsys["Trial Exhibit & Witness Deposition Subsystem"]
    StateBarRegistry["State Judiciary Bar Association Registry"]

    System(("Court Filing & E-Litigation System"))

    TrialAttorney -->|"inputs pleading briefs, emergency motion requests, trial strategy & digital signatures"| System
    System -->|"provides court docket receipts (NEF Entry #42), oral argument calendars & judge trial rules"| TrialAttorney

    EFilingClerk -->|"submits court filing packages, exhibit attachments, fee codes & e-service recipient lists"| System
    System -->|"delivers PDF/A validation warnings, filing fee receipts & e-service proof of delivery"| EFilingClerk

    OpposingCounsel -->|"submits electronic service acceptances, counter-motions & joint discovery stipulations"| System
    System -->|"delivers e-served pleading packages, motion response deadlines & shared trial binders"| OpposingCounsel

    CLO -->|"configures litigation risk exposure caps, e-filing SLA rules & external litigation spend limits"| System
    System -->|"provides active court case counts, missed filing risk alerts & e-litigation spend scorecards"| CLO

    System -->|"submits electronic PDF/A pleadings, motions, emergency writs & filing fee tokens"| GovernmentCMECFGateway
    GovernmentCMECFGateway -->|"returns court docket numbers, Notice of Electronic Filing (NEF) receipts & hearing notices"| System

    System -->|"submits certified electronic service of process payloads & recipient email tokens"| CertifiedEServiceProvider
    CertifiedEServiceProvider -->|"returns certified e-service delivery receipts, email open tracking & proof-of-service logs"| System

    System -->|"submits court filing fee payment authorizations & case fee codes"| FilingFeePaymentGateway
    FilingFeePaymentGateway -->|"returns Pay.gov transaction receipts & court fee clearance confirmations"| System

    System -->|"submits draft PDF pleadings & exhibit files for technical compliance checking"| PDF ValidationEngine
    PDF ValidationEngine -->|"returns PDF/A-1b compliance verifications, page margin checks & un-redacted PII warnings"| System

    System -->|"submits trial exhibit lists, video deposition clips & Bates-stamped evidence"| TrialExhibitSubsys
    TrialExhibitSubsys -->|"returns trial exhibit binder links, deposition transcript sync & trial presentation slides"| System

    System -->|"submits attorney bar registration numbers & court pro hac vice admission tokens"| StateBarRegistry
    StateBarRegistry -->|"returns attorney active standing certifications & pro hac vice admission receipts"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Lead Trial Attorney / Litigation Partner | Primary | Pleading briefs, emergency motion requests, trial strategy notes, digital signatures, oral argument availability. | Court docket receipts (NEF Entry #42), judge trial rules, oral argument calendar invites, trial binders. | Lead litigation partner managing court cases, oral arguments, and electronic motion filings. |
| 2 | Electronic Filing Clerk / Legal Assistant | Primary | Court filing packages, exhibit attachments, filing fee codes, certified e-service recipient lists. | PDF/A validation warnings, filing fee payment receipts, certified e-service proof of delivery logs. | Legal assistant formatting PDF/A pleadings, managing court e-filing submissions, and logging fees. |
| 3 | Opposing Counsel / Co-Counsel | Primary | Electronic service of process acceptances, counter-motions, joint discovery stipulations, trial briefs. | E-served pleading packages, motion response deadline alerts, shared trial exhibit binders. | External opposing or co-counsel receiving certified electronic service of process and exchanging motions. |
| 4 | CLO | Primary | Litigation risk exposure caps, e-filing SLA rules, external litigation spend limits, emergency alert rules. | Active court case count, missed court filing risk alerts, e-litigation spend scorecards, litigation ROI. | Executive monitoring corporate court litigation exposure, court deadline SLAs, and litigation costs. |
| 5 | Government Court CM/ECF E-Filing Gateway | Supporting | Official court docket numbers, Notice of Electronic Filing (NEF) receipts, judge hearing notices, court orders. | Electronic PDF/A court pleadings, motions, emergency writs, filing fee payment tokens (Pay.gov). | Government court e-filing portals (Federal CM/ECF, State Court Tyler Odyssey APIs) processing dockets. |
| 6 | Certified Electronic Service of Process Provider | Supporting | Certified e-service delivery receipts, recipient email open tracking logs, statutory proof-of-service files. | Certified electronic service of process payloads, opposing counsel email tokens, service instructions. | Service provider (One Legal / InfoTrack) executing certified electronic service of process under court rules. |
| 7 | Court Filing Fee Payment Gateway | Supporting | Pay.gov transaction receipts, credit card authorization codes, court filing fee clearance confirmations. | Court filing fee payment authorizations, case filing fee codes, court account IDs. | Government payment gateway (Pay.gov / Tyler Payments) settling court filing fees and motion fees. |
| 8 | Legal Redaction & PDF/A Technical Validation Engine | Supporting | PDF/A-1b compliance verifications, page margin checks (1-inch), un-redacted PII warnings (SSN/Bank Acc). | Draft PDF pleading documents, exhibit attachments, PII redaction mask verification queries. | Technical validation engine verifying PDF/A formatting, page margins, and PII redactions prior to court filing. |
| 9 | Trial Exhibit & Witness Deposition Subsystem | Supporting | Trial exhibit binder links, video deposition transcript sync timestamps, trial presentation slide decks. | Trial exhibit lists, video deposition clips, Bates-stamped evidence files, witness subpoena logs. | Subsystem managing trial exhibit books, synchronized video deposition transcripts, and courtroom presentation decks. |
| 10 | State Judiciary Bar Association Registry | Supporting / Regulatory | Attorney active standing certifications, pro hac vice admission receipts, bar disciplinary status verification. | Attorney bar registration numbers, pro hac vice application tokens, court jurisdiction credentials. | Judicial bar association verifying attorney standing, bar admission numbers, and pro hac vice status. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Court Filing & E-Litigation System** is an enterprise court e-filing (CM/ECF), certified e-service of process, PDF/A compliance validation, court docketing, and trial exhibit management platform designed for court litigation.

**Inside System Scope:**
- Integration with Federal CM/ECF and State Court E-Filing APIs (Tyler Odyssey) for electronic pleading submissions.
- Certified Electronic Service of Process (e-Service) executing certified delivery to opposing counsel with proof-of-service logs.
- Technical PDF/A-1b validation engine enforcing court formatting rules (1-inch margins, embedded fonts, PII redaction).
- Pay.gov filing fee integration processing court motion fees and case opening payments.
- Trial exhibit management and synchronized video deposition transcript preparation for courtroom trials.

**Outside System Scope:**
- Direct physical trial proceedings in physical courtrooms (handled by Physical Judicial Courtrooms).
- Direct physical swearing-in of trial witnesses on the stand (handled by Courtroom Bailiffs).
- Direct physical jury deliberation behind closed doors (handled by Impaneled Juries).
