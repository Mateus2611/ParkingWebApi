package projects.ParkingWebApi.app.core.dto;

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

    public String brand;
    public String model;
    public String color;
    public String plate;
    public VehicleType type;
}
