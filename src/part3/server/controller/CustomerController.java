package part3.server.controller;

import java.util.ArrayList;
import java.util.List;

import part3.model.Customer;

public class CustomerController {

	private List<Customer> customerList;

    public CustomerController() {
        customerList = new ArrayList<>();
        createSampleCustomers();
    }

    // Method 1: Create a list of sample customer data
    private void createSampleCustomers() {
        customerList.add(new Customer(1, "Cheryl D. Perez"));
        customerList.add(new Customer(2, "Joyce R. Stephens"));
        customerList.add(new Customer(3, "Nancy P. Patterson"));
        customerList.add(new Customer(4, "Julie J. Verdugo"));
        customerList.add(new Customer(5, "Jeremy G. Cirillo"));
        customerList.add(new Customer(6, "Lissa W. Frances"));
        customerList.add(new Customer(7, "Clyde H. Ramos"));
        customerList.add(new Customer(8, "Sarah A. Faught"));
        customerList.add(new Customer(9, "Allan S. North"));
        customerList.add(new Customer(10, "Arnold J. Hollen"));
    }

    // Method 2: Search customer by name
    public Customer searchCustomerByName(String name) {
        for (Customer customer : customerList) {
            if (customer.getCustomerName().contains(name)) {
                return customer;
            }
        }
        return null;
    }

    // Method 3: Search customer by id
    public Customer searchCustomerById(int id) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    // Method 4: Return list of customers
    public List<Customer> getCustomerList() {
        return customerList;
    }
}
