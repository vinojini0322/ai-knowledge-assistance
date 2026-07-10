package com.vinojinip.ai_knowledge_assistant.common.exception;

public class UnsupportedDocumentTypeException extends RuntimeException {

    public UnsupportedDocumentTypeException(String contentType) {
        super("Unsupported document type: " + contentType);
    }
}