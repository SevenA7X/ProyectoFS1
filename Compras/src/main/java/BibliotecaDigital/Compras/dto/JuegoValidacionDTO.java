package BibliotecaDigital.Compras.dto;

public class JuegoValidacionDTO {
    
    private String titulo;
    private Double precio; 

    public String getTitulo() { 
        return titulo; 
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}