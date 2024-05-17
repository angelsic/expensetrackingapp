package com.project.expensetrackingapp.repository.entity.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Guatemala")
    private Date expiredDate;
}
