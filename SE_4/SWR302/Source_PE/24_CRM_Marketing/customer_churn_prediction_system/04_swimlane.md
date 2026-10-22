# Swimlane Diagram — Customer Churn Prediction System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Daily Customer Churn Scoring Cycle"])

    subgraph Lane_CSM["Customer Success Manager (CSM)"]
        M1["Review SHAP Value Root Causes (Seat Drop 22%)"]
        M2["Execute Executive Alignment Playbook & Log Outcome"]
    end

    subgraph Lane_System["Customer Churn Prediction System"]
        S1["Ingest Usage Telemetry & Support Ticket Signals"]
        S2["Transform Raw Signals into Engineered Feature Vectors"]
        S3{"Passes High-Risk Threshold (P(Churn) >= 0.70)?"}
        S4["Tag Account as Low Risk & Log Standard Health Metrics"]
        S5["Compute SHAP Root Cause Drivers & Generate Action Plan"]
        S6["Calculate Net Revenue Retention (105% NRR) & ARR Saved"]
    end

    subgraph Lane_MLEngine["XGBoost Machine Learning AI Engine"]
        E1["Execute XGBoost Model Inference (xgboost_v2.4.bin)"]
        E2["Return Churn Probability Score (P(Churn) = 0.84)"]
    end

    subgraph Lane_CRM["Salesforce CRM & Win-Back Platform"]
        C1["Dispatch Urgent High-Priority Retention Task (24h SLA)"]
        C2["Trigger Automated Win-Back Nurture Email Sequence"]
    end

    Finish(["End Churn Prediction Cycle"])

    Start --> S1 --> S2 --> E1 --> E2 --> S3
    S3 -->|"No / Low Risk"| S4 --> S6
    S3 -->|"Yes / High Risk"| S5 --> C1 --> M1 --> M2 --> C2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Customer Success Manager (CSM) | Reviews SHAP root-cause feature drivers (+0.38 seat drop, +0.24 unresolved tickets), executes recommended retention playbooks, conducts executive alignment meetings, and logs intervention outcomes. |
| 2 | Customer Churn Prediction System | Ingests daily usage telemetry and support tickets, transforms signals into engineered feature vectors, evaluates high-risk churn thresholds ($P \ge 0.70$), computes SHAP root causes, and measures Net Revenue Retention (NRR %). |
| 3 | XGBoost Machine Learning AI Engine | Executes model inference using trained XGBoost binary weights (`xgboost_v2.4.bin`), returning individual account churn probability scores ($P(\text{Churn}) \in [0.0, 1.0]$). |
| 4 | Salesforce CRM & Win-Back Platform | Receives automated task dispatches, creates urgent 24-hour SLA retention tasks in Salesforce, updates account tags to High Risk, and triggers automated win-back email sequences. |
