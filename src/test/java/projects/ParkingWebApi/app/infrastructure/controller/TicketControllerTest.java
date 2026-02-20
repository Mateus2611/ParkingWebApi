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
import org.springframework.util.Assert;
import projects.ParkingWebApi.app.controller.TicketController;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.Ticket;
import projects.ParkingWebApi.app.core.Vehicle;
import projects.ParkingWebApi.app.core.VehicleType;
import projects.ParkingWebApi.app.core.dto.TicketDTO;
import projects.ParkingWebApi.app.core.response.TicketResponse;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;
import projects.ParkingWebApi.app.infrastructure.repository.ITicketRepository;
import projects.ParkingWebApi.app.infrastructure.repository.IVehicleRepository;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @Mock
    private IEstablishmentRepository establishmentRepository;

    @Mock
    private IVehicleRepository vehicleRepository;

    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private TicketController controller;

    @Test
    void checkin_CreateCheckin_ReturnTicketResponse() {
        Establishment savedEstablishment = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        savedEstablishment.setId(1L);
        Vehicle savedVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        savedVehicle.setId(2L);
        Ticket newCheckin = new Ticket(savedEstablishment.getId(), savedVehicle.getId());
        TicketDTO dto = new TicketDTO(savedEstablishment.getId(), savedVehicle.getId());

        Mockito.when(establishmentRepository.findById(anyLong())).thenReturn(Optional.of(savedEstablishment));
        Mockito.when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(savedVehicle));
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(newCheckin);

        ResponseEntity<TicketResponse> response = controller.checkin(dto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(savedEstablishment, Objects.requireNonNull(response.getBody().establishment));
        Assertions.assertEquals(savedVehicle, Objects.requireNonNull(response.getBody().vehicle));
        Assertions.assertNotNull(response.getBody().checkin);
        Assertions.assertNull(response.getBody().checkout);
    }

    @Test
    void checkout_MakeCheckoutUsingId_ReturnTicketResponse() {
        Long id = 5L;
        Ticket ticket = new Ticket(1L, 2L);
        ticket.setCheckout();
        Establishment savedEstablishment = new Establishment("FreeParking", "64110362000118", "Avenida Dom João VI", "Cinquentenário", "30570066", "MG", "Belo Horizonte", "6424692539", 100, 180);
        savedEstablishment.setId(1L);
        Vehicle savedVehicle = new Vehicle("BMW", "320i", "Red", "JVC8403", VehicleType.CAR);
        savedVehicle.setId(2L);

        Mockito.when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));
        Mockito.when(establishmentRepository.findById(anyLong())).thenReturn(Optional.of(savedEstablishment));
        Mockito.when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(savedVehicle));
        Mockito.when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<TicketResponse> response = controller.checkout(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(ticket.getCheckout());
    }

}
