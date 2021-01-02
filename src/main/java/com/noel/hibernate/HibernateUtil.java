package com.noel.hibernate;

import com.noel.hibernate.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

//@PropertySource("classpath:application.properties")
public class HibernateUtil {

    /*@Value("${db.driverClassName}")
    private static String driver;

    @Value("${db.url}")
    private static String url;

    @Value("${db.dialect}")
    private static String dialect;

    @Value("${db.username}")
    private static String username;

    @Value("${db.password}")
    private static String password;

    @Value("${db.pool.initialSize}")
    private static Integer poolSize;*/

    private  static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){

        if(sessionFactory==null){
            try{
                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
                properties.put(Environment.URL,"jdbc:mysql://localhost:3306/hb_employee_tracker?useSSL=false&serverTimezone=UTC");
                properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
                properties.put(Environment.USER,"root");
                properties.put(Environment.PASS,"admin");
                properties.put(Environment.POOL_SIZE,1);
                properties.put(Environment.SHOW_SQL,true);
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");

                Configuration configuration = new Configuration();
                configuration.addProperties(properties);
                configuration.addAnnotatedClass(Employee.class);
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
