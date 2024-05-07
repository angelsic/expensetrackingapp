package com.project.expensetrackingapp.repository.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String username;
    private String password;
}
