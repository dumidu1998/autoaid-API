package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private long invoiceId;

    @Column(nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "repairId")
    Repair repair;

}
