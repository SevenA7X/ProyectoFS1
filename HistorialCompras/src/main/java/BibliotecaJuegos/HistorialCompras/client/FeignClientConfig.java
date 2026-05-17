package BibliotecaJuegos.HistorialCompras.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ComprasFeignClient compraClient() {
        return Feign.builder()
                .encoder(new JacksonEncoder()) 
                .decoder(new JacksonDecoder()) 
                .logger(new Slf4jLogger(ComprasFeignClient.class))
                .logLevel(feign.Logger.Level.FULL)
                .target(ComprasFeignClient.class, "http://localhost:8085");
    }
}