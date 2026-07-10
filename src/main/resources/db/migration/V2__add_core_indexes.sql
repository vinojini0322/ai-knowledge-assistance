CREATE INDEX idx_conversations_user_updated_at
    ON conversations(user_id, updated_at DESC);

CREATE INDEX idx_messages_conversation_created_at
    ON messages(conversation_id, created_at ASC);

CREATE INDEX idx_documents_user_created_at
    ON documents(user_id, created_at DESC);