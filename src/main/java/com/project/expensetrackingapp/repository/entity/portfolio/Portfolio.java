package com.project.expensetrackingapp.repository.entity.portfolio;

import com.project.expensetrackingapp.repository.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "portfolio")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
    , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
