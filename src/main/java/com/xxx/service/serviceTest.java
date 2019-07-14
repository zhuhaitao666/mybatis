package com.xxx.service;

import com.xxx.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class serviceTest {
    @Autowired
    @Qualifier("sdfsd")
    private Customer c;
}
