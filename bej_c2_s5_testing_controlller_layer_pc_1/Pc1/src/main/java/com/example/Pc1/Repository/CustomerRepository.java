package com.example.Pc1.Repository;

import com.example.Pc1.Domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {


    //retrieving with details with the phone number
    //public List<Customer> findByproductName(String product);




    @Query("{'customerProduct.productName':'Samsung'}") // to fetch the nested document
    List<Customer> getSamsungProduct();

}
