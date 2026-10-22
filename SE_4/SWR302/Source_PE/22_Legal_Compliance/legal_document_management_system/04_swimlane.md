# Swimlane Diagram — Legal Document Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Legal Document Storage & Retrieval Cycle"])

    subgraph Lane_Counsel["Corporate Counsel"]
        C1["Upload Scanned Legal Brief & Select Metadata Facets"]
        C2["Execute Full-Text & Semantic Search in Word Add-In"]
    end

    subgraph Lane_Auditor["Outside Legal Auditor"]
        A1["Request Secure Shared Download Link for Brief"]
        A2["Download Dynamically Watermarked PDF File"]
    end

    subgraph Lane_System["Legal Document Management System"]
        S1["Encrypt File (AES-256) & Dispatch to OCR Queue"]
        S2{"Passes Privilege Clearance & Access Permissions?"}
        S3["Suppress Document from Search Results & Block Download"]
        S4["Apply File Lock & Render Visual Diff Version Comparison"]
        S5["Stamp Dynamic DRM Watermark (IP/Email/Timestamp)"]
    end

    subgraph Lane_Indexer["Legal OCR & Search Indexer (Elasticsearch)"]
        E1["Execute Legal OCR & Generate Searchable PDF/A"]
        E2["Index Text, HOCR Coordinates & Dense Vector Embeddings"]
    end

    Finish(["End Document Management Cycle"])

    Start --> C1 --> S1 --> E1 --> E2 --> C2 --> S2
    S2 -->|"No / Privileged"| S3 --> Finish
    S2 -->|"Yes / Cleared"| S4 --> A1 --> S5 --> A2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Counsel | Uploads scanned legal filings, selects metadata facets, executes sub-second full-text searches inside Microsoft Word add-in, checks out files, and compares version revisions. |
| 2 | Outside Legal Auditor | Requests secure time-bound shared download links, views HTML5 proxy previews, and downloads dynamically watermarked PDF files. |
| 3 | Legal Document Management System | Encrypts binary files at rest with AES-256, verifies attorney-client privilege classifications, enforces role-based access control, manages exclusive check-out file locks, stamps dynamic DRM watermarks on exports, and logs immutable audit trails. |
| 4 | Legal OCR & Search Indexer (Elasticsearch) | Converts scanned paper legal briefs into searchable PDF/A documents, extracts HOCR layout coordinates, and indexes text, metadata, and vector semantic embeddings for sub-second retrieval. |
