package com.loscarpinchos.evertotqm.service;

import com.loscarpinchos.evertotqm.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    List<Funcionario> findAll();
    Funcionario findById(int id);
    Funcionario save(Funcionario funcionario);
    Funcionario deleteById(int id);
    List<Funcionario> findFuncionarioByFilialId(int filial_id);
    List<Funcionario> findFuncionarioByNameContaining(String name);
}
