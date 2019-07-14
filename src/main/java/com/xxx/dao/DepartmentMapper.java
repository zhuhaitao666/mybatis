package com.xxx.dao;

import com.xxx.model.Department;

public interface DepartmentMapper {
    public Department getDepartmentById(Integer id);
    public Department getDepartmentAndCustomerById(Integer id);
    public Department getDepartmentAndCustomerByStep(Integer id);

}
