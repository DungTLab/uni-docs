# Swimlane Diagram — Legal Research & Knowledge Management System

## Mermaid Code

```mermaid
flowchart LR
    Start(["Start Legal Research & Precedent Curation Lifecycle"])

    subgraph Lane_Associate["Corporate Counsel / Research Associate"]
        A1["Input Natural Language Legal Query & Target Jurisdiction"]
        A2["Review AI Synthesized Memo & Bluebook Verified Citations"]
    end

    subgraph Lane_Librarian["Senior Research Librarian"]
        L1["Curate Winning Brief Work Product & Apply Taxonomy Tags"]
        L2["Publish Anonymized Precedent to Enterprise Repository"]
    end

    subgraph Lane_System["Legal Research & Knowledge Management System"]
        S1["Execute Dense Vector Retrieval & Prompt AI RAG Engine"]
        S2{"Passes Grounding Confidence & KeyCite Good Law Check?"}
        S3["Flag Bad Law / Overruled Citation Warning for Attorney"]
        S4["Format Citations to Bluebook Standard & Render Memo"]
        S5["Index Curated Precedent in Vector Database for RAG Search"]
    end

    subgraph Lane_Engine["Westlaw & Legal AI RAG Engine (KeyCite / OpenAI RAG)"]
        E1["Synthesize Grounded Legal Answer & Return Case Chunks"]
        E2["Verify KeyCite Precedent Treatment Status (Good Law / Overruled)"]
    end

    Finish(["End Legal Research Cycle"])

    Start --> A1 --> S1 --> E1 --> E2 --> S2
    S2 -->|"No / Overruled Precedent"| S3 --> A1
    S2 -->|"Yes / Good Law & Grounded"| S4 --> A2 --> L1 --> L2 --> S5 --> Finish
```

## Flow Description | Mô tả luồng

| Lane | Actor | Role in Flow |
|------|-------|-------------|
| 1 | Corporate Counsel / Research Associate | Inputs natural language legal research queries, selects target jurisdictions, reviews AI-synthesized research memos, verifies Bluebook citations, and incorporates precedents into court briefs. |
| 2 | Senior Research Librarian | Curates high-value internal winning briefs, anonymizes client PII, applies legal practice area taxonomy tags, and publishes work product to the firm-wide precedent bank. |
| 3 | Legal Research & Knowledge Management System | Executes dense vector retrieval across case law embeddings, prompts the RAG LLM engine for grounded synthesis, checks KeyCite/Shepard's treatment flags, formats citations to Bluebook 21st Edition rules, indexes internal precedents in Pinecone vector DB, and tracks commercial API costs. |
| 4 | Westlaw & Legal AI RAG Engine (KeyCite / OpenAI RAG) | Retrieves authoritative case law text chunks, synthesizes grounded legal answers with zero hallucinations, and verifies judicial treatment status (Good Law, Overruled, Distinguished) via Westlaw KeyCite API. |
