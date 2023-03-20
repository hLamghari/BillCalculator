package factory;

import userinterface.UserInput;
import dao.CustomerDao;
import dao.FileDaoImpl;
import dao.ScannerDaoImpl;
import enums.DataModeEnum;
import enums.CustomerTypeEnum;
import model.Customer;
import model.IndividualCustomer;
import model.ProCustomer;

public abstract class CustomerFactory {

    private static CustomerDao customerDao;
    public static Customer getCustomer(UserInput userInput) {
        if(customerDao == null){
            customerDao = userInput.getCustomerSource().equals(DataModeEnum.MANUAL) ?  new ScannerDaoImpl(userInput) :  new FileDaoImpl();
        }
        Customer customerByReference = customerDao.getCustomerByReference(userInput.getCustomer().getReference());
        if(
                (userInput.getUserType().equals(CustomerTypeEnum.INDIVIDUAL) && customerByReference instanceof IndividualCustomer)
        || (userInput.getUserType().equals(CustomerTypeEnum.PRO) && customerByReference instanceof ProCustomer)){
            return customerByReference;
        }
        return null;
    }
}
