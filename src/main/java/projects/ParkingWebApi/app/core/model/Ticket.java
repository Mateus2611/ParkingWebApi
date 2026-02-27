package projects.ParkingWebApi.app.core.model;

import jakarta.annotation.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "tb_ticket")
public class Ticket {

    public Ticket() {}

    public Ticket(Long establishment, Long vehicle) {
        this.establishmentId = establishment;
        this.vehicleId = vehicle;
        this.checkin = LocalDateTime.now();
    }

    @Id
    @Column("id")
    private Long id;
    @Column("establishment_id")
    private Long establishmentId;
    @Column("vehicle_id")
    private Long vehicleId;
    @Column("checkin")
    private LocalDateTime checkin;
    @Nullable
    @Column("checkout")
    private LocalDateTime checkout = null;

    public Long getId() {
        return id;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout() {
        this.checkout = LocalDateTime.now();
    }
}
