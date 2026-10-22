# Context Diagram — Data Breach Response Management System

## Mermaid Code

```mermaid
flowchart LR
    IncidentCommander["Data Breach Incident Commander / Chief Privacy Officer"]
    ForensicLead["Forensic Security Lead / Incident Response Analyst"]
    PrivacyCounsel["External Privacy Counsel / Data Protection Attorney"]
    CISO["Chief Information Security Officer (CISO) / Legal Director"]
    DPARegulators["Data Protection Authorities & Regulators"]
    AffectedSubjectsPortal["Affected Data Subjects & Customer Notification Portal"]
    SIEMSubsystem["Security Information & Event Management (SIEM) Subsystem"]
    CreditMonitoringService["Credit Monitoring & Identity Protection Service"]
    CyberInsuranceGateway["Cyber Insurance Claims & Risk Underwriting Gateway"]
    PRCrisisDesk["Public Relations & Crisis Communication Management Desk"]

    System(("Data Breach Response Management System"))

    IncidentCommander -->|"inputs incident triage parameters, statutory notification triggers & response playbooks"| System
    System -->|"provides 72-hour GDPR clock countdown, breach impact heatmaps & regulatory notification binders"| IncidentCommander

    ForensicLead -->|"submits compromised record counts, PII breach scope data & forensic containment logs"| System
    System -->|"delivers PII identification analysis, compromised data subject lists & forensics tasks"| ForensicLead

    PrivacyCounsel -->|"submits multi-jurisdictional breach notification legal opinions & privilege logs"| System
    System -->|"delivers statutory deadline matrices, regulatory reporting templates & attorney work-product logs"| PrivacyCounsel

    CISO -->|"configures cyber breach containment rules, SIEM ingestion thresholds & executive alerts"| System
    System -->|"provides breach exposure scorecards, forensics containment SLAs & cyber risk metrics"| CISO

    System -->|"submits electronic breach notification filings (GDPR Art 33 / HIPAA / State AG)"| DPARegulators
    DPARegulators -->|"returns regulatory filing receipts, formal investigation notices & compliance inquiries"| System

    System -->|"submits batch customer notification mailings, breach notices & call center scripts"| AffectedSubjectsPortal
    AffectedSubjectsPortal -->|"returns notification delivery receipts, email bounce reports & identity redemption logs"| System

    SIEMSubsystem -->|"streams security alert feeds, compromised IP logs & exfiltrated database schemas"| System
    System -->|"returns incident correlation queries, PII data matching requests & containment flags"| SIEMSubsystem

    System -->|"submits affected data subject rosters for credit monitoring code generation"| CreditMonitoringService
    CreditMonitoringService -->|"returns 12-month credit monitoring activation codes & redemption confirmation logs"| System

    System -->|"submits cyber incident loss estimates, forensic invoice proofs & claim notifications"| CyberInsuranceGateway
    CyberInsuranceGateway -->|"returns insurance claim tracking numbers, coverage authorizations & claim payout logs"| System

    System -->|"submits approved public holding statements & breach response FAQ briefs"| PRCrisisDesk
    PRCrisisDesk -->|"returns media inquiry logs, public press release distribution receipts & sentiment scores"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Data Breach Incident Commander / Chief Privacy Officer | Primary | Incident triage parameters, statutory notification triggers (GDPR 72-Hour Rule), response playbooks, escalation decisions. | 72-hour GDPR clock countdown, breach impact heatmaps, regulatory notification binders, triage status. | Executive commanding data breach response, privacy compliance, and statutory notification timelines. |
| 2 | Forensic Security Lead / Incident Response Analyst | Primary | Compromised record counts, PII breach scope data, exfiltrated database schemas, forensic containment logs. | PII identification analysis, compromised data subject lists, forensic investigation task assignments. | Security analyst investigating cyber breach forensics, exfiltrated records, and PII containment. |
| 3 | External Privacy Counsel / Data Protection Attorney | Primary | Multi-jurisdictional breach notification legal opinions, privilege logs, regulatory reporting strategies. | Statutory deadline matrices (72h GDPR / 60d HIPAA / 30d State AG), regulatory filing draft packages. | External privacy attorney evaluating breach notification obligations across global jurisdictions. |
| 4 | CISO | Primary | Cyber breach containment rules, SIEM alert ingestion thresholds, executive alert escalation rules. | Breach exposure scorecards, forensics containment SLAs, cyber risk metrics, legal liability estimates. | Executive supervising cyber security containment, SIEM alert ingestion, and C-Suite breach escalations. |
| 5 | Data Protection Authorities & Regulators | Supporting | Regulatory filing receipts, formal investigation notices, regulatory inquiry letters, compliance orders. | Electronic breach notification filings (GDPR Art 33 / HIPAA Breach Report / US State AG notifications). | Data protection regulators (EU DPAs, US FTC, HHS OCR, State Attorneys General) receiving breach notifications. |
| 6 | Affected Data Subjects & Customer Notification Portal | Supporting | Notification delivery receipts, email bounce reports, identity protection redemption logs, opt-out requests. | Batch customer notification emails, formal breach notice letters (PDF), call center FAQ scripts. | Portal executing mass notification mailings to affected customers whose PII was compromised. |
| 7 | Security Information & Event Management (SIEM) Subsystem | Supporting | Real-time security alert feeds, compromised IP logs, exfiltrated database schemas, endpoint telemetry. | Incident correlation queries, PII data matching requests, SIEM containment flags. | External SIEM platform (Splunk / CrowdStrike) feeding security alerts and exfiltration logs to the system. |
| 8 | Credit Monitoring & Identity Protection Service | Supporting | 12-month credit monitoring activation codes, redemption confirmation logs, fraud alert receipts. | Affected data subject rosters, credit monitoring code generation requests, campaign parameters. | Identity protection vendor (Experian / Equifax) issuing credit monitoring activation codes for breach victims. |
| 9 | Cyber Insurance Claims & Risk Underwriting Gateway | Supporting | Insurance claim tracking numbers, coverage authorizations, forensic reimbursement payout logs. | Cyber incident loss estimates, forensic invoice proofs, ransom payout logs, breach claim notifications. | Cyber insurance carrier API (AIG / Chubb) processing breach response costs and insurance claims. |
| 10 | Public Relations & Crisis Communication Management Desk | Supporting / Operations | Media inquiry logs, public press release distribution receipts, public sentiment analysis scores. | Approved public holding statements, executive Q&A briefs, breach response press release drafts. | Corporate PR team managing public messaging, press releases, and media inquiries during a breach crisis. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Data Breach Response Management System** is an enterprise cyber incident response, 72-hour GDPR clock tracking, PII exfiltration analysis, multi-jurisdictional regulatory notification, mass customer notification, and cyber insurance claim platform.

**Inside System Scope:**
- Active 72-hour GDPR countdown timer enforcing strict statutory notification deadlines for EU DPAs.
- PII & PHI exfiltration analysis engine parsing compromised database schemas to identify affected data subjects.
- Multi-jurisdictional notification matrix evaluating breach laws across 50 US States, HIPAA, and international DPAs.
- Automated mass customer breach notification generator with Experian credit monitoring code integration.
- Cyber insurance claim tracker consolidating forensic costs, legal counsel fees, and notification expenses.

**Outside System Scope:**
- Direct physical technical containment of malware on infected servers (handled by SOC Security Engineers).
- Direct physical negotiation with ransomware extortion threat actors (handled by Specialized Extortion Negotiators).
- Direct physical credit report score updates on individual consumer credit files (handled by Credit Bureaus).
