
```
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "!?!?!")
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
```

What each part does:
 - extends RuntimeException
→ It’s an unchecked exception (doesn’t need to be caught).
- @ResponseStatus(code = HttpStatus.CONFLICT, reason = "!?!?!")
→ When this exception is thrown in a controller, Spring:
- Returns HTTP 409 Conflict
 - Uses "!?!?!" as the response reason/message
 - public MyException(String message)
→ Passes a custom message to the exception (mainly for logging/debugging)

```
throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Business require '@' symbol");
``` 

What:
Immediately returns HTTP 502 with that message.

Why:
To stop request processing and report a business/validation error to the client.

How:
Spring catches ResponseStatusException and maps it directly to an HTTP response (status + message), without extra handlers.
---
Include stacktrace in response(useful for debugging)
```
  web:
    error:
      include-stacktrace: always
```