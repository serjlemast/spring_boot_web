1) The newest way to create filter is to extend OncePerRequestFilter

```angular2html
public class SecondFilter extends OncePerRequestFilter 
```

2) Each Filter has own ordering(-1, 0 , 1, 2 ...); -1 = the earliest
We can change ordering via annotation @Ordering