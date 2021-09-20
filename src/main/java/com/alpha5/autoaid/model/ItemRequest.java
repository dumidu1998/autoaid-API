package com.alpha5.autoaid.model;


import com.alpha5.autoaid.enums.ItemRequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "repair_id")
    Repair repair;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_no")
    InventoryItem invItem;

}
