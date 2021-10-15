package com.loscarpinchos.evertotqm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "1")
    private String name;

    @NotBlank(message = "2")
    private String email;

    @NotBlank(message = "3")
    private String phone;

    @NotBlank(message = "4")
    private String sector;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    public Funcionario(FuncionarioForm funcionarioForm , Filial filial) {
        this.name = funcionarioForm.getName();
        this.email = funcionarioForm.getEmail();
        this.phone = funcionarioForm.getPhone();
        this.sector = funcionarioForm.getSector();
        this.filial = filial;
    }

    public Funcionario() {
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

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
