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

    // Nuevo método: Llama a la búsqueda por nombre de la entidad Categoria
    public List<Juego> buscarPorCategoria(String nombreCategoria) {
        return juegoRepository.findByCategoriaNombre(nombreCategoria);
    }

    // Llama a la búsqueda por coincidencia parcial
    public List<Juego> buscarPorTitulo(String titulo) {
        return juegoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Llama a la búsqueda por presupuesto
    public List<Juego> buscarPorPresupuesto(double precioMaximo) {
        return juegoRepository.findByPrecioLessThanEqual(precioMaximo);
    }

    // Llama a la búsqueda combinada JPQL
    public List<Juego> buscarPorGeneroYPresupuesto(String genero, double precioMaximo) {
        return juegoRepository.buscarPorGeneroYPrecioMaximo(genero, precioMaximo);
    }
    
    // Llama a la consulta SQL nativa
    public List<Juego> buscarOfertas() {
        return juegoRepository.buscarTop3OfertasNativo();
    }
}