# Context Diagram — Campus Visitor Management System

## Mermaid Code

```mermaid
flowchart LR
    Actor1["Student / Learner"]
    Actor2["Teacher / Instructor"]
    Actor3["Administrator"]
    Actor4["Parent / Guardian"]
    Actor5["Payment Gateway"]
    Actor6["Notification Service"]
    Actor7["Educational Board / Ministry"]
    System(("Campus Visitor Management System"))

    Actor1 -->|"submits course assignment & feedback"| System
    System -->|"sends learning material & grades"| Actor1
    Actor2 -->|"inputs grades & course syllabus"| System
    System -->|"provides class rosters & schedules"| Actor2
    Actor3 -->|"configures user roles & system policies"| System
    System -->|"generates audit logs & analytics reports"| Actor3
    Actor4 -->|"submits student progress inquiry"| System
    System -->|"sends attendance alerts & fee receipts"| Actor4
    System -->|"sends payment transaction request"| Actor5
    Actor5 -->|"returns payment status token"| System
    System -->|"sends email & SMS message payload"| Actor6
    Actor6 -->|"returns delivery status report"| System
    System -->|"exports statistical compliance report"| Actor7
    Actor7 -->|"provides national educational standards"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Student / Learner | Primary | Requests, submissions, profile updates, feedback | Notifications, academic results, schedules, receipts | Người sử dụng chính của hệ thống |
| 2 | Teacher / Instructor | Primary | Grades, attendance, course content, evaluation criteria | Rosters, schedules, submission files, analytics | Người quản lý nội dung và đánh giá |
| 3 | Administrator | Primary | System configurations, user assignments, policy updates | Audit logs, analytical reports, system alerts | Quản trị viên vận hành hệ thống |
| 4 | Parent / Guardian | Primary | Inquiries, consent forms, payments | Progress updates, attendance alerts, payment receipts | Người giám sát và hỗ trợ người học |
| 5 | Payment Gateway | Supporting | Payment status, transaction tokens | Payment requests, invoice details | Cổng thanh toán điện tử trực tuyến |
| 6 | Notification Service | Supporting | Delivery confirmations, error logs | Message templates, recipient numbers/emails | Dịch vụ gửi Email/SMS/Push notification |
| 7 | Educational Board / Ministry | Regulatory | Compliance guidelines, accreditation standards | Statistical reports, audit exports | Cơ quan quản lý giáo dục nhà nước |

## System Boundary Description | Mô tả Phạm vi Hệ thống

Hệ thống Campus Visitor Management System (Hệ thống Quản lý Khách thăm Khuôn viên) chịu trách nhiệm quản lý toàn bộ các quy trình nghiệp vụ chuyên biệt của hệ thống quản lý khách thăm khuôn viên. Phạm vi bên trong hệ thống bao gồm tiếp nhận hồ sơ, khởi tạo và theo dõi các thành phần nghiệp vụ, quản lý phân quyền và lưu trữ nhật ký hệ thống. Ngược lại, các thành phần không thuộc phạm vi xử lý của hệ thống bao gồm: xử lý cổng giao dịch tài chính điện tử (ủy quyền cho Payment Gateway), hạ tầng phần cứng viễn thông phát tin nhắn (giao cho Notification Service) và xây dựng bộ tiêu chuẩn khung pháp lý cấp quốc gia (do Educational Board / Ministry ban hành).
