package com.webii.webholamundo.repository;

import com.webii.webholamundo.model.Postagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface web2Repository extends JpaRepository <Postagens, Integer> {
}
