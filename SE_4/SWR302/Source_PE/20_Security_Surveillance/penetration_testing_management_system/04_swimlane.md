# Swimlane Diagram — Penetration Testing Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Pen Test Remediation Flow"])

    subgraph Lane1["Lead Penetration Tester"]
        T1["Execute Ethical Exploitation on Target"]
        T2["Log Discovered Finding & Upload PoC Artifacts"]
        T3["Re-Test Target Endpoint & Verify Code Fix"]
    end

    subgraph Lane2["System"]
        S1["Verify Target IP Scope & RoE Compliance"]
        S2["Calculate CVSS v3.1 Score & Encrypt Artifacts"]
        S3["Create Developer Jira Remediation Ticket"]
        S4["Track SLA Deadline & Send Escalation Alert"]
        S5{"Re-Test Passed?"}
        S6["Close Jira Ticket & Update Campaign Status"]
        S7["Re-Open Jira Ticket & Flag Re-Test Failure"]
    end

    subgraph Lane3["Development Team Lead"]
        D1["Receive Assigned Remediation Ticket"]
        D2["Deploy Application Code Patch to Staging"]
        D3["Mark Ticket Ready for Re-Test Verification"]
    end

    subgraph Lane4["CISO Executive"]
        C1["Sign Off Compliance Attestation Letter"]
    end

    Finish(["End Remediation Verification"])

    Start --> T1 --> S1 --> T2 --> S2 --> S3 --> D1 --> D2 --> D3 --> T3 --> S5
    S5 -->|"Verified Fixed"| S6 --> C1 --> Finish
    S5 -->|"Fix Failed"| S7 --> D1
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Lead Penetration Tester | Conducts ethical exploitation, logs findings, uploads encrypted PoC evidence, and conducts re-testing verifications. |
| 2 | System | Validates scope boundaries, calculates CVSS risk scores, encrypts artifacts, generates Jira developer tickets, tracks SLA timers, and updates campaign status. |
| 3 | Development Team Lead | Receives assigned Jira tickets, deploys application code fixes to staging environments, and requests re-test verification. |
| 4 | CISO Executive | Reviews campaign completion status and signs formal PCI-DSS / ISO 27001 compliance attestation letters. |
