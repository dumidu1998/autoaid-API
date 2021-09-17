package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlot, Long> {
    AppointmentSlot findByAppointmentSlotId(long slotId);

    @Query(value = "SELECT * FROM appointment_slot WHERE appointment_slot_id NOT IN (SELECT appointment_slot FROM appointment WHERE date=?1)" , nativeQuery = true)
    List<AppointmentSlot> findAvailableSlotsbyDate(String date);
}
