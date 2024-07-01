package com.wiktormalyska.backend.dto;

import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String email;
    private String username;
    private String password;
    private String role;

    public User toUser() {
        return new User(email, username, password);
    }
}
