https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/responseentity.html

### Reactive ResponseEntity Support in Spring WebFlux

Spring WebFlux allows flexible, fully asynchronous HTTP responses by combining ResponseEntity with reactive types such as Mono and Flux. This makes it possible to control status codes, headers, and response bodies independently and asynchronously, depending on your use case.

Below are the most common patterns supported by WebFlux.

---

### ResponseEntity<Mono<T>> / ResponseEntity<Flux<T>>

In this approach, the HTTP status and headers are determined immediately, while the response body is produced asynchronously.
 - Use Mono<T> when the response contains zero or one element
 - Use Flux<T> when the response contains multiple elements or a stream

This pattern is useful when the response metadata is known upfront, but the body requires asynchronous processing.

---

### Mono<ResponseEntity<T>>

This variant makes the entire response asynchronous, including:
 - HTTP status
 - Headers
 - Body

It allows you to dynamically determine the response status and headers based on the result of asynchronous operations (for example, returning 404 Not Found if data is missing).

This is one of the most commonly used patterns for conditional responses.

---

### Mono<ResponseEntity<Mono<T>>> / Mono<ResponseEntity<Flux<T>>>

This is a less common but still valid approach.

Here:
1.	The ResponseEntity (status and headers) is produced asynchronously
2.	The response body is also produced asynchronously afterward

This pattern can be useful in advanced scenarios where both the response metadata and the body depend on different asynchronous computations, or when streaming behavior needs to be wrapped conditionally.

---
### Server-Sent Events (SSE)

To enable streaming responses such as Server-Sent Events (SSE), use:

```angular2html
@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
```

When combined with Flux<T>, this allows the server to push data to the client incrementally as it becomes available.

---
### Summary

Spring WebFlux provides multiple ways to structure asynchronous HTTP responses. Choosing the right pattern depends on:
•	Whether the response body is single or multi-value
•	Whether the status and headers are known upfront
•	Whether streaming behavior is required

Understanding these options helps you design clean, flexible, and efficient reactive APIs.