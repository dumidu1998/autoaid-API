package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByStaffId(long sid);
    Staff findByUserData_Id(long userId);
    Staff findByUserData_UserName(String userName);
    Staff findByUserData_Email(String email);
    Staff findByUserData_ContactNo(String contact);
    Staff findByUserData(UserData userData);

    @Query(value = "SELECT MAX(staff_id) FROM staff  " , nativeQuery = true)
    long getMaxStaffId();

    @Query(value = "SELECT * FROM staff INNER JOIN user_data ON staff.user_data_id=user_data.id WHERE staff_id NOT IN (SELECT Staff_id FROM appointment WHERE date=?1 ) AND user_data.user_type='SERVICE_ADVISOR'" , nativeQuery = true)
    List<Staff> findAvailableServiceAdvisorsByDate(String date);

}
