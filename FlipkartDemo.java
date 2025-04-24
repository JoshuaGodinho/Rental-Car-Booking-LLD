package FlipkartLLD;

import java.time.LocalDateTime;
import java.util.Map;

public class FlipkartDemo {
    public static void main(String[] args){
        InventoryService inv=new InventoryService();

        inv.addBranch("California",
            Map.of(VehicleType.SUV,1,
                VehicleType.SEDAN, 3,
                VehicleType.BIKE, 3),
            Map.of(VehicleType.SUV, 12,
                VehicleType.SEDAN, 10,
                VehicleType.BIKE, 20));

        inv.addBranch("Austin",
            Map.of(VehicleType.SEDAN, 3,
                VehicleType.BIKE, 3,
                VehicleType.HATCHBACK, 4),
            Map.of(VehicleType.SEDAN, 11,
                VehicleType.BIKE, 30,
                VehicleType.HATCHBACK, 8));

        inv.addBranch("New York",
            Map.of(VehicleType.SUV, 1,
                VehicleType.BIKE, 10,
                VehicleType.SEDAN, 3),
            Map.of(VehicleType.SUV, 11,
                VehicleType.BIKE, 3,
                VehicleType.SEDAN, 10));
            
        inv.addBranch("Atlanta",
            Map.of(VehicleType.SUV,       3,
                       VehicleType.SEDAN,     2,
                       VehicleType.BIKE,      2),
            Map.of(VehicleType.SUV,       14,
                       VehicleType.SEDAN,     12,
                       VehicleType.BIKE,      18));
    
        inv.addBranch("Los Angeles",
            Map.of(VehicleType.BIKE,  10,
                       VehicleType.HATCHBACK, 2),
            Map.of(VehicleType.BIKE,      17,
                       VehicleType.HATCHBACK, 9));

        inv.addVehicle("California", VehicleType.SEDAN, 1);

        BookingService   bs     = new BookingService(inv, new PriceBasedStrategy());
        ReportingService report = new ReportingService(inv, bs);

        LocalDateTime day = LocalDateTime.of(2025, 3, 1, 0, 0);

        LocalDateTime t1Start = day.withHour( 9);  // 09:00-11:00
        LocalDateTime t1End   = day.withHour(11);

        LocalDateTime t2Start = day.withHour(10);  // 10:00-12:00  (overlaps t1)
        LocalDateTime t2End   = day.withHour(12);

        LocalDateTime t3Start = day.withHour(13);  // 13:00-15:00  (non-overlap)
        LocalDateTime t3End   = day.withHour(15);

        // (A) Two cheapest-SUV bookings → New York (Rs.11) then California (Rs.12)
        System.out.println(bs.rentVehicle(VehicleType.SUV, t1Start, t1End).getBranch().getName());
        System.out.println(bs.rentVehicle(VehicleType.SUV, t1Start, t1End).getBranch().getName());

        // (B) Third SUV in same slot → Atlanta (Rs.14) because Cali has only 1 SUV
        System.out.println(bs.rentVehicle(VehicleType.SUV, t1Start, t1End).getBranch().getName());

         // (C) Book overlapping slot for same SUV again → still works if capacity ≥ bookings
         System.out.println(bs.rentVehicle(VehicleType.SUV, t2Start, t2End).getBranch().getName());

          // (D) Book a hatchback (only Austin & LA have them → Austin cheaper)
        System.out.println(bs.rentVehicle(VehicleType.HATCHBACK, t1Start, t1End).getBranch().getName());

        // (E) Non-overlapping slot later in day → cheapest branch reset 
        System.out.println(bs.rentVehicle(VehicleType.SUV, t3Start, t3End).getBranch().getName());

        report.showSystemView(t1Start);
    }
}
