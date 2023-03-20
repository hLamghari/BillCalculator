package dao;

import model.Customer;

public interface CustomerDao {
    Customer getCustomerByReference(String Reference);
}
