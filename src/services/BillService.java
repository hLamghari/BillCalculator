package services;

import model.Customer;
import model.Bill;

public interface BillService {
    void calculateBill(Customer customer, Bill bill);
}
