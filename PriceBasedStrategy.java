package FlipkartLLD;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceBasedStrategy implements BookingStrategy{
    @Override
    public List<Branch> sortBranches(VehicleType type, Collection<Branch> all){
        return all.stream()
            .sorted(Comparator.comparingInt(b->b.getPrice(type)))
            .collect(Collectors.toList());
    }
}
