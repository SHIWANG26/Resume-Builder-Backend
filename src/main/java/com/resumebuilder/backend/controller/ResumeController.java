package com.resumebuilder.backend.controller;

import com.resumebuilder.backend.service.AgenticResumeService;
import com.resumebuilder.backend.service.DocumentProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    private final DocumentProcessingService documentProcessingService;
    private final AgenticResumeService agenticResumeService;

    public ResumeController(DocumentProcessingService documentProcessingService, AgenticResumeService agenticResumeService) {
        this.documentProcessingService = documentProcessingService;
        this.agenticResumeService = agenticResumeService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<?> tailorResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("jobDescription") String jobDescription) {
        try {
            long startTime = System.currentTimeMillis();
            logger.info("Starting resume tailoring process for job: {}", jobTitle);

            // 1. Extract raw text from the uploaded template
            String parsedText = documentProcessingService.extractText(file);
            long parsedTime = System.currentTimeMillis();
            logger.info("Document extraction completed in {} ms. Extracted {} characters.", (parsedTime - startTime), parsedText.length());

            // 2. Use Agentic AI to tailor the content
            logger.info("Sending prompt to Gemini AI...");
            String tailoredResume = agenticResumeService.tailorResume(parsedText, jobTitle, jobDescription);
            long aiTime = System.currentTimeMillis();
            logger.info("AI generation completed in {} ms", (aiTime - parsedTime));
            logger.info("Total processing time: {} ms", (aiTime - startTime));

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "tailoredResume", tailoredResume
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }
}
