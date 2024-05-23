package com.project.expensetrackingapp.repository.entity.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity for typename and amount
 * @author Angel Sic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinanceReport {
    private String typename;
    private double amount;
}
