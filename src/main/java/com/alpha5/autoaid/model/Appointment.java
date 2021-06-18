package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private long appointmentId;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customerId")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "staffId")
    Staff staff;

}
