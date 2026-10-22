# Context Diagram — Compliance Management System

## Mermaid Code

```mermaid
flowchart LR
    CCO["Chief Compliance Officer (CCO) / Governance Director"]
    ComplianceAuditor["Compliance Audit Manager / Internal Risk Officer"]
    CorporateEmployee["Corporate Employee / Compliance Policy Subject"]
    CRO["Chief Risk Officer (CRO) / Audit Committee Chair"]
    RegulatoryFeedRegistry["Regulatory Change Monitoring Registry"]
    ERPSystem["Enterprise Resource Planning (ERP) Accounting System"]
    HRISRegistry["HRIS Employee Registry"]
    EthicsHotlineGateway["Whistleblower Hotline & Ethics Incident Gateway"]
    PolicyELearningPortal["Policy Management & E-Learning Portal"]
    ExternalRegulator["External Regulatory Audit & Supervisory Authority"]

    System(("Compliance Management System"))

    CCO -->|"inputs compliance framework rules, risk appetite limits & audit schedules"| System
    System -->|"provides enterprise compliance risk heatmaps, audit coverage & regulatory readiness scorecards"| CCO

    ComplianceAuditor -->|"submits internal audit findings, control testing logs & corrective action plans"| System
    System -->|"delivers control testing workflows, non-compliance alerts & remediation tracking"| ComplianceAuditor

    CorporateEmployee -->|"submits policy attestations, conflict disclosures & training completions"| System
    System -->|"delivers mandatory policy documents, compliance training modules & code of conduct"| CorporateEmployee

    CRO -->|"configures enterprise risk tolerance thresholds & audit committee reporting limits"| System
    System -->|"provides residual risk scores, compliance violation trends & executive committee decks"| CRO

    RegulatoryFeedRegistry -->|"streams regulatory update alerts, statutory rule changes & compliance deadlines"| System
    System -->|"returns regulatory keyword tracking rules & compliance gap analysis queries"| RegulatoryFeedRegistry

    ERPSystem -->|"transmits financial transaction logs, procurement vendor lists & payment records"| System
    System -->|"returns compliance hold flags, FCPA vendor screening receipts & audit logs"| ERPSystem

    HRISRegistry -->|"streams employee onboarding records, job roles & organizational hierarchies"| System
    System -->|"returns mandatory training assignment lists & policy attestation status"| HRISRegistry

    EthicsHotlineGateway -->|"streams anonymous whistleblower reports, ethics hotline tickets & incident feeds"| System
    System -->|"returns incident investigation assignments & resolution outcome logs"| EthicsHotlineGateway

    System -->|"submits policy publication updates & employee training completion tracking"| PolicyELearningPortal
    PolicyELearningPortal -->|"returns course completion timestamps, quiz scores & policy sign-off receipts"| System

    System -->|"submits regulatory compliance evidence binders & statutory audit reports"| ExternalRegulator
    ExternalRegulator -->|"returns regulatory examination findings, compliance certifications & fines"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Chief Compliance Officer (CCO) / Governance Director | Primary | Compliance framework definitions (ISO 37001, SOC 2, HIPAA, SOX), risk appetite limits, audit schedules, policy governance rules. | Enterprise compliance risk heatmaps, regulatory readiness scorecards, audit coverage metrics, policy sign-off summaries. | CCO defining corporate compliance frameworks, monitoring regulatory risk, and leading compliance audits. |
| 2 | Compliance Audit Manager / Internal Risk Officer | Primary | Internal audit findings, control effectiveness testing logs, non-compliance issue logs, corrective action remediation plans. | Control testing workflows, non-compliance alert digests, remediation tracking dashboards, audit evidence binders. | Compliance auditor testing internal controls, logging compliance gaps, and tracking remediation plans. |
| 3 | Corporate Employee / Compliance Policy Subject | Primary | Policy sign-off attestations, conflict-of-interest disclosures, gift/hospitality declarations, training completions. | Mandatory corporate policy documents, Code of Conduct, compliance e-learning modules, conflict disclosure forms. | Corporate employee reviewing policy changes, completing mandatory compliance training, and signing attestations. |
| 4 | Chief Risk Officer (CRO) / Audit Committee Chair | Primary | Enterprise risk tolerance thresholds, audit committee reporting formats, risk escalation triggers. | Residual compliance risk scores, compliance violation trend reports, executive committee reporting decks, risk matrices. | Executive reviewing enterprise risk management (ERM), regulatory exposure, and audit committee reports. |
| 5 | Regulatory Change Monitoring Registry | Supporting | Regulatory rule changes (Thomson Reuters Regulatory Intelligence / LexisNexis), statutory update alerts, compliance deadlines. | Regulatory keyword tracking rules, compliance impact gap queries, statutory monitoring parameters. | External registry streaming real-time statutory regulatory updates and legal compliance changes. |
| 6 | Enterprise Resource Planning (ERP) Accounting System | Supporting | Financial transaction ledgers, vendor master registries, accounts payable payment records. | Compliance hold flags, FCPA/anti-bribery vendor screening receipts, transaction audit logs. | Enterprise ERP supplying financial records for compliance auditing and applying compliance payment holds. |
| 7 | HRIS Employee Registry | Supporting | Employee onboarding records (Workday / SuccessFactors), job roles, department codes, reporting lines. | Mandatory training assignment lists, policy attestation completion status, compliance violation flags. | HRIS supplying organizational employee hierarchies to route policy sign-offs and training modules. |
| 8 | Whistleblower Hotline & Ethics Incident Gateway | Supporting | Anonymous whistleblower reports, ethics hotline tickets, fraud allegation feeds, incident timestamps. | Incident investigation assignment tasks, case status updates, resolution outcome logs. | Secure third-party ethics hotline streaming anonymous fraud, harassment, and compliance reports. |
| 9 | Policy Management & E-Learning Portal | Supporting | E-learning course completion receipts, quiz scores, policy digital sign-off tokens, training feedback. | Policy publication updates, employee course assignment payloads, mandatory training SCORM packages. | Learning Management System (LMS) hosting compliance training courses and collecting sign-offs. |
| 10 | External Regulatory Audit & Supervisory Authority | Supporting / Regulatory | Regulatory examination notices, compliance audit findings, statutory directives, administrative penalties. | Regulatory compliance evidence binders, statutory audit reports, compliance certification files. | Government regulatory bodies (SEC, FINRA, OSHA, EPA) conducting compliance examinations. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Compliance Management System** is an enterprise regulatory compliance framework, policy governance, internal control testing, employee policy attestation, whistleblower incident management, and regulatory change tracking platform designed to minimize corporate legal and financial liability.

**Inside System Scope:**
- Multi-framework compliance modeling supporting SOX, ISO 27001, ISO 37001, GDPR, HIPAA, and OSHA.
- Policy governance and automated employee attestation workflow tracking policy sign-offs across global business units.
- Internal control testing and gap remediation management tracking control effectiveness and corrective action plans.
- Integration with Regulatory Change feeds (Thomson Reuters) mapping statutory updates to internal policies.
- Whistleblower incident triage and investigation management securing confidential ethics hotline reports.

**Outside System Scope:**
- Direct physical inspection of factory floor machinery safety (handled by On-Site Safety Inspectors).
- Direct hosting of external government regulatory servers (handled by Government Regulatory Agencies).
- Direct physical security guarding of corporate office buildings (handled by Corporate Physical Security).
