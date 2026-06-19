package BibliotecaDigital.Compras;
import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Repositorio.Repositorio;
import BibliotecaDigital.Compras.Servicio.Servicio;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Profile("test") 
@Component     
public class DataLoader implements CommandLineRunner {

    @Autowired
    private Servicio servicio; 

    @Autowired
    private Repositorio comprasRepository; 

    private final Faker faker = new Faker(Locale.forLanguageTag("es"));

    @Override
    public void run(String... args) throws Exception {
        System.out.println("====== REVISANDO BASE DE DATOS (PERFIL DEV) ======");

        if (servicio.findAll().isEmpty()) {
            System.out.println("La base de datos está vacía. Generando compras ficticias con Data Faker...");

            for (int i = 0; i < 10; i++) { 
                Compra entidad = new Compra(); 
                
                entidad.setUsuarioID(faker.number().randomNumber(3, true));    
                entidad.setVideojuegoID(faker.number().randomNumber(2, true)); 
                entidad.setEstado_orden(faker.options().option("Pendiente", "Completada", "Cancelada"));
                
                java.util.Date fechaFaker = faker.date().past(30, TimeUnit.DAYS);
                LocalDate fechaLocalDate = fechaFaker.toInstant()
                                                     .atZone(ZoneId.systemDefault())
                                                     .toLocalDate();
                entidad.setFecha_compra(fechaLocalDate);
                
                comprasRepository.save(entidad);
            }

            System.out.println("====== ¡10 COMPRAS GENERADAS EXITOSAMENTE EN MYSQL! ======");
        } else {
            System.out.println("La base de datos ya contiene registros. Se omitió la generación de datos.");
        }
    }
}