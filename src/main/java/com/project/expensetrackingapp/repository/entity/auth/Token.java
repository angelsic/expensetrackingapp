package com.project.expensetrackingapp.repository.entity.auth;

import com.project.expensetrackingapp.repository.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Entity for token, expiredDate and user
 * @author Angel Sic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String token;
    private Instant expiredDate;
    private User user;
}
