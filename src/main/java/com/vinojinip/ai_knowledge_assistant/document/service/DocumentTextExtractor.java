package com.vinojinip.ai_knowledge_assistant.document.service;

import com.vinojinip.ai_knowledge_assistant.common.exception.DocumentProcessingException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class DocumentTextExtractor {

    public String extract(MultipartFile file) {
        String contentType = file.getContentType();

        try {
            if ("application/pdf".equals(contentType)) {
                return extractPdf(file);
            }

            if ("text/plain".equals(contentType)) {
                return new String(
                        file.getBytes(),
                        StandardCharsets.UTF_8
                );
            }

            throw new UnsupportedOperationException(contentType);

        } catch (IOException exception) {
            throw new DocumentProcessingException(
                    "Failed to extract document text",
                    exception
            );
        }
    }

    private String extractPdf(MultipartFile file) throws IOException {
        try (PDDocument document =
                     Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}