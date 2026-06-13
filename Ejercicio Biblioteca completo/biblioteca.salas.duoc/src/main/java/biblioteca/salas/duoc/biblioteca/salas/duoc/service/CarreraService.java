package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {
    
    @Autowired
    private CarreraRepository carreraRepository;

    @ArraySchema(schema = @Schema(implementation = Carrera.class))
    private List<Carrera> carreras;
    
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    public Carrera findByCodigo(String codigo) {
        return carreraRepository.findById(codigo).orElse(null);
    }

    public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteByCodigo(String codigo) {
        carreraRepository.deleteById(codigo);
    }
}