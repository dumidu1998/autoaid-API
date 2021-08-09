package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findByRepairId(long repairId);
}
