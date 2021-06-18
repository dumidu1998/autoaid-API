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
public class ItemAdd {

    @Id
    @GeneratedValue
    private long grnNo;

    @Column(nullable = false)
    private int quantity;

    @CreationTimestamp
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "itemNo")
    InventoryItem item;



}
