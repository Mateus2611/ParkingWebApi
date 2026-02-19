package projects.ParkingWebApi.app.controller;

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
public class VehicleController {

    private final IVehicleRepository repository;

    public VehicleController(IVehicleRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity<Vehicle> create(VehicleDTO dto) {

        Vehicle created = repository.save(new Vehicle(dto.brand, dto.model, dto.color, dto.plate, dto.type));

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create(String.format("/api/v1/vehicle/%s", created.getId())))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> getById(@PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findById(id));
    }
}
