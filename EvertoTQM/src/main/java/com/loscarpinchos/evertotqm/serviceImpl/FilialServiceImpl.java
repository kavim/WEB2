package com.loscarpinchos.evertotqm.serviceImpl;

import com.loscarpinchos.evertotqm.model.Filial;
import com.loscarpinchos.evertotqm.service.FilialService;

import java.util.List;

public class FilialServiceImpl implements FilialService {
    @Override
    public List<Filial> findAll() {
        return null;
    }

    @Override
    public Filial findById(int id) {
        return null;
    }

    @Override
    public Filial save(Filial filial) {
        return null;
    }

    @Override
    public Filial deleteById(int id) {
        return deleteById(id);
    }

    @Override
    public List<Filial> findFilialByNameContaining(String name) {
        return findFilialByNameContaining(name);
    }

    @Override
    public List<Filial> findFilialByCityContaining(String city) {
        return findFilialByCityContaining(city);
    }
}
