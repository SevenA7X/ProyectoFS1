package BibliotecaDigital.Compras.Modelo;

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
@Entity
@Table(name= "db_compras")
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long compraID;
    
    @NotNull
    @Column(nullable= false)
    private Long usuarioID;

    @NotNull
    @Column(nullable= false)
    private Long videojuegoID;

    @NotNull
    @Column(nullable= false)
    private LocalDate fecha_compra;

    @NotNull
    @Column(nullable= false)
    private String estado_orden;
}
