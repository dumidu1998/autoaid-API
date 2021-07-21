package com.alpha5.autoaid.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long customerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @CreationTimestamp
    private Date registeredDate;

    @OneToOne
    @JsonIgnore
    private UserData userData;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = Vehicle.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Vehicle> vehicleDetails;

    @OneToMany(targetEntity = RateAndReview.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RateAndReview> rateAndReviews;

    public long getCustomerId(){return customerId; }
    public void setCustomerId(long CustomerId){
        this.customerId = CustomerId;
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
        return registeredDate;
    }
    public void setRegDate(Date registeredDate){
        this.registeredDate= registeredDate;
    }

}
