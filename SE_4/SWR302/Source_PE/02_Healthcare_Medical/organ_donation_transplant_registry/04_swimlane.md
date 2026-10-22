# Swimlane Diagram — Organ Donation & Transplant Registry

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Organ Offer & Matching Process"])

    subgraph Lane1["Organ Procurement Coordinator"]
        A1["Register Brain Death & Organ Viability"]
        A2["Initiate Automated Candidate Match Run"]
        A3["Dispatch Organ Transport & Express Courier"]
    end

    subgraph Lane2["HLA Lab Specialist"]
        B1["Perform HLA Allele Typing & PRA Assay"]
        B2["Conduct Physical Cross-Match Testing"]
    end

    subgraph Lane3["System / Matching & Logistics Engine"]
        S1["Run Deterministic Match Algorithm"]
        S2{"Physical Cross-Match Negative & Surgeon Accepted?"}
        S3["Bypass Candidate & Route to Rank 2"]
        S4["Issue Transport Manifest & Track Cold Ischemia"]
        S5["Log Graft Outcome & Sync OPTN Registry"]
    end

    subgraph Lane4["Transplant Surgeon"]
        C1["Review Match Offer & Candidate Dossier"]
        C2["Execute Transplant Surgery & Anastomosis"]
        C3["Chart Post-Op Graft Function & Follow-Up"]
    end

    Finish(["End Transplant & Graft Monitoring Cycle"])

    Start --> A1 --> B1 --> A2 --> S1 --> C1 --> B2 --> S2
    S2 -->|"No"| S3 --> S1
    S2 -->|"Yes"| A3 --> S4 --> C2 --> C3 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor / Subsystem | Role in Flow |
|------|-------------------|--------------|
| 1 | Organ Procurement Coordinator | Logs deceased donor brain death certification, registers donated organ viability, executes automated match runs, and coordinates organ courier transport. |
| 2 | HLA Lab Specialist | Performs high-resolution HLA allele typing (A, B, DR), Panel Reactive Antibody (PRA) assays, and conducts physical T-cell/B-cell cross-matching between donor and candidate. |
| 3 | System / Matching & Logistics Engine | Runs deterministic organ allocation algorithms (evaluating blood group, MELD/cPRA score, HLA match, geography), issues transport manifests, tracks GPS/temperature cold ischemia windows, and syncs national registries. |
| 4 | Transplant Surgeon | Reviews organ match offers, evaluates donor medical dossiers, accepts or declines offers, performs organ retrieval and implantation surgery, and tracks long-term graft survival. |
