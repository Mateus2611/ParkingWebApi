package projects.ParkingWebApi.app.core.dto;

import org.springframework.data.relational.core.mapping.Column;

public class EstablishmentDTO {

    public EstablishmentDTO(String name, String cnpj, String streetAddress, String neighborhood, String postalCode, String state, String city, String telephone, Integer parkingSpacesForMotorcycles, Integer parkingSpacesForCars) {
        this.name = name;
        this.cnpj = cnpj;
        this.streetAddress = streetAddress;
        this.neighborhood = neighborhood;
        this.postalCode = postalCode;
        this.state = state;
        this.city = city;
        this.telephone = telephone;
        this.parkingSpacesForMotorcycles = parkingSpacesForMotorcycles;
        this.parkingSpacesForCars = parkingSpacesForCars;
    }

    public String name;
    public String cnpj;
    public String streetAddress;
    public String neighborhood;
    public String postalCode;
    public String state;
    public String city;
    public String telephone;
    public Integer parkingSpacesForMotorcycles;
    public Integer parkingSpacesForCars;
}
