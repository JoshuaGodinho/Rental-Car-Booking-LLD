package FlipkartLLD;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Branch{
    private final String name;
    private final Map<VehicleType, Integer> capacity=new HashMap<>();
    private final Map<VehicleType, Integer> pricePerHour=new HashMap<>();
    private final List<Booking> bookings=new ArrayList<>();

    public Branch(String name, Map<VehicleType, Integer> initialCaps, Map<VehicleType, Integer> initialPrices) {
        this.name=name;
        this.capacity.putAll(initialCaps);
        this.pricePerHour.putAll(initialPrices);
    }

    public String getName() {
        return name;
    }

    public int getPrice(VehicleType type) {
        return pricePerHour.getOrDefault(type, Integer.MAX_VALUE);
    }

    public int getTotalCapacity(VehicleType type) {
        return capacity.getOrDefault(type, 0);
    }

    public void addVehicle(VehicleType type, int count){
        capacity.merge(type,count,Integer::sum);
    }

    public synchronized boolean isAvailable(VehicleType type, LocalDateTime start, LocalDateTime end){
        int booked=(int) bookings.stream()
                .filter(b->b.getVehicleType()==type && b.overlaps(start,end))
                .count();
            return booked<capacity.getOrDefault(type,0);
    }
    public synchronized void book(Booking b) {
        bookings.add(b);
    }

    public int getAvailable(VehicleType type, LocalDateTime start, LocalDateTime end){
        int booked=(int) bookings.stream()
            .filter(b->b.getVehicleType()==type && b.overlaps(start,end))
            .count();
        return capacity.getOrDefault(type,0)-booked;
    }
}