# Conceptual ERD — CCTV & Video Surveillance Management System

## Mermaid Code

```mermaid
erDiagram
    CAMERA_DEVICE {
        int camera_id PK
        string camera_name
        string ip_address
        string mac_address
        string model_number
        string onvif_profile_url
        string location_zone
        string status
    }

    VIDEO_STREAM {
        int stream_id PK
        int camera_id FK
        string stream_type
        string rtsp_url
        int resolution_width
        int resolution_height
        int frames_per_second
        string video_codec
    }

    RECORDING_ARCHIVE {
        int archive_id PK
        int camera_id FK
        datetime start_timestamp
        datetime end_timestamp
        decimal file_size_mb
        string storage_volume_path
        string retention_status
    }

    VIDEO_ANALYTICS_EVENT {
        int event_id PK
        int camera_id FK
        datetime event_timestamp
        string analytics_type
        decimal confidence_score
        string bounding_box_json
        string snapshot_file_path
    }

    PTZ_PRESET {
        int preset_id PK
        int camera_id FK
        string preset_name
        decimal pan_coordinate
        decimal tilt_coordinate
        decimal zoom_level
        int dwell_time_seconds
    }

    STORAGE_VOLUME {
        int volume_id PK
        string volume_name
        string san_nas_path
        decimal total_capacity_tb
        decimal used_capacity_tb
        string raid_status
    }

    EVIDENCE_EXPORT {
        int export_id PK
        int camera_id FK
        datetime export_timestamp
        string requested_by_user
        datetime clip_start_time
        datetime clip_end_time
        string sha256_signature_hash
        string export_file_path
    }

    CAMERA_STATUS_LOG {
        int log_id PK
        int camera_id FK
        datetime log_timestamp
        string status_event
        string error_code
        decimal network_latency_ms
    }

    VIDEO_WALL_LAYOUT {
        int layout_id PK
        string layout_name
        string operator_username
        int grid_rows
        int grid_columns
        string camera_mapping_json
    }

    AUDIT_LOG {
        int audit_id PK
        string username
        datetime action_timestamp
        string action_category
        string target_resource
        string client_ip_address
    }

    CAMERA_DEVICE ||--o{ VIDEO_STREAM : "provides"
    CAMERA_DEVICE ||--o{ RECORDING_ARCHIVE : "generates"
    CAMERA_DEVICE ||--o{ VIDEO_ANALYTICS_EVENT : "triggers"
    CAMERA_DEVICE ||--o{ PTZ_PRESET : "stores"
    CAMERA_DEVICE ||--o{ EVIDENCE_EXPORT : "exported_from"
    CAMERA_DEVICE ||--o{ CAMERA_STATUS_LOG : "records_health"
    STORAGE_VOLUME ||--o{ RECORDING_ARCHIVE : "hosts"
    VIDEO_WALL_LAYOUT ||--o{ CAMERA_DEVICE : "displays"
    CAMERA_DEVICE ||--o{ AUDIT_LOG : "monitored_in"
```

## Entity Description Table | Bảng mô tả Entity

| # | Entity Name | Vietnamese Name | Description | Key Attributes | Main Relationships |
|---|-------------|-----------------|-------------|----------------|-------------------|
| 1 | CAMERA_DEVICE | Thiết bị Camera IP | Master table storing IP camera hardware configurations, network IPs, location zones, and status. | camera_id, camera_name, ip_address, location_zone, status | Primary entity; provides VIDEO_STREAM, generates RECORDING_ARCHIVE. |
| 2 | VIDEO_STREAM | Luồng Video Stream | Stores video streaming parameters (RTSP URL, codec, resolution, FPS, main vs sub stream). | stream_id, stream_type, rtsp_url, resolution_width, video_codec | Belongs to CAMERA_DEVICE. |
| 3 | RECORDING_ARCHIVE | Lưu trữ Video NVR | Tracks recorded continuous video file segments stored on SAN/NAS storage volumes. | archive_id, start_timestamp, file_size_mb, retention_status | Belongs to CAMERA_DEVICE; hosted on STORAGE_VOLUME. |
| 4 | VIDEO_ANALYTICS_EVENT | Sự kiện Phân tích Video AI | Stores AI computer vision detection logs (motion, tripwire, LPR plate string, face match bounding box). | event_id, analytics_type, confidence_score, snapshot_file_path | Belongs to CAMERA_DEVICE. |
| 5 | PTZ_PRESET | Tọa độ PTZ Định sẵn | Stores Pan-Tilt-Zoom coordinate positions and automatic patrol tour dwell times for PTZ cameras. | preset_id, preset_name, pan_coordinate, tilt_coordinate, zoom_level | Belongs to CAMERA_DEVICE. |
| 6 | STORAGE_VOLUME | Ô Đĩa Lưu trữ (SAN/NAS) | Tracks storage array volumes, RAID status, total capacity, and available storage space. | volume_id, volume_name, total_capacity_tb, used_capacity_tb | Hosts multiple RECORDING_ARCHIVE files. |
| 7 | EVIDENCE_EXPORT | Trích xuất Bằng chứng Video | Stores video clip export logs, timestamps, operator usernames, and SHA-256 digital signature hashes. | export_id, requested_by_user, clip_start_time, sha256_signature_hash | Exported from CAMERA_DEVICE. |
| 8 | CAMERA_STATUS_LOG | Nhật ký Trạng thái Camera | Logs camera health events, video loss incidents, network latency, and tampering alerts. | log_id, log_timestamp, status_event, network_latency_ms | Belongs to CAMERA_DEVICE. |
| 9 | VIDEO_WALL_LAYOUT | Bố cục Màn hình Video Wall | Stores operator custom video matrix grid layouts and camera tile position JSON mappings. | layout_id, layout_name, grid_rows, grid_columns, camera_mapping_json | Displays multiple CAMERA_DEVICE feeds. |
| 10 | AUDIT_LOG | Nhật ký Kiểm toán Hệ thống | Captures user authentication timestamps, video viewing histories, export requests, and system edits. | audit_id, username, action_timestamp, target_resource | Audits system actions on CAMERA_DEVICE. |

## Relationship Description | Mô tả Quan hệ

| # | From Entity | Cardinality | To Entity | Relationship Label | Business Explanation |
|---|-------------|-------------|-----------|-------------------|----------------------|
| 1 | CAMERA_DEVICE | one-to-many | VIDEO_STREAM | provides | An IP camera device provides multiple video streams (Main/Sub). |
| 2 | CAMERA_DEVICE | one-to-many | RECORDING_ARCHIVE | generates | An IP camera generates multiple continuous recording file segments. |
| 3 | CAMERA_DEVICE | one-to-many | VIDEO_ANALYTICS_EVENT | triggers | An IP camera triggers AI video analytics detection events. |
| 4 | CAMERA_DEVICE | one-to-many | PTZ_PRESET | stores | A PTZ camera stores multiple predefined position presets. |
| 5 | CAMERA_DEVICE | one-to-many | EVIDENCE_EXPORT | exported_from | Video clips can be exported from a camera device for evidence. |
| 6 | CAMERA_DEVICE | one-to-many | CAMERA_STATUS_LOG | records_health | A camera device records continuous health and connectivity logs. |
| 7 | STORAGE_VOLUME | one-to-many | RECORDING_ARCHIVE | hosts | A storage SAN volume hosts multiple video recording archive files. |
| 8 | VIDEO_WALL_LAYOUT | many-to-many | CAMERA_DEVICE | displays | A video wall layout displays multiple camera feeds in grid tiles. |
