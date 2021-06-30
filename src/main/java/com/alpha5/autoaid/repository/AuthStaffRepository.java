package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthStaffRepository extends JpaRepository<Staff ,Long> {
    Staff findByFirstName(String firstName);
}
