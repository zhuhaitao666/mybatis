package com.xxx.test;

import com.xxx.dao.CustomerDynamicSqlMapper;
import com.xxx.model.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DynamicSqlTest {
    String Reource="mybatis-config.xml";
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    @Test
    public void getCustomerByIf()
    {
        try {
            Reader reader= Resources.getResourceAsReader(Reource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerDynamicSqlMapper customerDynamicSqlMapper=sqlSession.getMapper(CustomerDynamicSqlMapper.class);
            Customer c=new Customer();
//            c.setUsername("%2%");
//            List<Customer> customers=customerDynamicSqlMapper.getCustomersByIf(c);
//            for (Customer cus:customers) {
//                System.out.println(cus);
//            }
            //测试应用set标签更新
            c.setId(1);
            c.setUsername("admin");
            c.setPhone("18701623551");
            customerDynamicSqlMapper.update(c);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            sqlSession.close();
        }
    }
    @Test
    public void getDepartmentAndCustomerByStep()
    {
        try {
            Reader reader= Resources.getResourceAsReader(Reource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerDynamicSqlMapper customerDynamicSqlMapper=sqlSession.getMapper(CustomerDynamicSqlMapper.class);
            List ids=new ArrayList();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            List<Customer> customers =customerDynamicSqlMapper.getCustomersByForeach(ids);
            for (Customer c:customers) {
                System.out.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            sqlSession.close();
        }
    }
    @Test
    public void L2CacheTest()
    {
        try {
            Reader reader= Resources.getResourceAsReader(Reource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            SqlSession sqlSession1=sqlSessionFactory.openSession();

            CustomerDynamicSqlMapper customerDynamicSqlMapper=sqlSession.getMapper(CustomerDynamicSqlMapper.class);
            CustomerDynamicSqlMapper customerDynamicSqlMapper1=sqlSession1.getMapper(CustomerDynamicSqlMapper.class);

            Customer c1=customerDynamicSqlMapper.getCustomerById(1);
            System.out.println(c1);
            sqlSession.close();

            Customer c2=customerDynamicSqlMapper1.getCustomerById(1);
            System.out.println(c2);
            System.out.println(c1==c2);
            sqlSession1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
