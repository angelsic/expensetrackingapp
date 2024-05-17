package com.project.expensetrackingapp.repository.entity.auth;

import com.project.expensetrackingapp.repository.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String token;
    private Instant expiredDate;
    private User user;
}
