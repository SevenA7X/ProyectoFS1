package BibliotecaJuegos.LicenciasYDescargas.Modelo;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_licencias")
@Entity

public class Licencia{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenciaID;

    @NotNull
    @Column(nullable=false)
    private Long usuarioID;
    
    @NotNull
    @Column(nullable=false)
    private Long videojuegoID;

    @NotNull
    @Column(nullable=false)
    private LocalDate fecha;
}
