package br.edu.ifsp.model.entities.instructor;

import br.edu.ifsp.model.entities.category.DrivingCategory;
import br.edu.ifsp.model.enums.RegistrationStatus;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private long id;
    private String name;
    private String cpf;
    private String rg;
    private String cnh;
    private String address;
    private String phone;
    private String bankAccount;
    private List<DrivingCategory> drivingCategory = new ArrayList<>();
    private RegistrationStatus registrationStatus; // não estava no diagrama de classes

    public Instructor() {
    }

    public Instructor(long id, String name, String cpf, String rg, String cnh, String address, String phone, String bankAccount, RegistrationStatus registrationStatus) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.cnh = cnh;
        this.address = address;
        this.phone = phone;
        this.bankAccount = bankAccount;
        this.registrationStatus = registrationStatus;
    }

    public Instructor(String name, String cpf, String rg, String cnh, String address, String phone, String bankAccount, RegistrationStatus registrationStatus) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.cnh = cnh;
        this.address = address;
        this.phone = phone;
        this.bankAccount = bankAccount;
        this.registrationStatus = registrationStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setDrivingCategory(List<DrivingCategory> drivingCategory) {
        this.drivingCategory = drivingCategory;
    }

    public List<DrivingCategory> getDrivingCategory() {
        return drivingCategory;
    }

    public void addDrivingCategory(DrivingCategory drivingCategory){
        this.drivingCategory.add(drivingCategory);
    }

    public void removeDrivingCategory(DrivingCategory drivingCategory){
        this.drivingCategory.remove(drivingCategory);
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    @Override
    public String toString() {
        return getName();
    }
}
