package BibliotecaJuegos.LicenciasYDescargas.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;
import BibliotecaJuegos.LicenciasYDescargas.Servicio.Servicio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/licencias")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<Licencia>> listarLicencias(){
        List<Licencia> listaLicencias = servicio.findAll();

        if (listaLicencias.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaLicencias);
    }

    @GetMapping("/{licenciaID}")
    public ResponseEntity<Licencia> buscarLicencia(@Valid @PathVariable Long licenciaID){
        try {
            Licencia licencia = servicio.findById(licenciaID);
            return ResponseEntity.ok(licencia);
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Licencia> agregarLicencia(@Valid @RequestBody Licencia licencia){
        try {
            Licencia licenciaNueva = servicio.save(licencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(licenciaNueva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{licenciaID}")
    public ResponseEntity<?> eliminarLicencia(@Valid @PathVariable Long licenciaID){
        try {
            servicio.delete(licenciaID);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{licenciaID}")
    public ResponseEntity<Licencia> actualizarLicencia (@PathVariable Long licenciaID, @Valid @RequestBody Licencia licencia){
        try {
            Licencia lic = servicio.findById(licenciaID);
            if (lic == null){
                return ResponseEntity.notFound().build();
            }
            lic.setLicenciaID(licenciaID);
            lic.setUsuarioID(licencia.getUsuarioID());
            lic.setVideojuegoID(licencia.getVideojuegoID());
            lic.setFecha(licencia.getFecha());
            
            servicio.save(lic);
            return ResponseEntity.ok(lic);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
    }
}
