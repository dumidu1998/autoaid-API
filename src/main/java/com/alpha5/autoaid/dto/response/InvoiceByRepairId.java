package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceByRepairId {
    private String customerName;
    private String address;
    private String city;
    private String mobileNumber;
    private long invoiceNumber;
    private String invoiceDate;
    private BigDecimal amount;
    private String PaymentType;
    private String vehicleNumber;
    private String model;
    private List<ServiceList> services;
    private List<MaterialList> materials;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }

    public List<MaterialList> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialList> materials) {
        this.materials = materials;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }
}
