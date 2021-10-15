package com.loscarpinchos.evertotqm.service;

import com.loscarpinchos.evertotqm.model.Filial;

import java.util.List;

public interface FilialService {
    List<Filial> findAll();
    Filial findById(int id);
    Filial save(Filial filial);
    Filial deleteById(int id);
    List<Filial> findFilialByNameContaining(String name);
    List<Filial> findFilialByCityContaining(String city);
}
