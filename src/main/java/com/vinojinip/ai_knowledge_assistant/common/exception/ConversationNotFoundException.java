package com.vinojinip.ai_knowledge_assistant.common.exception;

import java.util.UUID;

public class ConversationNotFoundException extends RuntimeException{

    public ConversationNotFoundException(UUID conversationId) {
        super("Conversation not found: " + conversationId);
    }

}
