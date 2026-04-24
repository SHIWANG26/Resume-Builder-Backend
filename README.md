# AI-Powered Resume Builder - Backend

This is the Spring Boot backend for the AI-Powered Resume Builder application. It provides secure REST APIs for user authentication, document processing (PDF/Word), and AI-assisted resume generation and tailoring.

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.x** (Web, Data JPA, Security, Validation)
- **Spring Security & JWT** for secure authentication
- **Spring AI** integrated with Google Gemini for agentic resume writing
- **Apache PDFBox & POI** for parsing PDF and DOCX files
- **H2 Database** for local development (PostgreSQL dependency included for production)
- **Maven** for dependency management

## Prerequisites

- Java 17 JDK or higher
- Maven 3.8+
- A Google Gemini API Key

## Setup & Running Locally

1. **Configure Environment Variables**
   Create a `.env` file in the root of the `backend` directory and add your Gemini API key:
   ```env
   SPRING_AI_OPENAI_API_KEY=your_gemini_api_key_here
   ```

2. **Database Configuration**
   By default, the application uses an in-memory H2 database for local development. The database will be created automatically on startup.
   - **H2 Console:** `http://localhost:8080/h2-console`
   - **JDBC URL:** `jdbc:h2:mem:resumedb`
   - **Username:** `sa`
   - **Password:** `password`

3. **Build the Application**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The backend will start on `http://localhost:8080`.

## Key Features

- **Authentication System:** JWT-based secure login and registration.
- **Document Processing:** Extraction of text from uploaded resumes (PDF and Word formats) using PDFBox and Apache POI.
- **AI Integration:** Uses Gemini AI via Spring AI to analyze resumes, tailor content to specific job descriptions, and generate professional summaries.
- **Resume Management:** APIs to save, update, and retrieve user profiles and parsed resumes.

## Project Structure

- `controller/`: REST API endpoints (`AuthController`, `DocumentController`, `ResumeController`)
- `service/`: Core business logic (`AgenticResumeService`, `DocumentProcessingService`, etc.)
- `security/`: JWT filters, authentication providers, and security configurations
- `model/`: JPA Entities (`User`, `Resume`, `JobProfile`)
- `repository/`: Spring Data JPA repositories

## Security Notes

Environment variables like `SPRING_AI_OPENAI_API_KEY` should be kept in your `.env` file. This file is ignored by Git to prevent accidentally committing sensitive keys.
