package com.alpha5.autoaid.model;


import com.alpha5.autoaid.enums.SpecialItemRequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "special_item_request")
public class SpecialItemRequest {

    @Id
    @GeneratedValue
    private long specialRequestId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String itemName;

    @Column
    private Date requestedDateTime;

    @Column(precision = 10,scale = 2)
    private BigDecimal price;

    @Column
    private Date approvedDateTime;

    @Enumerated(EnumType.STRING)
    private SpecialItemRequestStatus status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "repair_id")
    Repair repair;


}
