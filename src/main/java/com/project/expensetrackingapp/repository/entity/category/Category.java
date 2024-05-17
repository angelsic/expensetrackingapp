package com.project.expensetrackingapp.repository.entity.category;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "category")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
}
