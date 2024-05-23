package com.project.expensetrackingapp.repository.entity.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entity for Finance Response id, portfolioName, typeFinanceName
 * , categoryName, name, amount and dateTime
 * @author Angel Sic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinanceResponse {
    private long id;
    private String portfolioName;
    private String typeFinanceName;
    private String categoryName;
    private String name;
    private double amount;
    private LocalDateTime dateTime;
}
