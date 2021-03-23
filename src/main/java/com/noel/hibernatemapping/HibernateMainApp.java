package com.noel.hibernatemapping;

import com.noel.hibernatemapping.dboperationsonetoone.DeleteInstructor;
import com.noel.hibernatemapping.dboperationsonetoone.RetreiveInstuctor;
import com.noel.hibernatemapping.dboperationsonetoone.SaveInstructor;
import com.noel.hibernatemapping.entity.Course;
import com.noel.hibernatemapping.entity.Instructor;
import com.noel.hibernatemapping.entity.InstructorDetail;
import com.noel.hibernatemapping.entity.Review;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HibernateMainApp {

    public static void main(String[] args)throws Exception{
        Integer choice;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("\nWelcome To Instructor Registration\n");
            System.out.println("1. Add New Instructor");
            System.out.println("2. Retrieve Instructor Details by Id");
            System.out.println("3. Retrieve Instructor Details by Detail Id");
            System.out.println("4. Delete Instructor by Id");
            System.out.println("5. Delete Instructor Details by Id");
            System.out.println("6. Add Course to Instructor");
            System.out.println("7. Add Course Review");
            System.out.println("8. Retrieve Course Review");
            System.out.println("9. Delete Course");
            System.out.println("10. Exit");
            System.out.println("Please Enter Your Choice:");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    saveInstructor();
                break;
                case 2:
                    retreiveInstructorById();
                break;
                case 3:
                    retreiveInstructorByInstructorDetailId();
                break;
                case 4:
                     deleteInstructorById();
                 break;
                case 5:
                    deleteInstructorByDetailsId();
                break;
                case 6:
                    saveCoursesToInstructor();
                case 7:
                    addCourseReview();
                break;
                case 8:
                    getCourseReview();
                break;
                case 9:
                    deleteCourse();
                break;
                case 10:
                    System.out.println("Exiting.....");
                break;
                default:
                    System.out.println("Please Enter a Valid Choice");
            }
        }while(choice!=10);
    }
    public static void saveInstructor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String firstName =  scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Email:");
        String email= scanner.next();
        System.out.println("Enter Youtube Channel:");
        String youtubeChannel= scanner.next();
        System.out.println("Enter Hobby:");
        String hobby= scanner.next();
        InstructorDetail instructorDetail = new InstructorDetail(youtubeChannel,hobby);
        Instructor instructor = new Instructor(firstName,lastName,email,instructorDetail);
        SaveInstructor.saveInstructor(instructor);
        System.out.println("Instructor Saved.");
    }
    public static void retreiveInstructorById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id:");
        Integer id =  scanner.nextInt();
        Instructor instructor = RetreiveInstuctor.instructorDetailsById(id);
        System.out.println(instructor);
    }
    public static void retreiveInstructorByInstructorDetailId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id:");
        Integer id =  scanner.nextInt();
        Instructor instructor = RetreiveInstuctor.instructorDetailsByDetailsId(id);
        if(instructor==null){
            System.out.println("Record Not Found.");
        }
        else {
            System.out.println(instructor);
        }
    }
    public static void deleteInstructorById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id:");
        Integer id =  scanner.nextInt();
        Instructor instructor = DeleteInstructor.deleteInstructor(id);
        System.out.println(instructor);
    }
    public static void deleteInstructorByDetailsId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Id:");
        Integer id =  scanner.nextInt();
        Instructor instructor = DeleteInstructor.deleteInstructorByDetail(id);
        System.out.println(instructor);
    }
    public static void saveCoursesToInstructor()throws Exception{
        Scanner scanner = new Scanner(System.in);
        Integer courseCount;
        String courseTitle;
        System.out.println("Enter Id:");
        Integer id =  scanner.nextInt();
        Instructor instructor = RetreiveInstuctor.instructorDetailsById(id);
        System.out.println("Please enter the number of courses:");
        courseCount = scanner.nextInt();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        for(Integer i=0;i<courseCount;i++){
            System.out.println("Enter Course Title:");
            courseTitle = reader.readLine();
            Course course = new Course(courseTitle);
            instructor.add(course);
            SaveInstructor.saveInstructor(course);
        }
    }
    public static void addCourseReview()throws Exception{
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        Integer reviewCount;
        String review;
        String courseTitle;
        System.out.println("Enter Course Title:");
        courseTitle = reader.readLine();
        Course course = new Course(courseTitle);
        System.out.println("Please enter the number of reviews:");
        reviewCount = scanner.nextInt();
        for(Integer i=0;i<reviewCount;i++){
            System.out.println("Enter Review:");
            review = reader.readLine();
            course.add(new Review(review));
        }
        SaveInstructor.saveInstructor(course);
    }
    public static void getCourseReview(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Course Id:");
        Integer courseId = scanner.nextInt();
        Course course = RetreiveInstuctor.courseDetailsById(courseId);
        for(Review review : course.getReviews()){
            System.out.println(review.getComment());
        }
    }
    public static void deleteCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Course Id:");
        Integer courseId = scanner.nextInt();
        Course course = DeleteInstructor.deleteCourseById(courseId);
        System.out.println("Deleted Course is: "+course.getTitle());
    }
}
