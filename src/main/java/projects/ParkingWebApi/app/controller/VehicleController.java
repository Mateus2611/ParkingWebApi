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
import projects.ParkingWebApi.app.core.Vehicle;
import projects.ParkingWebApi.app.core.dto.VehicleDTO;
import projects.ParkingWebApi.app.infrastructure.repository.IVehicleRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicle")
@Tag(name = "Vehicle", description = "Endpoints for managing vehicles registration")
public class VehicleController {

    private final IVehicleRepository repository;

    public VehicleController(IVehicleRepository repository) {
        this.repository = repository;
    }

    @Operation(
            summary = "Created new vehicle",
            description = "Register new vehicles in the system"
    )
    @ApiResponse(responseCode = "201", description = "Vehicle created")
    @PostMapping
    public ResponseEntity<Vehicle> create(VehicleDTO dto) {

        Vehicle created = repository.save(new Vehicle(dto.brand, dto.model, dto.color, dto.plate, dto.type));

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create(String.format("/api/v1/vehicle/%s", created.getId())))
                .body(created);
    }

    @Operation(
            summary = "Get saved vehicles",
            description = "Get all vehicles saved on system"
    )
    @ApiResponse(responseCode = "200", description = "Found Vehicles")
    @GetMapping
    public ResponseEntity<List<Vehicle>> get(
            @Parameter(description = "Zero-based page index (0..N)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "The size of the page to be returned")
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findAll(pageable));
    }

    @Operation(
            summary = "Get a vehicle saved on the system",
            description = "Find a vehicle filtered by ID"
    )
    @ApiResponse(responseCode = "200", description = "Found vehicle")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> getById(@PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findById(id));
    }

    @Operation(
            summary = "Update information's of a vehicle saved on the system",
            description = "Get a vehicle and update your information's"
    )
    @ApiResponse(responseCode = "200", description = "Vehicle updated")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody VehicleDTO dataToUpdate) {

        Optional<Vehicle> vehicle = repository.findById(id);

        vehicle.get().setBrand(dataToUpdate.brand);
        vehicle.get().setModel(dataToUpdate.model);
        vehicle.get().setColor(dataToUpdate.color);
        vehicle.get().setPlate(dataToUpdate.plate);
        vehicle.get().setType(dataToUpdate.type);

        Vehicle updated = repository.save(vehicle.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(updated);
    }

    @Operation(
            summary = "Delete vehicle saved on the system",
            description = "Delete the vehicle using ID"
    )
    @ApiResponse(responseCode = "204", description = "Vehicle deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
