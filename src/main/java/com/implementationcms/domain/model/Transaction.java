package com.implementationcms.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "TRANSACTOINS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private static Boolean statusTransaction;

    @Column
    private static Integer valueTranstaction;

    @Column
    private static LocalDateTime transactionTime;
}
