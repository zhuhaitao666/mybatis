package com.xxx.test;


import com.xxx.model.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class Testmodel {
    String resource="mybatis-config.xml";
    @Test
    public void findCustomerByIdTest(){

        try {
//            Reader reader =Resources.getResourceAsReader(resource);
            InputStream inputStream= Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            Customer customer=sqlSession.selectOne("findCustomerById",1);
            System.out.println(customer.toString());
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findCustomerByNameTest(){
        try {
            InputStream inputStream=Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            List<Customer> customers=sqlSession.selectList("findCustomerByName","2");
            for (Customer customer:customers) {
                System.out.println(customer);
            }
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addOneCostomer(){
        try {
            //通过输入流得到配置信息
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            Customer c=new Customer();
            c.setJobs("java工程师");
            c.setPhone("18337666124");
            c.setUsername("朱海涛");
            int rows= sqlSession.insert("addOneCustomer",c);//返回值是影响的行数
            if(rows>0) {
                System.out.println("插入成功");
            }
            else {
                System.out.println("插入失败");
            }
            System.out.println("插入一条记录的主键值"+c.getId());
            sqlSession.commit();//提交事务
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateOneCustomer(){

        try {
            Reader reader=Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            Customer c=new Customer();
            c.setUsername("223");
            c.setJobs("新工作");
            c.setPhone("新手机号");

            int rows=sqlSession.update("UpdateOneCustomer",c);
            if (rows>0)
            {
                System.out.println("更新成功，更新的主键为"+c.getId());
            }
            else
            {
                System.out.println("更新失败！！！");
            }
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findCustomerByIdSqlTest(){
        try {
//            Reader reader =Resources.getResourceAsReader(resource);
            InputStream inputStream= Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            Customer customer=sqlSession.selectOne("findCustomersByIdSql",1);
            System.out.println(customer.toString());
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
