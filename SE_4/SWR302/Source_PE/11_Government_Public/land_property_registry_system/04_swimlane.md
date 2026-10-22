# Swimlane Diagram — Land & Property Registry System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start"])

    subgraph Lane1["Property Owner / Buyer"]
        A1["Submit Land Transfer Application"]
        A2["Pay Property Transfer Tax & Fees"]
        A3["Receive New Digital Land Certificate"]
    end

    subgraph Lane2["Notary Public Office"]
        O1["Notarize Real Estate Sale Contract"]
        O2["Upload Contract XML to System"]
    end

    subgraph Lane3["Land Registry System Core"]
        S1["Verify Land Ownership & Encumbrance"]
        S2{"Lien or Freeze Exist?"}
        S3["Calculate Transfer Tax & Fee Orders"]
        S4["Update GIS Spatial & Ownership Database"]
        S5["Generate Signed Digital Certificate"]
    end

    subgraph Lane4["Land Registration Officer"]
        E1["Review Cadastral Boundary & Dossier"]
        E2["Approve Land Title Change"]
    end

    Finish(["End"])

    Start --> O1 --> O2 --> S1 --> S2
    S2 -->|"Yes / Frozen"| O1
    S2 -->|"No / Clean"| A1 --> E1 --> S3 --> A2 --> E2 --> S4 --> S5 --> A3 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Property Owner / Buyer | Ký hợp đồng chuyển nhượng tại phòng công chứng, nộp đơn sang tên đất đai, nộp tiền thuế/lệ phí trước bạ và nhận Sổ đỏ mới. |
| 2 | Notary Public Office | Công chứng hợp đồng mua bán/tặng cho bất động sản, mã hóa dữ liệu hợp đồng XML và đẩy lên CSDL đất đai. |
| 3 | Land Registry System Core | Tự động kiểm tra tình trạng thế chấp/phong tỏa của thửa đất, tính thuế đất, cập nhật biến động không gian GIS và cấp Sổ đỏ điện tử. |
| 4 | Land Registration Officer | Thẩm định tính pháp lý của hồ sơ, đối soát bản đồ ranh giới thửa đất địa chính và duyệt Quyết định cấp sang tên Sổ đỏ. |
