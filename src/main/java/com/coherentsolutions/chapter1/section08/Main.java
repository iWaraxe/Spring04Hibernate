package com.coherentsolutions.chapter1.section08;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.coherentsolutions.chapter1.entity.Employee;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Update a persistent object
        updateEmployee(sessionFactory);

        // Update a detached object
        updateDetachedEmployee(sessionFactory, getDetachedEmployee());

        sessionFactory.close();
    }

    public static void updateEmployee(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, 1);  // Assume an employee with ID 1 exists
        if (employee != null) {
            employee.setSalary(50000);
            // No need to call session.save() or session.update(); changes are tracked automatically
        }

        transaction.commit();
        session.close();
        System.out.println("Employee updated successfully.");
    }

    public static Employee getDetachedEmployee() {
        // This is a placeholder for the method that would typically fetch a detached employee
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setSalary(55000);
        return employee;
    }

    public static void updateDetachedEmployee(SessionFactory sessionFactory, Employee detachedEmployee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(detachedEmployee);

        transaction.commit();
        session.close();
        System.out.println("Detached employee updated successfully.");
    }
}
