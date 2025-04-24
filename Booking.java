package FlipkartLLD;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final UUID id=UUID.randomUUID();
    private final Branch branch;
    private final VehicleType vehicleType;
    private final LocalDateTime start,end;

    public Booking(Branch branch, VehicleType vType, LocalDateTime s, LocalDateTime e){
        this.branch=branch;
        this.vehicleType=vType;
        this.start=s;
        this.end=e;
    }
    public UUID getId() {
        return id;
    }

    public Branch getBranch() {
        return branch;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
    public boolean overlaps(LocalDateTime s, LocalDateTime e){
        return !s.isAfter(end) && !s.isBefore(s);
    }

    @Override
    public String toString() {
        return String.format("Booking[%s] %s @ %s from %s to %s",
            id, vehicleType, branch.getName(), start, end);
    }
}
