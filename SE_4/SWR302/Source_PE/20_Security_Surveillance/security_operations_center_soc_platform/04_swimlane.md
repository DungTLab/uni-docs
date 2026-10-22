# Swimlane Diagram — Security Operations Center (SOC) Platform

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start SOC Alert Handling"])

    subgraph Lane1["SIEM & Log Ingestion"]
        S1["Ingest Raw Security Events"]
        S2["Normalize OCSF Schema & Deduplicate"]
    end

    subgraph Lane2["System"]
        C1["Map Alert to MITRE ATT&CK Tactic"]
        C2["Enrich Alert with Asset & Threat Intel"]
        C3["Queue Alert in Analyst Workstation"]
        C4["Issue Host Isolation Command via EDR API"]
        C5["Calculate MTTD & MTTR Operational KPIs"]
    end

    subgraph Lane3["SOC Analyst"]
        A1["Inspect Process Tree & Telemetry"]
        A2["Classify Alert as True Positive Threat"]
        A3["Select SOAR Ransomware Containment Playbook"]
        A4["Document Containment Notes & Close Case"]
    end

    subgraph Lane4["SOAR Engine"]
        R1["Execute Parallel API Containment Actions"]
        R2["Confirm Host Isolation & Credential Lock"]
    end

    Finish(["End SOC Incident Process"])

    Start --> S1 --> S2 --> C1 --> C2 --> C3 --> A1 --> A2 --> A3 --> R1 --> R2 --> C4 --> A4 --> C5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | SIEM & Log Ingestion | Ingests multi-source security events, normalizes log fields into OCSF schema, and deduplicates redundant alerts. |
| 2 | System | Maps threat alerts to MITRE ATT&CK techniques, enriches IoCs with threat intelligence, queues alerts, executes host isolation API calls, and calculates MTTD/MTTR KPIs. |
| 3 | SOC Analyst | Inspects alert process execution trees, classifies threats as true positives, selects automated response playbooks, and documents containment notes. |
| 4 | SOAR Engine | Executes automated multi-platform response actions (host isolation, credential locks, firewall IP blocks) via API connectors. |
