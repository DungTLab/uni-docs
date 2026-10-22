# Swimlane Diagram — Content Marketing Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Editorial Content Lifecycle"])

    subgraph Lane_Strategist["Content Strategist"]
        S1["Create Content Brief & Target Keywords"]
        S2["Assign Writer & Set Milestone Deadlines"]
        S3["Conduct Editorial Review & Sign Off"]
    end

    subgraph Lane_Writer["Content Writer"]
        W1["Write Article in WYSIWYG Editor"]
        W2["Optimize Real-Time SEO & Readability"]
        W3["Run Plagiarism & Originality Scan"]
    end

    subgraph Lane_System["Content Marketing System"]
        E1["Attach Strategy Brief to Editorial Calendar"]
        E2["Stream Real-Time SEO Keyword Feedback"]
        E3{"Is Originality Score >= 90% & SEO Score >= 80?"}
        E4["Flag Quality Deficit & Prompt Revision"]
        E5["Package HTML Payload & Canonical Metadata"]
        E6["Track Pageviews, Reading Time & Lead ROI"]
    end

    subgraph Lane_CMS["CMS Platform / WordPress"]
        C1["Receive Article REST API Payload"]
        C2["Publish Live Web Page & Return Live URL"]
    end

    Finish(["End Content Publishing Cycle"])

    Start --> S1 --> E1 --> S2 --> W1 --> W2 --> E2 --> W3 --> E3
    E3 -->|"No"| E4 --> W1
    E3 -->|"Yes / Approved"| S3 --> E5 --> C1 --> C2 --> E6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Content Strategist | Creates structured content briefs with target keywords, assigns writers with milestone deadlines, conducts editorial reviews, and authorizes publication sign-offs. |
| 2 | Content Writer | Drafts article copy in the WYSIWYG editor, optimizes text against real-time SEO keyword recommendations and Flesch readability scores, and executes plagiarism verification. |
| 3 | Content Marketing System | Attaches briefs to editorial calendars, streams real-time SEO scoring feedback, enforces originality and quality thresholds, packages approved HTML payloads with canonical metadata, and tracks pageviews, reading time, and lead ROI. |
| 4 | CMS Platform / WordPress | Ingests published article REST API payloads, generates live public web pages, assigns canonical URLs, and streams reader pageview telemetry back to the system. |
