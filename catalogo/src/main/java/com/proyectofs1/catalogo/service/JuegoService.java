package com.proyectofs1.catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofs1.catalogo.model.Juego;
import com.proyectofs1.catalogo.repository.JuegoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JuegoService {
    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> findAll(){
        return juegoRepository.findAll();
    }
    public Juego findById(Long id){
        return juegoRepository.findById(id).get();
    }
    public Juego save(Juego juego){
        return juegoRepository.save(juego);
    
    }
    public void deleteById(Long id){
        juegoRepository.deleteById(id);
    }
}
