package com.wiktormalyska.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Role {
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
