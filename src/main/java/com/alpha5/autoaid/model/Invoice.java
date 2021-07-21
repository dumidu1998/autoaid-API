package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    private Date invoiceDate;

    @ManyToOne
    @JoinColumn(name = "repair_id")
    Repair repair;

}
