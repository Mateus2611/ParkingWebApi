package projects.ParkingWebApi.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.Ticket;
import projects.ParkingWebApi.app.core.Vehicle;
import projects.ParkingWebApi.app.core.dto.TicketDTO;
import projects.ParkingWebApi.app.core.response.TicketResponse;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;
import projects.ParkingWebApi.app.infrastructure.repository.ITicketRepository;
import projects.ParkingWebApi.app.infrastructure.repository.IVehicleRepository;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/ticket")
@Tag(name = "Ticket", description = "Endpoints to maneging tickets")
public class TicketController {

    private final IEstablishmentRepository establishmentRepository;

    private final IVehicleRepository vehicleRepository;

    private final ITicketRepository ticketRepository;

    public TicketController(IEstablishmentRepository establishmentRepository, IVehicleRepository vehicleRepository, ITicketRepository ticketRepository) {
        this.establishmentRepository = establishmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    @Operation(
            summary = "Make the vehicle checking in the parking location",
            description = "This endpoint its responsible to create the ticket and relationship of the vehicle with establishment."
    )
    @ApiResponse(responseCode = "201", description = "Checking created")
    @PostMapping
    public ResponseEntity<TicketResponse> checkin(TicketDTO dto) {
        Establishment establishment = establishmentRepository.findById(dto.establishmentId).get();
        Vehicle vehicle = vehicleRepository.findById(dto.vehicleId).get();

        Ticket newCheckin = ticketRepository.save(new Ticket(establishment.getId(), vehicle.getId()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create(String.format("/api/v1/ticket/%s", newCheckin.getId())))
                .body(new TicketResponse(newCheckin.getId(), establishment, vehicle, newCheckin.getCheckin(), newCheckin.getCheckout()));
    }

    @Operation(
            summary = "Make the vehicle checkout in the parking location",
            description = "This endpoint its responsible to set the checkout field in the ticket"
    )
    @ApiResponse(responseCode = "200", description = "Checkout")
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> checkout(@PathVariable Long id) {
        Ticket saved = ticketRepository.findById(id).get();
        saved.setCheckout();
        Establishment establishment = establishmentRepository.findById(saved.getEstablishmentId()).get();
        Vehicle vehicle = vehicleRepository.findById(saved.getVehicleId()).get();
        saved = ticketRepository.save(saved);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new TicketResponse(saved.getId(), establishment, vehicle, saved.getCheckin(), saved.getCheckout()));
    }
}
