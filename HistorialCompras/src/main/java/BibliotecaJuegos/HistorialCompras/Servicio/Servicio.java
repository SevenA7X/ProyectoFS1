package BibliotecaJuegos.HistorialCompras.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class Servicio {
    @Autowired
    private Repositorio repositorio;

    public List<HistorialCompras> findAll(){
        return repositorio.findAll();
    }

    public HistorialCompras findById(Long historialID){
        return repositorio.findById(historialID).get();
    }

    public HistorialCompras save(HistorialCompras historialCompras){
        return repositorio.save(historialCompras);
    }

    public void delete(Long historialID){
        repositorio.deleteById(historialID);
    }

}
