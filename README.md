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
