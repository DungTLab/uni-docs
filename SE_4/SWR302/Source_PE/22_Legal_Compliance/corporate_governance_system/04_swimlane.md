# Swimlane Diagram — Corporate Governance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Corporate Governance & Board Meeting Lifecycle"])

    subgraph Lane_Director["Board Director"]
        D1["Review Encrypted Board Book & Cast Resolution Vote"]
        D2["Pre-Clear Stock Trade & Sign Unanimous Consent (UWC)"]
    end

    subgraph Lane_Secretary["Corporate Secretary / General Counsel"]
        S1["Compile Board Book Agenda & Draft Resolution #RES-08"]
        S2["Review SEC Form 8-K / Form 4 Package & Apply Digital Signature"]
    end

    subgraph Lane_System["Corporate Governance System"]
        Sys1["Encrypt Board Book (AES-256) & Enforce Blackout Window"]
        Sys2{"Passes Board Voting Majority & EDGAR iXBRL Validation?"}
        Sys3["Reject Resolution & Flag Blackout Violation Warning"]
        Sys4["Certify Passed Resolution & Convert Document to iXBRL"]
        Sys5["Update Governance Scorecard & Archive Minutes in Vault"]
    end

    subgraph Lane_Gateways["SEC EDGAR & Board Portal Gateways (Diligent / SEC)"]
        G1["Sync Board Book to Director Tablet Apps via Diligent API"]
        G2["Issue Official SEC EDGAR Accession Receipt (0001188412-26-000412)"]
    end

    Finish(["End Corporate Governance Cycle"])

    Start --> S1 --> G1 --> D1 --> D2 --> Sys1 --> Sys2
    Sys2 -->|"No / Voting Failed or Syntax Error"| Sys3 --> S1
    Sys2 -->|"Yes / Approved & Valid"| S2 --> Sys4 --> G2 --> Sys5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Board Director | Reviews encrypted board books on tablet apps, casts resolution votes (For/Against/Abstain), applies digital signatures to Unanimous Written Consents (UWC), and submits trade pre-clearance requests. |
| 2 | Corporate Secretary / General Counsel | Compiles board meeting binders, drafts resolutions, records corporate minutes, reviews D&O conflict disclosures, evaluates director independence, and approves statutory SEC filings. |
| 3 | Corporate Governance System | Applies AES-256 DRM encryption to board books, enforces quarterly insider trading blackout windows (SEC Rule 10b5-1), tabulates resolution votes against bylaws thresholds, converts documents to SEC iXBRL format, and updates governance scorecards. |
| 4 | SEC EDGAR & Board Portal Gateways (Diligent / SEC) | Syncs encrypted board books to directors' tablet devices via Diligent API, processes statutory filings on SEC EDGAR gateway, and issues official SEC Accession Receipts. |
