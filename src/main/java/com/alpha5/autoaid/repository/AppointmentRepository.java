package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {



}
