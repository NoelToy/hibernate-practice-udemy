package com.noel.hibernatemapping.dboperationsonetoone;

import com.noel.hibernatemapping.HibernateUtil;
import org.hibernate.Session;

public class SaveInstructor {
    public static void saveInstructor(Object instructor){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(instructor);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
}
