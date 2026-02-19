package projects.ParkingWebApi.app.infrastructure.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import projects.ParkingWebApi.app.core.Vehicle;

import java.util.List;

public interface IVehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAll(Pageable pageable);
}
