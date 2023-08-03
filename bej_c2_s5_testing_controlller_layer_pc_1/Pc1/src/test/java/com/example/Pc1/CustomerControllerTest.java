package com.example.Pc1;

import com.example.Pc1.Controller.CustomerController;
import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Domain.Product;
import com.example.Pc1.Exception.CustomerExistsException;
import com.example.Pc1.Exception.CustomerNotFoundException;
import com.example.Pc1.Service.ICustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private ICustomerService iCustomerService;
    @InjectMocks
    private CustomerController customerController;

    //predefined class for the mockMVC
    @Autowired
    private MockMvc mockMvc;

    private Product product;

    private Customer customer;

    private Product product1;
    private Customer customer1;


    @BeforeEach
    public void setUp(){
        product= new Product(1,"Samsung", "Samsung Mobile Phones");
        customer=new Customer(100, "Abc", "989196792", product);
        product1= new Product(2,"Nokia", "Nokia Mobile Phones");
        customer1=new Customer(101, "Abc", "989196792", product1);

        mockMvc= MockMvcBuilders.standaloneSetup(customerController).build(); // standaloneSetup does not depend on any server to run it runs on its own

    }

    @AfterEach
    public void tearDown(){
        product=null;
        customer=null;
        product1=null;
        customer1=null;
    }
    @Test
    public void testAddCustomer() throws Exception {
        when(iCustomerService.saveCustomer(customer)).thenReturn(customer);
        mockMvc.perform(
                        post("/api/v1/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertJson(customer)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(iCustomerService, times(1)).saveCustomer(customer);
    }
    @Test
    public void testSaveCustomerFail() throws Exception {
        when(iCustomerService.saveCustomer(customer)).thenThrow(CustomerExistsException.class);

        mockMvc.perform(
                        post("/api/v1/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertJson(customer)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testDeleteSuccess() throws  Exception{
        when(iCustomerService.deleteCustomers(customer.getCustomerId())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/delete-customer/100"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(iCustomerService, times(1)).deleteCustomers(customer.getCustomerId());
    }
    @Test
    public void testDeleteFail() throws Exception {
        when(iCustomerService.deleteCustomers(customer.getCustomerId())).thenThrow(CustomerNotFoundException.class);
        mockMvc.perform(delete("/api/v1/delete-customer/100"))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(iCustomerService, times(1)).deleteCustomers(customer.getCustomerId());
    }


    @Test
    public void testGetSamsungProductSuccess() throws Exception {
        List<Customer> testlist=new ArrayList<>();
        testlist.add(customer);
        when(iCustomerService.getSamsungData()).thenReturn(testlist);
        mockMvc.perform(get("/api/v1/fetchSamsungData")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(iCustomerService, times(1)).getSamsungData();
    }


    //Method to convert the java object to json object is shown below
    public static String convertJson(final Object object){
        String result="";
        ObjectMapper mapper=new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
