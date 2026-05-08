package BibliotecaJuegos.LicenciasYDescargas.modelo;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "El ID de usuario es obligatorio")
    @Positive(message = "El ID de usuario debe ser un número positivo")
    @Column(nullable=false)
    private Long usuarioID;
    
    @NotNull(message = "El ID del videojuego es obligatorio")
    @Positive(message = "El ID del videojuego debe ser un número positivo")
    @Column(nullable=false)
    private Long videojuegoID;

    @NotNull(message = "La fecha de emisión de la licencia es obligatoria")
    @PastOrPresent(message = "La fecha de la licencia no puede ser una fecha futura")
    @Column(nullable=false)
    private LocalDate fecha;
}