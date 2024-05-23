package com.project.expensetrackingapp.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Error Response errorCode and errorMessage
 * @author Angel Sic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrResponse {
    public String errorCode;
    public String errorMessage;
}
