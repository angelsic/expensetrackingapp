package com.project.expensetrackingapp.repository.entity.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinanceReport {
    private String typename;
    private double amount;
}
