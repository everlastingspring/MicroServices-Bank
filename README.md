# 🏦 MicroServices-Bank

A production-grade distributed banking platform built with **Java**, **Spring Boot**, and **Spring Cloud** — demonstrating real-world microservices patterns including service discovery, centralised configuration, API gateway, event-driven messaging, fault tolerance, observability, security, and Kubernetes deployment.

> Built iteratively across 14 versions, each introducing a new production-grade pattern — from a basic Spring Boot app to a fully cloud-native, observable, and secured microservices system.

---

## 📌 Table of Contents

- [Architecture Overview](#architecture-overview)
- [Services](#services)
- [Tech Stack](#tech-stack)
- [Key Patterns Implemented](#key-patterns-implemented)
- [Version History](#version-history)
- [Getting Started](#getting-started)
- [API Reference](#api-reference)
- [Observability](#observability)
- [Security](#security)
- [Kubernetes Deployment](#kubernetes-deployment)

---

## Architecture Overview

```
                   ┌──────────────────────────────────────────┐
                   │          CLIENT (REST / Browser)          │
                   └───────────────────┬──────────────────────┘
                                       │
                   ┌───────────────────▼──────────────────────┐
                   │     API GATEWAY (Spring Cloud Gateway)    │
                   │  • JWT Validation    • Load Balancing     │
                   │  • Routing           • Rate Limiting      │
                   └────┬───────────────┬──────────────────────┘
                        │               │               │
           ┌────────────▼──┐   ┌────────▼───┐   ┌──────▼──────┐
           │  Account Svc  │   │  Loan Svc  │   │   Card Svc  │
           │  Port: 8080   │   │ Port: 8090 │   │  Port: 8091 │
           └───────┬───────┘   └─────┬──────┘   └──────┬──────┘
                   │                 │                  │
           ┌───────▼─────────────────▼──────────────────▼──────┐
           │                   Apache Kafka                      │
           │        (Asynchronous Event-Driven Messaging)        │
           └────────────────────────────────────────────────────┘

           ┌────────────────────────────────────────────────────┐
           │               INFRASTRUCTURE LAYER                  │
           │  Eureka Server │ Config Server │ Zipkin │ Prometheus│
           └────────────────────────────────────────────────────┘
```

---

## Services

| Service | Port | Description |
|---|---|---|
| `api-gateway` | 8072 | Spring Cloud Gateway — routing, JWT auth, load balancing |
| `accounts` | 8080 | Core banking accounts and customer management |
| `loans` | 8090 | Loan products and tracking |
| `cards` | 8091 | Debit/credit card management |
| `configserver` | 8071 | Centralised Spring Cloud Config Server |
| `eurekaserver` | 8070 | Service discovery and registry |
| `message` | — | Kafka-based messaging between services |

---

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x, Spring Cloud 2023.x |
| Service Discovery | Netflix Eureka |
| API Gateway | Spring Cloud Gateway |
| Config Management | Spring Cloud Config Server + Spring Cloud Bus |
| Messaging | Apache Kafka |
| Fault Tolerance | Resilience4j (Circuit Breaker, Retry, Rate Limiter) |
| Security | Spring Security 6, JWT, OAuth2 |
| Databases | MySQL, PostgreSQL |
| Observability | Micrometer, Zipkin, Prometheus, Grafana |
| Containerisation | Docker, Docker Compose |
| Orchestration | Kubernetes, Helm |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito |

---

## Key Patterns Implemented

### 1. Service Discovery — Eureka
All microservices register with Eureka Server on startup. The API Gateway resolves service locations dynamically, enabling load balancing without any hardcoded URLs.

### 2. Centralised Configuration — Spring Cloud Config
All service configurations are managed in a centralised Config Server backed by Git. Config changes propagate to all running services at runtime via Spring Cloud Bus + Kafka — no restarts needed.

### 3. API Gateway — Spring Cloud Gateway
Single entry point for all client requests. Handles:
- JWT token validation before routing to any service
- Path-based routing to downstream microservices
- Load balancing across multiple service instances
- Cross-cutting concerns (logging, tracing headers)

### 4. Fault Tolerance — Resilience4j
Every inter-service call is protected with:
- **Circuit Breaker** — opens after consecutive failures, fails fast to prevent cascading failures
- **Retry** — automatically retries transient failures with exponential backoff
- **Rate Limiter** — limits concurrent downstream calls to prevent overload
- **Fallback** — returns a safe default response when the circuit is open

### 5. Event-Driven Messaging — Apache Kafka
Services communicate asynchronously via Kafka topics, decoupling producers from consumers and enabling reliable, high-throughput inter-service communication.

### 6. Distributed Tracing — Micrometer + Zipkin
Every request is assigned a `traceId` that propagates across all service hops. Zipkin UI lets you visualise the full request journey with latency breakdowns at each service.

### 7. Metrics & Alerting — Prometheus + Grafana
All services expose `/actuator/prometheus` endpoints. Prometheus scrapes these metrics and Grafana dashboards provide real-time visibility into service health, JVM metrics, and circuit breaker states.

### 8. Security — Spring Security 6 + JWT + OAuth2
- JWT-based authentication issued at login
- API Gateway validates every token before routing
- Role-based access control (`ROLE_CUSTOMER`, `ROLE_ADMIN`)
- OAuth2 support for third-party integrations

### 9. Kubernetes + Helm
Full cloud-native deployment with Kubernetes manifests and Helm charts. Uses ConfigMaps for environment config and Secrets for sensitive data, ensuring clean separation between code and configuration.

---

## Version History

This project was built incrementally, with each version adding a new production pattern:

| Version | What Was Added |
|---|---|
| `appv1` | Basic Spring Boot microservices — accounts, loans, cards |
| `appv2` | Docker containerisation and Docker Compose setup |
| `appv3` | Eureka Server — service discovery and registration |
| `appv4` | Spring Cloud Config Server — centralised configuration |
| `appv5` | Spring Cloud Gateway — API gateway and routing |
| `appv6` | Resilience4j — Circuit Breaker, Retry, Rate Limiter |
| `appv7` | Apache Kafka — asynchronous inter-service messaging |
| `appv8` | Micrometer + Zipkin — distributed tracing |
| `appv9` | Prometheus + Grafana — metrics and dashboards |
| `appv_10` | Spring Security 6 + JWT + OAuth2 — API security |
| `appv_11` | Kubernetes manifests — cloud-native deployment |
| `appv_12` | Helm charts — parameterised Kubernetes deployments |
| `appv_13` | Spring Cloud Bus — runtime config refresh via Kafka |
| `appv_14` | Final integrated version — all patterns combined |

> **Start here → [`appv_14`](./appv_14)** for the complete, fully integrated implementation.

---

## Getting Started

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven 3.8+

### Run with Docker Compose (from `appv_14`)

```bash
# Clone the repository
git clone https://github.com/everlastingspring/MicroServices-Bank.git
cd MicroServices-Bank/appv_14

# Start infrastructure (Kafka, MySQL, Zipkin, Prometheus, Grafana)
docker-compose -f docker-compose-infra.yml up -d

# Start all microservices
docker-compose up -d

# Verify all services are running
docker-compose ps
```

### Access Points

| Service | URL |
|---|---|
| Eureka Dashboard | http://localhost:8070 |
| API Gateway | http://localhost:8072 |
| Zipkin Tracing | http://localhost:9411 |
| Grafana Dashboards | http://localhost:3000 (admin/admin) |
| Prometheus | http://localhost:9090 |

---

## API Reference

All requests go through the **API Gateway** at `http://localhost:8072`.

### Accounts

```
POST   /api/accounts/create          Create new bank account
GET    /api/accounts/{mobileNumber}  Fetch account details
PUT    /api/accounts/update          Update account info
DELETE /api/accounts/delete          Close account
```

### Loans

```
POST   /api/loans/create             Apply for a loan
GET    /api/loans/{mobileNumber}     Fetch loan details
PUT    /api/loans/update             Update loan
DELETE /api/loans/delete             Close loan
```

### Cards

```
POST   /api/cards/create             Issue a new card
GET    /api/cards/{mobileNumber}     Fetch card details
PUT    /api/cards/update             Update card
DELETE /api/cards/delete             Cancel card
```

### Example Request

```bash
curl -X POST http://localhost:8072/api/accounts/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <jwt-token>" \
  -d '{
    "name": "Prashanth R R",
    "email": "prashanth@example.com",
    "mobileNumber": "9876543210"
  }'
```

---

## Observability

### Distributed Tracing
Every request generates a `traceId` visible across all service logs:

```
[accounts]    [traceId=4a1f2b3c] INFO  - Account created for 9876543210
[loans]       [traceId=4a1f2b3c] INFO  - Loan eligibility checked
[api-gateway] [traceId=4a1f2b3c] INFO  - Response returned in 142ms
```

View full traces at **http://localhost:9411** (Zipkin).

### Key Prometheus Metrics

```
http_server_requests_seconds_count     # Request rate per endpoint
resilience4j_circuitbreaker_state      # Circuit breaker open/closed state
jvm_memory_used_bytes                  # JVM heap usage
kafka_consumer_records_lag             # Kafka consumer lag
```

---

## Security

### Authentication Flow

```
1. POST /auth/login  →  Returns JWT token
2. Include token:   Authorization: Bearer <token>
3. API Gateway validates token before routing
4. Services read userId and role from SecurityContext
```

### JWT Payload

```json
{
  "sub": "9876543210",
  "role": "ROLE_CUSTOMER",
  "iat": 1739000000,
  "exp": 1739086400
}
```

---

## Kubernetes Deployment

```bash
# Deploy using Helm (from appv_12 or appv_14)
cd appv_12/helm

helm install microservices-bank . \
  --namespace banking \
  --create-namespace

# Check pod status
kubectl get pods -n banking

# View service logs
kubectl logs -f deployment/accounts -n banking
```

---

## What I Learned Building This

- How Eureka eliminates hardcoded URLs and enables dynamic service scaling
- Why circuit breakers are essential — one slow service can cascade into full system failure without them
- The tradeoff between synchronous REST and async Kafka: consistency vs availability
- How Kubernetes ConfigMaps and Secrets cleanly solve environment-specific configuration
- Why distributed tracing is non-negotiable in microservices — logs alone aren't enough across services
- How Spring Cloud Config + Git enables runtime config changes without any redeployment

---

## Author

**Prashanth R R** — Backend Software Engineer  
[LinkedIn](https://linkedin.com/in/prashanthrr) · [GitHub](https://github.com/everlastingspring)
