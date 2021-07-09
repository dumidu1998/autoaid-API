package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByFirstName(String username);

}
