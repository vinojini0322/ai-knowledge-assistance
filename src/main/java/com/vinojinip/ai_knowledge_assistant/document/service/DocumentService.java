package com.vinojinip.ai_knowledge_assistant.document.service;

import com.vinojinip.ai_knowledge_assistant.auth.entity.User;
import com.vinojinip.ai_knowledge_assistant.chat.service.CurrentUserService;
import com.vinojinip.ai_knowledge_assistant.common.exception.DocumentNotFoundException;
import com.vinojinip.ai_knowledge_assistant.common.exception.DocumentProcessingException;
import com.vinojinip.ai_knowledge_assistant.common.exception.UnsupportedDocumentTypeException;
import com.vinojinip.ai_knowledge_assistant.document.entity.Document;
import com.vinojinip.ai_knowledge_assistant.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private final DocumentRepository documentRepository;
    private final DocumentTextExtractor textExtractor;
    private final CurrentUserService currentUserService;

    @Transactional
    public Document upload(MultipartFile file) {
        validate(file);

        User currentUser = currentUserService.getCurrentUser();

        String extractedText = textExtractor.extract(file).trim();

        if (extractedText.isBlank()) {
            throw new DocumentProcessingException(
                    "No readable text found in document",
                    null
            );
        }

        Document document = new Document();
        document.setUser(currentUser);
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setExtractedText(extractedText);

        return documentRepository.save(document);
    }

    public List<Document> getMyDocuments() {
        UUID userId = currentUserService.getCurrentUser().getId();

        return documentRepository
                .findByUser_IdOrderByCreatedAtDesc(userId);
    }

    public Document getById(UUID documentId) {
        UUID userId = currentUserService.getCurrentUser().getId();

        return documentRepository
                .findByIdAndUser_Id(documentId, userId)
                .orElseThrow(
                        () -> new DocumentNotFoundException(documentId)
                );
    }

    @Transactional
    public void delete(UUID documentId) {
        Document document = getById(documentId);
        documentRepository.delete(document);
    }

    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new DocumentProcessingException(
                    "Document file is required",
                    null
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new DocumentProcessingException(
                    "File size must not exceed 5 MB",
                    null
            );
        }

        String contentType = file.getContentType();

        if (!"application/pdf".equals(contentType)
                && !"text/plain".equals(contentType)) {
            throw new UnsupportedDocumentTypeException(contentType);
        }
    }

    public String askQuestion(
            UUID documentId,
            String question
    ) {
        Document document = getById(documentId);

        String extractedText = document.getExtractedText();

        return """
            This is a mock document answer.

            The uploaded document was successfully processed.
            Your question was: %s

            OpenAI integration will later use the extracted document text
            together with this question to generate the final answer.
            """.formatted(question.trim()).trim();
    }
}