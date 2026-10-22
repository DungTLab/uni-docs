# Swimlane Diagram — Intrusion Detection & Prevention System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Live Packet Inspection"])

    subgraph Lane1["Network TAP / Sensor"]
        T1["Ingest Mirrored Packet Stream"]
        T2["Decode & Normalize Headers"]
    end

    subgraph Lane2["System"]
        S1["Execute Signature Matching Engine"]
        S2{"Signature Match Found?"}
        S3["Check Sensor Deployment Mode"]
        S4{"Mode = Inline IPS?"}
        S5["Drop Malicious Packet & Inject TCP Reset"]
        S6["Generate PCAP & Store Intrusion Alert"]
        S7["Log NetFlow Statistics"]
    end

    subgraph Lane3["Firewall Appliance"]
        F1["Inject Dynamic Block List Rule"]
    end

    subgraph Lane4["SOC Security Analyst"]
        A1["Inspect Real-Time Intrusion Alert"]
        A2["Analyze PCAP Payload & Confirm Exploit"]
    end

    Finish(["End Intrusion Prevention Cycle"])

    Start --> T1 --> T2 --> S1 --> S2
    S2 -->|"No"| S7 --> Finish
    S2 -->|"Yes"| S3 --> S4
    S4 -->|"Yes"| S5 --> F1 --> S6 --> A1 --> A2 --> Finish
    S4 -->|"No"| S6 --> A1 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Network TAP / Sensor | Ingests raw mirrored network packet streams, parses Ethernet/IP/TCP headers, and normalizes payloads. |
| 2 | System | Evaluates Snort/Suricata rules, checks sensor deployment mode (Inline vs Passive), drops inline packets, sends TCP Resets, and logs PCAP alerts. |
| 3 | Firewall Appliance | Receives automated API commands from IPS engine to dynamically block attacker IP addresses at the perimeter. |
| 4 | SOC Security Analyst | Reviews real-time intrusion alerts on SOC dashboard, analyzes captured PCAP hex payloads, and verifies exploit attempts. |
