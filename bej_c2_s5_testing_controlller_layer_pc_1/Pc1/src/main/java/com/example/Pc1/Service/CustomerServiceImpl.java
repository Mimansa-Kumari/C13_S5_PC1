package com.example.Pc1.Service;

import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Exception.CustomerExistsException;
import com.example.Pc1.Exception.CustomerNotFoundException;
import com.example.Pc1.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{


    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;

    }

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerExistsException {
        if(customerRepository.findById(customer.getCustomerId()).isEmpty())// if empty it will save or else it will give exception
        {
            return customerRepository.insert(customer);
        }
        else{
            throw new CustomerExistsException();
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomers(int id) throws CustomerNotFoundException {
        if(customerRepository.findById(id).isEmpty()){
           throw new CustomerNotFoundException();
        }else
        {
            return true;
        }

    }

    @Override
    public List<Customer> getSamsungData() {
        return customerRepository.getSamsungProduct();
    }

//    @Override
//    public Customer updateCustomer(Customer customer, int customerId) {
//        Optional<Customer> checkMusic=customerRepository.findById(customerId);
//        if(checkMusic.isEmpty()){
//            return null;
//        }
//        Customer existingMusic=checkMusic.get(); //
//        if(customer.getCustomerName()!=null){
//            existingMusic.setCustomerId(customer.getCustomerId());
//        }
//        if(customer.getCustomerName()!=null){
//            existingMusic.setCustomerName(customer.getCustomerName());
//        }
//        if(customer.getCustomerProduct()!=null){
//            existingMusic.setCustomerProduct(customer.getCustomerProduct());
//        }
//        if(customer.getCustomerPhoneNumber()!=null){
//            existingMusic.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
//        }
//        return customerRepository.save(existingMusic);
//    }

//    @Override
//    public Customer getCustomerById(int customerId) {
//        return null;
//    }
//
//    @Override
//    public List<Customer> getCustomerByName(String customerName) {
//        return null;
//    }
}
