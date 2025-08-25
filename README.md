# Insurance Helper API

## Overview

Insurance Helper is a Spring Boot REST API for managing insurance operations. It provides secure JWT-based authentication, policy management, and AI-powered chat support.

## Features

- JWT authentication and authorization
- RESTful endpoints for policy and chat management
- Swagger UI for API documentation
- AI chat integration for user queries

## Architecture

- **Spring Boot** for application framework
- **Spring Security** for endpoint protection
- **Spring AI** for chat support
- **Maven** for build and dependency management

## Main Endpoints

| Endpoint                  | Method | Description                        | Auth Required |
|---------------------------|--------|------------------------------------|--------------|
| `/api/auth/**`            | POST   | Authentication (login, register)   | No           |
| `/api/chat/messages`      | POST   | AI chat support                    | Yes          |
| `/api/policies/**`        | CRUD   | Policy management                  | Yes          |
| `/swagger-ui.html`        | GET    | API documentation                  | No           |

## Security

- Uses JWT for securing endpoints
- Excludes: `/api/auth/**`, `/swagger-ui.html`, `/swagger-ui/**`, `/v3/api-docs/**`, `/swagger-resources/**`, `/h2-console/**`
- Stateless session management

## Setup

1. Clone the repository:
   ```sh
   git clone <your-repo-url>
   cd insurancehelper




Add your JWT secret to src/main/resources/application.properties:
jwt.secret=your-very-secret-key
Build and run the project:
./mvnw spring-boot:run
Access Swagger UI at: http://localhost:8080/swagger-ui.html

Dependencies
Java 17+
Spring Boot
Spring Security
Spring AI
Maven

```POST /api/chat/messages
Content-Type: application/json

{
  "prompt": "What is the best insurance plan for a family?"
}

## How GitHub Copilot Assisted in This Project


- Suggested best practices for structuring Spring Boot applications and configuring security.
- Provided code snippets for integrating JWT authentication and stateless session management.
- Helped generate REST controller methods and DTO classes quickly.
- Offered real-time code completions for Maven dependencies and configuration files.
- Assisted in writing clear and concise documentation, including setup and usage instructions.
- Enabled faster debugging by suggesting fixes for common Spring Security issues.
- Improved productivity by reducing boilerplate code and repetitive tasks.
   
