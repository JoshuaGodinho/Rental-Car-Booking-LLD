package FlipkartLLD;

import java.util.Collection;
import java.util.List;

public interface BookingStrategy {
    List<Branch> sortBranches(VehicleType type, Collection<Branch> allBranches);
}
