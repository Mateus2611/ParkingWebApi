package projects.ParkingWebApi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/establishment")
public class EstablishmentController {

    private final IEstablishmentRepository repository;

    public EstablishmentController(IEstablishmentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Establishment> create(@RequestBody EstablishmentDTO establishmentDTO) {

        Establishment created = repository.save(new Establishment(
                establishmentDTO.name,
                establishmentDTO.cnpj,
                establishmentDTO.streetAddress,
                establishmentDTO.neighborhood,
                establishmentDTO.postalCode,
                establishmentDTO.state,
                establishmentDTO.city,
                establishmentDTO.telephone,
                establishmentDTO.parkingSpacesForMotorcycles,
                establishmentDTO.parkingSpacesForCars));

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create(String.format("/api/v1/establishment/%s", created.getId())))
                .body(created);
    }
}
