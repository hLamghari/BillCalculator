package model;

import bill.BillingMethode;

public class Bill {

    public Bill(EnergyConsumption energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    private final EnergyConsumption energyConsumption;

    public double calculateBill(BillingMethode billingMethode) {
        return billingMethode.calculateBillAmount(energyConsumption);
    }

}
