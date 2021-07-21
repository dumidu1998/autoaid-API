package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.StaffRole;
import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByStaffId(long sid);
    Staff findByFirstName(String name);
    List<Staff> findAllByRole(StaffRole role);
    @Query(value = "SELECT MAX(staff_id) FROM staff  " , nativeQuery = true)
    long getMaxStaffId();

}
