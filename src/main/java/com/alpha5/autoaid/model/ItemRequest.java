package com.alpha5.autoaid.model;


import com.alpha5.autoaid.enums.ItemRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_request")
public class ItemRequest {

    @Id
    @GeneratedValue
    private long requestId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Date issuedDateTime;

    @Enumerated(EnumType.STRING)
    private ItemRequestStatus status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ManyToOne
    @JoinColumn(name = "repair_id")
    Repair repair;

    @ManyToOne
    @JoinColumn(name = "item_no")
    InventoryItem invItem;

}
