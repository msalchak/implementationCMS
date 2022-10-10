package com.implementationcms.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean statusTransaction;

    @Column
    private Integer valueTranstaction;

    @Column
    private LocalDateTime transactionTime;
}
