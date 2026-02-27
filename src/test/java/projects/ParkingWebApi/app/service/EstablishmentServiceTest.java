package projects.ParkingWebApi.app.service;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.InvalidStateException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import projects.ParkingWebApi.app.core.exception.InvalidTelephoneNumberException;
import projects.ParkingWebApi.app.core.model.Establishment;
import projects.ParkingWebApi.app.core.model.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.core.service.EstablishmentService;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstablishmentServiceTest {

    @Mock
    private IEstablishmentRepository repository;

    @InjectMocks
    private EstablishmentService service;

    @Test
    void save_CreateNewEstablishment_ReturnEstablishment() throws NumberParseException {

        EstablishmentDTO dto = new EstablishmentDTO("FreeParking", "73.041.245/0001-30", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);

        Establishment dataMock = new Establishment("FreeParking", "73.041.245/0001-30", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        dataMock.setId(1L);

        Mockito.when(repository.save(any(Establishment.class))).thenReturn(dataMock);

        Establishment response = service.save(dto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals(dto.name, response.getName());
        Assertions.assertEquals(dto.cnpj, response.getCnpj());
        Assertions.assertEquals(dto.streetAddress, response.getStreetAddress());
        Assertions.assertEquals(dto.neighborhood, response.getNeighborhood());
        Assertions.assertEquals(dto.postalCode, response.getPostalCode());
        Assertions.assertEquals(dto.state, response.getState());
        Assertions.assertEquals(dto.city, response.getCity());
        Assertions.assertEquals(dto.telephone, response.getTelephone());
        Assertions.assertEquals(dto.parkingSpacesForMotorcycles, response.getParkingSpacesForMotorcycles());
        Assertions.assertEquals(dto.parkingSpacesForCars, response.getParkingSpacesForCars());
    }

    @Test
    void save_ThrowInvalidStateExceptionWhenReceiveIncorrectCnpj_ReturnException() {
        EstablishmentDTO dto = new EstablishmentDTO("FreeParking", "73.041.245/0001-47", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);

        Assertions.assertThrows(InvalidStateException.class, () -> {
            service.save(dto);
        });
    }

    @Test
    void save_ThrowInvalidTelephoneNumberException_ReturnException() {
        EstablishmentDTO dto = new EstablishmentDTO("FreeParking", "73.041.245/0001-30", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "(01) 3367-6445", 100, 180);

        Assertions.assertThrows(InvalidTelephoneNumberException.class, () -> {
            service.save(dto);
        });
    }
}
