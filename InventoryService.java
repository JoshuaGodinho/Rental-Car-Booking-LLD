package FlipkartLLD;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Branch> branches=new HashMap<>();

    public void addBranch(String name, Map<VehicleType, Integer> caps, Map<VehicleType,Integer> prices){
        if(branches.containsKey(name)){
            throw new IllegalArgumentException("Branch name already exists:"+name);
        }
        branches.put(name, new Branch(name, caps, prices));
    }

    public void addVehicle(String branchName, VehicleType vehicleType, int count){
        Branch b=branches.get(branchName);
        if(b==null){
            throw new IllegalArgumentException("Branch name doesnt exists:"+branchName);
        }
        b.addVehicle(vehicleType, count);
    }

    public Collection<Branch> allBranches() {
        return branches.values();
    }
    
    public Branch getBranch(String name) {
        return branches.get(name);
    }
}
