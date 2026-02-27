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
import projects.ParkingWebApi.app.controller.VehicleController;
import projects.ParkingWebApi.app.core.model.Vehicle;
import projects.ParkingWebApi.app.core.model.VehicleType;
import projects.ParkingWebApi.app.core.model.dto.VehicleDTO;
import projects.ParkingWebApi.app.infrastructure.repository.IVehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private IVehicleRepository repository;

    @InjectMocks
    private VehicleController controller;

    @Test
    void create_CreateNewVehicle_ReturnnVehicle() {
        VehicleDTO dto = new VehicleDTO("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        Vehicle newVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        newVehicle.setId(1L);

        Mockito.when(repository.save(any(Vehicle.class))).thenReturn(newVehicle);

        ResponseEntity<Vehicle> response = controller.create(dto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getId());
        Assertions.assertEquals(newVehicle.getBrand(), response.getBody().getBrand());
        Assertions.assertEquals(newVehicle.getModel(), response.getBody().getModel());
        Assertions.assertEquals(newVehicle.getColor(), response.getBody().getColor());
        Assertions.assertEquals(newVehicle.getPlate(), response.getBody().getPlate());
        Assertions.assertEquals(newVehicle.getType(), response.getBody().getType());
    }

    @Test
    void get_ReturnSavedVehicles_ReturnAListOfVehicle() {

        List<Vehicle> savedVehicles = new ArrayList<>();
        savedVehicles.add(new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR));
        savedVehicles.add(new Vehicle("FIAT", "Palio EDX", "Green", "MVD6096", VehicleType.CAR));
        savedVehicles.add(new Vehicle("HONDA", "CG 125", "Black", "MOP9201", VehicleType.MOTORCYCLE));
        savedVehicles.add(new Vehicle("YAMAHA", "Thunder", "White", "HOS3492", VehicleType.MOTORCYCLE));
        savedVehicles.add(new Vehicle("FORD", "Mustang GTI", "Red", "HXK9033", VehicleType.CAR));

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(savedVehicles);

        ResponseEntity<List<Vehicle>> response = controller.get(0, 10);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().containsAll(savedVehicles));
    }

    @Test
    void getById_ReturnASavedVehicleFilteredById_ReturnVehicleObject() {
        Vehicle savedVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        savedVehicle.setId(1L);

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(savedVehicle));

        ResponseEntity<Optional<Vehicle>> response = controller.getById(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(savedVehicle.getId(), Objects.requireNonNull(response.getBody().get().getId()));
        Assertions.assertEquals(savedVehicle.getBrand(), response.getBody().get().getBrand());
        Assertions.assertEquals(savedVehicle.getModel(), response.getBody().get().getModel());
        Assertions.assertEquals(savedVehicle.getColor(), response.getBody().get().getColor());
        Assertions.assertEquals(savedVehicle.getPlate(), response.getBody().get().getPlate());
        Assertions.assertEquals(savedVehicle.getType(), response.getBody().get().getType());
    }

    @Test
    void update_UseIdToUpdateAVehicleData_ReturnVehicleObject() {
        Vehicle savedVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        savedVehicle.setId(1L);
        Vehicle originalVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        originalVehicle.setId(1L);
        VehicleDTO dataToUpdate = new VehicleDTO("HONDA", "CG 125", "Black", "MOP9201", VehicleType.MOTORCYCLE);

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(savedVehicle));
        Mockito.when(repository.save(any(Vehicle.class))).thenReturn(savedVehicle);

        ResponseEntity<Vehicle> response = controller.update(originalVehicle.getId(), dataToUpdate);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(originalVehicle.getId(), Objects.requireNonNull(response.getBody().getId()));
        Assertions.assertNotEquals(originalVehicle.getBrand(), response.getBody().getBrand());
        Assertions.assertNotEquals(originalVehicle.getModel(), response.getBody().getModel());
        Assertions.assertNotEquals(originalVehicle.getColor(), response.getBody().getColor());
        Assertions.assertNotEquals(originalVehicle.getPlate(), response.getBody().getPlate());
        Assertions.assertNotEquals(originalVehicle.getType(), response.getBody().getType());

    }

    @Test
    void delete_UseIdToDeleteSavedVehicle_WithoutReturn() {
        Long id = 1L;

        Mockito.doNothing().when(repository).deleteById(id);

        ResponseEntity<Void> response = controller.delete(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }
}
