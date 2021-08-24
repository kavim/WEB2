package com.webii.webholamundo.servicesImpl;

import com.webii.webholamundo.model.Postagens;
import com.webii.webholamundo.services.blogEvertonServices;
import org.springframework.beans.factory.annotation.Autowired;

import com.webii.webholamundo.repository.web2Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogEvertonServiceImpl implements blogEvertonServices {
    @Autowired
    web2Repository blogEvertonRepository;

    @Override
    public List<Postagens> findAll() {

        return blogEvertonRepository.findAll();
    }

    @Override
    public Postagens findById(long id) {

        return blogEvertonRepository.findById(id).get();
    }

    @Override
    public Postagens save(Postagens postagens) {

        return blogEvertonRepository.save(postagens);
    }
}
