package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.InventoryStatus;
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
    @JoinColumn(name = "category_id")
    ItemCategory category;

    @OneToMany(targetEntity = ItemAdd.class, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<ItemAdd> itemAdd;

    @OneToMany(targetEntity = ItemRequest.class, mappedBy = "invItem", cascade = CascadeType.ALL)
    private Set<ItemRequest> inventoryItem;

}
