package com.coherentsolutions.chapter1.section04;

import com.coherentsolutions.chapter1.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Nikita", "Mekhanikov", "TrainingCenter", 5500);
            System.out.println(employee);
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
