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

}
