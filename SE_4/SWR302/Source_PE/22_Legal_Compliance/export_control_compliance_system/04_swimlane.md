# Swimlane Diagram — Export Control Compliance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Export Order Fulfillment"])

    subgraph Lane1["Customs Manager"]
        M1["Initiate Sales Order Export Dispatch"]
        M2["Submit End-User Statement & Destination Info"]
        M3["Execute Shipping Clearance & AES Filing"]
    end

    subgraph Lane2["System"]
        S1["Execute Automated Denied Party Screening"]
        S2["Check ECCN & Country Control Matrix"]
        S3{"License Required?"}
        S4["Check Active Approved License Quota"]
        S5{"Valid License Available?"}
        S6["Place Automated Export Compliance Hold"]
        S7["Assign License ID & Release ERP Hold"]
        S8["Generate AES ITN Confirmation Number"]
    end

    subgraph Lane3["Export Compliance Officer"]
        O1["Review Order Hold & Licensing Block"]
        O2["Submit Government License Application"]
        O3["Verify Approved License Provisos"]
    end

    subgraph Lane4["Government Export Authority"]
        G1["Process License Application & Issue Permit"]
    end

    Finish(["End Export Shipping Fulfillment"])

    Start --> M1 --> S1 --> S2 --> S3
    S3 -->|"No"| S7
    S3 -->|"Yes"| S4 --> S5
    S5 -->|"Yes"| S7
    S5 -->|"No"| S6 --> O1 --> O2 --> G1 --> O3 --> S7
    S7 --> M2 --> M3 --> S8 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Customs Manager | Initiates international shipping fulfillment, submits buyer end-user statements, executes final customs export declarations, and receives AES ITN numbers. |
| 2 | System | Automates real-time denied party screening, checks Commerce Country Matrix for license requirements, verifies available license quota balances, locks non-compliant orders in ERP, and interfaces with AES Customs portal. |
| 3 | Export Compliance Officer | Reviews compliance order holds, drafts and submits government license applications (BIS SNAP-R), and verifies government proviso conditions upon permit issuance. |
| 4 | Government Export Authority | Federal export licensing agency (BIS / OFAC / DDTC) evaluating permit applications, conducting inter-agency reviews, and issuing approved export licenses. |
