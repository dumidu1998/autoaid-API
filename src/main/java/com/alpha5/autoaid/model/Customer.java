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

    @OneToMany(targetEntity = Vehicle.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Vehicle> vehicleDetails;

    @OneToMany(targetEntity = RateAndReview.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RateAndReview> rateAndReviews;


}
