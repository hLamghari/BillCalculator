package userinterface;

import model.Customer;
import model.EnergyConsumption;

public interface Ihm {
    UserInput getConfigAndDataFromUser();

    EnergyConsumption getConsumptionFromUser();

    void verifyCustomer(Customer customer);
}
