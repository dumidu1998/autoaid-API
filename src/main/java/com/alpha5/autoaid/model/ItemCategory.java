package com.alpha5.autoaid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemCategory {

    @Id
    @GeneratedValue
    private long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    @JsonIgnore
    @OneToMany(targetEntity = InventoryItem.class, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<InventoryItem> inventoryItems;
}
