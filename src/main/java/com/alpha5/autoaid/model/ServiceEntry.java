package com.alpha5.autoaid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceEntry {

    @Id
    @GeneratedValue
    private long entryId;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "repairId")
    Repair repair;

    @ManyToOne
    @JoinColumn(name = "staffId")
    Staff staff;

    @ManyToOne
    @JoinColumn(name = "subCatId")
    SubCategory subCategory;

}
