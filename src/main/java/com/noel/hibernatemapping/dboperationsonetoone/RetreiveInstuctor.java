package com.noel.hibernatemapping.dboperationsonetoone;

import com.noel.hibernatemapping.HibernateUtil;
import com.noel.hibernatemapping.entity.Course;
import com.noel.hibernatemapping.entity.Instructor;
import com.noel.hibernatemapping.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RetreiveInstuctor {
    public static Instructor instructorDetailsById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Instructor> query = session.createQuery("SELECT i from Instructor i join fetch i.courses where i.id=:theInstructorId",Instructor.class);
            query.setParameter("theInstructorId",id);
            session.getTransaction().commit();
            Instructor instructor = query.getSingleResult();
            return instructor;
        }
        finally {
            session.close();
        }
    }
    public static Instructor instructorDetailsByDetailsId(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class,id);
            session.getTransaction().commit();
            if(instructorDetail==null){
                return null;
            }
            return instructorDetail.getInstructor();
        }
        finally {
            session.close();
        }
    }
    public static Course courseDetailsById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<Course> query = session.createQuery("SELECT c from Course c join fetch c.reviews where c.id=:courseId",Course.class);
            query.setParameter("courseId",id);
            Course course = query.getSingleResult();
            session.getTransaction().commit();
            if(course==null){
                return null;
            }
            return course;
        }
        finally {
            session.close();
        }
    }
}
