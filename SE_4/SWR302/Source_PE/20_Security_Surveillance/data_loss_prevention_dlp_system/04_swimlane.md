# Swimlane Diagram — Data Loss Prevention (DLP) System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Endpoint File Copy Handling"])

    subgraph Lane1["Corporate Employee"]
        E1["Attempt Copying File to USB Flash Drive"]
        E2["Select Business Justification Reason"]
    end

    subgraph Lane2["Endpoint DLP Agent"]
        A1["Hook OS File System Copy Event"]
        A2["Extract Content & Match Sensitive Patterns"]
        A3{"Pattern Matched?"}
        A4{"Policy Allows Justification?"}
        A5["Display Desktop Justification Prompt Modal"]
        A6["Abort OS File Copy & Delete Target File"]
        A7["Release File Lock & Complete Copy"]
    end

    subgraph Lane3["System"]
        S1["Capture Forensic Evidence Snapshot"]
        S2["Send Incident Notification to DLP Admin & Manager"]
    end

    subgraph Lane4["DLP Security Admin"]
        D1["Review Incident Ticket & Redacted Snippet"]
        D2["Mark Ticket Approved or Escalate to DPO"]
    end

    Finish(["End DLP Prevention Cycle"])

    Start --> E1 --> A1 --> A2 --> A3
    A3 -->|"No Match"| A7 --> Finish
    A3 -->|"Sensitive Match"| A4
    A4 -->|"Strict Block"| A6 --> S1 --> S2 --> D1 --> D2 --> Finish
    A4 -->|"Prompt Justification"| A5 --> E2 --> S1 --> S2 --> A7 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Employee | Attempts copying a sensitive file to a USB flash drive and inputs operational business justification if prompted. |
| 2 | Endpoint DLP Agent | Hooks local OS file system I/O, extracts text streams, matches sensitive patterns (PCI/PII/EDM), blocks non-compliant file copies, and displays popups. |
| 3 | System | Captures redacted forensic evidence snippets, generates incident alerts, and emails notification summaries to managers and DLP admins. |
| 4 | DLP Security Admin | Reviews triaged incident tickets, inspects evidence snippets, confirms policy enforcement, or escalates severe leaks to the DPO. |
