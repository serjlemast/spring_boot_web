package com.serjlemast2;

import com.serjlemast2.repository.UserRepository;
import com.serjlemast2.service.UserService;
import com.serjlemast2.service.UserServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class UserAutoConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepository();
    }
}
