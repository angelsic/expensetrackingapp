package com.project.expensetrackingapp.repository.entity.finance;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "finance")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
            , fetch = FetchType.EAGER)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
            , fetch = FetchType.EAGER)
    @JoinColumn(name = "type_finance_id")
    private TypeFinance typeFinance;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
            , fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    private String name;
    @Column(name = "amount")
    private double amount;
    @Column(name = "datetime_process")
    private LocalDateTime dateTime;
}
