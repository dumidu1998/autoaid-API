package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.enums.ItemRequestStatus;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.model.*;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service

public class CashierService {

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

    public CashierInvoiceRespond cashierGetRepairInvoice(long id) {
        CashierInvoiceRespond response = new CashierInvoiceRespond();
        Repair repair = repairRepository.findByRepairId(id);
        List<ServiceEntry> services = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIs(id, ServiceEntryStatus.COMPLETED);
        List<ServiceList> serviceList = new ArrayList<>();
        BigDecimal sum = BigDecimal.valueOf(0);
        if(services.size()!=0) {
            for (ServiceEntry serviceEntry : services) {
                System.out.println(serviceEntry.getEstimatedTime());
                serviceList.add(new ServiceList(serviceEntry.getSubCategory().getSubCatName() + (serviceEntry.getDescription() == null ? " " : " (" + serviceEntry.getDescription() + ") "), new BigDecimal((serviceEntry.getEstimatedTime())*1000/60)));
                sum = sum.add(new BigDecimal((double)(serviceEntry.getEstimatedTime())*1000/60));
            }
        }
        List<MaterialList> materialList=new ArrayList<>();
        List<ItemRequest> requests = itemCategoryRepository.findAllByRepair_repairIdAndStatusIs(id, ItemRequestStatus.COMPLETED);
        if(requests.size()!=0) {

            for (ItemRequest request : requests) {
                materialList.add(new MaterialList(request.getInvItem().getItemName(), request.getQuantity(), request.getInvItem().getPrice(), new BigDecimal(request.getQuantity()).multiply(request.getInvItem().getPrice())));
                sum = sum.add(new BigDecimal(request.getQuantity()).multiply(request.getInvItem().getPrice()));
            }
        }
        response.setAmount(sum);
        response.setCustomerName(repair.getVehicle().getCustomer().getFirstName()+' '+repair.getVehicle().getCustomer().getLastName());
        response.setAddress(repair.getVehicle().getCustomer().getUserData().getAddress());
        response.setCity(repair.getVehicle().getCustomer().getUserData().getCity());
        response.setMobileNumber(repair.getVehicle().getCustomer().getUserData().getContactNo());
        response.setVehicleNumber(repair.getVehicle().getVehicleNumber());
        response.setModel(repair.getVehicle().getModel());
        response.setServices(serviceList);
        response.setMaterials(materialList);

        return response;

    }

    public  List<CashierRepairDetailsRespond> getAllRepairs(){
        List<Repair> all = repairRepository.findAll();
        List<CashierRepairDetailsRespond> response = new ArrayList<>();
        for(Repair repair : all){
            if(repair.getStatus().toString() == "COMPLETED"){
                CashierRepairDetailsRespond cashierRepairDetailsRespond = new CashierRepairDetailsRespond();
                cashierRepairDetailsRespond.setRepairId(repair.getRepairId());
                cashierRepairDetailsRespond.setVehicleNumber(repair.getVehicle().getVehicleNumber());
                cashierRepairDetailsRespond.setName(repair.getVehicle().getCustomer().getFirstName()+" "+repair.getVehicle().getCustomer().getLastName());
                cashierRepairDetailsRespond.setContactNo(repair.getVehicle().getCustomer().getUserData().getContactNo());

                response.add(cashierRepairDetailsRespond);
            }
        }
        return response;
    }
    public  List<CashierRepairDetailsRespond> getHandOverRepairs(){
        List<Repair> allHandOver = repairRepository.findAll();
        List<CashierRepairDetailsRespond> response = new ArrayList<>();
        for(Repair repair : allHandOver){
            if(repair.getStatus().toString() == "HANDOVER"){
                CashierRepairDetailsRespond cashierRepairDetailsRespond = new CashierRepairDetailsRespond();
                cashierRepairDetailsRespond.setRepairId(repair.getRepairId());
                cashierRepairDetailsRespond.setVehicleNumber(repair.getVehicle().getVehicleNumber());
                String completedDate=new SimpleDateFormat("YYYY-MM-dd").format(repair.getRepairCompletedDate());
                cashierRepairDetailsRespond.setRepairCompletedDate(completedDate);
                cashierRepairDetailsRespond.setName(repair.getVehicle().getCustomer().getFirstName()+" "+repair.getVehicle().getCustomer().getLastName());
                cashierRepairDetailsRespond.setContactNo(repair.getVehicle().getCustomer().getUserData().getContactNo());

                response.add(cashierRepairDetailsRespond);
            }
        }
        return response;
    }


}
