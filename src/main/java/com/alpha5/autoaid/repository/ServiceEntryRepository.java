package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.ServiceEntry;
import com.alpha5.autoaid.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {
    List<ServiceEntry> findAllByRepair_RepairId(long repairId);
    List<ServiceEntry> findAllByRepair_RepairIdAndSubCategory_Section_SectionName(long repairId, String sectionName);
    ServiceEntry findByRepair_RepairIdAndSubCategory_SubCatId(long repairId,long subCatId);
}
