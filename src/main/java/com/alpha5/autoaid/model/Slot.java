package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.SlotStatus;
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
public class Slot {

    @Id
    @GeneratedValue
    private long slotID;

    @Column(nullable = false)
    private String slotName;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore
    Section section;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    @JsonIgnore
    Staff staff;

    @OneToMany (targetEntity = ServiceEntry.class, mappedBy = "slot", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ServiceEntry> serviceEntries;


}
