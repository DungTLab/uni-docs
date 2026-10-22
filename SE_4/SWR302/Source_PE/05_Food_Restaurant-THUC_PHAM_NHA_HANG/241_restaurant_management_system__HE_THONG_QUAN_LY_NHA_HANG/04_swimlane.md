# Swimlane Diagram — Restaurant Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Customer"]
        C1["Scan Table QR & Browse Menu"]
        C2["Select Items & Submit Order"]
        C3["Receive & Enjoy Meal"]
        C4{"Order More Items?"}
        C5["Select Payment Method & Pay"]
    end

    subgraph Lane2["Waiter (Front-of-House)"]
        W1["Verify Table Seating & Guest Count"]
        W2["Review & Confirm QR Order"]
        W3["Deliver Prepared Dishes to Table"]
        W4["Present Bill Summary to Guest"]
    end

    subgraph Lane3["Kitchen Staff / KDS (Back-of-House)"]
        K1["Receive Ticket on KDS Screen"]
        K2["Prepare Food according to Recipe"]
        K3["Mark Dishes as Ready to Serve"]
    end

    subgraph Lane4["POS & Billing System"]
        S1["Route Items to Kitchen/Bar KDS"]
        S2["Update Active Table Bill Subtotal"]
        S3["Calculate Tax, Service Fee & Discount"]
        S4["Process Payment & Issue E-Invoice"]
        S5["Close Table Session & Release Table"]
    end

    Finish(["End"])

    Start --> W1 --> C1 --> C2 --> W2
    W2 --> S1 --> K1 --> K2 --> K3
    K3 --> W3 --> C3 --> C4
    C4 -->|"Yes"| C2
    C4 -->|"No (Request Bill)"| W4
    W4 --> S3 --> C5 --> S4 --> S5 --> Finish
```

## Flow Description | Mo ta luong

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| **Lane 1** | Customer | Scans table QR code, selects menu items, submits dine-in order, consumes meal, decides on repeat orders or bill settlement, and completes payment. |
| **Lane 2** | Waiter (Front-of-House) | Verifies guest table assignment, confirms order details on handheld POS, picks up ready food from kitchen pass counter, serves dishes, and presents final bill summary. |
| **Lane 3** | Kitchen Staff / KDS | Receives routed order tickets on Kitchen Display System, prepares food according to standard recipes, and flags ticket status as ready for server pickup. |
| **Lane 4** | POS & Billing System | Dispatches ticket items to designated kitchen stations, maintains real-time table bill subtotals, computes VAT and service fees, processes payment authorizations, and releases table inventory upon session closure. |

### Process Execution Steps | Cac buoc thuc hien quy trinh

1. **Table Setup & Menu Access**: Waiter seats guests and verifies table seating (`W1`). Customer scans table QR code to open live digital menu (`C1`).
2. **Order Submission & Verification**: Customer selects menu options and submits order (`C2`). Waiter reviews order on POS and confirms dispatch (`W2`).
3. **Kitchen Processing**: POS System routes ticket items to KDS screens (`S1`). Kitchen Staff accepts ticket (`K1`), cooks food (`K2`), and marks dishes ready (`K3`).
4. **Food Delivery & Dining**: Waiter picks up prepared dishes and serves customer table (`W3`). Customer receives food and dines (`C3`).
5. **Decision Point**: Customer decides whether to add additional food/drink rounds (`C4`).
   - If **Yes**: Customer submits additional items (`C2`), repeating kitchen processing.
   - If **No**: Customer requests bill settlement (`W4`).
6. **Billing & Settlement**: POS System calculates taxes, service charges, and vouchers (`S3`). Customer completes payment via chosen tender method (`C5`). System records payment, issues tax e-invoice payload (`S4`), and closes table session (`S5`), ending the workflow (`Finish`).
