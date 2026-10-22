# Swimlane Diagram — SEO Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Technical SEO & Rank Cycle"])

    subgraph Lane_Specialist["SEO Specialist"]
        S1["Configure Target Keywords & Site Audit Rules"]
        S2["Review Technical Crawl Issues & Disavow Toxic Links"]
    end

    subgraph Lane_Developer["Web Developer"]
        D1["Fix 404 Broken Links & Redirect Loops"]
        D2["Optimize Core Web Vitals & Deploy JSON-LD Schema"]
    end

    subgraph Lane_System["SEO Management System"]
        E1["Execute Technical Audit Crawler & Speed Diagnostics"]
        E2["Query Daily SERP Rank Positions & Features"]
        E3{"Is Ranking Drop > 5 Positions or Health < 80?"}
        E4["Trigger Urgent SEO Alert & Developer Action Ticket"]
        E5["Package Indexing Payload & Format Disavow File"]
    end

    subgraph Lane_GSC["Google Search Console API"]
        G1["Ingest Indexing API Payload & Queue URL Indexation"]
        G2["Process Disavow File & Ignore Toxic Inbound Links"]
    end

    Finish(["End SEO Operations Cycle"])

    Start --> S1 --> E1 --> E2 --> E3
    E3 -->|"Yes"| E4 --> S2 --> D1 --> D2 --> E5 --> G1 --> G2 --> Finish
    E3 -->|"No / Stable"| E5
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | SEO Specialist | Configures target keywords, defines audit rules, reviews technical crawl health scorecards, and compiles toxic disavow link files. |
| 2 | Web Developer | Resolves 404 broken links and 301 redirect loops, optimizes Core Web Vitals (LCP/CLS), and deploys JSON-LD structured schema markup. |
| 3 | SEO Management System | Executes automated technical site audit crawlers, queries daily SERP rank positions, evaluates rank drop anomalies, generates developer tickets, and packages Google Search Console indexing/disavow API dispatches. |
| 4 | Google Search Console API | Ingests URL indexing API requests to accelerate search indexation and processes `disavow.txt` files to exclude toxic links from Google's ranking algorithms. |
