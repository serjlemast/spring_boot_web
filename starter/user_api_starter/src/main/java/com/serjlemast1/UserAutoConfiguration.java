package com.serjlemast1;

import com.serjlemast1.controller.UserController;
import com.serjlemast1.mapper.UserMapper;
import com.serjlemast1.service.UserRestService;
import com.serjlemast1.service.DefaultUserRestService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class UserAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public UserRestService userService() {
    return new DefaultUserRestService();
  }

  @Bean
  public UserMapper userMapper(UserRestService userService) {
    return new UserMapper(userService);
  }

  @Bean
  public UserController userController(UserMapper userMapper) {
    return new UserController(userMapper);
  }
}
