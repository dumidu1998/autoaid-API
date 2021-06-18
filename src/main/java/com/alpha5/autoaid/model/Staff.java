package com.alpha5.autoaid.model;


import com.alpha5.autoaid.enums.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {

    @Id
    @GeneratedValue
    private long staffId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String contactNo;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = Section.class, mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Section> sections;

    @OneToMany(targetEntity = ItemRequest.class, mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<ItemRequest> itemRequests;

    @OneToMany(targetEntity = ServiceEntry.class, mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<ServiceEntry> serviceEntries;

}
