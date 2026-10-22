# Swimlane Diagram — Customer Lifetime Value (CLV) Analytics System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start CLV & Unit Economics Cycle"])

    subgraph Lane_Analyst["CLV Analyst / Retention Strategist"]
        A1["Configure BTYD Predictive Model Parameters & COGS Rates"]
        A2["Review Cohort Retention Heatmap & LTV Sensitivity"]
    end

    subgraph Lane_System["Customer Lifetime Value Analytics System"]
        S1["Ingest Transaction Ledgers & Deduct COGS Margins"]
        S2["Calculate Historical Net LTV & Composite RFM 555 Scores"]
        S3{"Is Account High LTV (> $10K) AND At-Risk (P(Alive) < 0.40)?"}
        S4["Flag Account as Healthy VIP & Log Routine Retention Tag"]
        S5["Flag Account as High-Value At-Risk VIP & Quantify Risk Value"]
        S6["Compute LTV:CAC Ratio (4.0x) & Net Retention Rate (112% NRR)"]
    end

    subgraph Lane_BTYDAI["Predictive BTYD AI Engine"]
        E1["Execute BG-NBD & Gamma-Gamma Machine Learning Regression"]
        E2["Return P(Alive) Churn Probability & 36-Month pLTV Forecast"]
    end

    subgraph Lane_CSM["Customer Success Manager (CSM)"]
        M1["Receive High-Priority Retention Task in Salesforce CRM"]
        M2["Execute Proactive VIP Retention Playbook & Log Outreach"]
    end

    Finish(["End CLV Analytics Cycle"])

    Start --> A1 --> S1 --> S2 --> E1 --> E2 --> S3
    S3 -->|"No / Healthy VIP"| S4 --> S6
    S3 -->|"Yes / At-Risk VIP"| S5 --> M1 --> M2 --> S6 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | CLV Analyst / Retention Strategist | Configures predictive BTYD model parameters (discount rates, time horizons), sets COGS margin rates, reviews cohort retention heatmaps, and runs LTV sensitivity simulations. |
| 2 | Customer Lifetime Value Analytics System | Ingests multi-year transaction ledgers, deducts product COGS, calculates historical net LTV and RFM 555 scores, evaluates at-risk VIP account thresholds ($LTV \ge \$10,000$ and $P(\text{Alive}) < 0.40$), calculates LTV:CAC ratios and Net Retention Rates (NRR), and renders scorecards. |
| 3 | Predictive BTYD AI Engine | Runs BG-NBD (frequency/recency) and Gamma-Gamma (monetary value) machine learning regression algorithms, returning individual $P(\text{Alive})$ probability scores and 36-month predicted $pLTV$ values. |
| 4 | Customer Success Manager (CSM) | Receives automated high-priority VIP retention tasks in CRM, executes proactive retention playbooks (executive outreach, expansion discount), and logs intervention results. |
