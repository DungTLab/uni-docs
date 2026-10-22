# Context Diagram — Campaign Performance Tracking System

## Mermaid Code

```mermaid
flowchart LR
    CampaignManager["Campaign Performance Manager / Analytics Lead"]
    MediaSpecialist["Paid Media Specialist / Channel Manager"]
    CMO["Chief Marketing Officer (CMO)"]
    GrowthVP["VP of Growth / Demand Gen Lead"]
    AdNetworkAPIs["Digital Advertising Network APIs"]
    CRMSystem["Customer Relationship Management (CRM) System"]
    EcommerceGateway["E-Commerce Storefront & Order Gateway"]
    UTMGovernanceEngine["UTM Parameter & Tracking Link Governance Engine"]
    AttributionAPI["Marketing Attribution & Conversion Tracking API"]
    PrivacyRegistry["Data Privacy & Ad Tracking Regulatory Registry"]

    System(("Campaign Performance Tracking System"))

    CampaignManager -->|"inputs campaign tracking taxonomy, KPI benchmarks & budget caps"| System
    System -->|"provides unified multi-channel campaign scorecards & ROAS heatmaps"| CampaignManager

    MediaSpecialist -->|"inputs channel ad spend, campaign naming rules & UTM parameters"| System
    System -->|"returns channel-level CAC, impression pacing & conversion rate benchmarks"| MediaSpecialist

    CMO -->|"configures strategic ROI targets, pipeline goals & quarterly campaign budgets"| System
    System -->|"provides blended ROMI scorecards, cross-channel attribution & total revenue"| CMO

    GrowthVP -->|"configures campaign conversion funnel goals & lead velocity targets"| System
    System -->|"provides lead-to-MQL conversion velocity, channel acquisition cost & CAC trendlines"| GrowthVP

    AdNetworkAPIs -->|"streams API ad spend, impressions, clicks, CTR & ad set metrics"| System
    System -->|"returns conversion postback webhooks & cost reconciliation tokens"| AdNetworkAPIs

    CRMSystem -->|"transmits campaign lead creations, deal stage progressions & closed sales"| System
    System -->|"returns campaign attribution tags & deal source tracking metadata"| CRMSystem

    EcommerceGateway -->|"streams campaign order checkouts, transaction revenue & promo redemptions"| System
    System -->|"returns campaign order source attribution tags & checkout conversion logs"| EcommerceGateway

    System -->|"submits tracking link parameters for UTM taxonomy validation"| UTMGovernanceEngine
    UTMGovernanceEngine -->|"returns standardized tracking short URLs & validated UTM tokens"| System

    System -->|"submits multi-touch attribution queries & conversion event paths"| AttributionAPI
    AttributionAPI -->|"returns fractional campaign conversion credits & attribution weights"| System

    System -->|"submits user tracking consent status & iOS ATT privacy tokens"| PrivacyRegistry
    PrivacyRegistry -->|"acknowledges GDPR, CCPA & Apple ATT privacy compliance clearance"| System
```

## Actor & Interaction Table | Bảng Actor & Tương tác

| # | Actor | Actor Type | Data Sent TO System | Data Received FROM System | Notes |
|---|-------|------------|---------------------|---------------------------|-------|
| 1 | Campaign Performance Manager / Analytics Lead | Primary | Campaign tracking taxonomy, KPI benchmarks (Target CAC/ROAS), campaign budget limits, channel attribution rules. | Unified multi-channel campaign scorecards, channel ROAS heatmaps, campaign pacing alerts. | Analytics leader monitoring global campaign performance across digital and offline channels. |
| 2 | Paid Media Specialist / Channel Manager | Primary | Channel ad spend entries, campaign naming conventions, UTM parameter links, creative ad set IDs. | Channel-level Customer Acquisition Cost (CAC), impression pacing stats, Click-Through Rate (CTR) benchmarks. | Specialist managing Google, Meta, and LinkedIn paid media ad campaigns and budget allocations. |
| 3 | Chief Marketing Officer (CMO) | Primary | Strategic ROI targets, quarterly marketing budgets, target payback period goals. | Blended Return on Marketing Investment (ROMI), cross-channel attribution reports, total pipeline revenue. | Executive evaluating global campaign contribution to enterprise revenue and brand growth. |
| 4 | VP of Growth / Demand Gen Lead | Primary | Campaign conversion funnel goals, lead velocity targets, channel scaling thresholds. | Lead-to-MQL conversion velocity, channel acquisition cost, CAC trendlines, growth pacing. | Growth executive scaling high-performing campaign channels and tuning acquisition funnels. |
| 5 | Digital Advertising Network APIs | Supporting | API ad spend, impression counts, click telemetry, Click-Through Rates (CTR), cost-per-click (CPC). | Conversion postback webhooks, CAPI server-side event dispatches, cost reconciliation tokens. | Ad network APIs (Google Ads, Meta, TikTok, LinkedIn) streaming real-time ad performance data. |
| 6 | Customer Relationship Management (CRM) System | Supporting | Campaign lead creations, deal pipeline stage progressions, closed-won opportunity sales value. | Campaign revenue attribution tags, deal source tracking metadata, campaign lead source history. | Enterprise CRM providing closed sales pipeline revenue data for campaign ROI mapping. |
| 7 | E-Commerce Storefront & Order Gateway | Supporting | Campaign order checkouts, net transaction revenue, promo code redemptions, customer refund events. | Campaign order source attribution tags, checkout conversion logs, UTM session tracking tags. | Storefront payment gateway providing e-commerce order revenue data mapped to campaign links. |
| 8 | UTM Parameter & Tracking Link Governance Engine | Supporting | Validated UTM parameters (`utm_source`, `utm_medium`, `utm_campaign`), short URL mappings. | Standardized tracking short URLs, UTM taxonomy validation tokens, broken link alerts. | Link governance service enforcing consistent UTM parameter naming conventions across campaigns. |
| 9 | Marketing Attribution & Conversion Tracking API | Supporting | Multi-touch attribution model outputs, fractional conversion credits, customer touchpoint sequences. | Campaign conversion event paths, attribution weight queries, touchpoint timestamps. | Attribution engine calculating multi-touch campaign revenue attribution models. |
| 10 | Data Privacy & Ad Tracking Regulatory Registry | Supporting / Regulatory | Apple App Tracking Transparency (ATT) guidelines, GDPR cookie consent rules, CCPA opt-out rules. | User tracking consent status verification, iOS ATT privacy compliance tokens. | Governance registry enforcing ad privacy laws and server-side Conversion API (CAPI) compliance. |

## System Boundary Description | Mô tả Phạm vi Hệ thống

The **Campaign Performance Tracking System** is an enterprise multi-channel campaign analytics, real-time ROAS tracking, UTM link governance, server-side Conversion API (CAPI) integration, and campaign budget pacing platform designed to measure the end-to-end performance of marketing campaigns.

**Inside System Scope:**
- Multi-channel ad spend aggregation ingesting APIs from Google Ads, Meta Ads, TikTok Ads, LinkedIn Ads, and Bing.
- Real-time Return on Ad Spend ($\text{ROAS} = \frac{\text{Attributed Revenue}}{\text{Ad Spend}}$), CAC, CPC, and CTR performance tracking.
- UTM parameter builder and tracking link governance engine enforcing standardized URL taxonomy.
- Server-side Conversion API (CAPI) dispatching privacy-compliant conversion webhooks to ad networks.
- Real-time campaign budget pacing alerts warning media buyers of over-spending or under-pacing campaigns.

**Outside System Scope:**
- Direct creation of ad copy text or graphic design assets (handled by Content Marketing and Design Systems).
- Direct physical hosting of corporate web servers (handled by Cloud Web Hosting Services).
- Direct processing of bank merchant payment transactions (handled by Payment Gateways).
