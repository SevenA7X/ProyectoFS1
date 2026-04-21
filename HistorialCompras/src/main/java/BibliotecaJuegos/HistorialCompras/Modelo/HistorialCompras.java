package BibliotecaJuegos.HistorialCompras.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HISTORIALCOMPRAS")
@Entity

public class HistorialCompras {
    private Long compraID;

    private Long usuarioID;
}
