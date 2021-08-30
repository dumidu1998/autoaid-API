package com.alpha5.autoaid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentSlot {

    @Id
    @GeneratedValue
    private int appointmentSlotId;

    @Column(nullable = false)
    private String slotTime;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "appointmentSlot", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments;

}
