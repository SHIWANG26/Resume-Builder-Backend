package com.resumebuilder.backend.controller;

import com.resumebuilder.backend.service.DocumentProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentProcessingService documentProcessingService;

    public DocumentController(DocumentProcessingService documentProcessingService) {
        this.documentProcessingService = documentProcessingService;
    }

    @PostMapping("/parse")
    public ResponseEntity<?> parseDocument(@RequestParam("file") MultipartFile file) {
        try {
            String extractedText = documentProcessingService.extractText(file);
            return ResponseEntity.ok(Map.of("text", extractedText));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
