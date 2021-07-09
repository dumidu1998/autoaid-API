package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long CustomerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @CreationTimestamp
    private Date RegisteredDate;

    @OneToOne
    private UserData userData;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = Vehicle.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicleDetails;

    @OneToMany(targetEntity = RateAndReview.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<RateAndReview> rateAndReviews;

    public long getCustomerId(){return CustomerId; }
    public void setCustomerId(long CustomerId){
        this.CustomerId= CustomerId;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }


    public Date getRegDate(){
        return RegisteredDate;
    }
    public void setRegDate(Date RegisteredDate){
        this.RegisteredDate= RegisteredDate;
    }

}
