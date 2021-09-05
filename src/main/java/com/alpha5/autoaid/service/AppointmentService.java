package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddAppointment;
import com.alpha5.autoaid.dto.response.StaffListRespond;
import com.alpha5.autoaid.model.Appointment;
import com.alpha5.autoaid.model.AppointmentSlot;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    VehicleRepository vehicleRepository;
    @Autowired
    StaffRepository staffRepository;

    public List<AppointmentSlot> getAllSlots() {
        return appointmentSlotsRepository.findAll();
    }

    public boolean addAppointment(AddAppointment addAppointment) {
        Appointment appointment = new Appointment();

        appointment.setVehicle(vehicleRepository.findByVehicleId(addAppointment.getVehicleId()));
        appointment.setDate(addAppointment.getDate());
        appointment.setStaff(staffRepository.findByStaffId(addAppointment.getStaffId()));
        appointment.setAppointmentSlot(appointmentSlotsRepository.findByAppointmentSlotId(addAppointment.getSlotId()));

        if(appointmentRepository.save(appointment)!=null){
            return true;
        }else{
            return false;
        }
    }

    public List<AppointmentSlot> getFreeSlotsByDate(Date date) {
        String shortDate=new SimpleDateFormat("YYYY-MM-dd").format(date);
        List<AppointmentSlot> response = appointmentSlotsRepository.findAvailableSlotsbyDate(shortDate);
        if(response!=null) {
            return response;
        }else{
            return null;
        }
    }

    public List<StaffListRespond> getServiceAdvisorFromDate(Date date) {
        String shortDate=new SimpleDateFormat("YYYY-MM-dd").format(date);
        List<Staff> output=staffRepository.findAvailableServiceAdvisorsByDate(shortDate);
        List<StaffListRespond> response = new ArrayList<>();
        for(Staff staff:output){
            StaffListRespond out = new StaffListRespond();
            out.setFirstName(staff.getFirstName());
            out.setLastname(staff.getLastName());
            out.setId(staff.getStaffId());
            response.add(out);
        }
        return response;
    }

//    public ResponseEntity getUpcomingAppointments(long id) {
//        List<Appointments> appointments = staffRepository.
//    }
}
