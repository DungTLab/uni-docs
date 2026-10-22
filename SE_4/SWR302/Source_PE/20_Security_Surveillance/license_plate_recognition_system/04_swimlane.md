# Swimlane Diagram — License Plate Recognition System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Vehicle Arrival Flow"])

    subgraph Lane1["Vehicle Driver"]
        D1["Drive Vehicle into Parking Entrance Lane"]
        D2["Trip Inductive Ground Loop Sensor"]
    end

    subgraph Lane2["LPR Camera Hardware"]
        C1["Flash IR Illuminator & Capture Image Crop"]
        C2["Execute Neural Network OCR Text Extraction"]
    end

    subgraph Lane3["System"]
        S1["Match Plate String against Whitelist & Hotlist"]
        S2{"Plate Matched?"}
        S3["Send Open Pulse Command to Barrier Controller"]
        S4["Keep Barrier Closed & Trigger Strobe Horn"]
        S5["Dispatch Real-Time SMS Alert to Police"]
        S6["Calculate Parking Fee & Record Exit Timestamp"]
    end

    subgraph Lane4["Barrier Gate Controller"]
        G1["Raise Barrier Gate Arm to 90 Degrees"]
        G2["Lower Barrier Gate Arm after Vehicle Exit"]
    end

    subgraph Lane5["Security Guard"]
        O1["Inspect Stolen Vehicle & Verify Driver Identity"]
    end

    Finish(["End Vehicle Gate Cycle"])

    Start --> D1 --> D2 --> C1 --> C2 --> S1 --> S2
    S2 -->|"Whitelisted / Paid"| S3 --> G1 --> G2 --> S6 --> Finish
    S2 -->|"Police Hotlist Hit"| S4 --> S5 --> O1 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Vehicle Driver | Drives vehicle into entry lane, tripping inductive ground loop sensors. |
| 2 | LPR Camera Hardware | Flashes IR illuminator, captures high-contrast plate image crops, and executes neural network OCR text extraction. |
| 3 | System | Matches plate strings against Whitelist and Police Hotlists, issues gate open pulse commands, calculates parking fees, and dispatches police SMS alerts. |
| 4 | Barrier Gate Controller | Receives relay pulse commands, raises barrier gate arms for entry, and lowers gate arms safely behind vehicles. |
| 5 | Security Guard | Inspects flagged stolen vehicles, verifies driver identity, and coordinates with law enforcement. |
