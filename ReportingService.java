package FlipkartLLD;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportingService {
    private final InventoryService inventory;
    private final BookingService bookingService;

    public ReportingService(InventoryService inventory, BookingService bookingService){
        this.inventory=inventory;
        this.bookingService=bookingService;
    }

    public void showSystemView(LocalDateTime atTime){
        System.out.println("=== System view "+atTime+" ===");
        for(Branch b:inventory.allBranches()){
            System.out.println("Branch: "+b.getName());
            for(VehicleType vt:VehicleType.values()){
                int total=b.getTotalCapacity(vt);
                int available=b.getAvailable(vt, atTime, atTime.plusHours(1));
                System.out.printf(" %s → total: %d, available: %d%n", vt,total,available);
            }
        }
    }
}
