# Spring MVC Interceptor Example with UserController

This module demonstrates how to use **Spring MVC interceptors** alongside a simple in-memory REST API.  
Interceptors provide a way to execute **pre- and post-processing logic** for incoming HTTP requests before they reach the controller, and after the response is generated.

---

### How It Is Built

- `InterceptorRegistrationConfig` implements `WebMvcConfigurer` to register a custom interceptor (`MyInterceptor`)
- `MyInterceptor` implements `HandlerInterceptor` with three main methods:
    - `preHandle` – runs before the controller; can block requests or modify request data
    - `postHandle` – runs after the controller but before the view is rendered
    - `afterCompletion` – runs after the request is fully processed, useful for cleanup or logging
- `UserController` provides a simple CRUD-like in-memory API for `User` objects
- Thread-safe storage with `ConcurrentHashMap` and atomic ID generation using `AtomicInteger`

---

### What Interceptors Can Do

Interceptors allow implementing various **business logic concerns** without touching controller code:

- Logging and measuring **execution time** of endpoints
- Authentication and **token validation**
- Request modification or enrichment (e.g., adding headers)
- Implementing a **blacklist** or rate-limiting logic
- Collecting metrics or audit data
- Handling cross-cutting concerns such as CORS, caching, or IP filtering

---

### Design Characteristics

- Clear separation of request processing (`Interceptor`) and business logic (`UserController`)
- Synchronous handling of requests using `ResponseEntity`
- In-memory, thread-safe storage for simplicity
- Demonstrates basic usage of `HandlerInterceptor` lifecycle methods

---

### Pros

- Centralized point for cross-cutting concerns
- Avoids duplication of common logic in multiple controllers
- Can enforce rules globally for all routes or specific path patterns
- Easy to log, monitor, or modify requests/responses consistently

---

### Cons

- Adds complexity for very simple APIs
- Overuse can make request flow harder to follow
- Synchronous interceptors can block request processing
- Not reactive; works only in standard Spring MVC (blocking)

---

### When to Use

- Logging, monitoring, and auditing HTTP requests
- Authentication, authorization, and security checks
- Rate limiting, token blacklisting, or IP filtering
- Any cross-cutting concern that must run for multiple controllers

---