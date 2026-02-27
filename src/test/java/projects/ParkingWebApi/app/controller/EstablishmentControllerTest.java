package projects.ParkingWebApi.app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projects.ParkingWebApi.app.controller.EstablishmentController;
import projects.ParkingWebApi.app.core.model.Establishment;
import projects.ParkingWebApi.app.core.model.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.core.model.response.EstablishmentResponse;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

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

    @Test
    void get_ReturnSavedEstablishments_ReturnAListOfEstablishmentResponse() {
        List<EstablishmentResponse> listDataMock = new ArrayList<EstablishmentResponse>();
        listDataMock.add(new EstablishmentResponse(1L, "FreeParking", "1721578268", 140, 100));
        listDataMock.add(new EstablishmentResponse(1L, "FlowParking", "1323869759", 100, 80));
        listDataMock.add(new EstablishmentResponse(1L, "ForeverParking", "1125438242", 50, 120));
        listDataMock.add(new EstablishmentResponse(1L, "Parking", "1822390104", 150, 70));
        listDataMock.add(new EstablishmentResponse(1L, "TreeParking", "1621195855", 30, 10));

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(listDataMock);

        ResponseEntity<List<EstablishmentResponse>> response = controller.get(0, 10);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().containsAll(listDataMock));
    }

    @Test
    void getById_ReturnDetailSavedEstablishment_ReturnEstablishmentObject() {
        Establishment dataMock = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        dataMock.setId(1L);

        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(dataMock));

        ResponseEntity<Optional<Establishment>> response = controller.getById(dataMock.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, response.getBody().get().getId());
        Assertions.assertEquals(dataMock.getName(), response.getBody().get().getName());
        Assertions.assertEquals(dataMock.getCnpj(), response.getBody().get().getCnpj());
        Assertions.assertEquals(dataMock.getStreetAddress(), response.getBody().get().getStreetAddress());
        Assertions.assertEquals(dataMock.getNeighborhood(), response.getBody().get().getNeighborhood());
        Assertions.assertEquals(dataMock.getPostalCode(), response.getBody().get().getPostalCode());
        Assertions.assertEquals(dataMock.getState(), response.getBody().get().getState());
        Assertions.assertEquals(dataMock.getCity(), response.getBody().get().getCity());
        Assertions.assertEquals(dataMock.getTelephone(), response.getBody().get().getTelephone());
        Assertions.assertEquals(dataMock.getParkingSpacesForMotorcycles(), response.getBody().get().getParkingSpacesForMotorcycles());
        Assertions.assertEquals(dataMock.getParkingSpacesForCars(), response.getBody().get().getParkingSpacesForCars());

    }

    @Test
    void update_UseIdToUpdateAEstablishmentData_ReturnEstablishmentUpdated() {
        Establishment savedEstablishment = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        savedEstablishment.setId(1L);
        Establishment originalEstablishment = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        originalEstablishment.setId(1L);
        EstablishmentDTO dto = new EstablishmentDTO("Flow Parking", "79298938000190", "Avenida Engenheiro Carlos Goulart 900", "Estoril", "30455902", "SP", "São Paulo", "1725336163", 230, 300);

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(savedEstablishment));
        Mockito.when(repository.save(any(Establishment.class))).thenReturn(savedEstablishment);

        ResponseEntity<Establishment> response = controller.update(savedEstablishment.getId(), dto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(originalEstablishment.getId(), Objects.requireNonNull(response.getBody()).getId());
        Assertions.assertNotEquals(originalEstablishment.getName(), response.getBody().getName());
        Assertions.assertNotEquals(originalEstablishment.getCnpj(), response.getBody().getCnpj());
        Assertions.assertNotEquals(originalEstablishment.getStreetAddress(), response.getBody().getStreetAddress());
        Assertions.assertNotEquals(originalEstablishment.getNeighborhood(), response.getBody().getNeighborhood());
        Assertions.assertNotEquals(originalEstablishment.getPostalCode(), response.getBody().getPostalCode());
        Assertions.assertNotEquals(originalEstablishment.getState(), response.getBody().getState());
        Assertions.assertNotEquals(originalEstablishment.getCity(), response.getBody().getCity());
        Assertions.assertNotEquals(originalEstablishment.getTelephone(), response.getBody().getTelephone());
        Assertions.assertNotEquals(originalEstablishment.getParkingSpacesForMotorcycles(), response.getBody().getParkingSpacesForMotorcycles());
        Assertions.assertNotEquals(originalEstablishment.getParkingSpacesForCars(), response.getBody().getParkingSpacesForCars());
    }

    @Test
    void delete_UseIdToDeleteASavedEstablishment_WithoutReturn() {
        Long idToDelete = 1L;

        Mockito.doNothing().when(repository).deleteById(idToDelete);

        ResponseEntity<Void> response = controller.delete(idToDelete);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(repository, Mockito.times(1)).deleteById(idToDelete);
    }
}
