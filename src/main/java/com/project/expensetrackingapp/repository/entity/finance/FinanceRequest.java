package com.project.expensetrackingapp.repository.entity.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinanceRequest {
    private Long id;
    private Long idPortfolio;
    private Long idTypeFinance;
    private Long idCategory;
    private double initAmount;
    private double endAmount;
    private LocalDateTime start;
    private LocalDateTime end;
}
