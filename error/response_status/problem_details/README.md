# Spring Boot Exception Handling in Controller

This module demonstrates **exception handling** in a Spring Boot REST controller using `ProblemDetail` responses compliant with [RFC 9457](https://www.rfc-editor.org/rfc/rfc9457.html).

---
### How It Is Built

- Validation in the `save()` method throws `RuntimeException` if required fields are missing:

  if (user.getEmail() == null) {
  throw new RuntimeException("Email is required");
  }

  if (user.getName() == null) {
  throw new RuntimeException("Name is required");
  }

- Exceptions are handled by a dedicated handler method using `@ExceptionHandler`:

  @ExceptionHandler(RuntimeException.class)
  public ProblemDetail errorControllerHandler(Exception e) {
  ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
  problemDetail.setTitle("Bad Request");
  problemDetail.setDetail(e.getMessage());
  problemDetail.setStatus(HttpStatus.BAD_REQUEST);
  problemDetail.setType(URI.create("www.google.com"));
  return problemDetail;
  }

- The handler constructs a **ProblemDetail** object including:
    - `status` → HTTP status code
    - `title` → short description
    - `detail` → exception message
    - `type` → URI pointing to documentation or info

---
### Key Points

- Uses **RFC 9457-compliant Problem Details** for consistent error responses
- Allows returning structured JSON for errors instead of plain messages
- Can include stacktrace for debugging (configure with `web.error.include-stacktrace: always`)
- Maps all `RuntimeException`s to structured HTTP responses

---
### Pros

- Standardized error response format for clients
- Clear separation of business logic and error handling
- Easy to extend for other exception types

### Cons

- All validation errors are currently mapped to `RuntimeException`
- ProblemDetail URI is hardcoded; ideally should point to API docs or error guide

---
### When to Use

- When you want structured, RFC-compliant error responses for REST APIs
- For handling validation, business, or runtime errors consistently
- To provide clients with meaningful error information in JSON
---