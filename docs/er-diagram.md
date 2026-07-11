# Entity Relationship Diagram

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

## Relationships

- One user can own many conversations.
- One conversation can contain many messages.
- One user can upload many documents.
- Deleting a user removes their conversations and documents.
- Deleting a conversation removes its messages.

## Constraints

- `users.email` is unique.
- `messages.role` is restricted to `USER` or `ASSISTANT`.
- All primary keys use UUIDs.
- Foreign keys use cascading deletes.

## Indexes

- `idx_conversations_user_updated_at`
    - Optimizes fetching a user's conversations ordered by recent activity.

- `idx_messages_conversation_created_at`
    - Optimizes loading messages in chronological order for a conversation.

- `idx_documents_user_created_at`
    - Optimizes fetching a user's recently uploaded documents.