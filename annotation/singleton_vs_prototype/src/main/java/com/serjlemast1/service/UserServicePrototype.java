package com.serjlemast1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class UserServicePrototype {

    private Integer number = 321;

    public Integer getNumber() {
        log.info("UserPrototype hashCode {}", this.hashCode());
        return number;
    }

    public Integer setNumber(Integer number) {
        log.info("UserPrototype hashCode {}", this.hashCode());
        this.number = number;
        return this.number;
    }
}
