package com.resumebuilder.backend.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenticResumeService {

    private final ChatClient chatClient;

    public AgenticResumeService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Tailors an existing resume to a specific job description using Agentic AI.
     * 
     * @param originalResumeText The raw text extracted from the user's uploaded template
     * @param jobTitle The title of the job the user is applying for
     * @param jobDescription The description/requirements of the target job
     * @return The tailored resume text
     */
    public String tailorResume(String originalResumeText, String jobTitle, String jobDescription) {
        // Formulate the strict agent instructions
        String systemPrompt = "You are an expert resume writer, career coach, and ATS (Applicant Tracking System) optimizer. " +
                "Your task is to perfectly tailor a candidate's resume to match a specific job description and output the result entirely in LaTeX format.\n\n" +
                "CRITICAL RULES:\n" +
                "1. DO NOT invent or fabricate new work experience, degrees, or skills the candidate does not possess.\n" +
                "2. Reframe, highlight, and optimize the existing experience to maximize ATS scores by naturally incorporating keywords from the job description.\n" +
                "3. Maintain a highly professional, action-oriented tone (use strong action verbs).\n" +
                "4. FORMAT REQUIREMENT: Your entire output MUST be valid LaTeX code. Structure the resume using standard LaTeX formatting (e.g., \\section, \\textbf, \\begin{itemize}, \\item).\n" +
                "5. Ensure all special characters (like %, &, $) are properly escaped in LaTeX.\n" +
                "6. Output ONLY the finalized tailored LaTeX code. Do not include markdown code blocks (like ```latex) or any introductory/concluding remarks. The output should compile directly in a LaTeX editor.";

        // Construct the user input containing the context
        String userPrompt = String.format(
                "=== TARGET JOB TITLE ===\n%s\n\n" +
                "=== TARGET JOB DESCRIPTION ===\n%s\n\n" +
                "=== ORIGINAL RESUME ===\n%s",
                jobTitle, jobDescription, originalResumeText
        );

        // Execute the call using Spring AI
        Prompt prompt = new Prompt(List.of(
                new SystemMessage(systemPrompt),
                new UserMessage(userPrompt)
        ));

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
