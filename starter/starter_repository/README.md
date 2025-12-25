### Create custom starter

#### 1. Add minimal dependencies

in build.gradle

```
plugins {
    id 'java-library'
    id "com.github.ben-manes.versions" version "${benManesDependencyVersion}"
}

group = "${buildGroup}"
version = "${buildVersion}"

java {
    sourceCompatibility = "${javaVersion}"
}

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.springframework.boot:spring-boot-dependencies:${springBootVersion}"))
    api("org.springframework.boot:spring-boot-autoconfigure")
}
```

Rule:

* api → what the application should see
* implementation → internal only

#### 2. Write ONLY one responsibility

Examples:

* REST starter → controllers + REST interfaces
* Logic starter → services, algorithms
* Infra starter → clients, config, adapters

❌ No orchestration
❌ No app-specific logic

#### 3. Create public API (interfaces)

```java
public interface MyService {
    String execute(String input);
}

```

Note: Interfaces live in the starter that consumes them.

#### 3.1 (Optional) Provide default implementation

```java
class DefaultMyService implements MyService {
    public String execute(String input) {
        return input;
    }
}

```

#### 4. Create Autoconfiguration class

```java

@AutoConfiguration
public class MyStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    MyService myService() {
        return new DefaultMyService();
    }
}

```

Note: Spring Boot 4 → use @AutoConfiguration

#### 4.1 Add safety conditions (important)

```
@ConditionalOnClass(MyService.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix="my.starter", name="enabled", matchIfMissing=true)

```

```java

@AutoConfiguration
@ConditionalOnClass(MyService.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(
        prefix = "my.starter",
        name = "enabled",
        matchIfMissing = true
)
public class MyStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    MyService myService() {
        return new DefaultMyService();
    }
}
```

#### 5. Register auto-configuration

⚠️ Mandatory — otherwise starter won’t load

folder:

```
src/main/resources/
└── META-INF/
    └── spring/
        └── org.springframework.boot.autoconfigure.AutoConfiguration.imports

```

contains:

```
com.example.mystarter.MyStarterAutoConfiguration
```

How to verify it:

run: `jar tf signature-api-starter-1.0.0.jar | grep AutoConfiguration.imports`

expected: `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

#### 6. Use starter in an application

```
implementation("com.example:my-starter")
```

* No @ComponentScan needed ✅
* No manual config needed ✅

#### 7. Override from app (composition)

```java

@Bean
MyService customService() {
    return input -> "custom";
}

```

#### 8. Troubleshot

create application.properties with:

``
debug=true
``

and in log we cant see starter autoconfiguration:

```
   SignatureRestAutoConfiguration matched:
      - found 'session' scope (OnWebApplicationCondition)
      
    SignatureRestAutoConfiguration#signatureService:
      Did not match:
         - @ConditionalOnMissingBean (types: com.malexj.api.SignatureService; SearchStrategy: all) found beans 
                 of type 'com.malexj.api.SignatureService' signatureServiceImpl (OnBeanCondition)
```