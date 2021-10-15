package com.loscarpinchos.evertotqm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "filiais")
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String city;

    @OneToMany(targetEntity = Funcionario.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "filial_id", referencedColumnName = "id")
    private List<Funcionario> funcionarios;

    public Filial(int id, String name, String email, String city, List<Funcionario> funcionarios) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.funcionarios = funcionarios;
    }

    public Filial() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
