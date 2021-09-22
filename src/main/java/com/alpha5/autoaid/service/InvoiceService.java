package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.response.InvoiceByRepairId;
import com.alpha5.autoaid.dto.response.MaterialList;
import com.alpha5.autoaid.dto.response.ServiceList;
import com.alpha5.autoaid.enums.ItemRequestStatus;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.model.ItemRequest;
import com.alpha5.autoaid.model.Repair;
import com.alpha5.autoaid.model.ServiceEntry;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ItemRequestRepository itemCategoryRepository;

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    public InvoiceByRepairId getInvoiceByRepairId(long id) {
        InvoiceByRepairId response = new InvoiceByRepairId();
        Repair repair = repairRepository.findByRepairId(id);
        List<ServiceEntry> services = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIs(id, ServiceEntryStatus.COMPLETED);
        List<ServiceList> serviceList = new ArrayList<>();
        if(services.size()!=0) {
            for (ServiceEntry serviceEntry : services) {
                System.out.println(serviceEntry.getEstimatedTime()/60);
                serviceList.add(new ServiceList(serviceEntry.getSubCategory().getSubCatName() + (serviceEntry.getDescription() == null ? " " : " (" + serviceEntry.getDescription() + ") "), new BigDecimal((double)serviceEntry.getEstimatedTime()/60).multiply(new BigDecimal(1000))));
            }
        }
        List<MaterialList> materialList=new ArrayList<>();
        List<ItemRequest> requests = itemCategoryRepository.findAllByRepair_repairIdAndStatusIs(id, ItemRequestStatus.COMPLETED);
        if(requests.size()!=0) {
            for (ItemRequest request : requests) {
                materialList.add(new MaterialList(request.getInvItem().getItemName(), request.getQuantity(), request.getInvItem().getPrice(), new BigDecimal(request.getQuantity()).multiply(request.getInvItem().getPrice())));
            }
        }
        response.setCustomerName(repair.getVehicle().getCustomer().getFirstName()+' '+repair.getVehicle().getCustomer().getLastName());
        response.setAddress(repair.getVehicle().getCustomer().getUserData().getAddress());
        response.setCity(repair.getVehicle().getCustomer().getUserData().getCity());
        response.setMobileNumber(repair.getVehicle().getCustomer().getUserData().getContactNo());
        response.setInvoiceDate(repair.getInvoices().getInvoiceDate().toString());
        response.setAmount(repair.getInvoices().getAmount());
        response.setInvoiceNumber(repair.getInvoices().getInvoiceId());
        response.setPaymentType(repair.getPaymentType().toString());
        response.setVehicleNumber(repair.getVehicle().getVehicleNumber());
        response.setModel(repair.getVehicle().getModel());
        response.setServices(serviceList);
        response.setMaterials(materialList);

        return response;

    }
}

