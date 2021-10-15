package com.loscarpinchos.evertotqm.repository;

import com.loscarpinchos.evertotqm.model.Filial;
import com.loscarpinchos.evertotqm.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("Select f from Funcionario as f where f.filial = :filial_id")
    List<Funcionario> findFuncionarioByFilialId(int filial_id);

    List<Funcionario> findFuncionarioByNameContaining(String name);
}
