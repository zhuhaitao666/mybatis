package com.xxx.dao;

import com.xxx.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDynamicSqlMapper {
    public List<Customer> getCustomersByIf(Customer customer);
    public void update(Customer customer);
    public List<Customer> getCustomersByForeach(@Param("ids") List <Integer>ids);
    public Customer getCustomerById(Integer id);
}
