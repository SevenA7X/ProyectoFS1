package BibliotecaDigital.Compras;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/db_compras_test",
    "spring.datasource.username=root",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    
    "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=false",
    
    "spring.cloud.openfeign.circuitbreaker.enabled=false",
    "spring.cloud.openfeign.enabled=false",
    "eureka.client.enabled=false",
    
    "springdoc.api-docs.enabled=false",
    "springdoc.swagger-ui.enabled=false"
})

@ActiveProfiles("test")
class ComprasApplicationTests {

    @Test
    void contextLoads() {
    }
}