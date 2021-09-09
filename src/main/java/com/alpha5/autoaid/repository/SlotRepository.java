package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    Slot findBySlotName(String slotName);
    Slot findByStatus(SlotStatus status);
    List<Slot> findAllBySection_SectionName(String sectionName);
    List<Slot> findAllBySection_SectionNameAndStatusIsNot(String sectionName, SlotStatus status);

}
