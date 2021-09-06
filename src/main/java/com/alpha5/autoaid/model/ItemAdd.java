package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_add")
public class ItemAdd {

    @Id
    @GeneratedValue
    private long grnNo;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal quantity;

    @CreationTimestamp
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "item_no")
    InventoryItem item;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal buyingPrice;





}
