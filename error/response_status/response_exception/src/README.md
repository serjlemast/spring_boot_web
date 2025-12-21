# Spring Boot Exception Handling Example

This module demonstrates custom exceptions and Spring’s built-in ResponseStatusException for handling business and validation errors.

---
### Custom Exception with @ResponseStatus

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "!?!?!")
    public class MyException extends RuntimeException {
        public MyException(String message) {
            super(message);
        }
    }

**Explanation:**

- `extends RuntimeException` → unchecked exception (doesn’t need to be caught)
- `@ResponseStatus(code = HttpStatus.CONFLICT, reason = "!?!?!")` → when thrown in a controller, Spring:
    - Returns HTTP 409 Conflict
    - Uses "!?!?!" as the response reason/message
- `public MyException(String message)` → passes a custom message for logging/debugging

---
### Using ResponseStatusException

    throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Business require '@' symbol");

**What it does:**
- Immediately stops request processing
- Returns HTTP 502 with the given message

**How it works:**
- Spring maps ResponseStatusException directly to an HTTP response
- No additional handler is required

---
### Example in UserService

- Validation/business rules trigger exceptions:
    - Missing email → MyException
    - Missing name → ResponseStatusException with HTTP 417
    - Invalid email format → ResponseStatusException with HTTP 502
- Client receives HTTP status + message
- Request processing stops when exception is thrown

---
### Include Stacktrace in Response

For debugging:

    web:
      error:
        include-stacktrace: always

- Useful for development/testing
- Avoid in production unless necessary

---
### Summary

- `@ResponseStatus` → predefined HTTP status + reason for custom exceptions
- `ResponseStatusException` → dynamic, runtime HTTP error responses
- Both stop request processing and return appropriate HTTP responses
- Stacktrace can be included for debugging
---