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
public class Section {

    @Id
    @GeneratedValue
    private long sectionId;

    @Column(nullable = false)
    private String sectionName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    Staff staff;

    @OneToMany(targetEntity = Slot.class, mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Slot> slots;

    @OneToMany(targetEntity = SubCategory.class, mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SubCategory> subCategories;

}
