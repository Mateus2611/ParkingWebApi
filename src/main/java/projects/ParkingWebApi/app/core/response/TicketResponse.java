package projects.ParkingWebApi.app.core.response;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "876467", description = "Ticket ID")
    public Long id;

    @Schema(description = "Establishment registered on the ticket")
    public Establishment establishment;
    @Schema(description = "Vehicle parked in the establishment lot registered on the ticket. ")
    public Vehicle vehicle;

    @Schema(example = "2026-02-26 17:30:35", description = "Date and time of the checkin on the establishment")
    public LocalDateTime checkin;

    @Schema(example = "2026-02-26 20:10:43", description = "Date and timo of the checkout on the establishment")
    public LocalDateTime checkout = null;
}
