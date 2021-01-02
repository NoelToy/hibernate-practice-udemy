package com.noel.hibernate;

import com.noel.hibernate.entity.Employee;
import org.hibernate.Session;

public class SaveEmployee {
    public static void saveEmployee(String firstName,String lastName,String company){

        Employee employee = new Employee(firstName,lastName,company);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }
}
