package com.coherentsolutions.chapter2.section22;

import com.coherentsolutions.chapter2.section22.entity.Department;
import com.coherentsolutions.chapter2.section22.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Relation2OneToManyBiDirectional {
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

            Department department = session.get(Department.class, 2);
            System.out.println(department);
            System.out.println(department.getEmployeeList());

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
