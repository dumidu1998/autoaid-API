package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_item")
public class InventoryItem {
    @Id
    @GeneratedValue
    private long itemNo;

    @Column(nullable = false, unique = true)
    private String itemName;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal stock;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal reorderLevel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    ItemCategory category;

    @OneToMany(targetEntity = ItemAdd.class, mappedBy = "item", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ItemAdd> itemAdd;

    @OneToMany(targetEntity = ItemRequest.class, mappedBy = "invItem", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ItemRequest> inventoryItem;

}
