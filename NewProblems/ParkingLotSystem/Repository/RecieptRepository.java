package ParkingLotSystem.Repository;

import ParkingLotSystem.Models.Reciept;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RecieptRepository {
    private Map<String, Reciept> reciepts = new ConcurrentHashMap<>();

    public void save(Reciept reciept) {
        reciepts.put(reciept.getRecieptId(), reciept);
    }

    public Reciept findById(String recieptId) {
        return reciepts.get(recieptId);
    }

    public void deleteById(String recieptId) {
        reciepts.remove(recieptId);
    }

    public void clearAll() {
        reciepts.clear();
    }
}
