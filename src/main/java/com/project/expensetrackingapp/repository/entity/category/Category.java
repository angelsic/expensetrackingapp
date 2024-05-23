package com.project.expensetrackingapp.repository.entity.category;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity for Category Id and name
 * @author Angel Sic
 */
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
