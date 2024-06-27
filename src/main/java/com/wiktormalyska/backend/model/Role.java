package com.wiktormalyska.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Role {
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
