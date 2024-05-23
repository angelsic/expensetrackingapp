package com.project.expensetrackingapp.repository.entity.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity for User Role id and name
 * @author Angel Sic
 */
@Entity(name = "roles")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roles_id")
    private long id;
    @Column(unique = true)
    private String name;

}
