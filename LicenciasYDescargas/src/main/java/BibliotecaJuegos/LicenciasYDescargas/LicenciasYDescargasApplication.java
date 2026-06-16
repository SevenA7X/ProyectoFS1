package BibliotecaJuegos.LicenciasYDescargas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LicenciasYDescargasApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenciasYDescargasApplication.class, args);
	}

}
