package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByRepair_RepairId(long repairId);
    List<Invoice> findAllByInvoiceDateBetween(Date date, Date date2);
    List<Invoice> findAllByInvoiceDateAfter(Date date);
    List<Invoice> findAllByInvoiceDateBefore(Date date);

    @Query(value = "SELECT sum(amount) FROM invoice as i WHERE i.invoice_date BETWEEN CURDATE() - INTERVAL 1 MONTH AND CURDATE() " , nativeQuery = true)
    BigDecimal findsuminmonth();

    @Query(value = "SELECT sum(amount),MONTH( invoice_date ) FROM invoice i INNER JOIN repair r ON r.repair_id=i.repair_id INNER JOIN vehicle v ON v.vehicle_id=r.vehicle_id INNER JOIN customer c ON c.customer_id=v.customer_customer_id INNER JOIN user_data ud ON ud.id=c.user_data_id WHERE ud.id=?1 GROUP BY MONTH( invoice_date )" , nativeQuery = true)
    List<Object> getsummonth(long id);

    @Query(value = "SELECT sum(amount),v.vehicle_number FROM invoice i INNER JOIN repair r ON r.repair_id=i.repair_id INNER JOIN vehicle v ON v.vehicle_id=r.vehicle_id INNER JOIN customer c ON c.customer_id=v.customer_customer_id INNER JOIN user_data ud ON ud.id=c.user_data_id WHERE ud.id=?1 GROUP BY v.vehicle_number" , nativeQuery = true)
    List<Object> getsummonth2(long id);

}
