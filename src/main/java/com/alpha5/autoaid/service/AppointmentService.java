package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddAppointment;
import com.alpha5.autoaid.model.Appointment;
import com.alpha5.autoaid.model.AppointmentSlot;
import com.alpha5.autoaid.repository.AppointmentRepository;
import com.alpha5.autoaid.repository.AppointmentSlotsRepository;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentSlotsRepository appointmentSlotsRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    StaffRepository staffRepository;

    public List<AppointmentSlot> getAllSlots() {
        return appointmentSlotsRepository.findAll();
    }

    public boolean addApointment(AddAppointment addAppointment) {
        Appointment appointment = new Appointment();

        appointment.setCustomer(customerRepository.findByUserData_Id(addAppointment.getUserId()));
        appointment.setDate(addAppointment.getDate());
        appointment.setStaff(staffRepository.findByStaffId(addAppointment.getStaffId()));
        appointment.setAppointmentSlot(appointmentSlotsRepository.findByAppointmentSlotId(addAppointment.getSlotId()));

        if(appointmentRepository.save(appointment)!=null){
            return true;
        }else{
            return false;
        }
    }

}
