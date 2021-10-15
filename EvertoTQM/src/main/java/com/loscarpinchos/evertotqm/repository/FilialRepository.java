package com.loscarpinchos.evertotqm.repository;

import com.loscarpinchos.evertotqm.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilialRepository  extends JpaRepository <Filial, Integer>{
    List<Filial> findFilialByNameContaining(String name);
    List<Filial> findFilialByCityContaining(String city);
}
