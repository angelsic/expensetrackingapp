package com.project.expensetrackingapp.repository.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.expensetrackingapp.repository.entity.role.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}
            , fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_id"
            )
    )
    private Set<UserRole> roles = new HashSet<>();
}
