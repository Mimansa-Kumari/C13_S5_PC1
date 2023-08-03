package com.example.Pc1;

import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Domain.Product;
import com.example.Pc1.Repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@ExtendWith(SpringExtension.class)  // SpringBoot Starter Test combines the junit test with the spring to work with the test cases.
@DataMongoTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository iCustomerRepository;
    private Customer customer;
    private Product product;
    private Customer customer1;
    private Product product1;


    @BeforeEach
    public void setUp(){
        product= new Product(1,"Samsung", "Samsung Mobile Phones");
        customer=new Customer(100, "Abc", "989196792", product);
        product1= new Product(1,"Nokia", "Samsung Mobile Phones");
        customer1=new Customer(101, "Abc", "989196792", product1);
    }
    @AfterEach
    public void tearDown(){
        product=null;
        customer=null;
        product1=null;
        customer1=null;
        iCustomerRepository.deleteAll();
    }
    @Test
    public void testFindAllSuccess(){
        iCustomerRepository.insert(customer);
        customer.setCustomerId(101);
        iCustomerRepository.insert(customer);
        customer.setCustomerId(102);
        iCustomerRepository.insert(customer);

        List<Customer> customerList = iCustomerRepository.findAll();
        assertEquals("Matching", 3, customerList.size());
    }
    @Test
    public void testFindAllFail(){
        iCustomerRepository.insert(customer);
        customer.setCustomerId(101);
        iCustomerRepository.insert(customer);
        customer.setCustomerId(102);
        iCustomerRepository.insert(customer);

        List<Customer> customerList = iCustomerRepository.findAll();
        assertNotEquals("Not-Matching", 4, customerList.size());
    }
    @Test
    public void testDeleteCustomerSuccess(){
        iCustomerRepository.insert(customer);
        iCustomerRepository.deleteById(customer.getCustomerId());
        List<Customer> customerList = iCustomerRepository.findAll();
        assertEquals("Matching", 0, customerList.size());
    }
    @Test
    public void testDeleteCustomerFail(){
        iCustomerRepository.insert(customer);
        iCustomerRepository.deleteById(customer.getCustomerId());
        List<Customer> customerList = iCustomerRepository.findAll();
        assertNotEquals("Matching", 1, customerList.size());
    }
    @Test
    public void insertCustomerSuccess(){
        iCustomerRepository.insert(customer);
        List<Customer> customerList = iCustomerRepository.findAll();
        assertEquals("Matching", 1, customerList.size());
    }
    @Test
    public void insertCustomerFail(){
        iCustomerRepository.insert(customer);
        List<Customer> customerList = iCustomerRepository.findAll();
        assertNotEquals("Matching", 2, customerList.size());
    }
    @Test
    public void testGetSamsungProduct() {

        iCustomerRepository.insert(customer);
        iCustomerRepository.insert(customer1);
        List<Customer> samsungCustomers = iCustomerRepository.getSamsungProduct();
        assertEquals("Matching", "Samsung", samsungCustomers.get(0).getCustomerProduct().getProductName());

    }
    @Test
    public void testGetSamsungProductFail() {

        iCustomerRepository.insert(customer);
        iCustomerRepository.insert(customer1);
        List<Customer> samsungCustomers = iCustomerRepository.getSamsungProduct();
        assertNotEquals("Matching", "Nokia", samsungCustomers.get(0).getCustomerProduct().getProductName());

    }

}
