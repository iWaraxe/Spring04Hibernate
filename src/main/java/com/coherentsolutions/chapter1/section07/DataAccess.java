package com.coherentsolutions.section07;

import com.coherentsolutions.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DataAccess {
    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Employee.class)
            .buildSessionFactory();

    public static Employee getEmployee(int id) {
        try (Session session = factory.openSession()) {
            Transaction txn = session.beginTransaction();
            try {
                Employee employee = session.get(Employee.class, id);
                session.getTransaction().commit();
                return employee;
            }
            catch (Exception e) {
                txn.rollback();
                throw e; // or handle the exception as necessary
            }
        }
    }

    public static Employee loadEmployee(int id) {
        try (Session session = factory.openSession()) {
            Employee employee = session.load(Employee.class, id);
            System.out.println(employee.getName());  // Access triggers the actual database hit
            return employee;
        } // session automatically closed here
    }


    public static List<Employee> findAllEmployees() {
        try (Session session = factory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.list();
        } // session automatically closed here
    }

    public static Employee saveAndGetEmployee() {
        try (Session session = factory.openSession()) {
            Employee employee = new Employee("Philip", "Goldsmith", "Finances", 1000);
            System.out.println(employee);
            Transaction txn = session.beginTransaction();
            try {
                session.save(employee);
                int myId = employee.getId();
                employee = session.get(Employee.class, myId);
                session.getTransaction().commit();
                System.out.println(employee);
                return employee;
            }
            catch (Exception e) {
                txn.rollback();
                throw e; // or handle the exception as necessary
            }
        }
    }

    public static void closeFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}
