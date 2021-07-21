package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByStaffId(long sid);
    Staff findByFirstName(String name);
    Staff findAllByUserData(UserData userData);

}
