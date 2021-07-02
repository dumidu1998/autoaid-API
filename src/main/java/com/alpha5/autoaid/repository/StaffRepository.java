package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff ,Long> {
    Staff findByFirstName(String firstName);
    Staff findByContactNoAndEmail(String contactNo,String email);
}
