package com.xxx.dao;

import com.xxx.model.Customer;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    public Customer getCustomerById(Integer id);
    public Customer findCustomerByNameAndJobs(@Param("username") String username, @Param("job") String jobs);
    public Customer findCustomerByMap(Map<String,Object> map);
    //返回单条记录 key对应的属性列，value中的值对应的是其中的值
    public Map<String,Object> findCustomerByNameReturnMap(String username);
    //返回多条记录。 告诉mybatis用哪一个属性作为主键
    @MapKey("id")
    public Map<Integer,Customer>findCustomerByNameReturnCustomerMap(String username);
    public Customer findCustomerByResultMap(Integer id);
    //级联查询
    public Customer findCustomerAndDept(Integer id);
    public Customer findCustomerAndDeptByStep(Integer id);
    public List<Customer> findCustomersByDepartment(Integer did);

    public Customer findCustomerByDiscriminatorAndStep(Integer id);
}
