package projects.ParkingWebApi.app.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projects.ParkingWebApi.app.core.Establishment;

@Repository
public interface IEstablishmentRepository extends CrudRepository<Establishment, Long> {}
