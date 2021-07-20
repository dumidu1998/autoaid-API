package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.StaffRole;
import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByStaffId(long sid);
    Staff findByFirstName(String name);
    List<Staff> findAllByRole(StaffRole role);

}
