package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Appointment;
import com.alpha5.autoaid.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment,Long> {
}
