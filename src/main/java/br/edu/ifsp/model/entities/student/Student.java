package br.edu.ifsp.model.entities.student;

public class Student {
    private long id;
    private String name;
    private String cpf;
    private String rg;
    private String cnh;
    private String address;
    private String phone;
    private String email;


    public Student(long id, String name, String cpf, String rg, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public Student(long id, String name, String cpf, String rg, String cnh, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.cnh= cnh;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Student(String name, String cpf, String rg, String cnh, String address, String phone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.cnh= cnh;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getName();
    }
}
