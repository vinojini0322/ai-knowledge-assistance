package com.vinojinip.ai_knowledge_assistant.document.dto;

import java.util.UUID;

public record DocumentChatResponse(
        UUID documentId,
        String question,
        String answer
) {
}