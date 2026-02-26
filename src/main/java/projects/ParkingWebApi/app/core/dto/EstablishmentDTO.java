package projects.ParkingWebApi.app.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "Green Parking ltd", description = "Comercial name")
    public String name;

    @Schema(example = "55380550000175", description = "Comercial CNPJ")
    public String cnpj;

    @Schema(example = "Rua Tiradentes", description = "Establishment Street Addres")
    public String streetAddress;

    @Schema(example = "Setor 09", description = "Establishment Neighborhood")
    public String neighborhood;

    @Schema(example = "76876216", description = "Establishment Postal Code")
    public String postalCode;

    @Schema(example = "RO", description = "Establishment State, use only acronym")
    public String state;

    @Schema(example = "Ariquemes", description = "Establishment city")
    public String city;

    @Schema(example = "2835264034", description = "Comercial telephone")
    public String telephone;

    @Schema(example = "140", description = "Set parking spaces for motorcycles of the establishment")
    public Integer parkingSpacesForMotorcycles;

    @Schema(example = "300", description = "Set parking spaces for cars of the establishment")
    public Integer parkingSpacesForCars;
}
