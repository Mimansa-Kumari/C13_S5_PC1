package com.example.Pc1.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Document
@EqualsAndHashCode
public class Customer {
    @Id
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private Product customerProduct;
}
