package com.noel.hibernatemapping.dboperationsonetoone;

import com.noel.hibernatemapping.HibernateUtil;
import com.noel.hibernatemapping.entity.Course;
import com.noel.hibernatemapping.entity.Instructor;
import com.noel.hibernatemapping.entity.InstructorDetail;
import org.hibernate.Session;

public class DeleteInstructor {
    public static Instructor deleteInstructor(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class,id);
            session.delete(instructor);
            session.getTransaction().commit();
            return instructor;
        }
        finally {
            session.close();
        }
    }
    public static Instructor deleteInstructorByDetail(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class,id);
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);
            session.getTransaction().commit();
            return instructorDetail.getInstructor();
        }
        finally {
            session.close();
        }
    }
    public static Course deleteCourseById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Course course = session.get(Course.class,id);
            session.delete(course);
            session.getTransaction().commit();
            return course;
        }
        finally {
            session.close();
        }
    }
}
