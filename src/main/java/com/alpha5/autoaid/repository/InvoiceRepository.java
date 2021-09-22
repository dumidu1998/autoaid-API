package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByRepair_RepairId(long repairId);
    List<Invoice> findAllByInvoiceDateBetween(Date date, Date date2);
}
