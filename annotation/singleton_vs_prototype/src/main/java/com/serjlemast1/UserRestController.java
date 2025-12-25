package com.serjlemast1;

import com.serjlemast1.model.UserInfo;
import com.serjlemast1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    private final ApplicationContext context;

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> findById(@PathVariable Integer id) {
        log.info("UserRestController hashCode {}", this.hashCode());
//        return ResponseEntity.ok(context.getBean(UserServicePrototype.class).setNumber(number));
        return ResponseEntity.ok(userService.findById(id));
    }

}
