package projects.ParkingWebApi.app.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projects.ParkingWebApi.app.core.model.Ticket;

@Repository
public interface ITicketRepository extends CrudRepository<Ticket, Long> {
}
