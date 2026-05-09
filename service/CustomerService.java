package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
private static CustomerService one;
private final Map<String, Customer> book = new HashMap<>();

private CustomerService() {}

public static CustomerService getInstance() { if (one == null) { one = new CustomerService(); } return one; }

public void addCustomer(String mail, String fname, String lname) {
Customer c = new Customer(fname, lname, mail); book.put(mail, c);
}

public Customer getCustomer(String mail) { return book.get(mail); }
public Collection<Customer> getAllCustomers() { return book.values(); }
}
