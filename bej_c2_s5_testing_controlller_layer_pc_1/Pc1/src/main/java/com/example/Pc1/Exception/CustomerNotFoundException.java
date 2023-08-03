package com.example.Pc1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Customer Not Found ")
public class CustomerNotFoundException extends Exception{
}
