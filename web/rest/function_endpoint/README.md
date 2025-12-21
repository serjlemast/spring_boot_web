# Spring Synchronous UserHandler Example

This module demonstrates a simple **synchronous handler-based API** using Spring components.
It manages an in-memory user store and constructs HTTP responses using `ServerResponse`.

---
### How It Is Built

- Handlers are implemented as Spring `@Component` methods
- Routes can be mapped to handlers programmatically or via functional router
- Responses are created directly using `ServerResponse` (blocking)
- User IDs are generated with an `AtomicInteger`
- Uses `ConcurrentHashMap` for thread-safe in-memory storage

---
### Design Characteristics

- Synchronous, blocking API
- Explicit request handling without annotations on methods
- Clear separation between storage and request handling
- Simple and minimal for demonstration or small services

---
### Pros

- Very simple and straightforward
- No reactive complexity
- Easy to read and maintain
- Thread-safe in-memory operations

---
### Cons

- Blocking, not suitable for high-concurrency or streaming scenarios
- Less scalable than reactive alternatives
- Limited to simple synchronous flows

---
### When to Use

- Small applications or prototypes
- Learning purposes
- When non-blocking/reactive behavior is not needed
---