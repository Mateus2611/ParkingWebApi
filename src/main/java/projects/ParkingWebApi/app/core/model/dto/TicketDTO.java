package projects.ParkingWebApi.app.core.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TicketDTO {

    public TicketDTO(Long establishmentId, Long vehicleId) {
        this.establishmentId = establishmentId;
        this.vehicleId = vehicleId;
    }

    @Schema(example = "42314", description = "Id of establishment")
    public Long establishmentId;

    @Schema(example = "67864", description = "Id of vehicle")
    public Long vehicleId;
}
