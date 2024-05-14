package com.project.expensetrackingapp.repository.entity.user;

import com.project.expensetrackingapp.repository.entity.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
   private Long id;
   private String username;
   private String password;
   private Set<UserRole> roles;
}