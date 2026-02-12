package projects.ParkingWebApi.app.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI parkingOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Parking Management API")
                        .description("A Rest API created to management parking spaces. " +
                                "\nYou can do CRUD operation (CREATE, READ, UPDATE and DELETE) in Establishment and Vehicles entities. " +
                                "The Ticket entity are used to management the checkin and checkout of the vehicles (motorcycles and cars) on the establishment (parking spaces)")
                        .version("v1.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Mateus Henrique Alves de Souza")
                                .email("mateushas.dev@gmail.com")
                                .url("https://github.com/Mateus2611")));
    }
}
