package com.noel.hibernate;

import com.noel.hibernate.entity.Employee;
import org.hibernate.Session;

public class DeleteEmployee {

    public static Employee deleteEmployeeById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Employee employee = session.get(Employee.class,id);
            session.delete(employee);
            session.getTransaction().commit();
            return employee;
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
