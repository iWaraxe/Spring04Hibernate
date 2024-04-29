package com.coherentsolutions.chapter2.section24;

import com.coherentsolutions.chapter2.section24.entity.Department;
import com.coherentsolutions.chapter2.section24.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerVsLazyLoading02 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Get department");
            Department department = session.get(Department.class, 3);

            System.out.println("Show department");
            System.out.println(department);
            System.out.println("Show employees department");
            System.out.println(department.getEmployeeList()); // what will happen if we move employeeList after commit?
            System.out.println("Done!");

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
