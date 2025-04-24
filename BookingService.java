package FlipkartLLD;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingService {
    private final InventoryService inventory;
    private final BookingStrategy strategy;
    private final List<Booking> bookings=new ArrayList<>();

    public BookingService(InventoryService inventory, BookingStrategy strategy){
        this.inventory=inventory;
        this.strategy=strategy;
    }

    public Booking rentVehicle(VehicleType type, LocalDateTime start, LocalDateTime end){
        for( Branch b: strategy.sortBranches(type, inventory.allBranches())){
            if(b.isAvailable(type, start, end)){
                Booking booking=new Booking(b, type, start, end);
                b.book(booking);
                bookings.add(booking);
                return booking;
            }
        }
        throw new IllegalArgumentException("No " + type+" available from "+start+" to "+end);
    }
    public List<Booking> getAllBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
