package services;

import bill.IndividualBilling;
import bill.ProBilling;
import model.Customer;
import model.Bill;
import model.IndividualCustomer;
import model.ProCustomer;

import java.text.MessageFormat;

public class ManuelBillService implements BillService {
    @Override
    public void calculateBill(Customer customer, Bill bill) {
        double billAmount = 0;
        if (customer instanceof IndividualCustomer) {
            billAmount = bill.calculateBill(new IndividualBilling());
        } else if (customer instanceof ProCustomer proCustomer) {
            billAmount = bill.calculateBill(new ProBilling(proCustomer));
        }
        System.out.println(MessageFormat.format("Le montant de la facture est {0}", billAmount));
    }
}
