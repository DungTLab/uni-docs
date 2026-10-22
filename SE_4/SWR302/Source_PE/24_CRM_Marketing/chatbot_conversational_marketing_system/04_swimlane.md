# Swimlane Diagram — Chatbot & Conversational Marketing System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Conversational Chatbot Session"])

    subgraph Lane_Visitor["Messaging User / Visitor"]
        V1["Click Chat Widget & Submit Inquiry Message"]
        V2["Select Available Time Slot (Tomorrow 2:00 PM)"]
    end

    subgraph Lane_System["Chatbot & Conversational Marketing System"]
        S1["Ingest Message Webhook & Load CDP Visitor Context"]
        S2["Evaluate Playbook Rules & Lead Qualification"]
        S3{"Passes MQL Qualification & Has Available Calendar Slots?"}
        S4["Route to Self-Service Trial Link & End Session"]
        S5["Display Interactive Calendar Slot Picker in Chat"]
        S6["Confirm Meeting Booking & Trigger CRM Lead Sync"]
    end

    subgraph Lane_LLM["Generative AI LLM & RAG Engine"]
        E1["Parse Intent & Extract Lead Entities (Company Size)"]
        E2["Generate RAG Answer & Formulate Natural Reply"]
    end

    subgraph Lane_CRM["Calendar API & Salesforce CRM"]
        C1["Query Live Sales Rep Calendar Availability"]
        C2["Book Calendar Event (.ics) & Sync Lead + Chat Transcript"]
    end

    Finish(["End Chatbot Session"])

    Start --> V1 --> S1 --> E1 --> E2 --> S2 --> S3
    S3 -->|"No / Unqualified"| S4 --> Finish
    S3 -->|"Yes / MQL Qualified"| C1 --> S5 --> V2 --> C2 --> S6 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Messaging User / Visitor | Clicks web chat widget or sends WhatsApp message, submits natural language queries, selects interactive buttons, and picks calendar demo slots. |
| 2 | Chatbot & Conversational Marketing System | Ingests multi-channel webhooks, loads CDP session context, evaluates playbook decision trees, checks MQL lead qualification rules, renders interactive calendar pickers, confirms meeting bookings, and triggers CRM sync. |
| 3 | Generative AI LLM & RAG Engine | Parses natural language intent, extracts key lead entities (Name, Email, Company Size), searches RAG vector knowledge bases, and formulates natural answers. |
| 4 | Calendar API & Salesforce CRM | Queries live sales rep calendar availability slots (Calendly API), books meeting events, sends `.ics` invites, creates lead contacts in CRM, and attaches complete chat transcripts. |
