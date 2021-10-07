package com.webii.webholamundo.services;

import com.webii.webholamundo.model.Postagens;

import java.util.List;

public interface blogEvertonServices {

    List<Postagens> findAll();
    Postagens findById(int id);
    Postagens save(Postagens postagens);
    List<Postagens> findPostagensByTipo(int tipo);
    Postagens deleteById(int id);
}
