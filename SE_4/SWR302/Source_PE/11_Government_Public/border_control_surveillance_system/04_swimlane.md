# Swimlane Diagram — Border Control & Surveillance System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["International Traveler"]
        A1["Place e-Passport on Reader Kiosk"]
        A2["Step into e-Gate Biometric Booth"]
        A3["Look at AI Facial Camera"]
        A4["Walk Through Open Gate Exit"]
    end

    subgraph Lane2["e-Gate Kiosk Hardware"]
        K1["Read e-Passport ICAO Chip Data"]
        K2["Capture Live Facial Biometrics"]
    end

    subgraph Lane3["Border Control Platform Core"]
        S1["Query Interpol Red Notice & Ban List"]
        S2{"Interpol & Ban Watchlist Match?"}
        S3["Verify Facial Biometrics Match >98%"]
        S4["Record Digital Immigration Entry Stamp"]
        S5["Trigger Open e-Gate Barrier Signal"]
    end

    subgraph Lane4["Border Guard Officer"]
        O1["Receive Red Alarm & Lock e-Gate"]
        O2["Escort Suspect to Detention Room"]
    end

    Finish(["End"])

    Start --> A1 --> K1 --> S1 --> S2
    S2 -->|"Yes / Match"| O1 --> O2 --> Finish
    S2 -->|"No / Clean"| A2 --> A3 --> K2 --> S3 --> S4 --> S5 --> A4 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | International Traveler | Đặt hộ chiếu chíp lên máy đọc, bước vào buồng sinh trắc học e-Gate, nhìn vào camera quét mặt và đi qua cổng. |
| 2 | e-Gate Kiosk Hardware | Đọc dữ liệu chíp ICAO trên hộ chiếu điện tử, tự động chụp ảnh sinh trắc học khuôn mặt thực tế của hành khách. |
| 3 | Border Control Platform Core | Truy vấn CSDL Interpol & Danh sách cấm xuất nhập cảnh, đối soát sinh trắc học khuôn mặt, ghi nhận tem số và mở cổng. |
| 4 | Border Guard Officer | Nhận báo động Đỏ khi phát hiện đối tượng truy nã/cấm xuất cảnh, tự động khóa e-Gate và áp giải đối tượng vào phòng an ninh. |
