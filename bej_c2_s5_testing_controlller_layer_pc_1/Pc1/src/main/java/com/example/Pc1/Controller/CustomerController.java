package com.example.Pc1.Controller;

import com.example.Pc1.Domain.Customer;
import com.example.Pc1.Exception.CustomerExistsException;
import com.example.Pc1.Exception.CustomerNotFoundException;
import com.example.Pc1.Service.CustomerServiceImpl;
import com.example.Pc1.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CustomerController {


    ICustomerService customerServiceImpl;

    @Autowired
    public CustomerController(ICustomerService customerServiceImpl)
    {
        this.customerServiceImpl = customerServiceImpl;
    }
    //http://localhost:65500/api/v1/customer
    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomers(@RequestBody Customer customer) throws CustomerExistsException {
        try{
            return new ResponseEntity<>(customerServiceImpl.saveCustomer(customer), HttpStatus.CREATED);
        }catch(CustomerExistsException exception)
        {
            throw exception;
        }
    }
    //http://localhost:65500/api/v1/customers


    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerServiceImpl.getCustomers(), HttpStatus.OK);
    }

    //http://localhost:65500/api/v1/delete-customer/{id}
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<?> deleteMusic(@PathVariable int id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerServiceImpl.deleteCustomers(id), HttpStatus.OK);
    }


    //http://localhost:65500/api/v1/fetchSamsungData
    @GetMapping("/fetchSamsungData")
    public ResponseEntity<?> getSamsungData() {
        return new ResponseEntity<>(customerServiceImpl.getSamsungData(), HttpStatus.OK);
    }

    //    http://localhost:66550/api/v1/updateCustomer/{id}
//    @PutMapping("/updateCustomer/{id}")
//    public ResponseEntity<?> updateProduct(@RequestBody Customer customer, @PathVariable int id) {
//        return new ResponseEntity<>(customerServiceImpl.updateCustomer(customer, id), HttpStatus.OK);
//    }
//    //http://localhost:66550/api/v1/customer/{id}
//    @GetMapping("customer/{id}")
//    public ResponseEntity getCustomerById(@PathVariable int id) {
//        return new ResponseEntity(customerServiceImpl.getCustomerById(id), HttpStatus.OK);
//    }
//    //http://localhost:66550/api/v1/musics/{name}
//    @GetMapping("/customers/{name}")
//    public ResponseEntity<?> getProductByName(@PathVariable String name) {
//        return new ResponseEntity<>(customerServiceImpl.getCustomerByName(name), HttpStatus.OK);
//    }


}
