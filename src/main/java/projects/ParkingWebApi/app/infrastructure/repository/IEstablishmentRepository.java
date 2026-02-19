package projects.ParkingWebApi.app.infrastructure.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.response.EstablishmentResponse;

import java.util.List;

@Repository
public interface IEstablishmentRepository extends CrudRepository<Establishment, Long> {

    List<EstablishmentResponse> findAll(Pageable pageable);
}
