package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {
    List<ServiceEntry> findAllByRepair_RepairId(long repairId);
}
