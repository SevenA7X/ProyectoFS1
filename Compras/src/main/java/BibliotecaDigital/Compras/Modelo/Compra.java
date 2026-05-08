package BibliotecaDigital.Compras.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name= "db_compras")
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long compraID;
    
    @NotNull(message = "El ID de usuario es obligatorio")
    @Column(nullable= false)
    private Long usuarioID;

    @NotNull(message = "El ID del videojuego es obligatorio")
    @Column(nullable= false)
    private Long videojuegoID;

    @NotNull(message = "La fecha de compra no puede ser nula")
    @PastOrPresent(message = "La fecha de compra no puede ser futura") 
    @Column(nullable= false)
    private LocalDate fecha_compra;

    @NotBlank(message = "El estado de la orden no puede estar vacío")
    @Column(nullable= false)
    private String estado_orden;
}
