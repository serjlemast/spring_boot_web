# Spring Boot Filters with Ordering

This example demonstrates Spring Boot filters with explicit execution order.

---
### How It Is Built

1️⃣ Recommended way: extend `OncePerRequestFilter`

    public class SecondFilter extends OncePerRequestFilter { ... }

- Executes once per request, even if the request is forwarded internally
- Ideal for logging, security checks, and cross-cutting concerns

2️⃣ Classic way: implement `Filter` directly

    public class FirstFilter implements Filter { ... }

- Executes on every filter pass
- Older approach, less commonly used in modern Spring Boot apps

3️⃣ Ordering filters

- Each filter has an order: -1, 0, 1, 2... (-1 = earliest)
- Set the order using @Order:

  @Order(1) // executes before @Order(2)

- Filters with lower order values are executed first

---
### Pros

- Fine-grained control of filter execution order
- OncePerRequestFilter prevents duplicate execution per request
- Useful for logging, security, metrics, and validation

---
### Cons

- Overusing filters can complicate debugging
- Filters execute for all requests unless filtered by URL pattern

---
### When to Use

- For cross-cutting concerns like logging, authentication, or request validation
- When filter execution order matters
- Use OncePerRequestFilter for most modern Spring Boot applications
---