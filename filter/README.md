### Filters

```angular2html
Client
  |
  v
[ Servlet Container (Tomcat/Jetty/Undertow) ]
  |
  v
[ Filters (javax.servlet.Filter) ]
  |   - logging
  |   - CORS
  |   - security (JWT, auth headers)
  |   - compression
  v
[ DispatcherServlet (Spring MVC) ]
  |
  v
[ HandlerInterceptor ]
  |   preHandle()
  v
[ Controller (@RestController / @Controller) ]
  |
  v
[ Service / Business Logic ]
  |
  v
[ Controller returns Response / Future / Mono / Flux ]
  |
  v
[ HandlerInterceptor ]
  |   postHandle()
  |   afterCompletion()
  v
[ Filters (response phase) ]
  |
  v
Client

```
