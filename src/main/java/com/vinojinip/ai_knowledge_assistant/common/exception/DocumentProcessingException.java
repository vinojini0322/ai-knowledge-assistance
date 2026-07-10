package com.vinojinip.ai_knowledge_assistant.common.exception;

public class DocumentProcessingException extends RuntimeException {

    public DocumentProcessingException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}
