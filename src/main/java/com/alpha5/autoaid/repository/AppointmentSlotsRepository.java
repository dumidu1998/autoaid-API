package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlot, Long> {
    AppointmentSlot findByAppointmentSlotId(long slotId);

}
