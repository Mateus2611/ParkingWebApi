package projects.ParkingWebApi.app.infrastructure.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projects.ParkingWebApi.app.controller.EstablishmentController;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EstablishmentControllerTest {

    @Mock
    private IEstablishmentRepository repository;

    @InjectMocks
    private EstablishmentController controller;

    @Test
    void create_CreateNewEstablishment_ReturnsEstablishment() {
        EstablishmentDTO dto = new EstablishmentDTO("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);

        Establishment dataMock = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        dataMock.setId(1L);

        Mockito.when(repository.save(any(Establishment.class))).thenReturn(dataMock);

        ResponseEntity<Establishment> response = controller.create(dto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1L, response.getBody().getId());
        Assertions.assertEquals(dto.name, response.getBody().getName());
        Assertions.assertEquals(dto.cnpj, response.getBody().getCnpj());
        Assertions.assertEquals(dto.streetAddress, response.getBody().getStreetAddress());
        Assertions.assertEquals(dto.neighborhood, response.getBody().getNeighborhood());
        Assertions.assertEquals(dto.postalCode, response.getBody().getPostalCode());
        Assertions.assertEquals(dto.state, response.getBody().getState());
        Assertions.assertEquals(dto.city, response.getBody().getCity());
        Assertions.assertEquals(dto.telephone, response.getBody().getTelephone());
        Assertions.assertEquals(dto.parkingSpacesForMotorcycles, response.getBody().getParkingSpacesForMotorcycles());
        Assertions.assertEquals(dto.parkingSpacesForCars, response.getBody().getParkingSpacesForCars());
    }
}
