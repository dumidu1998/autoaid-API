package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.ServiceEntryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    private Date assignedTime;

    @Column
    private Date completedTime;

    @Enumerated(EnumType.STRING)
    private ServiceEntryStatus serviceEntryStatus;

    @ManyToOne
    @JoinColumn(name = "repair_id")
    Repair repair;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Staff staff;

    @ManyToOne
    @JoinColumn(name = "sub_cat_id")
    SubCategory subCategory;

    @OneToOne
    @JoinColumn(name = "slot_id")
    Slot slot;

}
