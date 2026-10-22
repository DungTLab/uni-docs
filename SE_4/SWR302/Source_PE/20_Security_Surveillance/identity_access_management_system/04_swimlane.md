# Swimlane Diagram — Identity & Access Management (IAM) System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start SSO Login Flow"])

    subgraph Lane1["Corporate Employee"]
        U1["Access SaaS App & Click Corporate SSO"]
        U2["Enter Primary Domain Credentials"]
        U3["Complete MFA Push Notification"]
    end

    subgraph Lane2["System"]
        S1["Parse SAML AuthNRequest"]
        S2["Evaluate Adaptive Risk Score"]
        S3{"High Risk Detected?"}
        S4["Send Push Challenge to MFA Gateway"]
        S5["Validate Password & MFA Response"]
        S6["Generate Cryptographic SAML Assertion"]
    end

    subgraph Lane3["MFA Gateway"]
        M1["Deliver Push Prompt to Mobile Phone"]
        M2["Return MFA Verification Token"]
    end

    subgraph Lane4["SaaS Application"]
        A1["Validate SAML 2.0 Digital Signature"]
        A2["Grant App Access & Render Dashboard"]
    end

    Finish(["End Authentication Flow"])

    Start --> U1 --> S1 --> U2 --> S2 --> S3
    S3 -->|"Yes"| S4 --> M1 --> U3 --> M2 --> S5
    S3 -->|"No"| S5
    S5 --> S6 --> A1 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Employee | Initiates login at target SaaS app, enters primary corporate domain credentials, and approves step-up MFA push prompts. |
| 2 | System | Parses incoming SAML AuthNRequest payloads, evaluates adaptive login risk scores, validates domain passwords, and generates signed SAML 2.0 assertions. |
| 3 | MFA Gateway | External MFA provider delivering push notification prompts to employee mobile devices and returning verified authentication tokens. |
| 4 | SaaS Application | Validates incoming SAML assertion signatures, provisions local session cookies, and renders application dashboards. |
