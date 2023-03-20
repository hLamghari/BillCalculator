package bill;

import model.EnergyConsumption;
import model.ProCustomer;

import java.math.BigDecimal;

public class ProBilling implements BillingMethode {
    public static final String ELECTRICITY_PRICE_FOR_HIGH_REVENUE = "0.114";
    public static final String GAS_PRICE_FOR_HIGH_REVENUE = "0.111";
    public static final String ELECTRICITY_PRICE_FOR_LOW_REVENUE = "0.118";
    public static final String GAS_PRICE_FOR_LOW_REVENUE = "0.113";
    private final ProCustomer proCustomer;

    public ProBilling(ProCustomer proCustomer) {
        this.proCustomer = proCustomer;
    }

    @Override
    public double calculateBillAmount(EnergyConsumption energyConsumption) {
        return Long.parseLong(proCustomer.getRevenue()) > 1000000 ?
                new BigDecimal(ELECTRICITY_PRICE_FOR_HIGH_REVENUE).multiply(new BigDecimal(energyConsumption.getElectricityConsumption()))
                        .add(new BigDecimal(GAS_PRICE_FOR_HIGH_REVENUE).multiply(new BigDecimal(energyConsumption.getGazConsumption()))).doubleValue()
                : new BigDecimal(ELECTRICITY_PRICE_FOR_LOW_REVENUE).multiply(new BigDecimal(energyConsumption.getElectricityConsumption()))
                .add(new BigDecimal(GAS_PRICE_FOR_LOW_REVENUE).multiply(new BigDecimal(energyConsumption.getGazConsumption()))).doubleValue();
    }
}
