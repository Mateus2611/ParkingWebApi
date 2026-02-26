package projects.ParkingWebApi.app.core.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import projects.ParkingWebApi.app.core.Establishment;

public class EstablishmentResponse {

    public EstablishmentResponse(Long id, String name, String telephone, Integer parkingSpacesForMotorcycles, Integer parkingSpacesForCars) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.parkingSpacesForMotorcycles = parkingSpacesForMotorcycles;
        this.parkingSpacesForCars = parkingSpacesForCars;
    }

    @Id
    @Schema(example = "432524", description = "Establishment ID")
    private Long id;

    @Column("name")
    @Schema(example = "Green Parking ltd", description = "Comercial name")
    private String name;

    @Column("telephone")
    @Schema(example = "2835264034", description = "Comercial telephone")
    private String telephone;

    @Column("psf_motorcycles")
    @Schema(example = "140", description = "Get parking spaces for motorcycles of the establishment")
    private Integer parkingSpacesForMotorcycles;

    @Column("psf_cars")
    @Schema(example = "300", description = "Get parking spaces for cars of the establishment")
    private Integer parkingSpacesForCars;
}
