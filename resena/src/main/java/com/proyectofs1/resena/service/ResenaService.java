package com.proyectofs1.resena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.proyectofs1.resena.model.Resena;
import com.proyectofs1.resena.repository.ResenaRepository;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> findAll() {
        return resenaRepository.findAll();
    }

    public Resena findById(Long id) {
        return resenaRepository.findById(id).get();
    }

    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void deleteById(Long id) {
        resenaRepository.deleteById(id);
    }
}