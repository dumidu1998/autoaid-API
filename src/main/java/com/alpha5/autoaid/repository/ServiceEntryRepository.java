package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.model.ServiceEntry;
import com.alpha5.autoaid.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {
    List<ServiceEntry> findAllByRepair_RepairId(long repairId);
    List<ServiceEntry> findAllByRepair_RepairIdAndSubCategory_Section_SectionName(long repairId, String sectionName);
    ServiceEntry findByRepair_RepairIdAndSubCategory_SubCatId(long repairId,long subCatId);
    List<ServiceEntry> findAllBySlotAndServiceEntryStatusIs(Slot slot, ServiceEntryStatus serviceEntryStatus);
//    SELECT SUM(estimated_time) FROM `service_entry` AS se INNER JOIN slot AS s ON s.slotid=se.slot_id WHERE s.slotid=39 AND se.service_entry_status="PENDING"
//    SELECT SUM(estimated_time) FROM `service_entry` WHERE slot_id=39 AND service_entry_status="PENDING"
    @Query(value = "SELECT SUM(estimated_time) FROM service_entry WHERE slot_id=?1 AND service_entry_status='PENDING'", nativeQuery = true)
    int findSumOfPending(long slotId);

}
