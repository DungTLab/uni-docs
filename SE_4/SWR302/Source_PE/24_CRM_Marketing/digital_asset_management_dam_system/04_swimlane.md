# Swimlane Diagram — Digital Asset Management (DAM) System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Digital Asset Governance Cycle"])

    subgraph Lane_Designer["Creative Designer"]
        D1["Upload Master High-Res Media File (RAW / PSD)"]
        D2["Check Out File, Edit in Photoshop & Check In v2.0"]
    end

    subgraph Lane_Author["Content Author"]
        C1["Search Asset Catalog & Request Dynamic WebP Rendition"]
        C2["Embed Responsive CDN Image Tag into Web CMS"]
    end

    subgraph Lane_System["Digital Asset Management System"]
        S1["Store Master Binary File in AWS S3 Storage Bucket"]
        S2["Apply File Lock & Track Asset Version Tree"]
        S3{"Passes DRM Clearance & License Expiration Checks?"}
        S4["Block Download & Apply Expired DRM Watermark"]
        S5["Generate Dynamic WebP Image & Deliver via CDN"]
    end

    subgraph Lane_AIEngine["AI Vision & CDN Transcoding Engine"]
        E1["Execute Computer Vision Auto-Tagging & OCR Extraction"]
        E2["Perform On-The-Fly Transcoding & AI Smart Cropping"]
    end

    Finish(["End DAM Cycle"])

    Start --> D1 --> S1 --> E1 --> D2 --> S2 --> C1 --> S3
    S3 -->|"No / Expired DRM"| S4 --> Finish
    S3 -->|"Yes / Cleared"| S5 --> E2 --> C2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Creative Designer | Uploads high-res master media files (RAW photography, Photoshop `.psd`), checks out files to apply exclusive locks, edits in Photoshop, checks in new asset versions, and performs visual diff comparisons. |
| 2 | Content Author | Searches the asset catalog using metadata facets and AI tags, requests web-ready dynamic image renditions (WebP/AVIF), and embeds responsive CDN tags into web CMS platforms. |
| 3 | Digital Asset Management System | Stores master binary files in AWS S3 buckets, manages file checkout locks, tracks hierarchical version trees, verifies DRM model releases and expiration dates, blocks expired downloads, and dispatches CDN cache purges. |
| 4 | AI Vision & CDN Transcoding Engine | Runs computer vision AI to auto-tag images, extract OCR text, and extract dominant colors, performing on-the-fly media transcoding and AI smart cropping around primary subject focal points. |
