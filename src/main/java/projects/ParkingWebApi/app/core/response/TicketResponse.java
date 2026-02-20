package projects.ParkingWebApi.app.core.response;

import jakarta.annotation.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import projects.ParkingWebApi.app.core.Establishment;
import projects.ParkingWebApi.app.core.Vehicle;

import java.time.LocalDateTime;

public class TicketResponse {

    public TicketResponse(Long id, Establishment establishment, Vehicle vehicle, LocalDateTime checkin, LocalDateTime checkout) {
        this.id = id;
        this.establishment = establishment;
        this.vehicle = vehicle;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Long id;
    public Establishment establishment;
    public Vehicle vehicle;
    public LocalDateTime checkin;
    public LocalDateTime checkout = null;
}
