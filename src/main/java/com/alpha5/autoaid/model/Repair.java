package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.RepairStatus;
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
public class Repair {

    @Id
    @GeneratedValue
    private long repairId;

    @CreationTimestamp
    private Date date;

    @Column(nullable = false)
    private String paymentType; //enum or String?? TODO

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    Vehicle vehicle;

    @OneToMany(targetEntity = ServiceEntry.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ServiceEntry> serviceEntries;

    @OneToMany(targetEntity = Invoice.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Invoice> invoices;

    @OneToMany(targetEntity = RateAndReview.class, mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RateAndReview> rateAndReviews;

}
