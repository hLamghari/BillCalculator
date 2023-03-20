import userinterface.UserInput;
import factory.CustomerFactory;
import model.Customer;
import model.EnergyConsumption;
import model.Bill;
import services.BillService;
import services.ManuelBillService;
import userinterface.ConsoleIHM;
import userinterface.Ihm;

public class Main {
    public static void main(String[] args) {

        Ihm ihm = new ConsoleIHM();
        UserInput userInput = ihm.getConfigAndDataFromUser();
        Customer customer = CustomerFactory.getCustomer(userInput);
        ihm.verifyCustomer(customer);
        BillService billService = new ManuelBillService();
        EnergyConsumption energyConsumption = ihm.getConsumptionFromUser();
        billService.calculateBill(customer, new Bill(energyConsumption));
    }
}