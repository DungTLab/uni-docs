# Swimlane Diagram — Restaurant Staff Scheduling System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Restaurant Employee / Staff Member"]
        C1["Submit Weekly Availability & Time-Off Requests"]
        C2["Initiate Peer Shift Swap or Open Shift Claim"]
        C3["Clock In via Mobile Geofence or Facial Scanner"]
    end

    subgraph Lane2["Restaurant General Manager / Scheduler"]
        M1["Configure Target Labor Cost Percentage Budget"]
        M2["Review & Approve Shift Swaps or Overtime Flags"]
    end

    subgraph Lane3["HR & Payroll Officer"]
        P1["Audit Punch Variances & Export Payroll Timesheets"]
    end

    subgraph Lane4["Scheduling & Labor Compliance Core Engine"]
        S1["Fetch POS Hourly Sales Forecast & Generate AI Roster"]
        S2{"Passes Fair Workweek Laws & Overtime Limits?"}
        S3["Flag Predictive Pay Penalties & Clopening Rest Violations"]
        S4["Validate Geofence Bounds & Calculate Net Shift Hours"]
        S5["Export Verified Gross Hours & Premiums to Payroll API"]
    end

    Finish(["End"])

    Start --> C1 --> M1 --> S1 --> S2
    S2 -->|"No (Compliance Breach / Overtime Risk)"| S3 --> M2
    S2 -->|"Yes (Fully Compliant)"| C2 --> M2 --> C3 --> S4 --> P1 --> S5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Restaurant Employee / Staff Member | Submits weekly availability patterns, initiates peer shift swaps, claims open shifts, and clocks in via geofenced mobile GPS or facial scanners. |
| **Lane 2** | Restaurant General Manager / Scheduler | Configures labor cost percentage budgets (e.g., 28%), reviews AI-generated rosters, approves shift swaps, and overrides punch variances. |
| **Lane 3** | HR & Payroll Officer | Audits verified timecards, checks meal break compliance, verifies overtime calculations, and exports timesheets to payroll. |
| **Lane 4** | Scheduling & Labor Compliance Core Engine | Fetches POS sales forecasts, auto-generates shift rosters, enforces Fair Workweek/clopening laws, validates geofenced punches, and exports payroll data. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Availability & Demand Forecasting**: Restaurant Staff submits availability (`C1`). Manager sets target labor cost % budget (`M1`). Core Engine fetches POS sales forecasts and generates AI roster (`S1`).
2. **Labor Compliance Audit**: Core Engine audits schedule against Fair Workweek laws and overtime limits (`S2`).
   - If **No (Compliance Breach / Overtime Risk)**: Core Engine flags predictive pay penalties and clopening rest violations (`S3`), requiring Manager review (`M2`).
   - If **Yes (Fully Compliant)**: Staff initiates peer shift swap or claims open shift (`C2`), and Manager approves (`M2`).
3. **Time Clocking & Punch Validation**: Staff clocks in via mobile GPS geofence or facial scanner (`C3`). Core Engine validates geofence bounds and computes net shift hours (`S4`).
4. **Timesheet Reconciliation & Payroll**: HR/Payroll Officer audits punch variances (`P1`). Core Engine exports verified gross hours, overtime, and split-shift premiums to external payroll API (`S5`), completing execution (`Finish`).
