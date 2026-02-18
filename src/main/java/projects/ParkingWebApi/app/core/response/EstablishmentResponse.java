package projects.ParkingWebApi.app.core.response;

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
    private Long id;
    @Column("name")
    private String name;
    @Column("telephone")
    private String telephone;
    @Column("psf_motorcycles")
    private Integer parkingSpacesForMotorcycles;
    @Column("psf_cars")
    private Integer parkingSpacesForCars;
}
