package com.alpha5.autoaid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue
    private long subCatId;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private String subCatName;

    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;

    @OneToMany(targetEntity = ServiceEntry.class, mappedBy = "subCategory", cascade = CascadeType.ALL)
    private Set<ServiceEntry> serviceEntries;

}
