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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    接口式编程
    SqlSession 表示一次和数据库的对话，不是线程安全的，必须要关闭该对象
 */
public class CustomerTest {
    String Resource="mybatis-config.xml";
    Reader reader;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    @Test
    public void getCustomerByID(){
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            //mybatis为接口实现代理对象
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Customer customer =customerMapper.getCustomerById(1);
            System.out.println(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }
    }
    @Test
    public void findCustomerByNameAndJobs(){

        try {
            reader=Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);

            Map<String,Object> map=new HashMap();
            map.put("jobs","3");
            map.put("username","2");
            Customer customer1=customerMapper.findCustomerByMap(map);
            System.out.println("通过map对象来绑定多个参数"+customer1);
            Customer customer=customerMapper.findCustomerByNameAndJobs("2","3");
            System.out.println("通过接口中的@Param绑定传递的多个参数"+customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        }
        sqlSession.close();
    }
    @Test
    public void findCustomerByNameReturnMap(){

        try {
            reader=Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Map <String,Object> map=customerMapper.findCustomerByNameReturnMap("%32%");
            Set set = map.keySet();
            Iterator iterator= set.iterator();
            while(iterator.hasNext())
            {
                String key= (String)iterator.next();
                System.out.println(key);
            }
            System.out.println(map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

    }
    @Test
    public void findCustomerByNameReturnCustomerMap(){
        try {
            reader=Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
            Map <Integer,Customer> map=customerMapper.findCustomerByNameReturnCustomerMap("%2%");

            Set <Integer>set = map.keySet();
            for(Integer key: set)
            {
                System.out.println(map.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

    }
}
