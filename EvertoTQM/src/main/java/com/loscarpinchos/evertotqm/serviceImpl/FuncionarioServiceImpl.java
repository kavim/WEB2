package com.loscarpinchos.evertotqm.serviceImpl;

import com.loscarpinchos.evertotqm.model.Funcionario;
import com.loscarpinchos.evertotqm.service.FuncionarioService;

import java.util.List;

public class FuncionarioServiceImpl implements FuncionarioService {
    @Override
    public List<Funcionario> findAll() {
        return null;
    }

    @Override
    public Funcionario findById(int id) {
        return null;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        return null;
    }

    @Override
    public Funcionario deleteById(int id) {
        return deleteById(id);
    }

    @Override
    public List<Funcionario> findFuncionarioByFilialId(int filial_id) {
        return null;
    }

    @Override
    public List<Funcionario> findFuncionarioByNameContaining(String name) {
        return findFuncionarioByNameContaining(name);
    }
}
