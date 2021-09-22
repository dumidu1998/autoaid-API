package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.RepairStatus;
import com.alpha5.autoaid.model.Repair;
import com.alpha5.autoaid.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findByRepairId(long repairId);
    Repair findByStatusAndAndVehicle(RepairStatus status, Vehicle vehicle);
    List<Repair> findAllByStaff_StaffIdAndStatusIsNot(long staffId,RepairStatus status);
    List<Repair> findAllByStatusAndVehicleVehicleId(RepairStatus status,long vehicleId);
    List<Repair> findAllByStatusAndVehicle_Customer_UserData_id(RepairStatus status,long userId);
}
