package projects.ParkingWebApi.app.controller;

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
public class TicketController {

    private final IEstablishmentRepository establishmentRepository;

    private final IVehicleRepository vehicleRepository;

    private final ITicketRepository ticketRepository;

    public TicketController(IEstablishmentRepository establishmentRepository, IVehicleRepository vehicleRepository, ITicketRepository ticketRepository) {
        this.establishmentRepository = establishmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> checkin(TicketDTO dto) {
        Establishment establishment = establishmentRepository.findById(dto.establishmentId).get();
        Vehicle vehicle = vehicleRepository.findById(dto.vehicleId).get();

        Ticket newCheckin = ticketRepository.save(new Ticket(establishment.getId(), vehicle.getId()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create(String.format("/api/v1/ticket/%s", newCheckin.getId())))
                .body(new TicketResponse(newCheckin.getId(), establishment, vehicle, newCheckin.getCheckin(), newCheckin.getCheckout()));
    }

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
