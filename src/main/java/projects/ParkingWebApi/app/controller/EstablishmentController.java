package projects.ParkingWebApi.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.ParkingWebApi.app.core.model.Establishment;
import projects.ParkingWebApi.app.core.model.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.core.model.response.EstablishmentResponse;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/establishment")
@Tag(name = "Establishment", description = "Endpoints for managing parking locations")
public class EstablishmentController {

    private final IEstablishmentRepository repository;

    public EstablishmentController(IEstablishmentRepository repository) {
        this.repository = repository;
    }

    @Operation(
         summary = "Create new establishment",
         description = "Registers a new parking establishment in the system."
    )
    @ApiResponse(responseCode = "201", description = "Establishment created")
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

    @Operation(
            summary = "Get saved establishments",
            description = "Get all establishments saved on the system"
    )
    @ApiResponse(responseCode = "200", description = "Found Establishments")
    @GetMapping
    public ResponseEntity<List<EstablishmentResponse>> get(
            @Parameter(description = "Zero-based page index (0..N)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "The size of the page to be returned")
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findAll(pageable));
    }

    @Operation(
            summary = "Get details of a establishment saved on the system",
            description = "Find a establishment filtered by ID"
    )
    @ApiResponse(responseCode = "200", description = "Found establishment")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Establishment>> getById(@PathVariable long id) {

        Optional<Establishment> establishment = repository.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(establishment);
    }

    @Operation(
            summary = "Update information's of a establishment saved on the system",
            description = "Get a establishment and update your information's"
    )
    @ApiResponse(responseCode = "200", description = "Establishment updated")
    @PutMapping("/{id}")
    public ResponseEntity<Establishment> update(@PathVariable long id, @RequestBody EstablishmentDTO establishmentDTO) {

        Optional<Establishment> establishment = repository.findById(id);

        establishment.get().setName(establishmentDTO.name);
        establishment.get().setCnpj(establishmentDTO.cnpj);
        establishment.get().setStreetAddress(establishmentDTO.streetAddress);
        establishment.get().setNeighborhood(establishmentDTO.neighborhood);
        establishment.get().setPostalCode(establishmentDTO.postalCode);
        establishment.get().setState(establishmentDTO.state);
        establishment.get().setCity(establishmentDTO.city);
        establishment.get().setTelephone(establishmentDTO.telephone);
        establishment.get().setParkingSpacesForMotorcycles(establishmentDTO.parkingSpacesForMotorcycles);
        establishment.get().setParkingSpacesForCars(establishmentDTO.parkingSpacesForCars);

         Establishment updated = repository.save(establishment.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(updated);
    }

    @Operation(
            summary = "Delete a establishment saved on the system",
            description = "Delete the establishment using ID"
    )
    @ApiResponse(responseCode = "204", description = "Establishment deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
