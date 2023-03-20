package bill;

import model.EnergyConsumption;

import java.math.BigDecimal;

public class IndividualBilling implements BillingMethode {
    @Override
    public double calculateBillAmount(EnergyConsumption energyConsumption) {
        return new BigDecimal("0.121").multiply(new BigDecimal(energyConsumption.getElectricityConsumption()))
                .add(new BigDecimal("0.115").multiply(new BigDecimal(energyConsumption.getGazConsumption()))).doubleValue();
    }
}
