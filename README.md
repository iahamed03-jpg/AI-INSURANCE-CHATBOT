# AI Insurance Chatbot

A Spring Boot-based insurance chatbot application that uses Ollama and Spring AI to answer user queries with insurance domain context. The application supports PDF document ingestion and vector search for retrieval-augmented generation (RAG).

## Key Features

- REST API for chat requests
- Two chat modes:
  - custom Ollama HTTP integration (`/api/chat/message`)
  - Spring AI `ChatClient` integration (`/api/chat/message/spring-ai`)
- PDF upload endpoint for ingesting documents and storing text as vector embeddings
- In-memory vector store and cache for improved response performance
- Prompt builder for insurance support context

## Technology Stack

- Java 17
- Spring Boot 4.1.0
- Spring Web
- Spring AI with Ollama model integration
- Apache PDFBox for PDF parsing
- Maven build system

## Project Structure

- `AiinsurnacechatbotApplication.java` — Spring Boot entry point
- `controller/` — REST controllers for chat and document upload
- `service/` — chat services and Spring AI integration logic
- `document/` — PDF parsing, chunking, and document processing
- `vector/` — in-memory vector store and similarity search
- `embedding/` — embedding model wrapper using Spring AI
- `rag/` — retrieval-augmented generation prompt building
- `cache/` — simple response cache service
- `config/` — application configuration and beans

## API Endpoints

### POST `/api/chat/message`

Send a chat request using the custom Ollama HTTP integration.

Request body:
```json
{
  "text": "What are my coverage options?"
}
```

Response body:
```json
{
  "reply": "...",
  "model": "llama3.2",
  "timestamp": 1710000000000
}
```

### POST `/api/chat/message/spring-ai`

Send a chat request using Spring AI's `ChatClient`.

Request body is the same as above.

### POST `/api/document/upload`

Upload a PDF document to ingest text into the vector store.

Form field:
- `file` — the PDF file

Response:
- `200 OK` on success
- `500` on failure

## Configuration

Default configuration values are stored in `src/main/resources/application.properties`:

```properties
spring.application.name=aiinsurnacechatbot

ollama.base-url=http://localhost:11434
ollama.model=llama3.2

spring.ai.ollama.chat.options.model=llama3.2
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.embedding.options.model=nomic-embed-text
```

Update these values if your Ollama service or model changes.

## Running the Application

1. Start the Ollama service locally and confirm it is accessible at the configured `ollama.base-url`.
2. In the project root, run:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Build and Test

Build the project:

```bash
./mvnw clean package
```

Run tests:

```bash
./mvnw test
```

## Notes

- The project uses an in-memory vector store and cache, so data is lost when the application restarts.
- `DocumentService` parses PDFs, chunks the extracted text, and stores the chunks as embeddings.
- `RagService` queries the vector store to build contextual prompts for the chatbot.
- The application is currently configured for local Ollama access; update `application.properties` for remote or Docker-hosted Ollama.
