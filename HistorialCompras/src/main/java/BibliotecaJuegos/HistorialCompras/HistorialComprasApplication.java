package BibliotecaJuegos.HistorialCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HistorialComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialComprasApplication.class, args);
	}

}
