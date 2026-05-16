package BibliotecaJuegos.LicenciasYDescargas.Modelo;

import java.time.LocalDate;

import BibliotecaJuegos.LicenciasYDescargas.dto.LicenciasYDescargasDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_licencias")
@Entity
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenciaID;

    @Column(nullable=false)
    private Long usuarioID;
    
    @Column(nullable=false)
    private Long videojuegoID;

    @Column(nullable=false)
    private LocalDate fecha;

    @Column(nullable=false, unique=true)
    private String codigoLicencia;
}