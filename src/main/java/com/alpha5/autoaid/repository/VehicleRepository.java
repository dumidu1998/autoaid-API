package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByVin(String vin);
    List<Vehicle> findAllByCustomer_UserData_Email(String email);

    List<Vehicle> findAllByCustomer_CustomerId(long id);
    List<Vehicle> findAllByCustomer_UserData_Id(long id);

    Vehicle findByVehicleId(long id);

    @Query(value = "SELECT SUM(amount) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id WHERE v.vehicle_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 YEAR AND CURDATE() " , nativeQuery = true)
    float vehicleSummary(long vid);

    @Query(value = "SELECT SUM(amount) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id WHERE v.vehicle_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 MONTH AND CURDATE() " , nativeQuery = true)
    float vehicleSummary2(long vid);

    @Query(value = "SELECT COUNT(*) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id WHERE v.vehicle_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 YEAR AND CURDATE() " , nativeQuery = true)
    int vehicleSummary3(long vid);

    @Query(value = "SELECT COUNT(*) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id WHERE v.vehicle_id=?1 AND r.status != 'COMPLETED'" , nativeQuery = true)
    int vehicleSummary4(long vid);

}
