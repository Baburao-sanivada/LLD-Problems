package ParkingLotSystem.Services;

import ParkingLotSystem.Models.Reciept;
import ParkingLotSystem.Repository.RecieptRepository;

import java.time.LocalDateTime;

public class RecieptService {

    private RecieptRepository recieptRepository;

    public RecieptService(RecieptRepository recieptRepository) {
        this.recieptRepository = recieptRepository;
    }

    public Reciept getRecieptById(String recieptId) {
        return recieptRepository.findById(recieptId);
    }

    public Reciept generateReciept(String ticketId, double amount, LocalDateTime time) {
        Reciept reciept = new Reciept(ticketId, amount, time);
        recieptRepository.save(reciept);
        return reciept;
    }
}
