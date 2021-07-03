package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.VehicleAddRequest;
import com.alpha5.autoaid.dto.response.VehicleAddResponse;
import com.alpha5.autoaid.model.VehicleDetails;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdvisorService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleAddResponse addvehicle(VehicleAddRequest vehicleAddRequest){
        //check whether vehicle exists
        VehicleDetails vehicle = vehicleRepository.findByVehicleNumber(vehicleAddRequest.getVehicleNumber());
        if(vehicle != null){
            VehicleAddResponse response= new VehicleAddResponse();
            response.setVehiceId(vehicle.getVehicleId());
            response.setChassisNo(vehicle.getChassisNo());
            response.setEnginNo(vehicle.getEngineNo());
            response.setMake(vehicle.getMake());
            response.setModel(vehicle.getModel());

            return response;
        }else throw new RuntimeException("Vehicle not there");

    }
}
