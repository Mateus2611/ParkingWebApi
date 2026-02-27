package projects.ParkingWebApi.app.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "tb_establishment")
public class Establishment{

    public Establishment() {}

    public Establishment(String name, String cnpj, String streetAddress, String neighborhood, String postalCode, String state, String city, String telephone, Integer parkingSpacesForMotorcycles, Integer parkingSpacesForCars) {
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

    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("cnpj")
    private String cnpj;
    @Column("street_address")
    private String streetAddress;
    @Column("neighborhood")
    private String neighborhood;
    @Column("postal_code")
    private String postalCode;
    @Column("state")
    private String state;
    @Column("city")
    private String city;
    @Column("telephone")
    private String telephone;
    @Column("psf_motorcycles")
    private Integer parkingSpacesForMotorcycles;
    @Column("psf_cars")
    private Integer parkingSpacesForCars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getParkingSpacesForMotorcycles() {
        return parkingSpacesForMotorcycles;
    }

    public void setParkingSpacesForMotorcycles(Integer parkingSpacesForMotorcycles) {
        this.parkingSpacesForMotorcycles = parkingSpacesForMotorcycles;
    }

    public Integer getParkingSpacesForCars() {
        return parkingSpacesForCars;
    }

    public void setParkingSpacesForCars(Integer parkingSpacesForCars) {
        this.parkingSpacesForCars = parkingSpacesForCars;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", cnpj:'" + cnpj + '\'' +
                ", streetAddress:'" + streetAddress + '\'' +
                ", neighborhood?'" + neighborhood + '\'' +
                ", postalCode:'" + postalCode + '\'' +
                ", state:'" + state + '\'' +
                ", city:'" + city + '\'' +
                ", telephone:'" + telephone + '\'' +
                ", parkingSpacesForMotorcycles:" + parkingSpacesForMotorcycles +
                ", parkingSpacesForCars:" + parkingSpacesForCars +
                '}';
    }
}
