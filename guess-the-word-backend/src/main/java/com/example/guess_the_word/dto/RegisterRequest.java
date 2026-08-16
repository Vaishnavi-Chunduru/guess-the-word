package com.example.guess_the_word.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Username must have at least 5 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])[A-Za-z]+$",
            message = "Username must contain both uppercase and lowercase letters"
    )
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password must have at least 5 characters")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$%*])[A-Za-z0-9$%*]+$",
            message = "Password must contain an alphabet, a number, and one special character ($, %, *)"
    )
    private String password;

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



/*
* Username regex - ^(?=.*[a-z])(?=.*[A-Z])[A-Za-z]+$
(?=.*[a-z])    → must contain lowercase
(?=.*[A-Z])    → must contain uppercase
[A-Za-z]+      → only letters are allowed

* Password regex - ^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$%*])[A-Za-z0-9$%*]+$
(?=.*[A-Za-z]) → at least one letter
(?=.*[0-9])    → at least one number
(?=.*[$%*])    → at least one $, % or *
[A-Za-z0-9$%*]+ → only these characters are allowed

*/