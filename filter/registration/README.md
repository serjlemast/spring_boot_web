# Spring Boot Filter Registration Example

This module demonstrates how to register and use **servlet filters** in a Spring Boot application.  
Filters allow you to execute **pre- and post-processing logic** for HTTP requests before they reach controllers.  
They can be used for logging, security checks, request modification, and other cross-cutting concerns.

---
### How It Is Built

- Filters extend `OncePerRequestFilter` to ensure they run **once per request**
- Filters are registered programmatically using `FilterRegistrationBean`
- Each filter can be restricted to specific URL patterns using `addUrlPatterns()`
- Execution order can be set with `setOrder()`
- Filters are executed in the order defined in the filter chain

---
### Filters in This Example

1. **SecondFilter**
   - Logs a message for every request to `/admin/*`
   - Demonstrates basic filter execution and ordering

2. **UserSecurityFilter**
   - Checks for the presence of a custom HTTP header `api-api`
   - Returns `401 Unauthorized` if the header is missing
   - Demonstrates simple security logic in a filter

---
### Pros and Cons of `OncePerRequestFilter`

**Pros:**
- Guarantees filter logic is executed **exactly once per request**, even in forwarded requests
- Thread-safe, safe for concurrent requests
- Easy to implement common cross-cutting logic

**Cons:**
- Always executes for every request (unless URL pattern filtering is applied)
- Adds synchronous processing overhead if heavy logic is used
- Not reactive — blocks the thread during execution

---
# Pros and Cons of Registration Methods

#### Using @Component

**Pros:**
- Fast and automatic registration
- Minimal boilerplate code
- Simple to implement

**Cons:**
- Cannot restrict filter to specific URL paths
- Harder to control execution order

---
#### Using FilterRegistrationBean


**Pros:**
- Can restrict filter to specific URL patterns
- Can set execution order explicitly
- Easy to combine multiple filters in a controlled chain

**Cons:**
- URL pattern matching may behave differently from controller mappings
- Slightly more verbose than using `@Component`

---
### When to Use

- **@Component** – for simple global filters applied to all requests
- **FilterRegistrationBean** – when URL-specific filtering or precise ordering is required, e.g., security, logging, metrics, or request validation

---

### URL Pattern Matching Differences

No — the pattern matching used for `@Controller` (Spring MVC) is different from **Servlet filter URL pattern matching**. Let me explain clearly:

---

#### 1️⃣ Filters (`FilterRegistrationBean`, `@WebFilter`)

- Use **Servlet specification URL patterns** (Servlet API).
- Supported patterns:

| Type      | Example  | Matches                            |
|-----------|----------|------------------------------------|
| Exact     | /users   | only /users                        |
| Prefix    | /users/* | /users, /users/123, /users/abc/xyz |
| Extension | *.jsp    | /index.jsp, /admin.jsp             |
| Root      | /        | everything                         |

**Important:**
- No Ant-style patterns (`**`)
- No path variables (`{id}`)
- Only `*` for prefix, or `*.ext` for extension

---

#### 2️⃣ Controllers (`@RequestMapping`, `@GetMapping`, etc.)

- Use **Spring MVC path matching** (flexible, Spring-specific).
- Supported patterns:

| Type                  | Example          | Matches                    |
|-----------------------|------------------|----------------------------|
| Exact                 | /users           | only /users                |
| Path variable         | /users/{id}      | /users/1, /users/abc       |
| Ant-style wildcard    | /users/**        | /users/1, /users/1/profile |
| Single-level wildcard | /users/*/profile | /users/1/profile           |

**Key points:**
- Spring MVC allows path variables, Ant-style wildcards (`*`, `**`), regex patterns
- Filters **cannot** use these — they only see the URL according to Servlet rules

---

#### 3️⃣ Key Differences

| Feature           | Servlet Filter       | Spring Controller                              |
|-------------------|----------------------|------------------------------------------------|
| Wildcards         | Prefix (`*`) only    | `*`, `**` supported                            |
| Path variables    | ❌                    | ✅ {id}                                         |
| Regex             | ❌                    | ✅ optional in `@RequestMapping`                |
| Trailing slash    | Exact match only     | Can match `/users/` if configured              |
| Multiple patterns | ✅ (`addUrlPatterns`) | ✅ (`@RequestMapping({"pattern1","pattern2"})`) |

---

#### 4️⃣ Practical Implication

- If a filter uses `/users/*` and the controller uses `/users/{id}`, the filter matches only `/users/123` requests.
- If the controller uses `/users/**` for deeper paths, the filter will **not match deeper paths** unless multiple patterns are registered in the filter.
- **Filters are coarse-grained** (Servlet spec), while **controllers are fine-grained** (Spring MVC path matching).

---terns may behave differently from controller mappings.