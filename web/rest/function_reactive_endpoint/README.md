### Spring WebFlux Functional Routing

This module demonstrates an API built using **Spring WebFlux functional routing** instead of traditional
annotation-based controllers. Routing and request handling are explicitly defined, providing a clear and
predictable reactive execution flow.

---
### How It Is Built

- HTTP routes are declared programmatically using `RouterFunction`
- Request processing logic is implemented in handler classes
- Routes are mapped directly to handler method references
- Responses are created using reactive types (`Mono<ServerResponse>`)
- Execution is lazy and fully non-blocking

---
### Design Characteristics


- Functional, annotation-free routing
- Explicit request-to-handler mapping
- Reactive and backpressure-aware execution
- Easy replacement of in-memory storage with a reactive repository

---
### Pros

- Clear separation of routing and business logic
- No hidden behavior caused by annotations
- Fine-grained control over request handling
- Well suited for streaming and reactive systems
- Easy unit testing of handlers and routes

---
### Cons


- More verbose than `@RestController`
- Steeper learning curve for new Spring users
- Manual response construction
- Can be excessive for simple CRUD APIs

---
### When to Use

Functional routing is recommended for:

- Reactive microservices
- API gateways
- Streaming or event-driven APIs
- Projects that favor explicit control over conventions

For small or conventional REST APIs, annotation-based controllers may offer a simpler approach.