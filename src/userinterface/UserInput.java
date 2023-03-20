package userinterface;

import enums.DataModeEnum;
import enums.CustomerTypeEnum;
import model.Customer;
import model.EnergyConsumption;

public class UserInput {
    private DataModeEnum customerSource;
    private CustomerTypeEnum userType;

    private Customer customer;

    private EnergyConsumption energyConsumption;

    public CustomerTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(CustomerTypeEnum userType) {
        this.userType = userType;
    }

    public DataModeEnum getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(DataModeEnum customerSource) {
        this.customerSource = customerSource;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EnergyConsumption getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(EnergyConsumption energyConsumption) {
        this.energyConsumption = energyConsumption;
    }
}
