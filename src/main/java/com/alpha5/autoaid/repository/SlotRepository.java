package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    Slot findBySlotName(String slotName);
    Slot findByStatus(SlotStatus status);
}
