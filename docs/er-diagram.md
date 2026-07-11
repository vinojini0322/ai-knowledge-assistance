# Entity Relationship Diagram

The following ER diagram represents the database schema used by the AI Knowledge Assistant.

```mermaid
erDiagram

    USERS ||--o{ CONVERSATIONS : owns
    USERS ||--o{ DOCUMENTS : uploads
    CONVERSATIONS ||--o{ MESSAGES : contains

    USERS {
        UUID id PK
        VARCHAR email UK
        VARCHAR password
        VARCHAR first_name
        VARCHAR last_name
        TIMESTAMPTZ created_at
        TIMESTAMPTZ updated_at
    }

    CONVERSATIONS {
        UUID id PK
        UUID user_id FK
        VARCHAR title
        TIMESTAMPTZ created_at
        TIMESTAMPTZ updated_at
    }

    MESSAGES {
        UUID id PK
        UUID conversation_id FK
        VARCHAR role
        TEXT content
        TIMESTAMPTZ created_at
        TIMESTAMPTZ updated_at
    }

    DOCUMENTS {
        UUID id PK
        UUID user_id FK
        VARCHAR file_name
        VARCHAR file_type
        TEXT extracted_text
        TIMESTAMPTZ created_at
        TIMESTAMPTZ updated_at
    }
```

---

## Relationships

- A **User** can own multiple **Conversations**.
- A **Conversation** can contain multiple **Messages**.
- A **User** can upload multiple **Documents**.

---

## Cascade Delete Rules

- Deleting a **User** deletes all associated Conversations.
- Deleting a **Conversation** deletes all associated Messages.
- Deleting a **User** deletes all associated Documents.

---

## Constraints

- `users.email` is unique.
- `messages.role` accepts only:
  - USER
  - ASSISTANT

---

## Indexes

The following indexes are created to improve query performance:

| Index | Purpose |
|--------|---------|
| idx_conversations_user_updated_at | Fetch user conversations ordered by latest activity |
| idx_messages_conversation_created_at | Load conversation messages chronologically |
| idx_documents_user_created_at | Fetch recently uploaded documents |