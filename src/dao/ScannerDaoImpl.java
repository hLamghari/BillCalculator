package dao;

import userinterface.UserInput;
import model.Customer;

public class ScannerDaoImpl implements CustomerDao {

    private final UserInput userInput;

    public ScannerDaoImpl(UserInput userInput) {
        this.userInput = userInput;
    }

    @Override
    public Customer getCustomerByReference(String Reference) {
        return userInput.getCustomer().getReference().equals(Reference) ? userInput.getCustomer() : null;
    }
}
