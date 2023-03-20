package dao;

import enums.CivilityEnum;
import model.Customer;
import model.IndividualCustomer;
import model.ProCustomer;

import java.util.Arrays;

public class FileDaoImpl implements CustomerDao {

    private final Customer[] customers = new Customer[]{new IndividualCustomer("EKW00000001", CivilityEnum.MADAME, "Lam", "Hat")
            , new ProCustomer("EKW00000002", "11112222333344", "LAMTECH", "100")};

    @Override
    public Customer getCustomerByReference(String reference) {
        // reading customers from some binary file or database, here we choose a simple array
        return Arrays.stream(customers).filter(customer -> customer.getReference().equals(reference)).findFirst().orElse(null);
    }
}
