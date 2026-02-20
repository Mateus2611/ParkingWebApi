package projects.ParkingWebApi.app.core.dto;

public class TicketDTO {

    public TicketDTO(Long establishmentId, Long vehicleId) {
        this.establishmentId = establishmentId;
        this.vehicleId = vehicleId;
    }

    public Long establishmentId;
    public Long vehicleId;
}
