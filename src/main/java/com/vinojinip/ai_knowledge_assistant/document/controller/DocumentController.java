package com.vinojinip.ai_knowledge_assistant.document.controller;

import com.vinojinip.ai_knowledge_assistant.document.dto.DocumentChatRequest;
import com.vinojinip.ai_knowledge_assistant.document.dto.DocumentChatResponse;
import com.vinojinip.ai_knowledge_assistant.document.dto.DocumentResponse;
import com.vinojinip.ai_knowledge_assistant.document.entity.Document;
import com.vinojinip.ai_knowledge_assistant.document.mapper.DocumentMapper;
import com.vinojinip.ai_knowledge_assistant.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<DocumentResponse> upload(
            @RequestPart("file") MultipartFile file
    ) {
        Document document = documentService.upload(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documentMapper.toResponse(document));
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getMyDocuments() {
        List<DocumentResponse> response =
                documentService.getMyDocuments()
                        .stream()
                        .map(documentMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                documentMapper.toResponse(
                        documentService.getById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        documentService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/chat")
    public ResponseEntity<DocumentChatResponse> chat(
            @Valid @RequestBody DocumentChatRequest request
    ) {
        String answer = documentService.askQuestion(
                request.documentId(),
                request.question()
        );

        return ResponseEntity.ok(
                new DocumentChatResponse(
                        request.documentId(),
                        request.question(),
                        answer
                )
        );
    }
}