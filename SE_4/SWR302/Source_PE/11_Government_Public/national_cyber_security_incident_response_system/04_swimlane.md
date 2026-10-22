# Swimlane Diagram — National Cyber Security Incident Response System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Targeted Government Entity"]
        A1["Detect Abnormal Server Slowdown / Defacement"]
        A2["Submit Emergency Incident Ticket & Logs"]
        A3["Execute System Restoration from Backups"]
    end

    subgraph Lane2["National Response Platform (NCSC)"]
        S1["Ingest SIEM Logs & Run AI APT Detector"]
        S2["Match STIX IOC Threat Intelligence Feeds"]
        S3{"APT Campaign & Ransomware Verified?"}
        S4["Send Auto BGP Blackhole / Sinkhole to ISPs"]
        S5["Publish Post-Mortem Incident Report"]
    end

    subgraph Lane3["Cyber Security Incident Commander"]
        O1["Order Immediate Network Segment Quarantine"]
        O2["Dispatch On-Site CERT Response Team"]
        O3["Extract RAM Memory Dump & Disk Images"]
    end

    subgraph Lane4["Military Cyber Command (Command 86)"]
        B1["Co-Protect Military Defense Infrastructure"]
        B2["Analyze Zero-Day Malware in Sandbox"]
    end

    Finish(["End"])

    Start --> A1 --> A2 --> S1 --> S2 --> S3
    S3 -->|"No / Minor"| S1
    S3 -->|"Yes / Severe APT"| O1 --> S4 --> O2 --> B1 --> B2 --> O3 --> A3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Targeted Government Entity | Phát hiện dấu hiệu bất thường trên hệ thống, nộp báo cáo sự cố khẩn cấp đính kèm log và thực hiện khôi phục từ bản sao lưu. |
| 2 | National Response Platform (NCSC) | Thu thập SIEM log, đối soát chỉ số IOC tình báo đe dọa, chạy AI phát hiện APT và tự động gửi lệnh chặn C2 tới các nhà mạng. |
| 3 | Cyber Security Incident Commander | Ra lệnh cô lập khẩn cấp phân đoạn mạng bị nhiễm mã độc, điều động Đội CERT ứng cứu và thu thập chứng cứ kỹ thuật số. |
| 4 | Military Cyber Command (Command 86) | Phối hợp bảo vệ hạ tầng thông tin quốc phòng khẩn cấp và phân tích mẫu mã độc Zero-Day trong môi trường Sandbox. |
