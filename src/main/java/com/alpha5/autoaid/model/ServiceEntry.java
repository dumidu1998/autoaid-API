package com.alpha5.autoaid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_entry")
public class ServiceEntry {

    @Id
    @GeneratedValue
    private long entryId;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "repair_id")
    Repair repair;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ManyToOne
    @JoinColumn(name = "sub_cat_id")
    SubCategory subCategory;

}
