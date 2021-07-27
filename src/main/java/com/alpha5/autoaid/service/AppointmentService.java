package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.NewAppointmentRequest;
import com.alpha5.autoaid.dto.response.NewAppointmentRespond;
import com.alpha5.autoaid.model.Appointment;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.AppointmentRepository;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class AppointmentService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public NewAppointmentRespond newAppointment(NewAppointmentRequest newAppointmentRequest) {
        Customer customer = customerRepository.findByCustomerId(newAppointmentRequest.getCustomerId());
        Staff staff = staffRepository.findByStaffId(newAppointmentRequest.getServiceAdvisorId());

        Vehicle vehicle = new Vehicle();
        if(newAppointmentRequest.getVehicleId()!= null) {
             vehicle = vehicleRepository.findByVehicleId(newAppointmentRequest.getVehicleId());
        }

        Appointment appointment = new Appointment();

        appointment.setCustomer(customer);
        appointment.setStaff(staff);
        appointment.setVehicle(vehicle);
        appointment.setDateAppointment(newAppointmentRequest.getDate());
        appointment.setTimeAppointment(newAppointmentRequest.getTime());
        appointment.setVehicleName(newAppointmentRequest.getVehicleName());

        Appointment newAppointment = appointmentRepository.save(appointment);
        NewAppointmentRespond newAppointmentRespond = new NewAppointmentRespond();

        newAppointmentRespond.setDate(newAppointment.getDateAppointment());
        newAppointmentRespond.setTime(newAppointment.getTimeAppointment());

        return newAppointmentRespond;

    }
}
