package BibliotecaDigital.Compras.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BibliotecaDigital.Compras.modelo.Compra;
import BibliotecaDigital.Compras.repositorio.Repositorio;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class Servicio {
    @Autowired
    private Repositorio repositorio;

    public List<Compra> findAll(){
        return repositorio.findAll();
    }

    public Compra findById(Long compraID){
        return repositorio.findById(compraID).get();
    }

    public Compra save(Compra compra){
        return repositorio.save(compra);
    }

    public void delete(Long compraID){
        repositorio.deleteById(compraID);
    }
}
