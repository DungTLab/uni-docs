# Context Diagram — National ID Card Issuance System

## Mermaid Code

```mermaid
flowchart LR
    Citizen["Citizen Applicant"]
    EnrollmentOfficer["ID Enrollment Officer"]
    CardFactory["Smart Card Personalization Factory"]
    PopulationDatabase["National Identity System (VNeID Core)"]
    PostalService["Postal & Courier Service"]
    PayGW["Payment Gateway"]
    PoliceAuthority["Police Verification Authority"]
    QualityInspector["Card Quality Control Inspector"]

    System(("National ID Card Issuance System"))

    Citizen -->|"submits ID card renewal/issuance request & fee"| System
    System -->|"returns application receipt, tracking code & physical ID card"| Citizen

    EnrollmentOfficer -->|"captures biometrics, verifies physical documents"| System
    System -->|"provides enrollment checklist & biometric quality score"| EnrollmentOfficer

    System -->|"transmits encrypted card personalization payload"| CardFactory
    CardFactory -->|"returns physical smart card production status & UID"| System

    System -->|"queries verified population record attributes"| PopulationDatabase
    PopulationDatabase -->|"returns master citizen demographics & PIN"| System

    System -->|"sends ID card shipping manifests & recipient address"| PostalService
    PostalService -->|"returns card delivery tracking status & POD signature"| System

    System -->|"sends card issuance fee payment order"| PayGW
    PayGW -->|"returns fee payment confirmation"| System

    System -->|"submits identity verification & background check dossier"| PoliceAuthority
    PoliceAuthority -->|"returns card issuance security clearance"| System

    QualityInspector -->|"submits physical card & chip test inspection results"| System
    System -->|"provides quality inspection queue & defect logging"| QualityInspector
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Citizen Applicant | Primary | ID card application form, old card (if renewal), fee payment | Application receipt code, delivery tracking ID, physical smart card | Công dân làm thủ tục cấp mới, cấp đổi, cấp lại thẻ CCCD |
| 2 | ID Enrollment Officer | Primary | Document verification status, captured biometrics & photo | Enrollment task queue, biometric quality feedback, signature check | Cán bộ công an tiếp nhận hồ sơ và thu nhận sinh trắc học |
| 3 | Smart Card Personalization Factory | Supporting | Card chip UID, laser engraving status, production completion | Encrypted card personalization payload, batch print orders | Nhà máy in phôi và cá thể hóa thẻ căn cước thông minh gắn chip |
| 4 | National Identity System (VNeID Core) | Supporting | Master demographic records, 12-digit PIN, household reference | Population data query, national PIN verification | Cơ sở dữ liệu quốc gia về dân cư cung cấp dữ liệu gốc |
| 5 | Postal & Courier Service | Supporting | Delivery status updates, proof-of-delivery (POD) digital signature | Package shipping manifest, citizen delivery address | Bưu điện Việt Nam thực hiện chuyển phát thẻ CCCD tận nhà |
| 6 | Payment Gateway | Supporting | Fee payment confirmation, transaction reference | ID card issuance fee amount, citizen application ID | Cổng thanh toán trực tuyến xử lý lệ phí cấp thẻ |
| 7 | Police Verification Authority | Regulatory | Security approval status, criminal check clearance | Dossier verification query, card issuance request details | Cơ quan công an có thẩm quyền duyệt cấp thẻ |
| 8 | Card Quality Control Inspector | Supporting | Physical card defect status, chip readability test score | Card quality inspection queue, defect log form | Cán bộ kiểm định chất lượng phôi thẻ và dữ liệu chíp |

## System Boundary Description | Mô tả Phạm vi Hệ thống

Hệ thống National ID Card Issuance System (Hệ thống Cấp phát Căn cước Công dân Quốc gia) chịu trách nhiệm quản lý quy trình sản xuất và cấp phát thẻ Căn cước thông minh gắn chip điện tử. Hệ thống bao gồm tiếp nhận đơn cấp thẻ, kiểm tra dữ liệu nhân khẩu gốc, thu nhận sinh trắc học, đẩy dữ liệu sang nhà máy cá thể hóa phôi thẻ (khắc laser và ghi chip PKI), kiểm định chất lượng và chuyển phát tới tay công dân qua bưu điện. Tuy nhiên, hệ thống KHÔNG trực tiếp vận hành máy móc cơ khí dập phôi nhựa và KHÔNG thay thế ứng dụng định danh số VNeID trên điện thoại di động.
