package com.app.users.infrastructure;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
    
    Long id,
    
    @NotBlank(message = "El nombre no puede estar vacío")
    String name,
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    String email
) {
    public static UserDto of(Long id, String name, String email) {
        return new UserDto(id, name, email);
    }

    public static UserDto of(String name, String email) {
        return new UserDto(null, name, email);
    }
}
