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
public class Section {

    @Id
    @GeneratedValue
    private long sectionID;

    @Column(nullable = false)
    private String sectionName;

    @ManyToOne
    @JoinColumn(name = "staffId")
    Staff staff;

    @OneToMany(targetEntity = Slot.class, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Slot> slots;

    @OneToMany(targetEntity = SubCategory.class, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<SubCategory> subCategories;




}
