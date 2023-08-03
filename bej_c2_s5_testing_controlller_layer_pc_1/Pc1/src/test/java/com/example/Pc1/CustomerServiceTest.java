package com.example.Pc1;

import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Domain.Product;
import com.example.Pc1.Exception.CustomerExistsException;
import com.example.Pc1.Exception.CustomerNotFoundException;
import com.example.Pc1.Repository.CustomerRepository;
import com.example.Pc1.Service.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock     //
    private CustomerRepository iCustomerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImp;

    private Customer customer;
    private Product product;

    private Customer customer1;
    private Product product1;

    @BeforeEach
    public void setUp(){
        product= new Product(1,"Samsung", "Samsung Mobile Phones");
        customer=new Customer(100, "Abc", "989196792", product);
        product1= new Product(2,"Nokia", "Nokia Mobile Phones");
        customer1=new Customer(101, "Abc", "989196792", product1);
    }

    @AfterEach
    public void tearDown(){
        product=null;
        customer=null;
        product1=null;
        customer1=null;
    }

    @Test
    public void testAddCustomerSuccess() throws CustomerExistsException {
        when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(null)); // In This line we are checking if the id exists if exists its ok or else send null to line number 56
        when(iCustomerRepository.insert(customer)).thenReturn(customer);
        Customer insertedCustomer=customerServiceImp.saveCustomer(customer);
        assertEquals(insertedCustomer, customer);
    }
    @Test
    public void testAddCustomerFail() throws CustomerExistsException {
        when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(customer));
        assertThrows(CustomerExistsException.class, ()->customerServiceImp.saveCustomer(customer));
    }
    @Test
    public void testDeleteCustomerSuccess() throws CustomerNotFoundException {
        when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(customer));
        boolean result=customerServiceImp.deleteCustomers(customer.getCustomerId());
        assertEquals(true, result);
    }
    @Test
    public void testDeleteCustomerFail() throws CustomerNotFoundException {
        when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomerNotFoundException.class, ()->customerServiceImp.deleteCustomers(customer.getCustomerId()));
    }
}
