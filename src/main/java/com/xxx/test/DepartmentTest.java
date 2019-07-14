package com.xxx.test;

import com.xxx.dao.DepartmentMapper;
import com.xxx.model.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class DepartmentTest {
    String Resource="mybatis-config.xml";
    Reader reader;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    @Test
    public void getDepartmentAndCustomerById(){
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
            Department department =departmentMapper.getDepartmentAndCustomerById(2);
            System.out.println(department);
            System.out.println(department.getCustomers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

    }
    @Test
    public void getDepartmentAndCustomerByStep(){
        try {
            reader= Resources.getResourceAsReader(Resource);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);
            Department department =departmentMapper.getDepartmentAndCustomerByStep(1);
            System.out.println(department);
            System.out.println(department.getCustomers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

    }
}
