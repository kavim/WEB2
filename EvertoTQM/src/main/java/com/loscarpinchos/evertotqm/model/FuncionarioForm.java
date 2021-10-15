package com.loscarpinchos.evertotqm.model;

public class FuncionarioForm {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String sector;
    private int filial;

    public FuncionarioForm() {
    }

    public FuncionarioForm(int id, String name, String email, String phone, String sector, int filial) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sector = sector;
        this.filial = filial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }
}
