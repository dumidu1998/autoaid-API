package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
    Object findByContactNo(String contactNo);
    Customer findByFirstName(String username);

}
