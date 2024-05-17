package com.project.expensetrackingapp.repository.entity.typefinance;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "type_finance")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
}
