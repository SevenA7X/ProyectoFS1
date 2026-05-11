package BibliotecaDigital.Compras.Modelo;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    
    @Column(nullable= false)
    private Long usuarioID;

    @Column(nullable= false)
    private Long videojuegoID;

    @Column(nullable= false)
    private LocalDate fecha_compra;

    @Column(nullable= false)
    private String estado_orden;
}