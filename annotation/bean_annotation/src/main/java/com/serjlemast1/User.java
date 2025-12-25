package com.serjlemast1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {

    private Integer id;

    @NotBlank
    private String name;

    @Email(message = "Invalid email")
    private String email;
}
