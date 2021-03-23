package com.noel.hibernatemapping;

import com.noel.hibernatemapping.entity.Course;
import com.noel.hibernatemapping.entity.Instructor;
import com.noel.hibernatemapping.entity.InstructorDetail;
import com.noel.hibernatemapping.entity.Review;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try{
                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
                properties.put(Environment.URL,"jdbc:mysql://localhost:3306/hb_instructor_tracker?useSSL=false&serverTimezone=UTC");
                properties.put(Environment.SHOW_SQL,true);
                properties.put(Environment.USER,"root");
                properties.put(Environment.PASS,"admin");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                properties.put(Environment.POOL_SIZE,1);

                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(InstructorDetail.class);
                configuration.addAnnotatedClass(Instructor.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Review.class);
                configuration.addProperties(properties);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
