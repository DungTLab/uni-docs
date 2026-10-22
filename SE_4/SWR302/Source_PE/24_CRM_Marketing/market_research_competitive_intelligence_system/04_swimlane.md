# Swimlane Diagram — Market Research & Competitive Intelligence System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Competitive Intelligence Cycle"])

    subgraph Lane_Analyst["CI Analyst / PMM"]
        A1["Configure Competitor Pricing & Feature URLs"]
        A2["Review AI-Generated Battlecard Draft & Approve"]
    end

    subgraph Lane_System["Market Research & Competitive System"]
        S1["Schedule Automated Daily Scraping Crawl Job"]
        S2["Compare Scraped DOM against Yesterday's Baseline"]
        S3{"HTML Diff / Price Disruption Detected?"}
        S4["Log No-Change Crawl Status & Archive DOM Baseline"]
        S5["Synthesize AI Sales Battlecard & Objection Talk Tracks"]
        S6["Push Updated Battlecard Embeds directly to CRM"]
    end

    subgraph Lane_Crawler["Web Scraping Crawler Subsystem"]
        C1["Scrape Competitor Pricing Page HTML & Render JavaScript"]
        C2["Return Structured Price & Feature JSON Payload"]
    end

    subgraph Lane_CRM["CRM System"]
        R1["Stream Closed-Lost Deal Reasons & Competitor Tags"]
        R2["Render Dynamic Battlecards on Active Sales Opportunity Tabs"]
    end

    Finish(["End CI Intelligence Cycle"])

    Start --> A1 --> S1 --> C1 --> C2 --> S2 --> S3
    S3 -->|"No Change"| S4 --> Finish
    S3 -->|"Price Hike / Feature Diff"| R1 --> S5 --> A2 --> S6 --> R2 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | CI Analyst / PMM | Configures competitor target URLs, defines pricing watch parameters, reviews AI-generated sales battlecard drafts, and approves sales talk tracks. |
| 2 | Market Research & Competitive System | Schedules daily crawling jobs, compares scraped DOM baselines, detects price changes ($> 5\%$) and new features, aggregates CRM win/loss deal reasons, synthesizes AI battlecard talk tracks, and embeds battlecards into CRM. |
| 3 | Web Scraping Crawler Subsystem | Executes scheduled web scraping using rotating residential proxies, renders client-side JavaScript, captures screenshot baselines, and returns structured price/feature JSON payloads. |
| 4 | CRM System | Streams real-time closed-lost deal reason codes and competitor displacement data to the system, displaying dynamic battlecard widgets directly on sales reps' deal tabs. |
