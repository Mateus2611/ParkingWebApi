package projects.ParkingWebApi.app.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.relational.core.mapping.Column;
import projects.ParkingWebApi.app.core.VehicleType;

public class VehicleDTO {

    public VehicleDTO(String brand, String model, String color, String plate, VehicleType type) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.plate = plate;
        this.type = type;
    }

    @Schema(example = "Toyota", description = "Brand of vehicle")
    public String brand;

    @Schema(example = "Corolla", description = "Vehicle model")
    public String model;

    @Schema(example = "Black", description = "Vehicle color")
    public String color;

    @Schema(example = "JVC8403", description = "Vehicle plate in Brazilian model")
    public String plate;

    @Schema(example = "CAR", description = "Vehicle type its a enum value, use CAR or MOTORCYCLE")
    public VehicleType type;
}
