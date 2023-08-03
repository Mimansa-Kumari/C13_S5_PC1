package com.example.Pc1.Service;

import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Exception.CustomerExistsException;
import com.example.Pc1.Exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {
    public Customer saveCustomer(Customer customer) throws CustomerExistsException;
    List<Customer> getCustomers();
    public boolean deleteCustomers(int id) throws CustomerNotFoundException;
    //public Customer updateCustomer(Customer customer, int customerId);
//    public Customer getCustomerById(int customerId);
    public List<Customer> getSamsungData();
}
