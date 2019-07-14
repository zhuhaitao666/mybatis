package com.xxx.test;

import com.xxx.dao.CustomerMapper;
import com.xxx.model.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class ResultMapper {
    String Resource="mybatis-config.xml";
    Reader reader;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    @Test
    public void getCustomerByResultMapper()
    {
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Customer c=customerMapper.findCustomerByResultMap(1);
            System.out.println(c);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void findCustomerAndDept()
    {
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Customer c=customerMapper.findCustomerAndDept(2);
            System.out.println(c);
            System.out.println(c.getDept());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
    @Test
    public void findCustomerAndDeptByStep()
    {
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Customer c=customerMapper.findCustomerAndDeptByStep(3);
            System.out.println(c);
            System.out.println(c.getDept());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
    @Test
    public void findCustomerByDiscriminatorAndStep()
    {
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);

            //鉴别器鉴别如果电话是3 就分步查出部门。不然部门对象为空
            Customer c=customerMapper.findCustomerByDiscriminatorAndStep(3);

            System.out.println(c);
            System.out.println(c.getDept());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
}
