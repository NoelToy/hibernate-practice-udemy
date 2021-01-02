package com.noel.hibernate;

import com.noel.hibernate.entity.Employee;
import org.hibernate.Session;

import java.util.List;

public class ReadEmployee {

    public static Employee retriveEmployeeById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = new Employee();
        try {
            session.beginTransaction();
            employee = session.get(Employee.class,id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return employee;
    }
    public static List<Employee> rettiveEmployeeByCompany(String company){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            List employeeList = session.createQuery("from Employee emp where emp.company ='"+company+"'").getResultList();
            session.getTransaction().commit();
            return employeeList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

    }
}
