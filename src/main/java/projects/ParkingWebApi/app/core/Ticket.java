package projects.ParkingWebApi.app.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_ticket")
public class Ticket {

    public Ticket() {}

    public Ticket(Establishment establishment, Vehicle vehicle, LocalDateTime checkin, LocalDateTime checkout) {
        this.establishment = establishment;
        this.vehicle = vehicle;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Id
    private Long id;
    @MappedCollection(idColumn = "establishment_id")
    private Establishment establishment;
    @MappedCollection(idColumn = "vehicle_id")
    private Vehicle vehicle;
    @Column("checkin")
    private LocalDateTime checkin;
    @Column("checkout")
    private LocalDateTime checkout;

    public Long getId() {
        return id;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }
}
