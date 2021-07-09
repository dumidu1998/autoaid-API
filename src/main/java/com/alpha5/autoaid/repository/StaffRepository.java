package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff ,Long> {
    Staff findByFirstName(String firstName);
}
