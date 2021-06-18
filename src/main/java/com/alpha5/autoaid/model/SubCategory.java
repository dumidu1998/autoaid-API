package com.alpha5.autoaid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubCategory {

    @Id
    @GeneratedValue
    private long subCatId;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private String subCatName;

    @ManyToOne
    @JoinColumn(name = "sectionID")
    Section section;

}
