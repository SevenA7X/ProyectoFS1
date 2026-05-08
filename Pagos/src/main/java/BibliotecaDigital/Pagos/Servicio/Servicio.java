package BibliotecaDigital.Pagos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BibliotecaDigital.Pagos.Modelo.Pagos;
import BibliotecaDigital.Pagos.Repositorio.Repositorio;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class Servicio{
    @Autowired
    private repositorio repositorio;

    public List<Pagos> listarPagos() {
        return repositorio.findAll();
    }

    public Pagos obtenerPagoPorId(Long pagoID) {
        return repositorio.findById(pagoID).get();
    }

    public Pagos guardarPago(Pagos pago) {
        return repositorio.save(pago);
    }

    public void eliminarPago(Long pagoID) {
        repositorio.deleteById(pagoID);
    }
}
