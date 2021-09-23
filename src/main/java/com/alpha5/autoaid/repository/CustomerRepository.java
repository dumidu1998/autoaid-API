package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String username);
    Customer findByUserData(UserData user);
    Customer findByUserData_Email(String email);
    Customer findByUserData_ContactNo(String contact);


    @Query(value = "SELECT SUM(amount) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id INNER JOIN customer as c ON c.customer_id=v.customer_customer_id WHERE v.customer_customer_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 YEAR AND CURDATE() " , nativeQuery = true)
     float customerSummary(long userId);

    @Query(value = "SELECT SUM(amount) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id INNER JOIN customer as c ON c.customer_id=v.customer_customer_id WHERE v.customer_customer_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 MONTH AND CURDATE() " , nativeQuery = true)
    float customerSummary2(long userId);

    @Query(value = "SELECT COUNT(*) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id INNER JOIN customer as c ON c.customer_id=v.customer_customer_id WHERE v.customer_customer_id=?1 AND i.invoice_date BETWEEN CURDATE() - INTERVAL 1 YEAR AND CURDATE() " , nativeQuery = true)
    int customerSummary3(long userId);

    @Query(value = "SELECT COUNT(*) FROM invoice as i INNER JOIN repair as r ON i.repair_id=r.repair_id INNER JOIN vehicle as v ON v.vehicle_id=r.vehicle_id INNER JOIN customer as c ON c.customer_id=v.customer_customer_id WHERE v.customer_customer_id=?1 AND r.status != 'COMPLETED'" , nativeQuery = true)
    int customerSummary4(long userId);

    Customer findByUserData_Id(long userId);

    List<Customer> findAllByRegisteredDateIsBetween(Date d1,Date d2);
    List<Customer> findAllByRegisteredDateBefore(Date d1);

}
