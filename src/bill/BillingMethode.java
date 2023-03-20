package bill;

import model.EnergyConsumption;

public interface BillingMethode {
    double calculateBillAmount(EnergyConsumption energyConsumption);
}
