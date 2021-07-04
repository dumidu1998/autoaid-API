package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
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

    public VehicleDetailsAutofillResponse autoFillVehicleDetails(VehicleDetailsAutofillRequest vehicleDetailsAutofillRequest){
        //check whether vehicle exists
        VehicleDetails vehicle = vehicleRepository.findByVehicleNumber(vehicleDetailsAutofillRequest.getVehicleNumber());
        if(vehicle != null){
            VehicleDetailsAutofillResponse response= new VehicleDetailsAutofillResponse();
            response.setVehiceId(vehicle.getVehicleId());
            response.setChassisNo(vehicle.getChassisNo());
            response.setEnginNo(vehicle.getEngineNo());
            response.setMake(vehicle.getMake());
            response.setModel(vehicle.getModel());

            return response;
        }else throw new RuntimeException("Vehicle not registered. Add details");

    }
    public String registerNewVehicle(VehicleDetails vehicleDetails){
        VehicleDetails newVehicle=vehicleRepository.save(vehicleDetails);
        return "Vehicle Added Successfully";
    }
}
