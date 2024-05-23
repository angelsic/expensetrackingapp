package com.project.expensetrackingapp.repository.entity.user;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * Entity for User Response id, username and roles
 * @author Angel Sic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String username;
    private Set<UserRole> roles;
}
