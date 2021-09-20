package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByRepair_RepairId(long repairId);
}
