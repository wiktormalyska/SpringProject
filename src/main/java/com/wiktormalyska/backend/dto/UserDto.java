package com.wiktormalyska.backend.dto;

import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Date creationDate;
    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.creationDate = user.getCreationDate();
        this.roles = user.getRoles();
    }

    public User toUser() {
        return new User(this.id, this.email, this.username, this.password, this.creationDate, this.roles);
    }
}
