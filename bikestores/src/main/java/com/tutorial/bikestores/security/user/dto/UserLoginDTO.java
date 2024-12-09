package com.tutorial.bikestores.security.user.dto;

import lombok.*;

@Data
public class UserLoginDTO {

    private final String username;
    private final String password;
}
