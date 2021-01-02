package com.noel.hibernate;

import com.noel.hibernate.entity.Employee;
import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class HibernateMainApp {


    public static void main(String[] args){
        //TestConnection();
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        do{
            System.out.println("\nWelcome To Employee Registration\n");
            System.out.println("1. Add New Employee");
            System.out.println("2. Retrieve Employee Details by Id");
            System.out.println("3. Retrieve Employees Details by Company");
            System.out.println("4. Delete Employee by Id");
            System.out.println("5. Exit");
            System.out.println("Please Enter Your Choice:");
            choice = Integer.parseInt(scanner.next());

            switch (choice){
                case 1:
                    saveEmployee();
                break;
                case 2:
                    readEmployeeById();
                break;
                case 3:
                    readEmployeeByCompany();
                break;
                case 4:
                    deleteEmployeeById();
                break;
                case 5:
                    System.out.println("Exiting.....");
                break;
                default:
                    System.out.println("Please Enter a Valid Choice.");
            }

        }while (choice!=5);
    }

    private static void saveEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String firstName =  scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Company Name:");
        String company= scanner.next();
        SaveEmployee.saveEmployee(firstName,lastName,company);
        System.out.println("Employee Saved Successfully.");
    }
    private static void readEmployeeById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Id:");
        Integer id = scanner.nextInt();
        Employee employee = ReadEmployee.retriveEmployeeById(id);
        System.out.println(employee);
    }
    private static void readEmployeeByCompany(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Company Name:");
        String company = scanner.next();
        List<Employee> employees = ReadEmployee.rettiveEmployeeByCompany(company);
        for(Employee employee:employees){
            System.out.println(employee);
        }
    }
    private static void deleteEmployeeById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Id:");
        Integer id = scanner.nextInt();
        Employee employee = DeleteEmployee.deleteEmployeeById(id);
        System.out.println(employee);
    }
    private static void TestConnection() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String result = (String)session.createNativeQuery("SELECT version()").getSingleResult();
            session.getTransaction().commit();
            System.out.println(result);
        }
        finally {
            session.close();
        }
    }
}
