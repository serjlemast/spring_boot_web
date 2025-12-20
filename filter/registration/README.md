1) Simple registration mark Filter class by annotation @Component

Pros:
1) Fast
2) Simple
3) Less code

Cons:
1) Impossible to set access url path
2) Harder to include to exist Filter chain

---
1) Filter registration Bean 

Pros:
1) Use URL pattern matching
2) Add filter in special order

Cons:
1) URL pattern matching(different in Controllers)